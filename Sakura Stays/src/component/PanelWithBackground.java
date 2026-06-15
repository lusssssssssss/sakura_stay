package component;

import javax.swing.*;
import java.awt.*;

public class PanelWithBackground extends JPanel {
    private Image backgroundImage;

    public PanelWithBackground(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}