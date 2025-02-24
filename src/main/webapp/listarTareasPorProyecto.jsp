<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Proyectos" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Tareas</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <h2>Lista de Tareas</h2>
        <!-- Mensaje de éxito -->
    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>

    <!-- Mensaje de error -->
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

        <!-- Formulario para seleccionar el proyecto y filtrar las tareas -->
        <form action="svTareas" method="get">
            <label>Selecciona Proyecto:</label>
            <select name="idProyecto">
                <!-- Verificar si la lista de proyectos no está vacía -->
                <c:if test="${not empty proyectos}">
                    <c:forEach var="proyecto" items="${proyectos}">
                        <option value="${proyecto.id}">${proyecto.nombreProyecto}</option>
                    </c:forEach>
                </c:if>
                <!-- Si la lista está vacía, mostrar un mensaje de que no hay proyectos disponibles -->
                <c:if test="${empty proyectos}">
                    <option value="">Pulsa el botón Filtrar para mostrar los Proyectos</option>
                </c:if>
            </select>
            <button type="submit">Filtrar</button>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descripción</th>
                    <th>Responsable</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tarea" items="${tareas}">
                    <tr>
                        <td>${tarea.id}</td>
                        <td>${tarea.descripcionTarea}</td>
                        <td>${tarea.responsable}</td>
                        <td>${tarea.fechaInicio}</td>
                        <td>${tarea.fechaFin}</td>
                        <td>${tarea.estado}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <br>
        <!-- Botón para cerrar sesión -->
        <form action="logout.jsp" method="post">
            <button type="submit">Cerrar Sesión</button>
        </form>

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
