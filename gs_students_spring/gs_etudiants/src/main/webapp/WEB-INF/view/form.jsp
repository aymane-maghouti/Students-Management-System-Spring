<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>registration form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<style>
h3 {
	margin-top: 20px;
}

#navbarNav div {
	height: 0;
}

#navbarNav form {
	margin: 0;
	padding: 0;
}

form {
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 10px;
}


</style>

</head>
<body>
	
	<div>
		<h3>Registration Form</h3>
	</div>
	
	<div>
	<c:if test="${infoMsg!=null}">
				<div class="alert alert-success" role="alert">${infoMsg}</div>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
	</c:if>
	
	<f:form action="${pageContext.request.contextPath}/addStudent" method="POST" modelAttribute="studentModel">
		<f:input path="id" type="hidden"/>
		
		
		
		
		<div class="row">
			<div class="col">
				<label>CNE</label>
				<f:input path="cne" type="text" class="form-control" placeholder="CNE" />
				<f:errors path="cne" class="text-danger" />
	
			</div>
			
			<div class="col">
				<label>Nom</label>
				<f:input path="nom" type="text" class="form-control"
					placeholder="Nom" />
				<f:errors path="nom" class="text-danger" />
			</div>
			
			<div class="col">
				<label>Prenom</label>
				<f:input path="prenom" type="text" class="form-control"
					placeholder="Prenom" />
				<f:errors path="prenom" class="text-danger" />
			</div>
		</div>
		<div class="row">	
			
			<div class="col">
				<label>Addresse</label>
				<f:input path="addresse" type="text" class="form-control"
					placeholder="...., Morocco" />
				<f:errors path="addresse" class="text-danger" />
			</div>
			
			
			<div class="col">
				<label>Email</label>
				<f:input path="email" class="form-control" placeholder="Email" />
				<f:errors path="email" class="text-danger" />
			</div>

			<div class="col">
				<label>Level</label>
				<f:select path="Level" items="${levelList}" class="form-control" />
				<f:errors path="Level" class="text-danger" />
			</div>
			
		</div>
		
		<div class="col">
			<legend class="col-form-label col-sm-2 pt-0">Gender</legend>
			<div class="form-check">
				<f:radiobutton path="gender" class="form-check-input"
					value="Female" />
				<label class="form-check-label">Female </label>
			</div>
			<div class="form-check">
				<f:radiobutton path="gender" class="form-check-input"
					value="Male " />
				<label class="form-check-label">Male </label>
			</div>
			<f:errors path="gender" class="text-danger" />

		</div>
		
		<br>
		
		
		
		<div style="text-align: right">
			<button type="submit" class="btn btn-primary">Register</button>
		</div>	
		
		
	</f:form>
	
	</div>
	
	
		<div>
			<h3>List of Persons</h3>
		</div>

		<div>

			<table class="table">
				<thead>
					<tr>
						<th scope="col">CNE</th>
						<th scope="col">Prenom</th>
						<th scope="col">Nom</th>
						<th scope="col">Email</th>
						<th scope="col">Address</th>
						<th scope="col">gender</th>
						<th scope="col">Level</th>
						
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<c:forEach items="${studentList}" var="s">
					<tr>
						<td><c:out value="${s.cne}" /></td>
						<td><c:out value="${s.prenom}" /></td>
						<td><c:out value="${s.nom}" /></td>
						<td><c:out value="${s.email}" /></td>
						<td><c:out value="${s.addresse}" /></td>
						<td><c:out value="${s.gender}" /></td>
						<td><c:out value="${s.level}" /></td>
						
	
						<td>
							<ul>
								<li><a href="${pageContext.request.contextPath}/deleteStudent/${s.id}">Delete</a></li>

								<li><a href="${pageContext.request.contextPath}/updateStudentForm/${s.id}">Update</a></li>
							</ul>
						</td>

					</tr>

				</c:forEach>

			</table>
		</div>
</body>
</html>