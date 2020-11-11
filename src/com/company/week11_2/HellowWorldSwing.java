package com.company.week11_2;

import javax.swing.*;

public class HellowWorldSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("HelloWorldSwing");
        final JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Object[] options = {"Yes!", "No, I'll pass", "Well, if I must"};
        int n = JOptionPane.showOptionDialog(frame, "Duke is a cartoon mascot. \n" +
                "Do you still want to cast your vote?",
                "A follow-up Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);


    }
}
