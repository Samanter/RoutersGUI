package System;

import java.io.Serializable;
import java.util.ArrayList;

public class RoutesList implements Serializable {
    private ArrayList<Route> rutas;
    
    public RoutesList() {
        rutas = new ArrayList();
    }
    
    public RoutesList(RoutesList rl) {
        rutas = (ArrayList<Route>) rl.getList().clone();
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
    
    public void removeRuta(Route ruta) {
        rutas.remove(ruta);
    }
    
    public void editRuta(Route ruta) {
        Route aux = getRuta(ruta.getId());
        aux.setDatos(ruta);
    }
    
    public void loadRuta(Route ruta) {
        getRuta(ruta.getId()).loadDatos(ruta);
    }
}
