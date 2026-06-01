package motor;

public class Item extends EntidadVideojuego {
    private final int efectoVelocidad;

    public Item(int x, int y, String nombre, int efectoVelocidad) {
        super(x, y, 1, 1, nombre, "ACTIVO", "texturas/item_" + nombre.toLowerCase() + ".png");
        this.efectoVelocidad = efectoVelocidad;
    }

    @Override
    public void actualizar() {
        // Los ítems de la pista suelen quedarse estáticos esperando ser recogidos
        System.out.println("[LOG - Item: " + getNombre() + "] Esperando en posición (X: " + getX() + ", Y: " + getY() + ")");
    }

    public int getEfectoVelocidad() { return efectoVelocidad; }
}