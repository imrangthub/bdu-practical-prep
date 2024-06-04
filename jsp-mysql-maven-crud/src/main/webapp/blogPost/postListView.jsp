<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Posts List</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 10px;
	text-align: left;
}
</style>
</head>
<body>
	<h1>Posts List</h1>
	<a href="home">Home</a>  
	<a href="blog-create">Create Post</a>  
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Body</th>
				<th>Image</th>
				<th>Category</th>
				<th>Created At</th>
				<th>Modified At</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${postModelList}">
				<tr>
					<td>${post.id}</td>
					<td>${post.postTitle}</td>
					<td>${post.postBody}</td>
					<td><img src="${pageContext.request.contextPath}/resources/imgFolder/${post.postImage}" width="150px" height="150px"/></td>
					<td>${post.category}</td>
					<td>${post.create_at}</td>
					<td>${post.modify_at}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>