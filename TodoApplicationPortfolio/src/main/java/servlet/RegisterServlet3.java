package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RegisterDAO;

@WebServlet("/RegisterServlet3")
public class RegisterServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 3つ目
		// 必要変数 password newregires
		String username = (String)request.getAttribute("username");
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		boolean newregires = false;
		RequestDispatcher dispatcher;
		// とりあえずpasswordの登録可否を検査する
		RegisterDAO regidao = new RegisterDAO();
		try {
			newregires = regidao.RegisterCheck(password);
		} catch (SQLException | ClassNotFoundException e) {
			// テスト用
			e.printStackTrace();
		}
				
		// もしnewregiresがfalse、つまり入力したパスワードに重複がある場合、
		// register.jspに戻る
		if (!newregires) {
			// テスト用
			// エラーメッセージを格納してregister.jspに戻る
			String errorMsg = "このパスワードは既に存在します";
			request.setAttribute("errorMsg", errorMsg);
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			dispatcher = request.getRequestDispatcher("RegisterServlet4");
			dispatcher.forward(request, response);
		}
	}

}
