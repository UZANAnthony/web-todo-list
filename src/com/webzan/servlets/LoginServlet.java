package com.webzan.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		//User user = userDBUtil.fetchUser(username);
		
		ConnectionForm form = new ConnectionForm();
		form.checkId(request);
		request.setAttribute("form", form);
		
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		
		//System.out.println(user.getPassword());
		//System.out.println(password);
		//boolean res = (password == user.getPassword().toString());
		//System.out.println(res);
		/*if(password.toString() == user.getPassword().toString()) {
			System.out.println("TRUE");
		}
		else {
			System.out.println("FALSE");
		}*/
		
	}

}
