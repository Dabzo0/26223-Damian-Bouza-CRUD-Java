package com.talento.crud.modelos;

public class ProductoModelo {
    private int codigo;
    private String nombre;
    private double precio;
    private int stock;

    public ProductoModelo (){}

    public ProductoModelo(int codigo, String nombre, double precio, int stcok) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stcok;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "** Producto\t\t\tcódigo [ " + codigo +" ]\n"+
            "\t- Nombre: " + nombre + ".\n" +
            "\t- Precio: $" + precio + ".\n" +
            "\t- Stock: " + stock + ".\n";
    }
}