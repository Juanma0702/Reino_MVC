package vista;

import java.awt.*;
import javax.swing.*;

import controlador.ControladorJuego;

public class VistaNombre extends JPanel {
    private ControladorJuego controlador;
    private JTextField campoNombre;
    private Image imagenFondo = new ImageIcon("fC:/resources/menu.jpg").getImage(); // Ruta de la imagen de fondo

    public VistaNombre(ControladorJuego controlador) {
        this.controlador = controlador;

        try {
            imagenFondo = new ImageIcon("C:/resources/menu.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen de fondo: " + e.getMessage());
        }

        // Configuración del layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Etiqueta y campo de texto
        JLabel etiquetaNombre = new JLabel("Ingrese su nombre:");
        etiquetaNombre.setFont(new Font("Arial", Font.PLAIN, 18));
        etiquetaNombre.setForeground(Color.BLACK); // Texto blanco para destacar
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
        panelBotones.setOpaque(false); // Fondo transparente para combinar con la imagen de fondo

        JButton botonAceptar = crearBoton("Aceptar", new Color(46, 139, 87)); // Verde
        botonAceptar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()) {
                controlador.cambiarVista(new VistaSeleccionClase(controlador, nombre));
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese su nombre.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonCancelar = crearBoton("Cancelar", new Color(178, 34, 34)); // Rojo
        botonCancelar.addActionListener(e -> System.exit(0));

        panelBotones.add(botonAceptar);
        panelBotones.add(botonCancelar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Suavizado

        // Texto con contorno
        String texto = "¡Bienvenido al Reino Encantado!";
        Font fuente = new Font("Serif", Font.BOLD, 36); // Fuente del texto
        g2d.setFont(fuente);

        // Coordenadas donde se dibuja el texto
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(texto) / 2;
        int y = 100;

        // Color del contorno
        g2d.setColor(Color.BLACK);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2d.drawString(texto, x + i, y + j); // Dibuja el contorno desplazado
                }
            }
        }

        // Color del texto principal
        g2d.setColor(Color.WHITE);
        g2d.drawString(texto, x, y); // Dibuja el texto principal encima
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
        boton.setFocusPainted(false); // Elimina el borde de enfoque
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Espacio para agregar imágenes al botón (opcional)
        // boton.setIcon(new ImageIcon("ruta/a/imagen.png"));

        return boton;
    }
}
