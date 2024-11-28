package vista;

import java.awt.*;
import javax.swing.*;

import controlador.ControladorJuego;

public class VistaNombre extends JPanel {
    private ControladorJuego controlador;
    private JTextField campoNombre;
    private Image imagenFondo = new ImageIcon(
            "Paradigma OO\\reino\\src\\resources\\menu.png").getImage();

    public VistaNombre(ControladorJuego controlador) {
        this.controlador = controlador;

        // Configuración del layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        // Etiqueta y campo de texto
        JLabel etiquetaNombre = new JLabel("Ingrese su nombre:") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibuja el contorno negro
                g2.setColor(Color.BLACK);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = 0;
                int y = fm.getAscent();
                g2.drawString(getText(), x - 1, y - 1);
                g2.drawString(getText(), x - 1, y + 1);
                g2.drawString(getText(), x + 1, y - 1);
                g2.drawString(getText(), x + 1, y + 1);

                // Dibuja el texto blanco
                g2.setColor(getForeground());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaNombre.setForeground(Color.WHITE); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(etiquetaNombre, gbc);

        campoNombre = new JTextField(20);
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(campoNombre, gbc);

        // Panel para los botonesI
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setOpaque(false); 

        JButton botonAceptar = crearBoton("Aceptar", new Color(46, 139, 87)); 
        botonAceptar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()) {
                controlador.cambiarVista(new VistaSeleccionClase(controlador, nombre));
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese su nombre.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonCancelar = crearBoton("Cancelar", new Color(178, 34, 34)); 
        botonCancelar.addActionListener(e -> System.exit(0));

        panelBotones.add(botonAceptar);
        panelBotones.add(botonCancelar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotones, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja la imagen de fondo
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("La imagen de fondo no se ha cargado correctamente.");
        }

        // Configuración para el texto con contorno
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 

        // Texto con contorno
        String texto = "¡Bienvenido al Reino Encantado!";
        Font fuente = new Font("Serif", Font.BOLD, 36); 
        g2d.setFont(fuente);

        // Coordenadas donde se dibuja el texto
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(texto) / 2;
        int y = 100;

        // Color del contorno
        g2d.setColor(Color.BLACK);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2d.drawString(texto, x + i, y + j); 
                }
            }
        }

        // Color del texto principal
        g2d.setColor(Color.WHITE);
        g2d.drawString(texto, x, y); 
    }

    /**
     * Método para crear un botón estilizado.
     * 
     * @param texto Texto del botón.
     * @param color Fondo del botón.
     * @return JButton estilizado.
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false); 
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);

        return boton;
    }
}
