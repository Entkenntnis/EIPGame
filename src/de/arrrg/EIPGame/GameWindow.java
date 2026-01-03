package de.arrrg.EIPGame;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWindow implements Runnable {

    GameLoop loop = new GameLoop();
    GameScreen screen = new GameScreen(loop);

    @Override
    public void run() {
        // Einstiegspunkt und Schaltzentrale
        JFrame frame = new JFrame("EIP Game");
        frame.add(screen);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000 / loop.FPS, (ActionEvent e) -> {
            loop.tick();
            screen.repaint();
        });
        timer.start();
    }
}
