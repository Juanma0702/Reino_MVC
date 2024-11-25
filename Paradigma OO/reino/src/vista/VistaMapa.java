package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

import controlador.ControladorJuego;

public class VistaMapa extends JPanel {
    private JLabel titulo;
    private JPanel panelMapa;
    private Map<String, JButton> botonesUbicaciones;
    private ControladorJuego controlador;
    private String ubicacionActual;
    private List<String> ubicaciones;
    private List<String> caminosDisponibles;

    // Constructor
    public VistaMapa(ControladorJuego controlador, String ubicacionActual, List<String> ubicaciones,
            List<String> caminosDisponibles) {
        this.controlador = controlador;
        this.ubicacionActual = ubicacionActual;
        this.ubicaciones = ubicaciones;
        this.caminosDisponibles = caminosDisponibles;
        this.botonesUbicaciones = new HashMap<>();

        // Configuración del panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255)); // Fondo blanco claro

        // Título que muestra la ubicación actual
        titulo = new JLabel("Mapa del Reino de Uadengard", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setForeground(new Color(0, 102, 204)); // Color azul claro
        add(titulo, BorderLayout.NORTH);

        // Panel para mostrar el mapa en una cuadrícula
        panelMapa = new JPanel();
        panelMapa.setLayout(new GridLayout(20, 3, 10, 10)); // Estructura de 20 filas por 3 columnas
        panelMapa.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        panelMapa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno
        add(panelMapa, BorderLayout.CENTER);

        // Crear los botones y añadirlos en la disposición deseada
        inicializarBotonesUbicaciones();

        // Habilitar manualmente el primer botón después de la inicialización
        JButton primerBoton = botonesUbicaciones.get(ubicacionActual);
        if (primerBoton != null) {
            primerBoton.setEnabled(true); // Habilitar el primer botón
        }

        // Botón para volver al Hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolverHub.setBackground(new Color(102, 204, 102)); // Fondo verde claro
        botonVolverHub.setForeground(Color.WHITE);
        botonVolverHub.setFocusPainted(false);
        botonVolverHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
        add(botonVolverHub, BorderLayout.SOUTH);
    }

    private void inicializarBotonesUbicaciones() {
        // Crear espacios vacíos en la cuadrícula
        for (int i = 0; i < 60; i++) {
            panelMapa.add(new JLabel(""));
        }

        // Añadir botones de ubicaciones en las posiciones específicas
        agregarBotonUbicacion(ubicaciones.get(0), 0, 1); // Entrada del Reino
        agregarBotonUbicacion(ubicaciones.get(1), 1, 1); // Camino del Bosque
        agregarBotonUbicacion(ubicaciones.get(2), 2, 1); // Bosque Encantado
        agregarBotonUbicacion(ubicaciones.get(3), 3, 0); // Colina del Viento (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(4), 3, 2); // Lago de Cristal (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(5), 4, 1); // Caverna Oscura
        agregarBotonUbicacion(ubicaciones.get(6), 5, 0); // Puente Antiguo (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(7), 5, 2); // Llanura Rocosa (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(8), 6, 1); // Montaña Helada (en el centro)
        agregarBotonUbicacion(ubicaciones.get(9), 7, 1); // Pantano de la Niebla
        agregarBotonUbicacion(ubicaciones.get(10), 8, 1); // Pueblo Abandonado
        agregarBotonUbicacion(ubicaciones.get(11), 9, 1); // Torre de Vigilancia
        agregarBotonUbicacion(ubicaciones.get(12), 10, 0); // Bosque Sombrío (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(13), 10, 2); // Ruinas Antiguas (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(14), 11, 1); // Campo de Batalla
        agregarBotonUbicacion(ubicaciones.get(15), 12, 0); // Valle de los Ecos (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(16), 12, 2); // Altar Oscuro (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(17), 13, 0); // Foso Profundo (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(18), 13, 2); // Cueva de los Tesoros (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(19), 14, 1); // Trono del Rey Olvidado
        agregarBotonUbicacion(ubicaciones.get(20), 15, 1); // Bosque Profundo
        agregarBotonUbicacion(ubicaciones.get(21), 16, 1); // Altar Sagrado
        agregarBotonUbicacion(ubicaciones.get(22), 17, 0); // Colina Brumosa (bifurcación izquierda)
        agregarBotonUbicacion(ubicaciones.get(23), 17, 2); // Desierto Sombrío (bifurcación derecha)
        agregarBotonUbicacion(ubicaciones.get(24), 18, 1); // Cascada Silenciosa
        agregarBotonUbicacion(ubicaciones.get(25), 19, 1); // Torre Espectral

        // Validar y repintar el panel para asegurar que se actualice visualmente
        panelMapa.revalidate();
        panelMapa.repaint();
    }

    private void agregarBotonUbicacion(String nombreUbicacion, int fila, int columna) {
        JButton botonUbicacion = new JButton(nombreUbicacion);
        botonUbicacion.setEnabled(false);
        botonUbicacion.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente de texto en negrita
        botonUbicacion.setBackground(new Color(173, 216, 230)); // Fondo azul claro
        botonUbicacion.setForeground(Color.BLACK); // Texto negro
        botonUbicacion.setFocusPainted(false); // Sin borde cuando se selecciona
        botonUbicacion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambio de cursor al pasar por encima

        botonUbicacion.addActionListener(e -> {
            botonUbicacion.setEnabled(false); // Deshabilitar el botón actual inmediatamente
            controlador.avanzarUbicacion(nombreUbicacion);
            actualizarVisibilidadUbicaciones(); // Actualiza los caminos disponibles
            mostrarMensajeEventoEspecial(nombreUbicacion); // Mostrar mensaje del evento especial
        });

        int posicion = fila * 3 + columna;
        panelMapa.remove(posicion);
        panelMapa.add(botonUbicacion, posicion);
        botonesUbicaciones.put(nombreUbicacion, botonUbicacion);
    }

    public void actualizar(String ubicacionActual, List<String> ubicaciones, List<String> caminosDisponibles) {
        this.ubicacionActual = ubicacionActual;
        this.ubicaciones = ubicaciones;
        this.caminosDisponibles = caminosDisponibles;
        actualizarVisibilidadUbicaciones();
    }

    private void mostrarMensajeEventoEspecial(String nombreUbicacion) {
        String mensaje = controlador.obtenerMensajeEventoEspecial(nombreUbicacion);
        if (mensaje != null) {
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }

    public void actualizarVisibilidadUbicaciones() {
        ubicacionActual = controlador.getUbicacionActual();
        caminosDisponibles = controlador.getCaminosDisponibles();

        // Deshabilitar todos los botones
        for (Map.Entry<String, JButton> entry : botonesUbicaciones.entrySet()) {
            entry.getValue().setEnabled(false); // Deshabilitar todos los botones
        }

        // Habilitar solo los botones de los caminos disponibles
        for (String ubicacion : caminosDisponibles) {
            JButton botonSiguiente = botonesUbicaciones.get(ubicacion);
            if (botonSiguiente != null) {
                botonSiguiente.setEnabled(true); // Habilita los botones accesibles
            }
        }

        titulo.setText("Ubicación actual: " + ubicacionActual);

        panelMapa.revalidate();
        panelMapa.repaint();
    }
}