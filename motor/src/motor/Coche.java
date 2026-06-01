package motor;

public class Coche extends EntidadVideojuego {
    private int velocidadActual;
    private final boolean esJugador;

    public Coche(int x, int y, String nombre, boolean esJugador) {
        super(x, y, 2, 4, nombre, "NORMAL", "texturas/coche_" + nombre.toLowerCase() + ".png");
        this.velocidadActual = 0;
        this.esJugador = esJugador;
    }

    @Override
    public void actualizar() {
        // Simulación del movimiento vertical en la pista
        setY(getY() + velocidadActual);
        System.out.println("[LOG - " + getNombre() + "] Posición Y: " + getY() + " | Velocidad: " + velocidadActual + " km/h | Estado: " + getEstado());
    }

    public void modificarVelocidad(int variacion) {
        this.velocidadActual += variacion;
        if (this.velocidadActual < 0) {
            this.velocidadActual = 0; // Evitar marcha atrás involuntaria
        }
    }

    public int getVelocidadActual() { return velocidadActual; }
    public boolean isEsJugador() { return esJugador; }
}