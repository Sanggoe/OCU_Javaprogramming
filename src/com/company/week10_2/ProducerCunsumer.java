package com.company.week10_2;

public class ProducerCunsumer {
    public static void main(String[] args) {
        Buffer c = new Buffer();
        Producer p1 = new Producer(c);
        Cunsumer c1 = new Cunsumer(c);

        p1.start();
        c1.start();
    }
}
