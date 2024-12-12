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
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Pirámide de Gatos</h2>
    <form method="post" action="PiramideServlet">
        <div class="mb-3">
            <label for="height" class="form-label">Altura</label>
            <input type="number" class="form-control" id="height" name="height" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Generar</button>
        <div class="mt-3 text-danger">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null && !error.isEmpty()) {
                    out.print(error);
                }
            %>
        </div>
    </form>
</div>
</body>
</html>