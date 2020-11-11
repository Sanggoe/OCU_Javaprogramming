package com.company.week10_1;

public class Thread_Class {
    /**
     * static void sleep(long msec) throws InterruptedException
     * Main class는 sleep 할 수 없다!!
     * 하지만 Thread는 sleep 가능!!
     * 대신 반드시 try catch를 넣어서 실행해야 한다.
     * interrupt exception 부분을 예외처리 해주어야 컴파일 오류가 안난다!
     *
     * String getName() 스레드 이름 반환
     *
     * void setName(String s) 스레드 이름 설정
     *
     * void start() 스레드 시작
     * Thread 객체에 존재하는 start() 함수!! 매우 중요하다.
     * 이 객체를 만든 후에는 start() 를 호출하지 않으면 수행되지 않는다.
     * 반드시 호출해야 하는 함수.
     *
     * void setPriority(int p) 우선순위 설정하는 함수
     *
     * boolean isAlive() 스레드가 수행 중인지 여부를 true false로 반환.
     *
     * void join() 스레드가 끝날 때까지 대기
     *
     * void suspend() 스레드 일시 정지, resume()에 의해 다시 시작 가능
     *
     * void resume() 일시 정지된 스레드 다시 시작
     */
}
