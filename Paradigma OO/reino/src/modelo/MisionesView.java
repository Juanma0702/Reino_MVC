package modelo;

import java.util.ArrayList;
import java.util.List;

public class MisionesView {
    private List<ObjetoView> objetos = new ArrayList<>();
    private static MisionesView instancia;

    private MisionesView() {
    }

    public static MisionesView getInstance() {
        if (instancia == null) {
            instancia = new MisionesView();
        }
        return instancia;
    }

    public void agregarObjeto(ObjetoView objeto) {
        if (objeto != null) {
            if (objetos.size() > 3) {
                objetos.clear();
            }
            objetos.add(objeto);
        }
        System.out.println("Misiones actuales: " + objetos.size());
    }

    public List<ObjetoView> getObjetos() {
        return objetos;
    }
}