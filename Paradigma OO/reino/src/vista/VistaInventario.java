package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.ControladorJuego;
import modelo.PersonajeView;
import modelo.ObjetoView;

public class VistaInventario extends JPanel {
    private ControladorJuego controlador;
    private PersonajeView inventario;

    public VistaInventario(ControladorJuego controlador, PersonajeView inventario) {  
        this.controlador = controlador;
        this.inventario = inventario;

        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen exterior

        // Título
        JLabel titulo = new JLabel("Inventario", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central para la lista de inventario
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        if (this.inventario.getObjetos().isEmpty()) {
            // Mostrar mensaje si el inventario está vacío
            JLabel mensajeVacio = new JLabel("No hay objetos en el inventario.", JLabel.CENTER);
            mensajeVacio.setFont(new Font("Arial", Font.PLAIN, 16));
            mensajeVacio.setForeground(Color.RED);
            panelCentral.add(mensajeVacio);
        } else {
            // Crear un panel para cada objeto en el inventario
            for (ObjetoView datos : inventario.getObjetos()) {
                if (datos != null) {
                    System.out.println("Agregando objeto: " + datos.getNombre());
                    JPanel panelObjeto = new JPanel(new BorderLayout());
                    panelObjeto.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                    ));
                    
                    // Panel izquierdo: descripción del objeto
                    JPanel panelDescripcion = new JPanel();
                    panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
                    panelDescripcion.setOpaque(false); // Sin color de fondo
                    List<JLabel> labels = obtenerLabels(datos);
                    for (JLabel label : labels) {
                        panelDescripcion.add(label);
                    }
                    panelObjeto.add(panelDescripcion, BorderLayout.CENTER);

                    // Agregar el objeto al panel central
                    panelCentral.add(panelObjeto);
                    panelCentral.add(Box.createVerticalStrut(10)); // Espaciado entre objetos
                }
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

    private List<JLabel> obtenerLabels(ObjetoView datos) {
        List<JLabel> aux = new ArrayList<>();
        JLabel n = new JLabel(datos.getNombre(), JLabel.CENTER);
        JLabel v = new JLabel("Se encuentra en: " + datos.getUbicacion(), JLabel.CENTER);
        JLabel a = new JLabel("Descripción: " + datos.getDescripcion(), JLabel.CENTER);
        aux.add(n);
        aux.add(v);
        aux.add(a);
        if (datos.getCantidadCriaturas() != 0) {
            JLabel r = new JLabel("Custodiado por: " + datos.getCantidadCriaturas() + " criaturas", JLabel.CENTER);
            aux.add(r);
        }
        return aux;
    }
}
