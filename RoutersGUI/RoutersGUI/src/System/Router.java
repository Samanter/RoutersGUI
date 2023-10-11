package System;

import UI.Misc.RouterPanel;
import java.io.Serializable;

public class Router implements Serializable {
    private int id;
    private String nombre;
    private String modelo;
    private int list_id;
    private RouterPanel panel;
    
    public Router(int id, String nombre, String modelo) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        list_id = -1;
        panel = new RouterPanel(nombre);
    }
    
    public Router(Router router) {
        this.id = router.getId();
        this.nombre = router.getNombre();
        this.modelo = router.getModelo();
        this.list_id = router.getListId();
        this.panel = router.getPanel();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getListId() {
        return list_id;
    }
    
    public void setListId(int list_id) {
        this.list_id = list_id;
    }
    
    public RouterPanel getPanel() {
        return panel;
    }
    
    public void setPanel(RouterPanel panel) {
        this.panel = panel;
    }
    
    public void setDatos(String nombre, String modelo) {
        this.nombre = nombre;
        this.modelo = modelo;
        panel.setRouterName(nombre);
    }
    
    public void loadDatos(Router router) {
        this.id = router.getId();
        this.nombre = router.getNombre();
        this.modelo = router.getModelo();
        this.list_id = router.getListId();
        this.panel = router.getPanel();
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
