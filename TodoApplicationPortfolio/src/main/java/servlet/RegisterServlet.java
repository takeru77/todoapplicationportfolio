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
		} else {
			password = null;
		}
		
		if (username == null ||  email == null || password == null) {
			if (username == null) {
				String spaceErrorMsg = "文字が未入力または空白文字が含まれています";
				request.setAttribute("spaceErrorMsg", spaceErrorMsg);
			} else if (email == null) {
				String spaceErrorMsg2 = "文字が未入力または空白文字が含まれています";
				request.setAttribute("spaceErrorMsg2", spaceErrorMsg2);
			} else if (password == null) {
				String spaceErrorMsg3 = "文字が未入力または空白文字が含まれています";
				request.setAttribute("spaceErrorMsg3", spaceErrorMsg3);
			}
			// テスト用
			System.out.println("3つのnullチェックに引っ掛かりました");
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		} else {
			// テスト用
			System.out.println("3つのnullチェックを抜けました");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			dispatcher = request.getRequestDispatcher("RegisterServlet2");
			dispatcher.forward(request, response);
		}
	}
}
