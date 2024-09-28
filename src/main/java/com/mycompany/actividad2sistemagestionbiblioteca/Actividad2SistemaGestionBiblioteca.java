/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.actividad2sistemagestionbiblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author vanessa
 */
public class Actividad2SistemaGestionBiblioteca {

    public static void main(String[] args) {

        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosPrestados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {
             System.out.println("Elaborado por: Sindy Vanessa Realpe Rincon");
            System.out.println("========================================");
            System.out.println("    SISTEMA DE GESTION DE BIBLIOTECA    ");
            System.out.println("========================================");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios registrados");
            System.out.println("7. Salir");
            System.out.println("Seleccione una opcion");

            while (!entrada.hasNextInt()) {
                System.out.println("Error: Ingrese un numero valido!");
                entrada.next();
                System.out.println("Seleccione una opcion para continuar");
            }
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del libro (único)");
                    String pkLibro = entrada.nextLine();
                    boolean pkDuplicado = false;
                    for (String[] libro : libros) {
                        if (libro[0].equals(pkLibro)) {
                            pkDuplicado = true;
                            break;
                        }
                    }
                    if (pkDuplicado) {
                        System.out.println("Error: el ID del libro ya se encuentra registrado!!");
                    } else {
                        System.out.println("Ingrese el nombre del libro: ");
                        String nombreLibro = entrada.nextLine();
                        System.out.println("Ingrese el autor del libro: ");
                        String autorLibro = entrada.nextLine();

                        libros.add(new String[]{pkLibro, nombreLibro, autorLibro});
                        System.out.println("Libro agregado con éxito!");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese la cedula del usuario a registrar: ");

                    while (!entrada.hasNextInt()) {
                        System.out.println("Error: Ingrese una cedula valida!");
                        entrada.next();
                        System.out.println("Ingrese la cedula del usuario para continuar (SOLO NUMEROS!!)");
                    }

                    int cedulaUsuarioInt = entrada.nextInt();
                    entrada.nextLine();
                    String cedulaUsuario = String.valueOf(cedulaUsuarioInt);

                    System.out.println("Ingrese el nombre del usuario");
                    String nombreUsuario = entrada.nextLine();
                    System.out.println("Ingrese los apellidos del usuario");
                    String apellidosUsuario = entrada.nextLine();

                    boolean cedulaDuplicada = false;
                    for (String[] usuario : usuarios) {
                        if (usuario[0].equals(cedulaUsuario)) {
                            cedulaDuplicada = true;
                            break;
                        }
                    }
                    if (cedulaDuplicada) {
                        System.out.println("Error: la cedula ya existe!!");
                    } else {
                        usuarios.add(new String[]{cedulaUsuario, nombreUsuario, apellidosUsuario});
                        System.out.println("Usuario registrado correctamente!!");
                    }
                    break;

                case 3:
                    // Lógica para prestar libro
                    break;
                case 4:
                    // Lógica para devolver libro
                    break;
                case 5:
                    // Lógica para mostrar libros disponibles
                    break;
                case 6:
                    // Lógica para mostrar usuarios registrados
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Error: Opción no válida!");
                    break;
            }
        } while (opcion != 7);

    }
}
