<%-- 
    Document   : update
    Created on : Jul 1, 2020, 3:41:39 PM
    Author     : USER
--%>

<%@page import="dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
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
            Book ID<input type="text" name="txtBookID" value="${param.txtBookID} " readonly="true"></br>
            Title<input type="text" name="txtTitle" value="${param.txtTitle}"></br>
            ${requestScope.ERROR_BOOK.errorBookTitle}</br>
            price<input type="text" name="txtPrice" value="${param.txtPrice} "></br>
            ${requestScope.ERROR_BOOK.errorBookPrice}</br>
            quantity<input type="text" name="txtQuantity" value="${param.txtQuantity} "></br>
            ${requestScope.ERROR_BOOK.errorBookQuantity}</br>
            author<input type="text" name="txtAuthor" value="${param.txtAuthor} "></br>
            ${requestScope.ERROR_BOOK.errorBookAuthor}</br>
            status<input type="text" name="txtStatus" value="${param.txtStatus} "></br>
            ${requestScope.ERROR_BOOK.errorStatus}</br>
            <input type="hidden" name="txtSearch" value="${param.txtSearch}"></br>
            <input type="submit" name="btnAction" value="update"></br>

        </form>
    </body>
</html>
