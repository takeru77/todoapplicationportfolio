package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccount;

/**
 * Servlet implementation class JumpNewTask
 */
@WebServlet("/JumpNewTask")
public class JumpNewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		
		if (useraccount != null) {
			response.sendRedirect("newtask.jsp");
		} else {
			System.out.println("useraccountはnullです");
			response.sendRedirect("index.html");
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

}
