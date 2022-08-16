<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Ride</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-ride-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Ride Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Ride</h3>
	
		<form:form action="saveRide" modelAttribute="ride" method="POST">
		<form:hidden path="id" />
			<!-- need to associate this data with ride id -->
		
			<table>
				<tbody>
					<tr>
						<td><label>startAddress</label></td>
						<td><form:input path="startAddress" /></td>
					</tr>
				
					<tr>
						<td><label>endAddress</label></td>
						<td><form:input path="endAddress" /></td>
					</tr>
						
					<tr>
						<td><label>User Id:</label></td>
						<td><form:input path="userId" /></td>
					</tr>
			
			
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/ride/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










