/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.proyecto;

/**
 *
 * @author pablo
 */
public class Jugador {
    String nombre;
    String apellido;
    boolean estado = true;

    public Jugador(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Jugador(boolean estado){
        this.nombre = "";
        this.apellido = "";
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + '}';
    }
    
    
}
