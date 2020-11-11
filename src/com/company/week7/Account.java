package com.company.week7;

public class Account {
    String accountNo;
    String ownerName;
    int balance;

    Account(String accountNo, String ownerName, int balance) {
        this.accountNo = accountNo;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    void deposit(int amount) {  // 예금
        balance += amount;
    }
    int withdraw(int amount) {  // 인출
        if (balance < amount)
            return 0;
        balance -= amount;
        return amount;
    }
}
