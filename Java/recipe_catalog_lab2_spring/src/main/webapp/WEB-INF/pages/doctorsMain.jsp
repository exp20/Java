<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css"/>
    <title>Doctors</title>
</head>
<body>
<table align="center">
    <thead>
    <th>id</th>
    <th>Name</th>
    <th>Last name</th>
    <th>Patronymic</th>
    <th>Specialization</th>
    </thead>
    <tbody>
    <c:forEach items="${doctors}" var="doctor">
            <td>${doctor.id}</td>
            <td>${doctor.name}</td>
            <td>${doctor.last_name}</td>
            <td>${doctor.patronymic}</td>
            <td>${doctor.specialization}</td>
        <td>
            <form action="updateDoctorPage" method="POST" >
                <input type="text"  name="idDoctorUpdate" value="${doctor.id}" hidden="true"/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td>
            <form action="deleteDoctor" method="POST" >
                    <input type="text"  name="id" value="${doctor.id}" hidden="true"/>
                    <input type="submit" value="delete"/>
            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="index">Main page</a>
<form action="addDoctor" method="POST">
    Add a doctor <br>
    <div>
        <label>Name</label> <input type="text" name="name"/>
        <label>Last Name</label> <input type="text"name="last_name"/>
        <label>Patronymic</label> <input type="text" name="patronymic"/>
        <label>Specialization</label> <input type="text" name="specialization"/>
    </div>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
</body>
</html>