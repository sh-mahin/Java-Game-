package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private BrickManager brickManager;
    private int score = 0;
    private int lives = 5;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private Image background;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(800, 600));

        paddle = new Paddle(325, 550);
        ball = new Ball(390, 530);
        brickManager = new BrickManager();
        loadBackground();

        timer = new Timer(10, this);
        timer.start();
    }

    private void loadBackground() {
        try {
            background = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Background image not found!");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        paddle.draw(g);
        ball.draw(g);
        brickManager.draw(g);

        drawScoreAndLives(g);

        if (gameOver) {
            drawGameOver(g);
        } else if (gameWon) {
            drawWin(g);
        }
    }

    private void drawScoreAndLives(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 20, 25);
        g.drawString("Lives: " + lives, 700, 25);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over!", 300, 300);
    }

    private void drawWin(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("You Win!", 320, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !gameWon) {
            ball.move();
            checkCollision();
        }
        repaint();
    }

    private void checkCollision() {
        if (new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter())
                .intersects(new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight()))) {
            ball.reverseY();
        }

        ArrayList<Brick> bricks = brickManager.getBricks();
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
                Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                if (ballRect.intersects(brickRect)) {
                    brick.setDestroyed(true);
                    ball.reverseY();
                    score += 10;
                    playHitSound();
                    break;
                }
            }
        }

        if (ball.getY() > 600) {
            lives--;
            if (lives <= 0) {
                gameOver = true;
            } else {
                resetBallAndPaddle();
            }
        }

        if (brickManager.allBricksDestroyed()) {
            gameWon = true;
        }
    }

    private void resetBallAndPaddle() {
        paddle = new Paddle(325, 550);
        ball = new Ball(390, 530);
    }

    private void playHitSound() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/hit.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.out.println("Hit sound error: " + e.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        paddle.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
