//package com.iesvdm.proyectos.servlet;
//
//import com.iesvdm.proyectos.dao.UserDAO;
//import com.iesvdm.proyectos.dao.UserDAOImpl;
//import com.iesvdm.proyectos.model.User;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(name = "LoginServlet", value = "/LoginServlet")
//public class LoginServlet extends HttpServlet {
//
//    private UserDAO userDAO = new UserDAOImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        Optional<User> user = userDAO.findByName(username);
//
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/controlAcceso.jsp");
//            dispatcher.forward(request, response);
//        } else {
//            request.setAttribute("error", "Usuario o contraseña incorrectos");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//            dispatcher.forward(request, response);
//        }
//
//    }
//}



package com.iesvdm.proyectos.servlet;

import com.iesvdm.proyectos.dao.UserDAO;
import com.iesvdm.proyectos.dao.UserDAOImpl;
import com.iesvdm.proyectos.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user = userDAO.findByName(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/controlAcceso.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}