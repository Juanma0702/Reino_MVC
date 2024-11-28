package vista;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import controlador.ControladorJuego;
import modelo.PersonajeView;

public class VistaEstadoPersonaje extends JPanel {
    PersonajeView personajeView;
    private ControladorJuego controlador;
    private BufferedImage backgroundImage;

    public VistaEstadoPersonaje(ControladorJuego controlador, PersonajeView personajeView) {
        this.controlador = controlador;
        this.personajeView = personajeView;

        try {
            backgroundImage = ImageIO.read(new File("Paradigma OO\\reino\\src\\resources\\vistaestado.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Título
        JLabel titulo = new JLabel("Estado del Personaje", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLACK); // Cambiar el color del título a negro
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Espaciado con el resto del contenido
        add(titulo, BorderLayout.NORTH);

        // Panel con el estado del personaje
        JPanel panelEstado = getPanelEstado();
        panelEstado.setOpaque(false);
        add(panelEstado, BorderLayout.CENTER);

        // Botón para volver al hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolverHub.setBackground(new Color(173, 216, 230));
        botonVolverHub.setForeground(new Color(0, 51, 102)); 
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
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel semitransparente para las etiquetas de texto
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(0, 1, 10, 10));
        panelTexto.setBackground(new Color(0, 0, 0, 150));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Mostrar los datos del personaje con estilo y letras blancas
        JLabel n = new JLabel("Nombre: " + personajeView.getNombre(), JLabel.CENTER);
        n.setFont(new Font("Arial", Font.BOLD, 18));
        n.setForeground(Color.WHITE);

        JLabel v = new JLabel("Vida: " + personajeView.getPuntosVida(), JLabel.CENTER);
        v.setFont(new Font("Arial", Font.BOLD, 18));
        v.setForeground(Color.WHITE);

        JLabel a = new JLabel("Ataque: " + personajeView.getNivelAtaque(), JLabel.CENTER);
        a.setFont(new Font("Arial", Font.BOLD, 18));
        a.setForeground(Color.WHITE);

        JLabel d = new JLabel("Defensa: " + personajeView.getNivelDefensa(), JLabel.CENTER);
        d.setFont(new Font("Arial", Font.BOLD, 18));
        d.setForeground(Color.WHITE);

        JLabel e = new JLabel("Experiencia: " + personajeView.getExperiencia(), JLabel.CENTER);
        e.setFont(new Font("Arial", Font.BOLD, 18));
        e.setForeground(Color.WHITE);

        panelTexto.add(n);
        panelTexto.add(v);
        panelTexto.add(a);
        panelTexto.add(d);
        panelTexto.add(e);
        panel.add(panelTexto);
        return panel;
    }
}
