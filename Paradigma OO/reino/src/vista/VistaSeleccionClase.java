package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import controlador.ControladorJuego;

public class VistaSeleccionClase extends JPanel {
    private String nombreJugador;
    private ControladorJuego controlador;
    private BufferedImage imagenFondo;

    public VistaSeleccionClase(ControladorJuego controlador, String nombreJugador) {
        this.controlador = controlador;
        this.nombreJugador = nombreJugador;

        // Cargar la imagen de fondo
        try {
            imagenFondo = ImageIO.read(new File("Paradigma OO\\reino\\src\\resources\\clase.png"));
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen de fondo: " + e.getMessage());
        }

        setLayout(new BorderLayout()); // Usamos un diseño BorderLayout para organizar los componentes

        // Mensaje de bienvenida
        JLabel mensaje = new JLabel("Bienvenido, " + nombreJugador + ". Selecciona tu clase:", JLabel.CENTER);
        mensaje.setFont(new Font("Arial", Font.BOLD, 35));
        mensaje.setForeground(Color.WHITE); // Color azul claro para el texto
        add(mensaje, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar los botones
        panelBotones.setOpaque(false); // Fondo transparente para combinar con la imagen de fondo

        // Configuración de los botones
        JButton botonMago = new JButton("Mago");
        JButton botonGuerrero = new JButton("Guerrero");
        JButton botonArquero = new JButton("Arquero");

        // Estilo de los botones
        Dimension buttonSize = new Dimension(500, 630); // Tamaño predefinido para los botones

        botonMago.setFont(new Font("Arial", Font.BOLD, 20));
        botonGuerrero.setFont(new Font("Arial", Font.BOLD, 20));
        botonArquero.setFont(new Font("Arial", Font.BOLD, 20));

        botonMago.setBackground(new Color(173, 216, 230)); // Fondo azul claro
        botonGuerrero.setBackground(new Color(255, 204, 102)); // Fondo dorado
        botonArquero.setBackground(new Color(144, 238, 144)); // Fondo verde claro

        // Escalar y establecer iconos
        Dimension iconSize = new Dimension(500, 600); // Tamaño de la imagen
        botonMago.setIcon(escalarIcono(
                "Paradigma OO\\reino\\src\\resources\\mago.png",
                iconSize));
        botonGuerrero.setIcon(escalarIcono(
                "Paradigma OO\\reino\\src\\resources\\guerrero.png",
                iconSize));
        botonArquero.setIcon(escalarIcono(
                "Paradigma OO\\reino\\src\\resources\\arquero.png",
                iconSize));

        botonMago.setForeground(Color.BLACK);
        botonGuerrero.setForeground(Color.BLACK);
        botonArquero.setForeground(Color.BLACK);

        botonMago.setFocusPainted(false); // Sin borde cuando se selecciona
        botonGuerrero.setFocusPainted(false);
        botonArquero.setFocusPainted(false);

        botonMago.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambio de cursor
        botonGuerrero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonArquero.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Establecer tamaño predefinido para los botones
        botonMago.setPreferredSize(buttonSize);
        botonGuerrero.setPreferredSize(buttonSize);
        botonArquero.setPreferredSize(buttonSize);

        // Colocar el texto debajo de la imagen
        botonMago.setHorizontalTextPosition(SwingConstants.CENTER);
        botonMago.setVerticalTextPosition(SwingConstants.BOTTOM);
        botonGuerrero.setHorizontalTextPosition(SwingConstants.CENTER);
        botonGuerrero.setVerticalTextPosition(SwingConstants.BOTTOM);
        botonArquero.setHorizontalTextPosition(SwingConstants.CENTER);
        botonArquero.setVerticalTextPosition(SwingConstants.BOTTOM);

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Método para escalar iconos
    private ImageIcon escalarIcono(String path, Dimension size) {
        ImageIcon icono = new ImageIcon(path);
        Image imagenEscalada = icono.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
}
