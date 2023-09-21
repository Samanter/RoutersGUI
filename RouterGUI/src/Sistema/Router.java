package Sistema;

public class Router {
    private String id;
    private String nombre;
    private String modelo;
    
    public Router(String id, String nombre, String modelo) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
    }   
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    
    public void setDatos(Router router) {
        this.nombre = router.getNombre();
        this.modelo = router.getModelo();
    }
}
