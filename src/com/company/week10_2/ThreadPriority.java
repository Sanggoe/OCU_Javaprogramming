package com.company.week10_2;

public class ThreadPriority {
    public static void main(String[] args) {
        PriorityTest t1 = new PriorityTest(" : 첫 번째 스레드");
        PriorityTest t2 = new PriorityTest(" : 두 번째 스레드");
        PriorityTest t3 = new PriorityTest(" : 세 번째 스레드");

        int priority_t1 = Integer.parseInt(args[0]);
        int priority_t2 = Integer.parseInt(args[1]);

        t1.setPriority(priority_t1);
        t2.setPriority(priority_t2);
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
