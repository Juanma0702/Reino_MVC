package modelo;

import java.util.*;

public abstract class Personaje {
    protected String nombre;
    protected String clase;
    protected int puntosVida;
    protected int maxVida;
    protected int nivelAtaque;
    protected int nivelDefensa;
    protected int experiencia;
    private List<Objeto> inventario = new ArrayList<>();

    public Personaje(String nombre, int puntosVida, int maxVida, int nivelAtaque, int nivelDefensa, String clase) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.maxVida = maxVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experiencia = 0;  // Inicializamos la experiencia en 0
        this.clase = clase;
    }

    public Map<String, String> obtenerDatos() {
        Map<String, String> datos = new HashMap<>();
        datos.put("Nombre", nombre);
        datos.put("Clase", clase);
        datos.put("Vida", String.valueOf(puntosVida));
        datos.put("MaxVida", String.valueOf(maxVida));
        datos.put("Ataque", String.valueOf(nivelAtaque));
        datos.put("Defensa", String.valueOf(nivelDefensa));
        datos.put("Experiencia", String.valueOf(experiencia));
        return datos;
    }

    public void objetoEncontrado(Objeto objeto) {
        inventario.add(objeto);
    }

    public void ganarExperiencia(int experiencia) {
        this.experiencia += experiencia;
    }

    public boolean sigueVivo() {
        return puntosVida > 0;
    }

    public void restaurarVida() {
        puntosVida = maxVida;
    }

    public int cantidadDeNiveles() {
        // Implementar la lógica para calcular la cantidad de niveles
        return experiencia / 100; // Ejemplo: 1 nivel por cada 100 puntos de experiencia
    }

    public List<Objeto> getInventario() {
        return inventario;
    }

    public void subirNivel(String opcionMejora) {
        experiencia -= 100; // Restar 100 puntos de experiencia por subir de nivel
        switch (opcionMejora) {
            case "vida":
                maxVida += 10;
                puntosVida = maxVida;
                break;
            case "ataque":
                nivelAtaque += 5;
                break;
            case "defensa":
                nivelDefensa += 5;
                break;
            default:
                throw new IllegalArgumentException("Opción de mejora desconocida: " + opcionMejora);
        }
    }

    // Métodos abstractos para el sistema de combate
    public abstract int recibirDanio(int danio, Criatura c);
    public abstract int hacerDanio(Criatura c);
}    
