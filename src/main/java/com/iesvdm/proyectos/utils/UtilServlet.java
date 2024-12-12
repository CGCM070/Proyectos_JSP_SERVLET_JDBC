package com.iesvdm.proyectos.utils;

import com.iesvdm.proyectos.model.Cliente;
import com.iesvdm.proyectos.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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

            password = hashPassword( request.getParameter("password"));

            return Optional.of(new User(-1, username, password));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }


    /**
     * Método que obtiene el hash de una password, por ejemplo, dado password = admin, devuelve:
     8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
     * @param password
     * @return hash de encriptación de la password
     * @throws NoSuchAlgorithmException
     */
    public static String hashPassword(String password ) throws NoSuchAlgorithmException {
        MessageDigest digest;

        digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(encodedhash);

    }

    private static String bytesToHex(byte[] byteHash) {

        StringBuilder hexString = new StringBuilder(2 * byteHash.length);
        for (int i = 0; i < byteHash.length; i++) {
            String hex = Integer.toHexString(0xff & byteHash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();

    }



    public static Optional<Cliente> validaGrabarV2(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        String nombre = null;
        String direccion = null;
        String telefono = null;
        Date fechaNacimiento = null;
        try {

            // Validación del nombre
            Objects.requireNonNull(request.getParameter("nombre"));
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            // Validación de la dirección
            Objects.requireNonNull(request.getParameter("direccion"));
            if (request.getParameter("direccion").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            direccion = request.getParameter("direccion");

            // Validación del teléfono
            Objects.requireNonNull(request.getParameter("telefono"));
            if (request.getParameter("telefono").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            telefono = request.getParameter("telefono");

            // Validación de la fecha de nacimiento
            Objects.requireNonNull(request.getParameter("fechaNacimiento"));
            if (request.getParameter("fechaNacimiento").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            fechaNacimiento = java.sql.Date.valueOf(request.getParameter("fechaNacimiento"));

            return Optional.of(new Cliente(-1, direccion, fechaNacimiento, nombre, telefono));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();
    }

}
