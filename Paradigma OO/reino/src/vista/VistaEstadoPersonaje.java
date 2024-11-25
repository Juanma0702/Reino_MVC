package vista;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import controlador.ControladorJuego;

public class VistaEstadoPersonaje extends JPanel {
    private Map<String, String> datosPersonaje;
    private ControladorJuego controlador;

    public VistaEstadoPersonaje(ControladorJuego controlador, Map<String, String> datosPersonaje) {
        this.controlador = controlador;
        this.datosPersonaje = datosPersonaje;

        // Configuración del panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Fondo claro

        // Título
        JLabel titulo = new JLabel("Estado del Personaje", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Espaciado con el resto del contenido
        add(titulo, BorderLayout.NORTH);

        // Panel con el estado del personaje
        JPanel panelEstado = getPanelEstado();
        add(panelEstado, BorderLayout.CENTER);

        // Botón para volver al hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolverHub.setBackground(new Color(173, 216, 230)); // Azul claro
        botonVolverHub.setForeground(new Color(0, 51, 102)); // Azul oscuro
        botonVolverHub.setFocusPainted(false);
        botonVolverHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
        add(botonVolverHub, BorderLayout.SOUTH);
    }

    // Método para crear y devolver el panel con el estado del personaje
    private JPanel getPanelEstado() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Espaciado entre elementos
        panel.setBackground(new Color(255, 255, 255)); // Fondo blanco
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interno

        // Mostrar los datos del personaje
        if (datosPersonaje != null && !datosPersonaje.isEmpty()) {
            for (Map.Entry<String, String> entry : datosPersonaje.entrySet()) {
                JLabel jLabel = new JLabel(entry.getKey() + ": " + entry.getValue(), JLabel.CENTER);
                jLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                jLabel.setForeground(new Color(0, 51, 102)); // Azul oscuro
                panel.add(jLabel);
            }
        } else {
            JLabel errorLabel = new JLabel("No se encontraron datos del personaje.", JLabel.CENTER);
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            errorLabel.setForeground(Color.RED);
            panel.add(errorLabel);
        }

        return panel;
    }
}
