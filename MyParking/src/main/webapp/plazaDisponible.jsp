<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plaza disponible</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffe4b5;
            color: #333;
            margin: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .info-container {
            border: 2px solid #4285f4;
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
            display: inline-block;
        }

        .info-label {
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>

    <!-- Redirección después de 5 segundos -->
    <meta http-equiv="refresh" content="5;url=loginCorrecto.jsp">
</head>
<body>

    <div class="container">
        <h1 style="color: #4285f4;">¡Plaza libre encontrada!</h1>

        <div class="info-container">
            <span class="info-label">Numero:</span>
            <p>${numero}</p>
        </div>
    </div>

</body>
</html>
