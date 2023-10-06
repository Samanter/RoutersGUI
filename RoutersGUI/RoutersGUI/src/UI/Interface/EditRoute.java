package UI.Interface;

import System.Route;
import System.Router;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class EditRoute extends javax.swing.JFrame {
    private Route route;
    private Main main_frame;
    
    public EditRoute() {
        initComponents();
        setInvisible();
        route = new Route();
        
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
    
    public Route getRoute() {
        return route;
    }
    
    public void initRouters() {
        Router router = main_frame.getSelectedRouter();
        router_a.setText(router.getNombre());
        
        for (int i = 0; i < main_frame.getFunctions().getRouters().size(); i++) {
            boolean exists = false;
            
            for (int j = 0; j < main_frame.getFunctions().neighbors(router).size(); j++) {
                if (main_frame.getFunctions().getRouters().getRouter(i) == 
                        main_frame.getFunctions().getRouters().getRouter(j)) {
                    exists = true;
                    break;
                }
            }
            
            if (router == main_frame.getFunctions().getRouters().getRouter(i)) exists = true;
            
            if (!exists) router_b.addItem(main_frame.getFunctions().getRouters().getRouter(i));
        }
    }
    
    public void initData(String id) {
        Route init_route = main_frame.getFunctions().getRuta(id);
        
        router_a.setText(init_route.getRouter_a().getNombre());
        router_b.setSelectedItem(init_route.getRouter_b());
        ip_a.setText(init_route.getIp_a());
        ip_b.setText(init_route.getIp_b());
        mask_a.setText(init_route.getMask_a());
        mask_b.setText(init_route.getMask_b());
        interfaz.setSelectedItem(init_route.getInterfaz());
        b_interfaz.setText(init_route.getB_interfaz().toString());
        b_ref.setText(init_route.getB_referencia().toString());
        
        router_b.setEditable(false);
        b_ref.setEditable(false);
    }
    
    public void setRoute() {
        route.setRouter_a(main_frame.getSelectedRouter());
        route.setRouter_b((Router) router_b.getSelectedItem());
        route.setId(route.getRouter_a().getId() + "-" + route.getRouter_b().getId());
        route.setIp_a(ip_a.getText());
        route.setIp_b(ip_b.getText());
        route.setMask_a(mask_a.getText());
        route.setMask_b(mask_b.getText());
        route.setInterfaz((String) interfaz.getSelectedItem());
        route.setB_interfaz(Integer.valueOf(b_interfaz.getText()));
        route.setB_referencia(Integer.valueOf(b_ref.getText()));
        route.setCosto(route.getB_referencia() / route.getB_interfaz());
    }
    
    public Object[] setTable() {
        Object[] data = new Object[] { route.getId(), ip_a.getText(), route.getRouter_b().getNombre(),
        ip_b.getText(), b_ref.getText(), b_interfaz.getText(), route.getInterfaz(), route.getCosto(), "" };
        
        return data;
    }
    
    public void close() {
    }
    
    public void addRoute() {
    }
    
    public final void setInvisible() {
        invalid1.setVisible(false);
        invalid2.setVisible(false);
        invalid3.setVisible(false);
        invalid4.setVisible(false);
        invalid5.setVisible(false);
        invalid6.setVisible(false);
    }
    
    public boolean hasValidValues() {
        setInvisible();
        
        try {
            Integer.valueOf(b_ref.getText());
        }
        catch (NumberFormatException e) {
            invalid5.setVisible(true);
        }
        
        try {
            Integer.valueOf(b_interfaz.getText());
        }
        catch (NumberFormatException e) {
            invalid6.setVisible(true);
        }
        
        return !(invalid1.isVisible() || invalid2.isVisible() || invalid3.isVisible() ||
                invalid4.isVisible() || invalid5.isVisible() || invalid6.isVisible());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simpleBackground1 = new UI.Misc.SimpleBackground();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ip_a = new UI.Misc.HintedText();
        jLabel5 = new javax.swing.JLabel();
        mask_a = new UI.Misc.HintedText();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ip_b = new UI.Misc.HintedText();
        mask_b = new UI.Misc.HintedText();
        router_b = new UI.Misc.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        b_ref = new UI.Misc.HintedText();
        jLabel10 = new javax.swing.JLabel();
        b_interfaz = new UI.Misc.HintedText();
        customButton1 = new UI.Misc.CustomButton();
        customButton2 = new UI.Misc.CustomButton();
        router_a = new javax.swing.JLabel();
        invalid1 = new javax.swing.JLabel();
        invalid2 = new javax.swing.JLabel();
        invalid3 = new javax.swing.JLabel();
        invalid4 = new javax.swing.JLabel();
        invalid5 = new javax.swing.JLabel();
        invalid6 = new javax.swing.JLabel();
        interfaz = new UI.Misc.ComboBoxSuggestion();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBar(null);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ruta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Router seleccionado");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Router vecino");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("IP");

        ip_a.setHint("192.168.1.0");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Máscara");

        mask_a.setHint("255.255.255.0");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("IP");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Máscara");

        ip_b.setHint("192.168.1.0");

        mask_b.setHint("255.255.255.0");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Interfaz");

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ancho de banda (referencia)");

        b_ref.setHint("100000000");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ancho de banda (interfaz)");

        b_interfaz.setHint("100000000");

        customButton1.setForeground(new java.awt.Color(255, 255, 255));
        customButton1.setText("Cancelar");
        customButton1.setBorderColor(new java.awt.Color(204, 204, 204));
        customButton1.setColor(new java.awt.Color(51, 51, 51));
        customButton1.setColorClick(new java.awt.Color(153, 153, 153));
        customButton1.setColorOver(new java.awt.Color(102, 102, 102));
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

        customButton2.setForeground(new java.awt.Color(255, 255, 255));
        customButton2.setText("Guardar");
        customButton2.setBorderColor(new java.awt.Color(204, 204, 204));
        customButton2.setColor(new java.awt.Color(51, 51, 51));
        customButton2.setColorClick(new java.awt.Color(153, 153, 153));
        customButton2.setColorOver(new java.awt.Color(102, 102, 102));
        customButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton2ActionPerformed(evt);
            }
        });

        router_a.setForeground(new java.awt.Color(0, 0, 0));
        router_a.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        router_a.setText("Router 0");

        invalid1.setForeground(new java.awt.Color(255, 0, 0));
        invalid1.setText("INVÁLIDO");

        invalid2.setForeground(new java.awt.Color(255, 0, 0));
        invalid2.setText("INVÁLIDO");

        invalid3.setForeground(new java.awt.Color(255, 0, 0));
        invalid3.setText("INVÁLIDO");

        invalid4.setForeground(new java.awt.Color(255, 0, 0));
        invalid4.setText("INVÁLIDO");

        invalid5.setForeground(new java.awt.Color(255, 0, 0));
        invalid5.setText("INVÁLIDO");

        invalid6.setForeground(new java.awt.Color(255, 0, 0));
        invalid6.setText("INVÁLIDO");

        interfaz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FastEthernet", "Gigabit", "10Gigabit", "100Gigabit", "Serial" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(invalid6)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b_interfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9))
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5))
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel6))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 2, Short.MAX_VALUE)
                                            .addComponent(jLabel7)
                                            .addGap(186, 186, 186)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(interfaz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(router_a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(router_b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mask_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ip_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ip_a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(mask_a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(b_ref, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(invalid1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(invalid2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(invalid3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(invalid4, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(invalid5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGap(11, 11, 11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(customButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(router_a))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ip_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(invalid1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mask_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(invalid2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(router_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ip_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalid3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(mask_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalid4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(interfaz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(b_ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalid5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(b_interfaz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalid6)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout simpleBackground1Layout = new javax.swing.GroupLayout(simpleBackground1);
        simpleBackground1.setLayout(simpleBackground1Layout);
        simpleBackground1Layout.setHorizontalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );
        simpleBackground1Layout.setVerticalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simpleBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simpleBackground1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        close();
    }//GEN-LAST:event_customButton1ActionPerformed

    private void customButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton2ActionPerformed
        if (hasValidValues()) {
            setRoute();
            addRoute();
            close();
        }
    }//GEN-LAST:event_customButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(EditRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new EditRoute().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Misc.HintedText b_interfaz;
    private UI.Misc.HintedText b_ref;
    private UI.Misc.CustomButton customButton1;
    private UI.Misc.CustomButton customButton2;
    private UI.Misc.ComboBoxSuggestion interfaz;
    private javax.swing.JLabel invalid1;
    private javax.swing.JLabel invalid2;
    private javax.swing.JLabel invalid3;
    private javax.swing.JLabel invalid4;
    private javax.swing.JLabel invalid5;
    private javax.swing.JLabel invalid6;
    private UI.Misc.HintedText ip_a;
    private UI.Misc.HintedText ip_b;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Misc.HintedText mask_a;
    private UI.Misc.HintedText mask_b;
    private javax.swing.JLabel router_a;
    private UI.Misc.ComboBoxSuggestion router_b;
    private UI.Misc.SimpleBackground simpleBackground1;
    // End of variables declaration//GEN-END:variables
}
