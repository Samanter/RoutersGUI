package UI.Interfaces;

import Structures.Graph;
import Structures.PathInfo;
import System.Router;
import UI.Misc.ScrollBar;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShortestPaths extends javax.swing.JFrame {
    private Main main_frame;
    private Router start_router;
    private Router end_router;
    
    public ShortestPaths() {
        initComponents();
        
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
        jScrollPane3.getViewport().setBackground(new Color(242, 242, 242, 255));
        jScrollPane3.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        jScrollPane4.setVerticalScrollBar(new ScrollBar());
        jScrollPane4.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        jScrollPane4.getViewport().setBackground(new Color(242, 242, 242, 255));
        jScrollPane4.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        
        jScrollPane5.setVerticalScrollBar(new ScrollBar());
        jScrollPane5.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        jScrollPane5.getViewport().setBackground(new Color(242, 242, 242, 255));
        jScrollPane5.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p3);
        
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
    }
    
    public void setRouters(Router r1, Router r2) {
        start_router = r1;
        end_router = r2;
    }
    
    public void initData() {
         ArrayList<PathInfo> paths = main_frame.getFunctions().findThreeShortestPaths(start_router, end_router);
        
        String path1 = "";
        String path2 = "";
        String path3 = "";
        
        start_endLabel.setText("Caminos: " + start_router + " a " + end_router);
        
        for (int router_id : paths.get(0).getPath()) {
            path1 += main_frame.getFunctions().getRouter(router_id).getNombre() + " -> ";
        }
        
        path1 = path1.substring(0, path1.length() - 3);
        path1Label.setText(path1);
        
        if (paths.size() > 1) {
            for (int router_id : paths.get(1).getPath()) {
                path2 += main_frame.getFunctions().getRouter(router_id).getNombre() + " -> ";
            }
            path2 = path2.substring(0, path2.length() - 3);
            path2Label.setText(path2);
            
            if (paths.size() > 2) {
                for (int router_id : paths.get(2).getPath()) {
                    path3 += main_frame.getFunctions().getRouter(router_id).getNombre() + " -> ";
                }
            }
            
            path3 = path3.substring(0, path3.length() - 3);
            path3Label.setText(path3);
        }
        
        
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
        path2Label = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table2 = new UI.Table.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        table3 = new UI.Table.Table();
        path3Label = new javax.swing.JLabel();
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IP (selec.)", "Nombre", "IP (vecino)", "Ancho de Banda (ref.)", "Ancho de Banda (inter.)", "Interfaz", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table1);

        path2Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        path2Label.setForeground(new java.awt.Color(0, 0, 0));
        path2Label.setText("Camino 2");

        jScrollPane4.setOpaque(false);

        table2.setBackground(new java.awt.Color(255, 255, 255));
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IP (selec.)", "Nombre", "IP (vecino)", "Ancho de Banda (ref.)", "Ancho de Banda (inter.)", "Interfaz", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(table2);

        jScrollPane5.setOpaque(false);

        table3.setBackground(new java.awt.Color(255, 255, 255));
        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IP (selec.)", "Nombre", "IP (vecino)", "Ancho de Banda (ref.)", "Ancho de Banda (inter.)", "Interfaz", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(table3);

        path3Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        path3Label.setForeground(new java.awt.Color(0, 0, 0));
        path3Label.setText("Camino 3");

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
                        .addComponent(path2Label)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                            .addComponent(path1Label, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(path3Label, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 30, Short.MAX_VALUE))))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(path2Label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(path3Label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel path1Label;
    private javax.swing.JLabel path2Label;
    private javax.swing.JLabel path3Label;
    private UI.Misc.SimpleBackground simpleBackground1;
    private javax.swing.JLabel start_endLabel;
    private UI.Table.Table table1;
    private UI.Table.Table table2;
    private UI.Table.Table table3;
    // End of variables declaration//GEN-END:variables
}
