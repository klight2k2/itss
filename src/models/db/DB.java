package models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import utils.Utils;

public class DB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

	public static Connection getConnection() {
		if (connect != null)
			return connect;
		try {
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/itss";
			connect = DriverManager.getConnection(url, username, password);
			LOGGER.info("Connect database successfully");
		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
			System.out.println(e);
		}
		return connect;
	}

	public static void main(String[] args) {
		DB.getConnection();
	}
}
