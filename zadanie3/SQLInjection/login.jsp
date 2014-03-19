<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%
String error=request.getParameter("error");
if(error==null || error=="null"){
 error="";
}
%>
<html>
<head>
<title>User Login JSP</title>
</head>

<body>
<div><%=error%></div>
<form name="frmLogin" action="doLogin.jsp" method="post">
User Name <input type="text" name="sUserName" /><br />
Password <input type="password" name="sPwd" /><br />
<input type="submit" name="sSubmit" value="Submit" />
</form>
<% if(session.getAttribute("username")!=null) { %>
	<form name="frmChange" action="doChange.jsp" method="post">
	Dane <input type="text" name="sUserName" /><br />
	<input type="submit" name="sSubmit" value="Submit" />
	</form>
<% } %>

<% if(session.getAttribute("username")!=null) { %>
	<form name="frmZnajdzPoDanych" action="doFind.jsp" method="post">
	<input type="submit" name="sSubmit" value="Submit" />
	</form>
<% } %>

</body>
</html>

