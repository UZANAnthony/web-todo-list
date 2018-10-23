package com.webzan.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.webzan.beans.User;
import com.webzan.forms.ConnectionForm;
import com.webzan.helpers.UserDBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBUtil userDBUtil;
	
	@Resource(name="jdbc/webtodolist")
    private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userDBUtil = new UserDBUtil(dataSource);
	}
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userDBUtil.fetchUser(username);
		String role = user.getRole();
		
		ConnectionForm form = new ConnectionForm();
		form.checkId(request, user);
		request.setAttribute("form", form);
		
		if(form.getResult()) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", role);
			response.sendRedirect("TodoControllerServlet");
		}
		else {
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
