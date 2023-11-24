<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estadísticas de Vehículos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            animation: fadeIn 0.5s ease-in-out;
        }

        .category {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        .category:hover {
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
        }

        h3 {
            margin-top: 0;
            color: #007bff;
            text-align: center;
        }

        .details {
            display: none;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Estadísticas de Vehículos</h2>

        <div class="category" onclick="toggleDetails('turismos')">
            <h3>Turismos</h3>
            <div id="turismosDetails" class="details">
                <%-- Aquí puedes incluir el código para obtener y mostrar las estadísticas de turismos desde la base de datos --%>
                Estadísticas de turismos
            </div>
        </div>

        <div class="category" onclick="toggleDetails('motos')">
            <h3>Motos</h3>
            <div id="motosDetails" class="details">
                <%-- Aquí puedes incluir el código para obtener y mostrar las estadísticas de motos desde la base de datos --%>
                Estadísticas de motos
            </div>
        </div>

        <div class="category" onclick="toggleDetails('electricos')">
            <h3>Vehículos Eléctricos</h3>
            <div id="electricosDetails" class="details">
                <%-- Aquí puedes incluir el código para obtener y mostrar las estadísticas de vehículos eléctricos desde la base de datos --%>
                Estadísticas de vehículos eléctricos
            </div>
        </div>

        <div class="category" onclick="toggleDetails('familiares')">
            <h3>Familiares</h3>
            <div id="familiaresDetails" class="details">
                <%-- Aquí puedes incluir el código para obtener y mostrar las estadísticas de vehículos familiares desde la base de datos --%>
                Estadísticas de vehículos familiares
            </div>
        </div>

        <div class="category" onclick="toggleDetails('minusvalidos')">
            <h3>Minusválidos</h3>
            <div id="minusvalidosDetails" class="details">
                <%-- Aquí puedes incluir el código para obtener y mostrar las estadísticas de vehículos para personas con discapacidad desde la base de datos --%>
                Estadísticas de vehículos para personas con discapacidad
            </div>
        </div>
    </div>

    <script>
        function toggleDetails(category) {
            var detailsElement = document.getElementById(category + 'Details');
            var allDetails = document.querySelectorAll('.details');
            for (var i = 0; i < allDetails.length; i++) {
                allDetails[i].style.display = 'none';
            }
            detailsElement.style.display = 'block';
        }
    </script>

</body>
</html>
