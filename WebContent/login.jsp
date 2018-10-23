<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>WEBZAN CORPORATION</h2>
	</div>
</div>
	<c:if test="${ !empty form.result }">
		<p>Incorrect username or password !</p>
	</c:if>

	<form method="post" action="LoginServlet">
		<label for="username">Username : </label>
		<input type="text" id="username" name="username"/>
		
		<label for="pass">Password : </label>
		<input type="password" id="pass" name="pass"/>
		
		<input type="submit" value="Login"/>
	</form>
</body>
</html>