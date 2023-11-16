/*
package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.si.g04.db.PoolConnectionManager;

public class AdminDAO {

	private static String getDNI = "SELECT DNI FROM myparking.\"Aministrador\" WHERE DNI = ? AND Contrasegna = ?";

	public AdministradorVO verificarCredenciales(String dni, String password) throws SQLException {
		try (Connection conn = PoolConnectionManager.getConnection()) {
			// Abrimos conexión
			AdministradorVO admin = new AdministradorVO("?", "?");
			try (PreparedStatement statement = conn.prepareStatement(verificarCredenciales(dni, password))) {
				// Asignamos prarametros
				statement.setString(1, dni);
				statement.setString(2, password);

				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						admin.setDni(rs.getString("DNI"));

					}
				} catch (SQLException se) {
					se.printStackTrace();
				}
				statement.close(); // PORQUE?
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			// Cerramos conexion y devolvemos el dato
			PoolConnectionManager.releaseConnection(conn);
			return admin;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

}
*/