package modelo;

public class ObjetoView {
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private int cantidadCriaturas;
    private boolean reclamable;

    public ObjetoView(String nombre, String descripcion, String ubicacion, int cantidadCriaturas, boolean reclamable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.cantidadCriaturas = cantidadCriaturas;
        this.reclamable = reclamable;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCantidadCriaturas() {
        return cantidadCriaturas;
    }

    public boolean isReclamable() {
        return reclamable;
    }
}