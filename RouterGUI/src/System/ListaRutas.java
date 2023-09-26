package System;

import java.util.ArrayList;

public class ListaRutas {
    private ArrayList<Ruta> rutas;
    
    public ListaRutas() {
        rutas = new ArrayList();
    }
    
    public int size() {
        return rutas.size();
    }
    
    public ArrayList<Ruta> getList() {
        return rutas;
    }
    
    public Ruta getRuta(String id) {
        return (Ruta) rutas.stream().filter(ruta -> ruta.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void addRuta(Ruta ruta) {
        rutas.add(ruta);
    }
    
    public void removeRuta(String id) {
        rutas.remove(getRuta(id));
    }
    
    public void editRuta(Ruta ruta) {
        Ruta aux = getRuta(ruta.getId());
        aux.setDatos(ruta);
    }
}
