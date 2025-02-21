<%@ page import="java.util.List" %>
<%@ page import="entities.Proyectos" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar Proyecto</title>
    </head>
    <body>
        <h2>Formulario de Registro de Proyecto</h2>
        <form action="svProyectos" method="post">
            <input type="hidden" name="accion" value="registrar">
            <label for="nombreProyecto">Nombre del Proyecto:</label>
            <input type="text" id="nombre_proyecto" name="nombre_proyecto" required><br><br>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required></textarea><br><br>

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" id="fecha_inicio" name="fecha_inicio" required><br><br>

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" id="fecha_fin" name="fecha_fin" required><br><br>

            <label for="estado">Estado:</label>
            <select id="estado" name="estado" required>
                <option value="en curso">En Curso</option>
                <option value="completado">Completado</option>                
            </select><br><br>

            <label for="idUsuario">ID Usuario:</label>
            <input type="number" id="idUsuario" name="idUsuario" value="${sessionScope.idUsuario}" readonly><br><br>

            <input type="submit" value="Registrar Proyecto">
        </form>

        <!-- Botón de Cerrar Sesión -->
        <form action="logout.jsp" method="post">
            <button type="submit">Cerrar Sesión</button>
        </form>
        <!-- Botón para volver a admin.jsp -->
        <a href="admin.jsp" style="display:inline-block; padding:10px 20px; background-color: #4CAF50; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver a Admin</a>
    
        <!-- Botón para volver a admin.jsp 
        <form action="admin.jsp">
            <button type="submit" style="padding:10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px;">Volver a Admin</button>
        </form>-->
    </body>
</html>
