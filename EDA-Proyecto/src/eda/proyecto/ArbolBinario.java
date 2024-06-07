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
    int partidosActivos;
    
    public ArbolBinario(int nRondas) {
        this.numRondas = nRondas;
        this.nivelActualArbol = nRondas;
        tam = (int) Math.pow(2,(nRondas + 1))-1;
        this.arbol = new Jugador[tam];
        for (int i = 0; i < tam; i++) {
            arbol[i] = new Jugador(false);
        }
        this.cantMaxJugadores = (int) Math.pow(2,numRondas);
        this.partidosActivos = cantMaxJugadores/2;
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
    
    public void controlRondas(){
        int rondaAct;
        Scanner scanner = new Scanner(System.in);
        String ganador;
        for (int i = 0; i < numRondas; i++) {
            rondaAct = i +1;
            cantMaxJugadores /= 2;
            partidosActivos = cantMaxJugadores/2;
            nivelActualArbol --;
            
            System.out.println("Ronda: " + (rondaAct));
            while (partidosActivos != 0) {
                System.out.println("Ingrese un ganador");
                String nombreG = scanner.nextLine();
                String apellidoG = scanner.nextLine();
                asignarJugadorGanador(nombreG,apellidoG);
                imprimirRonda(rondaAct);   
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
            if (jug.getNombre().equals(nombre) && jug.getApellido().equals(apellido) && jug.getNivelActual() == nivelActualArbol && jug.isEstado() == true) {
                jugadorEncontrado = true;
                jug.nivelActual --;
                if (ladoIzq == true) {
                    pos = (pos-1)/2;
                    arbol[pos].setNombre(nombre);
                    arbol[pos].setApellido(apellido);
                    pos = pos * 2 + 2;
                    arbol[pos].setEstado(false); 
                }else{
                    pos = (pos-2)/2;
                    arbol[pos].setNombre(nombre);
                    arbol[pos].setApellido(apellido);
                    pos = pos * 2 + 1;
                    arbol[pos].setEstado(false);
                }
            }
        }
        if (jugadorEncontrado) {
            System.out.println("Se ha asignado como ganador al jugador: " + nombre + " " + apellido + ", en la ronda: " + (numRondas-nivelActualArbol+1));
        }else{
            System.out.println("El jugador ingresado no existe o ya jugó");
        }
    }
    
    public void mostrar(){
        for (int i = 0; i < tam; i++) {
            System.out.print(arbol[i].toString() + ",");
        }
    }
    
    public void imprimirRonda(int rondaActual){
        int partidosJugados = 0;
        int nivelAux = numRondas-rondaActual+1;
        double cantJugadores = Math.pow(2, nivelAux);
        System.out.println("PARTIDOS DE LA RONDA: " + rondaActual);
        System.out.println("* * * * * * * * * * * * ");
        for (int i = 0; i < cantJugadores; i++) {
            if (i % 2 == 0) {
                if (irHoja(i).isEstado() == false || irHoja(i+1).isEstado() == false) {
                    System.out.println("Estado partido: Ya se ha jugado");
                    partidosJugados ++;
                }else{
                    System.out.println("Estado partido: Por jugar");
                }
                System.out.println(irHoja(i).getNombre() + " " + irHoja(i).getApellido() + " VS " + irHoja(i+1).getNombre() + irHoja(i+1).getApellido());
            } 
        }
        System.out.println("* * * * * * * * * * * * ");
        partidosActivos -= partidosJugados;
    }
    
    public Jugador irHoja(int nHoja){
        int pos = 0;
        String numBinarios = String.format("%" + (nivelActualArbol) + "s", Integer.toBinaryString(nHoja)).replace(' ', '0');
        for (int j = 0; j < nivelActualArbol; j++) {
            char n = numBinarios.charAt(j);
            if (n == '0') {
                pos = pos * 2 + 1;
            }else{
                pos = pos * 2 + 2;
            }  
        }
        return arbol[pos];
    }
}
