/**
 * Representa una carta de la baraja.
 */
public class Carta {
    public String numero;
    public String palo;
    public int valor;

    public Carta(String numero, String palo, int valor) {
        this.numero = numero;
        this.palo = palo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}