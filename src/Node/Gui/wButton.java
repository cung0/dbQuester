package Node.src.Node.Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class wButton extends JButton {
    private Border border = BorderFactory.createLineBorder(new Color(102,102,102));
    public wButton(){
        setBorder(border);
        setForeground(new Color(0, 255,255));
        setBackground(new Color(0,0,0));
        setContentAreaFilled(false);
    }
}
