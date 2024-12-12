package com.iesvdm.proyectos.dao;

import com.iesvdm.proyectos.model.Cliente;
import com.iesvdm.proyectos.model.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl  extends  AbstractDAOImpl implements  ClienteDAO{
    @Override
    public void create(Cliente cliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;


        try {
            conn = connectDB();

            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO clientes (nombre, direccion, telefono, localidad) VALUES (?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setDate(idx++, (Date) cliente.getFechaNacimiento());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                cliente.setClienteID(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public Optional<Cliente> find(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}
