package System;

public class Route {
    private String id;
    private Router router_a;
    private Router router_b;
    private String ip_a;
    private String mask_a;
    private String ip_b;
    private String mask_b;
    private Integer costo;
    private Integer b_referencia;
    private Integer b_interfaz;
    private String interfaz;
    
    public Route() {
    }
    
    public Route(String id, Router router_a, Router router_b, String ip_a, String mask_a, String ip_b, String mask_b, Integer costo, Integer b_referencia, Integer b_interfaz, String interfaz) {
        this.id = id;
        this.router_a = router_a;
        this.router_b = router_b;
        this.ip_a = ip_a;
        this.mask_a = mask_a;
        this.ip_b = ip_b;
        this.mask_b = mask_b;
        this.costo = costo;
        this.b_referencia = b_referencia;
        this.b_interfaz = b_interfaz;
        this.interfaz = interfaz;
    }      
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Router getRouter_a() {
        return router_a;
    }

    public void setRouter_a(Router router_a) {
        this.router_a = router_a;
    }

    public Router getRouter_b() {
        return router_b;
    }

    public void setRouter_b(Router router_b) {
        this.router_b = router_b;
    }

    public String getIp_a() {
        return ip_a;
    }

    public void setIp_a(String ip_a) {
        this.ip_a = ip_a;
    }

    public String getMask_a() {
        return mask_a;
    }

    public void setMask_a(String mask_a) {
        this.mask_a = mask_a;
    }

    public String getIp_b() {
        return ip_b;
    }

    public void setIp_b(String ip_b) {
        this.ip_b = ip_b;
    }

    public String getMask_b() {
        return mask_b;
    }

    public void setMask_b(String mask_b) {
        this.mask_b = mask_b;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getB_referencia() {
        return b_referencia;
    }

    public void setB_referencia(Integer b_referencia) {
        this.b_referencia = b_referencia;
    }

    public Integer getB_interfaz() {
        return b_interfaz;
    }

    public void setB_interfaz(Integer b_interfaz) {
        this.b_interfaz = b_interfaz;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(String interfaz) {
        this.interfaz = interfaz;
    }
    
    public void setDatos(Route ruta) {
        this.ip_a = ruta.getIp_a();
        this.ip_b = ruta.getIp_b();
        this.mask_a = ruta.getMask_a();
        this.mask_b = ruta.getMask_b();
        this.costo = ruta.getCosto();
        this.b_interfaz = ruta.getB_interfaz();
        this.interfaz = ruta.getInterfaz();
    }
}
