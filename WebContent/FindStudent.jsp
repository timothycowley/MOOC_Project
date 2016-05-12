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
 <!--  The lines above are always the same, just copy and past -->
 
  <!--  below is an arbitrary title to display on site-->
<title>Student</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
  <!--  the code below matters, the purple id is always the same, the blue id is our parameter name, which is also include in param.id-->
	<form action="findstudent" method="post">
	    <h1>Search for Student</h1>
	    <p>
	        <label for="studentID">by Student ID</label>
	        <input id="studentID" name="studentID" value="${fn:escapeXml(param.studentID)}">
	    </p>
		<p>
			<label for="schoolID">by School ID</label>
			<input id="schoolID" name="schoolID" value="${fn:escapeXml(param.schoolID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createstudent"><a href="createstudent">Create Student</a></div>
	<br/>
	  <!--  below is the output table for the matching student-->
	<h1>Matching Students</h1>
        <table border="1" class="centered">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>School ID</th>
                <th>Class</th>
                <th>Current Classes</th>
                <th>Class History</th>
                <th>Delete Student</th>
                <th>Update Student</th>
            </tr>
            <c:forEach items="${students}" var="student" >
                <tr>
                
                  <!--  below are the values that go into the table we made above, note how they line up-->
                    <!--  we retrieve the values with getters-->
                    <td><c:out value="${student.getID()}" /></td>
                    <td><c:out value="${student.getFirst_Name()}" /></td>
                    <td><c:out value="${student.getLast_Name()}" /></td>
                    <td><c:out value="${student.getEmail()}" /></td>
                    <td><a href="studentschool?schoolid=<c:out value="${student.getSchoolID()}" />"><c:out value="${student.getSchoolID()}" /></a></td>
                    <td><c:out value="${student.getYear()}" /></td>
            <!--  here we can add links to other things like studentHistory or studentCurrentMOOCs -->
            <!--  below are links to other jsp files that allow deletes and updates -->
            		<td><a href="schoolactiveclasses?studentid=<c:out value="${student.getID()}"/>">Classes</a></td>
            		<td><a href="history?studentid=<c:out value="${student.getID()}"/>">Past Classes</a></td>
                    <td><a href="deletestudent?id=<c:out value="${student.getID()}"/>">Delete</a></td>
                    <td><a href="updatestudent?id=<c:out value="${student.getID()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
