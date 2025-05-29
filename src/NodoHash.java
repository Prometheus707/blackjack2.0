//Nodo para la tabla hash de jugadores
public class NodoHash {
    public String clave; // nombre del jugador
    public Jugador valor;
    public NodoHash siguiente;

    public NodoHash(String clave, Jugador valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}
