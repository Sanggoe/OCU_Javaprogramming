package com.company.week5;

public class TelevisionTest {
    public static void main(String[] args) {
        Television myTv = new Television();
        myTv.setChannel(7);
        myTv.setVolume(9);
        myTv.setOnOff(true);
        int channel = myTv.getChannel();
        int volume = myTv.getVolume();
        boolean onOff = myTv.isOnOff();
        System.out.println("현재 채널은 " + channel + "이고 볼륨은 " + volume + "입니다.");

        myTv.print();

        Television yourTv = new Television();
        yourTv.channel = 9;
        yourTv.volume = 12;
        yourTv.onOff = true;
        yourTv.print();
    }
}
