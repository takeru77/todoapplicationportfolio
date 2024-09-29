package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher;
		
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
		
		if (email == null || password == null) {
			if (email == null) {
				String spaceErrorMsg = "文字が未入力または空白文字が含まれています";
				request.setAttribute("spaceErrorMsg", spaceErrorMsg);
			} else if (password == null) {
				String spaceErrorMsg2 = "文字が未入力または空白文字が含まれています";
				request.setAttribute("spaceErrorMsg2", spaceErrorMsg2);
			}
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			//
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			dispatcher = request.getRequestDispatcher("LoginServlet2");
			dispatcher.forward(request, response);
		}
	}

}
