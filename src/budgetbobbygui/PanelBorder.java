
package budgetbobbygui;

import java.awt.*;
import javax.swing.JLayeredPane;

public class PanelBorder extends JLayeredPane {
 
    public PanelBorder() {
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
    }
    
}
