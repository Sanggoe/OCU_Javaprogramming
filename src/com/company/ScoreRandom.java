package com.company;

import java.util.Random;
import java.util.Scanner;

public class ScoreRandom {
    public static void main(String[] args) {
        Random generator = new Random();
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int sum = 0;

        for(int i=0; i<count; i++) {
            int number = generator.nextInt(100);
            sum += number;
        }

        System.out.println("난수의 합 : " + sum);
    }
}
