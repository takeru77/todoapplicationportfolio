package filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/tasktodo.jsp", "/DeleteAccount", "/RegisterServlet4", "/EditRegServlet", "/DeleteTask", "/Logout", "/NewTaskAdd"})
public class SessionCheckFilter extends HttpFilter {

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession(false);
		
		if (session == null) {
			res.sendRedirect("index.html");
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}

}
