<%@ page import="com.iesvdm.proyectos.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 12/12/2024
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iesvdm.proyectos.model.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Gestión de Usuarios</title>
</head>
<body>

<h1>Gestión de Usuarios</h1>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form method="post" action="adminManagementServlet">
    <div class="row body mt-2">
        <div class="col-md-6 align-self-center">Nombre</div>
        <div class="col-md-6 align-self-center"><input type="text" name="username" required /></div>
    </div>
    <div class="row body mt-2">
        <div class="col-md-6 align-self-center">Contraseña</div>
        <div class="col-md-6 align-self-center"><input type="password" name="password" required /></div>
    </div>
    <div class="row mt-2">
        <div class="col-md-6">&nbsp;</div>
        <div class="col-md-6 align-self-center">
            <input class="btn btn-primary" type="submit" value="Guardar usuario">
            <a class="btn btn-primary text-center" href="index.jsp">Volver al incio</a>
        </div>
    </div>
</form>

<h2>Usuarios Registrados</h2>
<ul>
    <%
        List<User> listaUsuarios = (List<User>) request.getAttribute("listaUsuarios");
        if (listaUsuarios != null) {
            for (User user : listaUsuarios) {
    %>
    <li><%= user.getUsername() %></li>
    <li><%= user.getPassword() %></li>
    <%
            }
        }
    %>
</ul>

</body>
</html>