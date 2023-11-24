package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.si.g04.db.ConnectionManager;
import es.unizar.si.g04.db.PoolConnectionManager;

public class PlazaDAO {

	private static String consultarEstadoPlaza = "SELECT  myparking.\"Plaza\".\"Numero\", "
			+ "myparking.\"Plaza\".\"estado\", "
			+ "myparking.\"Plaza\".\"tipo\", "
			+ "FROM myparking.\"Plaza\" "
			+ "WHERE myparking.\"Plaza\".\"estado\" = false AND myparking.\"Plaza\".\"tipo\" = ?";

	private static String anadirPlaza = "INSERT INTO  myparking.\"Plaza\" VALUES (?, ?, ?)";

	public boolean addPlaza(int numero, boolean estado, String tipo) {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(anadirPlaza);
			statement.setInt(1, numero);
			statement.setBoolean(2, estado);
			statement.setString(3, tipo);

			statement.execute();

			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, addPlaza realizado");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public PlazaVO consultarPlazaLibre(String tipo) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PlazaVO plaza = new PlazaVO(-1, false, tipo);
			try (PreparedStatement statement = conn.prepareStatement(consultarEstadoPlaza)) {
				// Asignamos prarametros
				statement.setString(1, tipo);

				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						plaza.setEstadoPlaza(true);
						plaza.setNumeroPlaza(rs.getInt("Numero"));
						System.out.println(plaza.getNumeroPlaza());

					} else {
						System.out.println("Consulta fallida");
						return plaza; // Si no ha habido exito, se devuelve una plaza con numero "-1"
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
			System.out.println("Conexión cerrada, query consultarPlazaLibre realizada");
			return plaza;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

}
