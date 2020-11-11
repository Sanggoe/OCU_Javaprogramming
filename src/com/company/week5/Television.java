package com.company.week5;

public class Television {
    int channel;
    int volume;
    boolean onOff;

    void print() {
        System.out.println("채널은 " + channel + "이고 볼륨은 " + volume + "입니다.");
    }

    int getChannel() {
        return channel;
    }

    void setChannel(int channel) {
        this.channel = channel;
    }

    int getVolume() {
        return volume;
    }

    void setVolume(int volume) {
        this.volume = volume;
    }

    boolean isOnOff() {
        return onOff;
    }

    void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }
}
