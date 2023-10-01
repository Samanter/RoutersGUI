package System;

import Structures.Graph;
import Structures.PathInfo;
import java.util.ArrayList;

public class Funciones {
    private ListaRouters routers;
    private ListaRutas rutas;
    
    public Funciones() {
        routers = new ListaRouters();
        rutas = new ListaRutas();
    }
    
    public ListaRouters getRouters() {
        return routers;
    }
    
    public Router getRouter(String id) {
        return routers.getRouter(id);
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
    
    public int[][] adyacencyMatrix() {
        int n = routers.size();
        int[][] ady = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) ady[i][j] = 0;
        }
        
        for (int i = 0; i < rutas.size(); i++) {
            int a = rutas.getList().get(i).getRouter_a().getListId();
            int b = rutas.getList().get(i).getRouter_b().getListId();
            int costo = rutas.getList().get(i).getCosto();
            
            ady[a][b] = costo;
            ady[b][a] = costo;
        }
        
        return ady;
    }
    
    public PathInfo findPaths(Router router_a, Router router_b) {
        int n = routers.size();
        Graph g = new Graph(n);
        
        for (int i = 0; i < rutas.size(); i++) {
            g.addEdge(rutas.getList().get(i).getRouter_a().getListId(), 
                    rutas.getList().get(i).getRouter_b().getListId(), 
                    rutas.getList().get(i).getCosto());
        }

        return g.shortestPath(router_a.getListId(), router_b.getListId());
    }
}
