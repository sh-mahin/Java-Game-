package game;

import javax.swing.*;
import java.awt.*;

public class Brick {
    private int x, y;
    private final int width = 100;
    private final int height = 40;
    private boolean destroyed = false;
    private Image brickImage;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        loadBrickImage();
    }

    private void loadBrickImage() {
        try {
            brickImage = new ImageIcon(getClass().getResource("/brick.png")).getImage();
        } catch (Exception e) {
            System.out.println("Brick image not found!");
        }
    }

    public void draw(Graphics g) {
        if (!destroyed) {
            g.drawImage(brickImage, x, y, width, height, null);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public boolean isDestroyed() { return destroyed; }
    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }
}
