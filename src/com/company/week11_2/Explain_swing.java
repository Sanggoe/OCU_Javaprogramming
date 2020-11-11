package com.company.week11_2;

public class Explain_swing {
    /**
     * JFC/Swing 기반 GUI
     *
     * JComponent 클래스 다양한 기능 제공
     *
     * JComponent는 아래와 같은 Swing 컴포넌트들의 base class임
     * Label, JButton, Jlist, JPanel, JTable, ...
     *
     *
     * JFC/Swing의 5가지 레이아웃 방법:
     * BorderLayout
     *  : 동서남북으로 레이아웃 배치 방법. North, south, east, west, center
     *  : 동서남북은 보통 크기가 고정이다. 주로 변경되는건 center.
     *  : Content panel 계열의 기본 default 레이아웃 이다.
     *
     * BoxLayout
     *  : 행 또는 열 방향으로 컴포넌트 배치
     *  : Component들의 고유 크기를 지정해줄 수 있다.
     *
     * FlowLayout
     *  : 좌에서 우로 컴포넌트 배치
     *  : Jpanel 계열의 기본 default 레이아웃 이다.
     *
     * GridLayout
     *  : 요청된 행, 열 크기로 컴포넌트를 배치
     *  : 좌에서 우로(우선)  위에서 아래 방향으로 컴포넌트 배치
     *  : 모든 컴포넌트가 "동일한 사이즈"로 조정된다.
     *
     * GridBagLayout :
     *
     *
     * GUI 이벤트 리스너 타입
     * ActionListener : 버튼 클릭, text field 등 에서 return 자판 눌림 상황, 메뉴 아이템 선택 등..
     * WindowListener : 사용자가 프레임(메인 윈도우)을 닫을 경우
     * MouseListener : 마우스 커서를 컴포넌트 위에 올리고 클릭을 누를 경우
     * MouseMotionListener : 사용자가 마우스를 특정 컴포넌트 위로 올리는 경우
     * ComponentListener : 컴포넌트가 visible 해지는 경우
     * FocusListener : 컴포넌트가 keyboard focus를 얻는 경우
     * ListSelectionListener : 테이블 또는 리스트 선택이 변할 경우우
     * */
}
