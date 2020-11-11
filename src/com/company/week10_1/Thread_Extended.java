package com.company.week10_1;

public class Thread_Extended extends Thread {

    public static void main(String[] args) {
        Thread_Extended ta = new Thread_Extended();
        ta.start();
    }


    @Override
    public void run() {

    }
}

/**
 * 1. 쓰레드를 상속받아 구현하는 방법.
 *
 * Thread 클래스를 상속 받아 run() 메소드를 오버라이드 하여 실제로 쓰레드가 실행할 코드를 기술한다.
 * run() 함수는 반드시 만들어야 한다!!
 *
 * Thread 클래스 생성자를 호출한다 (생략 가능)
 *
 * 상속받은 start() 메소드를 호출한다.
 */