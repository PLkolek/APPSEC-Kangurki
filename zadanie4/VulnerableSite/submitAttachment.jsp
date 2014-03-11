<%@ page language="java" import="java.io.*" errorPage="" %>
<%
File file = new File("/var/lib/tomcat7/webapps/TEST/location.txt");
FileInputStream fis = new FileInputStream(file);
byte[] data = new byte[(int)file.length()];
fis.read(data);
fis.close();

String s = new String(data, "UTF-8");
	String name=request.getParameter("fileName");
	String nameOfFile = s.trim()+name.trim();
	String attachment=request.getParameter("attachment");
	try {   
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfFile));
		pw.println(attachment);
		//clean up
		pw.close();
		response.sendRedirect("success.jsp?error=Location saved");
	} catch(IOException e) {
		out.println(e.getMessage());
	}
%>
