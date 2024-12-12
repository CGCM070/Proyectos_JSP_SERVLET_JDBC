package com.iesvdm.proyectos.servlet.admin;


import com.iesvdm.proyectos.dao.UserDAO;
import com.iesvdm.proyectos.dao.UserDAOImpl;
import com.iesvdm.proyectos.model.User;
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

@WebServlet (name = "adminManagementServlet" , value = "/adminManagementServlet")
public class adminManagementServlet extends HttpServlet {

    private UserDAO userDAO  = new UserDAOImpl();

    @Override
    protected void doGet (HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher= null;

        List<User> listaUsuarios = userDAO.getAll();
        request.setAttribute("listaUsuarios" ,listaUsuarios);
        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/agregaUser.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        RequestDispatcher dispatcher= null;

        String username = request.getParameter("username");
 //       String password = request.getParameter("password");

        Optional<User> optionalUser = UtilServlet.validaGrabarV1(request);

        //!username.equals(userDAO.findByName(username)) && optionalUser.isPresent()
        if (optionalUser.isPresent() && userDAO.findByName(username).isEmpty() )  {

            User user = optionalUser.get();
            this.userDAO.create(user);

            List<User> listaUsuarios = this.userDAO.getAll();
            request.setAttribute("listaUsuario" , listaUsuarios);
            request.setAttribute("newSocioID", user.getUserID());

        }else {
            request.setAttribute("error" , "Ese nombre ya esta en uso.");

        }
        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/agregaUser.jsp");
        dispatcher.forward(request,response);

    }
}
