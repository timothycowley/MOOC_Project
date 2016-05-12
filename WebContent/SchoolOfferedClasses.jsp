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
    <title>Classes</title>
    </head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<form action="schoolofferedclasses" method="post">
	    <h1>Search for MOOC</h1>
	    <p>
	        <label for="moocid">by MOOC ID</label>
	        <input id="moocid" name="moocid" value="${fn:escapeXml(param.studentID)}">
	    </p>
		<p>
			<label for="schoolid">by School ID</label>
			<input id="schoolid" name="schoolid" value="${fn:escapeXml(param.schoolID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createmooc"><a href="createmooc">Create MOOC</a></div>
	<br/>


	<h1>${messages.title}</h1>
        <table border="1" class="centered">
            <tr>
                <th>MOOC ID</th>
                
                
                <th>Name</th>
                <th>Description</th>
                <th>Source</th>
                <th>Direct Link</th>
                <th>Offered</th>
                <th>Difficulty</th>
                <th>Type</th>
                <th>Length</th>
                
                <th>Schools</th>
                <th>Volunteers</th>
                
                <th>Active Classes</th>
                <th>Previous Classes</th>
                
                <th>Delete</th>
                <th>Update</th>
                
            </tr>
            <c:forEach items="${moocs}" var="mooc" >
                <tr>
                    <td><c:out value="${mooc.getID()}" /></td>
                   <td><c:out value="${mooc.getName()}" /></td>
                   <td><c:out value="${mooc.getDescription()}" /></td>
                    <td><c:out value="${mooc.getSource()}" /></td>
                    <td><a href=<c:out value="${mooc.getDirect_Link()}" />><c:out value="${mooc.getDirect_Link()}" /></a></td>
                    <td><c:out value="${mooc.isOffered()}" /></td>
                    <td><c:out value="${mooc.getDifficulty()}" /></td>
                    <td><c:out value="${mooc.getType()}" /></td>
                    <td><c:out value="${mooc.getLength()}" /></td>
                    <td><a href="studentschool?moocid=<c:out value="${mooc.getID()}"/>">Schools</a></td>
                    <td><a href="schoolvolunteers?moocID=<c:out value="${mooc.getID()}"/>">Volunteers</a></td>
                    
                    <td><a href="schoolactiveclasses?moocid=<c:out value="${mooc.getID()}"/>">Active</a></td>
                    <td><a href="history?moocid=<c:out value="${mooc.getID()}"/>">Past</a></td>
                    
                    <td><a href="deletemooc?moocid=<c:out value="${mooc.getID()}"/>">Delete</a></td>
                    <td><a href="updatemooc?moocid=<c:out value="${mooc.getID()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>