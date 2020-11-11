package com.company.week10_2;

public class Producer extends Thread {
    private Buffer blank;

    public Producer(Buffer blank) {
        this.blank = blank;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            blank.put(i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Cunsumer extends Thread {
    private Buffer blank;

    public Cunsumer(Buffer blank) {
        this.blank = blank;
    }

    @Override
    public void run() {
        int value = 0;
        for (int i=0; i<10; i++) {
            value = blank.get();
        }
    }
}

class Buffer {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("소비자########## : 소비 " + contents);
        notify();
        available = false;

        return contents;
    }

    public synchronized void put(int value) {
        while(available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("생산자########## : 생산 " + contents);
        notify();
        available = true;
    }
}