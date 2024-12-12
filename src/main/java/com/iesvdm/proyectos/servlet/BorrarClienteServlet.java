package com.iesvdm.proyectos.servlet;

import com.iesvdm.proyectos.dao.ClienteDAO;
import com.iesvdm.proyectos.dao.ClienteDAOImpl;
import com.iesvdm.proyectos.model.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "/BorrarClienteServlet", value = "/BorrarClienteServlet")
public class BorrarClienteServlet  extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        int nsocio = Integer.parseInt(request.getParameter("codigo"));

        Optional<Cliente> clienteOptional = this.clienteDAO.find(nsocio);


        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get(); // Obtenemos el socio del Optional porque sabemos que está presente
            this.clienteDAO.delete(cliente.getClienteID()); // Borramos el cliente

            // Redirigimos a la página de listado de socios pero antes actualizamos la lista de clientes
            List<Cliente> listado = this.clienteDAO.getAll();
            request.setAttribute("listado", listado);
            request.setAttribute("ID Socio Eliminado", nsocio);

            // Redirigimos a la página de listado de socios
//            dispatcher = request.getRequestDispatcher("ListarCli");
                response.sendRedirect("ListarClientesServlet");
        } else {
            // Si el optional no está presente, es decir, no existe el socio con ese codigo
            // Entonces redirigimos a la página de listado de socios con un mensaje de error
            request.setAttribute("error", "No existe el socio con el codigo: " + nsocio);
            dispatcher.forward(request, response);
            response.sendRedirect("ListarClientesServlet");
        }

 // Hacemos



    }
}
