package com.company.week10_2;

public class PriorityTest extends Thread {

    public PriorityTest(String str) {
        super(str);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(2);
                System.out.println(i + getName() + " 우선순위 : " + getPriority());
            }

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
