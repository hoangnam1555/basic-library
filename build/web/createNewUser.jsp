<%-- 
    Document   : createNewUser
    Created on : Jul 1, 2020, 3:47:21 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New User Page</title>
    </head>
    <body>
         <form action="MainController" method="POST" >
            User ID <input type="text" name="txtUserID"/></br>
            ${requestScope.ERROR.userIDError}</br>
            Full Name <input type="text" name="txtFullName"/></br>
            ${requestScope.ERROR.fullNameError}</br>
            Role ID <input type="text" name="txtRoleID" value="user" readonly="true"/></br>
             ${requestScope.ERROR.roleIDError}</br>
            Password <input type="password" name="txtPassword"/></br>
            Re Password <input type="password" name="txtRePassword"/></br>
             ${requestScope.ERROR.rePasswordError}</br>
            <input type="submit" name="btnAction" value="create" >
            
        </form>
    </body>
</html>
