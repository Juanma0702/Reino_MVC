package modelo;

import java.util.List;

public class Combate {
    private Personaje heroe;
    private List<Criatura> criaturas;
    private boolean turnoHeroe;
    private boolean victoria;

    public Combate(Personaje heroe, List<Criatura> criaturas, boolean emboscada) {
        this.heroe = heroe;
        this.criaturas = criaturas;
        this.turnoHeroe = emboscada;
    }

    public String iniciarCombate(String nombreHeroe) {
        StringBuilder resultado = new StringBuilder();
        for (Criatura criatura : criaturas) {
            while (heroe.sigueVivo() && criatura.sigueVivo()) {
                if (turnoHeroe) {
                    resultado.append(ataqueHeroe(criatura, nombreHeroe)).append("<br>");
                } else {
                    resultado.append(ataqueCriatura(criatura, nombreHeroe)).append("<br>");
                }
                turnoHeroe = !turnoHeroe;
            }

            // Determinar el resultado del combate
            if (heroe.sigueVivo()) {
                resultado.append("<span style='font-weight:bold; font-size:18px; color:#4CAF50;'>" + nombreHeroe + "</span> ha vencido a <span style='color:#FF5722; font-weight:bold;'>" + criatura.getClass().getSimpleName() + "</span>!<br>");
                heroe.ganarExperiencia(criatura.getExperienciaOtorgada());
                victoria = true;
            } else {
                resultado.append("<span style='font-weight:bold; font-size:18px; color:#FF5722;'>" + nombreHeroe + "</span> ha sido derrotado por <span style='color:#FF5722; font-weight:bold;'>" + criatura.getClass().getSimpleName() + "</span>.<br>");
                victoria = false;
            }
        }
        
        // Agregar un mensaje final
        if (victoria) {
            resultado.append("<h2 style='color:#4CAF50;'>¡Has ganado el combate!</h2>");
        } else {
            resultado.append("<h2 style='color:#FF5722;'>¡Has sido derrotado!</h2>");
        }
        
        return "<html><body style='font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; color: #333;'>" + resultado.toString() + "</body></html>";
    }

    private String ataqueHeroe(Criatura criatura, String nombreHeroe) {
        int danio = heroe.hacerDanio(criatura);
        int d = criatura.recibirDanio(danio, heroe);
        return "<span style='color:#2196F3; font-weight:bold;'>" + nombreHeroe + "</span> ataca a <span style='color:#FF9800;'>" + criatura.getClass().getSimpleName() + "</span> causando <span style='font-weight:bold; color:#4CAF50;'>" + d + "</span> puntos de daño.";
    }

    private String ataqueCriatura(Criatura criatura, String nombreHeroe) {
        int danio = criatura.hacerDanio(heroe);
        int d = heroe.recibirDanio(danio, criatura);
        return "<span style='color:#FF9800;'>" + criatura.getClass().getSimpleName() + "</span> ataca a <span style='color:#2196F3; font-weight:bold;'>" + nombreHeroe + "</span> causando <span style='font-weight:bold; color:#FF5722;'>" + d + "</span> puntos de daño.";
    }

    public Boolean getVictoria() {
        return victoria;
    }
}