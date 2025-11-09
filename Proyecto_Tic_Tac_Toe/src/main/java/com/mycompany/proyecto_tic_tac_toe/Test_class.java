/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tic_tac_toe;

/**
 *
 * @author WEB1-34
 */
public class Test_class {
    public static void main(String[] args) {
   int fil= 0;
        for (int i = 0; i < 20; i++) {
           do{ fil= 1 +((int)(Math.random()*(3)));
           }while (fil>3);
            
             System.out.println(fil);
        }

    }
}