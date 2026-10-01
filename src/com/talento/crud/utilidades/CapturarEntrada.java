package com.talento.crud.utilidades;

import java.util.Scanner;
import java.text.Normalizer;

public class CapturarEntrada {

    public static int opcion(Scanner scanner) {
        System.out.print("-> Irese una opción: ");
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

    //Está función no corresposte a este archivo de Utilidades pero, por ahora, no se requiere crear un nuevo archivo para una sola función.
    public static String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}