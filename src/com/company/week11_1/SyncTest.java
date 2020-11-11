package com.company.week11_1;

public class SyncTest extends Thread {
    static int a = 0;
    String s;

    public SyncTest(String s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            synchronized ((Object) a) {
                System.out.println(s + a);
                a++;
            }
        }
    }

    public static void main(String[] args) {
        SyncTest s1 = new SyncTest("첫");
        SyncTest s2 = new SyncTest("두");
        s1.start();
        s2.start();
    }

}
