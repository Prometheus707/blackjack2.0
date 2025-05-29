/**
 * Cola (queue) para gestión de turnos.
 */
public class ColaTurnos {
    private NodoCola frente, fin;

    public ColaTurnos() {
        frente = fin = null;
    }

    public void encolar(String jugador) {
        NodoCola nuevo = new NodoCola(jugador);
        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public String desencolar() {
        if (frente == null) return null;
        String jugador = frente.jugador;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return jugador;
    }
}