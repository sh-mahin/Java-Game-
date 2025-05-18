package game;

import java.awt.*;

public class Ball {
    private int x, y;
    private int diameter = 20;
    private int xSpeed = 3;
    private int ySpeed = -3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        if (x <= 0 || x + diameter >= 800) {
            xSpeed = -xSpeed;
        }

        if (y <= 0) {
            ySpeed = -ySpeed;
        }
    }

    public void reverseY() {
        ySpeed = -ySpeed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
}
