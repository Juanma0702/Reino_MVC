package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.ControladorJuego;

public class VistaMisionesSecundarias extends JPanel {
    private ControladorJuego controlador;
    private List<Map<String, String>> misiones;

    public VistaMisionesSecundarias(ControladorJuego controlador, List<Map<String, String>> misiones) {
        this.controlador = controlador;
        this.misiones = misiones != null ? misiones : new ArrayList<>(); // Asegúrate de que la lista no sea null

        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen exterior

        // Título
        JLabel titulo = new JLabel("Misiones Secundarias", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central para la lista de misiones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        // Crear un panel para cada misión
        for (Map<String, String> datos : misiones) {
            if (datos != null) {
                JPanel panelMision = new JPanel(new BorderLayout());
                panelMision.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1), // Borde gris
                    BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margen interno
                ));
                
                // Panel izquierdo: descripción de la misión
                JPanel panelDescripcion = new JPanel();
                panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
                panelDescripcion.setOpaque(false); // Sin color de fondo
                List<JLabel> labels = obtenerLabels(datos);
                for (JLabel label : labels) {
                    label.setFont(new Font("Arial", Font.PLAIN, 14));
                    panelDescripcion.add(label);
                }
                panelMision.add(panelDescripcion, BorderLayout.CENTER);

                // Botón "Reclamar" a la derecha
                JButton botonReclamar = new JButton("Reclamar");
                botonReclamar.setFont(new Font("Arial", Font.BOLD, 14));
                botonReclamar.setEnabled(Boolean.parseBoolean(datos.get("reclamable")));
                botonReclamar.addActionListener(e -> {
                    controlador.reclamarObjeto(datos.get("nombre"));
                    JOptionPane.showMessageDialog(this, "Recompensa reclamada: " + datos.get("nombre"));
                    botonReclamar.setEnabled(false); // Deshabilitar después de reclamar
                });
                panelMision.add(botonReclamar, BorderLayout.EAST);

                // Agregar la misión al panel central
                panelCentral.add(panelMision);
                panelCentral.add(Box.createVerticalStrut(10)); // Espaciado entre misiones
            }
        }

        // Hacer el panel central scrollable
        JScrollPane scrollPane = new JScrollPane(panelCentral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Sin bordes adicionales
        add(scrollPane, BorderLayout.CENTER);

        // Botón inferior para volver al Hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.BOLD, 14));
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
        add(botonVolverHub, BorderLayout.SOUTH);
    }

    private List<JLabel> obtenerLabels(Map<String, String> datos) {
        List<JLabel> aux = new ArrayList<>();
        JLabel n = new JLabel(datos.get("nombre"), JLabel.CENTER);
        JLabel v = new JLabel("Se encuentra en: " + datos.get("ubicacion"), JLabel.CENTER);
        JLabel a = new JLabel("Descripción: " + datos.get("descripcion"), JLabel.CENTER);
        aux.add(n);
        aux.add(v);
        aux.add(a);
        if (!datos.get("criaturas").equals("0")) {
            JLabel r = new JLabel("Custodiado por: " + datos.get("criaturas") + " criaturas", JLabel.CENTER);
            aux.add(r);
        }
        return aux;
    }
}
