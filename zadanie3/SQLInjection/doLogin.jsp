<%@ page language="java" import="java.sql.*" errorPage="" %>
<%  
    String sUserID=request.getParameter("sUserName");
    String sPassword=request.getParameter("sPwd");
    String message="User login successfully ";    
    try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/APPSEC", "mk", "");
		
		if (connection != null) {
			String query = "SELECT * FROM USERS where username=\'" + sUserID+"\' AND password=\'"+sPassword+"\';";
			
			Statement stmt = null;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next())
				message="Login failed";
			response.sendRedirect("login.jsp?error="+message);
		} else {
			message="No connection" ;
			response.sendRedirect("login.jsp?error="+message);
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace(new java.io.PrintWriter(out));
	} catch (SQLException e) {
		e.printStackTrace(new java.io.PrintWriter(out));
	}
%>
		
