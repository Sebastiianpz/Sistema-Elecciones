package com.sample.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionDB {

    private static final Logger log = Logger.getLogger(ConexionDB.class.getName());

    private static final String HOST = "db"; // nombre del servicio docker
    private static final String PORT = "3306";
    private static final String DBNAME = "elecciones";

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";

    private static ConexionDB instance;
    private Connection conn;

    private ConexionDB() {}

    public Connection dameConnection() {
        try {
            Class.forName(DRIVER);

            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }

            return conn;

        } catch (ClassNotFoundException e) {
            log.severe("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            log.severe("Error SQL conexión: " + e.getMessage());
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