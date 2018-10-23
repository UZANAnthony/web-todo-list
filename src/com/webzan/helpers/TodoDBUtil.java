package com.webzan.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.webzan.beans.Todo;

public class TodoDBUtil {
	private DataSource dataSource;
	
	public TodoDBUtil(DataSource initDataSource) {
		dataSource = initDataSource;
	}
	
	public List<Todo> getTodoList() throws Exception{
		List<Todo> todolist = new ArrayList<Todo>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String query = "SELECT * FROM todo";
			myRs = myStmt.executeQuery(query);
			
			while(myRs.next()) {
				int id = myRs.getInt("id");
				String description = myRs.getString("description");
				
				Todo tmpTodo = new Todo(id, description);
				todolist.add(tmpTodo);
			}
			return todolist;
			
		}finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public void addTodo(Todo todo) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String query = "INSERT INTO todo (description) VALUES (?);";
			myStmt = myConn.prepareStatement(query);
			String description = todo.getDescription();
			myStmt.setString(1, description);
			myStmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public Todo fetchTodo(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Todo todo = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String query = "SELECT * FROM todo WHERE id="+id;
			myRs = myStmt.executeQuery(query);
			
			while(myRs.next()) {
				String description = myRs.getString("description");
				todo = new Todo(id, description);
			}
			return todo;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public void updateTodo(Todo todo) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String query = "UPDATE todo SET description = ? WHERE id = ?";
			myStmt = myConn.prepareStatement(query);
			myStmt.setString(1, todo.getDescription());
			myStmt.setInt(2, todo.getId());
			myStmt.execute();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			close(myConn,myStmt,null);
		}
	}
	
	public void deleteTodo(int id) {
		Connection myConn=null;
		Statement myStmt=null;
		try {
			myConn=dataSource.getConnection();
			myStmt=myConn.createStatement();
			String query = "DELETE FROM todo WHERE id="+id;
			myStmt.execute(query);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			close(myConn, myStmt, null);
		}
	}
		
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myStmt != null) myStmt.close();
			if(myRs != null) myRs.close();
			if(myConn != null) myConn.close();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
}
