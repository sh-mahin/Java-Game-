package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Brick Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // causes GamePanel's getPreferredSize to be used
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
