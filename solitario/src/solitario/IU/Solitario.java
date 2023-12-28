/**
 * Representa el juego del solitario, con sus reglas.
 * Se recomienda una implementación modular.
 */
package solitario.IU;

import java.util.Scanner;
import solitario.Core.Baraja;
import solitario.Core.Carta;
import solitario.Core.Jugador;
import solitario.Core.Mesa;
import solitario.Core.Palos;

/**
 *
 * @author AEDI
 */
public class Solitario {

    private Mesa mesa;
    private Jugador jugador;

    public Solitario() {
        this.mesa = new Mesa();
        this.jugador = new Jugador();
    }

    public void pedirNombreJugador() {
        String nombre = ES.pideCadena("Introduce el nombre del jugador: ");
        this.jugador.setNombre(nombre);
    }

    public void mostrarMenu() {
        System.out.println("Opciones");
        System.out.println("");
    }

    public void inicioPartida() {

        this.pedirNombreJugador();

        mesa.iniciarMesa();

        boolean salir = false, partidaGanada = false, partidaPerdida = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir && !partidaGanada && !partidaPerdida) {

            mesa.mostrarMontonExterior();
            mesa.mostrarCartas();

            System.out.println("Elige una carta");
            Carta c = jugador.escogerCarta(mesa);

            if (c != null) {
                System.out.println("1. Mover carta a monton interior");
                System.out.println("2. Mover carta a monton exterior");
                System.out.println("3. Volver a elegir");
                System.out.println("4. Rendirse");

                opcion = ES.pideNumero("Escribe una de las opciones");

                switch (opcion) {
                    case 1:

                        System.out.println("Elige otra carta");
                        Carta cMontonInterior = jugador.escogerCarta(mesa);

                        if (cMontonInterior != null) {
                            if (c.equals(cMontonInterior)) {
                                System.out.println("No puedes elegir la misma carta");
                            } else if (c.getPalo() == cMontonInterior.getPalo()
                                    && ((c.getNumero() == 7 && cMontonInterior.getNumero() == 10)
                                    || (c.getNumero() == (cMontonInterior.getNumero() - 1)))) {
                                this.mesa.moverCarta(c, cMontonInterior);
                            } else {
                                System.out.println("No puedes mover esa carta");
                            }
                        }

                        break;
                    case 2:
                        String palo = ES.pideCadena("Elige un Palo: ").toUpperCase();

                        boolean existe = false;
                        for (Palos p : Palos.values()) {
                            if (palo.equals(p.name())) {
                                existe = true;
                            }
                        }

                        if (existe) {
                            Palos p = Palos.valueOf(palo.toUpperCase());

                            if (mesa.sePuedeAnadirCarta(p, c)) {
                                mesa.aniadeCartaMontoExteror(p, c);
                                System.out.println("Se añadido la carta al monton exterior");
                                mesa.eliminarCarta(c);
                            } else {
                                System.out.println("No puedes move la carta");
                            }

                        } else {
                            System.out.println("No es un palo correcto.");
                        }

                        break;
                    case 3:
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            }

            partidaGanada = this.mesa.montonesExterioresCompletos();
            partidaPerdida = this.mesa.noMasMovimientos();

        }

        System.out.println("----------");
        System.out.println("Fin");
        System.out.println("-----------");
        mesa.mostrarCartas();

        if (salir) {
            System.out.println("Te has rendido!");
        }

        if (partidaGanada) {
            System.out.println("Has ganado");
        }

        if (partidaPerdida) {
            System.out.println("Has perdido");
        }
    }

}
