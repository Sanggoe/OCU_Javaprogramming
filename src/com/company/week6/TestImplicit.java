package com.company.week6;

public class TestImplicit {
    public static void main(String[] args) {
        byte b = 123;
        short s = 321;
        char c = 'A';
        int i;
        long l;
        float f = 0.456f;
        i = b + s; // 묵시적 형변환 - 덧셈 연산은 기본적으로 int로 자동 변환된 후에 수행된다
        l = i + c; // 묵시적 형변환
        f += b;
        System.out.println("i = " + i);
        System.out.println("l = " + l);
        System.out.println("f = " + f);
    }
}
