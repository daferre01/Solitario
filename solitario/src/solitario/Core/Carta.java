/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package solitario.Core;

/**
 *
 * @author AEDI
 */
public class Carta {

    //Atributos
    private int numero;
    private Palos palo;
    
    public int getNumero() {
        return numero;
    }
    
    public Palos getPalo() {
        return palo;
    }

    //Constructor
    public Carta(int numero, Palos palo) {
        this.numero = numero;
        this.palo = palo;
    }
    
    
    @Override
    public String toString() {
        
        String estado = "";
        String nombreFigura = "";
        
        switch (numero) {
            case 1:
                nombreFigura = "A";
                break;
            case 10:
                nombreFigura = "S";
                break;
            case 11:
                nombreFigura = "C";
                break;
            case 12:
                nombreFigura = "R";
                break;
            default:
                nombreFigura = numero + "";
        }
        
        estado = nombreFigura + palo.name().substring(0, 1);
        
        return estado;
        
    }
}
