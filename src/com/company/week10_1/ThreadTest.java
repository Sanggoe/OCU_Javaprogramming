package com.company.week10_1;

public class ThreadTest extends Thread {
    public ThreadTest(String str) {
        super(str);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(2);
                System.out.println(i + getName());
            }
            System.out.println("끝" + getName());
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
