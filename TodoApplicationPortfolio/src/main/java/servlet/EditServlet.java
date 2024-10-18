package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AllTasks;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<AllTasks> todoList = (List<AllTasks>)session.getAttribute("todoList");
		
		Object completeObj = request.getParameter("complete");
		String complete;
		if (completeObj instanceof String) {
			complete = (String)completeObj;
		} else {
			complete = null;
		}
		
		String Number = (String)request.getParameter("edit");
		System.out.println("EditServletのNumberは" + Number);
		int pieceNumber = Integer.parseInt(Number);
		//
		AllTasks test = new AllTasks(pieceNumber);
		int todoListnumber = todoList.indexOf(test);
		System.out.println("テストの結果は" + todoListnumber + "でした");
		
		AllTasks purposeTask = todoList.get(todoListnumber);
		
		
		request.setAttribute("todoListnumber", todoListnumber);
		request.setAttribute("purposeTask", purposeTask);
		request.setAttribute("pieceNumberString", pieceNumber);
		
		RequestDispatcher dispatcher;
		
		if (complete != null) {
			//
			dispatcher = request.getRequestDispatcher("DeleteTask");
			dispatcher.forward(request, response);
			return;
		}
		
		dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edittasks.jsp");
		dispatcher.forward(request, response);
		return;
		
		// こうすればtodoListの何番目に格納されているかが分かる		
//		int kaisuu = 0;
//		
//		for (AllTasks alltasksloop : todoList) {
//			kaisuu++;
//			if (pieceNumber == alltasks.getPiece()) {
//				alltasks = alltasksloop;
//				break;
//			}
//		}
	}

}
