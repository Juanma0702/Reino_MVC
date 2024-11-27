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

    public void mostrarVistaHub() {
        cambiarVista(new VistaHub(this, nombreJugador, clase));
    }

    public void mostrarVistaMisiones() {
        List<Objeto> misiones = Misiones.getInstancia(null).getObjetos();
        List<Map<String, String>> datosMisiones = new ArrayList<>();
        for (Objeto mision : misiones) {
            if (mision != null) {
                datosMisiones.add(mision.getDatos());
            }
        }
        cambiarVista(new VistaMisionesSecundarias(this, datosMisiones));
    }

    public void mostrarVistaInventario() {
        List<Objeto> inventario = juego.getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto objeto : inventario) {
            if (objeto != null) {
                datosInventario.add(objeto.getDatos());
            }
        }
        cambiarVista(new VistaInventario(this, datosInventario));
    }

    public void mostrarVistaEstadoPersonaje() {
        Map<String, String> datosPersonaje = juego.obtenerDatosPersonaje();
        cambiarVista(new VistaEstadoPersonaje(this, datosPersonaje));
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

    public void reclamarObjeto(String nombreObjeto) {
        Objeto objeto = obtenerObjetoPorNombre(nombreObjeto);
        if (objeto != null) {
            objeto.reclamar(juego.getPersonaje());
        }
    }

    public Objeto obtenerObjetoPorNombre(String nombre) {
        return juego.getObjetoPorNombre(nombre);
    }

    public List<Map<String, String>> obtenerDatosMisiones() {
        List<Objeto> misiones = Misiones.getInstancia(null).getObjetos();
        List<Map<String, String>> datosMisiones = new ArrayList<>();
        for (Objeto o : misiones) {
            Map<String, String> datos = new HashMap<>();
            datos.put("nombre", o.getNombre());
            datos.put("descripcion", o.getDescripcion());
            datos.put("ubicacion", o.getUbicacion().getNombre());
            datos.put("criaturas", String.valueOf(o.getCriaturas().size()));
            datos.put("reclamable", String.valueOf(o.esReclamable()));
            datosMisiones.add(datos);
        }
        return datosMisiones;
    }

    public List<Map<String, String>> obtenerDatosInventario() {
        List<Objeto> inventario = juego.getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto o : inventario) {
            Map<String, String> datos = new HashMap<>();
            datos.put("nombre", o.getNombre());
            datos.put("descripcion", o.getDescripcion());
            datos.put("ubicacion", o.getUbicacion().getNombre());
            datos.put("criaturas", String.valueOf(o.getCriaturas().size()));
            datosInventario.add(datos);
        }
        return datosInventario;
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
