package controlador;

import vista.*;
import modelo.*;
import java.util.*;
import javax.swing.*;

public class ControladorJuego {
    private static ControladorJuego instancia;
    private Juego juego;
    private String nombreJugador;
    private String clase;

    private ControladorJuego() {
        juego = new Juego();
    }

    public static ControladorJuego getInstancia() {
        if (instancia == null) {
            instancia = new ControladorJuego();
        }
        return instancia;
    }

    public void iniciarJuego() {
        // Instancia la vista principal
        VistaPrincipal.getInstancia().setVisible(true);
        mostrarVistaNombre();
    }

    public void cambiarVista(JPanel nuevaVista) {
        VistaPrincipal.getInstancia().setVista(nuevaVista);
    }

    public void seleccionarPersonaje(String nombreJugador, String clase) {
        this.nombreJugador = nombreJugador;
        this.clase = clase;
        juego.seleccionarPersonaje(nombreJugador, clase);
        mostrarVistaHub();
    }

    public void mostrarVistaEstadoPersonaje() {
        Map<String, String> datosPersonaje = juego.obtenerDatosPersonaje();
        cambiarVista(new VistaEstadoPersonaje(this, datosPersonaje));
    }

    public void mostrarVistaHub() {
        cambiarVista(new VistaHub(this, nombreJugador, clase));
    }

    public void mostrarVistaNombre() {
        cambiarVista(new VistaNombre(this));
    }

    public void actualizarMapaVista() {
        if (VistaPrincipal.getInstancia().getVistaActual() instanceof VistaMapa) {
            ((VistaMapa) VistaPrincipal.getInstancia().getVistaActual()).actualizarVisibilidadUbicaciones();
        }
    }

    public void avanzarUbicacion(String nombreUbicacion) {
        juego.avanzarUbicacion(nombreUbicacion, this);
        actualizarMapaVista(); // Asegurarse de actualizar la vista del mapa después de avanzar
    }

    public void mostrarVistaMapa() {
        String ubicacionActual = juego.getUbicacionActual();
        List<String> ubicaciones = juego.getUbicaciones();
        List<String> caminosDisponibles = juego.getCaminosDisponibles();
        cambiarVista(new VistaMapa(this, ubicacionActual, ubicaciones, caminosDisponibles));
    }

    public void mostrarVistaCombate(String resultadoCombate, boolean victoria, boolean combateFinal) {
        VistaCombate.mostrar(this, resultadoCombate, victoria, combateFinal);
    }

    public void reiniciarJuego() {
        // Lógica para reiniciar el juego
    }

    public String getUbicacionActual() {
        return juego.getUbicacionActual();
    }

    public List<Ubicacion> getUbicacionesActuales() {
        return juego.getUbicacionesActuales();
    }

    public String obtenerMensajeEventoEspecial(String nombreUbicacion) {
        for (Ubicacion ubicacion : juego.getUbicacionesActuales()) {
            if (ubicacion.getNombre().equals(nombreUbicacion) && ubicacion.getMensajeEventoEspecial() != null) {
                return ubicacion.getMensajeEventoEspecial();
            }
        }
        return null;
    }

    public List<String> getCaminosDisponibles() {
        return juego.getCaminosDisponibles();
    }
}
