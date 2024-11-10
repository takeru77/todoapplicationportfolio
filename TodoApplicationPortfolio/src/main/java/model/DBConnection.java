package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// データベースのURL
		// 本番用
		final String URL = "jdbc:postgresql://153.126.161.113:5432/useraccount";
		// ローカル用
//		final String URL = "jdbc:postgresql://172.20.10.4:5432/UserAccount";
		// データベースにアクセスするユーザー
		final String USER = "tda";
		// データベースのパスワード
		final String PASSWORD = "tS;we@;+";
		
		Class.forName("org.postgresql.Driver");
		
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("DBConnection終わり");
		return con;
	}
	// 153.126.161.113
}
