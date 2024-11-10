package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		
//		Object completeObj = request.getParameter("complete");
//		String complete;
//		if (completeObj instanceof String) {
//			complete = (String)completeObj;
//		} else {
//			complete = null;
//		}
		// 最初にnullを用意しておいて、もし前jspファイルからcompleteが含まれている変数を
		// を貰った場合の変数。その時はcompleteをtrueにする。
		// この変数を何故用意したか？前jspファイルで完了ボタンを押したときに、
		// このサーブレットファイルで削除処理をするか？を判定する変数。
		boolean complete = false;
		
		// 検証用（後で消す）
		Map<String, String[]> map = request.getParameterMap();
		System.out.println(map.keySet());
		
		// リクエストパラメータ名を取得
		Enumeration<String> paramNames = request.getParameterNames();
		Iterator<String> it = paramNames.asIterator();
		String Number = "";
		while (it.hasNext()) {
			String e = it.next();
			if (e.startsWith("edit")) {
				Number = e.substring(4);
				System.out.println("現在のNumberは" + Number + "です");
			}
			if (e.startsWith("complete")) {
				Number = e.substring(12);
				complete = true;
			}
		}
//		 パラメータ名をループで処理
//		while (paramNames.hasMoreElements()) {
//			String paramName = paramNames.nextElement();
//			System.out.println(paramName);
			// editで始まるパラメータを処理
//			if (paramName.startsWith("edit")) {
				// editの後の数字を取得
//				System.out.println(paramName);
//				Number = paramName.substring(4);
//			}
//		}
		
		// パラメータの値を受け取る
//		String Number = request.getParameter("edit1");
//		System.out.println(Number);
		
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
		
		if (complete == true) {
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
