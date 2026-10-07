package com.talento.crud.modelos;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductoModelo extends Object {

    public static final ArrayList<String> CATEGORIAS = new ArrayList<>(
        List.of("Almacén", "Indumentaria")
    );
    
    private int codigo;
    private String nombre;
    private double precio;
    private String categoria;

    public ProductoModelo (){}

    public ProductoModelo(int codigo, String nombre, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public abstract String getTipoProducto();

    public abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        return "** " + categoria + "\t\t\tcódigo [ " + codigo +" ]\n"+
            "\t- Nombre: " + nombre + ".\n" +
            "\t- Precio: $" + precio + ".\n" +
            getDetalleEspecifico();
    }
}