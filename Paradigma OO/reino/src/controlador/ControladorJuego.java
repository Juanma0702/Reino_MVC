package controlador;

import java.util.*;
import javax.swing.*;
import modelo.*;
import vista.*;

public class ControladorJuego {
    private static ControladorJuego instancia;
    private String nombreJugador;
    private String clase;
    private Misiones misiones;
    private VistaMapa vistaMapa;

    private ControladorJuego() {
        Juego.getInstancia();
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
            vistaPrincipal.getVistaActual().setVisible(false);
        } else if (vistaPrincipal.getVistaActual() != null) {
            vistaPrincipal.remove(vistaPrincipal.getVistaActual());
        }
        vistaPrincipal.setVista(nuevaVista);
        nuevaVista.setVisible(true); 
    }

    public void seleccionarPersonaje(String nombreJugador, String clase) {
        System.out.println("Seleccionando personaje: " + nombreJugador + ", Clase: " + clase);
        this.nombreJugador = nombreJugador;
        this.clase = clase;
        Juego.getInstancia().seleccionarPersonaje(nombreJugador, clase);
        mostrarVistaHub();
    }

    public void mostrarVistaHub() {
        System.out.println("Mostrando vista Hub");
        cambiarVista(new VistaHub(this, nombreJugador, clase));
    }

    public void mostrarVistaMisiones() {
        System.out.println("Mostrando vista Misiones");
        cambiarVista(new VistaMisionesSecundarias(this, Misiones.getInstancia(null).obtenerVista()));
    }

    public void mostrarVistaInventario() {
        System.out.println("Mostrando vista Inventario");
        List<Objeto> inventario = Juego.getInstancia().getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto objeto : inventario) {
            datosInventario.add(objeto.getDatos());
        }
        cambiarVista(new VistaInventario(this, Juego.getInstancia().getPersonaje().obtenerVista()));
    }

    public void mostrarVistaEstadoPersonaje() {
        System.out.println("Mostrando vista Estado Personaje");
        PersonajeView datosPersonaje = Juego.getInstancia().getPersonaje().obtenerVista();
        cambiarVista(new VistaEstadoPersonaje(this, datosPersonaje));
    }

    public void mostrarVistaNombre() {
        System.out.println("Mostrando vista Nombre");
        cambiarVista(new VistaNombre(this));
    }

    public void actualizarMapaVista() {
        if (vistaMapa != null) {
            String ubicacionActual = Juego.getInstancia().getUbicacionActual();
            List<String> ubicaciones = Juego.getInstancia().getUbicaciones();
            List<String> caminosDisponibles = Juego.getInstancia().getCaminosDisponibles();
            vistaMapa.actualizar(ubicacionActual, ubicaciones, caminosDisponibles);
        }
    }

    public void avanzarUbicacion(String nombreUbicacion) {
        System.out.println("Avanzando a ubicación: " + nombreUbicacion);
        Juego.getInstancia().avanzarUbicacion(nombreUbicacion, this);
        actualizarMapaVista();
    }

    public void mostrarVistaMapa() {
        System.out.println("Mostrando vista Mapa");
        if (vistaMapa == null) {
            String ubicacionActual = Juego.getInstancia().getUbicacionActual();
            List<String> ubicaciones = Juego.getInstancia().getUbicaciones();
            List<String> caminosDisponibles = Juego.getInstancia().getCaminosDisponibles();
            vistaMapa = new VistaMapa(this, ubicacionActual, ubicaciones, caminosDisponibles);
        }
        cambiarVista(vistaMapa);
    }

    public void mostrarVistaCombate(String resultadoCombate, boolean victoria, boolean combateFinal) {
        System.out.println("Mostrando vista Combate");
        VistaCombate.mostrar(this, resultadoCombate, victoria, combateFinal);
    }

    public void reiniciarJuego() {
        //System.out.println("Reiniciando juego...");
    }

    public void reclamarObjeto(String nombreObjeto) {
        System.out.println("Reclamando objeto: " + nombreObjeto);
        Objeto objeto = obtenerObjetoPorNombre(nombreObjeto);
        if (objeto != null && objeto.esReclamable()) {
            objeto.reclamar(Juego.getInstancia().getPersonaje());
        } else {
            System.out.println("El objeto no es reclamable o no existe.");
        }
    }

    public boolean esMisionReclamable(String nombreMision) {
        List<Objeto> objetos = Misiones.getInstancia(null).getObjetos();
        for (Objeto objeto : objetos) {
            if(objeto.esReclamable() && objeto.getNombre().equals(nombreMision)){
                return objeto.esReclamable();
            }
        }
        return false;
    }

    public Objeto obtenerObjetoPorNombre(String nombre) {
        return Juego.getInstancia().getObjetoPorNombre(nombre);
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
        List<Objeto> inventario = Juego.getInstancia().getInventario();
        List<Map<String, String>> datosInventario = new ArrayList<>();
        for (Objeto o : inventario) {
            datosInventario.add(o.getDatos());
        }
        return datosInventario;
    }

    public String getUbicacionActual() {
        return Juego.getInstancia().getUbicacionActual();
    }

    public List<Ubicacion> getUbicacionesActuales() {
        return Juego.getInstancia().getUbicacionesActuales();
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
        return Juego.getInstancia().getCaminosDisponibles();
    }

    public int obtenerCantidadMejorasRestantes() {
        return Juego.getInstancia().getPersonaje().cantidadDeNiveles();
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
        Juego.getInstancia().aplicarMejora(opcionMejora);
        mostrarVistaMapa(); // Volver al mapa después de aplicar la mejora
    }
}
