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
    boolean estado;
    int nivelActual;

    public Jugador(String nombre, String apellido,int nivelActual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivelActual = nivelActual;
        this.estado = true;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }
    
    public Jugador(boolean estado){
        this.nombre = "";
        this.apellido = "";
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + '}';
    }
    
    
}
