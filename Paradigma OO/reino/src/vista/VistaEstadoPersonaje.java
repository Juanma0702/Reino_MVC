package vista;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import controlador.ControladorJuego;

public class VistaEstadoPersonaje extends JPanel {
    private Map<String, String> datosPersonaje;
    private ControladorJuego controlador;
    private BufferedImage backgroundImage; // Añadir esta línea

    public VistaEstadoPersonaje(ControladorJuego controlador, Map<String, String> datosPersonaje) {
        this.controlador = controlador;
        this.datosPersonaje = datosPersonaje;

        // Cargar la imagen de fondo
        try {
            backgroundImage = ImageIO.read(new File("Paradigma OO\\reino\\src\\resources\\vistaestado.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configuración del panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Fondo claro

        // Título
        JLabel titulo = new JLabel("Estado del Personaje", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLACK); // Cambiar el color del título a negro
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Espaciado con el resto del contenido
        add(titulo, BorderLayout.NORTH);

        // Panel con el estado del personaje
        JPanel panelEstado = getPanelEstado();
        panelEstado.setOpaque(false); // Hacer el panel transparente
        add(panelEstado, BorderLayout.CENTER);

        // Botón para volver al hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolverHub.setBackground(new Color(173, 216, 230)); // Azul claro
        botonVolverHub.setForeground(new Color(0, 51, 102)); // Azul oscuro
        botonVolverHub.setFocusPainted(false);
        botonVolverHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(botonVolverHub, BorderLayout.SOUTH);
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Método para crear y devolver el panel con el estado del personaje
    private JPanel getPanelEstado() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Espaciado entre elementos
        panel.setOpaque(false); // Hacer el panel transparente
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interno

        // Panel semitransparente para las etiquetas de texto
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(0, 1, 10, 10)); // Espaciado entre elementos
        panelTexto.setBackground(new Color(0, 0, 0, 150)); // Fondo negro semitransparente
        panelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        // Mostrar los datos del personaje
        if (datosPersonaje != null && !datosPersonaje.isEmpty()) {
            for (Map.Entry<String, String> entry : datosPersonaje.entrySet()) {
                JLabel label = new JLabel(entry.getKey() + ": " + entry.getValue(), JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 18)); // Aumentar el tamaño de la fuente
                label.setForeground(Color.WHITE); // Color del texto blanco
                panelTexto.add(label);
            }
        }

        panel.add(panelTexto);
        return panel;
    }
}
