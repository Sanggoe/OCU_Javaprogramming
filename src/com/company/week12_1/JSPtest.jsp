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

    // rs��� ��ü�� �����Ͱ� ��� ���� ��.
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
        <td><%= id %></td> <!-- = �ϸ� ������ ���� �� �������� ǥ��! -->
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

