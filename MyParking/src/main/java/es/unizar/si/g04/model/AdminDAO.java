package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DecimalFormat;

import es.unizar.si.g04.db.ConnectionManager;
import es.unizar.si.g04.db.PoolConnectionManager;

public class AdminDAO {

	private static String admin_consulta = "SELECT  myparking.\"Administrador\".\"DNI\", myparking.\"Administrador\".\"Contrasegna\"  "
			+ "FROM myparking.\"Administrador\" "
			+ "WHERE myparking.\"Administrador\".\"DNI\" = ? AND myparking.\"Administrador\".\"Contrasegna\" = ?";

	private static String consultarHoraPunta = "SELECT myparking.\"Estancia\".\"Fecha_Entrada\" FROM myparking.\"Estancia\"";

	private static String consultarTiempoEstancia = "SELECT myparking.\"Estancia\".\"Fecha_Entrada\", myparking.\"Estancia\".\"Fecha_salida\" "
			+ "FROM myparking.\"Estancia\" "
			+ "WHERE myparking.\"Estancia\".\"Fecha_salida\" IS NOT NULL";

	private static String totalClientes = "SELECT COUNT (*) "
			+ "FROM myparking.\"Estancia\" "
			+ "WHERE myparking.\"Estancia\".\"Fecha_salida\" IS NOT NULL";

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

	public int obtenerHoraPunta() throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PreparedStatement statement = conn.prepareStatement(consultarHoraPunta);
			int[] ocupacionPorHoras = new int[24];
			int horaPunta = -1;

			// Ejecutamos la consulta
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) { // Mientras haya registros de entrada al parking
					// Obtenemos la fecha leída
					Timestamp fechaEntrada = rs.getTimestamp("Fecha_entrada");

					// Obtener la hora como un entero (sin minutos ni segundos)
					int horaComoEntero = fechaEntrada.getHours();

					// Incrementamos en el vector de recuento de entradas según la hora
					ocupacionPorHoras[horaComoEntero]++;
				}
				int max = 0;

				for (int i = 0; i < 24; i++) {
					if (max < ocupacionPorHoras[i]) {
						max = ocupacionPorHoras[i];
						horaPunta = i;
					}
				}

			} catch (SQLException se) {
				se.printStackTrace();

			}
			statement.close();
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarHoraPunta realizada");
			return horaPunta;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return -1;
	}

	public int obtenerHoraBaja() throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PreparedStatement statement = conn.prepareStatement(consultarHoraPunta);
			int[] ocupacionPorHoras = new int[24];
			int horaBaja = -1;

			// Ejecutamos la consulta
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) { // Mientras haya registros de entrada al parking
					// Obtenemos la fecha leída
					Timestamp fechaEntrada = rs.getTimestamp("Fecha_entrada");

					// Obtener la hora como un entero (sin minutos ni segundos)
					int horaComoEntero = fechaEntrada.getHours();

					// Incrementamos en el vector de recuento de entradas según la hora
					ocupacionPorHoras[horaComoEntero]++;
				}
				// Mayo
				int min = 2147483647;

				for (int i = 0; i < 24; i++) {
					if (min > ocupacionPorHoras[i]) {
						min = ocupacionPorHoras[i];
						horaBaja = i;
					}
				}

			} catch (SQLException se) {
				se.printStackTrace();

			}
			statement.close();
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarHoraPunta realizada");
			return horaBaja;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return -1;
	}

	public float obtenerPromedioEstancia() throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PreparedStatement statement = conn.prepareStatement(consultarTiempoEstancia);
			float totalMinutosAparcados = 0,
					totalC = 0;

			// Ejecutamos la consulta
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) { // Mientras haya registros de entrada al parking
					// Obtenemos la fecha leída
					Timestamp fechaEntrada = rs.getTimestamp("Fecha_entrada");
					Timestamp fechaSalida = rs.getTimestamp("Fecha_salida");

					// Diferencia de tiempo entre la salida y la entrada
					long diferenciaEnMillis = fechaSalida.getTime() - fechaEntrada.getTime();

					// Convertir la diferencia de milisegundos a minutos
					float diferenciaEnMinutos = (float) diferenciaEnMillis / (60 * 1000);

					// Añadir valor a la variable resultado
					totalMinutosAparcados = totalMinutosAparcados + diferenciaEnMinutos;
				}

				PreparedStatement totalRegistrados = conn.prepareStatement(totalClientes);
				try (ResultSet rs2 = totalRegistrados.executeQuery()) {
					if (rs2.next()) {
						totalC = rs2.getInt(1);
						System.out.println("Total de clientes que han entrado y salido: " + String.valueOf(totalC));
					}
				} catch (SQLException se2) {
					System.out.println("Consulta del numero total de clientes que han usado el parking fallida");
					se2.printStackTrace();
				}
				totalRegistrados.close();
			} catch (SQLException se) {
				se.printStackTrace();

			}
			statement.close();
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarTiempoEstancia realizada");
			return (totalMinutosAparcados / 60) / totalC;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return -1;
	}

	public float obtenerRendimientoEconomico() throws SQLException {
		final long PPM = 3; // precio por minutos en centimos
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PreparedStatement statement = conn.prepareStatement(consultarTiempoEstancia);
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
			System.out.println("Conexión cerrada, query consultarTiempoEstancia realizada");
			float totalEuros = ((totalMinutosAparcados * PPM) / 100); // Euros totales
			totalEuros = (float) (Math.round(totalEuros * 1000.0) / 1000.0); // Redondear a tres decimales
			return totalEuros;
		} catch (Error e) {
			System.out.println("Error en la conexión");
			return -1;
		}
	}
}
