package UI.Interfaces;

import Structures.PathInfo;
import System.Functions;
import System.Route;
import System.Router;
import UI.Misc.ScrollBar;
import UI.Warnings.NoRoutesFound;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShortestPaths extends javax.swing.JFrame {
    private Functions functions;
    private Router start_router;
    private Router end_router;
    private PathInfo path;
    private ArrayList<Route> path_routes;
    private Main main_frame;
    private boolean from_main;
    
    public ShortestPaths() {
        initComponents();
        
        path_routes = new ArrayList();
        from_main = false;
        
        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255, 255));
        
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(255, 255, 255, 255));
        
        JPanel p3 = new JPanel();
        p3.setBackground(new Color(255, 255, 255, 255));
        
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
        jScrollPane1.getVerticalScrollBar().setBackground(new Color(204, 204, 204, 255));
        jScrollPane1.getViewport().setBackground(new Color(204, 204, 204, 255));
        
        jScrollPane3.setVerticalScrollBar(new ScrollBar());
        jScrollPane3.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        jScrollPane3.getViewport().setBackground(new Color(255, 255, 255, 255));
        jScrollPane3.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }
    
    public void setMainFrame(Main main_frame) {
        this.main_frame = main_frame;
        from_main = true;
    }
    
    public void setFunctions(Functions functions) {
        this.functions = functions;
    }
    
    public void setRouters(Router r1, Router r2) {
        start_router = r1;
        end_router = r2;
    }
    
    public void addRoute(int pos, Route route) {
        if (from_main) {
            path_routes.add(route);
            main_frame.getFunctions().getRuta(route.getId()).setSelected(true);
        }
        
        String name1, name2, ip1, ip2;
        
        if (pos == 0) {
            name1 = route.getRouter_a().getNombre();
            name2 = route.getRouter_b().getNombre();
            ip1 = route.getIp_a();
            ip2 = route.getIp_b();
        }
        else {
            name1 = route.getRouter_b().getNombre();
            name2 = route.getRouter_a().getNombre();
            ip1 = route.getIp_b();
            ip2 = route.getIp_a();
        }
        
        Object[] data = new Object[] { name1, ip1, name2, ip2, route.getB_referencia(), 
            route.getB_interfaz(), route.getInterfaz(), route.getCosto() };
        
        table1.addRow(data);
    }
    
    public int initData() {
        path = functions.findPath(start_router, end_router);
        
        if (path.getDistance() == Integer.MAX_VALUE) {
            NoRoutesFound frame = new NoRoutesFound();
            frame.setVisible(true);
            close();
            
            return -1;
        }
        
        if (from_main) main_frame.setEnabled(true);
        
        start_endLabel.setText("Caminos: " + start_router + " a " + end_router);
        
        String path_str = path.getDistance() + ": ";
        
        for (int router_id : path.getPath()) {
            path_str += functions.getRouter(router_id).getNombre() + " -> ";
        }
        
        path_str = path_str.substring(0, path_str.length() - 3);
        path1Label.setText(path_str);
        
        for (int i = 0; i < path.getPath().size() - 1; i++) {
            Router r1 = functions.getRouter(path.getPath().get(i));
            Router r2 = functions.getRouter(path.getPath().get(i + 1));
            
            String test_id1 = r1.getId() + "-" + r2.getId();
            String test_id2 = r2.getId() + "-" + r1.getId();
            
            if (functions.getRuta(test_id1) != null) {
                addRoute(0, functions.getRuta(test_id1));
            }
            else if (functions.getRuta(test_id2) != null) {
                addRoute(1, functions.getRuta(test_id2));
            }
        }
        
        if (from_main) main_frame.repaint();
        
        return 0;
    }
    
    public void close() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simpleBackground1 = new UI.Misc.SimpleBackground();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        start_endLabel = new javax.swing.JLabel();
        path1Label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new UI.Table.Table();
        customButton1 = new UI.Misc.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        start_endLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        start_endLabel.setForeground(new java.awt.Color(0, 0, 0));
        start_endLabel.setText("Caminos");

        path1Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        path1Label.setForeground(new java.awt.Color(0, 0, 0));
        path1Label.setText("Camino 1");

        jScrollPane3.setOpaque(false);

        table1.setBackground(new java.awt.Color(255, 255, 255));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre (local)", "IP (local)", "Nombre (vecino)", "IP (vecino)", "Ancho de Banda (ref.)", "Ancho de Banda (inter.)", "Interfaz", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table1);

        customButton1.setForeground(new java.awt.Color(255, 255, 255));
        customButton1.setText("Volver");
        customButton1.setBorderColor(new java.awt.Color(204, 204, 204));
        customButton1.setColor(new java.awt.Color(51, 51, 51));
        customButton1.setColorClick(new java.awt.Color(153, 153, 153));
        customButton1.setColorOver(new java.awt.Color(102, 102, 102));
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(start_endLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(path1Label)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(start_endLabel)
                .addGap(18, 18, 18)
                .addComponent(path1Label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout simpleBackground1Layout = new javax.swing.GroupLayout(simpleBackground1);
        simpleBackground1.setLayout(simpleBackground1Layout);
        simpleBackground1Layout.setHorizontalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        simpleBackground1Layout.setVerticalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simpleBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simpleBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        if (from_main) {
            for (Route route : path_routes) {
                main_frame.getFunctions().getRuta(route.getId()).setSelected(false);
            }
            
            main_frame.repaint();
        }
        
        close();
    }//GEN-LAST:event_customButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ShortestPaths.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new ShortestPaths().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Misc.CustomButton customButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel path1Label;
    private UI.Misc.SimpleBackground simpleBackground1;
    private javax.swing.JLabel start_endLabel;
    private UI.Table.Table table1;
    // End of variables declaration//GEN-END:variables
}
