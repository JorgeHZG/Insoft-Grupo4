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
            background-color: #5f9ea0;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .top-container {
            background-color: #294a61;
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
            border: 2px solid #294a61;
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
        .navigation {
	        display: flex;
	        flex-direction: column;
	        align-items: center;
	        margin-top: 20px; /* Bajada de la posición */
		    }
	
	    .button-container {
	        display: flex; /* Alinea los botones horizontalmente */
	    }
	
	    .mode-button,
	    .logout-button {
	        border: none;
	        border-radius: 30px;
	        padding: 20px;
	        margin-bottom: 15px; /* Separación entre botones */
	        margin-right: 15px;
	        cursor: pointer;
	        width: 250px;
	        font-weight: bold; /* Hacer el texto en negrita */
	        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	    }
	
	    .logout-button {
	        background-color: #294a61; 
	        color: white;
	    }
	
	    .mode-button {
	        background-color: #f5f5dc; 
	        border: 1px solid #294a61; 
	        color: #333;
	    }
    </style>
</head>
<body>

    <div class="top-container">
        <h2>BIENVENIDO ADMINISTRADOR</h2>
    </div>

    <div class="container">
        <div class="user-info">
            <h1 style="color: #294a61;">¡Éxito en el Login!</h1>

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
                <button class="mode-button" onclick="location.href='Estadisticas.jsp'">ESTADISTICAS</button>
                <button class="logout-button" onclick="location.href='login.html'">LOGOUT</button>
            </div>
        </div>
    </div>

</body>
</html>
