<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action= "noteServlet" method="post">
	Name: <input type="text"name= "userName">
	description: <input type="text" name="description">
	target date: <input type="text" name="target_date">
	accomplish: <select name ="accomplish">
		<option>yes</option>
		<option>no</option>
	</select>
	<input type="submit" value="call Servlet"/>
	</form>
</body>
</html>