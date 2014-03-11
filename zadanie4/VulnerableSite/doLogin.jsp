<%@ page language="java" errorPage="" %>
<%  
    String sUserID=request.getParameter("sUserName");
    String sPassword=request.getParameter("sPwd");
    String message="User login successfully ";
    
    if(sUserID.equals("admin") && sPassword.equals("hasloadmina")){ 
      session.setAttribute("admin", true);
      response.sendRedirect("success.jsp?error="+message);
    }
    else if(sUserID.equals("cienias") && sPassword.equals("haslocieniasa")){
		session.setAttribute("admin", false);
		response.sendRedirect("success.jsp?error="+message);
	}
    else {
      message="No user or password matched" ;
      response.sendRedirect("login.jsp?error="+message);
    }
    String sessionid = request.getSession().getId();
%>
