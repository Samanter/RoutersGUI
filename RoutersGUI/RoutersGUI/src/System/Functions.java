package System;

import Structures.Graph;
import Structures.PathInfo;
import java.util.ArrayList;

public class Functions {
    private final RoutersList routers;
    private final RoutesList rutas;
    
    public Functions() {
        routers = new RoutersList();
        rutas = new RoutesList();
    }
    
    public RoutersList getRouters() {
        return routers;
    }
    
    public Router getRouter(int id) {
        return routers.getRouter(id);
    }
    
    public void addRouter(Router router) {
        routers.addRouter(router);
    }
    
    public void removeRouter(int id) {
        routers.removeRouter(id);
    }
    
    public void editRouter(Router router) {
        routers.editRouter(router);
    }
    
    public RoutesList getRutas() {
        return rutas;
    }
    
    public Route getRuta(String id) {
        return rutas.getRuta(id);
    }
    
    public void addRuta(Route ruta) {
        rutas.addRuta(ruta);
    }
    
    public void removeRuta(Route ruta) {
        rutas.removeRuta(ruta);
    }
    
    public void editRuta(Route ruta) {
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
            g.addEdge(
                    rutas.getList().get(i).getRouter_a().getListId(), 
                    rutas.getList().get(i).getRouter_b().getListId(), 
                    rutas.getList().get(i).getCosto()
            );
        }

        return g.shortestPath(router_a.getListId(), router_b.getListId());
    }
    
    public ArrayList<PathInfo> findThreeShortestPaths(Router router_a, Router router_b) {
        int n = routers.size();
        Graph g = new Graph(n);

        for (int i = 0; i < rutas.size(); i++) {
            g.addEdge(
                rutas.getList().get(i).getRouter_a().getListId(),
                rutas.getList().get(i).getRouter_b().getListId(),
                rutas.getList().get(i).getCosto()
            );
        }

        return g.findThreeShortestPaths(router_a.getListId(), router_b.getListId());
    }
}
