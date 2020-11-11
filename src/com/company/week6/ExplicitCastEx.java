package com.company.week6;

public class ExplicitCastEx {
    public static void main(String[] args) {
        int i;
        float f = 1.7f;
        
        i = (int) f; // 명시적 형변환
        System.out.println(i); // 자료값의 손실
    }
}
