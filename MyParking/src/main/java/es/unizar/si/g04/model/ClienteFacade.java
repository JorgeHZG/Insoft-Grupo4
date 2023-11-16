package es.unizar.si.g04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.si.g04.db.PoolConnectionManager;

/*
 * 
 * public class ClienteFacade {
 * 
 * private static String isCorrectLogin =
 * "SELECT DNI, Nombre, Apellido FROM myparking.\"Cliente\" WHERE DNI = ? AND Contrasegna = ?"
 * ;
 * private static String findByUserName = "a";
 * private static String updateDate = "b";
 * 
 * 
 * public boolean validateUser(ClienteVO cliente) {
 * boolean result = false;
 * Connection conn = null;
 * 
 * try {
 * // Abrimos la conexión e inicializamos los parámetros
 * conn = PoolConnectionManager.getConnection();
 * PreparedStatement countPs = conn.prepareStatement(isCorrectLogin);
 * PreparedStatement findPs = conn.prepareStatement(findByUserName);
 * PreparedStatement updatePs = conn.prepareStatement(updateDate);
 * countPs.setString(1, cliente.getNombre());
 * findPs.setString(1, cliente.getApellido());
 * updatePs.setString(1, cliente.getDni());
 * 
 * // Ejecutamos la consulta
 * ResultSet findRs = findPs.executeQuery();
 * ResultSet countRs = countPs.executeQuery();
 * 
 * countRs.next();
 * int n = countRs.getInt(1);
 * System.out.println("Número de registros: " + n);
 * 
 * // Leemos resultados
 * if (n == 1) {
 * // Comparamos contraseñas
 * findRs.next();
 * String dbpwd = findRs.getString("password");
 * if (dbpwd.contentEquals(ClienteVO.getPassword())) {
 * updatePs.execute();
 * result = true;
 * }
 * } else {
 * result = false;
 * }
 * 
 * // liberamos los recursos utilizados
 * findRs.close();
 * findPs.close();
 * countRs.close();
 * countPs.close();
 * updatePs.close();
 * 
 * } catch (SQLException se) {
 * se.printStackTrace();
 * 
 * } catch (Exception e) {
 * e.printStackTrace(System.err);
 * } finally {
 * PoolConnectionManager.releaseConnection(conn);
 * }
 * 
 * return result;
 * }
 * 
 * public ClienteVo getUser(String username) {
 * Connection conn = null;
 * ClienteVO user = null;
 * 
 * try {
 * // Abrimos la conexión e inicializamos los parámetros
 * conn = PoolConnectionManager.getConnection();
 * PreparedStatement ps =
 * conn.prepareStatement("Select * from users where username= ?");
 * ps.setString(1, username);
 * ResultSet rset = ps.executeQuery();
 * rset.next();
 * user = new UserVO(rset.getString("username"), rset.getString("password"));
 * } catch (Exception e) {
 * e.printStackTrace();
 * } finally {
 * PoolConnectionManager.releaseConnection(conn);
 * }
 * return user;
 * }
 * 
 * public boolean addUser(ClienteVO user) {
 * Connection conn = null;
 * 
 * try {
 * // Abrimos la conexión e inicializamos los parámetros
 * conn = PoolConnectionManager.getConnection();
 * PreparedStatement ps =
 * conn.prepareStatement("insert into users set username = ?, password = ? ");
 * ps.setString(1, user.getUserName());
 * // ps.setString(2, new Md5(user.getPassword()).getBytes());
 * ps.execute();
 * } catch (Exception e) {
 * e.printStackTrace();
 * } finally {
 * PoolConnectionManager.releaseConnection(conn);
 * }
 * 
 * return true;
 * }
 * }
 */