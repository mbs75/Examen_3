package motor;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estadoActual; // MENU, JUGANDO, PAUSA, GAME_OVER
    private final List<EntidadVideojuego> entidades;
    private Coche jugador;

    public MotorJuego() {
        this.estadoActual = "MENU";
        this.entidades = new ArrayList<>();
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estadoActual = nuevoEstado.toUpperCase();
        System.out.println("[ESTADO MOTOR] El juego ha cambiado a: " + this.estadoActual);
        
        if (this.estadoActual.equals("JUGANDO") && jugador == null) {
            inicializarPartida();
        }
    }

    private void inicializarPartida() {
        System.out.println("[MOTOR] Inicializando pista de carreras y competidores...");
        jugador = new Coche(10, 0, "Player1", true);
        entidades.add(jugador);

        // Añadimos un rival (NPC)
        entidades.add(new Coche(12, 5, "RivalIA", false));

        // Añadimos ítems en la pista (Turbo y Mancha de Aceite)
        entidades.add(new Item(10, 40, "TurboNitro", 40));
        entidades.add(new Item(12, 60, "ManchaAceite", -30));
    }

    public void actualizar() {
        if (!estadoActual.equals("JUGANDO")) {
            System.out.println("[MOTOR PAUSADO] Saltando ciclo de actualización.");
            return;
        }

        System.out.println("\n--- [INICIO CICLO ACTUALIZAR - GAME LOOP] ---");
        
        // 1. Actualizar posiciones y estados lógicos
        for (EntidadVideojuego entidad : entidades) {
            if (entidad instanceof Coche && !((Coche) entidad).isEsJugador()) {
                gestionarComportamientoRival((Coche) entidad);
            }
            entidad.actualizar();
        }

        // 2. Ejecutar sistema avanzado de colisiones
        verificarColisiones();
    }

    // Avanzada 1: Sistema de Comportamiento Dinámico de Inteligencia Artificial (NPC)
    private void gestionarComportamientoRival(Coche rival) {
        if (this.jugador == null) return;

        int distanciaY = Math.abs(rival.getY() - jugador.getY());

        // Máquina de estados finitos basada en la distancia de carrera
        if (distanciaY > 50) {
            rival.setEstado("PATRULLAR_CRUCERO");
            if (rival.getVelocidadActual() < 60) rival.modificarVelocidad(10);
        } else if (distanciaY <= 50 && distanciaY > 10) {
            rival.setEstado("PERSEGUIR_AGRESIVO");
            rival.modificarVelocidad(20); // El rival acelera al ver cerca al jugador
        } else {
            rival.setEstado("ATAQUE_REBUFO");
            rival.modificarVelocidad(5);
        }
    }

    // Avanzada 2: Detector Matemático de Colisiones AABB
    private void verificarColisiones() {
        List<EntidadVideojuego> aEliminar = new ArrayList<>();

        for (int i = 0; i < entidades.size(); i++) {
            for (int j = i + 1; j < entidades.size(); j++) {
                EntidadVideojuego e1 = entidades.get(i);
                EntidadVideojuego e2 = entidades.get(j);

                // Fórmula matemática bounding box: (X1 < X2 + W2) && (X1 + W1 > X2) && (Y1 < Y2 + H2) && (Y1 + H1 > Y2)
                if (e1.getX() < e2.getX() + e2.getAncho() &&
                    e1.getX() + e1.getAncho() > e2.getX() &&
                    e1.getY() < e2.getY() + e2.getAlto() &&
                    e1.getY() + e1.getAlto() > e2.getY()) {

                    System.out.println("[COLISIÓN DETECTADA] " + e1.getNombre() + " chocó con " + e2.getNombre());
                    
                    // Resolver colisión entre Coche e Item
                    resolverColisionCocheItem(e1, e2, aEliminar);
                    resolverColisionCocheItem(e2, e1, aEliminar);
                }
            }
        }
        entidades.removeAll(aEliminar);
    }

    private void resolverColisionCocheItem(EntidadVideojuego posibleCoche, EntidadVideojuego posibleItem, List<EntidadVideojuego> aEliminar) {
        if (posibleCoche instanceof Coche && posibleItem instanceof Item) {
            Coche coche = (Coche) posibleCoche;
            Item item = (Item) posibleItem;

            System.out.println("[EFECTO] " + coche.getNombre() + " recoge " + item.getNombre() + ". Alterando velocidad en " + item.getEfectoVelocidad() + " km/h.");
            coche.modificarVelocidad(item.getEfectoVelocidad());
            
            // Si el jugador frena a 0 por el aceite, simula un descalabro catastrófico
            if (coche.getVelocidadActual() == 0 && item.getEfectoVelocidad() < 0) {
                cambiarEstado("GAME_OVER");
                System.out.println("[PARTIDA FINALIZADA] El coche derrapó por completo.");
            }
            
            aEliminar.add(item); // El ítem desaparece de la pista al ser consumido
        }
    }

    public Coche getJugador() { return jugador; }
    public String getEstadoActual() { return estadoActual; }
}