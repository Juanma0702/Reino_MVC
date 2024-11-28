package modelo;

public class Mago extends Personaje {

    public Mago(String nombre) {
        super(nombre, 500, 500, 70,30, "Mago");
    }

    @Override
    public int recibirDanio(int danio, Criatura c) {
        if (!(c instanceof Espectro)){
            if(danio != 0 && (danio-nivelDefensa) > 0){
                puntosVida -= (danio - nivelDefensa);
                return (int)(danio-nivelDefensa);
            }
        }
        return 0;
    }

    @Override
    public int hacerDanio(Criatura c) {
        return nivelAtaque;
    }
}
