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
	<title>Class History</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
    <form action="history" method="post">
	    <h1>Search for Student</h1>
	    <p>
			<label for="historyid">by History ID</label>
			<input id="historyid" name="historyid" value="${fn:escapeXml(param.historyID)}">
		</p>
	    <p>
	        <label for="studentid">by Student ID</label>
	        <input id="studentid" name="studentid" value="${fn:escapeXml(param.studentID)}">
	    </p>
		<p>
			<label for="moocid">by MOOC ID</label>
			<input id="moocid" name="moocid" value="${fn:escapeXml(param.moocID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createhistory"><a href="createhistory">Create History</a></div>
	<br/>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>History ID</th>
                <th>Student ID</th>
                <th>MOOC ID</th>
                <th>Volunteer ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach items="${histories}" var="history" >
                <tr>
                    <td><c:out value="${history.getHistoryID()}" /></td>
                    <td><a href="findstudent?studentID=<c:out value="${history.getStudentID()}" />"><c:out value="${history.getStudentID()}"/></a></td>
                    <td><a href="schoolofferedclasses?moocid=<c:out value="${history.getMOOCID()}" />"><c:out value="${history.getMOOCID()}"/></a></td>
                    <td><c:out value="${history.getVolunteerID()}" /></td>
                    <td><c:out value="${history.getStartDate()}" /></td>
                    <td><c:out value="${history.getEndDate()}" /></td>
                    <td><a href="deletehistory?historyid=<c:out value="${history.getHistoryID()}" />">Delete</a></td>
                    <td><a href="updatehistory?historyid=<c:out value="${history.getHistoryID()}" />">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>