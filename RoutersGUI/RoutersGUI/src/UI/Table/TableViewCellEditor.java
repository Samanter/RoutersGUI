package UI.Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableViewCellEditor extends DefaultCellEditor {
    private TableViewEvent event;

    public TableViewCellEditor(TableViewEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelView view = new PanelView();
        view.initEvent(event, row);
        view.setBackground(new Color(230, 230, 230, 255));
        return view;
    }
}
