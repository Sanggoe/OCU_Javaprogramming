package com.company.week10_1;

public class Thread_Implements implements Runnable {

    public static void main(String[] args) {
        Runnable rb = new Thread_Implements();
        
        Thread tb = new Thread(rb);
        tb.start();
        // new Thread(rb).start(); 로 축약 가능
    }

    @Override
    public void run() {

    }
}

/**
 * 2. Runnable 인터페이스를 통해 쓰레드 구현하는 방법
 *
 * Runnable 인터페이스를 implements 받아 run() 메소드를 반드시 구현
 *
 * 쓰레드 클래스의 객체가 생성 되며, Runnable 인터페이스를 구현하는 클래스 객체를 Thread class의 인자로 전달
 * 
 * 쓰레드 실행을 위해 start() 메소드 호출
 *
 * */