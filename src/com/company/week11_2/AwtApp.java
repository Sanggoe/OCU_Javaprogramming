package com.company.week11_2;

import java.awt.*;

public class AwtApp extends Frame {
    Button b;

    public static void main(String[] args) {
        AwtApp a = new AwtApp("AWT 예제"); a.display();
    }

    AwtApp(String title) {
        super(title);
        b = new Button("예");
    }

    void display() {
        add(b);
        pack();
        setVisible(true);
    }
}
