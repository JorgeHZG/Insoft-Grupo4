package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import es.unizar.si.g04.db.ConnectionManager;

public class VehiculoDAO {

    private static String registrarVehiculo = "INSERT INTO  myparking.\"Vehiculo\" VALUES (?, ?, ?)";

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

}
