package modelo;

public class Troll extends Criatura {

    public Troll() {
        super(150, 40, 35, 30);
    }

    @Override
    public int recibirDanio(int danio, Personaje p) {
        if(danio != 0 && (danio-(nivelDefensa*1.15)) > 0 && (danio-nivelDefensa) > 0){
            if (p instanceof Mago) {
                puntosVida = (int) (puntosVida - (danio - (nivelDefensa*1.15)));
                return (int)(danio-(nivelDefensa*1.15));
            }
            puntosVida = (int) (puntosVida - (danio - nivelDefensa));
            return (danio-nivelDefensa);
        }
        return 0;
    }

    @Override
    public int hacerDanio(Personaje p) {
        return nivelAtaque;
    }
}
