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
    private BufferedImage backgroundImage;

    public VistaHub(ControladorJuego controlador, String nombreJugador, String clase) {
        this.controlador = controlador;
        this.nombreJugador = nombreJugador;
        this.clase = clase;

        // Cargar la imagen de fondo
        try {
            backgroundImage = ImageIO.read(new File("Paradigma OO\\reino\\src\\resources\\hub.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Hub Principal", JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibuja el contorno
                g2.setColor(Color.BLACK);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int x = (getWidth() - textWidth) / 2;
                int y = fm.getAscent();
                g2.drawString(getText(), x - 1, y - 1);
                g2.drawString(getText(), x - 1, y + 1);
                g2.drawString(getText(), x + 1, y - 1);
                g2.drawString(getText(), x + 1, y + 1);

                // Dibuja el texto
                g2.setColor(getForeground());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 
        add(titulo, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(100, 220, 100, 220)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Crear y configurar botones
        JButton botonMapa = crearBoton("Mapa","Paradigma OO\\reino\\src\\resources\\mapa.png");
        JButton botonMisiones = crearBoton("Misiones", "Paradigma OO\\reino\\src\\resources\\misiones.png");
        JButton botonEstadoPersonaje = crearBoton("Estado Personaje", "Paradigma OO\\reino\\src\\resources\\estado.png");
        JButton botonInventario = crearBoton("Inventario", "Paradigma OO\\reino\\src\\resources\\inventario.png");

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
        panelInferior.setOpaque(false);
        botonCerrar.setBackground(new Color(178, 34, 34)); 
        botonCerrar.setFont(new Font("Arial", Font.BOLD, 25));
        botonCerrar.setPreferredSize(new Dimension(100, 40));
        panelInferior.add(botonCerrar);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        botonCerrar.addActionListener(e -> System.exit(0)); // Cerramos
        botonMapa.addActionListener(e -> controlador.mostrarVistaMapa());
        botonMisiones.addActionListener(e -> controlador.mostrarVistaMisiones());
        botonEstadoPersonaje.addActionListener(e -> controlador.mostrarVistaEstadoPersonaje());
        botonInventario.addActionListener(e -> controlador.mostrarVistaInventario());
    }

    private JButton crearBoton(String texto, String iconoPath) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(640, 315)); 
        boton.setMinimumSize(new Dimension(640, 315)); 
        boton.setMaximumSize(new Dimension(640, 315)); 

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
                        Image imagenEscalada = icono.getImage().getScaledInstance(boton.getPreferredSize().width - 50,
                                boton.getPreferredSize().height - 50, Image.SCALE_SMOOTH);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}