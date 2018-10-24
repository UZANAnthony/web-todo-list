package com.webzan.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.webzan.beans.User;

public class UserDBUtil {
	private DataSource dataSource;
	
	public UserDBUtil(DataSource initDataSource) {
		dataSource = initDataSource;
	}
	
	public boolean userExists(String username) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String query = "SELECT count(username) as res FROM user WHERE username=\""+username+"\"";
			myRs = myStmt.executeQuery(query);
			myRs.next();
			if(myRs.getInt("res") == 0) {
				return false;
			}
			else {
				return true;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public User fetchUser(String username){
		
		if(!userExists(username)) {
			return null;
		}
		else {
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			User user = null;
			
			try {
				myConn = dataSource.getConnection();
				myStmt = myConn.createStatement();
				String query = "SELECT * FROM user WHERE username=\""+username+"\"";
				myRs = myStmt.executeQuery(query);
				
				while(myRs.next()) {
					String password = myRs.getString("password");
					String role = myRs.getString("role");
					user = new User(username, password, role);
				}
				return user;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}finally {
				close(myConn,myStmt,myRs);
			}
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
