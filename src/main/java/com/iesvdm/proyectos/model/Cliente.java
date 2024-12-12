package com.iesvdm.proyectos.model;

import java.util.Date;
import java.util.Objects;

public class Cliente {

    private int clienteID;
    private String nombre;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;

    public Cliente() {
    }

    public Cliente(int clienteID, String direccion, Date fechaNacimiento, String nombre, String telefono) {
        this.clienteID = clienteID;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getClienteID() {
        return clienteID;
    }

    public Cliente setClienteID(int clienteID) {
        this.clienteID = clienteID;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public Cliente setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Cliente setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Cliente setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return clienteID == cliente.clienteID && Objects.equals(nombre, cliente.nombre) && Objects.equals(direccion, cliente.direccion) && Objects.equals(telefono, cliente.telefono) && Objects.equals(fechaNacimiento, cliente.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteID, nombre, direccion, telefono, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteID=" + clienteID +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
