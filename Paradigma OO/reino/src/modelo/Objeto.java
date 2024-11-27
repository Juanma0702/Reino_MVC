package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Objeto {
    private String nombre;
    private String descripcion;
    private Ubicacion ubicacion;
    private Personaje heroe;
    private List<Criatura> criaturas;
    private boolean reclamable;
    private Map<String, Integer> mejoras;

    public Objeto(String nombre, String descripcion, Ubicacion ubicacion, List<Criatura> criaturas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.criaturas = criaturas;
        this.mejoras = new HashMap<>();
    }

    public void encontrado() {
        this.reclamable = true;
    }

    public boolean esReclamable() {
        return heroe == null && reclamable;
    }

    public void reclamar(Personaje heroe) {
        this.heroe = heroe;
        heroe.objetoEncontrado(this);
        heroe.aplicarMejora(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public Map<String, Integer> getMejoras() {
        return mejoras;
    }

    public void agregarMejora(String tipo, int valor) {
        mejoras.put(tipo, valor);
    }

    public Map<String, String> getDatos() {
        Map<String, String> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("descripcion", descripcion);
        datos.put("ubicacion", ubicacion.getNombre());
        datos.put("criaturas", String.valueOf(criaturas.size()));
        datos.put("reclamable", String.valueOf(esReclamable()));
        return datos;
    }
}