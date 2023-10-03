package UI.Misc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        
        Menu file = new Menu("Archivo");
        Menu options = new Menu("Opciones");
        
        MenuItem new_file = new MenuItem("Nuevo");
        MenuItem open_file = new MenuItem("Abrir");
        MenuItem save_file = new MenuItem("Guardar");
        MenuItem findPaths = new MenuItem("Mostrar ruta");
        MenuItem show_all = new MenuItem("Mostrar todo");
        MenuItem hide_all = new MenuItem("Ocultar todo");
        MenuItem help = new MenuItem("Ayuda");
        MenuItem authors = new MenuItem("Autores");
        
        file.add(new_file);
        file.add(open_file);
        file.add(save_file);
        options.add(findPaths);
        options.add(show_all);
        options.add(hide_all);
        options.add(help);
        options.add(authors);
        
        add(file);
        add(options);
    }
    
     @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
