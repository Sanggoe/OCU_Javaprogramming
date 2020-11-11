package com.company.week6;

public class MyExplicit {
    public static void main(String[] args) {
        int i = 0;
        float f = 1.78f;
        i = (int) f + i;
        System.out.println("i = " + i);

        char c = 'a';
        short sh = (short) c;
        c = (char) sh;
        /**
         * 같은 변수끼리 대입 : 문제 없음
         * 작은 -> 큰 변수로 대입 : 문제 없음 (자동 promotion)
         * 큰 -> 작은 변수로 대입 : Type casting 필요
         *
         * **** char - short 간에는 반드시 명시적으로 변환해주어야 한다!
         */
    }
}
