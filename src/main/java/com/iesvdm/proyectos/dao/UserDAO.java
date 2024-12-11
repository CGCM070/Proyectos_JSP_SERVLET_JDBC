package com.iesvdm.proyectos.dao;

public interface UserDAO {


    public void create(User user);

    public List<User> getAll();
    public Optional<User> find(int id);

    public void update(User user);

    public void delete(int id);
}
