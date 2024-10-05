package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import model.AllTasks;
import model.DBConnection;
import model.UserAccount;

public class TaskTodoDAO {
	public int Create(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		//
		String name = useraccount.getUsername();
		Integer id = useraccount.getId();
		String getId = id.toString();
		String sql = "CREATE SCHEMA " + name + "_" + getId;
		StringBuilder sql2 = new StringBuilder();
		sql2.append("CREATE TABLE " + name + "_" + getId + ".alltasks (");
		sql2.append("userid INT,");
		sql2.append("piece INT PRIMARY KEY,");
		sql2.append("title VARCHAR(15),");
		sql2.append("memo VARCHAR(150),");
		sql2.append("deadlinedate DATE);");
		System.out.println(sql2.toString());
		
		// 
		int r = 0;
		
		try (Connection con = DBConnection.getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(sql);
				 PreparedStatement pstmt2 = con.prepareStatement(sql2.toString())) {
				//
				
				// もし失敗するとSQLExceptionを返すらしいが、
				// スキーマ名もチェック済みでエラーが考えられないため、
				// 戻り値は受け取らないこととした。?
				// やっぱりsql2は一応判断材料になるため戻り値を加えた。
				pstmt.executeUpdate();
				r = pstmt2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public int RecordCount(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		//
		Integer id = useraccount.getId();
		String getId = id.toString();
		String sql = "SELECT * FROM " + useraccount.getUsername() + "_" + getId + ".alltasks";
		int countrecords = 0;
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				countrecords += 1;
			}
			
//			if (countrecords == 0) {
//				countrecords += 1;
//			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return countrecords;
	}
	
	
	
	public AllTasks TaskAdd(UserAccount useraccount, int nextpiece, String title, StringBuilder memo, Optional<LocalDate> deadlinedate) throws SQLException, ClassNotFoundException {
		//
		Integer idstring = useraccount.getId();
		String getId = idstring.toString();
		AllTasks alltasks = new AllTasks();
		
		int id = useraccount.getId();
		
		LocalDate ld;
		
		if (deadlinedate == null) {
			//
		}
		
		String sql = "INSERT INTO " + useraccount.getUsername() + "_" + getId + ".alltasks (userid, piece, title, memo, deadlinedate) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setInt(1, useraccount.getId());
			pstmt.setInt(2, nextpiece);
			pstmt.setString(3, title);
			pstmt.setString(4, memo.toString());
//			pstmt.setObject(5, deadlinedate);
			
			if (deadlinedate == null) {
				//
				pstmt.setObject(5, null);
			} else {
				pstmt.setObject(5, deadlinedate);
			}
			
			int r = pstmt.executeUpdate();
			
			if (r != 0) {
				alltasks.setUserid(useraccount.getId());
				alltasks.setPiece(nextpiece);
				alltasks.setTitle(title);
				alltasks.setMemo(memo);
				alltasks.setDeadlinedate(deadlinedate);
			} else {
				alltasks = null;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return alltasks;
	}
}

