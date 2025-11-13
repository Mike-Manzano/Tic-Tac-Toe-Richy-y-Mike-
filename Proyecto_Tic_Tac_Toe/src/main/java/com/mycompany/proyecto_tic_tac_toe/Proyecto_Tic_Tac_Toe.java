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
        int trackerJugador[]= new int[2];
        int trackerMaquina[]= new int[2];
        rellenarTablero(tablero);
        System.out.println("Elije empezar primero o segundo");
        System.out.println("1.primero");
        System.out.println("2.Segundo");
        int empezar = sc.nextInt();

        System.out.println("Vamos a jugar una partida de tres en raya.");
        System.out.println("Elije la ficha");
        System.out.println("1. Ficha X");
        System.out.println("2. Ficha O");
        int ficha = sc.nextInt();
        while ((ficha != 1) && (ficha != 2)) {
            System.out.println("Input incorrecto. Introduce 1 o 2 para elegir tu ficha");
            ficha = sc.nextInt();
        }

        turnoColocarPrimero(ficha, tablero);//Jugamos turnos hasta colocar 3 fichas.
        if (!ganar(tablero)) {//Si al colocar 3 fichas nadie ha ganado, procedemos a moverlas
            turnoMoverPrimero(ficha, tablero);//Se siguen moviendo piezas hasta ganar
        }
        
    }

    public static void turno(int empezar, char tablero[][]) {
        Scanner sc = new Scanner(System.in);
        switch (empezar) {
            case 1:
                System.out.println("Elije la ficha");
                System.out.println("1. Ficha X");
                System.out.println("2. Ficha O");
                int ficha = sc.nextInt();
                while ((ficha != 1) && (ficha != 2)) {
                    System.out.println("Input incorrecto. Introduce 1 o 2 para elegir tu ficha");
                    ficha = sc.nextInt();
                }

                turnoColocarPrimero(ficha, tablero);//Jugamos turnos hasta colocar 3 fichas.
                if (!ganar(tablero)) {//Si al colocar 3 fichas nadie ha ganado, procedemos a moverlas
                    turnoMoverPrimero(ficha, tablero);//Se siguen moviendo piezas hasta ganar
                } else {
                    System.out.println("Felicidades ganaste");
                }
                break;
            case 2:
                break;
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

    public static void turnoColocarPrimero(int ficha, char tablero[][]) {
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

//Ahora necesitamos que no ejecute el resto del bucle si el jugador ha ganado.
            if ((!ganar(tablero))) {//En caso de que el jugador no haya ganado,mostramos el movimiento de la máquina ya realizado.
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
    /*
    public static void turnoColocarSegundo(int ficha, char tablero[][]) {
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
                
                //parque para mirar, creo que no hace falta porque tenemos comprobacion despues al salir del metodo(Ricardo)
                 
                System.out.println();

            }
        } while (!comprobadorJugador(tablero, ficha) || !comprobadorMaquina(tablero, ficha));
    }
    */

    public static void turnoMoverPrimero(int ficha, char tablero[][]) {
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
/**
 * Este método coloca una ficha en el tablero por parte de la máquina, de manera aleatoria y comprobando que su posición no está ocupada.
 * @param ficha
 * @param tablero 
 */
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
/**
 * Este metodo solicita al jugador una posición del tablero y le permite colocar una ficha en dicha posición. También comprueba que la posición no esté ocupada
 * @param ficha
 * @param tablero 
 */
    public static void colocarJugador(int ficha, char tablero[][]) {
        int filPoner;
        int colPoner;
        char fichaJugador= fichaJugador(ficha);
        Scanner sc = new Scanner(System.in);

        System.out.println("Escoge la fila en la que vas a colocar la ficha");
        filPoner = sc.nextInt();
        System.out.println("Escoge la columna en la que vas a colocar la ficha");
        colPoner = sc.nextInt();
        
        
        while (tablero[filPoner - 1][colPoner - 1] != '*') {//Comprobación para evitar que seleccione espacios ocupados
            System.out.println("Esta posición está ocupada. Introduce otra posición");
            System.out.println("Escoge la fila en la que vas a colocar la ficha");
            filPoner = sc.nextInt();
            System.out.println("Escoge la columna en la que vas a colocar la ficha");
            colPoner = sc.nextInt();
        }
        tablero[filPoner - 1][colPoner - 1] = fichaJugador;

    }

    /**
     *Este método realiza un movimiento aleatorio por parte de la máquina una vez esta ha colocado tres fichas,moviendo su ficha correspondiente y 
     * comprobando que los lugares a los que mueve no estén ocupados previamente.
     * Modifica la matriz de tablero de acuerdo a este movimiento
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

    /**
     * Este método realiza el turno del jugador en la situación de partida en la que debe mover las fichas en vez de colocarlas.
     * Como parametros solicita la ficha que está usando el jugador y el tablero, y una vez ejecutado modfica la matriz de tablero para reflejar el desplazamiento de la ficha.
     * @param ficha
     * @param tablero 
     */
    
    public static void moverJugador(int ficha, char tablero[][]) {
        int filSelect;
        int colSelect;
        int filMover;
        int colMover;
        char fichaJugador = fichaJugador(ficha);

        Scanner sc = new Scanner(System.in);
        System.out.println("Escoge la fila de la ficha que quieres mover");//Solicitud de posición de la ficha a mover
        filSelect = sc.nextInt();
        while (filSelect < 1 || filSelect > 3) {
            System.out.println("Valor no valido introduzca uno nuevo");
            filSelect = sc.nextInt();
        }
        System.out.println("Escoge la columna de la ficha que quieres mover");
        colSelect = sc.nextInt();
        while (colSelect < 1 || colSelect > 3) {
            System.out.println("Valor no valido introduzca uno nuevo");
            colSelect = sc.nextInt();
        }

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
        while (filMover < 1 || filMover > 3) {
            System.out.println("Valor no valido introduzca uno nuevo");
            colSelect = sc.nextInt();
        }
        System.out.println("Escoge la columna en la que vas a colocar la ficha");
        colMover = sc.nextInt();
        while (colMover < 1 || colMover > 3) {
            System.out.println("Valor no valido introduzca uno nuevo");
            colSelect = sc.nextInt();
        }
        while (tablero[filMover - 1][colMover - 1] != '*') {//Comprobación para evitar que seleccione espacios no disponibles
            System.out.println("Esta posición no está vacía. Introduce otra posición");
            System.out.println("Escoge la fila de la ficha que quieres mover");
            filMover = sc.nextInt();
            System.out.println("Escoge la columna de la ficha que quieres mover");
            colMover = sc.nextInt();
        }

        tablero[filMover - 1][colMover - 1] = fichaJugador;
    }

    /**
     * En este método haremos una lectura del tablero y detectara todas las
     * posibles combinaciones de victoria. 
     *
     * @param tablero
     * @return false o true
     */
    public static boolean ganar(char tablero[][]) {
        int contadorX = 0;
        int contadorO = 0;
        char ficha = 'X';
        char ficha2 = 'O';

        for (int i = 0; i < tablero.length; i++) {//hacemos las lecturas en filas de victoria
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ficha) {//si encuentra la ficha X se sumara 1 al contador X
                    contadorX++;
                } else if (tablero[i][j] == ficha2) {//si encuentra la ficha O se sumara 1 al contador O
                    contadorO++;
                }
                if (contadorX == 3 || contadorO == 3) {//si uno de los dos llega a 3 significa que tiene 3 en ralla de fila y se devielve true 
                    return true;
                }
            }//si no se detecta en la primera fila la victoria se resetearan los contadores a 0 y se volvera a repetir
            contadorX = 0;
            contadorO = 0;
        }
        for (int i = 0; i < tablero.length; i++) {//en este se haria lo mismo que en el antierior pero con las comprobaciuones de columnas
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[j][i] == ficha) {
                    contadorX++;
                } else if (tablero[j][i] == ficha2) {
                    contadorO++;
                }
                if (contadorX == 3 || contadorO == 3) {
                    return true;
                }
                contadorX = 0;
                contadorO = 0;

            }
            contadorX = 0;
            contadorO = 0;
        }
        //en este for haremos una comprobacion en diagonal de la parte superior izquierda a la inferior derecha 
        //ya que la primera es 0,0 y la ultima 3,3 no es necesatio el doble for
        for (int i = 0, j = 0; i < tablero.length; i++, j++) {
            if (tablero[j][i] == ficha) {
                contadorX++;
            } else if (tablero[j][i] == ficha2) {
                contadorO++;
            }
            if (contadorX == 3 || contadorO == 3) {
                return true;
            }
        }
        //se deben resetear los contadores a 0 siempre que se haga una comprobacion nueva
        contadorX = 0;
        contadorO = 0;

        //en este for haremos una comprobacion en diagonal de la parte superior derecha a la inferior izquierda 
        //ya que la primera es 0,2 y la ultima 2,0, se ira restando a uno y sumando a otro 
        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == ficha) {
                contadorX++;
            } else if (tablero[j][i] == ficha2) {
                contadorO++;
            }
            if (contadorX == 3 || contadorO == 3) {
                return true;
            }

        }
        return false;
    }

    /**
 * Este método recorre el tablero y comprueba si el jugador ha colocado tres fichas, para pasar de colocar fichas a desplazarlas posteriormente.
 * @param mat
 * @param ficha
 * @return Devuelve true si hay tres fichas de la máquina presentes en el tablero.
 */
    
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
/**
 * Este método recorre el tablero y comprueba si la máquina ha colocado tres fichas, para pasar de colocar fichas a desplazarlas posteriormente.
 * @param mat
 * @param ficha
 * @return Devuelve true si hay tres fichas de la máquina presentes en el tablero.
 */
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
    
    public static void trackerJugador(String[] args) {
        
    }
}
