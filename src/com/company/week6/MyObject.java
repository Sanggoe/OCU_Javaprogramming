package com.company.week6;

public class MyObject {
    public static void main(String[] args) {
        Object o = new Object();
        MyObject mo = new MyObject();

        o = mo;
        System.out.println(mo.equals(o));
    }
}
