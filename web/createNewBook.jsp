<%-- 
    Document   : createNewBook
    Created on : Jul 11, 2020, 4:53:11 PM
    Author     : USER
--%>

<%@page import="dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create book Page</title>
    </head>
    <body>
        <% UserDTO login_user = (UserDTO) session.getAttribute("LOGIN_USER");
            String fullName = null;
            try {
                fullName = login_user.getFullName();
            } catch (Exception e) {
                log("error " + e.toString());
            }
            if (login_user == null || !login_user.getRoleID().equals("ad") || fullName == null) {
                response.sendRedirect("login.html");
            }
        %>
        <form action="MainController">
                Book ID <input type="text" name="txtBookID" value="" /></br>
                ${requestScope.ERROR_BOOK.errorBookID}</br>
                Book name <input type="text" name="txtTitle" value="" /></br>
                ${requestScope.ERROR_BOOK.errorBookTitle}</br>
                Author <input type="text" name="txtAuthor" value="" /></br>
                ${requestScope.ERROR_BOOK.errorBookAuthor}</br>
                Quantity <input type="text" name="txtQuantity" value="" /></br>
                ${requestScope.ERROR_BOOK.errorBookQuantity}</br>
                Price <input type="text" name="txtPrice" value="" /></br>
                ${requestScope.ERROR_BOOK.errorBookPrice}</br>
                Status <input type="text" name="txtStatus" value="" /></br>
                ${requestScope.ERROR_BOOK.errorStatus}</br>
                <input type="submit" name="btnAction" value="createBook" />
            </form>
                <a href="search.jsp">back to admin page</a>
    </body>
</html>
