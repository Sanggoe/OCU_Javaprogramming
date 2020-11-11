package com.company.week;

import javax.swing.*;

public class ConitionalOperation {
    public static void main(String[] args) {
        String inString = JOptionPane.showInputDialog("Input number");
        int num = Integer.parseInt(inString);

        if ((num%2) == 0) {
            System.out.println(num + ":Even number!!");
        }
        System.out.println("The end");
    }
}
