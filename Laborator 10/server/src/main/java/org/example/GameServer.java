package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GameServer {
    public static final int PORT = 8100;
    private int serverTimeout = 30_000;
    private int clientTimeout = 20_000;
    private boolean running;

    /**
     * Metoda imi inchide serverul
     */
    public void stopServer() {
        System.out.println("Shuting down the server...");
        running = false;
    }

    /**
     * Imi initializez server-ul si astept clienti
     * @throws IOException
     */
    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        running = true;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(serverTimeout);
            while (running) {
                System.out.println("Waiting for a client ...");
                try{
                    Socket socket = serverSocket.accept();
                    socket.setSoTimeout(clientTimeout);
                    new ClientThread(this, socket).start();
                }catch (SocketTimeoutException e){
                    if (!running) {
                        System.out.println("Server stopped.");
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
    }

}
