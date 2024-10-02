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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<AllTasks> todoList = (List<AllTasks>)session.getAttribute("todoList");
		
		String Number = (String)request.getParameter("edit");
		int pieceNumber = Integer.parseInt(Number);
		//
		AllTasks test = new AllTasks(pieceNumber);
		int todoListnumber = todoList.indexOf(test);
		System.out.println("テストの結果は" + todoListnumber + "でした");
		
		AllTasks purposeTask = todoList.get(todoListnumber);
		
		request.setAttribute("purposeTask", purposeTask);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edittasks.jsp");
		dispatcher.forward(request, response);
		
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

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

}
