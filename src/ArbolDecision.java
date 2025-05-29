
public class ArbolDecision {
    private NodoDecision raiz;

    public ArbolDecision() {
        raiz = new NodoDecision(17); // Nodo raíz con valor 17
    }

    public boolean decidir(int puntaje) {
        NodoDecision actual = raiz;
        while (actual != null) {
            if (puntaje < actual.valor) {
                if (actual.izquierda == null) return true; // Pedir carta
                actual = actual.izquierda;
            } else {
                if (actual.derecha == null) return false; // Plantarse
                actual = actual.derecha;
            }
        }
        return false;
    }
}

class NodoDecision {
    public int valor;
    public NodoDecision izquierda, derecha;

    public NodoDecision(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}