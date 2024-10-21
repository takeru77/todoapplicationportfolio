package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1つ目
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher;
		
		// ユーザー名、メールアドレス、パスワードを取得
		Object usernameobj = request.getParameter("username");
		String username;
		if (usernameobj instanceof String) {
			username = (String)usernameobj;
			if (username.matches(".*\\s.*")) {
				username = null;
			}
			if (!(username.matches("^[a-zA-Z0-9]{1,10}$"))) {
				username = null;
			}
		} else {
			username = null;
		}
		
		Object emailobj = request.getParameter("email");
		String email;
		if (emailobj instanceof String) {
			email = (String)emailobj;
			if (email.matches(".*\\s.*")) {
				email = null;
			}
			if (!(email.matches("^[a-zA-Z0-9\\.\\-_@]{1,40}$"))) {
				email = null;
			}
		} else {
			email = null;
		}
		
		Object passwordobj = request.getParameter("password");
		String password;
		if (passwordobj instanceof String) {
			password = (String)passwordobj;
			if (password.matches(".*\\s.*")) {
				password = null;
			}
			if (!(password.matches("^[a-zA-Z0-9!@#$%^&*\\.=+<>?:;\\-_]{1,12}$"))) {
				password = null;
			}
//			
		} else {
			password = null;
		}
		
		if (username == null ||  email == null || password == null) {
			if (username == null) {
				String spaceErrorMsg = "文字が未入力または空白文字、または条件外の文字列です";
				request.setAttribute("spaceErrorMsg", spaceErrorMsg);
			} else if (email == null) {
				String spaceErrorMsg2 = "文字が未入力または空白文字、または条件外の文字列です";
				request.setAttribute("spaceErrorMsg2", spaceErrorMsg2);
			} else if (password == null) {
				String spaceErrorMsg3 = "文字が未入力または空白文字、または条件外の文字列です";
				request.setAttribute("spaceErrorMsg3", spaceErrorMsg3);
			}
			// テスト用
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		} else {
			// テスト用
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			dispatcher = request.getRequestDispatcher("RegisterServlet2");
			dispatcher.forward(request, response);
		}
	}
}
