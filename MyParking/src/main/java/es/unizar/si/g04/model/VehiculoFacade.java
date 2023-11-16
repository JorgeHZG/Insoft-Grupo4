package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.si.g04.db.PoolConnectionManager;

public class VehiculoFacade {

    private static String tipoVehiculo = "SELECT tipo FROM myParking.\"Vehiculo\" WHERE matricula = ?";

    public String tipoVehiculoSegunMatricula(String matricula) {

        String tipo = "";
        Connection conn = null;

        try {
            // Abrimos la conexión e inicializamos los parámetros
            conn = PoolConnectionManager.getConnection();
            PreparedStatement tipoV = conn.prepareStatement(tipoVehiculo);
            tipoV.setString(1, matricula);

            ResultSet resultado = tipoV.executeQuery();
            // Leemos cuántos registros tenemos
            if (resultado.next()) {
                tipo = resultado.getString("tipo");
            }

            tipoV.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            PoolConnectionManager.releaseConnection(conn);
        }
        return tipo;
    }
}
