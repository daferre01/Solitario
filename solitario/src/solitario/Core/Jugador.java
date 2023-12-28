/*
 * Representa al único jugador de la partida, identificado por el nombre 
 * Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
 *                o la mueve encima de otra carta del interior
 */
package solitario.Core;

import solitario.IU.ES;

/**
 *
 * @author AEDI
 */
public class Jugador {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carta escogerCarta(Mesa m) {
        int fila = ES.pideNumero("Escoge una fila");
        int columna = ES.pideNumero("Escoge una columna");

        if (fila >= 0 && fila < Mesa.FILAS && columna >= 0 && columna < Mesa.COLUMNAS) {
            Carta c = m.getCartaAt(fila, columna);
            if (c != null) {
                System.out.println("Has seleccionado " + c);
                return c;
            }else{
                System.out.println("La posicion indicada esta vacia");
                return null;
            }
        } else {
            System.out.println("La fila o columna no es valida");
            return null;
        }

    }

}
