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
<title>Update a Company</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<h1>Update Company's Location</h1>
	<form action="updatecompany" method="post">
		<p>
			<label for="companyid">Company ID</label> <input id="companyid"
				name="companyid" value="${fn:escapeXml(param.companyid)}">
		</p>
		<p>
			<label for="newcity">New City</label> <input id="newcity"
				name="newcity" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br />
	<br />
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>