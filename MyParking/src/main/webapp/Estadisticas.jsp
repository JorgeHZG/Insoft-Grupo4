<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estadísticas de Vehículos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffe4b5;
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
            <div id="turismos" class="details">
                Estadísticas de turismos
                <h4>Datos: %{lT}/%{tT}, Porcentaje: %{pT}%, Libres: %{oT} </h4> 
            </div>
        </div>

        <div class="category" onclick="toggleDetails('motos')">
            <h3>Motos</h3>
            <div id="motos" class="details">
                <%-- estadísticas de motos  --%>
                Estadísticas de motos
            </div>
        </div>

        <div class="category" onclick="toggleDetails('electricos')">
            <h3>Vehículos Eléctricos</h3>
            <div id="electricos" class="details">
                <%-- estadísticas de vehículos eléctricos  --%>
                Estadísticas de vehículos eléctricos
            </div>
        </div>

        <div class="category" onclick="toggleDetails('familiares')">
            <h3>Familiares</h3>
            <div id="familiares" class="details">
                <%-- estadísticas de vehículos familiares --%>
                Estadísticas de vehículos familiares
            </div>
        </div>

        <div class="category" onclick="toggleDetails('minusvalidos')">
            <h3>Minusválidos</h3>
            <div id="minusvalidos" class="details">
                <%-- estadísticas de vehículos para personas con discapacidad --%>
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
