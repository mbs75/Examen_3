package motor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL MOTOR DE CARRERAS IA-DRIVE ===");
        
        MotorJuego motor = new MotorJuego();
        GestorEntradas input = new GestorEntradas();

        // 1. Intentar actualizar en el menú
        motor.actualizar();

        // 2. Simular pulsación táctil para iniciar
        input.procesarComando("INICIAR", motor);

        // 3. Simular ciclos de juego (Game Loop) habituales
        motor.actualizar();
        
        input.procesarComando("ACELERAR", motor);
        motor.actualizar();

        // 4. Forzar avance de posiciones simulando el paso del tiempo para forzar las colisiones
        System.out.println("\n[SIMULACIÓN] Los coches avanzan por la autopista hacia los ítems...");
        motor.getJugador().setY(38); // El Turbo está en Y=40, el coche mide 4 de alto. ¡Colisión inminente!
        motor.actualizar();

        // 5. Simular entrada de pausa
        input.procesarComando("PAUSAR", motor);
        motor.actualizar();
    }
}