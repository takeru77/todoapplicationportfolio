package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.UserAccount;

public class RegisterDAO {
	public boolean RegisterCheck(String password) throws SQLException, ClassNotFoundException {
		// 入力パスワードの登録可否の結果を格納する変数
		boolean newregires = false;
		
		String sql = "SELECT password FROM UserAccount WHERE password = ?";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, password);
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				// もしも入力したパスワードが、このデータベースで見つかった場合
				// 登録は出来ないからfalseを格納する
				newregires = false;
			} else {
				// 見つからなかった場合は登録可能であるからtrueを格納する
				newregires = true;
			}
			
			res.close();
			pstmt.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newregires;
		
	}
	
	public boolean Register(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		// 入力パスワードの登録可否を格納する変数
		boolean newregisterjudge = false;
		
		String sql = "INSERT INTO UserAccount (username, email, password) VALUES (?, ?, ?)";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setString(1, useraccount.getUsername());
			pstmt.setString(2, useraccount.getEmail());
			pstmt.setString(3, useraccount.getPassword());
			
			int r = pstmt.executeUpdate();
			
			if (r != 0) {
				newregisterjudge = true;
			} else {
				newregisterjudge = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newregisterjudge;
	}
}

//セキュリティホールになる重大な欠点
//
//例えば、Aさんが同じメールアドレスでもパスワードを変更すれば何個でもアカウントを作れることに気付いたとする。
//そこでAさんは友達のメールアドレスでアカウントを作ったとする。いくつか作っていくとたまたまエラーが出た。
//
//この時このパスワードは友達が使っているものだ！と気が付いてしまう。
//何故なら友達のメールアドレスでもパスワードが作れたのに、このパスワードだけエラーが出るのはおかしいからである。
