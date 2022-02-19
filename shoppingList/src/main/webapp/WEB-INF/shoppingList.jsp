<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${name}</p> 
        <a href="ShoppingList?action=logout">Logout</a>
        
        <h2>List</h2>
        <form action="ShoppingList" method="POST">
            <input type="hidden" name="action" value="add">
            <label for="item">Item: </label>
            <input type="text" name="item" id="item">
            <button type="submit">Add</button>
        </form>
        <form action="ShoppingList" method="POST">
            <input type="hidden" name="action" value="delete">
            <table>
                <c:forEach items="${itemList}" var="item">
                    <tr>
                        <td>
                            <input type="radio" name="item" id="list-item" value="${item}">
                            <label for="list-item">${item}</label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit">Delete</button>
        </form>
            
        
    </body>
</html>
