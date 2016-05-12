<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="homepage.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Active Classes</title>
</head>
<body>
<img alt="Shoot For the Mooc" src="logo.png" class="rounded">
<form action="schoolactiveclasses" method="post">
	    <h1>Search for Active Class</h1>
	    <p>
	        <label for="activeid">by Active ID</label>
	        <input id="activeid" name="activeid" value="${fn:escapeXml(param.activeID)}">
	    </p>
	    <p>
	        <label for="moocid">by MOOC ID</label>
	        <input id="moocid" name="moocid" value="${fn:escapeXml(param.moocID)}">
	    </p>
		<p>
			<label for="schoolid">by School ID</label>
			<input id="schoolid" name="schoolid" value="${fn:escapeXml(param.schoolID)}">
		</p>
		<p>
			<label for="studentid">by Student ID</label>
			<input id="studentid" name="studentid" value="${fn:escapeXml(param.studentID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createactiveclass"><a href="createactiveclass">Create Active Class</a></div>
	<br/>


	<h1>${messages.title}</h1>
        <table border="1" class="centered">
            <tr>
                <th>Active ID</th>
                <th>Student ID</th>
                <th>MOOC ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Delete Active Class</th>
                <th>Update Active Class</th>
            </tr>
            <c:forEach items="${activeclasses}" var="activeclass" >
                <tr>
                    <td><c:out value="${activeclass.getActiveID()}" /></td>
                    <td><a href="findstudent?studentID=<c:out value="${activeclass.getStudentID()}" />"><c:out value="${activeclass.getStudentID()}"/></a></td>
                    <td><a href="schoolofferedclasses?moocid=<c:out value="${activeclass.getMOOCID()}" />"><c:out value="${activeclass.getMOOCID()}"/></a></td>
                    <td><c:out value="${activeclass.getStartDate()}" /></td>
                    <td><c:out value="${activeclass.getEndDate()}" /></td>
                    <td><a href="deleteactiveclass?activeid=<c:out value="${activeclass.getActiveID()}" />">Delete</a></td>
                    <td><a href="updateactiveclass?activeid=<c:out value="${activeclass.getActiveID()}" />">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>

