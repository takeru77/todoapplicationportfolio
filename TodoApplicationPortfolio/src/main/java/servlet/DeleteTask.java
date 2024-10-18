package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import model.dao.TaskTodoDAO;

@WebServlet("/DeleteTask")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String todoListnumberString = (String)request.getParameter("todoListnumber");
		String pieceNumberString = (String)request.getParameter("pieceNumberString");
		
		// もし送られてきたパラメータが空であった場合、
		// リクエストスコープから受け取る。そうすれば同じ変数を使って受け取れる
		int todoListnumber;
		int pieceNumber;
		if (todoListnumberString == null && pieceNumberString == null) {
			//
			todoListnumber = (int)request.getAttribute("todoListnumber");
			pieceNumber = (int)request.getAttribute("pieceNumberString");
		} else {
			todoListnumber = Integer.parseInt(todoListnumberString);
			pieceNumber = Integer.parseInt(pieceNumberString);			
		}
		
		TaskTodoDAO tasktododao = new TaskTodoDAO();
		
		boolean deleteResult = false;
		
		AllTasks allTasks = new AllTasks();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		// これも同様、削除ボタンが押されるということはタスクは1つ以上存在している。
		// よって型チェックはしない。
		List<AllTasks> todoList = (List<AllTasks>)session.getAttribute("todoList");
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		
		allTasks = todoList.get(todoListnumber);
		todoList.remove(todoListnumber);
		
		try {
			//
			deleteResult = tasktododao.DeleteTask(useraccount, pieceNumber);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (deleteResult) {
			//
			session.setAttribute("todoList", todoList);
			dispatcher = request.getRequestDispatcher("tasktodo.jsp");
			dispatcher.forward(request, response);
		} else {
			//
			todoList.set(todoListnumber, allTasks);
			String deleteError = "削除時にエラーが発生しました";
			request.setAttribute("deleteError", deleteError);
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edittasks.jsp");
			dispatcher.forward(request, response);
		}
	}

}
