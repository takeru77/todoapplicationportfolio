package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DateValidator;
import model.UserAccount;
import model.dao.TaskTodoDAO;

@WebServlet("/NewTaskAdd")
public class NewTaskAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		System.out.println("NewTaskAddに入りました");
		request.setCharacterEncoding("UTF-8");
		
		Object titleobj = request.getParameter("title");
		String title;
		if (titleobj instanceof String) {
			title = (String)titleobj;
		} else {
			title = "";
		}

		Object memostringobj = request.getParameter("memo");
		String memostring;
		if (memostringobj instanceof String) {
			memostring = (String)memostringobj;
		} else {
			memostring = "";
		}
//		一応参考程度に取っておく
//		StringBuilder memo = new StringBuilder(memostring != null ? memostring : "");
		StringBuilder memo = new StringBuilder(memostring);
		
		Object deadlinedateobj = request.getParameter("deadlinedate");
		String deadlinedatestring;
		if (deadlinedateobj instanceof String) {
			deadlinedatestring = (String)deadlinedateobj;
		} else {
			deadlinedatestring = "";
		}
		
		Optional<LocalDate> deadlinedate;
		DateValidator stringToLocalDate = new DateValidator();
		boolean localDateResult = stringToLocalDate.isValidDate(deadlinedatestring);
		System.out.println("DateValidatorが終わりました");
		if (localDateResult) {
			//
			try {
				deadlinedate = Optional.ofNullable(LocalDate.parse(deadlinedatestring));				
			} catch (Exception e) {
				System.out.println("NewTaskAdd catch Exception");
				deadlinedate = null;
			}
			System.out.println("deadlinedateをOptional型へ変換しました");
			HttpSession session = request.getSession();
			UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
			TaskTodoDAO tasktododao = new TaskTodoDAO();
			int countrecords = 0;
			System.out.println("countrecordの手前まで来ました");
			try {
				countrecords = tasktododao.RecordCount(useraccount);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("countrecordの条件式の手前まで来ました");
			if (countrecords < 20) {
				// countrecords++であるとnextpieceに格納された後に、
				// countrecordsがインクリメントされてしまう。
				int nextpiece = ++countrecords;
				System.out.println("nextpieceは" + nextpiece);
				request.setAttribute("title", title);
				request.setAttribute("memo", memo);
				request.setAttribute("deadlinedate", deadlinedate);
				request.setAttribute("nextpiece", nextpiece);
				System.out.println("NewTaskAddで画面遷移します");
				RequestDispatcher dispatcher = request.getRequestDispatcher("NewTaskAdd2");
				dispatcher.forward(request, response);
			} else {
				String errorMsg = "タスクの上限数は20個までになっております";
				request.setAttribute("errorMsg", errorMsg);
				response.sendRedirect("newtask.jsp");
			}
		} else {
			//
			String errorMsg = "正しい形式で入力して下さい";
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("newtask.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
