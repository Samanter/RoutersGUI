package UI.Interfaces;

import UI.Warnings.RouterWarning;
import Structures.PathInfo;
import System.*;
import UI.Misc.ScrollBar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Main extends javax.swing.JFrame {    
    private Functions functions;
    private Router selected_router = null;
    private final JPopupMenu popup_menu;
    private final PriorityQueue<Integer> ids;
    
    public Main() {
        initComponents();
        
        scrollPane.setVerticalScrollBar(new ScrollBar());
        scrollPane.getVerticalScrollBar().setBackground(new Color(220, 220, 220, 255));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255, 255));
        
        scrollPane.setHorizontalScrollBar(new ScrollBar());
        scrollPane.getHorizontalScrollBar().setBackground(new Color(220, 220, 220, 255));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255, 255));
        
        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255, 255));
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, p);
        
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
                    public void updateRouter() {
                        selected_router.setDatos(getRouterName(), getModelName());
                        setRouteConnections();
                    }
                     
                    @Override
                    public void showLabel(int index) {
                        mainArea.add(functions.getRuta(getTableRoute(index).getId()).getLabel());
                        mainArea.repaint();
                    }
                };
                 
                frame.setMainFrame(this);
                frame.initData();
                setEnabled(false);
                frame.setVisible(true);
            }
        });

        delete.addActionListener((ActionEvent e) -> {
            if (selected_router != null) {
                RouterWarning frame = new RouterWarning();
                
                frame.getEliminar().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    deleteSelectedRouter();
                    frame.dispose();
                });
                
                frame.getCancelar().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });
                
                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
        popup_menu.add(edit);
        popup_menu.add(delete);
        mainArea.add(popup_menu);
        
        mainArea.addMouseListener(new MouseAdapter() {
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
                        mainArea.repaint();
                    }
                }
            }
        });
        
        menuBar.getMenu("add_router").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                addRouter();
            }
        });
        
        menuBar.getMenu("find_paths").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChooseRouters frame = new ChooseRouters() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };

                frame.setMainFrame(Main.this);
                frame.initData();
                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
        menuBar.getMenu("show_all").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Router router : functions.getRouters().getList()) {
                    router.getPanel().setNameVisible(true);
                }

                for (Route route : functions.getRutas().getList()) {
                    route.getLabel().setVisible(true);
                }
            }
        });
         
        
        menuBar.getMenu("hide_all").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Router router : functions.getRouters().getList()) {
                    router.getPanel().setNameVisible(false);
                }

                for (Route route : functions.getRutas().getList()) {
                    route.getLabel().setVisible(false);
                }
            }
        });
        
        menuBar.getMenu("help").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Lista de rutas");
                for (Route route : functions.getRutas().getList()) {
                    System.out.println(route.getId());
                }
                System.out.println();
            }
        });
    }
    
    public Functions getFunctions() {
        return functions;
    }
    
    public Router getSelectedRouter() {
        return selected_router;
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

        router.getPanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setRouteConnections();
            }
        });
        
        functions.addRouter(router);
        mainArea.add(router.getPanel());
        mainArea.repaint();
    }
    
    public void selectRouter(Router router) {
        if (selected_router != null) {
            selected_router.getPanel().setSelected(false);
        }
        
        selected_router = router;
        
        if (selected_router != null) {
            selected_router.getPanel().setSelected(true);
        }
        
        mainArea.repaint();
    }
    
    public void deleteSelectedRouter() {
        ArrayList<Route> aux = new ArrayList();
        
        if (selected_router != null) {
            for (Route route : functions.getRutas().getList()) {
                if (route.getRouter_a() == selected_router || route.getRouter_b() == selected_router) {
                    mainArea.remove(route.getLabel());
                    setRouteConnections();
                    aux.add(route);
                }
            }
            
            for (Route route : aux) functions.removeRuta(route);
            
            functions.removeRouter(selected_router.getId());
            mainArea.remove(selected_router.getPanel());
            ids.add((selected_router.getId()));
            selected_router = null;
            
            mainArea.repaint();
        }
    }
    
    public void showPopupMenu(int x, int y, Router router) {
        selected_router = router;
        router.getPanel().setSelected(true);
        popup_menu.show(this, x, y);
    }
     
    public Point calculateRouterCenter(Router router) {
        int x = router.getPanel().getX() + router.getPanel().getWidth() / 2;
        int y = router.getPanel().getY() + router.getPanel().getHeight() / 2;
        return new Point(x, y);
    }
    
    public void setRouteConnections() {
        for (Route route : functions.getRutas().getList()) {
            Point p1 = calculateRouterCenter(route.getRouter_a());
            Point p2 = calculateRouterCenter(route.getRouter_b());
            
            route.setConnection(p1, p2);
            
            int label_x = (p1.x + p2.x) / 2;
            int label_y = (p1.y + p2.y) / 2;
            route.getLabel().setLocation(label_x, label_y);
        }
        
        mainArea.repaint();
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
        System.out.println();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        mainArea = new UI.Misc.MainArea() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.GRAY);

                for (Route route : functions.getRutas().getList()) {
                    g2.draw(route.getConnection());
                }
            }
        };
        menuBar = new UI.Misc.MenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        scrollPane.setBorder(null);
        scrollPane.setMinimumSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout mainAreaLayout = new javax.swing.GroupLayout(mainArea);
        mainArea.setLayout(mainAreaLayout);
        mainAreaLayout.setHorizontalGroup(
            mainAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );
        mainAreaLayout.setVerticalGroup(
            mainAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(mainArea);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
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
    private UI.Misc.MainArea mainArea;
    private UI.Misc.MenuBar menuBar;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
