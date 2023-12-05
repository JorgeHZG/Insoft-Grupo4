package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import es.unizar.si.g04.db.ConnectionManager;

public class PlazaDAO {

	private static String consultarEstadoPlaza = "SELECT  myparking.\"Plaza\".\"Numero\", "
			+ "myparking.\"Plaza\".\"estado\", "
			+ "myparking.\"Plaza\".\"tipo\" "
			+ "FROM myparking.\"Plaza\" "
			+ "WHERE myparking.\"Plaza\".\"estado\" = false AND myparking.\"Plaza\".\"tipo\" = ?";

	private static String modificarPlazaOcupada = "UPDATE myparking.\"Plaza\" SET \"estado\" = true WHERE \"Numero\" = ?";

	private static String reservaP = "INSERT INTO myparking.\"Estancia\" (\"Fecha_Entrada\", \"Fecha_reserva\", \"Vehiculo\", \"Num_plaza\")"
			+ " VALUES(?, ?, ?, ?) ";

	private static String registrarAparcar = "INSERT INTO myparking.\"Estancia\" (\"Fecha_Entrada\", \"Vehiculo\", \"Num_plaza\")"
			+ " VALUES(?, ?, ?) ";

	private static String registrarSalida = "UPDATE myparking.\"Estancia\" SET \"Fecha_salida\" = ? WHERE \"Vehiculo\" = ? AND \"Num_plaza\" = ?";

	private static String consultarPlazaOcupada = "SELECT  myparking.\"Estancia\".\"Num_plaza\" "
			+ "FROM myparking.\"Estancia\" "
			+ "WHERE myparking.\"Estancia\".\"Vehiculo\" = ?";

	private static String modificarPlazaNOOcupada = "UPDATE myparking.\"Plaza\" SET \"estado\" = false WHERE \"Numero\" = ?";

	// private static String anadirPlaza = "INSERT INTO myparking.\"Plaza\" VALUES
	// (?, ?, ?)";

	public PlazaVO Reservar(String tipo, String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PlazaVO plaza = new PlazaVO(-1, false, tipo);

			Date date = new Date(); // Obtener la fecha y hora actual
			Timestamp horaReserva = new Timestamp(date.getTime()); // Crear un objeto Timestamp

			try (PreparedStatement statement = conn.prepareStatement(consultarEstadoPlaza)) {
				// Asignamos prarametros
				statement.setString(1, tipo);
				System.out.println(statement.toString());
				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						int num = rs.getInt("Numero");
						plaza.setEstadoPlaza(true);
						plaza.setNumeroPlaza(rs.getInt("Numero"));

						PreparedStatement statement2 = conn.prepareStatement(modificarPlazaOcupada);
						statement2.setInt(1, num);
						System.out.println(statement2.toString());
						statement2.executeUpdate(); // Se ejecuta el update
						statement2.close();

						PreparedStatement escribeReserva = conn.prepareStatement(reservaP);
						escribeReserva.setTimestamp(1, horaReserva);
						escribeReserva.setTimestamp(2, horaReserva);
						escribeReserva.setString(3, matricula);
						escribeReserva.setInt(4, num);

						System.out.println(escribeReserva.toString());
						try (ResultSet rs2 = escribeReserva.executeQuery()) {
							System.out.println("Reserva añadida a la tabla estancia");
							escribeReserva.close();
						} catch (SQLException se2) {
							System.out.println("Fallo anadir dato tabla estancia");
							se2.printStackTrace();
						}

					} else {
						System.out.println("No se ha encontrado una plaza libre para el tipo de vehiculo seleccionado");
						return plaza; // Si no ha habido exito, se devuelve una plaza con numero "-1"
					}
				} catch (SQLException se) {
					System.out.println("Consulta fallida");
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

	public PlazaVO Aparcar(String tipo, String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			PlazaVO plaza = new PlazaVO(-1, false, tipo);

			Date date = new Date(); // Obtener la fecha y hora actual
			Timestamp horaEntrada = new Timestamp(date.getTime()); // Crear un objeto Timestamp

			try (PreparedStatement statement = conn.prepareStatement(consultarEstadoPlaza)) {
				// Asignamos prarametros
				statement.setString(1, tipo);
				System.out.println(statement.toString());
				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						int num = rs.getInt("Numero");
						plaza.setEstadoPlaza(true);
						plaza.setNumeroPlaza(rs.getInt("Numero"));

						PreparedStatement statement2 = conn.prepareStatement(modificarPlazaOcupada);
						statement2.setInt(1, num);
						System.out.println(statement2.toString());
						statement2.executeUpdate(); // Se ejecuta el update
						statement2.close();

						PreparedStatement escribeAparcar = conn.prepareStatement(registrarAparcar);
						escribeAparcar.setTimestamp(1, horaEntrada);
						escribeAparcar.setString(2, matricula);
						escribeAparcar.setInt(3, num);

						System.out.println(escribeAparcar.toString());
						escribeAparcar.executeUpdate(); //
						escribeAparcar.close();
					}

					else {
						System.out.println("No se ha encontrado una plaza libre para el tipo de vehiculo seleccionado");
						return plaza; // Si no ha habido exito, se devuelve una plaza con numero "-1"
					}
				} catch (SQLException se) {
					System.out.println("Consulta fallida");
					se.printStackTrace();

				}
				statement.close(); // Preguntar
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarEstadoPlaza realizada");
			return plaza;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

	public boolean SalirParking(String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			try (PreparedStatement statement = conn.prepareStatement(consultarPlazaOcupada)) {
				// Asignamos prarametros
				statement.setString(1, matricula);
				System.out.println(statement.toString());
				// Ejecutamos la consulta
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						// num = numero de plaza en la que estaba aparcado el vehiculo con matricula
						// "matricula"
						int num = rs.getInt("Num_plaza");
						System.out.printf("El vehículo está aparcado en la plaza: %d\n", num);

						PreparedStatement statement2 = conn.prepareStatement(modificarPlazaNOOcupada);
						statement2.setInt(1, num);
						System.out.println(statement2.toString());
						statement2.executeUpdate(); // Se ejecuta el update
						statement2.close();

						Date date = new Date(); // Obtener la fecha y hora actual
						Timestamp horaSalida = new Timestamp(date.getTime()); // Crear un objeto Timestamp

						PreparedStatement salida = conn.prepareStatement(registrarSalida);

						salida.setTimestamp(1, horaSalida);
						salida.setString(2, matricula);
						salida.setInt(3, num);
						System.out.println(salida.toString());

						try (ResultSet rs2 = salida.executeQuery()) {
							System.out.println("Salida registrada correctamente");
							salida.close();
						} catch (SQLException se2) {
							System.out.println("Fallo anadir salida en la tabla estancia");
							se2.printStackTrace();
						}

					} else {
						System.out.println("El vehículo no está aparcado actualmente en el parking");
						return false; // Si no ha habido exito, se devuelve una plaza con numero "-1"
					}
				} catch (SQLException se) {
					System.out.println("Consulta fallida");
					se.printStackTrace();
				}
				statement.close(); // Preguntar
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarPlazaOcupada realizada");
			return true;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return false;
	}
}
