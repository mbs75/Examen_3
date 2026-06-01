package motor;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estadoActual = "MENU";
    private Coche jugador;
    private final List<EntidadVideojuego> entidades = new ArrayList<>();

    public void cambiarEstado(String nuevoEstado) {
        this.estadoActual = nuevoEstado.toUpperCase();
        System.out.println("[ESTADO] Cambiado a: " + this.estadoActual);
    }

    public void actualizar() {
        // Se programará en la siguiente fase
        System.out.println("[MOTOR] Actualización base.");
    }

    public Coche getJugador() { return jugador; }
    public String getEstadoActual() { return estadoActual; }
}