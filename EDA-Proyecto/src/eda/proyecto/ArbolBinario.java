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
    int contNumJugadores = 0;
    
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
        for (int i = 0; i < numRondas+1; i++) {
            rondaAct = i +1;
            if (nivelActualArbol == 0) {
                System.out.println("EL GANADOR DEL TORNEO ES: " + irHoja(0,nivelActualArbol).getNombre() + " " + irHoja(0,nivelActualArbol).getApellido());
            }else{
                System.out.println("Ronda: " + (rondaAct));
            }
            while (partidosActivos != 0) {
                    imprimirRonda(rondaAct); 
                    System.out.println("Ingrese un ganador");
                    System.out.print("Nombre: ");
                    String nombreG = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellidoG = scanner.nextLine();
                    asignarJugadorGanador(nombreG,apellidoG); 
            }
            cantMaxJugadores /= 2;
            partidosActivos = cantMaxJugadores/2;
            nivelActualArbol --; 
        }
    }
    
    // subirDesdeIzq = (i-1)/2;
    // subirDesdeDer = (i-2)/2;
    // 0 -> i * 2 + 1
    // 1 -> i * 2 + 2 

    public boolean asignarJugadorGanador(String nombre, String apellido){
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
            if (jug.getNombre().equals(nombre) && jug.getApellido().equals(apellido) && jug.getNivelActual() == nivelActualArbol && jug.isEstado()) {
                jugadorEncontrado = true;
                jug.nivelActual --;
                if (ladoIzq == true) {
                    pos = (pos-1)/2;
                    arbol[pos].setNombre(nombre);
                    arbol[pos].setApellido(apellido);
                    arbol[pos].setEstado(true);
                    arbol[pos].setNivelActual(nivelActualArbol-1);
                    pos = pos * 2 + 2;
                    arbol[pos].setEstado(false); 
                }else{
                    pos = (pos-2)/2;
                    arbol[pos].setNombre(nombre);
                    arbol[pos].setApellido(apellido);
                    arbol[pos].setEstado(true);
                    arbol[pos].setNivelActual(nivelActualArbol-1);
                    pos = pos * 2 + 1;
                    arbol[pos].setEstado(false);
                }
            }
        }
        if (jugadorEncontrado) {
            System.out.println("Se ha asignado como ganador al jugador: " + nombre + " " + apellido + ", en la ronda: " + (numRondas-nivelActualArbol+1));
            partidosActivos --;
            return true;
        }else{
            System.out.println("El jugador ingresado no existe o ya jugo");
            return false;
        }
    }
    
    public void mostrar(){
        for (int i = 0; i < tam; i++) {
            System.out.print(arbol[i].toString() + ",");
        }
    }
    
    public void imprimirRonda(int rondaActual){
        int nivelAux = numRondas-rondaActual+1;
        double cantJugadores = Math.pow(2, nivelAux);
        System.out.println("* * * * * * * * * * * * ");
        for (int i = 0; i < cantJugadores; i++) {
            if (i % 2 == 0) {
                if (irHoja(i,nivelActualArbol).isEstado() == false || irHoja(i+1,nivelActualArbol).isEstado() == false) {
                    System.out.println("Estado partido: Ya se ha jugado");
                }else{
                    System.out.println("Estado partido: Por jugar");
                }
                System.out.println(irHoja(i,nivelActualArbol).getNombre() + " " + irHoja(i,nivelActualArbol).getApellido() + " VS " + irHoja(i+1,nivelActualArbol).getNombre() + " " + irHoja(i+1,nivelActualArbol).getApellido());
            } 
        }
        System.out.println("* * * * * * * * * * * * ");
    }
    
    public Jugador irHoja(int nHoja,int nivelActualArbol){
        int pos = 0;
        if (nivelActualArbol != 0) {
            String numBinarios = String.format("%" + (nivelActualArbol) + "s", Integer.toBinaryString(nHoja)).replace(' ', '0');
            for (int j = 0; j < nivelActualArbol; j++) {
                char n = numBinarios.charAt(j);
                if (n == '0') {
                pos = pos * 2 + 1;
                }else{
                pos = pos * 2 + 2;
                }  
            }
        }
        return arbol[pos];
    }
    
    public void buscarPartidoResultado(int ronda, String nombre, String apellido){
        int nivelAux = numRondas-ronda+1;
        double cantJugadores = Math.pow(2, nivelAux);
        for (int i = 0; i < cantJugadores; i++) {
            int pos = 0;
            if (nivelAux != 0) {
                String numBinarios = String.format("%" + (nivelAux) + "s", Integer.toBinaryString(i)).replace(' ', '0');
                for (int j = 0; j < nivelAux; j++) {
                    char n = numBinarios.charAt(j);
                    if (n == '0') {
                    pos = pos * 2 + 1;
                    }else{
                    pos = pos * 2 + 2;
                    }  
                }
            }
            Jugador jug = arbol[pos];
            if (jug.getNombre().equals(nombre) && jug.getApellido().equals(apellido)) {
                if (i % 2 == 0) {
                    System.out.println("El ganador del partido: " + irHoja(i,nivelAux).getNombre() + " " + irHoja(i,nivelAux).getApellido() + " VS " + irHoja(i+1,nivelAux).getNombre() + " " + irHoja(i+1,nivelAux).getApellido());
                    System.out.println("Fue: " + arbol[(pos-1)/2].getNombre() + " " + arbol[(pos-1)/2].getApellido());
                }else{
                    System.out.println("El ganador del partido: " + irHoja(i,nivelAux).getNombre() + " " + irHoja(i,nivelAux).getApellido() + " VS " + irHoja(i-1,nivelAux).getNombre() + " " +irHoja(i-1,nivelAux).getApellido());
                    System.out.println("Fue: " + arbol[(pos-2)/2].getNombre() + " " + arbol[(pos-2)/2].getApellido());
                }  
            }
        }
    }
}
