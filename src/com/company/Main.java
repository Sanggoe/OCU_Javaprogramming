package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello Java World!");
        int a = 0;

        try {
            System.out.println(10/a);
        } catch (ArithmeticException arithmeticException) {
            System.out.println("error");
        }
    }
}
