package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JSpinner dotsField;
    JComboBox<Double> linesCombo;
    JButton createButton;

    public ConfigPanel(MainFrame frame ) {
        this.frame = frame;
        init();
    }

    private void init() {
        // Creeaza eticheta si spinner-ul pentru nr de puncte

        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        // Creeaza eticheta si comboboxul pentru tipul de linie

        linesLabel = new JLabel("Line probability:");
        Double[]probabilities = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0};
        linesCombo = new JComboBox<>(probabilities);


        createButton = new JButton("Create new game");


        // Adauga componentele in panoul de configurare

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);

        // Creeaza butonul pentru crearea unui joc nou

        createButton.addActionListener(e -> {
            int dots = (int) dotsSpinner.getValue();
            Double lineType = (Double) linesCombo.getSelectedItem();

            // Actualizez nr de puncte si tipul de linie in panoul de desenare si deseneaza tabla

            frame.canvas.updateBoard(dots, lineType);
            frame.canvas.createBoard();
        });
    }
}
