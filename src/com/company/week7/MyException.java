package com.company.week7;

class MyException extends Exception {
    String name = "MyException";
    void printInfo(String msg) {
        System.out.println(name + " : ");
        System.out.println(msg);
    }
}