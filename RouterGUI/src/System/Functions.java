package System;

import java.util.ArrayList;

public class Functions {
    private ListaRouters routers;
    private ListaRutas rutas;
    
    public Functions() {
        routers = new ListaRouters();
        rutas = new ListaRutas();
    }
    
    public void addRouter(Router router) {
        routers.addRouter(router);
    }
    
    public void removeRouter(String id) {
        routers.removeRouter(id);
    }
    
    public void editRouter(Router router) {
        routers.editRouter(router);
    }
    
    public void addRuta(Ruta ruta) {
        rutas.addRuta(ruta);
    }
    
    public void removeRuta(String id) {
        rutas.removeRuta(id);
    }
    
    public void editRuta(Ruta ruta) {
        rutas.editRuta(ruta);
    }
    
    public ArrayList<Router> neighbors(Router router) {
        ArrayList<Router> router_neighbors = new ArrayList();
        
        for (int i = 0; i < rutas.size(); i++) {
            if (rutas.getList().get(i).getRouter_a() == router) {
                router_neighbors.add(rutas.getList().get(i).getRouter_b());
            }
            else if (rutas.getList().get(i).getRouter_b() == router) {
                router_neighbors.add(rutas.getList().get(i).getRouter_a());
            }
        }
        
        return router_neighbors;
    }
    
    public ArrayList<ArrayList<Integer>> adyacencyMatrix() {
        int n = routers.size();
        ArrayList<ArrayList<Integer>> ady = new ArrayList(n);
        
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> aux = new ArrayList(n);
            ady.add(aux);
            for (int j = 0; j < n; j++) aux.add(-1);
        }
        
        for (int i = 0; i < rutas.size(); i++) {
            int a = rutas.getList().get(i).getRouter_a().getListId();
            int b = rutas.getList().get(i).getRouter_b().getListId();
            int costo = rutas.getList().get(i).getCosto();
            
            ady.get(a).set(costo, b);
            ady.get(b).set(costo, a);
        }
        
        return ady;
    }
    
    public ArrayList<ArrayList<Integer>> findPaths(Router router_a, Router router_b) {
        ArrayList<ArrayList<Integer>> ady = adyacencyMatrix();
        
        
        
        return new ArrayList();
    }
}
