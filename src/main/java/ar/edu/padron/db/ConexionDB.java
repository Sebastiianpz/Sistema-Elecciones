package ar.edu.padron.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionDB {

    private static final Logger log = Logger.getLogger(ConexionDB.class.getName());

    private static final String HOST = "localhost";
    private static final String URL = "jdbc:mysql://" + HOST + ":3306";
    private static final String DBNAME = "padron";
    private static final String TIMEZONE = "?useUnicode=true&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; 

    private static ConexionDB instance; // No inicializar aquí para evitar recursividad
    private Connection conn;

    private ConexionDB() {}

    public Connection dameConnection() {
        try {
            Class.forName(DRIVER);
            // Agregamos validación para no reabrir si ya está abierta y es válida
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL + "/" + DBNAME + TIMEZONE, USUARIO, PASSWORD);
            }
            return conn;
        } catch (ClassNotFoundException e) {
            log.severe("Error de acceso al driver: " + e.getMessage());
        } catch (SQLException e) {
            log.severe("Error de SQL al conectar: " + e.getMessage());
        }
        return null;
    }

    public static ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }
}
