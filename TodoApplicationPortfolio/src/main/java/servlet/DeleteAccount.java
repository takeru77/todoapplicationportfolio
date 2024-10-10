package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccount;
import model.dao.DeleteUserDAO;
import model.dao.TaskTodoDAO;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		HttpSession session = request.getSession();
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		session.invalidate();
		
		
		TaskTodoDAO tasktododao = new TaskTodoDAO();
		DeleteUserDAO deleteuserdao = new DeleteUserDAO();
		
		boolean dropTableResult;
		boolean deleteUserAccount;
		RequestDispatcher dispatcher;
		
		try {
			dropTableResult = tasktododao.DropTable(useraccount);			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			String errorMsg = "削除に失敗しました";
			request.setAttribute("errorMsg", errorMsg);
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteaccount.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		try {
			deleteUserAccount = deleteuserdao.DeleteUserAccount(useraccount);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// ログイン状態からアカウントは削除するからdeleteUserAccountの失敗が考えにくいことは確か。
			// もしここでalltasksテーブルが削除され、useraccountテーブルが残ったとしても、
			// ログインは問題なく出来る。そして再度アカウント削除処理をしたとしても上のdropTableResult
			// はtrueで抜けるから、もしここで万一失敗したとしても大丈夫だと考える。
		}
		
		// スキーマを削除する処理
		try {
			//
			deleteuserdao.DropSchema(useraccount);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deletecheck.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
