package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import jakarta.servlet.http.HttpSession;

import es.unizar.si.g04.db.ConnectionManager;
import es.unizar.si.g04.db.PoolConnectionManager;

public class ClienteDAO {

	private static String verificarCredenciales = "SELECT  myparking.\"Cliente\".\"DNI\", "
			+ "myparking.\"Cliente\".\"Nombre\",  "
			+ "myparking.\"Cliente\".\"Apellido\" "
			+ "FROM myparking.\"Cliente\" "
			+ "WHERE myparking.\"Cliente\".\"DNI\" = ? AND myparking.\"Cliente\".\"Contrasegna\" = ?";

	private static String registrarUsuario = "INSERT INTO  myparking.\"Cliente\" VALUES (?, ?, ?, ?)";

	private static String tiempoReservaActual = "SELECT myparking.\"Estancia\".\"Fecha_Entrada\", myparking.\"Estancia\".\"Fecha_salida\" "
			+ "FROM myparking.\"Estancia\" "
			+ "INNER JOIN myparking.\"Vehiculo\" ON myparking.\"Estancia\".\"Vehiculo\" = myparking.\"Vehiculo\".\"Matricula\" "
			+ "INNER JOIN myparking.\"Cliente\" ON myparking.\"Vehiculo\".\"Cliente\" = myparking.\"Cliente\".\"DNI\" "
			+ "WHERE myparking.\"Cliente\".\"DNI\" = ? AND myparking.\"Estancia\".\"Fecha_salida\" IS NOT NULL;";

	// ("insert into users set username = ?, password = ? ");

	public boolean addUser(String dni, String password, String nombre, String apellido) {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(registrarUsuario);
			statement.setString(1, dni);
			// System.out.println("Leido: " + dni);
			statement.setString(2, password);
			// System.out.println("Leido: " + password);
			statement.setString(3, nombre);
			// System.out.println("Leido: " + nombre);
			statement.setString(4, apellido);
			// System.out.println("Leido: " + apellido);

			statement.execute();

			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, addUser realizado");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

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
						// System.out.println(cliente.getDni());
						cliente.setNombre(rs.getString("Nombre"));
						// System.out.println(cliente.getNombre());
						cliente.setApellido(rs.getString("Apellido"));
						// System.out.println(cliente.getApellido());

					} else {
						System.out.println("Consulta fallida");
						// System.out.println("Cliente despues de consulta fallida: " + cliente.getDni()
						// + ", " + cliente.getPassword() + ", " + cliente.getNombre() + ", " +
						// cliente.getApellido());
						return cliente;
					}
				} catch (SQLException se) {
					se.printStackTrace();

				}
				statement.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query verificarCredenciales realizada");
			return cliente;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

	public int obtenerHistoricoEstancias(String DNI) throws SQLException {

		// Verifica si la sesión existe y contiene el atributo "dni"
		if (DNI != null) {
			// Obtiene el DNI del usuario actual

			try (Connection conn = ConnectionManager.getConnection()) {
				// Abrimos conexión
				PreparedStatement statement = conn.prepareStatement(tiempoReservaActual);
				statement.setString(1, DNI);

				float totalMinutosAparcados = 0;
				// Ejecutamos la consulta
				System.out.println(statement);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						Timestamp fechaEntrada = rs.getTimestamp("Fecha_entrada");
						Timestamp fechaSalida = rs.getTimestamp("Fecha_salida");
						System.out.println("fecha entrada: " + String.valueOf(fechaEntrada));
						System.out.println("fecha salida: " + String.valueOf(fechaSalida));

						// Diferencia de tiempo entre la salida y la entrada
						long diferenciaEnMillis = fechaSalida.getTime() - fechaEntrada.getTime();
						System.out.println(String.valueOf(diferenciaEnMillis));

						// Convertir la diferencia de milisegundos a minutos
						float diferenciaEnMinutos = (float) diferenciaEnMillis / (60 * 1000);
						System.out.println(String.valueOf(diferenciaEnMinutos));

						// Añadir valor a la variable resultado
						totalMinutosAparcados += diferenciaEnMinutos;
						System.out.println(String.valueOf(totalMinutosAparcados));
					}
					System.out.format("total minutos aparcados: " + String.valueOf(totalMinutosAparcados));
				} catch (SQLException se) {
					se.printStackTrace();
				}
				statement.close();
				// Cerramos conexion y devolvemos el dato
				ConnectionManager.releaseConnection(conn);
				System.out.println("Conexión cerrada, query tiempoReservaActual realizada");
				return (int) totalMinutosAparcados;
			} catch (Error e) {
				System.out.println("Error en la conexión");
				return -1;
			}

		} else {
			// La sesión no existe o no contiene el atributo "dni"
			System.out.println("No se ha encontrado información de DNI en la sesión.");
			return -1;
		}

	}
}
