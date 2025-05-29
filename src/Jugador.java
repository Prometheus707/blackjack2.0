//Representa un jugador (humano o dealer).
public class Jugador {
    public String nombre;
    public NodoCarta mano; // Lista enlazada de cartas en mano
    public int puntaje;
    public boolean plantado;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = null;
        this.puntaje = 0;
        this.plantado = false;
    }

}
