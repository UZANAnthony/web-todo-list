package com.webzan.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.webzan.beans.Todo;
import com.webzan.helpers.TodoDBUtil;

@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private TodoDBUtil todoDBUtil;
    
	@Resource(name="jdbc/webtodolist")
    private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoDBUtil = new TodoDBUtil(dataSource);
	}
	
	public TodoControllerServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listTodos(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String desc = request.getParameter("description");
		Todo todo = new Todo(desc);
		todoDBUtil.addTodo(todo);
		try {
			listTodos(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void listTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Todo> todolist = todoDBUtil.getTodoList();
		request.setAttribute("TODO_LIST", todolist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-todo.jsp");
		dispatcher.forward(request, response);
	}

}
