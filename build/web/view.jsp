<%-- 
    Document   : view
    Created on : Jul 4, 2020, 2:51:42 PM
    Author     : USER
--%>

<%@page import="dtos.UserDTO"%>
<%@page import="java.util.Map"%>
<%@page import="dtos.BookDTO"%>
<%@page import="dtos.cartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
    </head>
    <body>
        <% UserDTO login_user = (UserDTO) session.getAttribute("LOGIN_USER");
            String fullName = null;
            try {
                fullName = login_user.getFullName();
            } catch (Exception e) {
                log("error " + e.toString());
            }
            if (login_user == null || fullName == null) {
                response.sendRedirect("login.html");
            }
        %>
        <h1>list book ban da muon</h1>
        <%
            int total = 0;
            if (session != null) {
                cartDTO cart = (cartDTO) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, BookDTO> items = cart.getItems();
                    if (items != null) {
        %>
        
        
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>price</th>
                    <th>Quantity</th>
                    <th>Author</th>
                    <th>Total</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>

                <%
                    int count = 0;
                    for (BookDTO dto : items.values()) {
                        total = total + dto.getPrice() * dto.getQuantity();
                %>
            <form action="MainController">
                <tr>
                    <td><%=++count%></td>
                    <td><%=dto.getBookID()%></td>

                    <td><%=dto.getTitle()%></td>
                    <td><%=dto.getPrice()%></td>
                    <td><input type="number" value="<%=dto.getQuantity()%>" name="txtQuantity"></td>
                    <td><%=dto.getAuthor()%></td>

                    <td><%= dto.getPrice() * dto.getQuantity()%></td>

                    <td>
                        <input type="hidden" name="txtBookID" value="<%= dto.getBookID()%>" />
                        <input type="submit" value="deleteBook" name="btnAction"/>
                    </td>
                    <td>
                        <input type="submit" value="updateBook" name="btnAction"/>
                    </td>
                </tr> 
            </form>

            <%}%>
        </tbody>
    </table>
    <h1>total : <%= total%></h1>
    <h2>${requestScope.MESSAGE}</h2>   
    <form action="MainController"> 
        <input type="hidden" name="txtTotal" value="<%= total %>" />
        <input type="submit" name="btnAction" value="CheckOut">
    </form>
    <a href="library.jsp">Back to library</a>
    <%
                    return;
                }
            }
        }
    %>
    <h2>

        Gio hang ko co sach!!</br>
        <a href="library.jsp">Back to library</a>
    </h2>

</body>
</html>
