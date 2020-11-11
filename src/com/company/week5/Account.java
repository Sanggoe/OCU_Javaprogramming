package com.company.week5;

public class Account {
    int account_number;
    double balance;

    Account (int account, double initial) {
        account_number = account;
        balance = initial;
    } // 생성자
    
    void deposit (double amount) {
        balance = balance + amount;
    } // 메소드
}
