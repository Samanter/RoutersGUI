package UI.Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableViewCellRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, row, column);
        setBorder(noFocusBorder);
        
        PanelView view = new PanelView();
        
        if (selected) {
            com.setBackground(new Color(230, 230, 230, 255));
            view.setBackground(new Color(230, 230, 230, 255));
            view.buttonBackground(new Color(230, 230, 230, 255));
        } 
        else {
            com.setBackground(Color.WHITE);
            view.setBackground(Color.WHITE);
            view.buttonBackground(Color.WHITE);
        }
        
        return view;
    }
}
