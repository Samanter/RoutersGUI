package UI.Misc;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        
        Menu new_file = new Menu("new_file", new javax.swing.ImageIcon(getClass().getResource("/Icons/new_file.png")));
        Menu open_file = new Menu("open_file", new javax.swing.ImageIcon(getClass().getResource("/Icons/open_file.png")));
        Menu save_file = new Menu("save_file", new javax.swing.ImageIcon(getClass().getResource("/Icons/save_file.png")));
        Menu add_router = new Menu("add_router", new javax.swing.ImageIcon(getClass().getResource("/Icons/add_router.png")));
        Menu find_paths = new Menu("find_paths", new javax.swing.ImageIcon(getClass().getResource("/Icons/find_paths.png")));
        Menu show_all = new Menu("show_all", new javax.swing.ImageIcon(getClass().getResource("/Icons/show_all.png")));
        Menu hide_all = new Menu("hide_all", new javax.swing.ImageIcon(getClass().getResource("/Icons/hide_all.png")));
        Menu help = new Menu("help", new javax.swing.ImageIcon(getClass().getResource("/Icons/help.png")));
        
        JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
        separator1.setPreferredSize(new java.awt.Dimension(5, 20));
        
        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        separator2.setPreferredSize(new java.awt.Dimension(5, 20));
        
        add(new_file);
        add(open_file);
        add(save_file);
        add(separator1);
        add(add_router);
        add(find_paths);
        add(show_all);
        add(hide_all);
        add(separator2);
        add(help);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    public Menu getMenu(String id) {
        for (int i = 0; i < getMenuCount(); i++) {
            Menu menu = (Menu) getMenu(i);
            if (menu != null && menu.getId().equals(id)) return menu;
        }
        
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
