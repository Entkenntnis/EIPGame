package de.arrrg.EIPGame;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class GameScreen extends JPanel {
    GameLoop loop;
    
    public GameScreen(GameLoop loop) {
        setPreferredSize(new Dimension(loop.WIDTH, loop.HEIGHT));
        setBackground(loop.BG_COLOR);
        addKeyListener(loop);
        setFocusable(true);
        this.loop = loop;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        loop.draw(g);
    }
}
