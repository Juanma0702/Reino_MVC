package modelo;

import java.util.ArrayList;
import java.util.List;

public class PersonajeView {
    private String nombre;
    private String clase;
    private int puntosVida;
    private int maxVida;
    private int nivelAtaque;
    private int nivelDefensa;
    private int experiencia;
    private List<ObjetoView> inventario; // Nueva lista de objetos

    public PersonajeView(String nombre, String clase, int puntosVida, int maxVida, int nivelAtaque, int nivelDefensa, int experiencia, List<ObjetoView> inventario) {
        this.nombre = nombre;
        this.clase = clase;
        this.puntosVida = puntosVida;
        this.maxVida = maxVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experiencia = experiencia;
        this.inventario = inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getMaxVida() {
        return maxVida;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public int getNivelDefensa() {
        return nivelDefensa;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public List<ObjetoView> getObjetos() {
        return inventario;
    }

}
