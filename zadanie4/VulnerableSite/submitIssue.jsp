<%@ page language="java" import="java.io.*" errorPage="" %>
<%
	String issue=request.getParameter("issue");
	String nameOfFile = "/var/lib/tomcat7/webapps/TEST/issue.txt";

	try {   
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfFile));
		pw.println(issue);
		//clean up
		pw.close();
		response.sendRedirect("success.jsp?error=Issue saved");
	} catch(IOException e) {
		out.println(e.getMessage());
	}
%>
