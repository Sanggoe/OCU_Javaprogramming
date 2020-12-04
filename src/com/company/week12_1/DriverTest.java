package com.company.week12_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
