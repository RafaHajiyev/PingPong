import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {

    GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping pong game");
        this.setResizable(false);
        this.setBackground(Color.blue);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }


}








