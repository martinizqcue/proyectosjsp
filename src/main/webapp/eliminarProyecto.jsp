<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Eliminar Proyecto</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    

    <h2>Eliminar Proyecto</h2>
    <form method="POST" action="svProyectos">
        <input type="hidden" name="accion" value="eliminarProyecto" />
        <label for="idProyecto">ID Proyecto a eliminar:</label>
        <input type="text" name="idProyecto" id="idProyecto" />
        <input type="submit" value="Eliminar Proyecto" />
    </form>
    <br>
    <a href="admin.jsp" style="display:inline-block; padding:10px 20px; background-color: #4CAF50; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver al Administrador</a>
</body>
</html>
