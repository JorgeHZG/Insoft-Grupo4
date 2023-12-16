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

	private static String registrarSalida = "UPDATE myparking.\"Estancia\" SET \"Fecha_salida\" = ? WHERE \"Vehiculo\" = ? AND \"Num_plaza\" = ? AND \"Fecha_salida\" IS NULL";

	private static String consultarPlazaOcupada = "SELECT  myparking.\"Estancia\".\"Num_plaza\" "
			+ "FROM myparking.\"Estancia\" "
			+ "WHERE myparking.\"Estancia\".\"Vehiculo\" = ? AND myparking.\"Estancia\".\"Fecha_salida\" IS NULL ";

	private static String modificarPlazaNOOcupada = "UPDATE myparking.\"Plaza\" SET \"estado\" = false WHERE \"Numero\" = ?";

	private static String numPlazas = "SELECT COUNT(*) FROM myparking.\"Plaza\" WHERE myparking.\"Plaza\".\"tipo\" = ?;";

	private static String numOcupa = "SELECT COUNT(*) FROM myparking.\"Plaza\" WHERE myparking.\"Plaza\".\"estado\" = true AND myparking.\"Plaza\".\"tipo\" = ?";

	private static String consultarTipoDeCoche = "SELECT myparking.\"Vehiculo\".\"Tipo\" FROM myparking.\"Vehiculo\" WHERE myparking.\"Vehiculo\".\"Matricula\" = ?;";

	private static String existeCoche = "SELECT COUNT(*) FROM myparking.\"Estancia\" WHERE myparking.\"Estancia\".\"Vehiculo\" = ?";

	private static String consultarSalidaCoche = "SELECT myparking.\"Estancia\".\"Fecha_salida\" FROM myparking.\"Estancia\" WHERE myparking.\"Estancia\".\"Vehiculo\"  = ? ORDER BY myparking.\"Estancia\".\"Fecha_Entrada\" DESC LIMIT 1;";

	public PlazaVO Reservar(String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión

			PlazaVO plaza = new PlazaVO(-1, false, "Turismo");
			String tipo = "Turismo";

			Date date = new Date(); // Obtener la fecha y hora actual
			Timestamp horaEntrada = new Timestamp(date.getTime()); // Crear un objeto Timestamp

			int exCo; // Numero de veces que el vehiculo con matricula "matricula" ha aparcado en el
						// parking

			PreparedStatement PSexisteCoche = conn.prepareStatement(existeCoche);
			PSexisteCoche.setString(1, matricula);
			System.out.println(PSexisteCoche.toString());

			try (ResultSet rsExisteCoche = PSexisteCoche.executeQuery()) {
				if (rsExisteCoche.next()) {
					exCo = rsExisteCoche.getInt(1);

					System.out.println(PSexisteCoche.toString());
					System.out.println("Existe coche = " + String.valueOf(exCo));

					if (exCo > 0) { // No es la primera vez que aparca en el parking

						// Preparamos consulta que nos devuelve la última hora de salida del coche con
						// matricula "matricula"
						PreparedStatement statementHoraSalidaCoche = conn.prepareStatement(consultarSalidaCoche);
						// Asignamos prarametros
						statementHoraSalidaCoche.setString(1, matricula);
						System.out.println(statementHoraSalidaCoche.toString());

						// Ejecutamos la consulta
						// Se comprueba si el coche con matrícula "matricula" tiene asociada una hora de
						// salida a la última vez que entró al parking
						try (ResultSet rs = statementHoraSalidaCoche.executeQuery()) {
							// Si la hora de salida no es nula
							// El coche no está en el parking
							if (rs.next()) {
								Timestamp salida = rs.getTimestamp("Fecha_salida"); // Nos guardamos la fecha de salida

								if (salida != null) { // Se registró la última salida del vehículo del parking

									// Buscamos en la base de datos de que tipo es el vehiculo que quiere aparcar
									PreparedStatement statementTipoDeCoche = conn
											.prepareStatement(consultarTipoDeCoche);
									// Asignamos la matricula del coche a la consulta
									statementTipoDeCoche.setString(1, matricula);
									// Imprimimos la consulta
									System.out.println(statementTipoDeCoche.toString());

									try (ResultSet rsTipo = statementTipoDeCoche.executeQuery()) {
										// Si tenemos resultado
										if (rsTipo.next()) {
											// Tipo del vehiculo con matricula "matricula"
											tipo = rsTipo.getString("Tipo");

											PreparedStatement statementNumPlaza = conn
													.prepareStatement(consultarEstadoPlaza);
											// Asignamos prarametros
											statementNumPlaza.setString(1, tipo);
											System.out.println(statementNumPlaza.toString());

											// Ejecutamos la consulta para buscar una plaza libre para el vehiculo de
											// ese tipo
											try (ResultSet rs2 = statementNumPlaza.executeQuery()) {
												if (rs2.next()) {

													System.out.println("Plaza libre encontrada");

													// "num" es el numero de plaza libre encontrada
													int num = rs2.getInt("Numero");
													plaza.setNumeroPlaza(num);
													plaza.setEstadoPlaza(true);

													// Establecemos esa plaza como ocupada
													PreparedStatement statement3 = conn
															.prepareStatement(modificarPlazaOcupada);
													statement3.setInt(1, num);
													System.out.println(statement3.toString());
													statement3.executeUpdate(); // Se ejecuta el update
													statement3.close();

													// Se registra que el coche con matricula "matricula" ha aparcado en
													// la plaza de numero "num" a la hora actual
													PreparedStatement escribeAparcar = conn.prepareStatement(reservaP);
													escribeAparcar.setTimestamp(1, horaEntrada);
													escribeAparcar.setTimestamp(2, horaEntrada);
													escribeAparcar.setString(3, matricula);
													escribeAparcar.setInt(4, num);

													System.out.println(escribeAparcar.toString());
													escribeAparcar.executeUpdate(); // Se ejecuta el update
													escribeAparcar.close();

												}
											} catch (Exception e5) {
												System.out.println(
														"No existe plaza libre para vehiculo de tipo: " + tipo);
												return plaza;
											}
											statementNumPlaza.close();
										}
									} catch (Exception e4) {
										e4.printStackTrace();
										System.out.println("Fallo consultar tipo de coche, pero ya ha aparcado");
									}
									statementTipoDeCoche.close();
									return plaza;
								} else { // El vehículo aún está en el parking
									System.out.println("El vehículo aun está en el parking");
									plaza.setNumeroPlaza(-2);
									return plaza;
								}

							}
						} catch (Exception e5) {
							e5.printStackTrace();
						}
						statementHoraSalidaCoche.close();
					} else { // Es la primera vez que el vehiculo aparca

						// Buscamos en la base de datos de que tipo es el vehiculo que quiere aparcar
						PreparedStatement statementTipoDeCoche = conn.prepareStatement(consultarTipoDeCoche);
						// Asignamos la matricula del coche a la consulta
						statementTipoDeCoche.setString(1, matricula);
						// Imprimimos la consulta
						System.out.println(statementTipoDeCoche.toString());

						// Ejecutamos la consulta
						try (ResultSet rsTipo = statementTipoDeCoche.executeQuery()) {
							// Si tenemos resultado
							if (rsTipo.next()) {
								// Tipo del vehiculo con matricula "matricula"
								tipo = rsTipo.getString("Tipo");

								PreparedStatement statementNumPlaza = conn.prepareStatement(consultarEstadoPlaza);
								// Asignamos prarametros
								statementNumPlaza.setString(1, tipo);
								System.out.println(statementNumPlaza.toString());

								// Ejecutamos la consulta para buscar una plaza libre para el vehiculo de ese
								// tipo
								try (ResultSet rs2 = statementNumPlaza.executeQuery()) {
									if (rs2.next()) {
										System.out.println("Plaza libre encontrada");

										// "num" es el numero de plaza libre encontrada
										int num = rs2.getInt("Numero");
										plaza.setNumeroPlaza(num);
										plaza.setEstadoPlaza(true);

										// Establecemos esa plaza como ocupada
										PreparedStatement statement3 = conn.prepareStatement(modificarPlazaOcupada);
										statement3.setInt(1, num);
										System.out.println(statement3.toString());
										statement3.executeUpdate(); // Se ejecuta el update
										statement3.close();

										// Se registra que el coche con matricula "matricula" ha aparcado en la plaza de
										// numero "num" a la hora actual
										PreparedStatement escribeAparcar = conn.prepareStatement(reservaP);
										escribeAparcar.setTimestamp(1, horaEntrada);
										escribeAparcar.setTimestamp(2, horaEntrada);
										escribeAparcar.setString(3, matricula);
										escribeAparcar.setInt(4, num);

										System.out.println(escribeAparcar.toString());
										escribeAparcar.executeUpdate(); // Se ejecuta el update
										escribeAparcar.close();

									}
								} catch (Error e2) {
									System.out.println("No existe plaza libre para vehiculo de tipo: " + tipo);
									return plaza;
								}
								statementNumPlaza.close();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
							System.out.println("Fallo consultar tipo de coche, siendo la primera vez que aparca");
						}
						statementTipoDeCoche.close();
						return plaza;
					}
				}
			} catch (Exception e) {
				System.out
						.println("Consulta para comprobar si el coche ha aparcado anteriormente en el parking fallida");
				e.printStackTrace();
			}
			PSexisteCoche.close();
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

	public PlazaVO Aparcar(String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión

			PlazaVO plaza = new PlazaVO(-1, false, "Turismo");
			String tipo = "Turismo";

			Date date = new Date(); // Obtener la fecha y hora actual
			Timestamp horaEntrada = new Timestamp(date.getTime()); // Crear un objeto Timestamp

			int exCo; // Numero de veces que el vehiculo con matricula "matricula" ha aparcado en el
						// parking

			PreparedStatement PSexisteCoche = conn.prepareStatement(existeCoche);
			PSexisteCoche.setString(1, matricula);
			System.out.println(PSexisteCoche.toString());

			try (ResultSet rsExisteCoche = PSexisteCoche.executeQuery()) {
				if (rsExisteCoche.next()) {
					exCo = rsExisteCoche.getInt(1);

					System.out.println("Existe coche = " + String.valueOf(exCo));

					if (exCo > 0) { // No es la primera vez que aparca en el parking

						// Preparamos consulta que nos devuelve la última hora de salida del coche con
						// matricula "matricula"
						PreparedStatement statementHoraSalidaCoche = conn.prepareStatement(consultarSalidaCoche);
						// Asignamos prarametros
						statementHoraSalidaCoche.setString(1, matricula);
						System.out.println(statementHoraSalidaCoche.toString());

						// Ejecutamos la consulta
						// Se comprueba si el coche con matrícula "matricula" tiene asociada una hora de
						// salida a la última vez que entró al parking
						try (ResultSet rs = statementHoraSalidaCoche.executeQuery()) {
							// Si la hora de salida no es nula
							// El coche no está en el parking
							if (rs.next()) {
								Timestamp salida = rs.getTimestamp("Fecha_salida"); // Nos guardamos la fecha de salida

								if (salida != null) { // Se registró la última salida del vehículo del parking

									// Buscamos en la base de datos de que tipo es el vehiculo que quiere aparcar
									PreparedStatement statementTipoDeCoche = conn
											.prepareStatement(consultarTipoDeCoche);
									// Asignamos la matricula del coche a la consulta
									statementTipoDeCoche.setString(1, matricula);
									// Imprimimos la consulta
									System.out.println(statementTipoDeCoche.toString());

									try (ResultSet rsTipo = statementTipoDeCoche.executeQuery()) {
										// Si tenemos resultado
										if (rsTipo.next()) {
											// Tipo del vehiculo con matricula "matricula"
											tipo = rsTipo.getString("Tipo");

											PreparedStatement statementNumPlaza = conn
													.prepareStatement(consultarEstadoPlaza);
											// Asignamos prarametros
											statementNumPlaza.setString(1, tipo);
											System.out.println(statementNumPlaza.toString());

											// Ejecutamos la consulta para buscar una plaza libre para el vehiculo de
											// ese tipo
											try (ResultSet rs2 = statementNumPlaza.executeQuery()) {
												if (rs2.next()) {

													System.out.println("Plaza libre encontrada");

													// "num" es el numero de plaza libre encontrada
													int num = rs2.getInt("Numero");
													plaza.setNumeroPlaza(num);
													plaza.setEstadoPlaza(true);

													// Establecemos esa plaza como ocupada
													PreparedStatement statement3 = conn
															.prepareStatement(modificarPlazaOcupada);
													statement3.setInt(1, num);
													System.out.println(statement3.toString());
													statement3.executeUpdate(); // Se ejecuta el update
													statement3.close();

													// Se registra que el coche con matricula "matricula" ha aparcado en
													// la plaza de numero "num" a la hora actual
													PreparedStatement escribeAparcar = conn
															.prepareStatement(registrarAparcar);
													escribeAparcar.setTimestamp(1, horaEntrada);
													escribeAparcar.setString(2, matricula);
													escribeAparcar.setInt(3, num);

													System.out.println(escribeAparcar.toString());
													escribeAparcar.executeUpdate(); // Se ejecuta el update
													escribeAparcar.close();

												}
											} catch (Exception e5) {
												System.out.println(
														"No existe plaza libre para vehiculo de tipo: " + tipo);
												return plaza;
											}
											statementNumPlaza.close();
										}
									} catch (Exception e4) {
										e4.printStackTrace();
										System.out.println("Fallo consultar tipo de coche, pero ya ha aparcado");
									}
									statementTipoDeCoche.close();
									System.out.println("-->" + String.valueOf(plaza.getNumeroPlaza()));
									return plaza;

								} else { // El vehículo aún está en el parking
									System.out.println("El vehículo aun está en el parking");
									plaza.setNumeroPlaza(-2);
									return plaza;
								}

							}
						} catch (Exception e5) {
							e5.printStackTrace();
							System.out.println("excepcion e5");
						}
						statementHoraSalidaCoche.close();
					} else { // Es la primera vez que el vehiculo aparca

						// Buscamos en la base de datos de que tipo es el vehiculo que quiere aparcar
						PreparedStatement statementTipoDeCoche = conn.prepareStatement(consultarTipoDeCoche);
						// Asignamos la matricula del coche a la consulta
						statementTipoDeCoche.setString(1, matricula);
						// Imprimimos la consulta
						System.out.println(statementTipoDeCoche.toString());

						// Ejecutamos la consulta
						try (ResultSet rsTipo = statementTipoDeCoche.executeQuery()) {
							// Si tenemos resultado
							if (rsTipo.next()) {
								// Tipo del vehiculo con matricula "matricula"
								tipo = rsTipo.getString("Tipo");

								PreparedStatement statementNumPlaza = conn.prepareStatement(consultarEstadoPlaza);
								// Asignamos prarametros
								statementNumPlaza.setString(1, tipo);
								System.out.println(statementNumPlaza.toString());

								// Ejecutamos la consulta para buscar una plaza libre para el vehiculo de ese
								// tipo
								try (ResultSet rs2 = statementNumPlaza.executeQuery()) {
									if (rs2.next()) {
										System.out.println(
												"Plaza libre encontrada: " + String.valueOf(rs2.getInt("Numero")));

										// "num" es el numero de plaza libre encontrada
										int num = rs2.getInt("Numero");
										plaza.setNumeroPlaza(num);
										plaza.setEstadoPlaza(true);

										// Establecemos esa plaza como ocupada
										PreparedStatement statement3 = conn.prepareStatement(modificarPlazaOcupada);
										statement3.setInt(1, num);
										System.out.println(statement3.toString());
										statement3.executeUpdate(); // Se ejecuta el update
										statement3.close();

										// Se registra que el coche con matricula "matricula" ha aparcado en la plaza de
										// numero "num" a la hora actual
										PreparedStatement escribeAparcar = conn.prepareStatement(registrarAparcar);
										escribeAparcar.setTimestamp(1, horaEntrada);
										escribeAparcar.setString(2, matricula);
										escribeAparcar.setInt(3, num);

										System.out.println(escribeAparcar.toString());
										escribeAparcar.executeUpdate(); // Se ejecuta el update
										escribeAparcar.close();

									}
								} catch (Error e2) {
									System.out.println("No existe plaza libre para vehiculo de tipo: " + tipo);
									return plaza;
								}
								statementNumPlaza.close();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
							System.out.println("Fallo consultar tipo de coche, siendo la primera vez que aparca");
						}
						statementTipoDeCoche.close();
						return plaza;
					}
				}
			} catch (Exception e) {
				System.out
						.println("Consulta para comprobar si el coche ha aparcado anteriormente en el parking fallida");
				e.printStackTrace();
			}
			PSexisteCoche.close();
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}

	public boolean SalirParking(String matricula) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión

			Date date = new Date(); // Obtener la fecha y hora actual
			Timestamp horaSalida = new Timestamp(date.getTime()); // Crear un objeto Timestamp

			// Preparamos consulta que nos devuelve la última hora de salida del coche con
			// matricula "matricula"
			PreparedStatement statementHoraSalidaCoche = conn.prepareStatement(consultarSalidaCoche);
			// Asignamos prarametros
			statementHoraSalidaCoche.setString(1, matricula);
			System.out.println(statementHoraSalidaCoche.toString());

			// Ejecutamos la consulta
			// Se comprueba si el coche con matrícula "matricula" tiene asociada una hora de
			// salida a la última vez que entró al parking
			try (ResultSet rs = statementHoraSalidaCoche.executeQuery()) {
				// Si la hora de salida es nula
				// El coche está en el parking
				if (rs.next()) {
					if (rs.getTimestamp("Fecha_salida") == null) {

						// Buscamos en la base de datos el numero de plaza que ocupa el vehiculo con
						// matricula "matricula"
						PreparedStatement statementNumPlaza = conn.prepareStatement(consultarPlazaOcupada);
						// Asignamos la matricula del coche a la consulta
						statementNumPlaza.setString(1, matricula);
						// Imprimimos la consulta
						System.out.println(statementNumPlaza.toString());

						// Ejecutamos la consulta
						try (ResultSet rsPlaza = statementNumPlaza.executeQuery()) {
							// Si tenemos resultado
							if (rsPlaza.next()) {
								// Tipo del vehiculo con matricula "matricula"
								int num = rsPlaza.getInt("Num_plaza");

								// Preparamos la consulta para establecer la plaza como libre
								PreparedStatement statementLiberarPlaza = conn
										.prepareStatement(modificarPlazaNOOcupada);
								statementLiberarPlaza.setInt(1, num);
								System.out.println(statementLiberarPlaza.toString());
								statementLiberarPlaza.executeUpdate(); // Se ejecuta el update
								statementLiberarPlaza.close();

								// Se registra que el coche con matricula "matricula" ha salido de la
								// plaza de numero "num" a la hora actual
								PreparedStatement escribeAparcar = conn.prepareStatement(registrarSalida);
								escribeAparcar.setTimestamp(1, horaSalida);
								escribeAparcar.setString(2, matricula);
								escribeAparcar.setInt(3, num);

								System.out.println(escribeAparcar.toString());
								escribeAparcar.executeUpdate(); // Se ejecuta el update
								escribeAparcar.close();

								statementNumPlaza.close();

								return true;
							} else {
								System.out.println("No se encuentra la plaza en la que esta aparcado el vehiculo");
								return false;
							}
						} catch (SQLException se) {
							System.out.println("Consulta de numero de plaza fallida");
							se.printStackTrace();
							return false;
						}
					} else {
						System.out.println("El vehiculo no esta aparcado en el parking actualmente");
						return false;
					}
				}
				statementHoraSalidaCoche.close();
			} catch (SQLException se) {
				System.out.println("Consulta para comprobar si el coche está en el parking fallida");
				se.printStackTrace();
				return false;
			}
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query consultarSalidaCoche realizada");
			return true;
		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return false;
	}

	public int[] ObtenerOcupacion(String tipo) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			// Abrimos conexión
			int plaza[] = new int[2];

			PreparedStatement total = conn.prepareStatement(numPlazas);
			// Asignamos prarametros
			total.setString(1, tipo);
			System.out.println(total.toString());
			// Ejecutamos la consulta
			try (ResultSet rs = total.executeQuery()) {
				if (rs.next()) {
					plaza[0] = rs.getInt(1);
					System.out.println("Plazas totales de " + tipo + ": " + String.valueOf(plaza[0]));

					PreparedStatement ocupadas = conn.prepareStatement(numOcupa);
					ocupadas.setString(1, tipo);

					try (ResultSet rs2 = ocupadas.executeQuery()) {
						if (rs2.next()) {
							plaza[1] = rs2.getInt(1);
							System.out.println("Plazas ocupadas de " + tipo + ": " + String.valueOf(plaza[1]));
							ocupadas.close();
						}
					} catch (SQLException se2) {
						System.out.println("Consulta numero de plazas libres fallida");
						se2.printStackTrace();
					}
				}
			} catch (SQLException se) {
				System.out.println("Consulta numero total de plazas fallida");
				se.printStackTrace();
			}
			total.close();
			// Cerramos conexion y devolvemos el dato
			ConnectionManager.releaseConnection(conn);
			System.out.println("Conexión cerrada, query ObtenerOcupacion realizada");
			return plaza;

		} catch (Error e) {
			System.out.println("Error en la conexión");
		}
		return null;
	}
}