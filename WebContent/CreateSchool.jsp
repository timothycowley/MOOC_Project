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
<title>Create a School</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<h1>Create School</h1>
	<form action="createschool" method="post">
		<p>
			<label for="name">Name</label>
			<input id="name" name="name" value="">
		</p>
		<p>
			<label for="participation">Participation Status</label>
			<input id="participation" name="participation" value="">
		</p>
		<p>
			<label for="contacted">Contacted?</label>
			<input id="contacted" name="contacted" value="">
		</p>
		<p>
			<label for="pocname">POC Name</label>
			<input id="pocname" name="pocname" value="">
		</p>
		<p>
			<label for="pocemail">POC Email</label>
			<input id="pocemail" name="pocemail" value="">
		</p>
		<p>
			<label for="location">Location</label>
			<input id="location" name="location" value="">
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