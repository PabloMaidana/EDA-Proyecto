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
    int numJugadores = 0;   
    int cantMaximaJugadores;
    
    public ArbolBinario(int nRondas) {
        this.numRondas = nRondas;
        tam = (int) Math.pow(2,(nRondas + 1))-1;
        this.arbol = new Jugador[tam];
        for (int i = 0; i < tam; i++) {
            arbol[i] = new Jugador(false);
        }
        this.cantMaximaJugadores = (int) Math.pow(2,numRondas);
        System.out.println("Tam:" + tam );
        System.out.println("Cantmax: " + cantMaximaJugadores);
        System.out.println("numRondas: " + numRondas);
    }

    public void insertarJugador(Jugador jugador){
        if(numJugadores < cantMaximaJugadores){
            arbol[tam-1-numJugadores] = jugador;
            numJugadores ++; 
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
    
    private void preorderHelper(int i, ) {
        if (i < arbol.length && arbol[i] != -1) {
            preorderHelper(i * 2 + 1, listaOrdenada);
            preorderHelper(i * 2 + 2, listaOrdenada);
        }
    }
    
    public void mostrar(){
        for (int i = 0; i < tam; i++) {
            System.out.print(arbol[i].toString() + ",");
        }
    }
    
    public void imprimirArbol(){
        
    }
}
