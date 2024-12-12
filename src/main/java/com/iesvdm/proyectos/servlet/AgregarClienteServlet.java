package com.iesvdm.proyectos.servlet;

import com.iesvdm.proyectos.dao.ClienteDAO;
import com.iesvdm.proyectos.dao.ClienteDAOImpl;
import com.iesvdm.proyectos.model.Cliente;
import com.iesvdm.proyectos.utils.UtilServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AgregarClienteServlet", value = "/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirección al formulario de cliente
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        // Validar el cliente utilizando la utilidad
        Optional<Cliente> optionalCliente = UtilServlet.validaGrabarV2(request);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            // Persistir el cliente en la base de datos
            this.clienteDAO.create(cliente);

            // Obtener el listado actualizado de clientes
            List<Cliente> listado = this.clienteDAO.getAll();

            // Preparar atributos para la vista
            request.setAttribute("listado", listado);

            request.setAttribute("newClienteID", cliente.getClienteID());

            // Redirección al listado de clientes
            response.sendRedirect("ListarClientesServlet");
        } else {
            // Preparar mensaje de error
            request.setAttribute("error", "Error de validación. Por favor, revise los datos ingresados.");
            // Redirección al formulario de cliente
            response.sendRedirect("ListarClientesServlet");
        }

    }
}