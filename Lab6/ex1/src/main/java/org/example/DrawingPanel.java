package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices; // nr varfuri
    private double edgeProbability; // prob pt crearea unei linii intre 2 vf
    private int[] x, y;
    BufferedImage image; //imaginea in afara ecranului
    Graphics2D graphics; //instrumentele necesare pentru a desena in imagine
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
//        createBoard();
    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H)); //seteaza dimensiunea panoului
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                //TODO...
//                repaint();
//            }
        });
    }
    private void createOffscreenImage() { // creaza o imagine in afara ecranului si seteaza fundalul alb
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }
    final void createBoard() {
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaintBoard();
    }
    private void createVertices() { // calc coordonatele vf in fct de nr de vf si deseneaza punctele negre in pozitiile lor
        int x0 = W / 2; int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }
    private void drawLines() { //deseneaza liniile intre vf in fct de probabilitatea de creare a unei linii intre ele
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++) { //  parcurg fiecare pereche de vf si daca prob de creare a unei linii intre cele doua vf este satisfăcuta, atunci trasez
                                                // o linie neagra intre cele doua vf
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }
    }

    private void drawVertices() { //deseneaza punctele negre pentru fiecare varf
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++) {
            graphics.fillOval(x[i] - 5, y[i] - 5, 10, 10);
        }
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public void setEdgeProbability(double edgeProbability) {
        this.edgeProbability = edgeProbability;
    }

    @Override
    public void update(Graphics g) { } // pt a evita redimensionarea
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public void updateBoard(int dots, Double lineType) { // actualizeaza valorile
        this.numVertices=dots;
        this.edgeProbability=lineType;
    }

    public void repaintBoard() { //  redeseneaza panoul
        createVertices();
        drawVertices();
        super.repaint();
    }
}
