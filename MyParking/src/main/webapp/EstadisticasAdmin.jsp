<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estadísticas de Vehículos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #5f9ea0;
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

        <div class="category">
            <h3>Turismos</h3>

               <p>Datos: ${lT}/${tT}, Porcentaje: ${pT}%, Libres: ${oT} </p>

        </div>

        <div class="category">
            <h3>Motos</h3>
                <p>Datos: ${lM}/${tM}, Porcentaje: ${pM}%, Libres: ${oM}</p>             
        </div>

        <div class="category" onmouseover="toggleDetails('electricos')">
            <h3>Vehículos Eléctricos</h3>
                <p>Datos: ${lE}/${tE}, Porcentaje: ${pE}%, Libres: ${oE}</p>
        </div>

        <div class="category">
            <h3>Minusválidos</h3>
                <p>Datos: ${lMin}/${tMin}, Porcentaje: ${pMin}%, Libres: ${oMin}
        </div>

        <div class="category">
            <h3>Familiares </h3>
            <p>Datos: ${lF}/${tF}, Porcentaje: ${pF}%, Libres: ${oF}</p>

        </div>
    </div>

    <script>
        function toggleDetails(category) {
            var detailsElement = document.getElementById(category);
            var allDetails = document.querySelectorAll('.details');
            for (var i = 0; i < allDetails.length; i++) {
                allDetails[i].style.display = 'none';
            }
            detailsElement.style.display = 'block';
        }
    </script>

</body>
</html>