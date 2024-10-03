package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DateValidator;

@WebServlet("/EditRegServlet")
public class EditRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		Object titleobj = request.getParameter("title");
		String title;
		if (titleobj instanceof String) {
			title = (String)titleobj;
		} else {
			title = "";
		}
		
		Object memoobj = request.getParameter("memo");
		String memoString;
		if (memoobj instanceof String) {
			memoString = (String)memoobj;
		} else {
			memoString = "";
		}
		StringBuilder memo = new StringBuilder(memoString);
		
		Object dateobj = request.getParameter("deadlinedate");
		String deadlinedatestring;
		if (dateobj instanceof String) {
			deadlinedatestring = (String)dateobj;
		} else {
			deadlinedatestring = "";
		}
		Optional<LocalDate> deadlinedate;
		DateValidator stringToLocalDate = new DateValidator();
		boolean localDateResult = stringToLocalDate.isValidDate(deadlinedatestring);
		
		Object todoListnumber = request.getParameter("todoListnumber");
		
		if (localDateResult) {
			//
			try {
				deadlinedate = Optional.ofNullable(LocalDate.parse(deadlinedatestring));
			} catch (Exception e) {
				System.out.println("EditRegServlet catch Exception");
				deadlinedate = null;
			}
			
			request.setAttribute("title", title);
			request.setAttribute("memo", memo);
			request.setAttribute("deadlinedate", deadlinedate);
			request.setAttribute("todoListnumber", todoListnumber);
			
		} else {
			//
			String errorMsg = "正しい形式で入力して下さい";
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edittasks.jsp");
			dispatcher.forward(request, response);
		}
	}

}
