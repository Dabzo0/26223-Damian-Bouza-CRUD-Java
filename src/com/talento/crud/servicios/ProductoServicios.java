package com.talento.crud.servicios;

import java.util.ArrayList;
import java.util.Scanner;

import com.talento.crud.modelos.ProductoModelo;
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
        System.out.println();
        String nombre = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese el NOMBRE del producto: ").toUpperCase();
        System.out.println();
        double precio = CapturarEntrada.doubleNoNegativo(scanner, "-> Ingrese el PRECIO del producto: ");
        System.out.println();
        int stock = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el STOCK del producto: ");

        ProductoModelo producto = new ProductoModelo(codigo, nombre, precio, stock);

        productos.add(producto);

        System.out.println("\n" + producto);

        System.out.println("\t[!] -- Producto ingresado correctamente.\n");
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
        int nuevoStock=producto.getStock();
        
        System.out.println(">> Modificar NOMBRE del producto?");
        if (CapturarEntrada.confirmar(scanner)){
            nuevoNombre = CapturarEntrada.textoNoVacio(scanner, "-> Ingrese el nuevo nombre del producto: ").toUpperCase();
        }

        System.out.println("\n>> Modificar el PRECIO del producto?");
        if (CapturarEntrada.confirmar(scanner)){
            nuevoPrecio = CapturarEntrada.doubleNoNegativo(scanner, "-> Ingrese el nuevo precio del producto: ");
        }
        
        System.out.println("\n>> Modificar el STOCK del producto?");
        if (CapturarEntrada.confirmar(scanner)){
            nuevoStock = CapturarEntrada.enteroNoNegativo(scanner, "-> Ingrese el nuevo stock del producto:");
        }

        System.out.println("\n"+producto);
        System.out.println(">> Cambiará a:\n");
        System.out.println("** Producto\t\t\tcódigo [ " + producto.getCodigo() +" ]");
        System.out.println("\t- Nombre: " + nuevoNombre + ".");
        System.out.println("\t- Precio: $" + nuevoPrecio + ".");
        System.out.println("\t- Stock: " + nuevoStock + ".\n");
        
        if (CapturarEntrada.confirmar(scanner)){
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            System.out.println("\n\t[!] -- Producto modificado correctamente!\n");
        }else{
            System.out.println("\n\t[X] -- El producto no se modificó.\n");
        }

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

}