package Sistema;

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
    
    public ArrayList<Router> neighbors() {
        return new ArrayList();
    }
    
    public ArrayList<ArrayList<Router>> findPaths(Router router_a, Router router_b) {
        return new ArrayList();
    }
}
