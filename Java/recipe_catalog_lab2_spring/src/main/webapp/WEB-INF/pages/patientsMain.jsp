<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>

    <title>Patients</title>
</head>
<body>
<table align="center">
    <thead>
    <th>id</th>
    <th>Name</th>
    <th>Last name</th>
    <th>Patronymic</th>
    <th>Phone</th>
    </thead>
    <tbody>
    <c:forEach items="${patients}" var="patient">
        <td>${patient.id}</td>
        <td>${patient.name}</td>
        <td>${patient.last_name}</td>
        <td>${patient.patronymic}</td>
        <td>${patient.phone}</td>
        <td>
            <form action="updatePatientPage" method="POST" >
                <input type="text"  name="idPatientUpdate" value="${patient.id}" hidden="true"/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td>
            <form action="deletePatient" method="POST" >
                <input type="text"  name="id" value="${patient.id}" hidden="true"/>
                <input type="submit" value="delete"/>
            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="index">Main page</a>
<form action="addPatient" method="POST">
    Add a patient <br>
    <div>
        <label>Name</label> <input type="text" name="name"/>
        <label>Last Name</label> <input type="text"name="last_name"/>
        <label>Patronymic</label> <input type="text" name="patronymic"/>
        <label>Phone</label> <input type="number" name="phone"/>
    </div>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
</body>
</html>