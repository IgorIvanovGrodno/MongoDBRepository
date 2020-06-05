<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>REST</title>
</head>
<body>
    <h2>Books</h2>
    <table>
    <c:forEach var="book" items="${allBooks}">
        <tr>
            <td>${book.title}</td>
            <td>${book.year}</td>
            <td>
                <form:form action="/book/${book.id}" method="delete">
                    <input type="submit" value="Delete"/>
                </form:form>
            </td>
            <td>
                <form:form action="/book/${book.id}" method="get">
                    <input type="submit" value="Update"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>
    </table>

    <h2>Create new book:</h2>
    <form:form modelAttribute="newBook" action="/book/" method="put">
        <form:label path="title">Title</form:label>
        <form:input type="text" path="title"/>
        <form:label path="year">Year</form:label>
        <form:input type="number" path="year" maxlength="4" min="2000"/>
        <input type="submit" value="Create"/>
    </form:form>
</body>
</html>
