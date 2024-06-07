/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eda.proyecto;

/**
 *
 * @author pablo
 */
public class EDAProyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolBinario torneo = new ArbolBinario(2);
        
        Jugador jugador1 = new Jugador("a","a");
        Jugador jugador2 = new Jugador("b","b");
        Jugador jugador3 = new Jugador("c","c");
        Jugador jugador4 = new Jugador("d","d");
      
        torneo.insertarJugador(jugador1);
        torneo.insertarJugador(jugador2);
        torneo.insertarJugador(jugador3);
        torneo.insertarJugador(jugador4);
        
        torneo.abrirMenu();
        
        
    }
    
}
