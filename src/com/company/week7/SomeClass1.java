package com.company.week7;

class SomeClass1 {
    int a, b;
    double c = 1.2;
    double d = c * 2;
}

class SomeClass2 {
    int a, b;
    // double d = c * 2; Error!
    double c = 1.2;
}

class Square {
    final int sideLength;

    Square(int sideLength) {
        this.sideLength = sideLength;
    }
}