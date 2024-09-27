package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;
import model.UserAccount;

public class TaskTodoDAO {
	public void Create(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		//
		String name = useraccount.getUsername();
		String sql = "CREATE SCHEMA " + name;
		StringBuilder sql2 = new StringBuilder();
		sql2.append("CREATE TABLE " + name + ".alltasks (");
		sql2.append("userid INT,");
		sql2.append("piece INT PRIMARY KEY,");
		sql2.append("title VARCHAR(15),");
		sql2.append("memo VARCHAR(150),");
		sql2.append("deadlinedate DATE);");
		
		try (Connection con = DBConnection.getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(sql);
				 PreparedStatement pstmt2 = con.prepareStatement(sql2.toString())) {
				//
				
				// もし失敗するとSQLExceptionを返すらしいが、
				// スキーマ名もチェック済みでエラーが考えられないため、
				// 戻り値は受け取らないこととした。?
				pstmt.executeUpdate();
				pstmt2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

