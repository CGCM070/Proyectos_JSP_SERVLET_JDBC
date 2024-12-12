<%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 11/12/2024
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Control de Acceso</title>
</head>
<body>
<h1>Control de Acceso</h1>
<p>Acceso permitido a la aplicación.</p>
<%
    String username = (String) session.getAttribute("username");
    if ("admin".equals(username)) {
%>
<form action="AdminManagementServlet" method="get">
    <button type="submit">Aceptar</button>
</form>
<%
} else {
%>
<form action="PiramideServlet" method="get">
    <button type="submit">Aceptar</button>
</form>
<%
    }
%>
</body>
</html>