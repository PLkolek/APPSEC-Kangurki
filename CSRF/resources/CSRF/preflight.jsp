<%@ page language="java" errorPage="" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.DataOutputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>

<html><body>

  
  <script>
  var http = new XMLHttpRequest();
  
  http.open("POST", 'http://127.0.0.1:8888/csrf/greet', true);
 
  http.setRequestHeader("Content-type", "text/x-gwt-rpc");
  http.setRequestHeader("Content-length", 0);
  http.setRequestHeader("Connection", "close");

  http.onreadystatechange = function() {//Call a function when the state changes.
      if(http.readyState == 4 && http.status == 200) {
          alert(http.responseText);
      }
  }
  http.send(null)
  </script>
  aaaa
</body></html>

