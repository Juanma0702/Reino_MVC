package modelo;

public class Espectro extends Criatura {

    public Espectro() {
        super(100, 65, 25, 30); 
    }

    @Override
    public int recibirDanio(int danio, Personaje p) {
        if (danio != 0 && (danio-(nivelDefensa*1.15)) > 0 && (danio-nivelDefensa) > 0) {
            puntosVida = (puntosVida - (danio - nivelDefensa));
            return (int)(danio-nivelDefensa);
        }
        return 0;
    }

    @Override
    public int hacerDanio(Personaje p) {
        if (!(p instanceof Arquero)) {
            return nivelAtaque;
        }
        else {
            return (int)(nivelAtaque *1.20);
        }
    }
}
