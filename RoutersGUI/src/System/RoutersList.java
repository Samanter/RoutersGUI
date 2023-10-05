package System;

import java.util.ArrayList;

public class RoutersList {
    private ArrayList<Router> routers;
    
    public RoutersList() {
        routers = new ArrayList();
    }
    
    public int size() {
        return routers.size();
    }
    
    public ArrayList<Router> getList() {
        return routers;
    }
    
    public Router getRouter(int id) {
        return (Router) routers.stream().filter(router -> router.getId() == id).findFirst().orElse(null);
    }
    
    public void addRouter(Router router) {
        router.setListId(routers.size());
        routers.add(router);
    }
    
    public void removeRouter(int id) {
        Router aux = getRouter(id);
        int list_id = aux.getListId();
        routers.remove(getRouter(id));
        for (int i = list_id; i < routers.size(); i++) {
            routers.get(i).setListId(routers.get(i).getListId() - 1);
        }
    }
    
    public void editRouter(Router router) {
        Router aux = getRouter(router.getId());
        aux.setDatos(router.getNombre(), router.getModelo());
    }
}