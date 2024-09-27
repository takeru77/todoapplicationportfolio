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
import model.dao.RegisterDAO;
import model.dao.TaskTodoDAO;

@WebServlet("/RegisterServlet4")
public class RegisterServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// newtask.jspにてtasktodo.jspファイルに戻るを押した場合、
		// このサーブレットファイルに遷移してそのファイルへ画面遷移する。
		// newtask.jspに遷移する際にログインチェックはしてあるので、
		// ここではチェックはしていない。
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tasktodo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		// もしnewregiresがtrue、つまり入力したパスワードに重複が無い場合、
		// それぞれ、ユーザー名、メールアドレス、パスワードをデータベースに
		// 登録して、新たなページに遷移する

		// 3つ目
		boolean newregisterjudge = false;
		RequestDispatcher dispatcher;
		String username = (String)request.getAttribute("username");
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		
		RegisterDAO regidao = new RegisterDAO();
		TaskTodoDAO tasktododao = new TaskTodoDAO();
		UserAccount useraccount = new UserAccount(username, email, password);
		HttpSession session = request.getSession();
		
		// セッションスコープにもしuseraccountがあれば、
		// Registerメソッドを実行しないようにしたい。
		Object useraccountobj = session.getAttribute("useraccount");
		if (!(useraccountobj instanceof UserAccount)) {
			// テスト用
			System.out.println("useraccount取得段階");
			try {
				newregisterjudge = regidao.Register(useraccount);
				if (newregisterjudge) {
					int id = regidao.Getid(useraccount);
					// ここでスキーマとそれに対応するalltasksテーブルを作成する
					tasktododao.Create(useraccount);
					// もしUserAccountDBに格納できたなら、その情報をセッションスコープに保存
					// この先のtasktodo.jspで使う他のデータベースでその情報が必要になるため
					session.setAttribute("useraccount", useraccount);
					session.setAttribute("useraccountid", id);
				}
			} catch (SQLException | ClassNotFoundException e) {
				// テスト用
				System.out.println("useraccount取得段階でエラーが起きました");
				e.printStackTrace();
			}
		} else {
			// もしuseraccountobjがUserAccountに変換できた場合、
			// 既にログインしているため、tasktodoに戻る。
			newregisterjudge = true;
		}
		
		if (newregisterjudge) {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tasktodo.jsp");
			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}
	}

}
