package com.company;

public class Shift {
    public static int leftShift(int i, int j) {
        return i << j;
    }

    public static void main(String[] args) {
        int i = 4, j = 10;
        i = leftShift(i, j);
        System.out.println(i);
    }
}
