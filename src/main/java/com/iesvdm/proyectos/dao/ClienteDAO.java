package com.iesvdm.proyectos.dao;

import com.iesvdm.proyectos.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

    public void create(Cliente cliente);

    public List<Cliente> getAll();
    public Optional<Cliente> find(int id);
    public Optional<Cliente>findByName(String name);

    public void update(Cliente cliente);

    public void delete(int id);
}
