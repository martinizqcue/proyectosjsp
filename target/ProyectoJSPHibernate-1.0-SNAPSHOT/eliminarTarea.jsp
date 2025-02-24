<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Tarea</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Eliminar Tarea</h2>

    <form action="svTareas" method="post">
        <label for="idTarea">ID de la Tarea:</label>
        <input type="number" id="idTarea" name="idTarea" required>
        <button type="submit" name="action" value="eliminar">Eliminar</button>
    </form>

    <br>
    <a href="admin.jsp" style="display:inline-block; padding:10px 20px; background-color: #4CAF50; color: white; text-align: center; text-decoration: none; border-radius: 5px;">Volver al Administrador</a>
</body>
</html>
