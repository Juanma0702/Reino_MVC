package modelo;

public class Dragon extends Criatura {

    public Dragon() {
        super(150, 55, 40, 50);
    }

    @Override
    public int recibirDanio(int danio, Personaje p) {
        if(danio != 0 && (danio-(nivelDefensa*1.15)) > 0 && (danio-nivelDefensa) > 0){
            puntosVida = (puntosVida - (danio - nivelDefensa));
            return (int)(danio-nivelDefensa);
        }
        return 0;
    }

    @Override
    public int hacerDanio(Personaje p) {
        if (!(p instanceof Guerrero)) {
            return nivelAtaque;
        }
        else {
            return (int)(nivelAtaque *1.30);
        }
    }
}
