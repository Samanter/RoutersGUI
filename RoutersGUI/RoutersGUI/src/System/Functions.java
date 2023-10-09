package System;

import Structures.Graph;
import Structures.PathInfo;
import UI.Misc.SerializablePriorityQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.JFileChooser;

public class Functions implements Serializable {
    private final RoutersList routers;
    private final RoutesList rutas;
    private final SerializablePriorityQueue ids;
    
    public Functions() {
        routers = new RoutersList();
        rutas = new RoutesList();
        ids = new SerializablePriorityQueue();
    }
    
    public Functions(Functions f) {
        routers = new RoutersList(f.getRouters());
        rutas = new RoutesList(f.getRutas());
        ids = new SerializablePriorityQueue();
    }
    
    public PriorityQueue<Integer> getIds() {
        return ids;
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
    
    public ArrayList<PathInfo> findPaths(Router router) {
        int n = routers.size();
        Graph g = new Graph(n);
        
        for (int i = 0; i < rutas.size(); i++) {
            g.addEdge(
                    rutas.getList().get(i).getRouter_a().getListId(), 
                    rutas.getList().get(i).getRouter_b().getListId(), 
                    rutas.getList().get(i).getCosto()
            );
        }
        
        return g.shortestPaths(router.getId());
    }
    
    public PathInfo findPath(Router router_a, Router router_b) {
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
    
    public void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                oos.writeObject(this);
                System.out.println("Functions saved successfully.");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Functions loadFromFile() {
        Functions functions = null;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                functions = (Functions) ois.readObject();
                System.out.println("Functions loaded successfully.");
            } 
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        return functions;
    }
    
    public void loadRouter(Router router) {
        routers.loadRouter(router);
    }
    
    public void loadRuta(Route ruta) {
        rutas.loadRuta(ruta);
    }
}
