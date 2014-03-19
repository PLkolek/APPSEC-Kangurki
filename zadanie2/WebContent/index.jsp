<%@page import="org.omg.PortableInterceptor.PolicyFactory"%>
<%@page import="org.owasp.esapi.filters.ESAPIFilter"%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@page import="org.owasp.html.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zadanie 2</title>
</head>
<body>
<form name="msgForm" method="post" action="index.jsp">
Message: <input type="text" name="message"> <br>
</form>

<%
String str = request.getParameter("message");
if (str != null) {
	// out.print(str);
	out.print(ESAPI.encoder().encodeForHTML(str));
}

//String link = str;
String link = ESAPI.encoder().encodeForURL(str);
String text = "";
if (str != null) {
	org.owasp.html.PolicyFactory policy = new HtmlPolicyBuilder().allowElements("b").toFactory();
	text = policy.sanitize(str);
}
%>

<a href="<%= link %>">click me</a>

<%= text %>
abcdefgh
</body>
</html>