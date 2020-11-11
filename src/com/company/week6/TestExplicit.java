package com.company.week6;

public class TestExplicit {
    public static void main(String[] args) {
        byte b = 100;
        short s;
        char c = 'A';
        int i;
        float f = 0.456f;

        i = (int) f + b;
        s = (short) (i+c);

        System.out.println("i = " + i);
        System.out.println("s = " + s);
    }
}
