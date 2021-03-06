<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Faculty</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Faculty</h3>


		<form action="faculty" method="post">
			<input type="hidden" name="facultyId" value="${faculty.id}" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${faculty.id}</td>
					<td>${faculty.name}</td>
					<td><input type="text" name="name" value="${faculty.name}" /></td>
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>

			</table>
		</form>







		<h3>Departments</h3>

		<c:if test="${not empty departmentsWithoutFaculty}">

			<form action="faculty/addDepartment" method="post">


				<select name="departmentId" class="button">
					<c:forEach var="department" items="${departmentsWithoutFaculty}">
						<option value="${department.id}">${department.name}</option>
					</c:forEach>
				</select> <input type="hidden" name="facultyId" value="${faculty.id }" /> <input
					type="submit" value="Add Department" class="button" />

			</form>
		</c:if>

		<table>

			<tr>
				<th>Departments</th>
				<th>Delete</th>

			</tr>


			<c:forEach var="department" items="${faculty.departments}">

				<c:url var="departmentLink" value="department">
					<c:param name="departmentId" value="${department.id}" />
				</c:url>



				<tr>
					<td><a href="${departmentLink}">${department.name}</a></td>
					<td><form action="faculty/deleteDepartment" method="post">

							<input type="hidden" name="facultyId" value="${faculty.id}" /> <input
								type="hidden" name="departmentId" value="${department.id}" /> <input
								type="submit" value="Delete" class="button"
								onclick="if (!(confirm('Are you sure you want to delete this department?'))) return false" />
						</form></td>
				</tr>
			</c:forEach>
		</table>







		<h3>Groups</h3>


		<c:if test="${not empty groupsWithoutFaculty}">
			<form action="faculty/addGroup" method="post">


				<select name="groupId" class="button">
					<c:forEach var="group" items="${groupsWithoutFaculty}">
						<option value="${group.id}">${group.name}</option>
					</c:forEach>
				</select> <input type="hidden" name="facultyId" value="${faculty.id }" /> <input
					type="submit" value="Add Group" class="button" />

			</form>
		</c:if>

		<table>

			<tr>
				<th>Groups</th>
				<th>Delete</th>

			</tr>


			<c:forEach var="group" items="${faculty.groups}">

				<c:url var="groupLink" value="group">
					<c:param name="groupId" value="${group.id}" />
				</c:url>

				<c:url var="deleteGroupLink" value="facultyGroup">
					<c:param name="groupId" value="${group.id}" />
					<c:param name="facultyId" value="${faculty.id}" />
				</c:url>

				<tr>
					<td><a href="${groupLink}">${group.name}</a></td>
					<td><form action="faculty/deleteGroup" method="post">

							<input type="hidden" name="facultyId" value="${faculty.id}" /> <input
								type="hidden" name="groupId" value="${group.id}" /> <input
								type="submit" value="Delete" class="button"
								onclick="if (!(confirm('Are you sure you want to delete this group?'))) return false" />
						</form></td>
				</tr>
			</c:forEach>
		</table>


	</div>

	<p>
		<a href="faculties">Back to list of all departments</a>
	</p>

</body>
</html>