<%@page pageEncoding="UTF-8"%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
<p><a href="index">Main page</a></p>
<button type="button" name="Back" onclick="history.back()">back</button>
<p></p>

<%
    /*for(Throwable elem : pageContext.getException().getSuppressed()) {
        out.println("<br>" + elem.getCause());
        out.println("<br>" + elem.getMessage());
    }*/

    for(StackTraceElement elem : pageContext.getException().getStackTrace()){
        out.println("<br>" + elem.toString());
    }

   // out.println(pageContext.getException().getCause().getMessage());
    out.println("<p><p>");

  //  pageContext.getException().printStackTrace(new java.io.PrintWriter(out));
%>

</body>
</html>