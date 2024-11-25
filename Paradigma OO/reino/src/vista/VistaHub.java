package vista;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;

import controlador.ControladorJuego;

public class VistaHub extends JPanel {
    private ControladorJuego controlador;
    private String nombreJugador;
    private String clase;

    public VistaHub(ControladorJuego controlador, String nombreJugador, String clase) {
        this.controlador = controlador;
        this.nombreJugador = nombreJugador;
        this.clase = clase;

        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes externos

        // Título
        JLabel titulo = new JLabel("Hub Principal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Espaciado con el resto del contenido
        add(titulo, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBorder(BorderFactory.createEmptyBorder(100, 220, 100, 220)); // Márgenes internos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Espaciado entre botones
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Crear y configurar botones
        JButton botonMapa = crearBoton("Mapa", "C:\\Users\\MSI\\Desktop\\REINO_MVC\\Reino_MVC\\Paradigma OO\\reino\\src\\resources\\Mapa.png");
        JButton botonMisiones = crearBoton("Misiones", "C:/re/inventario.jpeg");
        JButton botonEstadoPersonaje = crearBoton("Estado Personaje", "C:/re/misiones.jpeg");
        JButton botonInventario = crearBoton("Inventario", "C:/re/salir.jpeg");

        // Añadir botones al panel con GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(botonMapa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotones.add(botonMisiones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBotones.add(botonEstadoPersonaje, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelBotones.add(botonInventario, gbc);

        // Añadir panel de botones al centro del panel principal
        add(panelBotones, BorderLayout.CENTER);

        // Panel inferior con el botón para cerrar el programa
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botonCerrar = crearBoton("Salir", "");
        botonCerrar.setBackground(new Color(178, 34, 34)); // Fondo rojo oscuro
        botonCerrar.setFont(new Font("Arial", Font.BOLD, 25));
        botonCerrar.setPreferredSize(new Dimension(100, 40));
        panelInferior.add(botonCerrar);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        botonCerrar.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        botonMapa.addActionListener(e -> controlador.mostrarVistaMapa());
        //botonMisiones.addActionListener(e -> controlador.mostrarVistaMisiones());
        botonEstadoPersonaje.addActionListener(e -> controlador.mostrarVistaEstadoPersonaje());
        //botonInventario.addActionListener(e -> controlador.mostrarVistaInventario());
    }

    private JButton crearBoton(String texto, String iconoPath) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(730, 370)); // Tamaño del botón
        boton.setMinimumSize(new Dimension(730, 370)); // Tamaño mínimo del botón
        boton.setMaximumSize(new Dimension(730, 370)); // Tamaño máximo del botón

        if (!iconoPath.isEmpty()) {
            File archivo = new File(iconoPath);
            if (!archivo.exists()) {
                System.err.println("La imagen no se encuentra en la ruta: " + iconoPath);
            } else {
                try {
                    ImageIcon icono = new ImageIcon(iconoPath);
                    if (icono.getIconWidth() == -1) {
                        System.err.println("Error al cargar la imagen, formato inválido o corrupto: " + iconoPath);
                    } else {
                        Image imagenEscalada = icono.getImage().getScaledInstance(boton.getPreferredSize().width - 50, boton.getPreferredSize().height - 50, Image.SCALE_SMOOTH);
                        boton.setIcon(new ImageIcon(imagenEscalada));
                        boton.setHorizontalTextPosition(SwingConstants.CENTER);
                        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
                    }
                } catch (Exception e) {
                    System.err.println("Error inesperado al cargar la imagen: " + iconoPath);
                    e.printStackTrace();
                }
            }
        }
        return boton;
    }
}