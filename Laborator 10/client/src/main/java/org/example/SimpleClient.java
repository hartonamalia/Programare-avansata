package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
    /**
     * Creezi un client care se conecteaza la server
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            Scanner input = new Scanner(System.in);
            String cmd;
            while (true) {
                System.out.println("command: ");
                cmd = input.nextLine();
                if (cmd.equals("exit")) {
                    System.out.print("exiting...");
                    break;
                }
                out.println(cmd);
                if (cmd.equals("stop")) {
                    System.out.print("stopping...");
                    break;
                }
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (SocketException e) {
            System.out.println("Connection closed by server!");
        }
    }
}
