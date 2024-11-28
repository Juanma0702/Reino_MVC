package modelo;

public class Arquero extends Personaje {
    private float punteria;
    private float agilidad;

    public Arquero(String nombre) {
        super(nombre, 900, 900, 60,30, "Arquero");
        this.punteria = 90;  // Probabilidad inicial de acierto en ataque
        this.agilidad = 30;  // Probabilidad inicial de esquivar golpes
    }

    @Override
    public int recibirDanio(int danio, Criatura c) {
        double a = agilidad / 100;
        if (Math.random() > a) {
            if(danio != 0 && (danio-nivelDefensa) > 0){
                puntosVida -= (danio - nivelDefensa);
                return (int)(danio - nivelDefensa);
            }
        }
        return 0;
    }

    @Override
    public int hacerDanio(Criatura c) {
        if (c instanceof Dragon) {
            return nivelAtaque;
        }
        float p = punteria / 100;
        if (Math.random() < p){
            return nivelAtaque;
        }
        return 0;
    }

}
