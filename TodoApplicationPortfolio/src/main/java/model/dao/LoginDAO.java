package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.AllTasks;
import model.DBConnection;
import model.UserAccount;

public class LoginDAO {
	
	public boolean LoginCheck(String email, String password) throws SQLException, ClassNotFoundException {
		// ログイン成功か失敗かを格納する変数
		boolean loginCheckResult = false;
		
		String sql = "SELECT email, password FROM public.useraccount WHERE email = ? AND password = ?";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				loginCheckResult = true;
			} else {
				loginCheckResult = false;
			}
		}
		
		return loginCheckResult;
	}
	
	public UserAccount GetUserAccount(String password) throws SQLException, ClassNotFoundException {
		UserAccount useraccount = new UserAccount();
		
		String sql = "SELECT id, username, email, password FROM public.useraccount WHERE password = ?";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setString(1, password);
			
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				useraccount.setId(res.getInt("id"));
				useraccount.setUsername(res.getString("username"));
				useraccount.setEmail(res.getString("email"));
				useraccount.setPassword(res.getString("password"));
			} else {
				useraccount = null;
			}
		}
		
		return useraccount;
	}
	
	public List<AllTasks> GetAllTasks(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		//
		Integer id = useraccount.getId();
		String userid = id.toString();
		AllTasks alltasks = new AllTasks();
		List<AllTasks> todoList = new ArrayList<>();
		
		String sql = "SELECT userid, piece, title, memo, deadlinedate FROM " + useraccount.getUsername() + userid + ".alltasks";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				//
				alltasks.setUserid(res.getInt("userid"));
				alltasks.setPiece(res.getInt("piece"));
				alltasks.setTitle(res.getString("title"));
				alltasks.setMemo(new StringBuilder(res.getString("memo")));
				java.sql.Date sqlDate = res.getDate("deadlinedate");
				LocalDate localDate = sqlDate.toLocalDate();
				alltasks.setDeadlinedate(localDate);
				
				todoList.add(alltasks);
			}
		}
		return todoList;
	}
}
