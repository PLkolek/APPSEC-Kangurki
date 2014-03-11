<%@ page language="java" import="java.io.*" errorPage="" %>
<%
	String location=request.getParameter("location");
	String nameOfFile = "/var/lib/tomcat7/webapps/TEST/location.txt";

	try {   
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfFile));
		pw.println(location);
		//clean up
		pw.close();
		response.sendRedirect("success.jsp?error=Location saved");
	} catch(IOException e) {
		out.println(e.getMessage());
	}
%>
