package com.company.week10_1;

public class RunnableTest implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(2);
                System.out.println("Runnable 인터페이스로부터 생성" + i);
            }
            System.out.println("끝");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
