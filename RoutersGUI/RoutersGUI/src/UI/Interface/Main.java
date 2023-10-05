package UI.Interface;

import Structures.PathInfo;
import System.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Main extends javax.swing.JFrame {
    private Functions functions;
    private Router selected_router = null;
    private final JPopupMenu popup_menu;
    private final PriorityQueue<Integer> ids;
    
    public Main() {
        initComponents();
        functions = new Functions();
        ids = new PriorityQueue();
        
        popup_menu = new JPopupMenu();
        JMenuItem edit = new JMenuItem("Editar");
        JMenuItem delete = new JMenuItem("Eliminar");
        
        edit.addActionListener((ActionEvent e) -> {
             if (selected_router != null) {
                 EditRouter frame = new EditRouter() {
                     @Override
                     public void close() {
                         Main.this.setEnabled(true);
                         dispose();
                     }
                     
                     @Override
                     public void updateData() {
                        selected_router.setDatos(getRouterName(), getModelName());
                     }
                 };
                 
                 setEnabled(false);
                 frame.initData(selected_router, functions.neighbors(selected_router), functions.getRouters());
                 frame.setVisible(true);
             }
        });

        delete.addActionListener((ActionEvent e) -> {
            if (selected_router != null) {
                deleteSelectedRouter();
            }
        });
        
        popup_menu.add(edit);
        popup_menu.add(delete);
        area.getMainArea().add(popup_menu);
        
        area.getMainArea().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selected_router != null) {
                    boolean clicked_outside = true;
                    for (Router router : functions.getRouters().getList()) {
                        if (router.getPanel().getBounds().contains(e.getPoint())) {
                            clicked_outside = false;
                            break;
                        }
                    }
                    
                    if (clicked_outside) {
                        selected_router.getPanel().setSelected(false);
                        selected_router = null;
                        repaint();
                    }
                }
            }
        });
        
        area.getButton().addActionListener((ActionEvent e) -> {
            addRouter();
        });
        
        menuBar1.getMenuItem("Mostrar ruta").addActionListener((ActionEvent e) -> {
            ChooseRouters frame = new ChooseRouters() {
                @Override
                public void close() {
                    Main.this.setEnabled(true);
                    dispose();
                }
            };

            setEnabled(false);
            frame.setVisible(true);
        });
        
        menuBar1.getMenuItem("Mostrar todo").addActionListener((ActionEvent e) -> {
            for (int i = 0; i < functions.getRouters().size(); i++) {
                functions.getRouters().getList().get(i).getPanel().setNameVisible(true);
            }
        });
        
        menuBar1.getMenuItem("Ocultar todo").addActionListener((ActionEvent e) -> {
            for (int i = 0; i < functions.getRouters().size(); i++) {
                functions.getRouters().getList().get(i).getPanel().setNameVisible(false);
            }
        });
        
        stuff();
    }
    
    public void addRouter() {
        if (functions.getRouters().size() >= 100) return;
        
        int id;
        if (ids.isEmpty()) id = functions.getRouters().size();
        else id = ids.poll();   
        
        Router router = new Router(id, "Router " + id, "2111");
        router.getPanel().setBounds(20, 20, 108, 91);
        
        router.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (selected_router != null) {
                    selected_router.getPanel().setSelected(false);
                }

                router.getPanel().setSelected(true);
                selected_router = router;
                
                if (SwingUtilities.isRightMouseButton(e)) {
                    showPopupMenu(router.getPanel().getX() + e.getX(), router.getPanel().getY() + e.getY(), router);
                } 
            }
        });
        
        functions.addRouter(router);
        area.getMainArea().add(router.getPanel());
        repaint();
    }
    
    public void selectRouter(Router router) {
        if (selected_router != null) {
            selected_router.getPanel().setSelected(false);
        }
        
        selected_router = router;
        
        if (selected_router != null) {
            selected_router.getPanel().setSelected(true);
        }
        
        repaint();
    }
    
    public void deleteSelectedRouter() {
        if (selected_router != null) {
            functions.removeRouter(selected_router.getId());
            area.getMainArea().remove(selected_router.getPanel());
            ids.add((selected_router.getId()));
            selected_router = null;
            repaint();
        }
    }
    
     private void showPopupMenu(int x, int y, Router router) {
        selected_router = router;
        router.getPanel().setSelected(true);
        popup_menu.show(this, x, y);
    }
    
    public static void stuff() {
        Functions f = new Functions();
        
        Router router1 = new Router(1, "Router 1", "Model 1");
        Router router2 = new Router(2, "Router 2", "Model 2");
        Router router3 = new Router(3, "Router 3", "Model 3");
        Router router4 = new Router(4, "Router 4", "Model 4");
        Router router5 = new Router(5, "Router 5", "Model 5");
        Router router6 = new Router(6, "Router 6", "Model 6");
        Router router7 = new Router(7, "Router 7", "Model 7");
        Router router8 = new Router(8, "Router 8", "Model 8");
        Router router9 = new Router(9, "Router 9", "Model 9");
        Router router10 = new Router(10, "Router 10", "Model 10");
        
        f.addRouter(router1);
        f.addRouter(router2);
        f.addRouter(router3);
        f.addRouter(router4);
        f.addRouter(router5);
        f.addRouter(router6);
        f.addRouter(router7);
        f.addRouter(router8);
        f.addRouter(router9);
        f.addRouter(router10);

        f.addRuta(new Route("Route1", router1, router2, "192.168.1.1", "255.255.255.0", "192.168.2.1", "255.255.255.0", 10, 1, 2, "eth0"));
        f.addRuta(new Route("Route2", router2, router3, "192.168.2.1", "255.255.255.0", "192.168.3.1", "255.255.255.0", 15, 2, 3, "eth1"));
        f.addRuta(new Route("Route3", router1, router4, "192.168.1.2", "255.255.255.0", "192.168.4.1", "255.255.255.0", 12, 1, 4, "eth2"));
        f.addRuta(new Route("Route4", router3, router5, "192.168.3.2", "255.255.255.0", "192.168.5.1", "255.255.255.0", 20, 3, 5, "eth3"));
        f.addRuta(new Route("Route5", router4, router6, "192.168.4.2", "255.255.255.0", "192.168.6.1", "255.255.255.0", 18, 4, 6, "eth4"));
        
        int[][] adjacencyMatrix = f.adyacencyMatrix();
        
        System.out.println("Adjacency Matrix:");
        
        for (int[] adjacencyMatrix1 : adjacencyMatrix) {
            for (int j = 0; j < adjacencyMatrix1.length; j++) {
                System.out.print(adjacencyMatrix1[j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\nNeighbors of Each Router:");
        
        for (Router router : f.getRouters().getList()) {
            System.out.print("Router " + router.getId() + " neighbors: ");
            
            ArrayList<Router> neighbors = f.neighbors(router);
            
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i).getNombre() + " ");
            }
            
            System.out.println();
        }
        
        Router source_router = f.getRouter(1);
        Router dest_router = f.getRouter(5);
        PathInfo path = f.findPaths(source_router, dest_router);
        
        System.out.printf("""
                          Dijkstra's Shortest Path:
                          Source: %s
                          Destination: %s
                          Cost: %d
                          
                          """, source_router.getId(), dest_router.getId(), path.getDistance());
        
        for (int i = 0; i < path.getPath().size(); i++) {
            System.out.print(" -> " + f.getRouters().getList().get(path.getPath().get(i)).getId());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new UI.Misc.SimpleBackground();
        area = new RoutersArea.Area();
        menuBar1 = new UI.Misc.MenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setJMenuBar(menuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoutersArea.Area area;
    private UI.Misc.SimpleBackground background;
    private UI.Misc.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}
