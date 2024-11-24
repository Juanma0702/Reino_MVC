package modelo;

public abstract class Criatura {
    protected int puntosVida;
    protected int nivelAtaque;
    protected int nivelDefensa;
    protected int experienciaOtorgada; // Consistentemente usamos esta variable

    public Criatura(int puntosVida, int nivelAtaque, int nivelDefensa, int experienciaOtorgada) {
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experienciaOtorgada = experienciaOtorgada; // Inicializar correctamente
    }

    public boolean sigueVivo() {
        return puntosVida > 0;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada; // Usamos esta variable en todo el código
    }
    
    // Sistema de combate
    public abstract int recibirDanio(int danio, Personaje p) ;

    public abstract int hacerDanio(Personaje p);
}
