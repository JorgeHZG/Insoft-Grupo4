package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import es.unizar.si.g04.db.PoolConnectionManager;

public class PlazaFacade {

    private static String encontrarPlazasLibres = "SELECT numero FROM myParking.\"Plaza\" WHERE Estado = true";

    public List<Integer> plazasLibres() {

        List<Integer> plazasLibres = new ArrayList<>();
        Connection conn = null;

        try {
            // Abrimos la conexión e inicializamos los parámetros
            conn = PoolConnectionManager.getConnection();

            PreparedStatement countPlazas = conn.prepareStatement(encontrarPlazasLibres);
            ResultSet resultado = countPlazas.executeQuery();
            // Leemos cuántos registros tenemos
            while (resultado.next()) {
                int id = resultado.getInt("numero");
                plazasLibres.add(id);
            }

            countPlazas.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            PoolConnectionManager.releaseConnection(conn);
        }
        return plazasLibres;
    }
}
