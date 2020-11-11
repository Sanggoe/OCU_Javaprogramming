package com.company.week11_2;

public class Explain_ {
    /**
     * AWT 패키지
     * 컨테이너 개념!!!
     *
     *  Component : Object 클래스를 직접 상속받는 클래스. 모든 AWT 컴포넌트 클래스의 부모 클래스
     *      Container : 하나 이상의 다른 컨테이너나 다른 구성요소를 포함할 수 있다!
     *          Windows
     *              Frame
     *              Dialog
     *          Panel
     *              Applet
     *      Button
     *      Label
     *      CheckBox
     *      Choice
     *      List
     *      TextComponent
     *          TestField
     *          TextArea
     *
     *
     * Component : Object 클래스를 직접 상속받는 클래스. 모든 AWT 컴포넌트 클래스의 부모 클래스
     * 화면 구성을 위해 꼭 필요한 기능들. 모든 컴포넌트들이 필요한 기능들은 상위 클래스인 Component가 다 가지고 있다.
     *
     * void setSize(int width, int height);
     * void setSize(Dimension d);
     * Dimension getSize();
     * Int getSize(); // pixel 단위 반환
     *
     * Component add(Component comp);
     * Component add(Component comp, int index);
     * void add(Component comp, Object constraints);
     * void add(Component comp, Object constraints, int index);
     * Component add(String name, Component comp);
     *
     * setForeground(), setBackground() 메소드는 컴포넌트의 전경색과 배경색 설정을 위한 메소드
     * int setForeground(Color c);
     * int setBackground(Color c); // Color.black, ...
     *
     *
     */
}
