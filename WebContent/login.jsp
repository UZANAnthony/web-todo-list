<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="style.css"/>
<title>Login</title>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>WEBZAN CORPORATION</h2>
	</div>
</div>
	<p>
		<c:if test="${ !empty form.result }">
			<p>Incorrect username or password !</p>
		</c:if>
	</p>

	<form method="post" action="LoginServlet">
		<p>
		<label for="username">Username : </label>
		<c:if test="${ !empty username }">
			<input type="text" id="username" name="username" value="${ username }"/>
		</c:if>
		<c:if test="${ empty username }">
			<input type="text" id="username" name="username"/>
		</c:if>
		</p>
		
		<p>
		<label for="pass">Password : </label>
		<input type="password" id="pass" name="pass"/>
		</p>
		<p>
		<input type="submit" value="Login"/>
		</p>
	</form>
</body>
</html>