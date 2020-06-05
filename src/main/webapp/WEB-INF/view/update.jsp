<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REST</title>
</head>
<body>
<h2>Create new book:</h2>
<form:form modelAttribute="updateBook" action="/book/${updateBook.id}" method="post">
    <form:label path="title">Title</form:label>
    <form:input type="text" path="title"/>
    <form:label path="year">Year</form:label>
    <form:input type="number" path="year" maxlength="4" min="2000"/>
    <input type="submit" value="Update"/>
</form:form>
</body>
</html>
