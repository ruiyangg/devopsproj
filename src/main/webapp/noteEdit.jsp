<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>note Management Application</title>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> note Management Application </a>
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/notesServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${note != null}">
<form action="update" method="post">
</c:if>
<c:if test="${note == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${note != null}">
Edit note

</c:if>
<c:if test="${note == null}">
Add New note
</c:if>
</h2>
</caption>
<c:if test="${note != null}">
<input type="hidden" name="oriName" value="<c:out
value='${note.name}' />" />
</c:if>
<fieldset class="form-group">
<label>note Name</label> <input type="text" value="<c:out
value='${note.name}' />" class="form-control" name="name" required="required">
</fieldset>
<fieldset class="form-group">
<label>description</label> <input type="text" value="<c:out
value='${note.description}' />" class="form-control" name="description">
</fieldset>
<fieldset class="form-group">
<label>target_date</label> <input type="text" value="<c:out
value='${note.target_date}' />" class="form-control" name="target_date">
</fieldset>
<fieldset class="form-group">
<label> accomplish</label> <input type="text" value="<c:out
value='${note.accomplish}' />" class="form-control" name="accomplish">
</fieldset>
<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>