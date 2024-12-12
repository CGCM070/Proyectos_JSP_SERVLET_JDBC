package com.iesvdm.proyectos.dao;

import com.iesvdm.proyectos.model.Cliente;
import com.iesvdm.proyectos.model.User;

import java.sql.*;
import java.util.ArrayList;
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

            //Columna clienteID es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO cliente (nombre, direccion, telefono, fechaNaciemiento) VALUES (?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);

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
    public List<Cliente> getAll() {


        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Cliente> listaClientes = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setClienteID(rs.getInt("clienteID"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                listaClientes.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaClientes;

    }

    @Override
    public Optional<Cliente> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM cliente WHERE socioID = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setClienteID(rs.getInt("clienteID"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));

                return Optional.of(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public Optional<Cliente> findByName(String name) {


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM cliente WHERE nombre = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteID(rs.getInt("clienteID"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                return Optional.of(cliente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, fechaNacimiento = ?  WHERE clienteID = ?");
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setDate(idx++,  (Date) cliente.getFechaNacimiento());

            ps.setInt(idx, cliente.getClienteID());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de socio con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM cliente WHERE clienteID = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de socio con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }
}
