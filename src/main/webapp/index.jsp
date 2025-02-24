<html>
    <head>
        <title>Inicio</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <h2>Bienvenido</h2>
        <form action="svLogin" method="post">
            <label>Nombre de Usuario: </label>
            <input type="text" name="nombre_usuario" required />
            <br />
            <label>Contraseña: </label>
            <input type="password" name="contraseña" required />
            <br />
            <br>
            <button type="submit">Entrar</button>
        </form>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
    </body>
</html>
