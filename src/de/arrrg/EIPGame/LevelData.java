package de.arrrg.EIPGame;

public class LevelData {
    static Level getFirstLevel() {
        return new Level1();
    }
}

class Level1 extends Level {
    public Level1() {
        super(1, "Laufe zum Ziel, nutze WASD oder Pfeiltasten", 90, 130);
        finishBlocks.add(new Rectangle(210, 120, 10, 22));
    }
    
    public Level next() {
        return new Level2();
    }
}

class Level2 extends Level {
    public Level2() {
        super(2, "Weiche Hindernissen aus", 90, 130);
        finishBlocks.add(new Rectangle(210, 120, 10, 22));
        obstacles.add(new Rectangle(155, 70, 20, 120));
    }
    
    public Level next() {
        return new Level3();
    }
}

class Level3 extends Level {
    public Level3() {
        super(3, "JAVA ist cool", 230, 180);
        finishBlocks.add(new Rectangle(60, 110, 15, 15));
        obstacles.add(new Rectangle(30, 80, 25, 125));
        obstacles.add(new Rectangle(55, 80, 50, 25));
        obstacles.add(new Rectangle(55, 130, 25, 25));
        obstacles.add(new Rectangle(55, 180, 50, 25));
        obstacles.add(new Rectangle(130, 80, 25, 125));
        obstacles.add(new Rectangle(180, 80, 25, 125));
        obstacles.add(new Rectangle(205, 80, 50, 25));
        obstacles.add(new Rectangle(205, 130, 50, 25));
        obstacles.add(new Rectangle(230, 105, 25, 25));
    }
    
    public Level next() {
        return new Level4();
    }
}

class Level4 extends Level {
    public Level4() {
        super(4, "Höher und höher, wir zählen immer weiter", 10, 180);
        finishBlocks.add(new Rectangle(270, 0, 30, 30));
        obstacles.add(new Rectangle(23, 80, 25, 25));
        obstacles.add(new Rectangle(25, 130, 25, 75));
        obstacles.add(new Rectangle(75, 130, 75, 25));
        obstacles.add(new Rectangle(100, 105, 25, 75));
        obstacles.add(new Rectangle(175, 130, 75, 25));
        obstacles.add(new Rectangle(200, 105, 25, 75));
        obstacles.add(new Rectangle(266, 192, 13, 13));
        obstacles.add(new Rectangle(275, 130, 13, 13));
        obstacles.add(new Rectangle(275, 155, 13, 50));
        obstacles.add(new Rectangle(112, 130, 2, 200));
        obstacles.add(new Rectangle(212, 0, 2, 130));
    }
    
    public Level next() {
        return new TheEnd();
    }
}

class TheEnd extends Level {
    public TheEnd() {
        super(999, "Gewonnen!", 148, 148);
        finishBlocks.add(new Rectangle(50, 50, 50, 50));
        finishBlocks.add(new Rectangle(200, 50, 50, 50));
        finishBlocks.add(new Rectangle(50, 150, 50, 50));
        finishBlocks.add(new Rectangle(200, 150, 50, 50));
        finishBlocks.add(new Rectangle(50, 200, 200, 50));
    }

    public Level next() {
        return this;
    }
}