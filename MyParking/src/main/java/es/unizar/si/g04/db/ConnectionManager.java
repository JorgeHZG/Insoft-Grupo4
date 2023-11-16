package es.unizar.si.g04.db;

import java.sql.*;

/** * Clase que abstrae la conexion con la base de datos. */

public class ConnectionManager {
    // JDBC nombred el driver y URL de BD
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    // Proyecto dado como prueba -> private static final String DB_URL = "jdbc:pos
    // tgresql://localhost:54
    // 32/postgres?currentSchema=prj1";
    private static final String DB_URL = "jdbc:postgresql://br0915npjnsybccxqaqo-postgresql.services.clever-cloud.com:50013/br0915npjnsybccxqaqo";

    // Credenciales de la Base de Datos
    private static final String USER = "umymwa2zoi69hlq837n1";
    private static final String PASS = "Wr6C6GvOnL8avA3JWbIHOx7b64jUHA";

    // Devuelve una nueva conexion. ||METERLA EN UN DAO||
    public final static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Libera la conexion, devolviendola al pool
    public final static void releaseConnection(Connection conn) throws SQLException {
        conn.close();
    }
}
