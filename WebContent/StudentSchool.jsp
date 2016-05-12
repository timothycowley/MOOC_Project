<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="homepage.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>School</title>
</head>
<body>
	<img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<div>
		<img src="SchoolQualityHeatMap.jpg" alt="SchoolQualityHeatMap">
	</div>
	<form action="studentschool" method="post">
		<h1>Search for School</h1>
		<p>
			<label for="schoolid">by School ID</label> <input id="schoolid"
				name="schoolid" value="${fn:escapeXml(param.schoolid)}">
		</p>
		<p>
			<label for="moocid">by MOOC ID </label> <input id="moocid"
				name="moocid" value="${fn:escapeXml(param.moocid)}">
		</p>
		<p>
			<input type="submit"> <br />
			<br />
			<br /> <span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br />
	<br />
	<!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createschool">
		<a href="createschool">Create School</a>
	</div>
	<br />

	<h1>${messages.title}</h1>
	<table border="1" class="centered">
		<tr>
			<th>School ID</th>
			<th>Name</th>
			<th>Participating?</th>
			<th>Contacted?</th>
			<th>POC Name</th>
			<th>POC Email</th>
			<th>Location</th>
			<th>Offered Classes</th>
			<th>Active Classes</th>
			<th>Students</th>
			<th>Volunteers</th>
			<th>Delete School</th>
			<th>Update School</th>
		</tr>
		<c:forEach items="${schools}" var="school">
			<tr>
				<td><c:out value="${school.getID()}" /></td>
				<td><c:out value="${school.getName()}" /></td>
				<td><c:out value="${school.isParticipation()}" /></td>
				<td><c:out value="${school.isContacted()}" /></td>
				<td><c:out value="${school.getPOC_Name()}" /></td>
				<td><c:out value="${school.getPOC_Email()}" /></td>
				<td><c:out value="${school.getCity()}" /></td>
				<td><a
					href="schoolofferedclasses?schoolid=<c:out value="${school.getID()}"/>">Classes</a></td>
				<td><a
					href="schoolactiveclasses?schoolid=<c:out value="${school.getID()}"/>">Classes</a></td>
				<td><a
					href="findstudent?schoolID=<c:out value="${school.getID()}"/>">Students</a></td>
				<td><a
					href="schoolvolunteers?schoolID=<c:out value="${school.getID()}"/>">Volunteers</a></td>
				<td><a
					href="deleteschool?id=<c:out value="${school.getID()}"/>">Delete</a></td>
				<td><a
					href="updateschool?id=<c:out value="${school.getID()}"/>">Update</a></td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>