<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://caloriesmanagement.vitalie.com/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal
        {
            color: green;
        }
        .exceede
        {
            color: red;
        }
    </style>
</head>
<body>
    <section>
        <h3><a href="index.html">Home</a></h3>
        <h2>Meals</h2>
        <hr/>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calorties</th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="com.vitalie.caloriesmanagement.model.MealWithExceed" />
                <tr class="${meal.exceeded ? 'exceede' : 'normal'}">
                    <td>${fn:formatDateTime(meal.dateTime)}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
</body>
</html>
