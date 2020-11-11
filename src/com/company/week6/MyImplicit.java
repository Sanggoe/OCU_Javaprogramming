package com.company.week6;

public class MyImplicit {
    public static void main(String[] args) {
        int i;
        char c = 'A';
        i = c;
        System.out.println("ASCII value of A = " + i);

        float f = i;
        System.out.println(f); // 명시적 형변환
    }
}
