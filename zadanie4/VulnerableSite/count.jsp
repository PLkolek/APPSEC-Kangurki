<%@ page language="Java" %>     

<html>     

<head>     

<title>A Simple JSP</title>     

</head>     

<body>     

<p>     

  <% String num = request.getParameter("num");     

     if (num == null) { // No number specified     

       // Display the form     

  %>     

       <form action="<%= HttpUtils.getRequestURL(request) %>">     

       What should I count to? <input type="text" size="2" name="num" />     

       <input type="submit" />     

       </form>     

  <%     

     } else for (int i=1; i<=Integer.parseInt(num); i++) {     

  %>     

       Counting: <%= i %><br />     

  <% }     

  %>     

</p>     

     

<%@ include file="theEnd.html" %>     

     

</body>     

</html>
