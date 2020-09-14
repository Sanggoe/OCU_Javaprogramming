package com.company;

public class ShiftOperation {
    public static void main(String[] args) {
        int l1 = 128 << 2;      // == 곱하기 2
        int l2 = -128 << 2;     // == 곱하기 2
        int r1 = 128 >> 2;      // == 나누기 2
        int r2 = -128 >> 2;     // == 나누기 2
        int ur1 = 128 >>> 2;    // == 부호도 같이 이동, 양수이므로 값은 나누기 2와 같음
        int ur2 = -128 >>> 2;   // == 부호도 같이 이동, 음수가 양수로 바뀌면서 엄청 큰 값이 됨

        System.out.println("128 << 2 = " + l1);
        System.out.println("-128 << 2 = " + l2);
        System.out.println("128 >> 2 = " + r1);
        System.out.println("-128 >> 2 = " + r2);
        System.out.println("128 >>> 2 = " + ur1);
        System.out.println("-128 >>> 2 = " + ur2);

    }
}
