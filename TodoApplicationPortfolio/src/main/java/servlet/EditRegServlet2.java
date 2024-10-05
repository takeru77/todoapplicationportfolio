package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

@WebServlet("/EditRegServlet2")
public class EditRegServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String title = (String)request.getAttribute("title");
		StringBuilder memo = (StringBuilder)request.getAttribute("memo");
		Optional<LocalDate> deadlinedate = (Optional<LocalDate>)request.getAttribute("deadlinedate");
		String todoListnumberString = (String)request.getAttribute("todoListnumber");
		int todoListnumber = Integer.parseInt(todoListnumberString);
	    String pieceNumberString = (String)request.getAttribute("pieceNumber");
	    int pieceNumber = Integer.parseInt(pieceNumberString);
		boolean taskTodoResult = false;
		
		// 編集ボタンを押して編集するということは、sessionスコープにあるtodoList
		// の中にAllTasksが必ず1つは入っているから、いきなりList<AllTasks>に変換
		// しても問題ない。
		HttpSession session = request.getSession();
		List<AllTasks> todoList = (List<AllTasks>)session.getAttribute("todoList");
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		
		TaskTodoDAO taskTodo = new TaskTodoDAO();
		try {
			taskTodoResult = taskTodo.TaskUpdate(useraccount, pieceNumber, title, memo, deadlinedate);			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (taskTodoResult) {
			//
			AllTasks allTasks = new AllTasks(useraccount.getId(), pieceNumber, title, memo, deadlinedate);
			todoList.set(todoListnumber, allTasks);
			
			request.setAttribute("todoList", todoList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tasktodo.jsp");
			dispatcher.forward(request, response);
			//
		}
		System.out.println("開発未完了部分");
	}
}
