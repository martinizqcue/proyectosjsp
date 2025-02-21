<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Invitado - Opciones</title>
    </head>
    <body>
        <h2>Bienvenido Invitado</h2>
        <h3>Opciones</h3>
        <ul>
            <li><a href="listarProyectos.jsp">Mostrar Lista de Proyectos</a></li>
            <li><a href="registroProyecto.jsp">Registrar Proyecto</a></li>
            <li><a href="listarTareas.jsp">Mostrar Lista de Tareas de Proyecto</a></li>
            <li><a href="registroTarea.jsp">Registrar Tarea</a></li>
        </ul>
        <!-- Botón de Cerrar Sesión -->
        <form action="logout.jsp" method="post">
            <button type="submit">Cerrar Sesión</button>
        </form>
        
    </body>
</html>

