<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allDepartments</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<form action="departments" method="post">

				<input type="text" name="name" /> <input type="submit"
					value="Add Department" class="button" />
			</form>

			<table>

				<tr>
					<th>Name</th>
					<th>Subjects</th>
					<th>Teachers</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="department" items="${departments}">

					<c:url var="departmentLink" value="department">
						<c:param name="departmentId" value="${department.id}" />
					</c:url>



					<tr>
						<td><a href="${departmentLink}">${department.name}</a></td>
						<td>${fn:length(department.subjects)}</td>
						<td>${fn:length(department.teachers)}</td>

						<td><form action="department/delete" method="post">

								<input type="hidden" name="departmentId"
									value="${department.id}" /> <input type="submit"
									value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this department?'))) return false" />
							</form></td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
	<p>
		<a href="index.html">Back to University</a>
	</p>

</body>

</html>