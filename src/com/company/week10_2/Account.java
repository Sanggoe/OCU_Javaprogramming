package com.company.week10_2;

class Account {
    private int total = 0;

    synchronized void deposit(int amount) {
        total += amount;
    }
    int getTotal() {
        return total;
    }
}

class Customer extends Thread {
    Account account1;

    Customer(Account account) {
        this.account1 = account;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 200; i++) {
                System.out.println("성금자" + getName() + " : " + i + "번쨰");
                account1.deposit(1000);
                sleep(2);
                if (account1.getTotal() >= 500000) break;
            }

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class TVContribution {
    public static void main(String[] args) {
        Account account = new Account();

        Customer customers[] = new Customer[5];

        for(int i=0; i<5; i++) {
            customers[i] = new Customer(account);
            customers[i].start();
        }
        for(int i=0; i<5; i++) {
            try {
                customers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("성금 총액은 : " + account.getTotal());
    }
}