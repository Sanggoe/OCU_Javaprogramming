package com.company.week6;

class Car {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) return model.equals(((Car) obj).model);
        else return false;
    }

    @Override
    public String toString() {
        return "모델:" + model;
    }
}

public class Cartest {
    public static void main(String[] args) {
        Car firstCar = new Car("HMW520");
        Car secondCar = new Car("HMW520");

        if (firstCar.equals(secondCar))
            System.out.println("동일한 종류의 자동차 입니다.");
        else
            System.out.println("동일한 종류의 자동차가 아닙니다.");

        System.out.println(firstCar.toString());
    }
}
