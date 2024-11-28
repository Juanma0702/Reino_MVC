package modelo;

import controlador.ControladorJuego;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Juego {
    private Personaje personajeActual;
    private Mapa mapa;
    private static Juego instancia;

    private Juego() {
        personajeActual = new Mago("");
        mapa = new Mapa();
    }

    public static Juego getInstancia() {
        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    public void seleccionarPersonaje(String nombreJugador, String clase) {
        switch (clase) {
            case "Guerrero":
                personajeActual = new Guerrero(nombreJugador);
                break;
            case "Mago":
                personajeActual = new Mago(nombreJugador);
                break;
            case "Arquero":
                personajeActual = new Arquero(nombreJugador);
                break;
            default:
                throw new IllegalArgumentException("Clase de personaje desconocida: " + clase);
        }
        mapa = new Mapa();
    }

    public Map<String, String> obtenerDatosPersonaje() {
        if (personajeActual != null) {
            return personajeActual.obtenerDatos();
        }
        return null;
    }

    public String getUbicacionActual() {
        if (mapa != null) {
            return mapa.getUbicacionActual().getNombre();
        }
        return null;
    }

    public List<String> getUbicaciones() {
        if (mapa != null) {
            List<String> nombresUbicaciones = new ArrayList<>();
            for (Ubicacion ubicacion : mapa.getUbicaciones()) {
                nombresUbicaciones.add(ubicacion.getNombre());
            }
            return nombresUbicaciones;
        }
        return Collections.emptyList();
    }

    public List<String> getCaminosDisponibles() {
        if (mapa != null) {
            List<String> nombresCaminos = new ArrayList<>();
            for (Ubicacion ubicacion : mapa.getCaminosDisponibles()) {
                nombresCaminos.add(ubicacion.getNombre());
            }
            return nombresCaminos;
        }
        return Collections.emptyList();
    }

    public void aplicarMejora(String opcionMejora) {
        if (personajeActual != null) {
            personajeActual.subirNivel(opcionMejora);
        }
    }

    public void avanzarUbicacion(String nombreUbicacion, ControladorJuego controlador) {
        if (mapa != null) {
            for (Ubicacion ubicacion : mapa.getUbicaciones()) {
                if (ubicacion.getNombre().equals(nombreUbicacion)) {
                    mapa.avanzar(ubicacion);
                    if (!ubicacion.esNeutral()) {
                        ubicacion.CombateRealizado();
                        Combate combate = new Combate(personajeActual, ubicacion.getCriaturas(), true);
                        String resultadoCombate = combate.iniciarCombate(personajeActual.nombre);
                        boolean victoria = combate.getVictoria();
                        boolean combateFinal = ubicacion.getCriaturas().size() == 4 && ubicacion.getCriaturas().get(0) instanceof Dragon;
                        controlador.mostrarVistaCombate(resultadoCombate, victoria, combateFinal);
                    } else if (ubicacion.esNeutral()) {
                        personajeActual.restaurarVida(); // Restaurar vida 
                        for (int i = personajeActual.cantidadDeNiveles(); i > 0; i--) {
                            controlador.mostrarOpcionesMejora();
                        }
                    }
                    controlador.actualizarMapaVista();
                    break;
                }
            }
        }
    }

    public Personaje getPersonaje() {
        return personajeActual;
    }

    public List<Ubicacion> getUbicacionesActuales() {
        return mapa.getUbicaciones();
    }

    public void actualizarMapaVista(ControladorJuego controlador) {
        controlador.actualizarMapaVista();
    }

    public List<Objeto> getInventario() {
        // Retorna el inventario del personaje actual
        return personajeActual.getInventario();
    }

    public Objeto getObjetoPorNombre(String nombre) {
        for (Objeto objeto : Misiones.getInstancia(null).getObjetos()) {
            if (objeto.getNombre().equals(nombre)) {
                return objeto;
            }
        }
        return null;
    }
}