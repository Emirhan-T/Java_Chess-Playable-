import javax.swing.*;
import java.awt.*;
import Pieces.GamePanel;
public class MyFrame extends JFrame {

    GamePanel panel = new GamePanel();
    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setTitle("Chess_Java");
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
