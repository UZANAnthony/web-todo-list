package com.webzan.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.webzan.beans.Todo;
import com.webzan.helpers.TodoDBUtil;


@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoDBUtil todoDBUtil;
	
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
	
	int id;
	
	@Override
	public void init() throws ServletException{
		super.init();
		todoDBUtil = new TodoDBUtil(dataSource);
	}
       
    public EditTodoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("todoId"));
		Todo todo = todoDBUtil.fetchTodo(id);
		request.setAttribute("Todo", todo);
		request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		Todo todo = new Todo(id, description);
		todoDBUtil.updateTodo(todo);
		response.sendRedirect("TodoControllerServlet");
	}

}
