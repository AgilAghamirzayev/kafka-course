package org.udemy.consumer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DotDrawer implements ActionListener {

    static JButton choice = new JButton();

    private static final JButton magentaButton = new JButton("Draw Magenta Dot");
    private static final JButton greenButton = new JButton("Draw Green Dot");
    private static final JButton blueButton = new JButton("Draw Blue Dot");
    Drawing draw = new Drawing();


    public DotDrawer() {
        JFrame frame = new JFrame("Draw Shapes");
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(panel, "North");
        frame.setVisible(true);
        frame.add(draw);

        panel.add(magentaButton);
        panel.add(greenButton);
        panel.add(blueButton);

        magentaButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        choice = (JButton) e.getSource();
        draw.repaint();
    }

     static class Drawing extends JComponent {
         public void paint(Graphics g) {
             if (choice == magentaButton) {
                 g.setColor(Color.MAGENTA);
                 g.fillOval(100, 100, 100, 100);
             } else if (choice == greenButton) {
                 g.setColor(Color.GREEN);
                 g.fillOval(100, 100, 200, 200);
             } else if (choice == blueButton) {
                 g.setColor(Color.BLUE);
                 g.fillOval(100, 100, 300, 300);
             }
         }
     }


    public static void main(String[] args) {
        new DotDrawer();
    }
}