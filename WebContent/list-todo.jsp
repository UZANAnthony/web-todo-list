<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="style.css"/>
<title>Web Todo Tracker</title>
</head>

<body>
<div id="wrapper">
	<div id="header">
		<h2>WEBZAN CORPORATION</h2>
	</div>
</div>

<div id="container">
	<div id="content">
		
		<p><c:out value="Bienvenu ${ sessionScope.username } !"/></p>
		
		<p>
		<c:if test="${ sessionScope.role == 'instructor' }">
			<form action="AddTodoServlet" method="get">
				<input type="submit" value="Add Todo"/>
			</form>
		</c:if>
		</p>
		
		<table>
			<tr>
				<th>Id</th>
				<th>Description</th>
			</tr>
			<c:forEach var="tmpTodo" items="${ TODO_LIST }">
			
				<c:url var="EditLink" value="EditTodoServlet">
					<c:param name="todoId" value="${ tmpTodo.id }"/>
				</c:url>
				<c:url var="DeleteLink" value="DeleteTodoServlet">
					<c:param name="todoId" value="${ tmpTodo.id }"/>
				</c:url>
				
				<tr>
					<td> ${ tmpTodo.id } </td>
					<td> ${ tmpTodo.description } </td>
					<c:if test="${ sessionScope.role == 'instructor' }">
						<td> <a href="${ EditLink }">Edit</a>|<a href="${ DeleteLink }">Delete</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
		<p><a href="LoginServlet">Logout</a></p>
	</div>
</div>
</body>
</html>