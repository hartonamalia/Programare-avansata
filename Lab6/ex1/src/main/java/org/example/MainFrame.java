package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }


        private void init() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // creez componentele
            canvas = new DrawingPanel(this);

            configPanel = new ConfigPanel(this);

            controlPanel = new ControlPanel(this);


            // adaug componenetele la cadru folosind BorderLayout

            add(configPanel, BorderLayout.NORTH);
            add(canvas, BorderLayout.CENTER);
            add(controlPanel, BorderLayout.SOUTH);

//            System.out.println("bau");


            pack();
        }

        public static void main (String[] args){
        new MainFrame().setVisible(true);
        }
}
