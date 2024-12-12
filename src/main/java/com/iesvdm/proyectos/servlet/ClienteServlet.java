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

@WebServlet(name = "ClienteServlet", value = "/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<Cliente> listaClientes = clienteDAO.getAll();
            request.setAttribute("listaClientes", listaClientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listaClientes.jsp");
            dispatcher.forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Cliente> cliente = clienteDAO.find(id);
            if (cliente.isPresent()) {
                request.setAttribute("cliente", cliente.get());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editCliente.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("ClienteServlet?action=list");
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            clienteDAO.delete(id);
            response.sendRedirect("ClienteServlet?action=list");
        } else {
            response.sendRedirect("ClienteServlet?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            Optional<Cliente> optionalCliente = UtilServlet.validaGrabarV2(request);
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                if (clienteDAO.findByName(cliente.getNombre()).isEmpty()) {
                    clienteDAO.create(cliente);
                    response.sendRedirect("ClienteServlet?action=list");
                } else {
                    request.setAttribute("error", "Ese nombre ya está en uso.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/nuevoCliente.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("error", "Error en la validación de los datos del cliente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/nuevoCliente.jsp");
                dispatcher.forward(request, response);
            }
        } else if ("update".equals(action)) {
            Optional<Cliente> optionalCliente = UtilServlet.validaGrabarV2(request);
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                cliente.setClienteID(Integer.parseInt(request.getParameter("id")));
                clienteDAO.update(cliente);
                response.sendRedirect("ClienteServlet?action=list");
            } else {
                request.setAttribute("error", "Error en la validación de los datos del cliente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editCliente.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("ClienteServlet?action=list");
        }
    }
}