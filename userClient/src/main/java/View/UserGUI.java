package View;

import javax.swing.*;
import java.awt.*;

public class UserGUI extends JFrame{

    public UserGUI() throws HeadlessException {
        super("School board");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setVisible(true);
    }

}
