package game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y;
    private final int width = 150;  // Bigger paddle
    private final int height = 15;
    private final int speed = 8;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (moveLeft && x > 0) {
            x -= speed;
        }
        if (moveRight && x + width < 800) {
            x += speed;
        }
    }

    public void draw(Graphics g) {
        move(); // move paddle before drawing
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
