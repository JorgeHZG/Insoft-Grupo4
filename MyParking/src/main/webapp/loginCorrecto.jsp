<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Exitoso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .top-container {
            background-color: #007BFF;
            color: white;
            padding: 20px;
            text-align: center;
            width: 100%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 80%;
            margin: auto;
        }

        .user-info {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            margin-bottom: 40px; /* Aumentado el margen inferior */
        }

        .info-container {
            border: 2px solid #4285f4;
            border-radius: 10px;
            padding: 10px;
            margin-top: 10px;
        }

        .info-label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }

        .navigation {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px; /* Bajada de la posición */
        }
        
        /* Estilos para el mensaje de error */
        .confirm-Message {
            color: blue; 
            font-size: 16px;
            margin-top: 10px;
        }
        

        /* Estilos para el botón */
        .mode-button,
        .logout-button {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 30px;
            padding: 20px;
            margin-bottom: 15px; /* Separación entre botones */
            margin-right: 20px;
            cursor: pointer;
            width: 250px;
        }

        .logout-button {
            background-color: #FF0000;
        }
    </style>
</head>
<body>

    <div class="top-container">
        <h2>BIENVENIDO DE NUEVO</h2>
    </div>

    <div class="container">
        <div class="user-info">
            <h1 style="color: #4285f4;">¡Éxito en el Login!</h1>

            <div class="info-container">
                <span class="info-label">Nombre:</span>
                <p>${nombre}</p>
            </div>

            <div class="info-container">
                <span class="info-label">Apellido:</span>
                <p>${apellido}</p>
            </div>

            <div class="info-container">
                <span class="info-label">DNI:</span>
                <p>${dni}</p>
            </div>
        </div>
                <c:if test="${not empty confirmMessage}">
            <div class="confirm-Message">${confirmMessage}</div>
        </c:if>

        

        <div class="navigation">
            <div class="button-container">
                <button class="mode-button" onclick="location.href='ListarCoches'">RESERVAR PLAZA</button>
                <button class="mode-button" onclick="location.href='AparcarCoche.jsp'">APARCAR COCHE</button>
                <button class="logout-button" onclick="location.href='DesaparcarCoche.jsp'">DESAPARCAR COCHE</button>
                <button class="mode-button" onclick="location.href='Estadisticas.jsp'">ESTADISTICAS</button>
                <button class="mode-button" onclick="location.href='RegistroCoche.jsp'">ANADIR COCHE</button>
                <button class="logout-button" onclick="location.href='login.html'">LOGOUT</button>
            </div>
        </div>
    </div>

</body>
</html>
