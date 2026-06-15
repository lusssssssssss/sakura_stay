import javax.swing.*;
import java.awt.*;

public class BACKBUTTON extends JButton {
    BACKBUTTON(int x, int y) {
        this.setText("Back");
        this.setBackground(new Color(204, 51, 0));
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.setPreferredSize(new Dimension(100, 50));
        this.setBounds(x,y,100,30);
    }
}
