<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styles/styles.css"/>
    <title>Patients</title>
</head>
<body>
<table align="center">
    <thead>
    <th>id</th>
    <th>description</th>
    <th>doctor_id</th>
    <th>patient_id</th>
    <th>priority</th>
    </thead>
    <tbody>
    <c:forEach items="${recipes}" var="recipe">
        <td>${recipe.id}</td>
        <td>${recipe.description}</td>
        <td>${recipe.doctor.id}</td>
        <td>${recipe.patient.id}</td>
        <td>${recipe.priority}</td>
        <td>
            <form action="updateRecipePage" method="POST" >
                <input type="text"  name="idRecipeUpdate" value="${recipe.id}" hidden="true"/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td>
            <form action="deleteRecipe" method="POST" >
                <input type="text"  name="id" value="${recipe.id}" hidden="true"/>
                <input type="submit" value="delete"/>
            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="index">Main page</a>
<form action="addRecipe" method="POST">
    Add a recipe <br>
    <div>
        <label>Doctor id</label> <input type="number" name="doctor_id"/>
        <label>Patient id</label> <input type="number"name="patient_id"/>
        <label>description</label> <input type="text" name="description"/>
        <label>priority</label> <input type="text" name="priority"/>
    </div>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
</body>
</html>