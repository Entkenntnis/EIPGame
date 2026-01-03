package de.arrrg.EIPGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class GameLoop implements KeyListener {
    final int WIDTH = 300;
    final int HEIGHT = 300;
    final Color BG_COLOR = Color.BLACK;
    final int FPS = 60;
    final int PLAYER_SIZE = 5;
    final int PLAYER_SPEED = 1;
    final float HUE_SHIFT = 0.003f;
    final float PLAYER_SATURATION = 0.5f;
    
    Level lvl;

    float playerX;
    float playerY;
    boolean moveUp;
    boolean moveLeft;
    boolean moveDown;
    boolean moveRight;
    float hue;

    public GameLoop() {
        lvl = LevelData.getFirstLevel();
        resetLevel();
    }
    
    public void tick() {
        // Farbe
        hue += HUE_SHIFT;
        if (hue > 1) {
            hue = 0;
        }
        
        try {
            tryMovePlayer();
            
            // Ziel
            if (lvl.getFinishBlocks().stream().anyMatch(r -> playerIntersects(r, 1))) {
                lvl = lvl.next();
                resetLevel();
            }
        } catch (BorderCollisionException e) {
            resetLevel();
        } catch (ObstacleCollisionException e) {
            resetLevel();
        }
    }

    public void draw(Graphics g) {
        // Hindernisse
        g.setColor(Color.RED);
        for (Level.Rectangle block : lvl.getObstacles()) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }
        
        // Ziel
        g.setColor(Color.GREEN);
        for (Level.Rectangle block : lvl.getFinishBlocks()) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        // Beschreibungen
        g.setColor(Color.WHITE);
        g.drawString(lvl.getDescription(), 10, HEIGHT - 10);
        if (lvl.getN() < 999) {
            g.drawString("Level " + lvl.getN(), 10, 20);
        }
        
        // Spieler
        g.setColor(Color.getHSBColor(hue, PLAYER_SATURATION, 1));
        g.fillRect(Math.round(playerX), Math.round(playerY), PLAYER_SIZE, PLAYER_SIZE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) moveUp = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) moveLeft = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) moveDown = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) moveRight = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        final int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) moveUp = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) moveLeft = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) moveDown = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) moveRight = false;
    } 
    
    @Override
    public void keyTyped(KeyEvent e) {}

    private void resetLevel() {
        playerX = lvl.getStartX();
        playerY = lvl.getStartY();
    }

    private void tryMovePlayer() throws BorderCollisionException, ObstacleCollisionException {
        // Bewegung
        float dx = 0;
        float dy = 0;
        if (moveLeft && !moveRight) dx = -1;
        if (!moveLeft && moveRight) dx = 1;
        if (moveUp && !moveDown) dy = -1;
        if (!moveUp && moveDown) dy = 1;
        if (dx != 0 && dy != 0) {
            dx *= 0.707;
            dy *= 0.707;
        }
        playerX += dx * PLAYER_SPEED;
        playerY += dy * PLAYER_SPEED;
        
        // Kollision mit Rand
        if (playerX < 0 ||
            playerY < 0 ||
            playerX + PLAYER_SIZE > WIDTH ||
            playerY + PLAYER_SIZE > HEIGHT) {
            throw new BorderCollisionException();
        }

        // Hindernisse
        if (lvl.getObstacles().stream().anyMatch(r -> playerIntersects(r, 0))) {
            throw new ObstacleCollisionException();
        }
    }

    private boolean playerIntersects(Level.Rectangle r, int pad) {
        if (playerX >= r.x + r.width - pad || playerX + PLAYER_SIZE <= r.x + pad) {
            return false;
        }
         if (playerY >= r.y + r.height - pad || playerY + PLAYER_SIZE <= r.y + pad) {
            return false;
        }
        return true;
    }

    
    @SuppressWarnings("serial")
    class BorderCollisionException extends Exception {
        public BorderCollisionException() {
            super("Spieler ist gegen den Rand gelaufen");
        }
    }

    @SuppressWarnings("serial")
    class ObstacleCollisionException extends Exception {
        public ObstacleCollisionException() {
            super("Spieler ist gegen ein Hindernis gelaufen");
        }
    }
}
