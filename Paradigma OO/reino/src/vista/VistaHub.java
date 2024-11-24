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
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Espaciado con el resto del contenido
        add(titulo, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes internos
        // Panel inferior con el botón para cerrar el programa
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear y configurar botones
        JButton botonMapa = crearBoton("Mapa","C:/re/mapa.jpeg");
        JButton botonMisiones = crearBoton("Misiones", "C:/re/inventario.jpeg");
        JButton botonEstadoPersonaje = crearBoton("Estado Personaje", "C:/re/misiones.jpeg");
        JButton botonInventario = crearBoton("Inventario", "C:/re/salir.jpeg");
        JButton botonCerrar = crearBoton("Salir", "");
        botonCerrar.setBackground(new Color(178, 34, 34)); // Fondo rojo oscuro
        botonCerrar.setFont(new Font("Arial", Font.BOLD, 25));
        botonCerrar.setPreferredSize(new Dimension(100, 40));

        // Añadir botones al panel
        panelBotones.add(botonMapa);
        panelBotones.add(botonMisiones);
        panelBotones.add(botonEstadoPersonaje);
        panelBotones.add(botonInventario);
        panelInferior.add(botonCerrar);

        // Añadir panel de botones al centro del panel principal
        add(panelBotones, BorderLayout.CENTER);
        // Añadir panel inferior al sur del panel principal
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        botonCerrar.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        botonMapa.addActionListener(e -> controlador.mostrarVistaMapa()); // Cambia la vista a VistaMapa
        botonEstadoPersonaje.addActionListener(e -> controlador.mostrarVistaEstadoPersonaje()); // Cambia a VistaEstadoPersonaje                                                                 // VistaEstadoPersonaje
    }

    private JButton crearBoton(String texto, String iconoPath) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }
}