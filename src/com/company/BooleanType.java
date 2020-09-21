package com.company;

public class BooleanType {
    static int i;
    public static void main(String[] args) {
        int number = 10;
        boolean isPositive = (number > 0);

        if (isPositive) {
            System.out.println(number + "is positive");
        } else {
            System.out.println("zero or negative");
        }

/*
        if(i){
            // 조건문에는 반드시 boolean 타입만
        }
*/

    }
}
