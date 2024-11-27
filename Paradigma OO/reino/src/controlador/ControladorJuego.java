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
    private VistaMapa vistaMapa;

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
        System.out.println("Iniciando juego...");
        VistaPrincipal.getInstancia().setVisible(true);
        mostrarVistaNombre();
    }

    public void cambiarVista(JPanel nuevaVista) {
        System.out.println("Cambiando vista a: " + nuevaVista.getClass().getSimpleName());
        VistaPrincipal vistaPrincipal = VistaPrincipal.getInstancia();
        if (vistaPrincipal.getVistaActual() instanceof VistaMapa) {
            vistaPrincipal.getVistaActual().setVisible(false); // Ocultar la vista del mapa
        } else if (vistaPrincipal.getVistaActual() != null) {
            vistaPrincipal.remove(vistaPrincipal.getVistaActual());
        }
        vistaPrincipal.setVista(nuevaVista);
        nuevaVista.setVisible(true); // Asegurarse de que la nueva vista sea visible
    }

    public void seleccionarPersonaje(String nombreJugador, String clase) {
        System.out.println("Seleccionando personaje: " + nombreJugador + ", Clase: " + clase);
        this.nombreJugador = nombreJugador;
        this.clase = clase;
        juego.seleccionarPersonaje(nombreJugador, clase);
        mostrarVistaHub();
    }

    public void mostrarVistaHub() {
        System.out.println("Mostrando vista Hub");
        cambiarVista(new VistaHub(this, nombreJugador, clase));
    }

    public void mostrarVistaMisiones() {
        System.out.println("Mostrando vista Misiones");
        cambiarVista(new VistaMisionesSecundarias(this));
    }

    public void mostrarVistaInventario() {
        System.out.println("Mostrando vista Inventario");
        List<Objeto> inventario = juego.getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto objeto : inventario) {
            datosInventario.add(objeto.getDatos());
        }
        cambiarVista(new VistaInventario(this, datosInventario));
    }

    public void mostrarVistaEstadoPersonaje() {
        System.out.println("Mostrando vista Estado Personaje");
        Map<String, String> datosPersonaje = juego.obtenerDatosPersonaje();
        cambiarVista(new VistaEstadoPersonaje(this, datosPersonaje));
    }

    public void mostrarVistaNombre() {
        System.out.println("Mostrando vista Nombre");
        cambiarVista(new VistaNombre(this));
    }

    public void actualizarMapaVista() {
        if (vistaMapa != null) {
            String ubicacionActual = juego.getUbicacionActual();
            List<String> ubicaciones = juego.getUbicaciones();
            List<String> caminosDisponibles = juego.getCaminosDisponibles();
            vistaMapa.actualizar(ubicacionActual, ubicaciones, caminosDisponibles);
        }
    }

    public void avanzarUbicacion(String nombreUbicacion) {
        System.out.println("Avanzando a ubicación: " + nombreUbicacion);
        juego.avanzarUbicacion(nombreUbicacion, this);
        actualizarMapaVista(); // Asegurarse de actualizar la vista del mapa después de avanzar
    }

    public void mostrarVistaMapa() {
        System.out.println("Mostrando vista Mapa");
        if (vistaMapa == null) {
            String ubicacionActual = juego.getUbicacionActual();
            List<String> ubicaciones = juego.getUbicaciones();
            List<String> caminosDisponibles = juego.getCaminosDisponibles();
            vistaMapa = new VistaMapa(this, ubicacionActual, ubicaciones, caminosDisponibles);
        }
        cambiarVista(vistaMapa);
    }

    public void mostrarVistaCombate(String resultadoCombate, boolean victoria, boolean combateFinal) {
        System.out.println("Mostrando vista Combate");
        VistaCombate.mostrar(this, resultadoCombate, victoria, combateFinal);
    }

    public void reiniciarJuego() {
        System.out.println("Reiniciando juego...");
        // Lógica para reiniciar el juego
    }

    public void reclamarObjeto(String nombreObjeto) {
        System.out.println("Reclamando objeto: " + nombreObjeto);
        Objeto objeto = obtenerObjetoPorNombre(nombreObjeto);
        if (objeto != null && objeto.esReclamable()) {
            objeto.reclamar(juego.getPersonaje());
            mostrarVistaInventario(); // Actualizar la vista del inventario después de reclamar el objeto
        } else {
            System.out.println("El objeto no es reclamable o no existe.");
        }
    }

    public boolean esMisionReclamable(String nombreMision) {
        return Misiones.getInstancia(null).getObjetos().stream()
            .anyMatch(m -> m.getNombre().equals(nombreMision) && m.esReclamable());
    }

    public Objeto obtenerObjetoPorNombre(String nombre) {
        return juego.getObjetoPorNombre(nombre);
    }

    public List<Map<String, String>> obtenerDatosMisiones() {
        List<Objeto> misiones = Misiones.getInstancia(null).getObjetos();
        List<Map<String, String>> datosMisiones = new ArrayList<>();
        for (Objeto o : misiones) {
            datosMisiones.add(o.getDatos());
        }
        return datosMisiones;
    }

    public List<Map<String, String>> obtenerDatosInventario() {
        List<Objeto> inventario = juego.getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto o : inventario) {
            datosInventario.add(o.getDatos());
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
        for (Ubicacion ubicacion : getUbicacionesActuales()) {
            if (ubicacion.getNombre().equals(nombreUbicacion)) {
                return ubicacion.getMensajeEventoEspecial();
            }
        }
        return null;
    }

    public List<String> getCaminosDisponibles() {
        return juego.getCaminosDisponibles();
    }

    public int obtenerCantidadMejorasRestantes() {
        return juego.getPersonaje().cantidadDeNiveles();
    }

    public void mostrarOpcionesMejora() {
        System.out.println("Mostrando opciones de mejora");
        if (vistaMapa != null) {
            int mejorasRestantes = obtenerCantidadMejorasRestantes();
            vistaMapa.mostrarOpcionesMejora(mejorasRestantes);
        }
    }
    
    public void aplicarMejora(String opcionMejora) {
        System.out.println("Aplicando mejora: " + opcionMejora);
        juego.aplicarMejora(opcionMejora);
        mostrarVistaMapa(); // Volver al mapa después de aplicar la mejora
    }
}
