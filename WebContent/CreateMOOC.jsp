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
<title>Add a MOOC</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<h1>Add a MOOC</h1>
	<form action="createmooc" method="post">
		<p>
			<label for="name">Name</label>
			<input id="name" name="name" value="">
		</p>
		<p>
			<label for="description">Description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="source">Source</label>
			<input id="source" name="contacted" value="">
		</p>
		<p>
			<label for="directlink">Web Link</label>
			<input id="directlink" name="directlink" value="">
		</p>
		<p>
			<label for="offered">Offered</label>
			<input id="offered" name="offered" value="">
		</p>
		<p>
			<label for="difficulty">Difficulty</label>
			<input id="difficulty" name="difficulty" value="">
		</p>
		<p>
			<label for="type">Type</label>
			<input id="type" name="type" value="">
		</p>
		<p>
			<label for="length">Length in Weeks</label>
			<input id="length" name="length" value="">
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