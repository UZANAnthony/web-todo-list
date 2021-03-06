<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css"/>
<title>Add a Todo</title>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>WEBZAN CORPORATION</h2>
	</div>
</div>
<p><c:out value="Welcome ${ sessionScope.username } !"/></p>

<c:if test="${ sessionScope.role == 'instructor' }">
	<div id="container">
	<h3>Add New Todo</h3>
	<form action="TodoControllerServlet" method="post">
		<table>
			<tbody>
				<tr>
					<td><label>Description : </label></td>
					<td><input type="text" name="description"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save"/></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="clear:both;"></div>
	<p><a href="TodoControllerServlet">Back to List</a></p>
	<p><a href="LoginServlet">Logout</a></p>

</div>
</c:if>

<c:if test="${ sessionScope.role != 'instructor' }">
	Access denied !
	<p><a href="TodoControllerServlet">Back to List</a></p>
	<p><a href="LoginServlet">Logout</a></p>
</c:if>




</body>
</html>