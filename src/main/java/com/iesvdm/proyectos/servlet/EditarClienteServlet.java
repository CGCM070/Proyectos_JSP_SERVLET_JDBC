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


@WebServlet(name = "/EditarClienteServlet", value = "/EditarClienteServlet")
public class EditarClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recogemos el codigo del socio a editar
        int nsocio = Integer.parseInt(request.getParameter("codigo"));

        // El find de la interfaz SocioDAO nos devuelve un Optional de Socio
        // Si el socio existe lo editamos y sino damos un mensaje de error
        Optional<Cliente> clienteOptional = this.clienteDAO.find(nsocio);

        if (clienteOptional.isPresent()) {
            Cliente editarCliente = clienteOptional.get();
            // Preparamos el socio para enviarlo al formulario de edición
            request.setAttribute("editarSocio", editarCliente);
            response.sendRedirect("ListarClientesServlet?success=updated");
        } else {
            // Si el optional no está presente, es decir, no existe el socio con ese codigo
            // Entonces redirigimos a la página de listado de socios con un mensaje de error
            request.setAttribute("error", "No existe el socio con el codigo: " + nsocio);
            response.sendRedirect("ListarClientesServlet");
        }
    }


    //metodo doPost actualizamos los datos del socio
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos un objeto RequestDispatcher para luego redirigir a la página de listado de socios
        RequestDispatcher dispatcher = null;

        // Recuperamos el id del socio a editar y lo validamos

        int numeroSocio = -1;
        try {
            numeroSocio = Integer.parseInt(request.getParameter("codigo"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID Socio no válido");
            response.sendRedirect( "ListarClientesServlet");
            return;
        }


        //verificanos que el socio con ese ID existe
        Optional<Cliente> clienteOptional = this.clienteDAO.find(numeroSocio);
        if (!clienteOptional.isPresent()) {
            request.setAttribute("error", "No existe el socio con el codigo: " + numeroSocio);
            response.sendRedirect( "ListarClientesServlet");
            return;
        }

        //validamos los datos del socio
        Optional<Cliente> clienteValidado = UtilServlet.validaGrabarV2(request);
        if (clienteValidado.isPresent()) {
            Cliente clienteSocio = clienteValidado.get();
            clienteSocio.setClienteID(numeroSocio);

            //actualizamos los datos del socio
            this.clienteDAO.update(clienteSocio);

            // Redirigimos a la página de listado
            List<Cliente> listado = this.clienteDAO.getAll();
            request.setAttribute("listado", listado);
            response.sendRedirect( "ListarClientesServlet");
        } else {
            //si hay errores en la validación
            response.sendRedirect( "ListarClientesServlet");
        }
    }

}
