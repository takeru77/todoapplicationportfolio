package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SchemaNameCheck;

@WebServlet("/RegisterServlet2")
public class RegisterServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 2つ目
		String username = (String)request.getAttribute("username");
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		boolean checkusername = false;
		RequestDispatcher dispatcher;
		
		checkusername = SchemaNameCheck.startsWithLetter(username);
		if (!checkusername) {
			// テスト用
			String errorMsg2 = "英文字を先頭にしてください";
			request.setAttribute("errorMsg2", errorMsg2);
			dispatcher = request.getRequestDispatcher("register.jsp");
		    dispatcher.forward(request, response);
		} else {
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			dispatcher = request.getRequestDispatcher("RegisterServlet3");
			dispatcher.forward(request, response);
		}
	}

}
