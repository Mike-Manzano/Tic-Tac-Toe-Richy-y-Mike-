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
        while ((ficha != 1) && (ficha != 2)) {
            System.out.println("Input incorrecto. Introduce 1 o 2 para elegir tu ficha");
            ficha = sc.nextInt();
        }

        turnoColocar(ficha, tablero);//Jugamos turnos hasta colocar 3 fichas.
        if (!ganar(tablero)) {//Si al colocar 3 fichas nadie ha ganado, procedemos a moverlas
            turnoMover(ficha, tablero);//Se siguen moviendo piezas hasta ganar
        }
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

    public static char fichaJugador(int ficha) {
        switch (ficha) {
            case 1:
                return 'X';
            case 2:
                return 'O';
            default:
                return '*';
        }
    }

    public static char fichaMaquina(int ficha) {
        switch (ficha) {
            case 1:
                return 'O';
            case 2:
                return 'X';
            default:
                return '*';
        }
    }

    public static void turnoColocar(int ficha, char tablero[][]) {
        Scanner sc = new Scanner(System.in);
        do {
            colocarJugador(ficha, tablero);
            mostrarMatriz(tablero);
            if (comprobadorJugador(tablero, ficha)) {//Comprobamos si hay 3 fichas
                if (ganar(tablero)) {//En caso de haberlas, comprobamos si el jugador ha 
                    System.out.println("¡Felicidades, has ganado!");
                }
            }

            colocarMaquina(ficha, tablero);//La máquina va a colocar ahora su ficha, gane o pierda. De no hacerlo, podría quedarse en 2 fichas y forzar otro bucle del do while.

//Ahora necesitamos que no ejecute el resto del bucle si el jugador ha ganado, pero solo si hay 3 fichas o mas. De haber menos, Ganar siempre saldra True.
            if ((comprobadorJugador(tablero, ficha) && !ganar(tablero)) || (!comprobadorJugador(tablero, ficha))) {//En caso de que el jugador no haya ganado,mostramos el movimiento de la máquina ya realizado.
                System.out.println("Es el turno de la máquina. Este es su movimiento:");
                mostrarMatriz(tablero);

                if (comprobadorMaquina(tablero, ficha)) {//Comprobamos que la máquina haya puesto 3 fichas
                    if (ganar(tablero)) {//En caso de que tenga 3 fichas, comprobamos si ha ganado
                        System.out.println("Lo sentimos, la máquina ha ganado");
                    }
                }
                System.out.println();

            }
        } while (!comprobadorJugador(tablero, ficha) || !comprobadorMaquina(tablero, ficha));
    }

    public static void turnoMover(int ficha, char tablero[][]) {
        char fichaJugador = '.';

        Scanner sc = new Scanner(System.in);

        do {
            moverJugador(ficha, tablero);
            mostrarMatriz(tablero);

            if (ganar(tablero)) {
                System.out.println("¡Felicidades, has ganado!");
            } else {
                System.out.println("Es el turno de la máquina. Este es su movimiento:");
                moverMaquina(ficha, tablero);
                mostrarMatriz(tablero);
                if (ganar(tablero)) {
                    System.out.println("Lo sentimos: La máquina ha ganado");
                }
            }

            System.out.println("");
        } while (!ganar(tablero));
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
        tablero[fil][col] = fichaMaquina(ficha);
    }

    public static void colocarJugador(int ficha, char tablero[][]) {
        int filPoner;
        int colPoner;
        Scanner sc = new Scanner(System.in);

        System.out.println("Escoge la fila en la que vas a colocar la ficha");
        filPoner = sc.nextInt();
        System.out.println("Escoge la columna en la que vas a colocar la ficha");
        colPoner = sc.nextInt();

        tablero[filPoner - 1][colPoner - 1] = fichaJugador(ficha);

    }

    /**
     *
     * @param ficha
     * @param tablero
     *
     */
    public static void moverMaquina(int ficha, char tablero[][]) {
        int fil;
        int col;
        int filSelect = 0;
        int colSelect = 0;
        char fichaMaquina = fichaMaquina(ficha);

        Scanner sc = new Scanner(System.in);

        if (fichaMaquina == 'O') {
            // Este if decide entre dos casos: La máquina tiene asignada O o X. 
            //En cada uno de los dos casos busca un espacio que tenga una ficha del tipo que le corresponde

            do {
                filSelect = (int) (Math.random() * 3);
                colSelect = (int) (Math.random() * 3);
            } while (tablero[filSelect][colSelect] != 'O');
        } else if (fichaMaquina == 'X') {
            do {
                filSelect = (int) (Math.random() * 3);
                colSelect = (int) (Math.random() * 3);
            } while (fichaMaquina != 'X');

        }

        tablero[filSelect][colSelect] = '*'; //Dejamos en blanco el espacio de la ficha seleccionada para poder colocarla en otro espacio

        fil = (int) (Math.random() * 3);//Seleccionamos espacio aleatorio y comprobamos que no esté ocupado
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

        tablero[fil][col] = fichaMaquina;//Colocamos la pieza correspondiente

    }

    public static void moverJugador(int ficha, char tablero[][]) {
        int filSelect;
        int colSelect;
        int filMover;
        int colMover;
        char fichaJugador = fichaJugador(ficha);

        Scanner sc = new Scanner(System.in);

        System.out.println("Escoge la fila de la ficha que quieres mover");//Solicitud de posición de la ficha a mover
        filSelect = sc.nextInt();
        System.out.println("Escoge la columna de la ficha que quieres mover");
        colSelect = sc.nextInt();
        while (tablero[filSelect - 1][colSelect - 1] != fichaJugador) {//Comprobación para evitar que seleccione espacios no ocupados por sus fichas
            System.out.println("Esta posición no contiene una de tus fichas. Introduce otra posición");
            System.out.println("Escoge la fila de la ficha que quieres mover");
            filSelect = sc.nextInt();
            System.out.println("Escoge la columna de la ficha que quieres mover");
            colSelect = sc.nextInt();
        }
        tablero[filSelect - 1][colSelect - 1] = '*';

        System.out.println("Escoge la fila en la que vas a colocar la ficha");//Solicitud de posición a la que moverse
        filMover = sc.nextInt();
        System.out.println("Escoge la columna en la que vas a colocar la ficha");
        colMover = sc.nextInt();
        while (tablero[filMover - 1][colMover - 1] != '*') {//Comprobación para evitar que seleccione espacios no disponibles
            System.out.println("Esta posición no está vacía. Introduce otra posición");
            System.out.println("Escoge la fila de la ficha que quieres mover");
            filMover = sc.nextInt();
            System.out.println("Escoge la columna de la ficha que quieres mover");
            colMover = sc.nextInt();
        }

        tablero[filMover - 1][colMover - 1] = fichaJugador;
    }

    public static boolean ganar(char tablero[][]) {
        int contadorX = 0;
        int contadorY = 0;
        char ficha = 'X';
        char ficha2 = 'O';

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ficha) {
                    contadorX++;
                } else if (tablero[i][j] == ficha2) {
                    contadorY++;
                }
                if (contadorX == 3 || contadorY == 3) {
                    return true;
                }
            }
            contadorX = 0;
            contadorY = 0;
        }
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[j][i] == ficha) {
                    contadorX++;
                } else if (tablero[j][i] == ficha2) {
                    contadorY++;
                }
                if (contadorX == 3 || contadorY == 3) {
                    return true;
                }

            }
            contadorX = 0;
            contadorY = 0;
        }
        for (int i = 0, j = 0; i < tablero.length; i++, j++) {
            if (tablero[j][i] == ficha) {
                contadorX++;
            } else if (tablero[j][i] == ficha2) {
                contadorY++;
            }
            if (contadorX == 3 || contadorY == 3) {
                return true;
            }
        }
        contadorX = 0;
        contadorY = 0;

        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == ficha) {
                contadorX++;
            } else if (tablero[j][i] == ficha2) {
                contadorY++;
            }
            if (contadorX == 3 || contadorY == 3) {
                return true;
            }

        }
        return false;


        /*
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (j == 0) {
                    if (tablero[i][j] == tablero[i][j + 1] && tablero[i][j] == tablero[i][j + 2]) {
                        return true;
                    }

                }
                if (i == 0) {
                    if (tablero[i][j] == tablero[i + 1][j] && tablero[i][j] == tablero[i + 2][j]) {
                        return true;
                    }
                }
                if (j == 1 && i == 1) {
                    if (tablero[i][j] == tablero[i - 1][j - 1] && tablero[i][j] == tablero[i + 1][j + 1]) {
                        return true;
                    }
                    if (tablero[i][j] == tablero[i - 1][j + 1] && tablero[i][j] == tablero[i + 1][j - 1]) {
                        return true;
                    }
                }

            }
        }
        return false;
         */
    }

    public static boolean comprobadorJugador(char mat[][], int ficha) {
        int contador = 0;
        int fichaJugador = fichaJugador(ficha);
        boolean xPuestas = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == fichaJugador) {
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

    public static boolean comprobadorMaquina(char mat[][], int ficha) {
        int contador = 0;
        int fichaMaquina = fichaMaquina(ficha);
        boolean xPuestas = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == fichaMaquina) {
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
