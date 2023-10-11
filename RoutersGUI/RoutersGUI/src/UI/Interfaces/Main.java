package UI.Interfaces;

import UI.Warnings.RouterWarning;
import System.*;
import UI.Misc.LoadFunctionsData;
import UI.Misc.ScrollBar;
import UI.Warnings.SaveBeforeClosing;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Main extends javax.swing.JFrame {    
    private Functions functions;
    private JPopupMenu popup_menu;
    private String file_path;
    
    public Main() {
        initComponents();
        
        setTitle("OSPF Simulator");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveBeforeClosing frame = new SaveBeforeClosing() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };
        
                frame.getYes().addActionListener((ActionEvent e1) -> {
                    saveFilePath();
                    frame.dispose();
                    dispose();
                });
                
                frame.getNo().addActionListener((ActionEvent e1) -> {
                    frame.dispose();
                    dispose();
                });

                frame.getVolver().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });

                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
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
        file_path = null;
        
        popup_menu = new JPopupMenu();
        JMenuItem edit = new JMenuItem("Editar");
        JMenuItem delete = new JMenuItem("Eliminar");
        
        edit.addActionListener((ActionEvent e) -> {
            if (functions.getSelectedRouter() != null) {
                EditRouter frame = new EditRouter() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                     
                    @Override
                    public void updateRouter() {
                        functions.getSelectedRouter().setDatos(getRouterName(), getModelName());
                        setRouteConnections();
                    }
                     
                    @Override
                    public void showLabel(int index) {
                        mainArea.add(functions.getRuta(getTableRoute(index).getId()).getLabel());
                        mainArea.repaint();
                    }
                };
                 
                frame.setMainFrame(this);
                frame.setFunctions(functions);
                frame.initData();
                setEnabled(false);
                frame.setVisible(true);
            }
        });

        delete.addActionListener((ActionEvent e) -> {
            if (functions.getSelectedRouter() != null) {
                RouterWarning frame = new RouterWarning() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };
                
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
                if (functions.getSelectedRouter() != null) {
                    boolean clicked_outside = true;
                    for (Router router : functions.getRouters().getList()) {
                        if (router.getPanel().getBounds().contains(e.getPoint())) {
                            clicked_outside = false;
                            break;
                        }
                    }
                    
                    if (clicked_outside) {
                        functions.getSelectedRouter().getPanel().setSelected(false);
                        functions.setSelectedRouter(null);
                        mainArea.repaint();
                    }
                }
            }
        });
        
        menuBar.getMenu("new_file").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                SaveBeforeClosing frame = new SaveBeforeClosing() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };
        
                frame.getYes().addActionListener((ActionEvent e1) -> {
                    saveFilePath();
                    
                    functions = new Functions();
                    file_path = null;
                    mainArea.removeAll();
                    mainArea.add(popup_menu);
                    mainArea.repaint();
                    
                    setEnabled(true);
                    frame.dispose();
                });
                
                frame.getNo().addActionListener((ActionEvent e1) -> {
                    functions = new Functions();
                    file_path = null;
                    mainArea.removeAll();
                    mainArea.add(popup_menu);
                    mainArea.repaint();
                    
                    setEnabled(true);
                    frame.dispose();
                });

                frame.getVolver().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });

                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
        menuBar.getMenu("open_file").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                SaveBeforeClosing frame = new SaveBeforeClosing() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };
        
                frame.getYes().addActionListener((ActionEvent e1) -> {
                    saveFilePath();
                    
                    LoadFunctionsData loaded_functions_data = Functions.loadFromFile();
                
                    if (loaded_functions_data != null) {
                        Functions loaded_functions = loaded_functions_data.getFunctions();
                        file_path = loaded_functions_data.getFilePath();
                        functions = loaded_functions;
                        functions.setSelectedRouter(null);
                        mainArea.removeAll();
                        mainArea.add(popup_menu);
                        loadData();
                    }
                    
                    setEnabled(true);
                    frame.dispose();
                });
                
                frame.getNo().addActionListener((ActionEvent e1) -> {
                    LoadFunctionsData loaded_functions_data = Functions.loadFromFile();
                
                    if (loaded_functions_data != null) {
                        Functions loaded_functions = loaded_functions_data.getFunctions();
                        file_path = loaded_functions_data.getFilePath();
                        functions = loaded_functions;
                        functions.setSelectedRouter(null);
                        mainArea.removeAll();
                        mainArea.add(popup_menu);
                        loadData();
                    }
                    
                    setEnabled(true);
                    frame.dispose();
                });

                frame.getVolver().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });

                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
        menuBar.getMenu("save_file").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (file_path == null) {
                    file_path = functions.saveToFile();
                }
                else {
                    functions.saveToFile(file_path);
                }
            }
        });
        
        menuBar.getMenu("save_file_as").addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                file_path = functions.saveToFile();
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
                Help frame = new Help() {
                    @Override
                    public void close() {
                        Main.this.setEnabled(true);
                        dispose();
                    }
                };
                
                setEnabled(false);
                frame.setVisible(true);
            }
        });
    }
    
    public Functions getFunctions() {
        return functions;
    }
    
    public void saveFilePath() {
        if (file_path == null) {
            file_path = functions.saveToFile();
        }
        else {
            functions.saveToFile(file_path);
        }
    }
    
    public void loadData() {
        for (Router router : functions.getRouters().getList()) {
            loadRouter(router);
        }
        
        for (Route route : functions.getRutas().getList()) {
            loadRoute(route);
        }
        
        setRouteConnections();
    }
    
    public void loadRouter(Router router) {
        Router aux = new Router(router.getId(), router.getNombre(), router.getModelo());
        aux.setListId(router.getListId());
        aux.getPanel().setBounds(router.getPanel().getX(), router.getPanel().getY(), 108, 91);
        
        aux.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (functions.getSelectedRouter() != null) {
                    functions.getSelectedRouter().getPanel().setSelected(false);
                }

                aux.getPanel().setSelected(true);
                functions.setSelectedRouter(aux);
                
                if (SwingUtilities.isRightMouseButton(e)) {
                    showPopupMenu(aux.getPanel().getX() + e.getX(), aux.getPanel().getY() + e.getY(), aux);
                } 
            }
        });
        
        aux.getPanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setRouteConnections();
            }
        });
        
        functions.loadRouter(aux);
        mainArea.add(aux.getPanel());
        mainArea.repaint();
    }
    
    public void loadRoute(Route route) {
        Route aux = new Route();
        
        aux.setId(route.getId());
        aux.setRouter_a(functions.getRouter(route.getRouter_a().getId()));
        aux.setRouter_b(functions.getRouter(route.getRouter_b().getId()));
        aux.setIp_a(route.getIp_a());
        aux.setIp_b(route.getIp_b());
        aux.setMask_a(route.getMask_a());
        aux.setMask_b(route.getMask_b());
        aux.setCosto(route.getCosto());
        aux.setB_interfaz(route.getB_interfaz());
        aux.setB_referencia(route.getB_referencia());
        aux.setInterfaz(route.getInterfaz());
        aux.setLabelText(route.getLabel().getText());
        
        functions.loadRuta(aux);
        mainArea.add(aux.getLabel());
    }
    
    public void addRouter() {
        if (functions.getRouters().size() >= 100) return;
        
        int id;
        if (functions.getIds().isEmpty()) id = functions.getRouters().size();
        else id = functions.getIds().poll();   
        
        Router router = new Router(id, "Router " + id, "2111");
        router.getPanel().setBounds(20, 20, 108, 91);
        
        router.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (functions.getSelectedRouter() != null) {
                    functions.getSelectedRouter().getPanel().setSelected(false);
                }

                router.getPanel().setSelected(true);
                functions.setSelectedRouter(router);
                
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
        if (functions.getSelectedRouter() != null) {
            functions.getSelectedRouter().getPanel().setSelected(false);
        }
        
        functions.setSelectedRouter(router);
        
        if (functions.getSelectedRouter() != null) {
            functions.getSelectedRouter().getPanel().setSelected(true);
        }
        
        mainArea.repaint();
    }
    
    public void deleteSelectedRouter() {
        if (functions.getSelectedRouter() != null) {
            ArrayList<Route> aux = new ArrayList();
            
            for (Route route : functions.getRutas().getList()) {
                if (route.getRouter_a().getId() == functions.getSelectedRouter().getId() || 
                        route.getRouter_b().getId() == functions.getSelectedRouter().getId()) {
                    mainArea.remove(route.getLabel());
                    setRouteConnections();
                    aux.add(route);
                }
            }
            
            for (Route route : aux) functions.removeRuta(route);
            
            functions.removeRouter(functions.getSelectedRouter().getId());
            mainArea.remove(functions.getSelectedRouter().getPanel());
            functions.getIds().add((functions.getSelectedRouter().getId()));
            functions.setSelectedRouter(null);
            
            mainArea.repaint();
        }
    }
    
    public void showPopupMenu(int x, int y, Router router) {
        functions.setSelectedRouter(router);
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

                for (Route route : functions.getRutas().getList()) {
                    if (route.getSelected()) {
                        g2.setColor(new Color(40, 130, 0, 255));
                        g2.setStroke(new BasicStroke(2.0f));
                    }
                    else {
                        g2.setColor(Color.GRAY);
                        g2.setStroke(new BasicStroke(1.0f));
                    }

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
