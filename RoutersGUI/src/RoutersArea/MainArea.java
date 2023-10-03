package RoutersArea;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class MainArea extends javax.swing.JPanel {
    private ArrayList<RouterPanel> routers;
    private RouterPanel selected_router = null;
    private final JPopupMenu popup_menu;
    
    public MainArea() {
        initComponents();
        routers = new ArrayList();
        
        popup_menu = new JPopupMenu();
        JMenuItem edit = new JMenuItem("Editar");
        JMenuItem delete = new JMenuItem("Eliminar");
        
         edit.addActionListener((ActionEvent e) -> {
             if (selected_router != null) {
                 
             }
        });

        delete.addActionListener((ActionEvent e) -> {
            if (selected_router != null) {
                deleteSelectedRouter();
            }
        });
        
        popup_menu.add(edit);
        popup_menu.add(delete);
        add(popup_menu);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selected_router != null) {
                    boolean clicked_outside = true;
                    for (RouterPanel router : routers) {
                        if (router.getBounds().contains(e.getPoint())) {
                            clicked_outside = false;
                            break;
                        }
                    }
                    
                    if (clicked_outside) {
                        selected_router.setSelected(false);
                        selected_router = null;
                        repaint();
                    }
                }
            }
        });        
    }
    
    public void addRouter() {
        if (routers.size() >= 100) return;
        
        RouterPanel router_panel = new RouterPanel("Router " + routers.size());
        router_panel.setBounds(20, 20, 80, 120);
        
        router_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (selected_router != null) {
                    selected_router.setSelected(false);
                }

                router_panel.setSelected(true);
                selected_router = router_panel;
                
                if (SwingUtilities.isRightMouseButton(e)) {
                    router_panel.print();
                    showPopupMenu(router_panel.getX() + e.getX(), router_panel.getY() + e.getY(), router_panel);
                } 
            }
        });
        
        routers.add(router_panel);
        add(router_panel);
        repaint();
    }
    
    public void selectRouter(RouterPanel router) {
        if (selected_router != null) {
            selected_router.setSelected(false);
        }
        selected_router = router;
        if (selected_router != null) {
            selected_router.setSelected(true);
        }
        repaint();
    }
    
    public void deleteSelectedRouter() {
        if (selected_router != null) {
            routers.remove(selected_router);
            remove(selected_router);
            selected_router = null;
            repaint();
        }
    }
    
     private void showPopupMenu(int x, int y, RouterPanel router) {
        selected_router = router;
        router.setSelected(true);
        popup_menu.show(this, x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(3000, 3000));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
