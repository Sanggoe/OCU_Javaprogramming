package com.company.week10_1;

public class ThreadfromRunnable {
    public static void main(String[] args) {
        Runnable r = new RunnableTest();
        new Thread(r).start();
    }
}
