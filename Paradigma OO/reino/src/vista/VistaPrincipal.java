package vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    private JPanel vistaActual;
    private static VistaPrincipal instancia;

    public static VistaPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new VistaPrincipal();
        }
        return instancia;
    }

    private VistaPrincipal(){
        super("Reino de Uadengard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);  
        this.setLayout(new BorderLayout()); 
    }

    public void setVista(JPanel nuevaVista) {
        if (vistaActual != null) {
            remove(vistaActual);
        }
        vistaActual = nuevaVista;
        add(vistaActual, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JPanel getVistaActual() {
        return vistaActual;
    }
}
