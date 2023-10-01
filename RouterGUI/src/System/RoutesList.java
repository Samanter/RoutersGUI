package System;

import java.util.ArrayList;

public class RoutesList {
    private ArrayList<Routes> rutas;
    
    public RoutesList() {
        rutas = new ArrayList();
    }
    
    public int size() {
        return rutas.size();
    }
    
    public ArrayList<Routes> getList() {
        return rutas;
    }
    
    public Routes getRuta(String id) {
        return (Routes) rutas.stream().filter(ruta -> ruta.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void addRuta(Routes ruta) {
        rutas.add(ruta);
    }
    
    public void removeRuta(String id) {
        rutas.remove(getRuta(id));
    }
    
    public void editRuta(Routes ruta) {
        Routes aux = getRuta(ruta.getId());
        aux.setDatos(ruta);
    }
}
