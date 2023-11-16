package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.si.g04.db.ConnectionManager;
import es.unizar.si.g04.db.PoolConnectionManager;

public class ClienteDAO {

	private static String verificarCredenciales = "SELECT  myparking.\"Cliente\".\"DNI\", "
			+ "myparking.\"Cliente\".\"Nombre\",  "
			+ "myparking.\"Cliente\".\"Apellido\" "
			+ "FROM myparking.\"Cliente\" "
			+ "WHERE myparking.\"Cliente\".\"DNI\" = ? AND myparking.\"Cliente\".\"Contrasegna\" = ?";

	public ClienteVO verificarCredenciales(String dni, String password) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			ClienteVO cliente = new ClienteVO(null, null, null, null);
			try (PreparedStatement statement = conn.prepareStatement(verificarCredenciales)) {
				// Asignamos prarametros
				statement.setString(1, dni);
				System.out.println("Leido: " + dni);
				statement.setString(2, password);
				System.out.println("Leido: " + password);

				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						cliente.setDni(rs.getString("DNI"));
						System.out.println(cliente.getDni());
						cliente.setNombre(rs.getString("Nombre"));
						System.out.println(cliente.getNombre());
						cliente.setApellido(rs.getString("Apellido"));
						System.out.println(cliente.getApellido());

					}else {
						System.out.println("Consulta fallida");
						 System.out.println("Cliente despues de consulta fallida: " + cliente.getDni() + ", " + cliente.getPassword()  + ", " + cliente.getNombre() + ", " + cliente.getApellido());
						return cliente;
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
			System.out.println("Conexión cerrada, query clienteDao realizada");
			return cliente;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

}
