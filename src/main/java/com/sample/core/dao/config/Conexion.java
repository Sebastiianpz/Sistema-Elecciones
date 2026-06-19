package com.sample.core.dao.config;
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Conexion {
	
	
	private static final Logger log = Logger.getLogger(Conexion.class
			.getPackage().getName());
	
	private static final String HOST = "200.41.173.226"; // <-- Ponemos la IP del servidor
	private static final String URL = "jdbc:mysql://"+HOST+":3306";
	private static final String DBNAME = "padron"; // <-- Ojo: si la base de datos en el servidor se llama distinto a "padron", cambialo acá
	
	private static final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";				
	private static final String USUARIO = "proyectos"; // <-- Tu usuario nuevo
	private static final String PASSWORD = "Fatima5totc"; // <-- Tu contraseña nueva
	

	private static Conexion instance = Conexion.getInstance();
	 
	private Connection conn;
	
	private Conexion() {}
	
	public Connection dameConnection() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL+"/"+ DBNAME+TIMEZONE, USUARIO, PASSWORD);
			if (!conn.isClosed()) {
				
				log.info("conectado a la base de datos");
			}	
			return conn;
		} catch (ClassNotFoundException e) {
			//log.error("Error de acceso al driver" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			//log.error("Error de SQL" + e.getMessage());
		}
		return null;
	}
	
	 public static Conexion getInstance() {
	       if (instance == null) {
	    	   instance = new Conexion();
	       }
	       return instance;
	   }

	 
}
