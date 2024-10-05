package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.AllTasks;
import model.DBConnection;
import model.UserAccount;

public class LoginDAO {
	
	public boolean LoginCheck(String email, String password) throws SQLException, ClassNotFoundException {
		// ログイン成功か失敗かを格納する変数
		boolean loginCheckResult = false;
		
		String sql = "SELECT email, password FROM public.useraccount WHERE email = ? AND password = ?";
		
		System.out.println("データベースに接続します");
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			System.out.println("データベースに接続しました");
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
		System.out.println("GetAllTasksに入りました");
		Integer id = useraccount.getId();
		String userid = id.toString();
		System.out.println("useridを取得しました");
		// AllTasks.javaファイルで新しい変数宣言をしたため、
		// その変数のスコープから取得できないためNullが発生し、
		// エラーが発生した。
//		AllTasks alltasks = new AllTasks();
//		↑場所を変更する
		List<AllTasks> todoList = new ArrayList<>();
		
		System.out.println(useraccount.getUsername());
		System.out.println(userid);
		
		String sql = "SELECT userid, piece, title, memo, deadlinedate FROM " + useraccount.getUsername() + "_" + userid + ".alltasks";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			System.out.println("GetAllTasksのtryに入りました");
			ResultSet res = pstmt.executeQuery();
			System.out.println("ResultSetが終わりました");
			int syuukaisuu = 0;
			while (res.next()) {
				//
				AllTasks alltasks = new AllTasks();
				System.out.println("GetAllTasksのwhile文に入りました");
				alltasks.setUserid(res.getInt("userid"));
				alltasks.setPiece(res.getInt("piece"));
				alltasks.setTitle(res.getString("title"));
				alltasks.setMemo(new StringBuilder(res.getString("memo")));
				java.sql.Date sqlDate = res.getDate("deadlinedate");
				if (sqlDate == null) {
//					Optional<LocalDate> nulldate = null;
					alltasks.setDeadlinedate(null);
				} else {
					LocalDate localDate = sqlDate.toLocalDate();
					Optional<LocalDate> date = Optional.ofNullable(localDate);
					alltasks.setDeadlinedate(date);
				}
				
				System.out.println("渡したalltasks " + alltasks.getPiece() + " " + alltasks.getPiece() + " " + alltasks.getTitle());
				todoList.add(alltasks);
				System.out.println(todoList.get(syuukaisuu).getPiece());
				++syuukaisuu;
			}
		}
		System.out.println("try終了後");
		System.out.println("todoListが終わりました");
		return todoList;
	}
}
