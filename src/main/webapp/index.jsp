<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
Redirigiendo. Por favor, espere...
<%
  response.sendRedirect("ListarClientesServlet");
%>
</body>
</html>