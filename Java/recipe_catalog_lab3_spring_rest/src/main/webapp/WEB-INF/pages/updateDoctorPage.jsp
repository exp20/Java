<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Update doctor</title>
</head>
<body>
<table align="center">
    <form action="updateDoctor" method="POST" >
        <p>Id</p>
       <p><input type="text"  name="id" value="${doctor.id}" readonly/></p>
        <p>Name</p>
        <p><input type="text"  name="name" value="${doctor.name}"/></p>
        <p>Last name</p>
        <p><input type="text"  name="last_name" value="${doctor.last_name}"/></p>
        <p>Patronymic</p>
        <p><input type="text"  name="patronymic" value="${doctor.patronymic}"/></p>
        <p>Specialization</p>
        <p><input type="text"  name="specialization" value="${doctor.specialization}"/></p>
        <p><input type="submit" value="update"/><p>
    </form>
</table>
<a href="doctors">Back</a>
<a href="index">Main page</a>

</body>
</html>