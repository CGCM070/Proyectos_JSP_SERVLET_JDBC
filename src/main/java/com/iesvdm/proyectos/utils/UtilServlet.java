package com.iesvdm.proyectos.utils;

import com.iesvdm.proyectos.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
}
