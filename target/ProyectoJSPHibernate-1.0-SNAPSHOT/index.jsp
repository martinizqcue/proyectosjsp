<html>
    <head>
        <title>Inicio</title>
    </head>
    <body>
        <h2>Bienvenido</h2>
        <form action="svLogin" method="post">
            <label>Nombre de Usuario: </label>
            <input type="text" name="nombre_usuario" required />
            <br />
            <label>Contrase�a: </label>
            <input type="password" name="contrase�a" required />
            <br />
            <button type="submit">Entrar</button>
        </form>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
    </body>
</html>
