package com.company.week6;

public class MyObject2 {
    public static void main(String[] args) {
        Object o = new Object();
        MyObject2 mo = new MyObject2();

        mo = (MyObject2) o;
        System.out.println(mo.equals(o));
    }
}
