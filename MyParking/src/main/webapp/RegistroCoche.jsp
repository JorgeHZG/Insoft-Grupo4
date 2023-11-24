<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Registrar Coche</title>
    <style>
        /* Estilos para centrar el formulario */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #edf2f7; /* Fondo azul claro */
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
        select {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Estilos para el botón */
        input[type="submit"] {
            background-color: rgb(0, 128, 255); /* Verde bonito */
            color: white;
            border: none;
            border-radius: 20px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838; /* Verde más oscuro al pasar el ratón */
        }
    </style>
</head>

<body>
    <form action="CocheServlet" method="post">
        <h1>Registrar Coche</h1>

        <label for="Matricula">Matrícula:</label>
        <input type="text" id="Matricula" name="Matricula" required>

        <label for="TipoVehiculo">Tipo de Vehículo:</label>
        <select id="TipoVehiculo" name="TipoVehiculo" required>
            <option value="Turismo">Turismo</option>
            <option value="Moto">Moto</option>
            <option value="Familiar">Familiar</option>
            <option value="VehiculosElectricos">Vehículos Eléctricos</option>
            <option value="Minusvalidos">Minusválidos</option>
        </select>

        <input type="submit" value="Registrar">
    </form>
</body>

</html>