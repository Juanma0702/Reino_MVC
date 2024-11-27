package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {
    private String nombre;
    private List<Criatura> criaturas;
    private boolean esNeutral;
    private List<Ubicacion> caminosPosibles;
    private String mensajeEventoEspecial;
    private Objeto objeto;

    public Ubicacion(String nombre, boolean esNeutral) {
        this.nombre = nombre;
        this.esNeutral = esNeutral;
        this.caminosPosibles = new ArrayList<>();
        this.criaturas = new ArrayList<>();
        configurarUbicacion();
    }

    private void configurarUbicacion() {
        if (!this.esNeutral) {
            double tipoEnemigo = Math.random();
            if (tipoEnemigo >= 0 && tipoEnemigo <= 0.425) {
                Criatura cr = new Troll();
                this.criaturas.add(cr);
                for (int i = 0; i < 2; i++) {
                    if (Math.random() > 0.6) {
                        Criatura c = new Troll();
                        this.criaturas.add(c);
                    }
                }
            } else if (tipoEnemigo > 0.425 && tipoEnemigo <= 0.85) {
                Criatura cr = new Espectro();
                this.criaturas.add(cr);
                for (int i = 0; i < 3; i++) {
                    if (Math.random() > 0.35) {
                        Criatura c = new Espectro();
                        this.criaturas.add(c);
                    }
                }
            } else if (tipoEnemigo > 0.85 && tipoEnemigo <= 1) {
                Criatura cr = new Dragon();
                this.criaturas.add(cr);
                if (Math.random() > 0.75) {
                    Criatura c = new Dragon();
                    this.criaturas.add(c);
                }
            }
        }
        if (nombre.contains("Torre Espectral")) {
            for (int i = 0; i < 4; i++) {
                Criatura c = new Dragon();
                this.criaturas.add(c);
            }
            this.esNeutral = false;
        } else if (nombre.contains("Montaña Helada")) {
            Criatura dragon = new Dragon();
            this.objeto = new Objeto("Espada de fuego", "Aumenta el nivel de ataque del heroe en 20%", this, criaturas);
            Misiones.getInstancia(objeto);
            this.criaturas.add(dragon);
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has matado al dragon del norte!  \nYa puedes reclamar la Espada de fuego";
        } else if (nombre.contains("Pantano Oscuro")) {
            for (int i = 0; i < 5; i++) {
                Criatura espectro = new Espectro();
                this.criaturas.add(espectro);
            }
            this.objeto = new Objeto("Arco de luz", "Aumenta el nivel de ataque del heroe en 25%", this, criaturas);
            Misiones.getInstancia(objeto);
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has vencido a los espectros del pantano!  \nYa puedes reclamar el Arco de Luz";
        } else if (nombre.contains("Aldea de los Sirith")) {
            for (int i = 0; i < 3; i++) {
                Criatura troll = new Troll();
                this.criaturas.add(troll);
            }
            this.objeto = new Objeto("Escudo de titanio", "Aumenta la defensa en 30 puntos", this, criaturas);
            Misiones.getInstancia(objeto);
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has limpiado la aldea de trolls!  \nYa puedes reclamar el Escudo de titanio";
        } else if (nombre.contains("Bosque de los Susurros")) {
            Criatura espectro = new Espectro();
            this.objeto = new Objeto("Amuleto de proteccion", "Aumenta el nivel de defensa del heroe en 15%", this, criaturas);
            Misiones.getInstancia(objeto);
            this.criaturas.add(espectro);
            this.esNeutral = false; // Neutral porque solo hay un evento
            this.mensajeEventoEspecial = "¡Has encontrado el amuleto perdido! \nDecides quedartelo, reclamalo para equiparlo";
        }
    }

    public void agregarCamino(Ubicacion ubicacion) {
        caminosPosibles.add(ubicacion);
    }

    public List<Ubicacion> getCaminosPosibles() {
        return caminosPosibles;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public boolean esNeutral() {
        return esNeutral;
    }

    public String getMensajeEventoEspecial() {
        return mensajeEventoEspecial;
    }

    public void CombateRealizado() {
        if (this.objeto != null){
            this.objeto.encontrado();
        }
    }
}