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
		
		String sql = "SELECT password FROM public.useraccount WHERE password = ?";
		
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
		String sql2 = "INSERT INTO public.useraccount (username, email, password) VALUES (?, ?, ?)";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
				
				pstmt2.setString(1, useraccount.getUsername());
				pstmt2.setString(2, useraccount.getEmail());
				pstmt2.setString(3, useraccount.getPassword());
				
				int r = pstmt2.executeUpdate();
				
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
	
	public int Getid(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		int id;
		
		String sql = "SELECT id FROM public.useraccount WHERE password = ?";
		String password = useraccount.getPassword();
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setString(1, password);
			
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				//
				id = res.getInt("id");
			} else {
				id = 0;
			}
		}
	
		return id;
	}
}

//セキュリティホールになる点
//
// このWebアプリに登録していることを、友人は知っているという場合。
// 友人がメールアドレスを勝手に使い、パスワードを設定した所、
// 「このパスワードは既に使われている」と表示された場合、情報が盗み見られる事になる。
