<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="homepage.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="homepage.css">
<title>Summary</title>
</head>
<body>
	<img alt="Shoot For the Mooc" src="logo.png" class="rounded">
	<h1>Summary and Final Thoughts</h1>
	<div style="text-align: left;">
		<p>
			The final result of our project fulfilled all major elements of our
			value proposition as we did create a system by which high schoolers
			would be able to sign up for MOOCs related to the computer science
			field. <br /> We were able to meet most of our deliverables in that
			we provided:<br />
		<ul>
			<li>Basic frontend and backend</li>
			<li>Mock profiles for students</li>
			<li>Volunteer teachers</li>
			<li>Businesses</li>
			<li>A small number of different academic paths which require
				other courses</li>
		</ul>
		<br /> We did not get around to developing a project offering side as
		we felt that this was most difficult to simulate as our idea about the
		source of projects would be the companies themselves. <br /> <br />
		As to what went well in the project, being able to implement basic
		functionality for each of the milestones as well as getting all of
		different layers of code to interact went generally fairly smooth. <br />
		<br /> However, there were many things that did not go as smoothly as
		we expected it to. Due to our choice of project it was difficult to
		obtain actual student data due to its sensitive nature. <br /> <br />
		Also it might has been useful to discuss our idea with potential
		schools and companies in order to get more information about what both
		sides would want. In addition, our planning for what we were actually
		going to deliver should made been better thought out as we
		overestimated what we would be able to achieve. <br /> <br /> This,
		in conjunction with spending more time on the project, would have
		yielded a more comprehensive product. <br /> <br /> We also feel
		that we should have spent more time working on the database
		architecture like what we need to represent and in what tables we
		needed. This happened when we had to refactor the Volunteer and
		Companies table to form a City_County table. <br /> <br /> We also
		might have wanted to refactor the active classes table to treat active
		class as a unique class entry and then make a separate table linking
		students to the active classes. This also would have simplified our
		history table and made analysis on student trends and overall progress
		much easier.
		</p>
		<h1 class="centered">UMLs</h1>
		<div>
			<div style="float: left">
				<h2>Old UML</h2>
				<img alt="Old UML" src="OldUML.jpg" style="width: 500px; height: 500px; margin-right: 20px">
			</div>
			<div style="float: left">
				<h2>New UML</h2>
				<img alt="New UML" src="FinalUML.jpg" style="width: 500px; height: 500px">
			</div>
		</div>
	</div>

</body>
</html>
