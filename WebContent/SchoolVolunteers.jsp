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
     <!--  The lines above are always the same, just copy and past -->
 
      <!--  below is an arbitrary title to display on site-->
<title>Find a Volunteer</title>
</head>
<body>
  <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
  <!--  the code below matters, the purple id is always the same, the blue id is our parameter name, which is also include in param.id-->
	<form action="schoolvolunteers" method="post">
	    <h1>Search for Volunteer</h1>
		<p>
			<label for="volunteerID">by Volunteer ID</label>
			<input id="volunteerID" name="volunteerID" value="${fn:escapeXml(param.volunteerID)}">
		</p>
		<p>
			<label for="schoolID">by School ID</label>
			<input id="schoolID" name="schoolID" value="${fn:escapeXml(param.schoolID)}">
		</p>
		
		<p>
			<label for="moocID">by Mooc ID</label>
			<input id="moocID" name="moocID" value="${fn:escapeXml(param.moocID)}">
		</p>
		
		<p>
			<label for="companyID">by Company ID</label>
			<input id="companyID" name="companyID" value="${fn:escapeXml(param.companyID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createvolunteer"><a href="createvolunteer">Create Volunteer</a></div>
	<br/>
	  <!--  below is the output table for the matching student-->
	<h1>Matching Volunteer</h1>
        <table border="1" class="centered">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Association</th>
                <th>Delete Volunteer</th>
                <th>Update Volunteer</th>
            </tr>
            <c:forEach items="${volunteers}" var="volunteer" >
                <tr>
                
                  <!--  below are the values that go into the table we made above, note how they line up-->
                    <!--  we retrieve the values with getters-->
                    <td><c:out value="${volunteer.getID()}" /></td>
                    <td><c:out value="${volunteer.getFirst_Name()}" /></td>
                    <td><c:out value="${volunteer.getLast_Name()}" /></td>
                    <td><c:out value="${volunteer.getEmail()}" /></td>
                    <td><a href="schoolvolunteers?companyID=<c:out value="${volunteer.getAssociation()}"/>"><c:out value="${volunteer.getAssociation()}" /></a></td>
            <!--  here we can add links to other things like studentHistory or studentCurrentMOOCs -->
            <!--  below are links to other jsp files that allow deletes and updates -->
                    <td><a href="deletevolunteer?id=<c:out value="${volunteer.getID()}"/>">Delete</a></td>
                    <td><a href="updatevolunteer?id=<c:out value="${volunteer.getID()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
