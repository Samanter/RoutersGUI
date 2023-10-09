package UI.Misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RouterPanel extends JPanel implements Serializable {
    private Point mouse_pos = null;
    private boolean selected = false;
    private final JLabel name;
    private final JLabel img;
    private int x;
    private int y;
    
    public RouterPanel(String text) {
        setOpaque(false);
        
        x = 20;
        y = 20;
        
        img = new JLabel(new ImageIcon(getClass().getResource("/Icons/router.png")));
        img.setHorizontalAlignment(JLabel.CENTER);
        
        add(img);
        img.setBounds(6, 6, 96, 63);
        
        name = new JLabel(text);
        name.setForeground(Color.BLACK);
        name.setHorizontalAlignment(JLabel.CENTER);
        
        add(name);
        name.setBounds(6, 69, 96, 16);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouse_pos = e.getPoint();
                getParent().setComponentZOrder(RouterPanel.this, 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse_pos = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouse_pos != null) {
                    int x = getX() + e.getPoint().x - mouse_pos.x;
                    int y = getY() + e.getPoint().y - mouse_pos.y;
                    setLocation(x, y);
                    setPos(x, y);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (selected) {
            name.setForeground(Color.RED);
        }
        else {
            name.setForeground(Color.BLACK);
        }
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public void setRouterName(String name) {
        this.name.setText(name);
    }
    
    public void setNameVisible(boolean flag) {
        name.setVisible(flag);
    }
    
    public void loadPos() {
        setLocation(x, y);
    }
    
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getXPos() {
        return x;
    }
    
    public int getYPos() {
        return y;
    }
}
