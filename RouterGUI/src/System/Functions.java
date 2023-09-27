package System;

import java.util.ArrayList;
import java.util.Arrays;

public class Functions {
    private ListaRouters routers;
    private ListaRutas rutas;
    
    public Functions() {
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
            for (int j = 0; j < n; j++) ady[i][j] = -1;
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
    
    public int[][] findPaths(Router router_a, Router router_b) {
        int[][] ady = adyacencyMatrix();
        
        int first = router_a.getListId();
        int last = router_b.getListId();
        
        int n = routers.size();
        int[] distance = new int[n];
        int[] previous = new int[n];
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            previous[i] = -1;
            visited[i] = false;
        }
        
        distance[first] = 0;
        
        int[] queue = new int[n];
        int queue_size = 1;
        queue[0] = first;
    
        while (queue_size > 0) {
            int current_router = extractMin(distance, queue, queue_size);
            visited[current_router] = true;

            if (current_router == last) break;

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (!visited[neighbor] && ady[current_router][neighbor] != -1) {
                    int alt_distance = distance[current_router] + ady[current_router][neighbor];

                    if (alt_distance < distance[neighbor]) {
                        distance[neighbor] = alt_distance;
                        previous[neighbor] = current_router;
                        queue_size = insert(queue, queue_size, neighbor, distance);
                    }
                }
            }
        }
        
        int[][] paths = new int[n][n];
        int current_router = last;
        int[] path = new int[n];
        int path_length = 0;
        
        while (current_router != -1) {
            path[path_length++] = current_router;
            current_router = previous[current_router];
        }
        
        for (int i = 0; i < path_length; i++) {
            paths[i] = Arrays.copyOf(path, path_length);
        }

        return Arrays.copyOf(paths, path_length);
    }
    
    private int extractMin(int[] distance, int[] queue, int queue_size) {
        int min_router = queue[0];
        int min_distance = distance[min_router];

        for (int i = 1; i < queue_size; i++) {
            int router = queue[i];
            int dist = distance[router];
            if (dist < min_distance) {
                min_router = router;
                min_distance = dist;
            }
        }
        
        return min_router;
    }

    private int insert(int[] queue, int queue_size, int router, int[] distance) {
        int i = queue_size;
        
        while (i > 0 && distance[queue[i - 1]] > distance[router]) {
            queue[i] = queue[i - 1];
            i--;
        }
        
        queue[i] = router;
        
        return queue_size + 1;
    }
}
