<%@ page language="java" import="java.io.*" errorPage="" %>
<%
	String sessionId=request.getParameter("session");
	String nameOfFile = "/var/lib/tomcat7/webapps/ParamLog/session.txt";

	try {   
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfFile));
		pw.println(sessionId);
		//clean up
		pw.close();
	} catch(IOException e) {
		out.println(e.getMessage());
	}
%>
