<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Update patient</title>
</head>
<body>
<table align="center">
    <form action="updatePatient" method="POST" >
        <p>Id</p>
       <p><input type="text"  name="id" value="${patient.id}" readonly"/></p>
        <p>Name</p>
        <p><input type="text"  name="name" value="${patient.name}"/></p>
        <p>Last name</p>
        <p><input type="text"  name="last_name" value="${patient.last_name}"/></p>
        <p>Patronymic</p>
        <p><input type="text"  name="patronymic" value="${patient.patronymic}"/></p>
        <p>Phone</p>
        <p><input type="number"  name="phone" value="${patient.phone}"/></p>
        <p><input type="submit" value="update"/><p>
    </form>
</table>
<a href="patients">Back</a>
<a href="index">Main page</a>

</body>
</html>