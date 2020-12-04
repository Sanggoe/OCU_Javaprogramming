## 10. 쓰레드

* static void **sleep**(long msec) throws InterruptedException
  * Main class는 sleep 할 수 없다!!
  * 하지만 Thread는 sleep 가능!!
  * 대신 반드시 **try catch**를 넣어서 실행해야 한다.
  * interrupt exception 부분을 예외처리 해주어야 컴파일 오류가 안난다!

  ```java
  @Override
      public void run() {
          try {
              for (int i = 0; i < 10; i++) {
                  Thread.sleep(2);
                  System.out.println("Runnable 인터페이스로부터 생성" + i);
              }
              System.out.println("끝");
          } catch (InterruptedException e) {
              System.out.println(e);
          }
      }
  ```



* String getName() : 스레드 이름 반환

  ```java
  @Override
      public void run() {
          try {
              for (int i = 0; i < 10; i++) {
                  Thread.sleep(2);
                  System.out.println(i + getName());
              }
              System.out.println("끝" + getName());
          } catch (InterruptedException e) {
              System.out.println(e);
          }
      }
  ```

  

* void setName(String s) : 스레드 이름 설정

* void **start()** : 스레드 시작
  * Thread 객체에 존재하는 start() 함수!! **매우 중요하다.** ★
  * 이 객체를 만든 후에는 **start() 를 호출하지 않으면 수행되지 않는다.**
  * **반드시 호출해야 하는 함수.**

  ```java
  public static void main(String[] args) {
      Thread_Extended ta = new Thread_Extended();
      ta.start();
  }
  ```

* void setPriority(int p) : 우선순위 설정하는 함수

* boolean isAlive() : 스레드가 수행 중인지 여부를 true false로 반환.

* void join() : 스레드가 끝날 때까지 대기

* void suspend() : 스레드 일시 정지, resume()에 의해 다시 시작 가능

* void resume() : 일시 정지된 스레드 다시 시작

<br/>

#### 쓰레드를 extends 받아 구현

* Thread 클래스를 상속 받아 run() 메소드를 오버라이딩 하여 실제로 쓰레드가 실행할 코드를 기술한다.
* run() 함수는 반드시 만들어야 한다!! ★
* Thread 클래스 생성자를 호출한다 (생략 가능)
* 상속받은 start() 메소드를 호출한다.

```java
public class Thread_Extended extends Thread {
    public static void main(String[] args) {
        Thread_Extended ta = new Thread_Extended();
        ta.start();
    }

    @Override
    public void run() {
        ...
    }
}
```

<br/>

#### 쓰레드를 implements 받아 구현

* Runnable 인터페이스를 implements 받아 **run() 메소드를 반드시 구현** ★
* 쓰레드 클래스의 객체가 생성 되며, Runnable 인터페이스를 구현하는 클래스 객체를 Thread class의 인자로 전달
* 쓰레드 실행을 위해 start() 메소드 호출

```java
public class Thread_Implements implements Runnable {
    public static void main(String[] args) {
        Runnable rb = new Thread_Implements();
        Thread tb = new Thread(rb);
        tb.start();
        // new Thread(rb).start(); 로 축약 가능
    }

    @Override
    public void run() {
		...
    }
}
```

<br/>

#### 쓰레드 우선순위

* 쓰레드 우선순위
* setPriority() 메소드를 이용해 우선 순위 부여!!
* static final int MAX_PRIORITY    우선순위 10
* static final int MIN_PRIORITY    우선순위 1
* static final int NORM_PRIORITY    우선순위 5
* getName()과 getPriority()는 Thread의 메소드이다!

```java
public class ThreadPriority {
    public static void main(String[] args) {
        PriorityTest t1 = new PriorityTest(" : 첫 번째 스레드");
        PriorityTest t2 = new PriorityTest(" : 두 번째 스레드");
        PriorityTest t3 = new PriorityTest(" : 세 번째 스레드");

        int priority_t1 = Integer.parseInt(args[0]);
        int priority_t2 = Integer.parseInt(args[1]);

        t1.setPriority(priority_t1);
        t2.setPriority(priority_t2);
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
```

<br/>

#### 쓰레드 동기화

* 대부분의 응용 프로그램에서 다수개의 스레드가 공유할 수 있는 부분이 요구된다.
* (mutual exclusion required)

* 임계영역 (critical section)
  * 상호 배타적으로 사용되는 공유부분
  * 자바는 한 순간에 하나의 스레드만 실행할 수 있는 synchronized method 제공
  * 한 스레드가 synchronized method를 수행 중이면 다른 스레드는 대기

  ```java
  class Account {
      private int total = 0;
  
      synchronized void deposit(int amount) {
          total += amount;
      }
      int getTotal() {
          return total;
      }
  }
  ```

* void wait() throws **InterruptedException**
  * 스레드 수행 중 이 메소드를 만나면 가지고 있는 lock을 양보하고, 대기상태로 들어간다.
  * 이 역시 반드시 인터럽티드 익셉션 처리를 해줘야 한다!

* void notify() : 대기 상태의 스레드 중에서 하나의 스레드를 깨운다.
* void notifyAll() : 대기 상태의 모든 스레드를 깨운다.

```java
class Buffer {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait(); // ★
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("소비자########## : 소비 " + contents);
        notify(); // ★
        available = false;

        return contents;
    }

    public synchronized void put(int value) {
        while(available == true) {
            try {
                wait(); // ★
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("생산자########## : 생산 " + contents);
        notify(); // ★
        available = true;
    }
}
```

<br/>

<br/>

## 11.1 블록 / 스코프

* static 블록은 클래스에 속하지만, 어떤 메소드에도 속하지 않는 블록!
* 클래스가 로딩될 때 한 번만 실행 된다.

<br/>

#### 블록/scope과 접근 제한

* public, private, protected

  * protected : 상속관계에서 접근 허용
  * public : 외부에서 접근 허용
  * private : 해당 클래스에서만 사용 가능. 외부 접근 불가

  ```java
  public static void main(String[] args) {
          // int a; - 1
          {
              int a; // 앞서 미리 선언되어 있다면, 중복된 변수 선언으로 컴파일 에러 - 1
              a = 10; // - 2
              System.out.println("value = " + a);
          }
          //a = 20; // scope 밖에서의 변수 사용 - 에러! - 2
      }
  ```

  

* Synchronized 블록

  * 해당 블록에 대해 lock을 걸어, 동기화 작업을 구체적으로 수행 가능.
  * 메소드 선언에 대해서도 붙일 수 있지만, 내부 블록에서도 적용 가능하다.
  * 필요한 부분만 synchronized 하니까 성능 향상이 기대 된다!

  ```java
  public void run() {
      while (true) {
          synchronized ((Object) a) {
              System.out.println(s + a);
              a++;
          }
      }
  }
  
  ////////////////////////////////////////////////
  
  public class SynchronizedTest {
      Object o;
      public void method() {
          // 동기화가 필요 없는 다른 작업
          synchronized (o) {
              // o객체와 연관된 동기화가 필요한 작업
          }
      }
  }
  ```

<br/>

## 11.2 GUI

#### AWT 패키지

* 컨테이너 개념!!!
*  Component : Object 클래스를 직접 상속받는 클래스. 모든 AWT 컴포넌트 클래스의 부모 클래스
* Container : 하나 이상의 다른 컨테이너나 다른 구성요소를 포함할 수 있다!
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
  * TextArea

<br/>

#### Component

* Object 클래스를 직접 상속받는 클래스. 모든 AWT 컴포넌트 클래스의 부모 클래스
* 화면 구성을 위해 꼭 필요한 기능들. 모든 컴포넌트들이 필요한 기능들은 상위 클래스인 Component가 다 가지고 있다.
* void setSize(int width, int height);
* void setSize(Dimension d);
* Dimension getSize();
* Int getSize(); // pixel 단위 반환
* Component add(Component comp);
* Component add(Component comp, int index);
* void add(Component comp, Object constraints);
* void add(Component comp, Object constraints, int index);
* Component add(String name, Component comp);
* setForeground(), setBackground() 메소드는 컴포넌트의 전경색과 배경색 설정을 위한 메소드
* int setForeground(Color c);
* int setBackground(Color c); // Color.black, ...

```java
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
```

<br/>

#### JFC/Swing 기반 GUI

* JComponent 클래스 다양한 기능 제공

* JComponent는 아래와 같은 Swing 컴포넌트들의 base class임

  * Label, JButton, Jlist, JPanel, JTable, ...

  ```java
  public class ButtonListerExample {
      public static int numClicks = 0;
  
      public static void main(String[] args) {
          JFrame frame = new JFrame();
          JPanel panel = new JPanel();
          JButton button = new JButton("I'm a Swing button");
          JLabel label = new JLabel("Number of button clicks: 0");
  
          button.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  ButtonListerExample.numClicks++;
                  label.setText("Number of button clicks:" + numClicks);
              }
          });
  
          panel.setLayout(new GridLayout(2,1));
          panel.add(button);
          panel.add(label);
  
          frame.add(panel);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
      }
  }
  ```

  ```java
  public class HellowWorldSwing {
      public static void main(String[] args) {
          JFrame frame = new JFrame("HelloWorldSwing");
          final JLabel label = new JLabel("Hello World");
          frame.getContentPane().add(label);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
  
          Object[] options = {"Yes!", "No, I'll pass", "Well, if I must"};
          int n = JOptionPane.showOptionDialog(frame, "Duke is a cartoon mascot. \n" +
                  "Do you still want to cast your vote?",
                  "A follow-up Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
  
  
      }
  }
  ```

<br/>

#### JFC/Swing의 5가지 레이아웃 방법:

* BorderLayout
  *  동서남북으로 레이아웃 배치 방법. North, south, east, west, center
  *  동서남북은 보통 크기가 고정이다. 주로 변경되는건 center.
  * Content panel 계열의 기본 default 레이아웃 이다.
* BoxLayout
  *  행 또는 열 방향으로 컴포넌트 배치
  * Component들의 고유 크기를 지정해줄 수 있다.
* FlowLayout
  *  좌에서 우로 컴포넌트 배치
  * Jpanel 계열의 기본 default 레이아웃 이다.
* GridLayout
  *  요청된 행, 열 크기로 컴포넌트를 배치
  *  좌에서 우로(우선)  위에서 아래 방향으로 컴포넌트 배치
  * 모든 컴포넌트가 "동일한 사이즈"로 조정된다.
* GridBagLayout :

<br/>

#### GUI 이벤트 리스너 타입

* ActionListener : 버튼 클릭, text field 등 에서 return 자판 눌림 상황, 메뉴 아이템 선택 등..
* WindowListener : 사용자가 프레임(메인 윈도우)을 닫을 경우
* MouseListener : 마우스 커서를 컴포넌트 위에 올리고 클릭을 누를 경우
* MouseMotionListener : 사용자가 마우스를 특정 컴포넌트 위로 올리는 경우
* ComponentListener : 컴포넌트가 visible 해지는 경우
* FocusListener : 컴포넌트가 keyboard focus를 얻는 경우
* ListSelectionListener : 테이블 또는 리스트 선택이 변할 경우

<br/>

<br/>

## 12. DB 연동

* 데이터베이스
  * Oracle, MySQL, Maria, ...

* MySQL ...
create table
show table
insert into

<br/>

#### 순서

* Class.forName("Driver_Name")
  * // 1. jdbc 드라이버를 찾는다.

* Connection conn = DriverManager.getConnection("DBURL", "Account ID","Account PW");
  * // 2. 드라이버 인스턴스를 생성하며 커넥션 객체을 생성한다.          

* Statement stmt = conn.createStatement();
  * // 3. 커넥션 객체로부터 스테이트먼트 객체를 생성
* ResultSet rs = stmt.executeQuery("select * from ... ");
  * // 4. statment 객체의 익스큐트 쿼리 메소드를 이용해 sql 쿼리문을 보낸다.

* rs.close();
* stmt.close();
* conn.close()
  * // 사용 완료 후 종료!

<br/>

#### JDBC 구성

* 자바 프로그램 (JSP, Server등) <-> JDBC (JDBC 인터페이스<-> JDBC 드라이버) <-> DBMS

<br/>

#### JSP와 데이터베이스 연동


* import = "java.sql.*"
* Class.forName() : JDBC 드라이버의 인스턴스 생성.
  인자로는

  * MySQL 사용 위한 드라이버 로딩 : "org.gjt.mm.mysql.Driver"
  * 오라클 사용 위한 드라이버 로딩 : "oracle.jdbc.driver.OracleDriver"
  * ODBC 브리지 : "sun.jdbc.odbc.JdbcOdbcDriver"

* getConnection() : JDBC 드라이버 인스턴스를 통해 DBMS에 대한 연결 생성
  인자 예시로는
  * "jdbc:mysql://localhost:3306/myDB", "root", ""
  * 즉, "JDBC URL/databaseName 형태", "DB로그인계정", "비밀번호" 이다.
* Oracle의 경우
  * getConnection("jdbc:oracle:thin:@localhost:1521:system_id", "scott", "tiger")
* ODBC의 경우
  * getConnection("jdbc:odbc:DSNname", "sa", "")

<br/>

```java
public class DriverTest {
    public static void main(String[] args) {
        Connection con;

        try {
            // Class forName my sql에 대한 jdbc 드라이버 찾아냄
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();

            // 커넥션 객체를 통해서 getConnection으로 url에 접근해서 mydb 데이터베이스 이름
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root", "");
            // root고 패스워드 없는 경우
            System.out.println("Success");
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex);
        }
    }
}
```

<br/>

<br/>

## 12-2. JSP

#### 서블릿의 등장

```
기존에 클라이언트는 서버가 html 파일을 직접 받는 방법.
이후에 애플릿 코드가 클라이언트에게 전달되어 클라이언트에서 실행되는 방법 사용.
그러나 당시에는 컴들이 안좋았기에, 너무 느렸음. 애플릿기술은 지금 거의 사장되었음.

애플릿을 대체하여 지금은 서블릿이라는 기술이 대체되었다.
자바 플랫폼에서 컴포넌트 기반의 웹 앱개발 기술!
JSP는 서블릿 기술에 기반한다.
자바 언어를 이용해 다이나믹한 웹 사이트를 개발하기 위해 등장한 기술이다.
웹서버 기능을 확장시키는 방법 제공

HTML의 경우에는 정적인 특성. 한 번 작성되면 쭉 동일한것...
하지만 동적인 특성을 가지려면 CGI, applet, servlet 등의 기술을 써야한다.
```

<br/>

#### 서블릿의 문제점!

```
프로그램에서 HTML을 다뤘었다.
컨텐츠와 비즈니스 로직 분리가 안된다.. 순수한 자바 코드가 서버측에 올라가야하기에..
사용자가 원하는 일부 부분만 수정하더라도 자바 코드 전체를 다시 컴파일 해줘야하는 일이..

그래서 JSP가 등장!
HTML에서 프로그램을 다룬다.
JSP는 우리가 컴파일 할 필요는 없다. 스크립팅 기술.

그러나 서블릿의 한계!
구현 방법. 디자인 패턴에서의... 컨텐츠와 비즈니스 로직이 분리되지 않아서.
컨텐츠 관리는 쉽지만, 프로그램 관리는 복잡해졌다.

그러면서 MVC 패턴 주목받기 시작!
모델, 뷰, 컨트롤러. JSP+MVC가 보편적으로 사용되고 있단다.


서블릿 컨테이너 (WAS - web application server)
자바 가상머신을 내장한 서블릿 운영환경
JSP는 서블릿으로 변환되어 실행
대부분 별도의 실행환경 없이 서블릿 컨테이너에 통합된다.
자체 웹서버 기능도 있으나, 웹서버와 분리하기도 한다.
(Webserver와  WAS Server...)
대표적으로 아파치(Webserver) 톰캣(WAS server)이 있다.
```

<br/>

#### 서블릿 동작 원리

```
클라이언트의 요청
서버상에 자바 클래스 파일이 존재한다. 그런 형태의 서블릿을 서블릿 컨테이너에 등록해놓는다.
스레드 서블릿은 cgi와 같은 기법등에서 프로세스를 매번 생성하는 것보다 효율적이다.
프로세스 생성보다는 스레드를 생성하는 것이 더 나으니까.

대부분의 사용자 요청을 처리하기 위해선 데이터베이스와 연결되어 동작한다.
그리고 최종적으로 클라이언트에게 응답을 해주는데, HTML 형식 등으로 반환할 수 있다.
그 안에 정적인 부분과 동적인 부분이 함께 들어가는 것!
```

<br/>

#### 서블릿의 장점

```
쓰레드를 기반으로 하므로 웹 애플리케이션 운영에 효율적이다!
자바를 기반으로 하므로 자바 API를 대부분 사용할 수 있다.
운영체제나 하드웨어에 영향을 받지 않는다.
한 번 개발된 애플리케이션은 다양한 서버 환경에서도 실행이 가능하다.
즉, WAS 서버만 돌아가면 그 웹 사이트는 돌아간다! 라는 것이야
웹 어플리케이션에서 효율적인 자료 공유를 제공한다.
```

<br/>

<br/>

#### JSP 파일 위치

```
JSP 파일의 모든 내용은 jspService() 메소드에 위치한다. ★

public void jspService(HttpServletRequest request, HttpServletResponse response_
    throws java.io.IOException, ServletException {
    ...
    ...
    out.write("\r\n\r\n");
    out.write("<HTML>\r\n");
    out.write("<HEAD>");
    out.write("<TITLE>Hello World");
    out.write("</TITLE>");
    out.write("</HEAD>\r\n\r\n");
    out.write("<BODY>");
    out.write("<H2>Hello Wolrd : 헬로월드");
    out.write("</H2>\r\n오늘의 날짜와 시간은 : ");
    out.print(new java.util.Date());
    ...
}
```

<br/>

#### JSP는

```
servlet 스펙에 기반하여 동적 web page를 생성하기 위한 스펙
Server측 processing. 서버측 언어!
표현과 동적 컨텐츠를 분리한다.
<%@page import = "java.util.Date"%>
<html>
<body>
<h1>example</h1>
current time is <% = new Date()%>
</body>
</html>
```

<br/>

#### JSP의 특징

* 보여주는 부분과 프로그램 로직 부분을 분리하여 제작할 수 있다.
  * 보여주는 부분 : HTML, XML
  * 프로그램 로직 : JSP 태그
* 컴포넌트의 재사용
  * 기존에 EJB로 만들어진 컴포넌트가 있다면 JSP에서 그대로 사용가능
* 자바의 특징 계승
  * JSP는 자바 기반 스크립트. 따라서 JSP 페이지는 자바 기술의 모든 장점을 수용한다.
  * 객체지향 프로그래밍, 플랫폼 독림성, JDBC의 사용 등
* 페이지 작성이 쉽다.
  * JSP파일은 서버에 올라갈 때가 아니라 사용자로부터 호출이 될 때 컴파일이 된다.
  * 따라서 매번 다시 컴파일 해야하는 번거로움이 없다. 와스가 알아서 한다.

<br/>

#### JSP 실행과정

* 사용자가 특정 JSP 페이지 요청
  * JSP 엔진은 요청 페이지가 새로 작성되거나 변경되었는지, 아니면 변화되지 않았는지 판단
    새로 요청(변경) 된 경우, JSP 파일을 이용해 서블릿 코드를 만든 후 이걸 컴파일 해서 서블릿을 실행
    재요청은 그전에 만들었던 서블릿을 그대로 실행!

<br/>

#### JSP의 장점

* 여러가지 일을 쉽게할 수 있게 도와준다.
* HTML 작성, HTML을 읽어들이고 유지관리
* 표준 HTML 툴을 사용해서 코딩 가능
* Java code(logic)와 HTML code(표현)을 분리 시킬 수 있다.

<br/>

#### 다른 기술들... PHP, ASP에 대한 JSP의 특징

* 유사점은 모두 서버측 언어이다 라는 것. JSP는 ASP에 대한 Java 진영의 구현이다.
* 차이점은, ASP는 MS에서 개발한 C# 등.. 플밍 언어임. 마소 플랫폼에서 동작한다.
* ASP는 캐시 되기도 하지만, 대부분 항상 인터프리팅 되어 실행된다.
* JSP는 자바 기술이고, 플랫폼 독립적이며, 최초가 아닌 이후에는 컴파일된 코드로 사용자에게 제공하므로 속도가 빠르다.

<br/>

#### JSP와 자바스크립트(in browser)

* JSP는 서버측 언어, 자바스크립트는 주로 사용자의 웹 브라우저에서 실행되는 클라이언트 언어이다.
* 자바스크립트는 인터프리팅 방식, 포터블 하지 않다. 동적인 기능을 제공하기 위해 나온 코드.
* Servlet : HTML in Java Code - 결국 자바코드.
* JSP : Java Code Scriptlets in HTML - 자바가 스크립트 형태로 포함

```
<% %> 형태로 하는 것이 JSP 문법이다!
따라서 작성한 자바 코드 형태가 서블릿 형태로 바뀌는 것.
JSP는 코드를 컴파일 할 필요가 없다. 자동 컴파일.
JSP page를 위해 별도의 특별한 디렉토리를 만들 필요 없다.
페이지 호출을 위해 특별한 URL을 사용할 필요 없다.
JSP 페이지에 사용되는 일반적 자바 클래스에 대해서는 클래스 패스, 인스톨 디렉토리 등에 관한 규칙이 여전히 적용되어야 한다.

<% %> 형태가 JSP 문법.
<%= : 이퀄 사인이 나오면, 코드 실행 결과값을 웹 브라우저 상에 표시하고자 하는 경우!!
버튼이 눌렸을 때 JSP 파일을 호출할 수 있다.

전적으로 JSP는 server에서 실행된다. 따라서 클라이언트가 다루는 내용을 변경하지는 못한다.
페이지 변환 시간에 JSP construct는 서블릿 코드로 변환된다.
Request time에 서블릿 코드가 실행된다. 이 요청 시간에 JSP 해석은 발생하지 않는다.
Original JSP 페이지는 request time에는 아무 일도 하지 않는다. 오직 서블릿 코드가 사용.
첫 번째 요청이 들어온 경우에만 JSP 파일을 서블릿 코드로 변환하는 작업이 수행된다.
```

<br/>

#### JSP 예제

```jsp
<%@ page contentType="text/html; charset=EUC-KR"
         import="java.sql.*" %>
<%
    Class.forName("org.gjt.mm.mysql.Driver");
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String id = "", name = "", e_mail = "";
    int counter = 0;
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB",
            "root", "");
    stmt = conn.createStatement();

    // rs라는 객체에 데이터가 들어 있을 것.
    rs = stmt.executeQuery("SELECT * FROM MEMBER");
%>
<html>
<body>
<table>
    <tr>
        <td><string>ID</string></td>
        <td><string>NAME</string></td>
        <td><string>E_MAIL</string></td>
    </tr>
    <% if(rs!=null) {
        while(rs.next()) {
            id = rs.getString("id");
            name = rs.getString("name");
            e_mail = rs.getString("e_mail");
            %>
    <tr>
        <td><%= id %></td> <!-- = 하면 변수의 값을 웹 페이지에 표시! -->
        <td><%= name %></td>
        <td><%= e_mail %></td>


    <%
            counter++;
        }
    }
    %>
    </tr>
</table>
<br/><br/>
total records : <%= counter%>
</body>
</html>
```

<br/>

<br/>

<br/>

## JSP 기본 문법구조

* JSP 모델1, 모델2
* 모델2는 MVC 디자인 패턴에 기반
* Spring... 최소 자바 빈즈에는 익숙해져야함



* JSP 문법 : <%X ... %> // X는 다음 것들 중 하나
  * **@ Derective** : Global Information for page
    * Language, import statement, 등
    * 현재 jsp 페이지 전체에 대해 적용되는 지시자!
  * **Scripting** 요소 : Java code
    * **!** 선언 : class level 변수와 메소드 -> jsp 페이지 전체에 적용되는, 글로벌 변수로 선언된다!!
    *  (공백)Scriptlets : Java code의 block이 외부호출을 가능하게 함
    * **=** Expression : 변수의 값이 페이지에 출력되도록 함 
  * **Actions** : To modify runtime behavior



### JSP 요소

```jsp
<!--OUTPUT COMMENT -->	: HTML 소스에서 보이는 주석
<%-- page comment --%>	: 사용자의 브라우저에 보이지 않는 주석
<%! Declaration %> 	: 페이지에서 유효한 변수 선언
<%= expression %>	: 페이지에 값을 출력할 경우
<% scriptlet %>		: 자바코드 - 가장 많이 사용 됨	

// 보통 웹 서버가 할 일 지시 - 아파치 톰캣 서버 상에
<%@ include %>	: text, code의 파일을 include 한다.
<%@ page %>		: 현재 페이지의 전체 속성을 정의한다.
<%@ taglib %>	: taglib를 정의한다.
```





### JSP 요소(주속)

* HTML 형식의 주석

  * JSP는 HTML과 함께 구성되므로 HTML 형식의 주석 사용가능

  * 단, JSP 문법이 사용된 부분에는 사용불가

  * ```jsp
    <!-- 주석 -->
    ```

* JSP 주석

  * HTML과 JSP 문법 모든 부분에 사용가능

  * 소스보기를 하면 보이지 않음

  * ```jsp
    <%-- 주석 -->
    ```

* 자바주석

  * JSP 스크립트릿 영역 내에서 사용함

  * ```java
    /**/, //, /** */
    ```



### JSP Directives <%@

* JSP Directive란?
  * JSP 컨테이너에게 보내는 메시지

* <%@ page attribute=value ... %> // contentType을 보통 사용한다. "text/html"
  * 페이지 속성을 설정
  * 일반적으로는 default 속성을 사용
* <%@ include \<filename> %>
  * JSP가 컴파일 되기 전에 JSP로 파일이 inline 형태로 삽입됨
  * 사실상 하나의 코드를 두 개의 소스코드로 따로 저장하는거라고 이해..하자



* Directives > include directive
  * 현재 JSP 파일에 다른 JSP나 HTML 문서를 포함시킴.
  * 기본 형식
    * <%@ include file="포함할 파일명"%>
    * 보통 홈페이지의 header나 footer 등은 고정되어 있어서.. 따로 만들어 포함시킨다.
* 사용 예

```jsp
<%@ page contentType="text/html; charset=euc-kr" %>

<HTML>
    <HEAD>
        <TITLE></TITLE>
    </HEAD>
    <BODY>
        <H2> include 지시어 테스트 1 </H2>
        <HR>
        <%@ include file="footer.jsp" %>
    </BODY>
</HTML>
```



* 그 외에도 사용할 수 있는 디렉토리들이 많이 있다! Directives
  * language
  * Import
  * session
  * Buffer
  * autoFlush
  * isThreadSafe
  * Info
  * errorPage
  * contentType



* <%을 열고%>를 닫기 전에 앞 뒤에 공백을 한 칸씩 주는 것은 선택이지만 가독성을 위해 권장!
* XML 기번 형식으로도 사용 가능
  * <jsp:directive.page attribute="value1" attribute2="value2" attribute3=../>
* <%@ page info="This is a valid set of page directives." %>
* <%@ page language="java" import="java.net" %>
* <%@ page import= "java.util.List, java.util.ArrayList" %>

* <%@ page info="This is an invalide page directive" session="false" buffer="16k" autoFlush="false" session="false" %>
* <%@ page info="This is an invalide page directive"%>
* <%@ page extends="com.taglib.wdjsp.MyJspPage" info="Use my superclass."%>



#### 위 항목들 설명

* Info attribute
  * Page 작성자가 기능에 대한 **웹 사이트 개발자에게 정보를 전달하는 용도**로, JSP 컨테이너에게만 avaliable함
  * <%@ page info="This is a valid set of page directives." %>
* Language attribute
  * <%@ page language="java" import="java.net" %>
  * 디폴트 속성이 자바기때문에, 랭귀지 속성을 넣을 필요는 없다.
* ContentType attribute
  * JSP 페이지에 의해 생성되는 응답의 MIME type을 지정할 수 있도록 한다.
  * <%@ page contentType="text/xml" %>
  * <%@ page contentType="text/html"; charset=ISO-8859-1" %>
* Extends attribute
  * JSP 페이지가 Javaservlet으로 translation 될 때, JSP 컨테이너에 의해서 사용되는 superclass를 식별하도록 하는 attribute
  * <%@ page extends="com.taglib.wdjsp.MyJspPage" "%>
* Import attribute
  * 매우 자주 사용된다.
  * 참조하는 자바 클레스 셋을 확장할 수 있기 때문에, 여러줄이 될 수 있다.
  * <%@ page import= "java.util.List, java.util.ArrayList" %>
  * <%@ page import= "java.util.*, java.awt.\*" %>
* Session attribute
  * 웹서버하고 클라이언트가 일시적으로 정보를 끝낼것이냐, 아니면 세션을 유지해서 지속적으로 해당 사용자라는 정보를 유지할 수 있도록 해줄 것이냐.
  * <%@ page session="false" %>
  * 통상적으로는 false로 하지만, 유지해야할 경우에는 true!
* Buffer attribute
  * JSP 페이지에 대한 버퍼 사이즈를 설정할 수 있는 속성
  * <%@ page buffer="none" %>
  * <%@ page buffer="12kb" %>
  * 버퍼의 크기를 지정해서 데이터를 한번에 모아서 전달할 수 있게 해준다.
* AutoFlush attribute
  * 페이지의 버퍼가 가득 찼을 때, 내용을 자동으로 컨테이너로 플러시 시켜줄 수 있도록 할것인지 설정
  * <%@ page autoFlush="true" %>
* ErrorPage attribute
  * 에러 페이지에 해당하는 경로를 지정해 준다.
  * <%@ page errorPage="/webdev/misc/error.jsp" %>



* 현재 JSP 페이지를 컨테이너에서 처리하기 위한 속성 지정 예시

```jsp
<%@ page contentType="text/html; charset=euc-kr"
import="javax.sql.*, java.util.*"
errorPage="error.jsp"%>
    
<%@ page import="java.util.*" %>
```



### 커스텀 태그 라이브러리

* 별도의 라이브러리를 통해서 태그를 사용할 수 있도록 하는 기법

* 사용자 정의 태그 라이브러리!!

* 표준화된 커스텀 태그 : JSTL (JSP Standard Tag Library)를 대부분 쓴다고 한다.

* 기본 형식

  * <%@ taglib uri="/META-INF/mytag.tld" prefix="mytag" %>

  * ```jsp
    <%@ page contentType="text/html; charset=euc-kr"%>
    <%@ taglib uri="/META-INF/mytag.tld" prefix="mytag" %>
    
    <HTML>
        <BODY>
            <mytag:GetInfo name="dinfree" />
        </BODY>
    </HTML>
    ```

    

### JSP Script - 선언 <!

* 선언(Declarations)

* 변수 선언

  * <%! private int x=0, y=0; private String units="ft"; %>
  * 페이지 전체에서 접근할 수 있는 글로벌 변수 형태!

* 메소드 선언

  * ```jsp
    <%! public long fact(long x) {
    if(x==0) return 1;
    else return x * fact(x-1);
    } %>
    ```

  * 선언 형태로 메소드도 정의할 수 있다.



* 예시

```jsp
<%@ page import="java.util.Date" %>
<%! int sum=0;
private int AddToCount(int X) {
sum = sum + X;
return sum;
}
%>

<html>
    <body>
        <h1>Declaration Example</h1>
        sum value = <%= sum %> <br>
        <% AddToCount(3); %>
        sum value = <%= sum %> <br>
    </body>
</html>
```



### JSP Script - Scriptlets 

* 일반적인 자바코드 블록.

* JSP 선언 <%! 으로 만든 변수들을 access할 수 있다.

* Scriptlet은 servlet 객체들을 access 할 수 있다. **꼭 알고있어야 할 객체들 세 개★**

  * Request : our usual request // 들어오는 인자값들
  * Response : our usual res // 나가는 출력값들
  * Out : for printing // out.print

* 예

  * <% String nameVal = **request.getParameter("LASTNAME"); ★★★★★★★★★**

    outprintln(nameVal); %>



### 예제

```jsp
= Test1.jsp =
<html>
    <BODY>
        <H2>Scriptlet Test</H2>
        <form method=post action="test2.jsp">
            <input type=text name=name size=15>
            <input type=submit value="Send">            
        </form>
    </BODY>
</html>

= test2.jsp =
<html>
    <body>
        <h1>scriptlet Example</h1>
        <% String nameVal = request.getParameter("name"); out.println(nameVal); %>
    </body>
</html>
```



* Expression
  * 간략화된 scriptlet print 명령
  * \<p> <%= nameval %> \</p>



### JSP 액션태그

* 액션태그 (action tag)
  * 어떤 동작 또는 액션이 일어나는 시점에 페이지와 페이지 사이 제어를 이동시킬 수 있다!
  * Include
  * Forward
  * Plug-in
  * useBean
  * 등등



* include 액션태그
  * include directive는 소스코드 자체를 포함시키는 것!!
  * 근데, include 액션태그는 다른 페이지의 처리 결과를 포함시킨다.
  * <jsp:include page="localURL flush="true"/>

```jsp
<%-- includedFile.jsp --%>
<%! int i=0; %>

<%-- includedFile 01 --%>
<%@ include file="includedFile.jsp" --%> // 요건 include 디렉티브
<%= i %>

<%-- includedFile 02 --%>
<sjp:include page="includedFile.jsp" flush="true" /> // 요건 include 액션태그
<%= i %>
```



* forward  액션태그
  * 다른 페이지로 이동할 때 사용. 자주 쓰인대!!
  * 결제 시스템 등의 사례에서는 특정 이벤트 처리가 완료되면 자동으로 이동할 때.
* 사용 예

```jsp
<jsp:forward page="local URL"/>
<jsp:forward page=,,<%= expression %>"/>
```



```jsp
== forwardact.html ==
<FORM METHOD=POST ACTION="forwardaction1.jsp">
ID : <INPUT TYPE="text" NAME="name"/>
    <INPUT TYPE="submit" VALUE="Send"/>
</FORM>

== forwardaction1.jsp ==
<%@ page contentType="text/html; charset=EUC-KR"%>
<%
request.setCharacterEncoding("euc-kr");
%>
<html>
    <body>
        forwarding 하기 이전 페이지입니다.
        <jsp:forward page="forwardaction2.jsp"/>
    </body>
</html>

== forwardaction2.jsp ==
<%@ page contentType="text/html; charset=EUC-KR" %>
<%
String name = request.getParameter("name");
%>
당신의 이름은 <b><%= name %></b>입니다.
Forwarding 페이지에서 별도의 매개변수 값을 추가할 수 있습니다.

<jsp:forwar page="URL">
    <jsp:param name="name1" value="value1"/>
    <jsp:param name="name2" value="value2"/>               
</jsp:forwar>
```



* plugin 액션태그
  * 과거 애플릿을 사용할 때라 넘어갑니다.



### 커스텀 태그 라이브러리!

* 표준태그(standard tag)
  * JSP 표준을 만든 SUN사에서 미리 정의해 놓은 JSTL (JSP Standard Tag Library)
* 커스텀 태그
* 커스텀 태그 라이브러리
* 커스텀 태그의 특징
  * 반복적인 일을 단순하게!
  * 표현 영역(HTML)과 연산영역(JSP)을 분리!
  * 웹 페이지의 일관성!
  * 재사용성!



* 반복적인 일을 단순하게...

  * 3x3 테이블 작성하기

  * ```jsp
    <table width="400" border="1">
        <tr>
        	<td>1</td>
            <td>2</td>
            <td>3</td>
        </tr>
        <tr>
        	<td>4</td>
            <td>5</td>
            <td>6</td>
        </tr>
        <tr>
        	<td>7</td>
            <td>8</td>
            <td>9</td>
        </tr>
    </table>
    
    
    
    <jsptaglib:table row="3" column="3" width="400" border="1">
    1;2;3;
    4;5;6;
    7;8;9
    </jsptaglib:table>
    ```

* 표현과 연산의 분리

  * ```jsp
    <% for (int i=0; i<9; i++) { %>	// scriptlet
        이 문장은 <%= i %>번 반복됩니다. <br/> // HTML
    <% } %> 						//scriptlet
    
    
    JSP 태그 라이브러리를 이용한 경우
    </jsptaglib:for>
    ```

* 웹 페이지의 일관성!!

* 재사용성!! 
  
  * jar 파일 형식 패키지로 배포된다.



* 커스텀 태그 라이브러리
  * 태그 핸들러 클래스
    * 태그를 사용할 때 어떤 일을 할 것인지 정의하고 처리한다.
  * 태그 라이브러리 서술 파일(Tag Library Descriptor : TLD)
    * 프리픽스와 함께 TLD를 셋팅 해주어야 태그 라이브러리의 기능이 어떤 것이다 라고 알 수 있다.
    * 태그 라이브러리를 설명해주는 파일로, XML의 표준 형식을 따르는 XML 문서
    * 즉, JSP에서 태그 호출 시 태그를 처리하는 핸들러로 연결시켜줘서 관련 처리를 실행하게 해준다.



#### 커스텀 태그를 웹 페이지에 설정하고 이용하는 부분

* 커스텀 태그 정의 부분
  * <%@ taglib uri="쓰고자 하는 태그가 속한 라이브러리 TLD 파일 경로" prifix="prefix"%>
  * 예시
    * <%@ taglib uri="http://contactjsp.com/bar-1.0" prefix='bar' %>
* 커스텀 태그 사용 부분
  * Tag 내용 없는 경우:
    * <tagPrefix: 태그이름 속성이름="속성값".../>
  * Tag 내용이 있는 경우:
    * <tagPrefix: 태그이름 속성이름="속성값"...>
    * 다른 태그나 내용들
    * </tagPrefix: 태그이름>
  * 예시
    * <bar:Hbar values="7,4,12" fgcolor="#0000FF" width="220" labels="Orange, Apple, Pear" bgcolor="00FF00" />
* 태그 라이브러리 사용하기
  * /WEB_INF 라는 디렉토리가 있다!!
    * 그 하위에는 web.xml 파일이 있다.
    * description이 끝나는 위치에 속성을 정의할 필요가 있다.
    * \<taglib> 시작부터 \</taglib> 끝 까지







### 쿠키와 세션

* 세션이든 쿠키든 정보를 만들어지는 주체는 서버.
* 세션은 서버, 쿠키는 클라이언트에 저장됨
* 쿠키를 구현할 수 있는 쿠키 클래스
  * Javax.servlet.http 패키지에 있는  Cookie 클래스
* 쿠키 생성하기
  * Cookie myCookie = new Cookie("CookieName", "What a Delicious Cookie it is!")
  * 쿠키는 이렇게 스트링 타입 인자 두 개 받는 형식 취함
  * Cookiename : 생성되는 쿠키에 대한 이름
  * 2번째 인자 : 쿠키의 값
* 쿠키 셋팅
  * myCookie.setValue("Wow!");
  * 이름에 대응하는 값을 새롭게 지정할 때 사용
* 쿠키 전달
  * response.addCookie(myCookie);
  * 서버가 클라이언트에게 응답할 때 나가는 객체인 response객체에 쿠키를 불러줘야 쿠키가 브라우저측에 저장될 수 있다.
* 쿠키 읽기
  * request.getCookies();
  * 브라우져상에 기존에 존재하는 쿠키 정보를 읽어들일 수 있다.
  * request.getParameter(); 
  * 반환형은 Cookie[] 타입으로 변환 (여러 개 이므로)
* 쿠키 수명 주기
  * cookie.setMaxAge(int expiry);
  * Expiry는 초단위로 쿠기의 최대 수명을 설정
  * 처음 쿠키가 생성된 뒤 이 때 설정한 시간(초)동안 쿠키는 유효함
  * 이 시간을 벗어난 쿠키는 사용기간이 만료된 쿠키로 분류됨.
  * 예) 일주일로 쿠키 유효기간 설정 : cookie.setMaxAge(7\*24\*60\*60);
* 쿠키의 전송
  * 기본적으로 쿠키는 그걸 보낸 서버에게만 전송할 수 있다.
  * 도메인을 설정하면 같은 도메인상의 다른 호스트들과 공유 가능.
  * 쿠키를 이용할 도메인을 정하는 것!
  * Cookie 클래스의 setDomain(String domain)을 사용
* 쿠키 생성 과정
  * 쿠키 생성
    * Cookie myCookie = new Cookie(String, String)
  * 생성된 쿠키에 대한 설정
    * myCookie.setValue(String)
  * 설정이 완료된 쿠키 전송
    * response.addCookie(myCookie)
* 쿠키 사용 과정
  * 요청에 포함된 쿠키 가져오기
    * Cookie[] cookies = request.getCookies()
  * 쿠키 이름 읽기
    * cookies[i].getName()
  * 얻어진 이름을 통해 정보 사용
    * cookies[i].getValue()



### JSP 내부 객체 (JSP implict object)

* JSP 컨테이너가 제공하는 특별한 객체(변수)
  * Request
  * Response
  * Out
  * Session
  * ...

```jsp
== set_cookie2.jsp == 
<%@ page contentType="text/html; charset=EUC-KR" session="true" %>
<html>
    <head>
        <title>쿠키 생성하기</title>
    </head>
    <body>
        <% Cookie cookie = new Cookie("name", "Jimmy");
        response.addCookie(cookie);
        %>
        <a href="test_cookie.jsp">쿠키 확인하기</a>
	</body>
</html>

== test_cookie.jsp ==
<%@ page contentType="text/html; charset=EUC-KR" %>
<html>
    ...
    <body>
        <% 
        Cookie[] cookie = request.getCookies();
        if (cookies != null && cookies.length > 0)
            for(int i = 0; i<cookies.length; i++) {
                out.print(cookies[i].getName());
                out.print(" : ");
                out.print(cookies[i].getValue());
                out.print("<br>")
            }
        response.addCookie(cookie);
        %>
	</body>
</html>




쿠키 값 변경하기 - 기존의 키값과 같은 키에 대해 value를 수정해 add
== set_cookie2.jsp == 
<%@ page contentType="text/html; charset=EUC-KR" session="true" %>
<html>
    <head>
        <title>쿠키 생성하기</title>
    </head>
    <body>
        <% Cookie cookie = new Cookie("name", "Jimmy");
        response.addCookie(cookie);
        %>
        <a href="test_cookie.jsp">쿠키 확인하기</a>
	</body>
</html>
```



* 쿠키 삭제하기

```jsp
== remove_cookie.jsp ==
<%@ page contentType="text/html; charset=EUC-KR" %>
<html>
    ...
    <body>
        <% 
        Cookie[] cookie = request.getCookies();
        if (cookies != null && cookies.length > 0)
            for(int i = 0; i<cookies.length; i++) {
                if (cookies[i].getName().equals("name")) {
                    Cookie cookie = new Cookie("name", ""); // 쿠키 내용 수정
                    cookie.setMaxAge(0); // 쿠키 유효기간 0으로 셋팅
                    response.addCookie(cookie);
                }
            }
        %>
	</body>
</html>
```



#### 세션 인터페이스

* 세션은 javax.servlet.http 패키지의 HttpSession 인터페이스를 통해 세션을 사용할 수 있다.
* 쿠키는 text 파일로 저장되기 때문에, 사용자 웹 브라우저 상에 암호화 없이 그대로 저장시킴
  * 예전에는 쿠키를 많이 썼으나, 요즘엔 더 보안성을 추구한 세션을 많이 쓴다고 한다.
* 서버 관련 정보를 노출시키지 않기 위해 쿠키보다 HttpSession 인터페이스를 이용한 세션 상태 관리가 더 효율적
* 서버는 각 클라이언트로 발생한 요청에 대해 특정 ID를 부여함
* 이 ID를 이후에 발생한 클라이언트의 요청과 비교해서 같은 ID인지 확인
* 여기서 사용되는 유일한 값인 식별자는 클라이언트에 저장 (클라이언트에 저장되는 것은 쿠키에서와 달리 **이 식별자** 뿐)



#### 세션 객체

* 클라이언트 요청에 대한 context 정보의 세션과 관련된 정보(데이터)를 저장하고 관리하는 내부 객체 (웹 컨테이너가 제공하는 것)
* 다른 내부 객체는 JSP 페이지 스크립트릿(scriptlet)에서 묵시적으로 사용할 수 있지만 session 객체는 page directive의 session 속성이 true로 되어있어야 사용 가능하다.
  * <%@ page session = "true" %>
* Session 객체 메소드
  * String getId()
  * long getCreationTime()
  * long getLastAccessedTime()
  * void setMaxInactivInterval(time) : 세션 유지할 시간을 초단위로 설정
  * int getMaxInactiveInterval()
  * Boolean isNew()
  * void invalidate()



* 세션관리
  * 웹 컨테이너는 브라우저당 1개의 세션 ID정보를 부여함
  * 클라이언트의 최초 요청 시 세션 객체를 만들고 세션 아이디를 부여함
  * 최초 응답에 세션 아이디를 포함시켜 웹 브라우저로 전송
    * 브라우저 측에 쿠키로 저장한다.
  * 이후 브라우저는 자신의 세션 아이디를 웹 컨테이너에 전송!
  * 클라이언트가 보낸 세션 아이디를 이용해 사용자를 식별



* Session 내장 객체
  * HttpSession 인터페이스의 메소드
    * getId() : 고유 ID 리턴
    * getCreationTime() : 세션이 생성된 시간 리턴
    * getLastAccessedTime()
    * getMaxInactiveInterval() : 세션의 유효시간 초단위 설정
    * invalidate() : 세션 삭제



* Session 내장 객체 사용

```jsp
<%@ page session="true" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<html>
    <head>
        <title>생성된 세션 정보 출력하기</title>
    </head>
    <body>
        <% Date c_time = new Date(), m_time = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c_time.setTime(session.getCreationTime());
        m_time.setTime(session.getLastAccessedTime());
        %>

        <H3>
            세션 아이디 : <%=session.getId()%><br>
            세션 생성 시각 : <%=formatter.format(c_time)%><br>
            세션 최종 접근 시각 : <%=formatter.format(m_time)%><br>
        </H3>
	</body>
</html>
```



* Session 객체의 공유
  * 세션이 유지되는 동안 여러 요청을 처리하는데 사용되는 JSP페이지들 사이에서 session 객체가 공유 된다.
  * setAttribute(String Object)와 getAttributre(String)를 이용해 속성값을 지정 및 읽을 수 있음

```jsp
<%@ page contentType="text/html; sharset=euc-kr" %>
<%@ page session="true" %>
<%
	session.setAttribute("NAME", "Jimmy");
	session.setAttribute("ADDRESS", "Seoul");
%>
<html>
    <head>
        <title>세션 속성 설정하기</title>
    </head>
    <body>
        <H3>
            NAME :  <%=session.getAttribute("NAME")%><br>
            Address : <%=session.getAttribute("ADDRESS")%><br>
        </H3>
	</body>
</html>
```



* 세션의 삭제
  * 세션 삭제를 위해선 invalidate()를 사용
    * 세션과 함께 저장된 모든 속성이 삭제됨
  * 세션에 저장된 특정 속성만 삭제하려면 removeAtttribute(String name)를 사용
  * 사용자가 웹 브라우저를 종료하면 세션이 삭제됨
  * 웹 브라우저를 종료하지 않더라도 설정된 유효시간(타임아웃 시간)이 초과되면 세션이 삭제됨



* Session 삭제하기

```jsp
<%@ page contentType="text/html; sharset=euc-kr" %>
<%
	session.invalidate();
%>
<html>
    <head>
        <title>세션 삭제하기</title>
    </head>
    <body>
        <H3>
            세션 삭제되었습니다. <br>
            <a href="session_date_view.jsp">여기</a>를 클릭하면 삭제 여부 확인 가능
        </H3>
	</body>
</html>
```



* 세션의 유효시간

  * 클라이언트가 서버에 추가 요청을 하지 않고도 세션을 유지시킬 수 있는 최대 시간

* 설정 방법 1

  * web.xml 파일에 설정 정보를 기록

  * [톰캣 설치 폴더] \conf\web.xml에 다음과 같이 기록되어 있다.

    * \<session-coinfig>의 \<session-timeout> 태그에서 단위는 분임

    ```xml
    <web_app version="4.0" ... >
    ...
        <session-config>
            <session-timeout>30</session-timeout>
        </session-config>
    </web_app>
    ```

* 세션의 유효 시간 설정방법2

  * setMaxInactiveInterval(int interval) 사용

    * 타임아웃 설정이라 하며, 단위는 초
    * 0 이하의 수는 브라우저 종료시까지
    * 설정된 유효시간 내에 추가요청 없을 시 세션 삭제

    ```jsp
    <%
    	session.setMaxInactiveInterval(60*30);
    %>
    ```



* Session 객체를 수동으로 얻기
  * JSP에서 세션은 자동으로 만들어진다.
    * Session 객체는 HttpSession 유형
  * 서블릿 프로그램에서는 request.getSession()을 사용하여 현재 세션 객체를 이용 가능
    * request.getCookies() 랑 똑같이 수동으로 받아올 수 있다.
  * Request.getSession(false)는 기존 세션 객체를 리턴하거나, 세션이 존재하지 않으면 null을 리턴



* HttpSession 인터페이스

```jsp
<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page session="true" %>
<%
	HttpSession httpSession = request.getSession();
%>
<html>
    <head>
        <title>생성된 세션 정보 출력하기</title>
    </head>
    <body>
        세션 아이디1 : <%= session.getId() %><br>
        세션 아이디2 : <%= httpSession.getId() %><br>
    </body>
</html>
```



* 세션을 이용한 로그인/로그아웃 절차
  * 로그인 폼을 이용해 아이디, 비번 입력받기
  * 입력된 아이디, 비번 확인하기
  * 아이디, 비번 정확하면 세션 객체에 로그인 여부 확인을 위한 속성 설정
  * 로그인 여부는 세션 객체에 특정 속성 값이 설정되어 있는지 확인
  * 로그아웃은 세션 삭제로 구현
* 로그인 폼의 처리
  * 로그인을 위해 회원 DB 이용 부분은 생략
  * 로그인 성공시 session 객체에 속성을 저장
    * 이름은 Login, 값은 입력 받은 id로 설정
  * 차후 로그인 여부를 확인하려면 Login 속성의 값을 확인
    * 값이 null이 아니면 로그인 한 상태가 됨



* 로그인 작업(login.jsp)

```jsp
<title>로그인 처리화면</title>
</head>
<body>
    <%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
    if (id.equals("admin") && pw.equals("pass")) {
        session.setAttribute("LOGIN", id);
        out.print("<h2>로그인 성공</h2>");
    } else {
        out.print("<h2>로그인 실패</h2>");
    }
    %>
</body>
```



* 로그인 확인 작업

```jsp
<title>로그인 처리화면</title>
</head>
<body>
    <%
	String id = (String)session.getAttribute("LOGIN");
    Boolean isLogin = false;
    if(id != null) isLogin = true;
    if (isLogin)
        out.print("<h2>로그인 한 상태입니다. ID=" + id + "</h2>");
    else
        out.print("<h2>로그인 안한 상태입니다.</h2>");
    %>
</body>
```



* 로그아웃 작업

```jsp
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page session=true" %>
<title>로그아웃</title>
</head>
<body>
    <%
    session.invalidate();
    %>
</body>
<html>
    <head>
        <title>로그아웃 화면</title>
    </head>
    <body>
        <h2>
            로그아웃 되었습니다.
        </h2>
    </body>
</html>
```



### 자바빈 개념과 정의

* 자바빈이란?
  * 자바로 만든 클래스! 인데, 설계 규약에 따라 코딩하는 것
* 사용 이유
  * JSP 페이지에서 자바 프로그램을 활용할 목적
  * 자바빈 규약에 맞추어 사용한..
  * 비즈니스 로직과 프레젠테이션 로직의 분리
    * 비즈니스는 자바빈, 프리젠테이션은 JSP
  * 여러 JSP 페이지에서 자바빈을 재사용
    * 별토의 클래스 파일로 존재한다.



* 자바빈 설계 규약
  * 스프링이나 다른 상용화된 프레임워크 등도 이 자바빈 규약을 따른다고..! 알아야 해
* **자바빈 클래스 특징**
  * 생성자가 필요하면 파라미터가 없는 기본 생성자를 반드시 만들어야 한다!
  * 읽기와 쓰기 가능한 속성(변수)을 가질 수 있다.
  * 속성(변수)들에 대해 getter, setter를 정의해야 한다.

* Getter, Setter
  * 이건 설명 필요 없지?



* <jsp:useBean id="name" class="package.Class" />

  * package.Class 타입의 클래스를 name이라는 이름으로 초기화 한다는 뜻
  * new 연산자를 통해 객체를 선언하고 인스턴스화 하는 것과 유사
  * package.Class name = new package.Class();

* \<jsp:useBean>  type 속성

  ```java
  == Longin.java ==
  package beans;
  public class Login {
      String userName;
      public void setUserName(String userName) {
          this.userName = userName;
      }
      public String getuserName() {
          return userName;
      }
  }
  ```

  

* 사용 예제

```jsp
== Login.html ==
<html>
    <head>
        <title>JavaBeans 테스트</title>
    </head>
    <body>
        <form action=Login.jsp method=post>
            이름을 입력해주세요.. <br><br>
            <input type=text name="userName">
            <input type=submit value="Send">
        </form>
    </body>
</html>
```

* 자바빈즈 클래스의 위치!!

  * 컴파일 한 Login.class 파일은 다음 디렉토리에 위치해야 한다.

  ![image-20201130210725927](C:\Users\smpsm\AppData\Roaming\Typora\typora-user-images\image-20201130210725927.png)



* Login.jsp

```jsp
== Login.jsp ==
<%@ page contentType="text/html; charset=euc-kr" %>
<jsp:useBean id="Login" class="beans.Login">
    <jsp:setProperty name="Login" property="userName"/>
</jsp:useBean>
<html>
    <head>
        <title>JavaBeans 테스트</title>
    </head>
    <body>
        <b><jsp:getProperty name="Login" property="userName"/></b>님반갑습니다..
    </body>
</html>
```



* 자바빈 사용

```jsp
= Test1.jsp =
<html>
    <BODY>
        <H2>Scriptlet Test</H2>
        <form method=post action="test2.jsp">
            <input type=text name=name size=15/>
            <input type=submit value="Send">
        </form>
    </BODY>
</html>

= Test2.jsp =
<html>
    <body>
        <h1>Scriptlet Example</h1>
        <% String nameVal = request.getParameter("name"); out.println(nameVal); %>
    </body>
</html>


```

