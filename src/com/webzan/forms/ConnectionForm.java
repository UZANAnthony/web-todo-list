package com.webzan.forms;

import javax.servlet.http.HttpServletRequest;

public class ConnectionForm {
	private String result;
	
	public void checkId(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		
		if(pass.equals("1")) {
			result = "You are log";
		}
		else {
			result = "Id incorrect !";
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
