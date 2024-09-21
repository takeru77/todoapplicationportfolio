package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserAccount;
import model.dao.RegisterDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ユーザー名、メールアドレス、パスワードを取得
		String username = (String)request.getParameter("username");
		String email = (String)request.getParameter("email");
		String password = (String)request.getParameter("password");
		boolean newregires = false;
		boolean newregisterjudge = false;
		
		// とりあえずpasswordの登録可否を検査する
		RegisterDAO regidao = new RegisterDAO();
		try {
			newregires = regidao.RegisterCheck(password);	
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// もしnewregiresがfalse、つまり入力したパスワードに重複がある場合、
		// register.jspに戻る
		if (!newregires) {
			// エラーメッセージを格納してregister.jspに戻る
			String errorMsg = "このパスワードは既に存在します";
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}
		
		// もしnewregiresがtrue、つまり入力したパスワードに重複が無い場合、
		// それぞれ、ユーザー名、メールアドレス、パスワードをデータベースに
		// 登録して、新たなページに遷移する
		
		UserAccount useraccount = new UserAccount(username, email, password);
		try {
			newregisterjudge = regidao.Register(useraccount);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (newregisterjudge) {
			response.sendRedirect("WEB-INF/jsp/ファイル名未定");
		}
	}

}
