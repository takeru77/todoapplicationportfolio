package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

@WebServlet("/NewTaskAdd2")
public class NewTaskAdd2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = (String)request.getAttribute("title");
		StringBuilder memo = (StringBuilder)request.getAttribute("memo");
		Optional<LocalDate> deadlinedate = (Optional<LocalDate>)request.getAttribute("deadlinedate");
		int nextpiece = (int)request.getAttribute("nextpiece");
		TaskTodoDAO tasktododao = new TaskTodoDAO();
		AllTasks alltasks = new AllTasks();
		
		HttpSession session = request.getSession();
		UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
		
		Object todoListobj = session.getAttribute("todoList");
		List<AllTasks> todoList;
		if (todoListobj instanceof List<?>) {
			todoList = (List<AllTasks>)todoListobj;
		} else {
			// todoListをnullではなくArrayListを挿入するように変更
			todoList = new ArrayList<>();
		}
		
		try {
			alltasks = tasktododao.TaskAdd(useraccount, nextpiece, title, memo, deadlinedate);		
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (alltasks != null) {
			todoList.add(alltasks);
			session.setAttribute("todoList", todoList);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tasktodo.jsp");
		dispatcher.forward(request, response);
	}

}
