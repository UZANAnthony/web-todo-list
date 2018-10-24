package com.webzan.forms;

import javax.servlet.http.HttpServletRequest;

import com.webzan.beans.User;

public class ConnectionForm {
	private boolean result;
	
	public void checkId(HttpServletRequest request, User user) {
		String pass = request.getParameter("pass");
		
		if(user == null) {
			result = false;
		}
		else {
			if(pass.equals(user.getPassword())) {
				result = true;
			}
			else {
				result = false;
			}
		}
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}
