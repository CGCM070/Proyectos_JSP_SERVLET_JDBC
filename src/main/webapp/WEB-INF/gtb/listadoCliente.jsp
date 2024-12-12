<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iesvdm.proyectos.model.Cliente" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Clientes</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="estilos.css" />
    <link rel="stylesheet" type="text/css" href="css/backtop.css" />
</head>
<body class="bg-light">

<!-- BackToTop Button -->
<a href="javascript:void(0);" id="backToTop" class="back-to-top">
    <i class="arrow"></i><i class="arrow"></i>
</a>

<div class="container bg-white sticky-top">
    <div class="row mb-2 border-bottom">
        <div class="col-md-8 h1">Listado de Socios</div>
        <div class="col-md-4 align-self-center">
            <form method="post" action="AgregarClienteServlet">
                <input class="btn btn-primary" type="submit" value="Crear Socio">
            </form>
        </div>
    </div>

    <!-- Formulario de Búsqueda -->
    <div class="row mb-3">
        <div class="col-md-10">
            <form method="get" action="BuscarClienteServlet">
                <div class="input-group">
                    <input type="text" class="form-control" name="nombre" placeholder="Buscar por nombre">
                    <button class="btn btn-secondary" type="submit">Buscar</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-1 h3">ID</div>
        <div class="col-md-4 h3">Nombre</div>
        <div class="col-md-1 h3">Dirección</div>
        <div class="col-md-1 h3">Teléfono</div>
        <div class="col-md-3 h3">Fecha de Nacimiento</div>
        <div class="col-md-2 h3 text-center">Operación</div>
    </div>
</div>

<div class="container bg-light">
    <%
        List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
        for (Cliente cliente : listaClientes) {
    %>
    <div id="<%=cliente.getClienteID()%>" class="row mt-2 body">
        <div class="col-md-1 align-self-center"><%=cliente.getClienteID() %></div>
        <div class="col-md-4 align-self-center"><%= cliente.getNombre() %></div>
        <div class="col-md-1 align-self-center"><%=cliente.getDireccion() %></div>
        <div class="col-md-1 align-self-center"><%=cliente.getTelefono() %></div>
        <div class="col-md-3 align-self-center"><%=cliente.getFechaNacimiento()%></div>

        <div class="col-md-2 align-self-center text-center">
            <form class="d-inline" method="post" action="BorrarClienteServlet">
                <input type="hidden" name="codigo" value="<%=cliente.getClienteID() %>"/>
                <input class="btn btn-primary" type="submit" value="Borrar">
            </form>
            <form class="d-inline" method="get" action="EditarClienteServlet">
                <input type="hidden" name="codigo" value="<%=cliente.getClienteID() %>"/>
                <input class="btn btn-primary" type="submit" value="Editar">
            </form>
        </div>
    </div>
    <%
        }
    %>
</div>

<!-- Formulario para Agregar un Nuevo Cliente -->
<div class="container mt-5">
    <h3>Agregar Nuevo Cliente</h3>
    <form method="post" action="AgregarClienteServlet">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion" required>
        </div>
        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="text" class="form-control" id="telefono" name="telefono" required>
        </div>
        <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Cliente</button>
    </form>
</div>

<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript">
    $(function () {
        let btn = $('#backToTop');
        $(window).on('scroll', function() {
            if ($(window).scrollTop() > 300) {
                btn.addClass('show');
            } else {
                btn.removeClass('show');
            }
        });
        btn.on('click', function(e) {
            e.preventDefault();
            $('html, body').animate({
                scrollTop: 0
            }, '300');
        });
    });
</script>
<script type="text/javascript" src="js/bootstrap.bundle.js"></script>
</body>
</html>
