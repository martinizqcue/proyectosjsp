<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cerrar Sesión</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <%
            // Obtener la sesión y invalidarla
           
            if (session != null) {
                session.invalidate(); // Invalidar la sesión
            }
        %>

        <p>Has cerrado sesión exitosamente. Redirigiendo al inicio...</p>
        <script>
            setTimeout(function() {
                window.location.href = "index.jsp"; // Redirigir a la página de inicio
            }, 2000); // Redirigir después de 2 segundos
        </script>
    </body>
</html>
