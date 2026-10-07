package com.talento.crud.utilidades;

import java.util.Scanner;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CapturarEntrada {

    public static int opcion(Scanner scanner) {
        System.out.print("-> Ingrese una opción: ");
        String opcion = scanner.nextLine().trim();
        int opcionValida = -1;

        try {
                opcionValida = Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
                opcionValida = -1;
        }

        if (opcionValida < 0 || opcionValida > 6) {
            System.out.println("\n\t[X] -- Error: [ " + opcion + " ] No es una opción válida.\n");
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
                    System.out.println("\n\t[X] -- Error: el valor no puede ser negativo.\n");
                    continue;
                }                
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("\n\t[X] -- Error: debe ingresar un número entero válido.\n");
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

            System.out.println("\n\t[X] -- Error: el texto no puede estar vacío.\n");
        }
    }

    public static double doubleNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("\n\t[X] -- Error: el precio no puede ser negativo.\n");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("\n\t[X] --Error: debe ingresar un número decimal válido.\n");
            }
        }
    }

    public static boolean confirmar(Scanner scanner) {
        System.out.print("-> [S] Para confirmar: ");
        String respuesta = scanner.nextLine().trim().toUpperCase();

        return respuesta.equals("S");
    }

    public static int categoria(Scanner scanner, int cantidadDeCategorias){
        int categoria = 0;
        String opcion = scanner.nextLine().trim();

        try {
            categoria = Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
            categoria = 0;
        }

        if (categoria < 1 || categoria > cantidadDeCategorias) {
            System.out.println("\n\t[X] -- Error: [ " + opcion + " ] No es una categoria válida.\n");
            categoria = 0;
        } 
        
        return categoria - 1;
    }
    
    public static String fecha(Scanner scanner, String mensaje) {
        String entrada = "";
        do{
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim().replace('.', '/').replace(' ', '/');
            
            try {
                LocalDate.parse(entrada, DateTimeFormatter.ofPattern("dd/MM/yy"));                 
            } catch (DateTimeParseException e) {
                System.out.println("\n\t[X] -- Error: [ " + entrada + " ] no es una fecha válida o real.\n");
                entrada = ""; 
            }

        } while (entrada.isEmpty());

        return entrada;
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
                System.out.println("\n\t[X] -- Talle inválido. Ingrese una medida estándar (S, M, L, XL, U, UNICO) o numérica (ej: 08).\n");
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