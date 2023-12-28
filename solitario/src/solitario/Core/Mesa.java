/*
* Representa la mesa de juego, donde estarán todas las cartas.
* Tendrá dos partes diferenciadas:
* - la parte interior, donde inicialmente estarán colocadas las cartas correctamente para jugar al solitario
* - los montones exteriores, donde estarán colocadas las cartas por palo ordenadas de menor a mayor
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:
* - Una matriz de Pilas para representar la parte o montón interior 
* - Un array de Pilas para representar los montones exteriores.
* Funcionalidad: colocar las cartas para iniciar el juego, quitar una carta de la parte interior, colocar una carta en el interior,
* colocar una carta en el montón exterior correspondiente, visualizar cartas en la mesa, etc

La Pila es una estructura de datos que existe en Java y que se corresponde con la clase Stack. Por lo tanto debereis hacer uso de dicha
clase para representar la mesa de juego, y en particular de los métodos que se indican a continuación (de ser necesarios):

        public boolean empty();
        // Produce: Si la pila está vacía devuelve true, sino false.
        public Carta peek();
        // Produce: Devuelve la Carta del tope de la pila, sin eliminarla de ella.
        public Carta pop();
        // Produce: Elimina la Carta del tope de la pila y la devuelve.
        public void push(Carta item);
        // Produce: Introduce la Carta en el tope de la pila.
 */
package solitario.Core;

import java.util.Stack;

/**
 *
 * @author AEDI
 */
public class Mesa {

    private Stack<Carta>[][] montonInterior;
    private Stack<Carta>[] montonExterior;

    public static final int FILAS = 4;
    public static final int COLUMNAS = 4;

    public Mesa() {

        // Monton exterior
        this.montonExterior = new Stack[Palos.values().length];
        for (int i = 0; i < montonExterior.length; i++) {
            this.montonExterior[i] = new Stack<>();
        }

        // Monton interior
        this.montonInterior = new Stack[FILAS][COLUMNAS];
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {
                this.montonInterior[i][j] = new Stack<>();
            }
        }

    }

    public void iniciarMesa() {

        Baraja b = new Baraja();
        b.crearBaraja();

        //Stack<Carta> cartas = b.getCartas();
        // primeras 16 cartas
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {
                Carta c = b.siguienteCarta();
                montonInterior[i][j].add(c);
                //         System.out.println(montonInterior[i][j]);
            }
        }
        //  System.out.println("-----");

        // siguientes 8 cartas (diagonal principal)
        for (int i = 0; i < montonInterior.length; i++) {
            Carta c = b.siguienteCarta();
            montonInterior[i][i].add(c);

            //    System.out.println(montonInterior[i][i]);
        }

        //System.out.println("-----");
        // siguientes 8 cartas (diagonal inversa)
        for (int i = 0, j = montonInterior.length - 1; i < montonInterior.length; i++, j--) {
            Carta c = b.siguienteCarta();
            montonInterior[i][j].add(c);

            //    System.out.println(montonInterior[i][j]);
        }

        //  System.out.println("-----");
        // ultimas 16 cartas 
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {
                Carta c = b.siguienteCarta();
                montonInterior[i][j].add(c);

                //     System.out.print(montonInterior[i][j]);
                //     System.out.print(" ");
            }
            // System.out.println("");
        }

        // System.out.println("-----");
    }

    public void mostrarCartas() {

        System.out.println("Zona interior");
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {

                if (montonInterior[i][j].empty()) {
                    System.out.print("XX ");
                } else {
                    Carta c = montonInterior[i][j].peek();
                    System.out.print(c + " ");
                }
            }
            System.out.println("");
        }
    }

    public void mostrarMontonExterior() {

        System.out.println("Monton exterior");
        Palos[] p = Palos.values();
        for (int i = 0; i < montonExterior.length; i++) {
            System.out.print(p[i].name() + ": ");
            if (this.montonExterior[i].empty()) {
                System.out.print("XX");
            } else {
                Carta c = this.montonExterior[i].peek();
                System.out.print(c);
            }

            System.out.print("    ");
        }

        System.out.println("");

    }

    public Carta getCartaAt(int fila, int columna) {
        if (this.montonInterior[fila][columna].empty()) {
            return null;
        } else {
            return this.montonInterior[fila][columna].peek();
        }
    }

    public void eliminarCarta(int fila, int columna) {
        this.montonInterior[fila][columna].pop();
    }

    public void eliminarCarta(Carta c) {

        int[] posC = posCarta(c);

        int fila = posC[0];
        int columna = posC[1];

        this.montonInterior[fila][columna].pop();

    }

    public int[] posCarta(Carta c) {

        int[] pos = null;
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {
                if (!montonInterior[i][j].empty() && montonInterior[i][j].peek().equals(c)) {
                    pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        return pos;
    }

    public void moverCarta(Carta c1, Carta c2) {

        int[] posC1 = posCarta(c1);

        int filaOri = posC1[0];
        int columnaOri = posC1[1];

        this.montonInterior[filaOri][columnaOri].pop();

        int[] posC2 = posCarta(c2);

        int filaDes = posC2[0];
        int columnaDes = posC2[1];

        this.montonInterior[filaDes][columnaDes].add(c1);

    }

    public void aniadeCartaMontoExteror(Palos p, Carta c) {

        switch (p) {
            case OROS:
                this.montonExterior[0].add(c);
                break;
            case COPAS:
                this.montonExterior[1].add(c);
                break;
            case ESPADAS:
                this.montonExterior[2].add(c);
                break;
            case BASTOS:
                this.montonExterior[3].add(c);
                break;
        }

    }

    public boolean montonesExterioresCompletos() {

        for (int i = 0; i < montonExterior.length; i++) {
            if (montonExterior[i].size() != 10) {
                return false;
            }
        }

        return true;
    }

    public boolean noMasMovimientos() {

        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior.length; j++) {

                if (!this.montonInterior[i][j].empty()) {
                    Carta c1 = this.montonInterior[i][j].peek();

                    if (this.sePuedeAnadirCarta(c1.getPalo(), c1)) {
                        return false;
                    } else {

                        for (int k = 0; k < montonInterior.length; k++) {
                            for (int z = 0; z < montonInterior.length; z++) {

                                if (!this.montonInterior[k][z].empty()) {

                                    Carta c2 = this.montonInterior[k][z].peek();
                                    if (!c1.equals(c2) && c1.getPalo() == c2.getPalo()
                                            && ((c1.getNumero() == 7 && c2.getNumero() == 10)
                                            || (c1.getNumero() == (c2.getNumero() - 1)))) {
                                        return false;
                                    }

                                }

                            }
                        }

                    }

                }
            }
        }

        return true;

    }

    public boolean sePuedeAnadirCarta(Palos p, Carta c) {

        Carta cMonton = null;
        switch (p) {
            case OROS:
                if (!this.montonExterior[0].empty()) {
                    cMonton = this.montonExterior[0].peek();
                }
                break;
            case COPAS:
                if (!this.montonExterior[1].empty()) {
                    cMonton = this.montonExterior[1].peek();
                }
                break;
            case ESPADAS:
                if (!this.montonExterior[2].empty()) {
                    cMonton = this.montonExterior[2].peek();
                }
                break;
            case BASTOS:
                if (!this.montonExterior[3].empty()) {
                    cMonton = this.montonExterior[3].peek();
                }
                break;
        }

        if (cMonton == null) {
            if (c.getNumero() == 1 && p.equals(c.getPalo())) {
                return true;
            } else {
                return false;
            }
        } else {

            if (((cMonton.getNumero() == 7 && c.getNumero() == 10)
                    || (cMonton.getNumero() == (c.getNumero() - 1)))
                    && cMonton.getPalo() == c.getPalo()) {
                return true;
            } else {
                return false;
            }

        }

    }

}
