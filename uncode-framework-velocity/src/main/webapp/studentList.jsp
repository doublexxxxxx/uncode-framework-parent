<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="includeJS.jsp" %>
	<script type="text/javascript">
		var pageConfig = {
				"list" : {
					"action" : "studentAction!studentList.action"
				},
				"add" : {
					"dialogTitle" : "Student Add",
					"action" : "studentAction!studentAdd.action",
					"toAction" : "studentAction!toStudentAdd.action",
					"errorMessage" : "Add Error!"
				},
				"modify" : {
					"dialogTitle" : "Student Modify",
					"action" : "studentAction!studentModify.action",
					"toAction" : "studentAction!toStudentModify.action",
					"errorMessage" : "Modify Error!"
				},
				"delete" : {
					"action" : "studentAction!studentDelete.action",
					"errorMessage" : "Delete Error!"
				},
				"idName" : "studentId"
		};
	</script>
	<script type="text/javascript" src="common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student List</title>
</head>
<body>
<h1>Student List</h1>
<div><button id="addButton">Add</button></div>
<table border="1" style="width: 100%">
	<thead>
		<tr><th>No.</th><th>Name</th><th>Age</th><th>Height</th><th>Weight</th><th>Operate</th></tr>
	</thead>
	<tbody>
		<c:forEach var="student" items="${students }" varStatus="s" >
			<tr studentId="${student.id }"><td>${s.index+1 }</td><td>${student.name }</td><td>${student.age }</td><td>${student.height }</td><td>${student.weight }</td><td><button class="modifyButton">Modify</button><button class="deleteButton">Delete</button></td></tr>
		</c:forEach>
	</tbody>
</table>
<div id="modifyDiv"></div>
<div id="addDiv"></div>
</body>
</html>