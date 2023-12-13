<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Iniciar Sesión</title>
    <style>
        /* Estilos para centrar el formulario */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #ffe4b5; 
        }

        /* Estilos para el formulario */
        form {
            text-align: center;
            background-color: #f2f2f2;
            border-radius: 25px;
            padding: 50px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        /* Estilos para el título */
        h1 {
            color: #001f3f; /* Azul marino bonito */
        }

        /* Estilos para las etiquetas y campos de entrada */
        label {
            display: block;
            margin: 10px 0;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Estilos para el botón */
        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 20px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Estilos para el mensaje de error */
        .error-message {
            color: #dc3545; /* Rojo oscuro */
            font-size: 16px;
            margin-top: 10px;
        }

        /* Estilos para el mensaje de registro */
        .register-message {
            margin-top: 20px;
            font-size: 16px;
            color: #007BFF; /* Azul del enlace */
            cursor: pointer;
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <form action="LoginServlet" method="post">
        <h1>Mi Parking</h1>

        <label for="DNI">DNI:</label>
        <input type="text" id="DNI" name="DNI" required>

        <label for="Password">Password:</label>
        <input type="Password" id="Password" name="Password" required>

        <input type="submit" value="Login">

        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <!-- Mensaje de registro -->
        <div class="register-message" onclick="location.href='registro.html'">No tienes una cuenta? Registrate aqui</div>
    </form>
</body>

</html>
