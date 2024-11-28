package vista;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import controlador.ControladorJuego;

public class VistaCombate extends JPanel {

    private JEditorPane areaCombate; 
    private boolean victoria;
    private ControladorJuego controlador;
    private boolean combateFinal;

    public VistaCombate(ControladorJuego controlador, String resultadoCombate, boolean victoria, boolean combateFinal) {
        this.controlador = controlador;
        this.victoria = victoria;
        this.combateFinal = combateFinal;

        setLayout(new BorderLayout());

        areaCombate = new JEditorPane();
        areaCombate.setEditable(false); 
        areaCombate.setContentType("text/html");
        areaCombate.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        areaCombate.setText(resultadoCombate); 
        JScrollPane scrollPane = new JScrollPane(areaCombate);
        add(scrollPane, BorderLayout.CENTER);

        mostrarResultado();
    }

    private void mostrarResultado() {
        JPanel panelBotones = new JPanel();

        JButton continuarButton = new JButton("Continuar");
        continuarButton.addActionListener(e -> {
            if (!victoria) {
                areaCombate.setText(areaCombate.getText() + "<br>¡Has sido derrotado! Fin del juego.");
                JOptionPane.showMessageDialog(
                    this,
                    "Game Over",
                    "Fin del Juego",
                    JOptionPane.INFORMATION_MESSAGE
                );
                if (controlador != null) {
                    System.exit(0); // Cerramos
                }
            } else {
                areaCombate.setText(areaCombate.getText() + "<br>¡Has ganado el combate! Felicitaciones.");

                if (controlador != null) {
                    controlador.mostrarVistaMapa();
                }
            }
            if (combateFinal) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><h2 style='color:green;'>¡Felicidades!</h2><p>Has completado el juego. Gracias por jugar.</p></html>",
                    "Juego Completado",
                    JOptionPane.INFORMATION_MESSAGE
                );
                System.exit(0); // Cerramos
            }
        });

        // Botón Volver al Hub
        JButton volverHubButton = new JButton("Volver al Hub");
        volverHubButton.addActionListener(e -> controlador.mostrarVistaHub());

        panelBotones.add(continuarButton);
        panelBotones.add(volverHubButton);
        add(panelBotones, BorderLayout.SOUTH);

        validate();
    }

    public static void mostrar(ControladorJuego controlador, String resultadoCombate, boolean victoria, boolean combateFinal) {
        controlador.cambiarVista(new VistaCombate(controlador, resultadoCombate, victoria, combateFinal));
    }
}