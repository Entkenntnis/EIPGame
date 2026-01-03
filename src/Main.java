import de.arrrg.EIPGame.GameWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Starte das Spiel auf dem UI-Thread
        SwingUtilities.invokeLater(new GameWindow());
    }
}