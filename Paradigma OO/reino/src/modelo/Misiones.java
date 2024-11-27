package modelo;

import java.util.ArrayList;
import java.util.List;

public class Misiones {
    private static Misiones instancia;
    private List<Objeto> objetos = new ArrayList<>();


    private Misiones(Objeto o) {
        if (o != null) {
            this.objetos.add(o);
        }
    }

    public MisionesView obtenerVista() {
        for (Objeto o : objetos) {
            MisionesView.getInstance().agregarObjeto(o.obtenerVista());
        }
        return MisionesView.getInstance();
    }    

    public static Misiones getInstancia(Objeto o) {
        if (instancia == null) {
            instancia = new Misiones(o);
        } else if (o != null) {
            if (instancia.objetos.size() > 3) {
                instancia.objetos.clear();
            }
            instancia.objetos.add(o);
        }
        System.out.println("Misiones actuales: " + instancia.objetos.size());
        return instancia;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }
}