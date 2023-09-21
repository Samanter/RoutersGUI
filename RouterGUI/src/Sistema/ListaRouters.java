package Sistema;

import java.util.ArrayList;

public class ListaRouters {
    private ArrayList<Router> routers;
    
    public ListaRouters() {
        routers = new ArrayList();
    }
    
    public Router getRouter(String id) {
        return (Router) routers.stream().filter(router -> router.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void addRouter(Router router) {
        routers.add(router);
    }
    
    public void removeRouter(String id) {
        routers.remove(getRouter(id));
    }
    
    public void editRouter(Router router) {
        Router aux = getRouter(router.getId());
        aux.setDatos(router);
    }
}
