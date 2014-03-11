<%@ page contentType="text/html; charset=iso-8859-1" import="java.io.*" language="java"%>
<html>
<head>
	<title>Successfully Login by JSP</title>
</head>

<%
String error=request.getParameter("error");
if(error==null || error=="null"){
	error="";
}
Boolean admin = (Boolean) session.getAttribute("admin");
if(admin==null)
	admin=false;

File file = new File("/var/lib/tomcat7/webapps/TEST/issue.txt");
FileInputStream fis = new FileInputStream(file);
byte[] data = new byte[(int)file.length()];
fis.read(data);
fis.close();

String s = new String(data, "UTF-8");

%>
<body>
	<div><%=error%></div>
	Session Value<br />
	<%
	out.print("Admin :"+ admin +"<br>");
	%>
	Saved issue: <br/>
	<% out.print(s); %>
	<br />
	
	<form name="formIssue" action="submitIssue.jsp" method="post">
		Issue <textarea  name="issue" /></textarea><br />
		<input type="submit" name="sSubmit" value="Submit" />
	</form>
	
	<% if ( admin!= null && admin ) { %>
		<form name="formAttachmentLocation" action="submitLocation.jsp" method="post">
			Location <input type="text" name="location" /><br />
			<input type="submit" name="sSubmit" value="Submit" />
		</form>
	<%}%>
	
	<form name="formAttachment" action="submitAttachment.jsp" method="post">
		Filename <input type="text" name="fileName" /><br />
		Attachment <textarea  name="attachment" /></textarea><br />
		<input type="submit" name="sSubmit" value="Submit" />
	</form>
</body>
</html>
