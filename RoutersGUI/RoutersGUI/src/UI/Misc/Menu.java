package UI.Misc;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

public class Menu extends JMenu {
    private String id;
    
    public Menu(String id, ImageIcon icon) {
        super();
        setIcon(icon);
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
