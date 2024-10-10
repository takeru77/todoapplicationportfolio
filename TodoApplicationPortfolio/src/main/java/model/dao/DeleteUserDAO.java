package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;
import model.UserAccount;

public class DeleteUserDAO {
	public boolean DeleteUserAccount(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		boolean deleteUserResult = false;
		int id = useraccount.getId();
		
		String sql = "DELETE FROM public.useraccount WHERE id = ?";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.setInt(1, id);
			
			int r = pstmt.executeUpdate();
			
			if (r != 0) {
				deleteUserResult = true;
			} else {
				deleteUserResult = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return deleteUserResult;
	}
	
	public void DropSchema(UserAccount useraccount) throws SQLException, ClassNotFoundException {
		
		Integer idInteger = useraccount.getId();
		String getId = idInteger.toString();
		
		String sql = "DROP SCHEMA " + useraccount.getUsername() + "_" + getId;
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			//
			pstmt.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
