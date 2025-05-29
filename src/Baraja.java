/**
 * Lista enlazada simple para representar la baraja.
 */
public class Baraja {
    private NodoCarta cabeza;

    public Baraja() {
        cabeza = null;
    }

    // Inicializa la baraja con 52 cartas
    public void inicializar() {
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] numeros = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] valores =   {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}; // Valores correctos

        for (String palo : palos) {
            for (int i = 0; i < numeros.length; i++) {
                Carta carta = new Carta(numeros[i], palo, valores[i]);
                agregarAlFinal(carta);
            }
        }
    }

    // Roba la primera carta de la baraja
    public Carta robar() {
        if (cabeza == null) return null;
        Carta carta = cabeza.carta;
        cabeza = cabeza.siguiente;
        return carta;
    }

    // Agrega una carta al final de la lista (para mezclar)
    public void agregarAlFinal(Carta carta) {
        NodoCarta nuevo = new NodoCarta(carta);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoCarta actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    // Métodos para mezclar, mostrar, etc.
}