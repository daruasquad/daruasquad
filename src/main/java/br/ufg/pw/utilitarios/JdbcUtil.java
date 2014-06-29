package br.ufg.pw.utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	
	public static Connection createConnection() {
		Connection conn = null;
		Properties bdProps = PropertiesManipulador.getProp();	
		
		try {
			// URL de conexão JDBC com o banco de dados.
			String url = "jdbc:postgresql://" + bdProps.getProperty("prop.bd.host") + ":" + bdProps.getProperty("prop.bd.port") + "/" + bdProps.getProperty("prop.bd.databasename");
			// Obtém uma conexão com o banco de dados.
			conn = DriverManager
					.getConnection(url, bdProps.getProperty("prop.bd.user"), bdProps.getProperty("prop.bd.pass"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return conn;
	}
	
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			conn.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			//Aqui já não dá para fazer nada
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	
	public static void close(ResultSet rs) {
		close(null, null, rs);
	}
}
