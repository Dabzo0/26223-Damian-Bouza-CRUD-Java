package com.talento.crud.utilidades;

import java.util.Scanner;
import java.text.Normalizer;

public class CapturarEntrada {

    public static int opcion(Scanner scanner) {
        System.out.println("-> Ingrese una opción: ");
        String opcion = scanner.nextLine().trim();
        int opcionValida = -1;

        try {
                opcionValida = Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
                opcionValida = -1;
        }

        if (opcionValida < 0 || opcionValida > 6) {
            System.out.println("\t[X] -- Error: [ " + opcion + " ] No es una opción válida.");
            opcionValida=-1;
        } 

        return opcionValida;
    }

    public static int enteroNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor < 0) {
                    System.out.println("\t[X] -- Error: el valor no puede ser negativo.");
                    continue;
                }                
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("\t[X] -- Error: debe ingresar un número entero válido.");
            }
        }
    }

    public static String textoNoVacio(Scanner scanner, String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.println("\t[X] -- Error: el texto no puede estar vacío.");
        }
    }

    public static double doubleNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("\t[X] -- Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("\t[X] --Error: debe ingresar un número decimal válido.");
            }
        }
    }

    public static boolean confirmar(Scanner scanner) {
        System.out.print("-> [S] Para confirmar: ");
        String respuesta = scanner.nextLine().trim().toUpperCase();

        return respuesta.equals("S");
    }

    public static int categoria(Scanner scanner){
        int categoria = -1;
        do {
            System.out.println(">> Seleccione CATEGORIA del producto:");
            System.out.println("[1] - Almacén.");
            System.out.println("[2] - Indumentaria.");                     
            System.out.println("[0] - CANCELAR.");
            System.out.println("-> Ingrese una opción: ");
            String opcion = scanner.nextLine().trim();

            try {
                    categoria = Integer.parseInt(opcion);
                } catch (NumberFormatException e) {
                    categoria = -1;
            }

            if (categoria < 0 || categoria > 2) {
                System.out.println("\t[X] -- Error: [ " + opcion + " ] No es una categoria válida.");
                categoria = -1;
            } 
        } while ( categoria != 0);
        
        return categoria;
    }
    
    public static String fecha(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine().trim();

        if (entrada.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{2}$")) {
            return entrada;
        } else {
            return "Sin especificar.";
        }
    }
    
    public static String talle(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().toUpperCase(); 

            if (entrada.matches("^(XS|S|M|L|XL|XXL|U|UNICO|ÚNICO)$") || entrada.matches("^\\d{2}$")) {
                if (entrada.equals("U") || entrada.equals("UNICO")) {
                    return "ÚNICO";
                }
                return entrada;
            } else {
                System.out.println("\n\t[X] -- Talle inválido. Ingrese una medida estándar (S, M, L, XL, U, UNICO) o numérica (ej: 38).\n");
            }
        }
    }
    
    //Está función no corresposte a este archivo de Utilidades pero, por ahora, no se requiere crear un nuevo archivo para una sola función.
    public static String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}