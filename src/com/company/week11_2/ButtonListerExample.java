package com.company.week11_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListerExample {
    public static int numClicks = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("I'm a Swing button");
        JLabel label = new JLabel("Number of button clicks: 0");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListerExample.numClicks++;
                label.setText("Number of button clicks:" + numClicks);
            }
        });

        panel.setLayout(new GridLayout(2,1));
        panel.add(button);
        panel.add(label);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}