<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva de Plaza</title>
    <style>
        /* Estilos para centrar el formulario */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #ffe4b5; /* Fondo azul claro */
            transition: background-color 0.5s; /* Transición suave del color de fondo */
            color: #001f3f; /* Color de texto predeterminado */
        }

        /* Estilos para el formulario */
        form {
            text-align: center;
            background-color: #f2f2f2;
            border-radius: 25px;
            padding: 50px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            transition: background-color 0.5s; /* Transición suave del color de fondo */
        }

        /* Estilos para el título */
        h1 {
            color: inherit; /* Hereda el color de texto del elemento padre (body) */
            border-bottom: 2px solid #001f3f; /* Línea inferior azul para el título */
            padding-bottom: 10px; /* Espaciado inferior para el título */
            margin-bottom: 20px; /* Margen inferior para el título */
        }

        /* Estilos para las etiquetas y campos de entrada */
        label {
            display: block;
            margin: 10px 0;
        }

        select, input {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Estilos para el botón */
        input[type="submit"], input[type="button"] {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 10px; /* Bordes redondeados */
            padding: 8px 15px; /* Tamaño reducido */
            cursor: pointer;
            margin: 10px; /* Espaciado entre botones */
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #0056b3;
        }

        /* Estilos para el mensaje de reserva */
        .reservation-message {
            margin-top: 20px;
            font-size: 16px;
            color: #007BFF; /* Azul del enlace */
            text-align: center;
        }

        /* Estilos para el botón de modo nocturno */
        .mode-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #001f3f;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
        }

        /* Estilos para el modo nocturno */
        .night-mode form {
            background-color: #001f3f;
            color: white;
        }
        
                /* Estilos para el mensaje de error */
        .errorMessage {
            color: red; 
            font-size: 16px;
            margin-top: 10px;
        }
        
        .night-mode h1 {
            border-bottom: 2px solid rgb(255, 255, 255); /* Línea inferior blanca para el título */
        }

        .night-mode input, .night-mode select {
            border: 1px solid #fff;
        }

        .night-mode .mode-button {
            background-color: #ccc;
        }
    </style>
</head>
<body>
	
    <form action="ReservaPlazaServlet" method="post">
    	<h1>Reservar plaza</h1>
	    <label for="matricula">Matricula:</label>
	    <select name="matricula" id="matricula">
	        <%
	            String[] vector_vehiculos = (String[]) request.getAttribute("vector_vehiculos");
	            if (vector_vehiculos != null) {
	                for (int i = 0; i < vector_vehiculos.length; i++) {
	        %>
	                    <option value="<%= vector_vehiculos[i] %>"><%= vector_vehiculos[i] %></option>
	        <%
	                }
	            }
	        %>
	    </select>
	    <br>
	
	    <c:if test="${not empty errorMessage}">
	        <div class="errorMessage">${errorMessage}</div>
	    </c:if>
	
	    <p class="reservation-message">Dispone de una hora de reserva desde el momento que pulse aceptar.</p>
	
	    <input type="submit" value="Continuar">
	    <input type="button" value="Cancelar" onclick="window.history.back();">
	</form>


</body>
</html>