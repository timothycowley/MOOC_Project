<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="homepage.css">
<title>Create History</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<h1>Create History</h1>
	<form action="createhistory" method="post">
		<p>
			<label for="studentID">Student ID</label>
			<input id="studentID" name="studentID" value="">
		</p>
		<p>
			<label for="moocID">MOOC ID</label>
			<input id="moocID" name="moocID" value="">
		</p>
		<p>
			<label for="volunteerID">MOOC ID</label>
			<input id="volunteerID" name="volunteerID" value="">
		</p>
		<p>
			<label for="startdate">Start Date (YYYY-MM-DD)</label>
			<input id="startdate" name="startdate" value="">
		</p>
		<p>
			<label for="enddate">End Date (YYYY-MM-DD)</label>
			<input id="enddate" name="enddate" value="">
		</p>		
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>