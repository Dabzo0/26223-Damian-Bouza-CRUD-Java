package com.talento.crud.servicios;

import java.util.ArrayList;
import java.util.Scanner;

import com.talento.crud.modelos.ProductoModelo;
import com.talento.crud.modelos.ProductoAlmacen;
import com.talento.crud.modelos.ProductoIndumentaria;
import com.talento.crud.utilidades.CapturarEntrada;

public class ProductoServicios {

    public static void mostrarProductos(ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> LISTADO DE PRODUCTOS:");

        if (productos.isEmpty()) {
            System.out.println("\n\t[X] -- No existen productos ingresados.\n");
        } else {
            System.out.println();
            for (ProductoModelo producto : productos) {
                System.out.println(producto);
            }
            System.out.println("\t[!] -- Fin del listado. Total de productos: " + productos.size() +".\n");
        }            
    }

    public static void ingresarProducto(Scanner scanner, ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> INGRESAR PRODUCTO:\n");

        int codigo = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el CÓDIGO del producto: ");

        if (buscarProductoPorCodigo(productos, codigo) != null) {
            System.out.println("\n\t[X] -- Error: ya existe un producto con ese código.\n");
            return;
        }

        int categoria = CapturarEntrada.categoria(scanner);

        System.out.println();
        String nombre = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese el NOMBRE del producto: ").toUpperCase();
        System.out.println();
        double precio = CapturarEntrada.doubleNoNegativo(scanner, "-> Ingrese el PRECIO del producto: ");
        System.out.println();

        ProductoModelo producto = null;

        switch (categoria) {
            case 1:
                String vencimientoFecha = CapturarEntrada.fecha(scanner, "-> Ingrese fecha de VENCIMIENTO del producto (dd/mm/aa): ");
                String presentacion = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese la PRESENTACIÓN del producto: ");
                producto = new ProductoAlmacen(codigo, nombre, precio, "Almacén", vencimientoFecha, presentacion);
                break;
            case 2:
                String talle = CapturarEntrada.talle(scanner, "-> Ingrese el TALLE del producto: ");
                String descripcion = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese breve DESCRIPCIÓN del producto: ");
                producto = new ProductoIndumentaria(codigo, nombre, precio, "Indumentaria", talle, descripcion);
                break;        
            default:
                break;
        }        

        if (producto != null){
            productos.add(producto);
            System.out.println("\n" + producto);
            System.out.println("\t[!] -- Producto ingresado correctamente.\n");
        } else {            
            System.out.println("\n\t[X] -- El producto NO se ingresó.\n");            
        }
    }

    public static void buscarProducto(Scanner scanner, ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> BUSCAR PRODUCTO:");

        if (productos.isEmpty()) {
            System.out.println("\n\t[X] -- No existen productos ingresados.\n");
            return;
        }

        int codigo = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el código del producto a buscar: ");

        ProductoModelo producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("\n\t[X] -- El producto no existe.\n");
        } else {
            System.out.println("\n\t[!] -- Producto encontrado:");
            System.out.println("\n" + producto);
        }
    }

    public static void modificarProducto(Scanner scanner, ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> MODIFICAR PRODUCTO:");

        if (productos.isEmpty()) {
            System.out.println("\n\t[X] -- No existen productos ingresados.\n");
            return;
        }

        System.out.println();
        int codigo = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el código del producto a modificar: ");

        ProductoModelo producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("\n\t[X] -- El producto no existe.\n");
            return;
        }
        System.out.println("\n\t[!] -- Producto encontrado.\n");
        System.out.println(producto);
        String nuevoNombre = producto.getNombre();
        double nuevoPrecio = producto.getPrecio();
        
        System.out.println(">> Modificar NOMBRE del producto?");
        if (CapturarEntrada.confirmar(scanner)){
            nuevoNombre = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese el nuevo nombre del producto: ").toUpperCase();
        }

        System.out.println("\n>> Modificar el PRECIO del producto?");
        if (CapturarEntrada.confirmar(scanner)){
            nuevoPrecio = CapturarEntrada.doubleNoNegativo(scanner, "-> Ingrese el nuevo precio del producto: ");
        }
        
        boolean realizado = false;

        if (producto instanceof ProductoAlmacen) realizado = modificarProductoAlmacen(scanner, (ProductoAlmacen) producto, nuevoNombre, nuevoPrecio);
        if (producto instanceof ProductoIndumentaria) realizado = modificarProductoIndumentaria(scanner, (ProductoIndumentaria) producto, nuevoNombre, nuevoPrecio);        
        
        System.out.println( realizado ? "\n\t[!] -- Producto modificado correctamente!\n" : "\n\t[X] -- El producto no se modificó.\n");   

    }

    public static void eliminarProducto(Scanner scanner, ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> ELIMINAR PRODUCTO:");

        if (productos.isEmpty()) {
            System.out.println("\n\t[X] -- No existen productos ingresados.\n");
            return;
        }
        System.out.println();
        int codigo = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el código del producto a eliminar: ");

        ProductoModelo producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("\n\t[X] -- El producto no existe.\n");
            return;
        }
        System.out.println("\n\t[!] -- Producto encontrado:\n");
        System.out.println(producto);
        System.out.println("\n>> Se eliminará definitivamente.");
        
        if (CapturarEntrada.confirmar(scanner)){
            productos.remove(producto);
            System.out.println("\n\t[!] -- El producto se eliminó correctamente.\n");
        }else{
            System.out.println("\n\t[X] -- El producto no se eliminó.\n");
        }
       
    }

    public static void filtrarProductos(Scanner scanner, ArrayList<ProductoModelo> productos) {

        System.out.println("\n>> FILTRAR PRODUCTOS:");

        if (productos.isEmpty()) {
            System.out.println("\n\t[X] -- No existen productos ingresados.\n");
            return;
        }

        System.out.println();
        String criterio = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese el nombre o código a filtrar: ").toUpperCase();
        String criterioNormalizado= CapturarEntrada.normalizarTexto(criterio);

        ArrayList<ProductoModelo> resultados = new ArrayList<>();

        for (ProductoModelo producto : productos) {
            String codigoString = String.valueOf(producto.getCodigo());
            String nombreString = CapturarEntrada.normalizarTexto(producto.getNombre()).toUpperCase();

            if (nombreString.contains(criterioNormalizado) || codigoString.contains(criterioNormalizado)) {
                resultados.add(producto);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("\n\t[X] -- No se encontraron coincidencias para: \"" + criterio + "\".\n");
        } else {
            System.out.println("\n>> Resultado:\n");
            for (ProductoModelo producto : resultados) {
                System.out.println(producto);
            }
            System.out.println("\t[!] -- Fin de la lista filtrada. Se encontraron " + resultados.size() + " coincidencia(s):\n");
        }
    }

    public static ProductoModelo buscarProductoPorCodigo(ArrayList<ProductoModelo> productos, int codigo) {
        
        for (ProductoModelo producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }

        return null;
    }

    public static boolean modificarProductoAlmacen(Scanner scanner, ProductoAlmacen almacen, String nuevoNombre, double nuevoPrecio){
        String nuevoVencimientoFecha= almacen.getVencimientoFecha();
        String nuevoPresentacion= almacen.getPresentacion();

        System.out.println("\n>> Modificar PRESENTACIÓN del producto?");
        if (CapturarEntrada.confirmar(scanner)) nuevoPresentacion = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese la nueva presentación del producto:");

        System.out.println("\n>> Modificar fecha de VENCIMIENTO del producto?");
        if (CapturarEntrada.confirmar(scanner)) nuevoVencimientoFecha = CapturarEntrada.fecha(scanner, "-> Ingrese la nueva fecha de vencimiento del producto dd/mm/aa:");
        
        System.out.println("\n"+ almacen );
        System.out.println(">> Cambiará a:\n");
        System.out.println("** "+almacen.getCategoria()+"\t\t\tcódigo [ " + almacen.getCodigo() +" ]");
        System.out.println("\t- Nombre: " + nuevoNombre + ".");
        System.out.println("\t- Precio: $" + nuevoPrecio + ".");
        System.out.println("\t- Presentación: " + nuevoPresentacion + ".");
        System.out.println("\t- Fecha de vencimiento: " + nuevoVencimientoFecha + ".");
        
        if (CapturarEntrada.confirmar(scanner)){
            almacen.setNombre(nuevoNombre);
            almacen.setPrecio(nuevoPrecio);
            almacen.setVencimientoFecha(nuevoVencimientoFecha);
            almacen.setPresentacion(nuevoPresentacion);
            return true;
        }else{
            return false;
        }
    }

    public static boolean modificarProductoIndumentaria(Scanner scanner, ProductoIndumentaria indumentaria, String nuevoNombre, double nuevoPrecio){
        String nuevoTalle = indumentaria.getTalle();
        String nuevoDescripcion = indumentaria.getDescripcion();

        System.out.println("\n>> Modificar TALLE del producto?");
        if (CapturarEntrada.confirmar(scanner)) nuevoTalle = CapturarEntrada.talle(scanner, "-> Ingrese el nuevo TALLE del producto: ");

        System.out.println("\n>> Modificar DESCRIPCIÓN del producto?");
        if (CapturarEntrada.confirmar(scanner)) nuevoDescripcion = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese la nueva breve DESCRIPCIÓN del producto: ");
        
        System.out.println("\n"+ indumentaria );
        System.out.println(">> Cambiará a:\n");
        System.out.println("** " + indumentaria.getCategoria() + "\t\t\tcódigo [ " + indumentaria.getCodigo() +" ]");
        System.out.println("\t- Nombre: " + nuevoNombre + ".");
        System.out.println("\t- Precio: $" + nuevoPrecio + ".");
        System.out.println("\t- Talle: " + nuevoTalle + ".");
        System.out.println("\t- Descripción: " + nuevoDescripcion + ".");
        
        if (CapturarEntrada.confirmar(scanner)){
            indumentaria.setNombre(nuevoNombre);
            indumentaria.setPrecio(nuevoPrecio);
            indumentaria.setTalle(nuevoTalle);
            indumentaria.setDescripcion(nuevoDescripcion);
            return true;
        }else{
            return false;
        }
    }

}