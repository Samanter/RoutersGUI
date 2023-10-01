package UI.Interface;

import Structures.PathInfo;
import System.*;
import java.util.ArrayList;

public class Main extends javax.swing.JFrame {
    public Main() {
        initComponents();
        stuff();
    }
    
    public static void stuff() {
        Funciones f = new Funciones();
        
        Router router1 = new Router("R1", "Router 1", "Model 1");
        Router router2 = new Router("R2", "Router 2", "Model 2");
        Router router3 = new Router("R3", "Router 3", "Model 3");
        Router router4 = new Router("R4", "Router 4", "Model 4");
        Router router5 = new Router("R5", "Router 5", "Model 5");
        Router router6 = new Router("R6", "Router 6", "Model 6");
        Router router7 = new Router("R7", "Router 7", "Model 7");
        Router router8 = new Router("R8", "Router 8", "Model 8");
        Router router9 = new Router("R9", "Router 9", "Model 9");
        Router router10 = new Router("R10", "Router 10", "Model 10");
        
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

        f.addRuta(new Ruta("Route1", router1, router2, "192.168.1.1", "255.255.255.0", "192.168.2.1", "255.255.255.0", 10, 1, 2, "eth0"));
        f.addRuta(new Ruta("Route2", router2, router3, "192.168.2.1", "255.255.255.0", "192.168.3.1", "255.255.255.0", 15, 2, 3, "eth1"));
        f.addRuta(new Ruta("Route3", router1, router4, "192.168.1.2", "255.255.255.0", "192.168.4.1", "255.255.255.0", 12, 1, 4, "eth2"));
        f.addRuta(new Ruta("Route4", router3, router5, "192.168.3.2", "255.255.255.0", "192.168.5.1", "255.255.255.0", 20, 3, 5, "eth3"));
        f.addRuta(new Ruta("Route5", router4, router6, "192.168.4.2", "255.255.255.0", "192.168.6.1", "255.255.255.0", 18, 4, 6, "eth4"));
        
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
        
        Router source_router = f.getRouter("R1");
        Router dest_router = f.getRouter("R5");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
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
    // End of variables declaration//GEN-END:variables
}
