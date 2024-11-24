package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import controlador.ControladorJuego;

public class Juego {
    private Personaje personajeActual;
    private Mapa mapa;

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

    public void avanzarUbicacion(String nombreUbicacion, ControladorJuego controlador) {
        if (mapa != null) {
            for (Ubicacion ubicacion : mapa.getUbicaciones()) {
                if (ubicacion.getNombre().equals(nombreUbicacion)) {
                    mapa.avanzar(ubicacion);
                    if (!ubicacion.esNeutral() && !ubicacion.isCombateRealizado()) {
                        ubicacion.setCombateRealizado(true);
                        Combate combate = new Combate(personajeActual, ubicacion.getCriaturas(), true);
                        String resultadoCombate = combate.iniciarCombate(personajeActual.nombre);
                        boolean victoria = combate.getVictoria();
                        boolean combateFinal = ubicacion.getCriaturas().size() == 4 && ubicacion.getCriaturas().get(0) instanceof Dragon;
                        controlador.mostrarVistaCombate(resultadoCombate, victoria, combateFinal);
                    } else if (ubicacion.esNeutral()) {
                        personajeActual.restaurarVida(); // Restaurar vida en ubicaciones neutrales.
                        for (int i = personajeActual.cantidadDeNiveles(); i > 0; i--) {
                            personajeActual.subirNivel();
                        }
                    }
                    controlador.actualizarMapaVista();
                    break;
                }
            }
        }
    }

    public void iniciarEvento() {
        Ubicacion ubicacion = mapa.getUbicacionActual();
        if (ubicacion.getEventoEspecial() != null) {
            ubicacion.getEventoEspecial().run(); // Ejecutar el evento especial si está configurado.
        }
    }

    public void actualizarMapaVista(ControladorJuego controlador) {
        controlador.actualizarMapaVista();
    }
}
