/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.proyecto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pablo
 */
public class ArbolBinario {
    Jugador[] arbol;
    int tam;
    int numRondas;  
    int cantMaxJugadores;
    int nivelActualArbol;
    
    public ArbolBinario(int nRondas) {
        this.numRondas = nRondas;
        this.nivelActualArbol = nRondas;
        tam = (int) Math.pow(2,(nRondas + 1))-1;
        this.arbol = new Jugador[tam];
        for (int i = 0; i < tam; i++) {
            arbol[i] = new Jugador(false);
        }
        this.cantMaxJugadores = (int) Math.pow(2,numRondas);
    }

    public void insertarJugador(Jugador jugador){
        int contNumJugadores = 0; 
        if(contNumJugadores < cantMaxJugadores){
            arbol[tam-1-contNumJugadores] = jugador;
            contNumJugadores ++; 
        }else{
            System.out.println("No se pueden insertar mas jugadores");
        }
    }
    
    public void abrirMenu(){
        Scanner scanner = new Scanner(System.in);
        String ganador;
        
        for (int i = 0; i < numRondas; i++) {
            System.out.println("Inserte el/los ganadores de la ronda: " + (i+1));
            System.out.println("Partidos en esta ronda: " + ((int) Math.pow(2, numRondas-i))/2);
            ganador = scanner.nextLine();
            switch(ganador){
                
            }
        }
    }
    
    // subirDesdeIzq = (i-1)/2;
    // subirDesdeDer = (i-2)/2;
    // 0 -> i * 2 + 1
    // 1 -> i * 2 + 2 
    
    public void asignarJugadorGanador(String nombre, String apellido){
        boolean ladoIzq = false;
        boolean jugadorEncontrado = false;
        // For que recorre cada hoja
        for (int i = 0; i < cantMaxJugadores; i++) {
            int pos = 0;
            // Transformo el numero de hoja que quiero llegar a binario para recorrer el arbol hasta llegar ahí
            String numBinarios = String.format("%" + (nivelActualArbol) + "s", Integer.toBinaryString(i)).replace(' ', '0');
            // Los 0 son un movimiento a la Izquierda y los 1 a la derecha
            for (int j = 0; j < nivelActualArbol; j++) {
                char n = numBinarios.charAt(j);
                if (n == '0') {
                    pos = pos * 2 + 1;
                    ladoIzq = true;
                }else{
                    pos = pos * 2 + 2;
                    ladoIzq = false;
                }  
            }
            Jugador jug = arbol[pos];
            // verifico si es el jugador que estoy buscando y si se encuentra en la ronda actual o ya ha pasado a la siguiente
            if (jug.getNombre().equals(nombre) && jug.getApellido().equals(apellido) && jug.getNivelActual() == nivelActualArbol) {
                jugadorEncontrado = true;
                if (ladoIzq == true) {
                    arbol[(i-1)/2].setNombre(nombre);
                    arbol[(i-1/2)].setApellido(apellido);
                }else{
                    arbol[(i-2)/2].setNombre(nombre);
                    arbol[(i-2)/2].setApellido(apellido);
                }
            }
        }
        if (jugadorEncontrado) {
            System.out.println("Se ha asignado como ganador al jugador: " + nombre + " " + apellido + ", en la ronda: " + (numRondas-nivelActualArbol+1));
        }
    }
    
    public void mostrar(){
        for (int i = 0; i < tam; i++) {
            System.out.print(arbol[i].toString() + ",");
        }
    }
    
    public void imprimirRonda(int ronda){
        System.out.println("PARTIDOS DE LA RONDA: " + ronda);
        System.out.println("* * * * * * * * * * * * ");
        
    }
}
