/**
 * Tabla hash simple para almacenar jugadores.
 */
public class TablaHash {
    private NodoHash[] tabla;
    private int capacidad = 10;

    public TablaHash() {
        tabla = new NodoHash[capacidad];
    }

    private int hash(String clave) {
        int hash = 0;
        for (char c : clave.toCharArray()) hash += c;
        return hash % capacidad;
    }

    public void put(String clave, Jugador valor) {
        int idx = hash(clave);
        NodoHash actual = tabla[idx];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor;
                return;
            }
            actual = actual.siguiente;
        }
        NodoHash nuevo = new NodoHash(clave, valor);
        nuevo.siguiente = tabla[idx];
        tabla[idx] = nuevo;
    }

    public Jugador get(String clave) {
        int idx = hash(clave);
        NodoHash actual = tabla[idx];
        while (actual != null) {
            if (actual.clave.equals(clave)) return actual.valor;
            actual = actual.siguiente;
        }
        return null;
    }
}