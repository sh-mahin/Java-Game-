package game;

import java.awt.*;
import java.util.ArrayList;

public class BrickManager {
    private ArrayList<Brick> bricks;

    public BrickManager() {
        bricks = new ArrayList<>();
        initBricks();
    }

    private void initBricks() {
        int rows = 2; // Only 2 rows
        int cols = 5; // 5 bricks per row
        int spacing = 10;
        int brickWidth = 100;
        int brickHeight = 40;

        int startX = (800 - (cols * (brickWidth + spacing))) / 2;
        int startY = 60;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = startX + j * (brickWidth + spacing);
                int y = startY + i * (brickHeight + spacing);
                bricks.add(new Brick(x, y));
            }
        }
    }

    public void draw(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public boolean allBricksDestroyed() {
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                return false;
            }
        }
        return true;
    }
}
