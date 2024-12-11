<%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 11/12/2024
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pirámide de Gatos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .cat-row {
            display: flex;
            justify-content: center;
        }
        .cat-row img {
            margin: 2px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Pirámide de Gatos</h2>
    <%
        int altura = (int) request.getAttribute("altura");
        for (int i = 1; i <= altura; i++) {
            out.println("<div class='cat-row'>");
            for (int j = 1; j <= i; j++) {
                out.println("<img src='images/gato.png' alt='Cat' width='50' height='50'>");
            }
            out.println("</div>");
        }
    %>
</div>
</body>
</html>