package motor;

public class GestorEntradas {
    public void procesarComando(String comando, MotorJuego motor) {
        System.out.println("\n[INPUT] Usuario ejecuta comando táctil: " + comando);
        Coche jugador = motor.getJugador();
        
        if (jugador == null && !comando.equals("INICIAR")) {
            System.out.println("[INPUT ADVERTENCIA] No hay partida activa para procesar " + comando);
            return;
        }

        switch (comando.toUpperCase()) {
            case "INICIAR":
                motor.cambiarEstado("JUGANDO");
                break;
            case "ACELERAR":
                jugador.modificarVelocidad(20);
                break;
            case "FRENAR":
                jugador.modificarVelocidad(-20);
                break;
            case "PAUSAR":
                motor.cambiarEstado("PAUSA");
                break;
            case "REANUDAR":
                motor.cambiarEstado("JUGANDO");
                break;
            default:
                System.out.println("[INPUT ERROR] Comando táctil no reconocido.");
        }
    }
}