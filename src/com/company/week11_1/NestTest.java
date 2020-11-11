package com.company.week11_1;

public class NestTest {
    public static void main(String[] args) {
        // int a; - 1
        {
            int a; // 앞서 미리 선언되어 있다면, 중복된 변수 선언으로 컴파일 에러 - 1
            a = 10; // - 2
            System.out.println("value = " + a);
        }
        //a = 20; // scope 밖에서의 변수 사용 - 에러! - 2
    }
}
