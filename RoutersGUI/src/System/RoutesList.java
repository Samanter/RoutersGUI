package System;

import java.util.ArrayList;

public class RoutesList {
    private ArrayList<Route> rutas;
    
    public RoutesList() {
        rutas = new ArrayList();
    }
    
    public int size() {
        return rutas.size();
    }
    
    public ArrayList<Route> getList() {
        return rutas;
    }
    
    public Route getRuta(String id) {
        return (Route) rutas.stream().filter(ruta -> ruta.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void addRuta(Route ruta) {
        rutas.add(ruta);
    }
    
    public void removeRuta(String id) {
        rutas.remove(getRuta(id));
    }
    
    public void editRuta(Route ruta) {
        Route aux = getRuta(ruta.getId());
        aux.setDatos(ruta);
    }
}
