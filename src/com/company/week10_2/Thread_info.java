package com.company.week10_2;

public class Thread_info {
    /**
     * 쓰레드 우선순위
     * setPriority() 메소드를 이용해 우선 순위 부여!!
     *
     * static final int MAX_PRIORITY    우선순위 10
     * static final int MIN_PRIORITY    우선순위 1
     * static final int NORM_PRIORITY    우선순위 5
     *
     * getName()과 getPriority()는 Thread의 메소드이다!
     *
     *
     *
     * 쓰레드 동기화
     * 대부분의 응용 프로그램에서 다수개의 스레드가 공유할 수 있는 부분이 요구된다.
     * (mutual exclusion required)
     *
     * 임계영역 (critical section)
     * 상호 배타적으로 사용되는 공유부분
     * 자바는 한 순간에 하나의 스레드만 실행할 수 있는 synchronized method 제공
     * 한 스레드가 synchronized method를 수행 중이면 다른 스레드는 대기
     *
     *
     *
     * void wait() throws InterruptedException
     * 메소드 : 스레드 수행 중 이 메소드를 만나면
     * 가지고 있는 lock을 양보하고, 대기상태로 들어간다.
     * 이 역시 반드시 인터럽티드 익셉션 처리를 해줘야 한다!
     *
     * void notify() : 대기 상태의 스레드 중에서 하나의 스레드를 깨운다.
     * void notifyAll() : 대기 상태의 모든 스레드를 깨운다.
     *
     *
     *
     * */
}