/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto_tic_tac_toe;

import java.util.Scanner;

/**
 *
 * @author WEB1-34
 */
public class Proyecto_Tic_Tac_Toe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Comenzamos proyecto Tres En Raya");
        char tablero[][] = new char[3][3];
        rellenarTablero(tablero);
        System.out.println("Vamos a jugar una partida de tres en raya.");
        System.out.println("Elije la ficha");
        System.out.println("1. Ficha X");
        System.out.println("2. Ficha O");
        int ficha = sc.nextInt();

        colocarFicha(ficha, tablero);
        moverFicha(ficha, tablero);

        //Declaramos Scanner para interactuar con usuario
    }

    public static void colocarFicha(int ficha, char tablero[][]) {
        int filPoner;
        int colPoner;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Escoge la fila en la que vas a colocar la ficha");
            filPoner = sc.nextInt();
            System.out.println("Escoge la columna en la que vas a colocar la ficha");
            colPoner = sc.nextInt();
            switch (ficha) {
                case 1: {
                    tablero[filPoner - 1][colPoner - 1] = 'X';

                }
                break;
                case 2: {
                    tablero[filPoner - 1][colPoner - 1] = 'O';

                }
            }

            mostrarMatriz(tablero);
            System.out.println("Es el turno de la máquina. Este es su movimiento:");
            colocarMaquina(ficha, tablero);
            mostrarMatriz(tablero);
            System.out.println("*****************************************************************************");
        } while (!comprobadorX(tablero) || !comprobadorO(tablero));
    }

    public static void rellenarTablero(char mat1[][]) {

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                mat1[i][j] = '*';

            }
        }

    }

    public static void mostrarMatriz(char mat1[][]) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                System.out.print(mat1[i][j] + " \t ");
            }
            System.out.println("");
        }
    }

    public static void moverFicha(int ficha, char tablero[][]) {

        int filSelect;
        int colSelect;
        int filMover;
        int colMover;
        char fichaJugador;

        Scanner sc = new Scanner(System.in);

        switch (ficha) {
            case 1: {
                fichaJugador = 'X';

            }
            break;
            case 2: {
                fichaJugador = 'O';

            }
            //do {

                System.out.println("Escoge la fila de la ficha que quieres mover");
                filSelect = sc.nextInt();
                System.out.println("Escoge la columna de la ficha que quieres mover");
                colSelect = sc.nextInt();
                while (tablero[filSelect - 1][colSelect - 1] != fichaJugador) {
                    System.out.println("Esta posición no contiene una de tus fichas. Introduce otra posición");
                    System.out.println("Escoge la fila de la ficha que quieres mover");
                    filSelect = sc.nextInt();
                    System.out.println("Escoge la columna de la ficha que quieres mover");
                    colSelect = sc.nextInt();
                }
                tablero[filSelect - 1][colSelect - 1] = '*';

                System.out.println("Escoge la fila en la que vas a colocar la ficha");
                filMover = sc.nextInt();
                System.out.println("Escoge la columna en la que vas a colocar la ficha");
                colMover = sc.nextInt();

                tablero[filMover - 1][colMover - 1] = fichaJugador;

                mostrarMatriz(tablero);
                System.out.println("Es el turno de la máquina. Este es su movimiento:");
                //moverMaquina(ficha,tablero);
                mostrarMatriz(tablero);
            System.out.println("***********************************************************************");
            //} while (!winJugador || !winMaquina));
        }
    }
    

    public static void colocarMaquina(int ficha, char tablero[][]) {
        int fil;
        int col;
        fil = (int) (Math.random() * 3);
        col = (int) (Math.random() * 3);
        while (tablero[fil][col] != '*') {
            fil = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
            while (fil == 3) {
                fil = (int) (Math.random() * 3);

            }
            while (col == 3) {
                col = (int) (Math.random() * 3);
            }
        }
        switch (ficha) {
            case 1: {
                tablero[fil][col] = 'O';

            }
            break;
            case 2: {
                tablero[fil][col] = 'X';

            }
        }

    }

    public static void movimientoDesplaza(char mat1[][]) {

    }

    public static boolean comprobadorX(char mat[][]) {
        int contador = 0;
        boolean xPuestas = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 'X') {
                    contador++;
                }
            }
        }

        if (contador >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean comprobadorO(char mat[][]) {
        int contador = 0;
        boolean xPuestas = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 'O') {
                    contador++;
                }
            }
        }

        if (contador >= 3) {
            return true;
        } else {
            return false;
        }
    }
}
