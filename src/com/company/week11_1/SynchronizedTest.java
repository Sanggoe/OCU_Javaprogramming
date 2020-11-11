package com.company.week11_1;

public class SynchronizedTest {
    Object o;
    public void method() {
        // 동기화가 필요 없는 다른 작업
        synchronized (o) {
            // o객체와 연관된 동기화가 필요한 작업
        }
    }
}
