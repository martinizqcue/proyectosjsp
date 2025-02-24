<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Proyectos" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Tarea</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Registrar Nueva Tarea</h2>
    
    <!-- Formulario para registrar nueva tarea -->
    <form action="svTareas" method="post">
        <label for="idProyecto">ID del Proyecto:</label><br>
        <input type="number" name="idProyecto" required><br><br>

        <label for="descripcionTarea">Descripción de la Tarea:</label><br>
        <textarea name="descripcionTarea" rows="4" cols="50" required></textarea><br><br>

        <label for="responsable">Responsable:</label><br>
        <input type="text" name="responsable" required><br><br>

        <label for="fechaInicio">Fecha de Inicio:</label><br>
        <input type="date" name="fechaInicio" required><br><br>

        <label for="fechaFin">Fecha de Fin:</label><br>
        <input type="date" name="fechaFin" required><br><br>

        <label for="estado">Estado:</label><br>
        <select name="estado" required>
            <option value="pendiente">Pendiente</option>
            <option value="en progreso">En Progreso</option>
            <option value="completada">Completada</option>
        </select><br><br>

        <button type="submit">Registrar Tarea</button>
    </form>

    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>

    <br>
    <!-- Botón para volver a la página correspondiente -->
        <c:choose>
            <c:when test="${sessionScope.role == 'admin'}">
                <a href="admin.jsp" style="display:inline-block; padding:10px 20px; background-color: #4CAF50; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver a Admin</a>
            </c:when>
            <c:when test="${sessionScope.role == 'invitado'}">
                <a href="invitado.jsp" style="display:inline-block; padding:10px 20px; background-color: #4CAF50; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver a Invitado</a>
            </c:when>
            <c:otherwise>
                <a href="index.jsp" style="display:inline-block; padding:10px 20px; background-color: #FF5733; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver al Inicio</a>
            </c:otherwise>
        </c:choose>
</body>
</html>
