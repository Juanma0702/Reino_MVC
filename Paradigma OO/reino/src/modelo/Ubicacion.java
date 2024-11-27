package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {
    private String nombre;
    private List<Criatura> criaturas;
    private boolean esNeutral;
    private List<Ubicacion> caminosPosibles;
    private String mensajeEventoEspecial;
    private boolean combateRealizado = false; // Variable para controlar si el combate ya se realizó.

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
            this.criaturas.add(dragon);
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has matado al dragon del norte!  \nYa puedes reclamar la Espada de fuego";
        } else if (nombre.contains("Pantano Oscuro")) {
            for (int i = 0; i < 5; i++) {
                Criatura espectro = new Espectro();
                this.criaturas.add(espectro);
            }
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has vencido a los espectros del pantano!  \nYa puedes reclamar el Arco de Luz";
        } else if (nombre.contains("Aldea de los Sirith")) {
            for (int i = 0; i < 3; i++) {
                Criatura troll = new Troll();
                this.criaturas.add(troll);
            }
            this.esNeutral = false;
            this.mensajeEventoEspecial = "¡Has limpiado la aldea de trolls!  \nYa puedes reclamar el Escudo de titanio";
        } else if (nombre.contains("Bosque de los Susurros")) {
            Criatura espectro = new Espectro();
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

    public boolean isCombateRealizado() {
        return combateRealizado;
    }

    public void setCombateRealizado(boolean combateRealizado) {
        this.combateRealizado = combateRealizado;
    }

    public void marcarMisionesReclamables() {
        if (nombre.contains("Montaña Helada")) {
            Misiones.getInstancia(null).getObjetos().stream()
                .filter(m -> m.getNombre().equals("Derrota al Dragón del Norte"))
                .forEach(m -> m.encontrado());
        } else if (nombre.contains("Pantano Oscuro")) {
            Misiones.getInstancia(null).getObjetos().stream()
                .filter(m -> m.getNombre().equals("Elimina a los Espectros del Pantano"))
                .forEach(m -> m.encontrado());
        } else if (nombre.contains("Aldea de los Sirith")) {
            Misiones.getInstancia(null).getObjetos().stream()
                .filter(m -> m.getNombre().equals("Limpia la Aldea de los Trolls"))
                .forEach(m -> m.encontrado());
        } else if (nombre.contains("Bosque de los Susurros")) {
            Misiones.getInstancia(null).getObjetos().stream()
                .filter(m -> m.getNombre().equals("Recupera el Amuleto Perdido"))
                .forEach(m -> m.encontrado());
        }
    }
}