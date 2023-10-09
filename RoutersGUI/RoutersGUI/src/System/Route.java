package System;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.Serializable;
import javax.swing.JLabel;

public class Route implements Serializable {
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
    private Line2D connection;
    private JLabel label;
    private boolean selected;
    
    public Route() {
        costo = 0;
        interfaz = "";
        connection = new Line2D.Double(new Point(), new Point());
        label = new JLabel("New Route");
        label.setForeground(Color.BLACK);
        label.setBounds(0, 0, 200, 16);
        selected = false;
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
        label.setText("Costo: " + costo + " / " + interfaz);
    }
    
    public void calcCosto() {
        if (b_referencia < b_interfaz) costo = 1;
        else costo = b_referencia / b_interfaz;
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
        label.setText("Costo: " + costo + " / " + interfaz);
    }
    
   public Line2D getConnection() {
        return connection;
    }
    
    public void setConnection(Point p1, Point p2) {
        connection = new Line2D.Double(p1, p2);
    }
    
    public JLabel getLabel() {
        return label;
    }
    
    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public void setLabelText(String text) {
        label.setText(text);
    }
    
    public boolean getSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void setDatos(Route ruta) {
        this.ip_a = ruta.getIp_a();
        this.ip_b = ruta.getIp_b();
        this.mask_a = ruta.getMask_a();
        this.mask_b = ruta.getMask_b();
        this.costo = ruta.getCosto();
        this.b_interfaz = ruta.getB_interfaz();
        this.interfaz = ruta.getInterfaz();
        
        label.setText("Costo: " + costo + " / " + interfaz);
    }
    
    public void loadDatos(Route ruta) {
        this.id = ruta.getId();
        this.router_a = ruta.getRouter_a();
        this.router_b = ruta.getRouter_b();
        this.ip_a = ruta.getIp_a();
        this.ip_b = ruta.getIp_b();
        this.mask_a = ruta.getMask_a();
        this.mask_b = ruta.getMask_b();
        this.costo = ruta.getCosto();
        this.b_referencia = ruta.getB_referencia();
        this.b_interfaz = ruta.getB_interfaz();
        this.interfaz = ruta.getInterfaz();
        this.label = ruta.getLabel();
        this.selected = ruta.getSelected();
    }
}
