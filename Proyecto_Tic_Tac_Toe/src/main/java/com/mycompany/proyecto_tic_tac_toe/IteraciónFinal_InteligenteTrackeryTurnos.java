/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tic_tac_toe;

import java.util.Scanner;

/**
 *
 * @author mikew
 */
public class IteraciónFinal_InteligenteTrackeryTurnos {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Comenzamos proyecto Tres En Raya");
        char tablero[][] = new char[3][3];
        int trackerJugador[] = new int[2];//Variables para llevar el recuento de numero de movimientos y numero de oportunidades de ganar del jugador
        int trackerMaquina[] = new int[2];//y de la máquina. El primero hueco de los arrays cuenta los movimientos, y el segundo las oportunidades de victoria.
        rellenarTablero(tablero);
        System.out.println("Elije empezar primero o segundo");
        System.out.println("1.Primero");
        System.out.println("2.Segundo");
        int empezar = sc.nextInt();
        turno(empezar, tablero, trackerJugador, trackerMaquina);
    }

    /**
     * Este metodo administra los turnos, con un switch que nos dirije a
     * distintos métodos en función de si empieza el jugador, o empieza la
     * máquina.
     *
     * @param empezar
     * @param tablero
     * @param trackerJugador
     */
    public static void turno(int empezar, char tablero[][], int trackerJugador[], int trackerMaquina[]) {
        Scanner sc = new Scanner(System.in);
        switch (empezar) {
            case 1: {
                System.out.println("Elije la ficha");
                System.out.println("1. Ficha X");
                System.out.println("2. Ficha O");
                int ficha = sc.nextInt();//Selección de ficha de jugador
                while ((ficha != 1) && (ficha != 2)) {
                    System.out.println("Input incorrecto. Introduce 1 o 2 para elegir tu ficha");
                    ficha = sc.nextInt();
                }

                turnoColocarPrimero(ficha, tablero, trackerJugador, trackerMaquina);//Jugamos turnos hasta colocar 3 fichas.
                if (!ganar(tablero)) {//Si al colocar 3 fichas nadie ha ganado, procedemos a moverlas
                    turnoMoverPrimero(ficha, tablero, trackerJugador, trackerMaquina);//Se siguen moviendo piezas hasta ganar
                }
            }
            break;
            case 2: {
                System.out.println("Elije la ficha");
                System.out.println("1. Ficha X");
                System.out.println("2. Ficha O");
                int ficha = sc.nextInt();//Selección de ficha de jugador
                while ((ficha != 1) && (ficha != 2)) {
                    System.out.println("Input incorrecto. Introduce 1 o 2 para elegir tu ficha");
                    ficha = sc.nextInt();
                }

                turnoColocarSegundo(ficha, tablero, trackerJugador, trackerMaquina);//Jugamos turnos hasta colocar 3 fichas.
                if (!ganar(tablero)) {//Si al colocar 3 fichas nadie ha ganado, procedemos a moverlas
                    turnoMoverSegundo(ficha, tablero, trackerJugador, trackerMaquina);//Se siguen moviendo piezas hasta ganar
                }
            }
            break;
        }

    }

    /**
     * Este método simplemente rellena el tablero con el caracter *, dejandolo
     * en blanco para poder empezar a jugar la partida y que al mostrar el
     * tablero el jugador pueda ver los espacios en blanco
     *
     * @param mat1
     */
    public static void rellenarTablero(char mat1[][]) {

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                mat1[i][j] = '*';

            }
        }

    }

    /**
     * Este método traduce la matriz tablero a una representación visual para
     * poder proporcionar feedback al jugador del estado de la partida y la
     * posición de las fichas.
     *
     * @param mat1
     */
    public static void mostrarMatriz(char mat1[][]) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                System.out.print(mat1[i][j] + " \t ");
            }
            System.out.println("");
        }
    }

    /**
     * Metodo sencillo que usa el input recogido previamente por el jugador para
     * asignarle una ficha.
     *
     * @param ficha
     * @return
     */
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

    /**
     * Metodo sencillo que asigna a la máquina la ficha opuesta a la que se le
     * ha asignado al jugador, basandose en el input recogido
     * previamente(ficha).
     *
     * @param ficha
     * @return
     */
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

    /**
     * Este método ejecuta los distintos pasos en orden que se necesitan para
     * realizar un turno en el que: El jugador empieza primero Las fichas se
     * están colocando, no moviendo
     *
     * @param ficha
     * @param tablero
     * @param trackerJugador
     */
    public static void turnoColocarPrimero(int ficha, char tablero[][], int trackerJugador[], int trackerMaquina[]) {
        Scanner sc = new Scanner(System.in);
        do {
            colocarJugador(ficha, tablero, trackerJugador);
            mostrarMatriz(tablero);

            if (!ganarJugador(tablero, ficha, trackerJugador)) {
                System.out.println("Es el turno de la maquina, este es su movimiento");
                colocarMaquinaInt(ficha, tablero, trackerMaquina);//La máquina va a colocar ahora su ficha, gane o pierda. De no hacerlo, podría quedarse en 2 fichas y forzar otro bucle del do while.
                mostrarMatriz(tablero);
                if (ganarMaquina(tablero, ficha, trackerMaquina)) {
                    System.out.println("Lo sentimos, ha ganado la maquina");
                    System.out.println("Estadísticas: ");
                    System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + trackerJugador[1] + " oportunidades de victoria");
                    System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + (trackerMaquina[1] - 1) + " oportunidades de victoria");
                    //Ajustamos con -1 para que no cuente la victoria como una oportunidad
                }
            } else {
                System.out.println("Felicidades, has ganado");
                System.out.println("Estadísticas: ");
                System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + (trackerJugador[1] - 1) + " oportunidades de victoria");
                System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + trackerMaquina[1] + " oportunidades de victoria");
            }

            System.out.println();

        } while (!comprobadorJugador(tablero, ficha));
    }

    /**
     * Este método ejecuta los distintos pasos en orden que se necesitan para
     * realizar un turno en el que: El jugador empieza segundo Las fichas se
     * están colocando, no moviendo
     *
     * @param ficha
     * @param tablero
     * @param trackerJugador
     */
    public static void turnoColocarSegundo(int ficha, char tablero[][], int trackerJugador[], int trackerMaquina[]) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("La máquina va a colocar su ficha: ");
            colocarMaquinaInt(ficha, tablero, trackerMaquina);//La máquina va a colocar ahora su ficha, gane o pierda. De no hacerlo, podría quedarse en 2 fichas y forzar otro bucle del do while.
            mostrarMatriz(tablero);

            if (!ganarMaquina(tablero, ficha, trackerMaquina)) {
                colocarJugador(ficha, tablero, trackerJugador);
                mostrarMatriz(tablero);
                if (ganarJugador(tablero, ficha, trackerJugador)) {
                    System.out.println("Felicidades, has ganado");
                    System.out.println("Estadísticas: ");
                    System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + (trackerJugador[1] - 1) + " oportunidades de victoria");
                    System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + trackerMaquina[1] + " oportunidades de victoria");
                    //Ajustamos con -1 para que no cuente la victoria como una oportunidad
                }
            } else {
                System.out.println("Lo sentimos, gano la maquina");
                System.out.println("Estadísticas: ");
                System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + trackerJugador[1] + " oportunidades de victoria");
                System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + (trackerMaquina[1] - 1) + " oportunidades de victoria");
                //Ajustamos con -1 para que no cuente la victoria como una oportunidad
            }

            System.out.println();

        } while (!comprobadorMaquina(tablero, ficha));
    }

    /**
     * Este método ejecuta los distintos pasos en orden que se necesitan para
     * realizar un turno en el que: El jugador empieza primero Las fichas se
     * están moviendo, dado que ya se han colocado 3 de cada.
     *
     * @param ficha
     * @param tablero
     * @param trackerJugador
     */
    public static void turnoMoverPrimero(int ficha, char tablero[][], int trackerJugador[], int trackerMaquina[]) {
        char fichaJugador = '.';

        Scanner sc = new Scanner(System.in);

        do {
            moverJugador(ficha, tablero, trackerJugador);
            mostrarMatriz(tablero);

            if (ganarJugador(tablero, ficha, trackerJugador)) {
                System.out.println("¡Felicidades, has ganado!");
                System.out.println("Estadísticas: ");
                System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + (trackerJugador[1] - 1) + " oportunidades de victoria");
                System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + trackerMaquina[1] + " oportunidades de victoria");
                //Ajustamos con -1 para que no cuente la victoria como una oportunidad
            } else {
                System.out.println("Es el turno de la máquina. Este es su movimiento:");
                moverMaquinaInt(ficha, tablero, trackerMaquina);
                mostrarMatriz(tablero);
                if (ganarMaquina(tablero, ficha, trackerMaquina)) {
                    System.out.println("Lo sentimos: La máquina ha ganado");
                    System.out.println("Estadísticas: ");
                    System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + trackerJugador[1] + " oportunidades de victoria");
                    System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + (trackerMaquina[1] - 1) + " oportunidades de victoria");
                    //Ajustamos con -1 para que no cuente la victoria como una oportunidad
                }
            }

            System.out.println("");
        } while ((!ganarMaquina(tablero, ficha, trackerMaquina)) && (!ganarJugador(tablero, ficha, trackerJugador)));
    }

    public static void turnoMoverSegundo(int ficha, char tablero[][], int trackerJugador[], int trackerMaquina[]) {
        char fichaJugador = '.';

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Es el turno de la máquina. Este es su movimiento:");
            moverMaquinaInt(ficha, tablero, trackerMaquina);
            mostrarMatriz(tablero);

            if (ganarMaquina(tablero, ficha, trackerMaquina)) {
                System.out.println("Lo sentimos: La máquina ha ganado");
                System.out.println("Estadísticas: ");
                System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + trackerJugador[1] + " oportunidades de victoria");
                System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + (trackerMaquina[1] - 1) + " oportunidades de victoria");
                //Ajustamos con -1 para que no cuente la victoria como una oportunidad
            } else {
                moverJugador(ficha, tablero, trackerJugador);
                mostrarMatriz(tablero);
            }
            if (ganarJugador(tablero, ficha, trackerJugador)) {
                System.out.println("¡Felicidades, has ganado!");
                System.out.println("Estadísticas: ");
                System.out.println("El jugador ha hecho " + trackerJugador[0] + " movimientos, y ha tenido " + (trackerJugador[1] - 1) + " oportunidades de victoria");
                System.out.println("La máquina ha hecho " + trackerMaquina[0] + " movimientos, y ha tenido " + trackerMaquina[1] + " oportunidades de victoria");
                //Ajustamos con -1 para que no cuente la victoria como una oportunidad
            }

            System.out.println("");
        } while ((!ganarMaquina(tablero, ficha, trackerMaquina)) && (!ganarJugador(tablero, ficha, trackerJugador)));
    }

    /**
     * Este método coloca una ficha en el tablero por parte de la máquina, de
     * manera aleatoria y comprobando que su posición no está ocupada.
     *
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
     * Este metodo solicita al jugador una posición del tablero y le permite
     * colocar una ficha en dicha posición. También comprueba que la posición no
     * esté ocupada
     *
     * @param ficha
     * @param tablero
     */
    public static void colocarJugador(int ficha, char tablero[][], int trackerJugador[]) {
        int filPoner;
        int colPoner;
        char fichaJugador = fichaJugador(ficha);
        Scanner sc = new Scanner(System.in);
        trackerJugador[0]++;//Incrementamos el contador de turnos del jugador.

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
     * Este método realiza un movimiento aleatorio por parte de la máquina una
     * vez esta ha colocado tres fichas,moviendo su ficha correspondiente y
     * comprobando que los lugares a los que mueve no estén ocupados
     * previamente. Modifica la matriz de tablero de acuerdo a este movimiento
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
            } while (tablero[filSelect][colSelect] != 'X');

        }

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
        tablero[filSelect][colSelect] = '*'; //Dejamos en blanco el espacio de la ficha seleccionada para poder colocarla en otro espacio
        tablero[fil][col] = fichaMaquina;//Colocamos la pieza correspondiente

    }

    /**
     * Este método realiza el turno del jugador en la situación de partida en la
     * que debe mover las fichas en vez de colocarlas. Como parametros solicita
     * la ficha que está usando el jugador y el tablero, y una vez ejecutado
     * modfica la matriz de tablero para reflejar el desplazamiento de la ficha.
     *
     * @param ficha
     * @param tablero
     */
    public static void moverJugador(int ficha, char tablero[][], int trackerJugador[]) {
        int filSelect;
        int colSelect;
        int filMover;
        int colMover;
        char fichaJugador = fichaJugador(ficha);
        trackerJugador[0]++;
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

        while ((filSelect == 2 && colSelect == 2) && tablero[filSelect - 1][colSelect - 1] == fichaJugador) {//Evitamos que mueva la ficha central
            System.out.println("En esta modalidad no es posible mover la ficha central. Elige otra ficha a mover");
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
     * Iteración de el método ganar documentado más arriba. Esta iteración es
     * específica para jugador o máquina, dado que incluye seguimiento
     * específico de cada uno Este método no solo comprueba si el jugador ha
     * ganado, sino que registra el numero de movimientos y "jaques" que ha
     * realizado.
     *
     * @param tablero
     * @param tipoFicha
     * @param trackerJugador
     * @return Devuelve true si el jugador gana la partida.
     */
    public static boolean ganarJugador(char tablero[][], int tipoFicha, int trackerJugador[]) {
        int contadorFichaJ = 0;
        int contadorFichaM = 0;
        char fichaM = fichaMaquina(tipoFicha);
        char fichaJ = fichaJugador(tipoFicha);

        for (int i = 0; i < tablero.length; i++) {//hacemos las lecturas en filas de victoria
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == fichaJ) {//si encuentra la ficha se sumara 1 al contador
                    contadorFichaJ++;
                } else if (tablero[i][j] == fichaM) {//si encuentra la ficha se sumara 1 al contador
                    contadorFichaM++;
                }
                if (contadorFichaJ == 3) {//si uno de los dos llega a 3 significa que tiene 3 en raya de fila y se devuelve true 
                    return true;
                } else if (contadorFichaJ == 2 && contadorFichaM == 0) {//Si el contador alcanza 2, significa que ha habido un jaque, subimos el contador del tracker
                    trackerJugador[1]++;
                }

            }//si no se detecta en la primera fila la victoria se resetearan los contadores a 0 y se volvera a repetir
            contadorFichaJ = 0;
            contadorFichaM = 0;
        }

        for (int i = 0; i < tablero.length; i++) {//en este se haria lo mismo que en el antierior pero con las comprobaciuones de columnas
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[j][i] == fichaJ) {
                    contadorFichaJ++;
                } else if (tablero[j][i] == fichaM) {
                    contadorFichaM++;
                }
                if (contadorFichaJ == 3) {
                    return true;
                } else if (contadorFichaJ == 2 && contadorFichaM == 0) {
                    trackerJugador[1]++;
                }

            }
            contadorFichaJ = 0;
            contadorFichaM = 0;
        }
        //en este for haremos una comprobacion en diagonal de la parte superior izquierda a la inferior derecha 
        //ya que la primera es 0,0 y la ultima 3,3 no es necesario el doble for
        for (int i = 0, j = 0; i < tablero.length; i++, j++) {
            if (tablero[j][i] == fichaJ) {
                contadorFichaJ++;
            } else if (tablero[j][i] == fichaJ) {
                contadorFichaJ++;
            }
            if (contadorFichaJ == 3) {
                return true;
            } else if (contadorFichaJ == 2 && contadorFichaM == 0) {
                trackerJugador[1]++;
            }
        }

        //se deben resetear los contadores a 0 siempre que se haga una comprobacion nueva
        contadorFichaJ = 0;
        contadorFichaM = 0;

        //en este for haremos una comprobacion en diagonal de la parte superior derecha a la inferior izquierda 
        //ya que la primera es 0,2 y la ultima 2,0, se ira restando a uno y sumando a otro 
        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == fichaJ) {
                contadorFichaJ++;

            } else if (tablero[j][i] == fichaM) {
                contadorFichaM++;
            }
            if (contadorFichaJ == 3) {
                return true;
            } else if (contadorFichaJ == 2 && contadorFichaM == 0) {
                trackerJugador[1]++;
            }

        }
        return false;
    }

    public static boolean ganarMaquina(char tablero[][], int tipoFicha, int trackerMaquina[]) {
        int contadorFichaJ = 0;
        int contadorFichaM = 0;
        char fichaM = fichaMaquina(tipoFicha);
        char fichaJ = fichaJugador(tipoFicha);

        for (int i = 0; i < tablero.length; i++) {//hacemos las lecturas en filas de victoria
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == fichaM) {//si encuentra la ficha se sumara 1 al contador
                    contadorFichaM++;
                } else if (tablero[i][j] == fichaJ) {//si encuentra la ficha se sumara 1 al contador
                    contadorFichaJ++;
                }
                if (contadorFichaM == 3) {//si uno de los dos llega a 3 significa que tiene 3 en raya de fila y se devuelve true 
                    return true;
                } else if (contadorFichaM == 2 && contadorFichaJ == 0) {//Si el contador alcanza 2, significa que ha habido un jaque, subimos el contador del tracker
                    trackerMaquina[1]++;
                }

            }//si no se detecta en la primera fila la victoria se resetearan los contadores a 0 y se volvera a repetir
            contadorFichaJ = 0;
            contadorFichaM = 0;
        }
        for (int i = 0; i < tablero.length; i++) {//en este se haria lo mismo que en el antierior pero con las comprobaciuones de columnas
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[j][i] == fichaM) {
                    contadorFichaM++;
                } else if (tablero[j][i] == fichaJ) {
                    contadorFichaJ++;
                }

                if (contadorFichaM == 3) {
                    return true;
                } else if (contadorFichaM == 2 && contadorFichaJ == 0) {
                    trackerMaquina[1]++;
                }

            }
            contadorFichaJ = 0;
            contadorFichaM = 0;
        }
        //en este for haremos una comprobacion en diagonal de la parte superior izquierda a la inferior derecha 
        //ya que la primera es 0,0 y la ultima 3,3 no es necesario el doble for
        for (int i = 0, j = 0; i < tablero.length; i++, j++) {
            if (tablero[j][i] == fichaM) {
                contadorFichaM++;
            } else if (tablero[j][i] == fichaM) {
                contadorFichaM++;

                if (contadorFichaM == 3) {
                    return true;
                } else if (contadorFichaM == 2 && contadorFichaJ == 0) {
                    trackerMaquina[1]++;
                }
            }
        }
        //se deben resetear los contadores a 0 siempre que se haga una comprobacion nueva
        contadorFichaJ = 0;
        contadorFichaM = 0;

        //en este for haremos una comprobacion en diagonal de la parte superior derecha a la inferior izquierda 
        //ya que la primera es 0,2 y la ultima 2,0, se ira restando a uno y sumando a otro 
        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == fichaM) {
                contadorFichaM++;
            } else if (tablero[j][i] == fichaJ) {
                contadorFichaJ++;
            }

            if (contadorFichaM == 3) {
                return true;
            } else if (contadorFichaM == 2 && contadorFichaJ == 0) {
                trackerMaquina[1]++;
            }

        }
        return false;
    }

    /**
     * Este método recorre el tablero y comprueba si el jugador ha colocado tres
     * fichas, para pasar de colocar fichas a desplazarlas posteriormente.
     * (ColocarJugador ---> moverJugador)
     *
     * @param mat
     * @param ficha
     * @return Devuelve true si hay tres fichas de la máquina presentes en el
     * tablero.
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

        if (contador == 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método recorre el tablero y comprueba si la máquina ha colocado tres
     * fichas, para pasar de colocar fichas a desplazarlas posteriormente.
     *
     * @param mat
     * @param ficha
     * @return Devuelve true si hay tres fichas de la máquina presentes en el
     * tablero.
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

        if (contador == 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método permite a la máquina colocar una ficha, pero en vez de
     * hacerlo de forma aleatoria, lee la situación de tablero. Si encuentra que
     * puede ganar, ejecuta un movimiento que le da la victoria. Si encuentra
     * que puede perder, ejecuta un movimiento que impide la derrota. Si no
     * encuentra ninguno pero el centro del tablero está disponible, lo toma por
     * superioridad estratégica. Si nada de esto se cumple,realiza un movimiento
     * aleatorio.
     *
     * @param ficha
     * @param tablero
     */
    public static void colocarMaquinaInt(int ficha, char tablero[][], int trackerMaquina[]) {
        int fil;
        int col;
        int contadorFichaJ = 0;
        int contadorFichaM = 0;
        char fichaJugador = fichaJugador(ficha);
        char fichaMaquina = fichaMaquina(ficha);
        boolean fichaColocada = false;//Variable que evita que se pongan mas de una ficha por ciclo de este método
        int posVacia[] = new int[2];//Variable que guarda las coordenadas de una posición vacía para poder llenarla 
        //si hay dos fichas del mismo tipo en las misma linea
        int contVacia = 0;//Variable que registra cuantas posiciones vacías hay por linea

        trackerMaquina[0]++;//Incrementamos el contador de turnos de la maquina

        for (int i = 0; i < tablero.length; i++) {//Comprobación de jaques por filas
            //Reset de variables por cada iteración
            contadorFichaJ = 0;
            contadorFichaM = 0;
            posVacia[0] = -1;
            posVacia[1] = -1;
            contVacia = 0;

            for (int j = 0; j < tablero[i].length; j++) {
                if ((tablero[i][j] == fichaJugador)) {//Este bucle registra cada fila y guarda en las variables lo que va encontrando
                    contadorFichaJ++;
                } else if ((tablero[i][j] == fichaMaquina)) {
                    contadorFichaM++;
                } else {
                    posVacia[0] = i;
                    posVacia[1] = j;
                    contVacia++;
                }
            }
            //En base a las variables, se toma una decisión
            if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
                fichaColocada = true;
            } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
                fichaColocada = true;
            }
        }

        for (int i = 0; i < tablero.length; i++) {//Mismo procedimiento que en el bucle anterior pero con columnas
            //Reset de variables por cada iteración
            contadorFichaJ = 0;
            contadorFichaM = 0;
            posVacia[0] = -1;
            posVacia[1] = -1;
            contVacia = 0;

            for (int j = 0; j < tablero[i].length; j++) {

                if (tablero[j][i] == fichaJugador) {//Este bucle registra cada columna y guarda en las variables lo que va encontrando
                    contadorFichaJ++;
                } else if (tablero[j][i] == fichaMaquina) {
                    contadorFichaM++;
                } else {
                    posVacia[0] = j;
                    posVacia[1] = i;
                    contVacia++;
                }
            }
            //En base a las variables, se toma una decisión
            if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
                fichaColocada = true;
            } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
                fichaColocada = true;
            }
        }
        //comprobador para jaques diagonales (1,1),(2,2),(3,3)
        //Reset de variables
        contadorFichaJ = 0;
        contadorFichaM = 0;
        posVacia[0] = -1;
        posVacia[1] = -1;
        contVacia = 0;

        for (int i = 0, j = 0; i < tablero.length; i++, j++) {

            if (tablero[j][i] == fichaJugador) {//Comprobación de diagonal y cambio de variables
                contadorFichaJ++;
            } else if (tablero[j][i] == fichaMaquina) {
                contadorFichaM++;
            } else {
                posVacia[0] = j;
                posVacia[1] = i;
                contVacia++;
            }
        }
        if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
            fichaColocada = true;
        } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
            fichaColocada = true;
        }

        //Comprobador para jaques diagonales (1,3)(2,2),(3,1)
        //Reset de variables
        contadorFichaJ = 0;
        contadorFichaM = 0;
        posVacia[0] = -1;
        posVacia[1] = -1;
        contVacia = 0;

        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == fichaJugador) {//Comprobación de diagonal y cambio de variables
                contadorFichaJ++;
            } else if (tablero[j][i] == fichaMaquina) {
                contadorFichaM++;
            } else {
                posVacia[0] = j;
                posVacia[1] = i;
                contVacia++;
            }

        }
        //Toma de decisiones
        if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
            fichaColocada = true;
        } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
            fichaColocada = true;
        }

        if ((tablero[1][1] == '*') && (!fichaColocada)) {//Si no encuentra jaques pero el centro está disponible, coloca allí por ventaja estratégica
            tablero[1][1] = fichaMaquina;
            fichaColocada = true;
        }

        if (!fichaColocada) {//Si despues de todos los comprobadores no decidido colocar ficha, la coloca de forma aleatoria
            fil = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
            while (tablero[fil][col] != '*') {
                fil = (int) (Math.random() * 3);
                col = (int) (Math.random() * 3);
            }
            tablero[fil][col] = fichaMaquina;
        }
    }

    /**
     * Este método permite a la máquina colocar una ficha, pero en vez de
     * hacerlo de forma aleatoria, lee la situación de tablero. Si encuentra que
     * puede ganar, ejecuta un movimiento que le da la victoria. Evita mover
     * fichas que sean parte de este movimiento ganador para no estropearlo. Si
     * encuentra que puede perder, ejecuta un movimiento que impide la derrota.
     * Si no encuentra ninguno pero el centro del tablero está disponible, lo
     * toma por superioridad estratégica. Si nada de esto se cumple,realiza un
     * movimiento aleatorio.
     *
     * @param ficha
     * @param tablero
     */
    public static void moverMaquinaInt(int ficha, char tablero[][], int trackerMaquina[]) {
        int fil;
        int col;
        int filSelect = 0;
        int colSelect = 0;
        int contadorFichaJ = 0;
        int contadorFichaM = 0;
        char fichaJugador = fichaJugador(ficha);
        char fichaMaquina = fichaMaquina(ficha);
        int lineaFija = 0;// En caso de poder ganar, esta variable determinará si el jaque se ha encontrado en filas, columnas, o diagonales
        //Usaremos esto para que no pueda mover fichas de la linea con la que vamos a ganar(EJ: rellenar el tercer hueco de una fila para ganar la partida, y hacerlo moviendo
        //una ficha que tenemos en el primer hueco de esa columna, invalidando el movimiento ganador)

        int posicionColocada[] = new int[2];//Aquí guardaremos las coordenadas de donde se ha colocado ficha para poder calcular junto con lineaFija que posiciones no podemos mover

        //Para colocar ficha, realizamos el mismo proceso que en colocarFichaInt. Seleccionamos primero donde colocar y despues de donde quitar, para así evitar que la máquina
        //quite una ficha de un hueco, y la vuelva a poner en el mismo, lo que no sería un movimiento.
        boolean fichaColocada = false;//Variable que evita que se pongan mas de una ficha por ciclo de este método
        int posVacia[] = new int[2];//Variable que guarda las coordenadas de una posición vacía para poder llenarla 
        //si hay dos fichas del mismo tipo en las misma linea
        int contVacia = 0;//Variable que registra cuantas posiciones vacías hay por linea

        trackerMaquina[0]++;//Incrementamos el contador de turnos de la maquina

        for (int i = 0; i < tablero.length; i++) {//Comprobación de jaques por filas
            //Reset de variables por cada iteración
            contadorFichaJ = 0;
            contadorFichaM = 0;
            posVacia[0] = -1;
            posVacia[1] = -1;
            contVacia = 0;

            for (int j = 0; j < tablero[i].length; j++) {
                if ((tablero[i][j] == fichaJugador)) {//Este bucle registra cada fila y guarda en las variables lo que va encontrando
                    contadorFichaJ++;
                } else if ((tablero[i][j] == fichaMaquina)) {
                    contadorFichaM++;
                } else {
                    posVacia[0] = i;
                    posVacia[1] = j;
                    contVacia++;
                }
            }
            //En base a las variables, se toma una decisión
            if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
                fichaColocada = true;
                lineaFija = 1;
                posicionColocada[0] = posVacia[0];
                posicionColocada[1] = posVacia[1];

            } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
                fichaColocada = true;
            }
        }

        for (int i = 0; i < tablero.length; i++) {//Mismo procedimiento que en el bucle anterior pero con columnas
            //Reset de variables por cada iteración
            contadorFichaJ = 0;
            contadorFichaM = 0;
            posVacia[0] = -1;
            posVacia[1] = -1;
            contVacia = 0;

            for (int j = 0; j < tablero[i].length; j++) {

                if (tablero[j][i] == fichaJugador) {//Este bucle registra cada columna y guarda en las variables lo que va encontrando
                    contadorFichaJ++;
                } else if (tablero[j][i] == fichaMaquina) {
                    contadorFichaM++;
                } else {
                    posVacia[0] = j;
                    posVacia[1] = i;
                    contVacia++;
                }
            }
            //En base a las variables, se toma una decisión
            if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
                fichaColocada = true;
                lineaFija = 2;
                posicionColocada[0] = posVacia[0];
                posicionColocada[1] = posVacia[1];
            } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
                tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
                fichaColocada = true;
            }
        }
        //comprobador para jaques diagonales (1,1),(2,2),(3,3)
        //Reset de variables
        contadorFichaJ = 0;
        contadorFichaM = 0;
        posVacia[0] = -1;
        posVacia[1] = -1;
        contVacia = 0;

        for (int i = 0, j = 0; i < tablero.length; i++, j++) {

            if (tablero[j][i] == fichaJugador) {//Comprobación de diagonal y cambio de variables
                contadorFichaJ++;
            } else if (tablero[j][i] == fichaMaquina) {
                contadorFichaM++;
            } else {
                posVacia[0] = j;
                posVacia[1] = i;
                contVacia++;
            }
        }
        if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
            fichaColocada = true;
            lineaFija = 3;
            posicionColocada[0] = posVacia[0];
            posicionColocada[1] = posVacia[1];
        } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
            fichaColocada = true;
        }

        //Comprobador para jaques diagonales (1,3)(2,2),(3,1)
        //Reset de variables
        contadorFichaJ = 0;
        contadorFichaM = 0;
        posVacia[0] = -1;
        posVacia[1] = -1;
        contVacia = 0;

        for (int i = tablero.length - 1, j = 0; j < tablero.length; i--, j++) {
            if (tablero[j][i] == fichaJugador) {//Comprobación de diagonal y cambio de variables
                contadorFichaJ++;
            } else if (tablero[j][i] == fichaMaquina) {
                contadorFichaM++;
            } else {
                posVacia[0] = j;
                posVacia[1] = i;
                contVacia++;
            }

        }
        //Toma de decisiones
        if ((contadorFichaM == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;//Si encuentra un jaque propio, gana la partida automáticamente.Esto toma prioridad sobre evitar jaques
            fichaColocada = true;
            lineaFija = 4;
            posicionColocada[0] = posVacia[0];
            posicionColocada[1] = posVacia[1];
        } else if ((contadorFichaJ == 2) && (!fichaColocada) && (contVacia == 1) && (posVacia[0] != -1)) {//Si encuentra un jaque del jugador, lo evita
            tablero[posVacia[0]][posVacia[1]] = fichaMaquina;
            fichaColocada = true;
        }

        if ((tablero[1][1] == '*') && (!fichaColocada)) {//Si no encuentra jaques pero el centro está disponible, coloca allí por ventaja estratégica
            tablero[1][1] = fichaMaquina;
            fichaColocada = true;
        }

        if (!fichaColocada) {//Si despues de todos los comprobadores no decidido colocar ficha, la coloca de forma aleatoria
            fil = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
            while (tablero[fil][col] != '*') {
                fil = (int) (Math.random() * 3);
                col = (int) (Math.random() * 3);
            }
            tablero[fil][col] = fichaMaquina;
        }

        Scanner sc = new Scanner(System.in);
        switch (lineaFija) {

            case 1://En caso de que hayamos hecho tres en raya en una fila, evitará tomar fichas de la misma fila que la ficha que acabamos de colocar para no romper la victoria.
                do {
                    do {
                        filSelect = (int) (Math.random() * 3);
                    } while (filSelect == posicionColocada[0]);
                    colSelect = (int) (Math.random() * 3);
                } while (tablero[filSelect][colSelect] != fichaMaquina || colSelect == filSelect);
                tablero[filSelect][colSelect] = '*';
                break;
            case 2://Misma situación, pero para columnas.
                do {
                    filSelect = (int) (Math.random() * 3);
                    do {
                        colSelect = (int) (Math.random() * 3);
                    } while (colSelect == posicionColocada[1]);
                } while (tablero[filSelect][colSelect] != fichaMaquina || colSelect == filSelect);
                tablero[filSelect][colSelect] = '*';
                break;
            case 3://Aquí cubrimos las victorias en diagonales desde izquierda a derecha
                do {
                    do {
                        filSelect = (int) (Math.random() * 3);

                        colSelect = (int) (Math.random() * 3);
                    } while (colSelect == filSelect);
                } while (tablero[filSelect][colSelect] != fichaMaquina || colSelect == filSelect);
                tablero[filSelect][colSelect] = '*';
                break;
            case 4://Finalmente, cubrimos el caso en el que la diagonal va de derecha a izquierda
                do {
                    do {
                        filSelect = (int) (Math.random() * 3);
                        colSelect = (int) (Math.random() * 3);
                    } while (colSelect == filSelect || colSelect == (filSelect - 2) || colSelect == (filSelect + 2));
                } while (tablero[filSelect][colSelect] != fichaMaquina || colSelect == filSelect);
                tablero[filSelect][colSelect] = '*';
                break;
            default://De no haber realizado un movimiento ganador en este turno, cualquier ficha puede usarse para mover.
                do {

                    filSelect = (int) (Math.random() * 3);
                    colSelect = (int) (Math.random() * 3);

                } while (tablero[filSelect][colSelect] != fichaMaquina || (colSelect == 1 && filSelect == 1));
                tablero[filSelect][colSelect] = '*';

        }

    }
}

