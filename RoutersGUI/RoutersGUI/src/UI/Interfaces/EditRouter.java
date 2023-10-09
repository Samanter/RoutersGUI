package UI.Interfaces;

import Structures.PathInfo;
import System.Functions;
import System.Route;
import System.Router;
import UI.Misc.ScrollBar;
import UI.Table.TableActionCellEditor;
import UI.Table.TableActionCellRender;
import UI.Table.TableActionEvent;
import UI.Table.TableViewCellEditor;
import UI.Table.TableViewCellRender;
import UI.Table.TableViewEvent;
import UI.Warnings.CancelWarning;
import UI.Warnings.RouteWarning;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EditRouter extends javax.swing.JFrame {
    private Main main_frame;
    private ArrayList<Route> table_routes;
    private ArrayList<Route> deleted_routes;
    private ArrayList<Boolean> is_new;
    private ArrayList<Integer> pos_list;
    private Functions temp_functions;
    
    public EditRouter() {
        initComponents();
        invalid1.setVisible(false);
        
        table_routes = new ArrayList();
        deleted_routes = new ArrayList();
        is_new = new ArrayList();
        pos_list = new ArrayList();
        
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
        jScrollPane1.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 255, 255));
        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255, 255));
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        jScrollPane2.setVerticalScrollBar(new ScrollBar());
        jScrollPane2.getVerticalScrollBar().setBackground(new Color(242, 242, 242, 255));
        jScrollPane2.getViewport().setBackground(new Color(255, 255, 255, 255));
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(255, 255, 255, 255));
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        
        jScrollPane4.setVerticalScrollBar(new ScrollBar());
        jScrollPane4.getVerticalScrollBar().setBackground(new Color(204, 204, 204, 255));
        jScrollPane4.getViewport().setBackground(new Color(204, 204, 204, 255));
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CancelWarning frame = new CancelWarning();
        
                frame.getEliminar().addActionListener((ActionEvent e1) -> {
                    frame.dispose();
                    close();
                });

                frame.getCancelar().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });

                setEnabled(false);
                frame.setVisible(true);
            }
        });
        
        TableActionEvent event_route = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                EditRoute frame = new EditRoute() {
                    @Override
                    public void close() {
                        EditRouter.this.setEnabled(true);
                        dispose();
                    }

                    @Override
                    public void addRoute() {
                        table1.updateRow(row, setTable());
                        Route aux = getRoute();
                        table_routes.set(row, aux);
                        
                        temp_functions.editRuta(getRoute());
                        routingTable(main_frame.getSelectedRouter());
                    }
                };

                frame.setMainFrame(main_frame);
                frame.initData(pos_list.get(row), table_routes.get(row));
                setEnabled(false);
                frame.setVisible(true);
            }
            
            @Override
            public void onDelete(int row) {
                RouteWarning frame = new RouteWarning();
                
                frame.getEliminar().addActionListener((ActionEvent e1) -> {
                    if (!is_new.get(row)) {
                        deleted_routes.add(table_routes.get(row));
                    }
                    
                    temp_functions.removeRuta(table_routes.get(row));
                    routingTable(main_frame.getSelectedRouter());
                    
                    table_routes.remove(row);
                    table1.removeRow(row);
                    
                    setEnabled(true);
                    frame.dispose();
                });
                
                frame.getCancelar().addActionListener((ActionEvent e1) -> {
                    setEnabled(true);
                    frame.dispose();
                });
                
                setEnabled(false);
                frame.setVisible(true);
            }
        };
        
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setPreferredWidth(0);
        table1.getColumnModel().getColumn(7).setPreferredWidth(30);
        table1.getColumnModel().getColumn(8).setPreferredWidth(20);
        
        table1.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event_route));
        
        TableViewEvent event_routingTable = (int row) -> {
            ShortestPaths frame = new ShortestPaths() {
                @Override
                public void close() {
                    EditRouter.this.setEnabled(true);
                    dispose();
                }
            };
            
            frame.setFunctions(temp_functions);
            frame.setRouters(main_frame.getSelectedRouter(), temp_functions.getRouter((int) table2.getValueAt(row, 0)));
            frame.initData();
            frame.setVisible(true);
            setEnabled(false);
        };
        
        table2.getColumnModel().getColumn(0).setMinWidth(0);
        table2.getColumnModel().getColumn(0).setMaxWidth(0);
        table2.getColumnModel().getColumn(6).setPreferredWidth(10);
        
        table2.getColumnModel().getColumn(6).setCellRenderer(new TableViewCellRender());
        table2.getColumnModel().getColumn(6).setCellEditor(new TableViewCellEditor(event_routingTable));
    }
    
    public void setMainFrame(Main main_frame) {
        this.main_frame = main_frame;
        temp_functions = new Functions(main_frame.getFunctions());
    }
    
    public String getRouterName() {
        return routerName.getText();
    }
    
    public UI.Table.Table getTable() {
        return table1;
    }
    
    public String getModelName() {
        return (String) modelName.getSelectedItem();
    }
    
    public int getId() {
        return Integer.parseInt(idLabel.getText());
    }
    
    public Route getTableRoute(int index) {
        return table_routes.get(index);
    }
    
    public void initData() {
        routerName.setText(main_frame.getSelectedRouter().getNombre());
        modelName.setSelectedItem(main_frame.getSelectedRouter().getModelo());
        idLabel.setText(((Integer) main_frame.getSelectedRouter().getId()).toString());
        
        for (Route route : main_frame.getFunctions().getRutas().getList()) {
            if (route.getRouter_a().getId() == main_frame.getSelectedRouter().getId()) {
                table1.addRow(routeObject(0, route));
            }
            else if (route.getRouter_b().getId() == main_frame.getSelectedRouter().getId())  {
                table1.addRow(routeObject(1, route));
            }
        }
        
        routingTable(main_frame.getSelectedRouter());
    }
    
    public void routingTable(Router router) {
        ArrayList<PathInfo> paths = temp_functions.findPaths(main_frame.getSelectedRouter());
        table2.clearTable();
        
        for (PathInfo path_info : paths) {
            if (path_info != null && path_info.getPath().size() > 1) {
                String d1, d2, d3;
                int d0, d4, d5;
                
                Router r1 = temp_functions.getRouter(path_info.getPath().get(path_info.getPath().size() - 2));
                Router r2 = temp_functions.getRouter(path_info.getPath().get(path_info.getPath().size() - 1));
                
                d0 = r2.getId();
                d1 = r2.getNombre();
                
                String aux1 = r1.getId() + "-" + r2.getId();
                String aux2 = r2.getId() + "-" + r1.getId();
                
                Route route = temp_functions.getRuta(aux1);
                
                if (route != null) {
                    d2 = route.getIp_b();
                    d3 = route.getMask_b();
                }
                else {
                    route = temp_functions.getRuta(aux2);
                    d2 = route.getIp_a();
                    d3 = route.getMask_a();
                }
                
                d4 = path_info.getPath().size() - 1;
                d5 = path_info.getDistance();

                Object[] data = new Object[] { d0, d1, d2, d3, d4, d5, "" };
                table2.addRow(data);
            }
        }
    }
    
    public Object[] routeObject(int pos, Route route) {
        Object[] data;
        
        if (pos == 0) {
            data = new Object[] { route.getId(), route.getIp_a(), route.getRouter_b().getNombre(),
            route.getIp_b(), route.getB_referencia(), route.getB_interfaz(), route.getInterfaz(), 
            route.getCosto(), "" };
        }
        else {
            data = new Object[] { route.getId(), route.getIp_b(), route.getRouter_a().getNombre(),
            route.getIp_a(), route.getB_referencia(), route.getB_interfaz(), route.getInterfaz(), 
            route.getCosto(), "" };
        }
        
        table_routes.add(route);
        pos_list.add(pos);
        is_new.add(false);
        
        return data;
    }
    
    public void close() {
    }
    
    public void updateRouter() {
    }
    
    public void showLabel(int index) {
    }

    public void updateRoutes() {
        for (Route route : deleted_routes) {
            main_frame.getFunctions().getRuta(route.getId()).setLabelText("");
            main_frame.getFunctions().removeRuta(route);
        }
        
        for (int i = 0; i < table_routes.size(); i++) {
            if (is_new.get(i)) {
                main_frame.getFunctions().addRuta(table_routes.get(i));
                showLabel(i);
            }
            else {
                main_frame.getFunctions().editRuta(table_routes.get(i));
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simpleBackground1 = new UI.Misc.SimpleBackground();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new UI.Table.Table();
        addButton = new UI.Misc.CustomButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new UI.Table.Table();
        jPanel2 = new javax.swing.JPanel();
        routerName = new UI.Misc.HintedText();
        modelName = new UI.Misc.ComboBoxSuggestion();
        idLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        customButton1 = new UI.Misc.CustomButton();
        customButton2 = new UI.Misc.CustomButton();
        invalid1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane4.setHorizontalScrollBar(null);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Router");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Vecinos");

        jScrollPane1.setOpaque(false);

        table1.setBackground(new java.awt.Color(255, 255, 255));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IP (local)", "Nombre", "IP (vecino)", "Ancho de Banda (ref.)", "Ancho de Banda (inter.)", "Interfaz", "Costo", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        addButton.setBorder(null);
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/plus2.png"))); // NOI18N
        addButton.setBorderColor(new java.awt.Color(204, 204, 204));
        addButton.setColor(new java.awt.Color(0, 153, 255));
        addButton.setColorClick(new java.awt.Color(0, 0, 153));
        addButton.setColorOver(new java.awt.Color(0, 102, 255));
        addButton.setRadius(40);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tabla de Enrutamiento");

        jScrollPane2.setOpaque(false);

        table2.setBackground(new java.awt.Color(255, 255, 255));
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Destino", "IP", "Máscara", "Saltos", "Métrica", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        routerName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        routerName.setHint("Nombre");

        modelName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        modelName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2111", "2560", "1941", "2901", "2911", "1841", "2811" }));

        idLabel.setForeground(new java.awt.Color(102, 102, 102));
        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idLabel.setText("1");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Modelo");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(routerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(routerName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        invalid1.setForeground(new java.awt.Color(255, 0, 0));
        invalid1.setText("No hay suficientes routers");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(customButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 840, Short.MAX_VALUE)
                                    .addComponent(invalid1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(30, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invalid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout simpleBackground1Layout = new javax.swing.GroupLayout(simpleBackground1);
        simpleBackground1.setLayout(simpleBackground1Layout);
        simpleBackground1Layout.setHorizontalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        simpleBackground1Layout.setVerticalGroup(
            simpleBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
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

    private void customButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton2ActionPerformed
        updateRoutes();
        updateRouter();
        close();
    }//GEN-LAST:event_customButton2ActionPerformed

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        CancelWarning frame = new CancelWarning();
        
        frame.getEliminar().addActionListener((ActionEvent e1) -> {
            frame.dispose();
            close();
        });

        frame.getCancelar().addActionListener((ActionEvent e1) -> {
            setEnabled(true);
            frame.dispose();
        });

        setEnabled(false);
        frame.setVisible(true);
    }//GEN-LAST:event_customButton1ActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        invalid1.setVisible(false);
        if (main_frame.getFunctions().getRouters().size() < 2) invalid1.setVisible(true);
        
        if (!invalid1.isVisible()) {
            EditRoute frame = new EditRoute() {
                @Override
                public void close() {
                    EditRouter.this.setEnabled(true);
                    dispose();
                }

                @Override
                public void addRoute() {
                    table1.addRow(setTable());
                    table_routes.add(getRoute());
                    is_new.add(true);
                    
                    temp_functions.addRuta(getRoute());
                    routingTable(main_frame.getSelectedRouter());
                }
            };

            frame.setMainFrame(main_frame);
            
            ArrayList<Router> table_neighbors = new ArrayList();
            
            for (Route route : table_routes) {
                if (route.getRouter_a() != main_frame.getSelectedRouter()) {
                    table_neighbors.add(route.getRouter_a());
                }
                else {
                    table_neighbors.add(route.getRouter_b());
                }
            }
            
            if (frame.initRouters(table_neighbors)) {
                setEnabled(false);
                frame.setVisible(true);
            }
            else {
                frame.dispose();
                invalid1.setVisible(true);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EditRouter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(() -> {
            new EditRouter().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Misc.CustomButton addButton;
    private UI.Misc.CustomButton customButton1;
    private UI.Misc.CustomButton customButton2;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel invalid1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private UI.Misc.ComboBoxSuggestion modelName;
    private UI.Misc.HintedText routerName;
    private UI.Misc.SimpleBackground simpleBackground1;
    private UI.Table.Table table1;
    private UI.Table.Table table2;
    // End of variables declaration//GEN-END:variables
}
