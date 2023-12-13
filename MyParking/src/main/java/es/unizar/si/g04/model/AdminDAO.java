package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.si.g04.db.ConnectionManager;
import es.unizar.si.g04.db.PoolConnectionManager;

public class AdminDAO {

	private static String admin_consulta = "SELECT  myparking.\"Administrador\".\"DNI\", myparking.\"Administrador\".\"Contrasegna\"  "
			+ "FROM myparking.\"Administrador\" "
			+ "WHERE myparking.\"Administrador\".\"DNI\" = ? AND myparking.\"Administrador\".\"Contrasegna\" = ?";

	public AdministradorVO verificarCredenciales(String dni, String password) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			AdministradorVO admin = new AdministradorVO(null, null);
			try (PreparedStatement statement = conn.prepareStatement(admin_consulta)) { 
				// Asignamos prarametros
				statement.setString(1, dni);
				System.out.println("Leido: " + dni);
				statement.setString(2, password);
				System.out.println("Leido: " + password);

				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						admin.setDni(rs.getString("DNI"));
						// System.out.println(cliente.getDni());
					} else {
						System.out.println("Consulta fallida");
						return admin;
					}
				} catch (SQLException se) {
					se.printStackTrace();

				}
				statement.close(); // Preguntar
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query verificarCredenciales realizada");
			return admin;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}
}


