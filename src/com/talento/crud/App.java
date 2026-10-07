package com.talento.crud;

import java.util.ArrayList;
import java.util.Scanner;

import com.talento.crud.modelos.ProductoModelo;
import com.talento.crud.servicios.ProductoServicios;
import com.talento.crud.utilidades.CapturarEntrada;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<ProductoModelo> productos = new ArrayList<>();
        int opcion;

        do {
            System.out.println("  >>>    GESTIÓN DE PRODUCTOS    <<<\n");
            System.out.println("[1] - Ingresar producto.");
            System.out.println("[2] - Mostrar todos los productos.");            
            System.out.println("[3] - Buscar un producto por código.");
            System.out.println("[4] - Filtrar productos.");
            System.out.println("[5] - Modificar un producto.");
            System.out.println("[6] - Eliminar un producto.");            
            System.out.println("[0] - Terminar.\n");

            opcion = CapturarEntrada.opcion(scanner);

            switch (opcion) {
                case 1:
                    ProductoServicios.ingresarProducto(scanner, productos);
                    break;
                case 2:
                    ProductoServicios.mostrarProductos(productos);
                    break;
                case 3:
                    ProductoServicios.buscarProducto(scanner, productos);
                    break;
                case 4:
                    ProductoServicios.filtrarProductos(scanner, productos);
                    break;
                case 5:
                    ProductoServicios.modificarProducto(scanner, productos);
                    break;
                case 6:
                    ProductoServicios.eliminarProducto(scanner, productos);
                    break;
                case 0:
                    System.out.println("\n\t[!] -- Finalizando...\n");
                    break;
                default:
                    //System.out.println("\t[ "+ opcion + " ] No es una opción válida.");
                    break;
            }
            if (opcion!=0){
                System.out.print("[ENTER] -- Para volver al menú principal...");
                scanner.nextLine();
                System.out.println();
            }
        } while (opcion != 0);

        scanner.close();
        System.out.println(">> Fin. ¡Tenga un buen día!");
    }
}