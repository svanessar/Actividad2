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
            System.out.println(" ");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|              BY: Sindy Vanessa Realpe Rincon           |");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|              SISTEMA DE GESTION DE BIBLIOTECA          |");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|  1. Agregar Libro                                      |");
            System.out.println("|  2. Registrar Usuario                                  |");
            System.out.println("|  3. Prestar Libro                                      |");
            System.out.println("|  4. Devolver Libro                                     |");
            System.out.println("|  5. Mostrar Libros Disponibles                         |");
            System.out.println("|  6. Mostrar Usuarios Registrados                       |");
            System.out.println("|  7. Salir                                              |");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("Seleccione una opcion:");

            while (!entrada.hasNextInt()) {
                System.out.println("Error: Ingrese un numero valido!");
                entrada.next();
                System.out.println("Seleccione una opcion para continuar");
            }
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del libro (unico)");
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
                    System.out.println("Ingrese el ID del libro que desea llevar:");
                    String idPrestar = entrada.nextLine();

                    System.out.println("Ingrese la cédula del usuario que desea prestar el libro:");
                    while (!entrada.hasNextInt()) {
                        System.out.println("Error: Ingrese una cédula válida (SOLO NÚMEROS):");
                        entrada.next();
                    }
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();

                    // Verificar si el usuario está registrado
                    boolean usuarioRegistrado = false;
                    for (String[] usuario : usuarios) {
                        if (usuario[0].equals(String.valueOf(cedulaPrestar))) {
                            usuarioRegistrado = true;
                            break;
                        }
                    }

                    if (!usuarioRegistrado) {
                        System.out.println("Error: El usuario con cédula " + cedulaPrestar + " no está registrado. Registre el usuario para continuar.");
                    } else {
                        // Verificar si el libro está disponible
                        boolean libroEncontrado = false;
                        for (String[] libro : libros) {
                            if (libro[0].equals(idPrestar)) {
                                librosPrestados.push(new String[]{idPrestar, libro[1], libro[2], String.valueOf(cedulaPrestar)});
                                libros.remove(libro);
                                libroEncontrado = true;
                                System.out.println("¡Libro prestado con éxito!");
                                break;
                            }
                        }

                        if (!libroEncontrado) {
                            System.out.println("Libro no disponible. ¿Desea agregar a la cola de espera? (si/no)");
                            String respuesta = entrada.nextLine();
                            if (respuesta.equalsIgnoreCase("si")) {
                                colaEspera.add(new String[]{idPrestar, String.valueOf(cedulaPrestar)});
                                System.out.println("Agregado a la cola de espera.");
                            }
                        }
                    }
                    break;
                case 4:
                    if (!librosPrestados.isEmpty()) {
                        String[] libroDevuelto = librosPrestados.pop();
                        libros.add(new String[]{libroDevuelto[0], libroDevuelto[1], libroDevuelto[2]});
                        System.out.println("Libro devuelto exitosamente");

                    }
                    if (!colaEspera.isEmpty()) {
                        String[] proximaEnCola = colaEspera.poll();
                        System.out.println("El usuario con cedula" + proximaEnCola[1] + "esta en cola y ahora prestara el libro con ID" + proximaEnCola[0]);
                        librosPrestados.push(proximaEnCola);
                    } else {
                        System.out.println("No hay libros prestados");
                    }
                    break;
                case 5:
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros disponibles");
                    } else {
                        System.out.println("+-------------------Libros Disponibles------------------+");
                        System.out.printf("%15s %-20s %-30s%n", "ID", "Nombre", "Autor");
                        for (String[] libro : libros) {
                            System.out.printf("%15s %-20s %-30s%n", libro[0], libro[1], libro[2]);

                        }

                    }
                    break;
                case 6:
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios disponibles");
                    } else {
                        System.out.println("+------------------Usuarios Disponibles------------------+");
                        System.out.printf("%15s %-15s %-20s%n", "Cedula", "Nombre", "Apellido");
                        for (String[] usuario : usuarios) {
                            System.out.printf("%15s %-15s %-20s%n", usuario[0], usuario[1], usuario[2]);

                        }

                    }
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
