package com.company.week;

public class CalculateInputTime {
    public static void main(String[] args) {
        int input = 40000;
        int hour, minute, second;

        hour = input / 3600;
        minute = (input % 3600) / 60;
        second = (input % 3600) % 60;

        System.out.println(input + "초는");
        System.out.println(hour + "시간 " + minute + "분 " + second + "초");
        System.out.println("와 같습니다");
    }
}
