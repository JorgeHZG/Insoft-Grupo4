<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Exitoso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
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
</head>
<body>

    <div class="container">
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

</body>
</html>
