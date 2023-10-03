package RoutersArea;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RouterPanel extends JPanel {
    private Point mouse_pos = null;
    private boolean selected = false;
    private final JLabel label;
    
    public RouterPanel(String text) {
        setOpaque(false);
        
        label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        add(label);
        label.setBounds(0, 100, 80, 20);
        
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
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth() - 4;
        int height = getWidth() - 4;
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        
        Stroke borderStroke = new BasicStroke(2.0f);
        g2.setStroke(borderStroke);
        
        if (selected) {
            g2.setColor(new Color(150, 150, 150, 255));
            label.setForeground(Color.RED);
        }
        else {
            g2.setColor(Color.LIGHT_GRAY);
            label.setForeground(Color.BLACK);
        }
        
        g.fillOval(x, y, width, height);
        
        g2.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public void print() {
        System.out.println(label.getText());
    }
}
