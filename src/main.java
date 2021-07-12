import javax.swing.*;
import java.awt.*;

public class main {
    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH)/UNIT_SIZE;
    static final int DELAY = 100;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setContentPane(new GamePanel());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
