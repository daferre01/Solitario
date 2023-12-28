/*
* Representa la baraja española, 40 cartas, 4 palos, valores de las cartas de 1 a 12 (excepto 8 y 9). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package solitario.Core;

import java.util.ArrayList;
import java.util.Stack;

public class Baraja {

    //Atributos
    protected Stack<Carta> cartas;
    protected int numCartas;
    protected int cartasPorPalo;

    public Baraja() {

        numCartas = 40;
        cartasPorPalo = 10;

        //crearBaraja(); //Creamos la baraja

    }

    public Stack<Carta> getCartas() {
        return cartas;
    }

    public void crearBaraja() {

        this.cartas = new Stack();

        Palos[] palos = Palos.values();

        Carta[] cartasBaraja = new Carta[numCartas];

        //Recorro los palos
        for (int i = 0; i < palos.length; i++) {

            //Recorro los numeros
            for (int j = 0; j < cartasPorPalo; j++) {

                if (j >= 7) {
                    //Solo para la sota, caballo y rey
                    cartasBaraja[((i * cartasPorPalo) + j)] = new Carta(j + 3, palos[i]);

                } else {
                    //Para los casos de 1 a 7
                    cartasBaraja[((i * cartasPorPalo) + j)] = new Carta(j + 1, palos[i]);
                }

            }

        }

        int posAleatoria;
        Carta c;
        ArrayList<Carta> cartasMetidas = new ArrayList<>();

        //Recorro las cartas
        while (cartasMetidas.size() != this.numCartas) {
            posAleatoria = generaNumeroAleatorio(0, numCartas - 1);

            c = cartasBaraja[posAleatoria];

            if (!cartasMetidas.contains(c)) {
                this.cartas.add(c);
                cartasMetidas.add(c);
            }

        }

    }

    private int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) (Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
        return num;
    }

    /**
     * Devuelve la casta donde se encuentre "posSiguienteCarta"
     *
     * @return carta del arreglo
     */
    public Carta siguienteCarta() {

        Carta c;

        if (this.cartas.empty()) {
            return null;
        } else {
            c = this.cartas.pop();
        }

        return c;

    }

    /**
     * Devuelve un numero de cartas. Controla si hay mas cartas de las que se
     * piden
     *
     * @param numCartas
     * @return
     */
    /*public Carta[] darCartas(int numCartas) {

        Carta[] cartasDar = new Carta[numCartas];

        //recorro el array que acabo de crear para rellenarlo
        for (int i = 0; i < cartasDar.length; i++) {
            cartasDar[i] = siguienteCarta(); //uso el metodo anterior
        }

        //Lo devuelvo
        return cartasDar;

    }*/
    /**
     * Indica el numero de cartas que hay disponibles
     *
     * @return
     */
    /*  public int cartasDisponible() {
        return numCartas - posSiguienteCarta;
    }*/
    /**
     * Muestro las cartas que ya han salido
     */
    /*public void cartasMonton() {

        if (cartasDisponible() == numCartas) {
            System.out.println("No se ha sacado ninguna carta");
        } else {
            //Recorro desde 0 a la posSiguienteCarta
            for (int i = 0; i < posSiguienteCarta; i++) {
                System.out.println(cartas[i]);
            }
        }

    }*/
    /**
     * Muestro las cartas que aun no han salido
     */
    public void mostrarBaraja() {

        while (!this.cartas.empty()) {
            Carta c = this.cartas.pop();
            System.out.println(c);
        }

    }

}
