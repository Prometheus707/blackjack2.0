/**
 * Pila (stack) para historial de cartas jugadas.
 */
public class PilaCartas {
    private NodoCarta tope;

    public PilaCartas() {
        tope = null;
    }

    public void apilar(Carta carta) {
        NodoCarta nuevo = new NodoCarta(carta);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Carta desapilar() {
        if (tope == null) return null;
        Carta carta = tope.carta;
        tope = tope.siguiente;
        return carta;
    }
}