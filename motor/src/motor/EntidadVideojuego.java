package motor;

public abstract class EntidadVideojuego {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private String nombre;
    private String estado;
    private String rutaImagenSimulada;

    public EntidadVideojuego(int x, int y, int ancho, int alto, String nombre, String estado, String rutaImagenSimulada) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.nombre = nombre;
        this.estado = estado;
        this.rutaImagenSimulada = rutaImagenSimulada;
    }

    public abstract void actualizar();

    // Getters y Setters (Encapsulamiento estricto)
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getRutaImagenSimulada() { return rutaImagenSimulada; }
}