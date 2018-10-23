package com.webzan.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.webzan.helpers.TodoDBUtil;


@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDBUtil;
	
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException{
		super.init();
		todoDBUtil = new TodoDBUtil(dataSource);
	}
       

    public DeleteTodoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("todoId"));
		todoDBUtil.deleteTodo(id);
		response.sendRedirect("TodoControllerServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
