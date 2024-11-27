package vista;

import controlador.ControladorJuego;
import modelo.MisionesView;
import modelo.ObjetoView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        for (ObjetoView objeto : misiones.getObjetos()) {
            if (objeto != null) {
                System.out.println("Agregando misión: " + objeto.getNombre());
                JPanel panelMision = new JPanel(new BorderLayout());
                panelMision.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                
                // Panel izquierdo: descripción de la misión
                JPanel panelDescripcion = new JPanel();
                panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
                panelDescripcion.setOpaque(false); // Sin color de fondo
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

    private List<Map<String, String>> definirMisiones() {
        List<Map<String, String>> misiones = new ArrayList<>();

        Map<String, String> mision1 = new HashMap<>();
        mision1.put("nombre", "Espada de fuego");
        mision1.put("objetivo", "Vencer al Dragón del Norte que habita en las Montañas Heladas.");
        mision1.put("recompensa", "Espada de Fuego (aumenta el nivel de ataque del Héroe en 20%).");
        mision1.put("reclamable", String.valueOf(controlador.esMisionReclamable("Derrota al Dragón del Norte")));
        misiones.add(mision1);

        Map<String, String> mision2 = new HashMap<>();
        mision2.put("nombre", "Amuleto de proteccion");
        mision2.put("objetivo", "Encontrar y recuperar el Amuleto Perdido en el Bosque de los Susurros.");
        mision2.put("recompensa", "Amuleto de Protección (aumenta la defensa del Héroe en 15%).");
        mision2.put("reclamable", String.valueOf(controlador.esMisionReclamable("Recupera el Amuleto Perdido")));
        misiones.add(mision2);

        Map<String, String> mision3 = new HashMap<>();
        mision3.put("nombre", "Arco de luz");
        mision3.put("objetivo", "Derrotar a 5 Espectros que infestan el Pantano Oscuro.");
        mision3.put("recompensa", "Arco de Luz (aumenta el nivel de ataque del Héroe en 25%).");
        mision3.put("reclamable", String.valueOf(controlador.esMisionReclamable("Elimina a los Espectros del Pantano")));
        misiones.add(mision3);

        Map<String, String> mision4 = new HashMap<>();
        mision4.put("nombre", "Escudo de titanio");
        mision4.put("objetivo", "Derrotar a 3 Trolls en la Aldea de los Sirith.");
        mision4.put("recompensa", "Escudo de Titanio (aumenta la defensa del Guerrero en 30 puntos).");
        mision4.put("reclamable", String.valueOf(controlador.esMisionReclamable("Limpia la Aldea de los Trolls")));
        misiones.add(mision4);

        return misiones;
    }
}