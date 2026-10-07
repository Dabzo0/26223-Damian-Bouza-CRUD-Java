package com.talento.crud.modelos;

public class ProductoIndumentaria extends ProductoModelo {
    private String talle;
    private String descripcion;

    public ProductoIndumentaria(int codigo, String nombre, double precio, String categoria, String talle, String descripcion) {
        super(codigo,nombre,precio,categoria);
        this.talle = talle;
        this.descripcion = descripcion;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle){
        this.talle = talle;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String getTipoProducto(){
        return "Indumentaria";
    }

    @Override
    public String getDetalleEspecifico() {        
        return "\t- Talle: " + talle + 
        "\n\t- Descripción: " + descripcion + ".\n";
    }
}