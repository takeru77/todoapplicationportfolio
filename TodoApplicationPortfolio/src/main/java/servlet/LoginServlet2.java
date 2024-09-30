package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AllTasks;
import model.UserAccount;
import model.dao.LoginDAO;

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		boolean loginResult = false;
		
		LoginDAO logindao = new LoginDAO();
		
		try {
			loginResult = logindao.LoginCheck(email, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (loginResult) {
			//
			UserAccount useraccount = new UserAccount();
			try {
				//
				useraccount = logindao.GetUserAccount(password);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (useraccount == null) {
				System.out.println("LoginServlet2でuseraccountが取得できません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("useraccount", useraccount);
				session.setAttribute("useraccountid", useraccount.getId());
				
				// alltasksデータベースから情報を取り出さなければならない
				List<AllTasks> todoList = new ArrayList<>();
				try {
					//
					todoList = logindao.GetAllTasks(useraccount);
				} catch (SQLException | ClassNotFoundException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				session.setAttribute("todoList", todoList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tasktodo.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			String falseInput = "メールアドレスまたはパスワードが違います";
			request.setAttribute("falseInput", falseInput);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}