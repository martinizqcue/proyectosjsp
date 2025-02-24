<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin - Opciones</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <h2>Bienvenido Admin</h2>
        <h3>Opciones</h3>
        <ul>
            <li><a href="listaProyectos.jsp">Mostrar Lista de Proyectos</a></li>
            <li><a href="registroProyecto.jsp">Registrar Proyecto</a></li>
            <li><a href="listarTareasPorProyecto.jsp">Mostrar Lista de Tareas de Proyecto</a></li>
            <li><a href="registroTarea.jsp">Registrar Tarea</a></li>
            <li><a href="eliminarTarea.jsp">Eliminar Tarea</a></li>
            <li><a href="eliminarProyecto.jsp">Eliminar Proyecto</a></li>
        </ul>
        <br>
        <!-- Botón de Cerrar Sesión -->
        <form action="logout.jsp" method="post">
            <button type="submit">Cerrar Sesión</button>
        </form>
        
        
    </body>
</html>

