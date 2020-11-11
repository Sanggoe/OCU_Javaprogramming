package com.company.week7;

public class SubscriberInfo {
    String name, id, password;
    String phoneNo, address;

    public SubscriberInfo() {
    }

    public SubscriberInfo(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public SubscriberInfo(String name, String id, int address) {
    }

    void changePassword(String password) {
        this.password = password;
    }

    void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    void setAddress(String address) {
        this.address = address;
    }
}
