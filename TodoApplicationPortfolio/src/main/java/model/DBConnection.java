package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// データベースのURL
		final String URL = "jdbc:postgresql://172.20.10.4:5432/UserAccount";
		// データベースにアクセスするユーザー
		final String USER = "tda";
		// データベースのパスワード
		final String PASSWORD = "tS;we@;+";
		
		Class.forName("org.postgresql.Driver");
		
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}
}
