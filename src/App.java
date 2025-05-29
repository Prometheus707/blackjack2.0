import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Inicializar estructuras
        Baraja baraja = new Baraja();
        baraja.inicializar();
        mezclarBaraja(baraja);

        TablaHash tablaJugadores = new TablaHash();
        ColaTurnos cola = new ColaTurnos();
        ArbolDecision arbolDealer = new ArbolDecision();

        // Crear jugador humano
        System.out.println("Bienvenido a Blackjack versión consola");
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = sc.nextLine();
        Jugador jugador = new Jugador(nombreJugador);
        tablaJugadores.put(nombreJugador, jugador);

        // Crear dealer
        Jugador dealer = new Jugador("Dealer");
        tablaJugadores.put("Dealer", dealer);

        // Encolar turnos
        cola.encolar(nombreJugador);
        cola.encolar("Dealer");

        // Repartir dos cartas a cada uno
        for (int i = 0; i < 2; i++) {
            jugadorAgregarCarta(jugador, baraja.robar());
            jugadorAgregarCarta(dealer, baraja.robar());
        }

        // Turno del jugador humano
        while (!jugador.plantado && jugador.puntaje < 21) {
            System.out.println("\nTurno de: " + jugador.nombre);
            mostrarMano(jugador);
            if (jugador.puntaje >= 21) break;
            System.out.print("¿Desea otra carta? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                jugadorAgregarCarta(jugador, baraja.robar());
            } else {
                jugador.plantado = true;
            }
        }

        // Turno del dealer (automático con árbol de decisión)
        System.out.println("\nTurno de: Dealer");
        while (!dealer.plantado && dealer.puntaje < 21) {
            mostrarMano(dealer);
            boolean pedir = arbolDealer.decidir(dealer.puntaje);
            if (pedir) {
                System.out.println("El dealer toma una carta...");
                jugadorAgregarCarta(dealer, baraja.robar());
            } else {
                System.out.println("El dealer se planta.");
                dealer.plantado = true;
            }
        }

        // Mostrar resultados
        System.out.println("\n--- RESULTADOS ---");
        System.out.println(jugador.nombre + " tiene:");
        mostrarMano(jugador);
        System.out.println("Puntaje: " + jugador.puntaje);

        System.out.println("\nDealer tiene:");
        mostrarMano(dealer);
        System.out.println("Puntaje: " + dealer.puntaje);

        // Determinar ganador
        String ganador = determinarGanador(jugador, dealer);
        if (ganador.equals("Empate")) {
            System.out.println("\n¡Empate!");
        } else {
            System.out.println("\n" + ganador + " gana! 🎉");
        }
        sc.close();
    }

    // Mezcla simple de la baraja (puedes mejorarla)
    private static void mezclarBaraja(Baraja baraja) {
        // Para cumplir la restricción, puedes robar todas las cartas a un arreglo temporal y volver a insertarlas en orden aleatorio.
        Carta[] temp = new Carta[52];
        int count = 0;
        Carta c;
        while ((c = baraja.robar()) != null) {
            temp[count++] = c;
        }
        for (int i = 0; i < count; i++) {
            int j = (int) (Math.random() * count);
            Carta aux = temp[i];
            temp[i] = temp[j];
            temp[j] = aux;
        }
        for (int i = 0; i < count; i++) {
            baraja.agregarAlFinal(temp[i]);
        }
    }

    // Agrega una carta a la mano del jugador y actualiza puntaje
    private static void jugadorAgregarCarta(Jugador jugador, Carta carta) {
        if (carta == null) return;
        NodoCarta nuevo = new NodoCarta(carta);
        nuevo.siguiente = jugador.mano;
        jugador.mano = nuevo;
        jugador.puntaje = calcularPuntaje(jugador.mano);
    }

    // Calcula el puntaje de la mano (considerando As como 1 u 11)
    private static int calcularPuntaje(NodoCarta mano) {
        int total = 0, ases = 0;
        NodoCarta actual = mano;
        while (actual != null) {
            total += actual.carta.valor;
            if (actual.carta.numero.equals("A")) ases++;
            actual = actual.siguiente;
        }
        // Ajustar Ases
        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }
        return total;
    }

    // Muestra la mano del jugador
    private static void mostrarMano(Jugador jugador) {
        NodoCarta actual = jugador.mano;
        // Para mostrar en orden de robo, primero contar y luego recorrer al revés
        int n = 0;
        NodoCarta temp = actual;
        while (temp != null) {
            n++;
            temp = temp.siguiente;
        }
        Carta[] cartas = new Carta[n];
        temp = actual;
        for (int i = n - 1; i >= 0; i--) {
            cartas[i] = temp.carta;
            temp = temp.siguiente;
        }
        for (Carta c : cartas) {
            System.out.println("  " + c);
        }
    }

    // Determina el ganador
    private static String determinarGanador(Jugador jugador, Jugador dealer) {
        if (jugador.puntaje > 21) return "Dealer";
        if (dealer.puntaje > 21) return jugador.nombre;
        if (jugador.puntaje > dealer.puntaje) return jugador.nombre;
        if (dealer.puntaje > jugador.puntaje) return "Dealer";
        return "Empate";
    }
}
