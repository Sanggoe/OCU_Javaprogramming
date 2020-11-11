package com.company.week;

import java.util.Scanner;

public class SwitchOperation {
    public static void main(String[] args) {
        String month;
        int monthNumber;
        Scanner sc = new Scanner(System.in);
        month = sc.next();
        switch (month) {
            case "test":
                monthNumber = 1;
                break;
            case "t":
                monthNumber = 1222;
                break;
            default:
                monthNumber = 12;
        }

        System.out.println(monthNumber);
    }
}
