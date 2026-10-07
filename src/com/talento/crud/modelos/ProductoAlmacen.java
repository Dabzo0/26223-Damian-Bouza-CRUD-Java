package com.talento.crud.modelos;

public class ProductoAlmacen extends ProductoModelo {
    private String vencimientoFecha;
    private String presentacion;

    public ProductoAlmacen(int codigo, String nombre, double precio, String categoria, String vencimientoFecha, String presentacion) {
        super(codigo,nombre,precio,categoria);
        this.vencimientoFecha = vencimientoFecha;
        this.presentacion = presentacion;
    }
    public String getVencimientoFecha() {
        return vencimientoFecha;
    }
    
    public void setVencimientoFecha(String vencimientoFecha) {
        this.vencimientoFecha = vencimientoFecha;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    @Override
    public String getTipoProducto() {
        return "Almacén";
    }

    @Override
    public String getDetalleEspecifico() {        
        return "\t- Presentación: " + presentacion + 
        "\n\t- Fecha de vencimiento: " + vencimientoFecha + ".\n";
    }
    
    /* @Override 
    public String toString() {
        return super.toString() + getDetalleEspecifico();
    }*/
}