<%@ page import="java.util.List" %>
<%@ page import="entities.Proyectos" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Proyectos</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <h2>Lista de Proyectos</h2>
        <!-- Mensaje de éxito -->
    <c:if test="${not empty sessionScope.successMessage}">
        <p style="color: green;">${sessionScope.successMessage}</p>
    </c:if>

    <!-- Mensaje de error -->
    <c:if test="${not empty sessionScope.errorMessage}">
        <p style="color: red;">${sessionScope.errorMessage}</p>
    </c:if>
        <form action="svProyectos" method="get">
            <label>Estado del Proyecto:</label>
            <select name="estado">
                <option value="en curso">En Curso</option>
                <option value="completado">Completado</option>
                
            </select>
            <button type="submit">Filtrar</button>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre Proyecto</th>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>ID Usuario</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="proyecto" items="${proyectos}">
                    <tr>
                        <td>${proyecto.id}</td>
                        <td>${proyecto.nombreProyecto}</td>
                        <td>${proyecto.descripcion}</td>
                        <td>${proyecto.fechaInicio}</td>
                        <td>${proyecto.fechaFin}</td>
                        <td>${proyecto.idUsuario}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <!-- Botón de Cerrar Sesión -->
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
