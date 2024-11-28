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
        this.experiencia = 0;
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
        return experiencia / 100;
    }

    public List<Objeto> getInventario() {
        return inventario;
    }

    public void aplicarMejora(Objeto o) {
        if (o.getNombre().equals("Espada de fuego")) {
            // Incrementar el ataque del héroe en 20%
            nivelAtaque = (int) (nivelAtaque * 1.2);
        } else if (o.getNombre().equals("Amuleto de proteccion")) {
            // Incrementar la defensa del héroe en 15%
            nivelDefensa = (int) (nivelDefensa * 1.15);
        } else if (o.getNombre().equals("Arco de luz")) {
            // Incrementar el ataque del héroe en 25%
            nivelAtaque = (int) (nivelAtaque * 1.25);
        } else if (o.getNombre().equals("Escudo de titanio")) {
            // Incrementar la defensa en 30 puntos
            nivelDefensa += 30;
        }
    }

    public PersonajeView obtenerVista() {
        List<ObjetoView> inventarioview = new ArrayList<>();

        for (Objeto o : inventario) {
            inventarioview.add(o.obtenerVista());
        }
        return new PersonajeView(nombre, clase, puntosVida, maxVida, nivelAtaque, nivelDefensa, experiencia, inventarioview);
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
