<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>

        <p>
            ${Message}
            </p>

            <table>
                <tr>
                    <td>Email</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Role</td>
                    <td> </td>
                    <td> </td>
                </tr>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.roleName}</td>
                    <td>
                        <a href="<c:url value='/users?action=edit&amp;'>
                           <c:param name='email' value='${user.email}'/>  
                           </c:url>">Edit
                        </a>
                    </td> 
                    <td>                            
                        <a href="<c:url value='/users?action=delete&amp;'>
                           <c:param name='email' value='${user.email}'/>  
                           </c:url>">Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>


        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" value="${email}"><br>
                First name: <input type="text" name="firstName" value="${firstName}"><br>
                Last name: <input type="text" name="lastName" value="${lastName}"><br>
                Password: <input type="password" name="password" value="${password}"><br>
                Role<select name = "role">
                    <option value="1">system admin</option>
                    <option value="2">regular user</option>
                </select>
                <br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add user">
            </form>
        </c:if>

        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2>
            <form action="users" method="post">
                Email: ${selectedUser.email}<br>
                First name: <input type="text" name="firstName" value="${selectedUser.firstName}"><br>
                Last name: <input type="text" name="lastName" value="${selectedUser.lastName}"><br>
                Password: <input type="password" name="password" value=""><br>
                Role<select name = "role">
                    <option value="1">system admin</option>
                    <option value="2">regular user</option>
                </select>
                <br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Update">
            </form>
            <a href="users">cancel</a>

        </c:if>
    </body>
</html>
