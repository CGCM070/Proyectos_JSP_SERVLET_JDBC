package com.iesvdm.proyectos.utils;

import com.iesvdm.proyectos.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<User> validaGrabarV1(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        String username = null;
        String password = null;
        try {

            if (request.getParameter("username").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            username = request.getParameter("username");


            if (request.getParameter("password").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            password = request.getParameter("password");

            return Optional.of(new User(-1, username, password));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }
}
