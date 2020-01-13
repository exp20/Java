<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>


<p><a href="index">Main page</a></p>
<p>${err.toString()}<p>
<p><p>
<p>
    <c:forEach items="${message}" var="message">
    <tr>
       <td>${message.getClassName()}
           ${message.toString()}
       </td>
    </tr>
</c:forEach>
</p>
<button type="button" name="Back" onclick="history.back()">back</button>
<p></p>
