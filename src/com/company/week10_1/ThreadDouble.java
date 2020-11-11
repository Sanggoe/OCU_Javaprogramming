package com.company.week10_1;

public class ThreadDouble {
    public static void main(String[] args) {
        ThreadTest t1 = new ThreadTest(" : 배우기 쉬운 자바");
        ThreadTest t2 = new ThreadTest(" : 배우기 어려운 자바");

        t1.start();
        t2.start();
    }
}
