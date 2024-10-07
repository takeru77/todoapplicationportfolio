package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccount;

@WebServlet("/ShowProfile")
public class ShowProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		
		String username = useraccount.getUsername();
		String email = useraccount.getEmail();
		String password = useraccount.getPassword();
		StringBuilder sb = new StringBuilder();
		
		int passwordlength = password.length();
		
		int count = 0;
		while (passwordlength > count) {
			sb.append('*');
			++count;
		}
		password = sb.toString();
		
		request.setAttribute("username", username);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
		return;
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

}
