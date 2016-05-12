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
<title>Find a Company</title>
</head>
<body>
    <img alt="Shoot For the Mooc" src="logo.png" class="rounded">
 	<br/>
		<form action="findcompanies" method="post">
	    <h1>Search for Company</h1>
	    <p>
	        <label for="companyid">by Company ID</label>
	        <input id="companyid" name="companyid" value="${fn:escapeXml(param.companyID)}">
	    </p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	  <br/>
	  <!--  below is a link to another jsp that allows creation of a new student-->
	<div id="createcompany"><a href="createcompany">Create Company</a></div>
	<br/>
	  <!--  below is the output table for the matching company-->
	<h1>Matching Companies</h1>
        <table border="1" class="centered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
                <th>Website</th>
                <th>Volunteers Employeed Here</th>
                <th>Delete Company</th>
                <th>Update Company</th>
            </tr>
            <c:forEach items="${companies}" var="companies" >
                <tr>
                
                  <!--  below are the values that go into the table we made above, note how they line up-->
                    <!--  we retrieve the values with getters-->
                    <td><c:out value="${companies.getID()}" /></td>
                    <td><c:out value="${companies.getName()}" /></td>
                    <td><c:out value="${companies.getLocation()}" /></td>
                    <td><c:out value="${companies.getWebsite()}" /></td>
            <!--  below are links to other jsp files that allow deletes and updates -->
            		<!--<td><a href="findcompanies?id=<c:out value="${company.getID()}"/>">Company</a></td>-->
            		<td><a href="schoolvolunteers?companyID=<c:out value="${companies.getID()}"/>">Volunteers</a></td>
                    <td><a href="deletecompany?companyid=<c:out value="${companies.getID()}"/>">Delete</a></td>
                    <td><a href="updatecompany?companyid=<c:out value="${companies.getID()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
     
</body>
</html>




 
 
