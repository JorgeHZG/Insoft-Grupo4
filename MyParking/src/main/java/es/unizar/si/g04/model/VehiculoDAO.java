package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.unizar.si.g04.db.ConnectionManager;

public class VehiculoDAO {

    private static String registrarVehiculo = "INSERT INTO  myparking.\"Vehiculo\" VALUES (?, ?, ?)";
    private static String vehiculosCliente = "SELECT  myparking.\"Vehiculo\".\"Matricula\" FROM myparking.\"Vehiculo\" WHERE myparking.\"Vehiculo\".\"Cliente\" = ?";

    // ("insert into users set username = ?, password = ? ");

    public boolean addVehicle(String mat, String user, String tipo) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(registrarVehiculo);
            statement.setString(1, mat);
            // System.out.println("Leido: " + dni);
            statement.setString(2, user);
            // System.out.println("Leido: " + password);
            statement.setString(3, tipo);
            // System.out.println("Leido: " + nombre);

            statement.execute();

            ConnectionManager.releaseConnection(conn);
            System.out.println("Conexión cerrada, addVehiculo realizado");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<String> obtenerVehiculosUsuario(String usuario) {
        List<String> vehiculos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            // Abrimos conexion

            try (PreparedStatement statement = conn.prepareStatement(vehiculosCliente)) {
                statement.setString(1, usuario);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        vehiculos.add(resultSet.getString("Matricula"));
                    }
                }
                statement.close();
            }
            ConnectionManager.releaseConnection(conn);
            System.out.println("Conexión cerrada, query vehiculosCliente realizada");
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return vehiculos;
    }

}
