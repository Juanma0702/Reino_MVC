package modelo;

public abstract class Criatura {
    protected int puntosVida;
    protected int nivelAtaque;
    protected int nivelDefensa;
    protected int experienciaOtorgada;

    public Criatura(int puntosVida, int nivelAtaque, int nivelDefensa, int experienciaOtorgada) {
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experienciaOtorgada = experienciaOtorgada; 
    }

    public boolean sigueVivo() {
        return puntosVida > 0;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada; 
    }
    
    
    public abstract int recibirDanio(int danio, Personaje p) ;

    public abstract int hacerDanio(Personaje p);
}
