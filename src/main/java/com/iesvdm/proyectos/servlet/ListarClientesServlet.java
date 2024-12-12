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

@WebServlet (name = "ListarClientesServlet" , value = "/ListarClientesServlet")
public class ListarClientesServlet  extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{

        RequestDispatcher dispatcher = null;
        List<Cliente> listaClientes = this.clienteDAO.getAll();
        request.setAttribute("listaClientes" , listaClientes);


        dispatcher= request.getRequestDispatcher("/WEB-INF/gtb/listadoCliente.jsp");
        dispatcher.forward(request,response);
    }


}
