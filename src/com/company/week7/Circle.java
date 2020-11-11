package com.company.week7;

class Circle {
    private double radius;  // Field

    Circle(double radius) { // Constructor
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    double getArea() {
        double area;
        area = radius * radius * 3.14;
        return area ;
    }
}
