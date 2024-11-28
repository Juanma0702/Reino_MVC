package vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private BufferedImage backgroundImage;
    private JLabel mejorasRestantesLabel;

    // Constructor
    public VistaMapa(ControladorJuego controlador, String ubicacionActual, List<String> ubicaciones,
            List<String> caminosDisponibles) {
        this.controlador = controlador;
        this.ubicacionActual = ubicacionActual;
        this.ubicaciones = ubicaciones;
        this.caminosDisponibles = caminosDisponibles;
        this.botonesUbicaciones = new HashMap<>();

        // Cargar la imagen de fondo
        try {
            backgroundImage = ImageIO.read(new File("Paradigma OO\\reino\\src\\resources\\vistamapa.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configuración del panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255)); 

        // Título que muestra la ubicación actual
        titulo = new JLabel("Mapa del Reino de Uadengard", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        // Panel para mostrar el mapa en una cuadrícula
        panelMapa = new JPanel();
        panelMapa.setLayout(new GridLayout(20, 3, 10, 10)); 
        panelMapa.setOpaque(false); 
        panelMapa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(panelMapa, BorderLayout.CENTER);

        // Crear los botones y añadirlos en la disposición deseada
        inicializarBotonesUbicaciones();

        // Habilitar manualmente el primer botón después de la inicialización
        JButton primerBoton = botonesUbicaciones.get(ubicacionActual);
        if (primerBoton != null) {
            primerBoton.setEnabled(true);
        }

        // Botón para volver al Hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolverHub.setBackground(new Color(102, 204, 102));
        botonVolverHub.setForeground(Color.WHITE);
        botonVolverHub.setFocusPainted(false);
        botonVolverHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
        add(botonVolverHub, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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

        panelMapa.revalidate();
        panelMapa.repaint();
    }

    private void agregarBotonUbicacion(String nombreUbicacion, int fila, int columna) {
        JButton botonUbicacion = new JButton(nombreUbicacion);
        botonUbicacion.setEnabled(false);
        botonUbicacion.setFont(new Font("Arial", Font.BOLD, 12)); 
        botonUbicacion.setBackground(new Color(173, 216, 230)); 
        botonUbicacion.setForeground(Color.BLACK); 
        botonUbicacion.setFocusPainted(false); 
        botonUbicacion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botonUbicacion.addActionListener(e -> {
            botonUbicacion.setEnabled(false); 
            controlador.avanzarUbicacion(nombreUbicacion);
            actualizarVisibilidadUbicaciones(); 
            mostrarMensajeEventoEspecial(nombreUbicacion); 
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
            entry.getValue().setEnabled(false); 
        }

        // Habilitar solo los botones de los caminos disponibles
        for (String ubicacion : caminosDisponibles) {
            JButton botonSiguiente = botonesUbicaciones.get(ubicacion);
            if (botonSiguiente != null) {
                botonSiguiente.setEnabled(true); 
            }
        }

        titulo.setText("Ubicación actual: " + ubicacionActual);

        panelMapa.revalidate();
        panelMapa.repaint();
    }

    public void mostrarOpcionesMejora(int mejorasRestantes) {
        String mensaje = "Mejoras restantes: " + mejorasRestantes;
        Object[] opciones = {"Mejorar Vida", "Mejorar Ataque", "Mejorar Defensa"};
        int opcionElegida = JOptionPane.showOptionDialog(
                this,
                mensaje,
                "Opciones de Mejora",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcionElegida != JOptionPane.CLOSED_OPTION) {
            switch (opciones[opcionElegida].toString()) {
                case "Mejorar Vida":
                    controlador.aplicarMejora("vida");
                    break;
                case "Mejorar Ataque":
                    controlador.aplicarMejora("ataque");
                    break;
                case "Mejorar Defensa":
                    controlador.aplicarMejora("defensa");
                    break;
                default:
                    throw new IllegalArgumentException("Opción de mejora desconocida: " + opciones[opcionElegida]);
            }
        }
    }
}