package RoutersArea;

import UI.Misc.ScrollBar;
import java.awt.Color;


public class Area extends javax.swing.JPanel {
    public Area() {
        initComponents();
        
        scrollPane.setVerticalScrollBar(new ScrollBar());
        scrollPane.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        scrollPane.getViewport().setBackground(new Color(242, 242, 242, 255));
        
        scrollPane.setHorizontalScrollBar(new ScrollBar());
        scrollPane.getHorizontalScrollBar().setBackground(new Color(242, 242, 242, 255));
        scrollPane.getViewport().setBackground(new Color(242, 242, 242, 255));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane = new javax.swing.JLayeredPane();
        scrollPane = new javax.swing.JScrollPane();
        mainArea = new RoutersArea.MainArea();
        addButton = new UI.Misc.CustomButton();

        javax.swing.GroupLayout mainAreaLayout = new javax.swing.GroupLayout(mainArea);
        mainArea.setLayout(mainAreaLayout);
        mainAreaLayout.setHorizontalGroup(
            mainAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        mainAreaLayout.setVerticalGroup(
            mainAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(mainArea);

        addButton.setBorder(null);
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/plus.png"))); // NOI18N
        addButton.setBorderColor(new java.awt.Color(255, 255, 255));
        addButton.setColor(new java.awt.Color(0, 153, 255));
        addButton.setColorClick(new java.awt.Color(0, 0, 153));
        addButton.setColorOver(new java.awt.Color(0, 102, 255));
        addButton.setRadius(40);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLayeredPane.setLayer(scrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.setLayer(addButton, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPaneLayout = new javax.swing.GroupLayout(jLayeredPane);
        jLayeredPane.setLayout(jLayeredPaneLayout);
        jLayeredPaneLayout.setHorizontalGroup(
            jLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
            .addGroup(jLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap(821, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );
        jLayeredPaneLayout.setVerticalGroup(
            jLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
            .addGroup(jLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap(548, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        mainArea.addRouter();
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Misc.CustomButton addButton;
    private javax.swing.JLayeredPane jLayeredPane;
    private RoutersArea.MainArea mainArea;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
