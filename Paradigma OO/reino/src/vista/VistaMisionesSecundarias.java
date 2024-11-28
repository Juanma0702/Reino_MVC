package vista;

import controlador.ControladorJuego;
import modelo.MisionesView;
import modelo.ObjetoView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VistaMisionesSecundarias extends JPanel {
    private ControladorJuego controlador;
    private MisionesView misiones;

    public VistaMisionesSecundarias(ControladorJuego controlador, MisionesView misiones) {
        this.controlador = controlador;
        this.misiones = misiones;

        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        // Título
        JLabel titulo = new JLabel("Misiones Secundarias", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central para la lista de misiones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCentral.setBackground(Color.DARK_GRAY);

        // Crear un panel para cada misión
        for (ObjetoView objeto : misiones.getObjetos()) {
            if (objeto != null) {
                System.out.println("Agregando misión: " + objeto.getNombre());
                JPanel panelMision = new JPanel(new BorderLayout());
                panelMision.setBackground(Color.LIGHT_GRAY);
                panelMision.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                
                // Panel izquierdo: descripción de la misión
                JPanel panelDescripcion = new JPanel();
                panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
                panelDescripcion.setOpaque(false); 

                JLabel nombre = new JLabel(objeto.getNombre(), JLabel.CENTER);
                JLabel objetivo = new JLabel("Objetivo: " + objeto.getDescripcion(), JLabel.CENTER);
                JLabel recompensa = new JLabel("Recompensa: " + objeto.getNombre(), JLabel.CENTER);
                panelDescripcion.add(nombre);
                panelDescripcion.add(objetivo);
                panelDescripcion.add(recompensa);
                panelMision.add(panelDescripcion, BorderLayout.CENTER);

                // Botón "Reclamar" a la derecha
                JButton botonReclamar = new JButton("Reclamar");
                botonReclamar.setFont(new Font("Arial", Font.BOLD, 14));
                botonReclamar.setEnabled(controlador.esMisionReclamable(objeto.getNombre()));
                botonReclamar.addActionListener(e -> {
                    controlador.reclamarObjeto(objeto.getNombre());
                    botonReclamar.setEnabled(controlador.esMisionReclamable(objeto.getNombre()));
                });
                panelMision.add(botonReclamar, BorderLayout.EAST);

                panelCentral.add(panelMision);
                panelCentral.add(Box.createVerticalStrut(10)); 
            }
        }

        // Hacer el panel central scrollable
        JScrollPane scrollPane = new JScrollPane(panelCentral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 
        add(scrollPane, BorderLayout.CENTER);

        // Botón inferior para volver al Hub
        JButton botonVolverHub = new JButton("Volver al Hub");
        botonVolverHub.setFont(new Font("Arial", Font.BOLD, 14));
        botonVolverHub.addActionListener(e -> controlador.mostrarVistaHub());
        add(botonVolverHub, BorderLayout.SOUTH);
    }

}