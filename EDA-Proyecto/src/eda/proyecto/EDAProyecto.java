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
        int numRondas = 2;
        ArbolBinario torneo = new ArbolBinario(numRondas);
        
        Jugador jugador1 = new Jugador("Carlos","Alcaraz",numRondas);
        Jugador jugador2 = new Jugador("Casper","Rudd",numRondas);
        Jugador jugador3 = new Jugador("JuanMartin","DelPotro",numRondas);
        Jugador jugador4 = new Jugador("Rafa","Nadal",numRondas);
        
        torneo.insertarJugador(jugador1);
        torneo.insertarJugador(jugador2);
        torneo.insertarJugador(jugador3);
        torneo.insertarJugador(jugador4);
        
        torneo.controlRondas();
        torneo.buscarPartidoResultado(1, "Rafa", "Nadal");
    
    }
    
}
