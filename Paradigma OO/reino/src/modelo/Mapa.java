package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private Ubicacion ubicacionActual;
    private Ubicacion ubicacionFinal;
    private List<Ubicacion> ubicaciones;

    public Mapa() {
        ubicaciones = new ArrayList<>();
        configurarMapa(); 
        ubicacionActual = ubicaciones.get(0); 
    }

    private void configurarMapa() {
        // Creamos ubicaciones
        Ubicacion entradaReino = new Ubicacion("Entrada del Reino", true);
        Ubicacion caminoBosque = new Ubicacion("Camino del Bosque", false);
        Ubicacion bosqueEncantado = new Ubicacion("Bosque Encantado", false);
        Ubicacion colinaViento = new Ubicacion("Colina del Viento", false);
        Ubicacion lagoCristal = new Ubicacion("Lago de Cristal", false);
        Ubicacion cavernaOscura = new Ubicacion("Caverna Oscura (Hoguera)", true);
        Ubicacion puenteAntiguo = new Ubicacion("Puente Antiguo", false);
        Ubicacion llanuraRocosa = new Ubicacion("Llanura Rocosa", false);
        Ubicacion montanaHelada = new Ubicacion("Montaña Helada", true);
        Ubicacion pantanoNiebla = new Ubicacion("Pantano de la Niebla", false);
        Ubicacion puebloAbandonado = new Ubicacion("Pueblo Abandonado", false);
        Ubicacion torreVigilancia = new Ubicacion("Torre de Vigilancia (Hoguera)", true);
        Ubicacion bosqueSusurros = new Ubicacion("Bosque de los Susurros", true);
        Ubicacion ruinasAntiguas = new Ubicacion("Ruinas Antiguas", false);
        Ubicacion campoBatalla = new Ubicacion("Campo de Batalla", false);
        Ubicacion valleEcos = new Ubicacion("Valle de los Ecos", false);
        Ubicacion altarOscuro = new Ubicacion("Altar Oscuro", false);
        Ubicacion fosoProfundo = new Ubicacion("Foso Profundo", false);
        Ubicacion pantanoOscuro = new Ubicacion("Pantano Oscuro", true);
        Ubicacion tronoRey = new Ubicacion("Trono del Rey Olvidado (Hoguera)", true);
        Ubicacion bosqueProfundo = new Ubicacion("Bosque Profundo", false);
        Ubicacion aldeaSirith = new Ubicacion("Aldea de los Sirith", true);
        Ubicacion colinaBrumosa = new Ubicacion("Colina Brumosa", false);
        Ubicacion desiertoSombrio = new Ubicacion("Desierto Sombrío", false);
        Ubicacion cascadaSilenciosa = new Ubicacion("Cascada Silenciosa", false);
        Ubicacion torreEspectral = new Ubicacion("Torre Espectral", true);

        // Caminos lineales y bifurcaciones
        entradaReino.agregarCamino(caminoBosque);       // Lineal
        caminoBosque.agregarCamino(bosqueEncantado);    // Lineal
        
        bosqueEncantado.agregarCamino(colinaViento);  // Bifurcación izquierda
        bosqueEncantado.agregarCamino(lagoCristal);   // Bifurcación derecha

        colinaViento.agregarCamino(cavernaOscura);      // Unión
        lagoCristal.agregarCamino(cavernaOscura);       // Unión
        
        cavernaOscura.agregarCamino(puenteAntiguo);   // Bifurcación izquierda
        cavernaOscura.agregarCamino(llanuraRocosa);   // Bifurcación derecha
        
        puenteAntiguo.agregarCamino(montanaHelada);     // Unión
        llanuraRocosa.agregarCamino(montanaHelada);     // Unión  

        montanaHelada.agregarCamino(pantanoNiebla);     // Lineal

        pantanoNiebla.agregarCamino(puebloAbandonado);  // Lineal

        puebloAbandonado.agregarCamino(torreVigilancia);    // Lineal

        torreVigilancia.agregarCamino(bosqueSusurros);  // Bifurcación izquierda
        torreVigilancia.agregarCamino(ruinasAntiguas); // Bifurcación derecha

        bosqueSusurros.agregarCamino(campoBatalla);      // Unión
        ruinasAntiguas.agregarCamino(campoBatalla);     // Unión

        campoBatalla.agregarCamino(valleEcos);  // Bifurcación izquierda
        campoBatalla.agregarCamino(altarOscuro);  // Bifurcación derecha

        valleEcos.agregarCamino(fosoProfundo);  // Lineal
        altarOscuro.agregarCamino(pantanoOscuro); // Lineal

        fosoProfundo.agregarCamino(tronoRey); // Unión
        pantanoOscuro.agregarCamino(tronoRey); // Unión

        tronoRey.agregarCamino(bosqueProfundo); // Lineal

        bosqueProfundo.agregarCamino(aldeaSirith); // Lineal

        aldeaSirith.agregarCamino(colinaBrumosa); // Bifurcación izquierda
        aldeaSirith.agregarCamino(desiertoSombrio); // Bifurcación derecha

        colinaBrumosa.agregarCamino(cascadaSilenciosa); // Unión
        desiertoSombrio.agregarCamino(cascadaSilenciosa); // Unión

        cascadaSilenciosa.agregarCamino(torreEspectral); // Final

        // Agregar todas las ubicaciones a la lista
        ubicaciones.add(entradaReino);
        ubicaciones.add(caminoBosque);
        ubicaciones.add(bosqueEncantado);
        ubicaciones.add(colinaViento);
        ubicaciones.add(lagoCristal);
        ubicaciones.add(cavernaOscura);
        ubicaciones.add(puenteAntiguo);
        ubicaciones.add(llanuraRocosa);
        ubicaciones.add(montanaHelada);
        ubicaciones.add(pantanoNiebla);
        ubicaciones.add(puebloAbandonado);
        ubicaciones.add(torreVigilancia);
        ubicaciones.add(bosqueSusurros);
        ubicaciones.add(ruinasAntiguas);
        ubicaciones.add(campoBatalla);
        ubicaciones.add(valleEcos);
        ubicaciones.add(altarOscuro);
        ubicaciones.add(fosoProfundo);
        ubicaciones.add(pantanoOscuro);
        ubicaciones.add(tronoRey);
        ubicaciones.add(bosqueProfundo);
        ubicaciones.add(aldeaSirith);
        ubicaciones.add(colinaBrumosa);
        ubicaciones.add(desiertoSombrio);
        ubicaciones.add(cascadaSilenciosa);
        ubicaciones.add(torreEspectral);
        //Ubicacion actual y final
        ubicacionActual = entradaReino;
        ubicacionFinal = torreEspectral;
    }
    
    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public List<Ubicacion> getCaminosDisponibles() {
        return ubicacionActual.getCaminosPosibles();
    }

    public void avanzar(Ubicacion nuevaUbicacion) {
        ubicacionActual = nuevaUbicacion;
    }

    public Ubicacion getUbicacionActual() {
        return ubicacionActual;
    }
}