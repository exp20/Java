<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Update recipe</title>
</head>
<body>
<table align="center">
    <form action="updateRecipe" method="POST" >
        <p>Id</p>
        <p><input type="text"  name="id" value="${recipe.id}" readonly/></p>
        <p>Doctor id</p>
        <p><input type="text"  name="doctor_id" value="${recipe.doctor.id}" readonly/></p>
        <p>Patient id </p>
        <p><input type="text"  name="patient_id" value="${recipe.patient.id}" readonly/></p>
        <p>Priority</p>
        <p><input type="text"  name="priority" value="${recipe.priority}"/></p>
        <p>Description</p>
        <p><input type="text"  name="description" value="${recipe.description}"/></p>
        <p><input type="submit" value="update"/><p>
    </form>
</table>
<a href="recipes">Back</a>
<a href="index">Main page</a>

</body>
</html>