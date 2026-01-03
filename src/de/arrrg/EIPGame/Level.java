package de.arrrg.EIPGame;

import java.util.ArrayList;
import java.util.List;

// Level-Daten
abstract class Level {
    private int n;
    private String description;
    private int startX;
    private int startY;
    
    final List<Rectangle> finishBlocks = new ArrayList<>();
    final List<Rectangle> obstacles = new ArrayList<>();
    
    public Level(int n, String description, int startX, int startY) {
        this.n = n;
        this.description = description;
        this.startX = startX;
        this.startY = startY;
    }

    public int getN() {
        return n;
    }

    public String getDescription() {
        return description;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public List<Rectangle> getFinishBlocks() {
        return finishBlocks;
    }

    public List<Rectangle> getObstacles() {
        return obstacles;
    }

    public abstract Level next();

    // innere Klasse
    class Rectangle {
        int x;
        int y;
        int width;
        int height;
        
        public Rectangle(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            width = w;
            height = h;
        }
    }
}