<%-- 
    Document   : search
    Created on : Jun 30, 2020, 4:22:48 PM
    Author     : USER
--%>
<%@page import="dtos.BookDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

        <a href="MainController?btnAction=logout" > Logout </a>
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

        <h1>welcome :${sessionScope.LOGIN_USER.fullName } </h1>
        <a href="createNewBook.jsp">Create New Book</a>
        <form action="MainController">
            Search <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" name="btnAction" value="search"/>
        </form>
            
        <%
            List<BookDTO> list = (List<BookDTO>) request.getAttribute("LIST_BOOK");

            if (list != null)
                if (!list.isEmpty()) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>no</th>
                    <th>Book ID</th>
                    <th>title</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>author</th>
                    <th>status</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%  int count = 1;
                    for (BookDTO dto : list) {
                %>
                <tr>

                    <td><%=count++%></td>
                    <td><%=dto.getBookID()%></td>
                    <td> <%=dto.getTitle()%> </td>
                    <td><%=dto.getPrice()%></td>
                    <td><%=dto.getQuantity()%></td>
                    <td><%=dto.getAuthor()%></td>
                    <% if (dto.getStatus() == 0) { %>
                    <td>${'not valid yet'}</td>
                    <%} else {%>
                    <td>${'valid'}</td>
                    <%}%>
                    <td> <a href="MainController?btnAction=delete&txtBookID=<%=dto.getBookID()%> &txtSearch=${param.txtSearch}"> Delete </a></td>
                    <td>
                        <form action = "MainController">
                            <input type="hidden" name="txtBookID" value="<%=dto.getBookID()%>"/>
                            <input type="hidden" name="txtTitle" value="<%=dto.getTitle()%>"/>
                            <input type="hidden" name="txtPrice" value="<%=dto.getPrice()%>"/>
                            <input type="hidden" name="txtQuantity" value="<%=dto.getQuantity()%>"/>
                            <input type="hidden" name="txtAuthor" value="<%=dto.getAuthor()%>"/>
                            <input type="hidden" name="txtStatus" value="<%=dto.getStatus()%>"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="submit" name="btnAction" value="update page"/>
                        </form>
                    </td> 

                </tr>
                <%}%>
            </tbody>
        </table>
        <%}%>

    </body>
</html>
