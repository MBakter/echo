package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgoundPanel extends JPanel {

    String path;

    public BackgoundPanel(String path) {
        this.path = path;
    }

    public void paintComponent(Graphics g) {
        ImageIcon image = new ImageIcon(path);
        
        g.drawImage(image.getImage(), 0, 0, 1920, 1080, null);
    }
}
