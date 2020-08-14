<%-- 
    Document   : library
    Created on : Jul 1, 2020, 2:33:05 PM
    Author     : USER
--%>

<%@page import="dtos.UserDTO"%>
<%@page import="daos.BookDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Page</title>
    </head>
    <body>
        <% UserDTO login_user = (UserDTO) session.getAttribute("LOGIN_USER");
            String fullName = null;
            try {
                fullName = login_user.getFullName();
            } catch (Exception e) {
                log("error " + e.toString());
            }
            if (login_user == null ||  fullName == null) {
                response.sendRedirect("login.html");
            }
        %>
        <a href="MainController?btnAction=logout" > Logout </a>
        <h1>Welcome : ${sessionScope.LOGIN_USER.fullName}</h1>
        ${requestScope.message}
        <table border="1">
            <thead>
                <tr>
                    <th>book ID</th>
                    <th>title</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>author</th>
                    <th>rent</th>
                </tr>
            </thead

            <tbody>   
                <%
                    BookDAO dao = new BookDAO();
                    ArrayList<BookDTO> list = (ArrayList<BookDTO>) dao.getListBook();
                    for (BookDTO book : list) {

                %>
                <tr>
                    <td><%=book.getBookID()%></td>
                    <td><%=book.getTitle()%></td>
                    <td><%=book.getPrice()%></td>
                    <td><%=book.getQuantity()%></td>
                    <td><%=book.getAuthor()%></td></br>  
            <form action="MainController">   

                <input type="hidden" name="txtBookID" value="<%=book.getBookID()%>"/>
                <input type="hidden" name="txtTitle" value="<%=book.getTitle()%>"/>
                <input type="hidden" name="txtPrice" value="<%=book.getPrice()%>"/>
                <input type="hidden" name="txtQuantity" value="<%=1%>"/>
                <input type="hidden" name="txtAuthor" value="<%=book.getAuthor()%>"/>
                <td><input type="submit" name="btnAction" value="add"/> </td></br>       
            </form>
        </tr>
        <%}%>

    </tbody>
</table>
        <a href="view.jsp"> view cart</a>
</body>
</html>
