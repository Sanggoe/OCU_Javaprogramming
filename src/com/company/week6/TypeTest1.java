package com.company.week6;

class A {
    A() {
    }
}

class B extends A {
    B() {
    }
}

public class TypeTest1 {
    public static void main(String[] args) {
        A a = new B(); // OK!
    }
}
