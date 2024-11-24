package vista;

import java.awt.*;
import javax.swing.*;

import controlador.ControladorJuego;

public class VistaSeleccionClase extends JPanel {
    private String nombreJugador;
    private ControladorJuego controlador;

    public VistaSeleccionClase(ControladorJuego controlador, String nombreJugador) {
        this.controlador = controlador;
        this.nombreJugador = nombreJugador;
        setLayout(new BorderLayout()); // Usamos un diseño BorderLayout para organizar los componentes

        // Mensaje de bienvenida
        JLabel mensaje = new JLabel("Bienvenido, " + nombreJugador + ". Selecciona tu clase:", JLabel.CENTER);
        mensaje.setFont(new Font("Arial", Font.BOLD, 18));
        mensaje.setForeground(new Color(0, 102, 204)); // Color azul claro para el texto
        add(mensaje, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar los botones
        panelBotones.setBackground(new Color(245, 245, 245)); // Fondo gris muy claro

        // Configuración de los botones
        JButton botonMago = new JButton("Mago");
        JButton botonGuerrero = new JButton("Guerrero");
        JButton botonArquero = new JButton("Arquero");

        // Estilo de los botones
        botonMago.setFont(new Font("Arial", Font.BOLD, 20));
        botonGuerrero.setFont(new Font("Arial", Font.BOLD, 20));
        botonArquero.setFont(new Font("Arial", Font.BOLD, 20));

        botonMago.setBackground(new Color(173, 216, 230)); // Fondo azul claro
        botonGuerrero.setBackground(new Color(255, 204, 102)); // Fondo dorado
        botonArquero.setBackground(new Color(144, 238, 144)); // Fondo verde claro

        botonMago.setIcon(new ImageIcon("C:/re/mago.jpeg"));
        botonGuerrero.setIcon(new ImageIcon("C:/re/gue.jpeg"));
        botonArquero.setIcon(new ImageIcon("C:/re/arq.jpeg"));

        botonMago.setForeground(Color.BLACK);
        botonGuerrero.setForeground(Color.BLACK);
        botonArquero.setForeground(Color.BLACK);

        botonMago.setFocusPainted(false); // Sin borde cuando se selecciona
        botonGuerrero.setFocusPainted(false);
        botonArquero.setFocusPainted(false);

        botonMago.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambio de cursor
        botonGuerrero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonArquero.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botonMago.addActionListener(e -> controlador.seleccionarPersonaje(nombreJugador, "Mago"));
        botonGuerrero.addActionListener(e -> controlador.seleccionarPersonaje(nombreJugador, "Guerrero"));
        botonArquero.addActionListener(e -> controlador.seleccionarPersonaje(nombreJugador, "Arquero"));

        // Añadimos los botones al panel central usando GridBagLayout para centrar
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre botones

        // Botones en el centro
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(botonMago, gbc);

        gbc.gridx = 1;
        panelBotones.add(botonGuerrero, gbc);

        gbc.gridx = 2;
        panelBotones.add(botonArquero, gbc);

        // Añadimos el panel de botones a la vista
        add(panelBotones, BorderLayout.CENTER);

        // Fondo general para la ventana
        setBackground(new Color(255, 255, 255)); // Fondo blanco claro
    }
}
