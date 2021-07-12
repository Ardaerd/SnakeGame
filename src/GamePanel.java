import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private GameModel gameModel;
    public static int width = main.SCREEN_WIDTH/main.UNIT_SIZE;
    public static int height = main.SCREEN_HEIGHT/main.UNIT_SIZE;
    private ImageIcon redCircle;
    private JLabel labels[][];

    public GamePanel() {
        // Initializing the components
        gameModel = new GameModel();
        redCircle = new ImageIcon("src/Pic/icons8-filled-circle-24.png");
        labels = new JLabel[width][height];

        // Setting layout
        setLayout(new GridLayout(width,height));

        // Setting label for each cell
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                JLabel label = new JLabel();
                label.setBackground(Color.BLACK);
                label.setOpaque(true);
                labels[i][j] = label;
                add(label,i,j);
            }
        }

        draw();

    }

    public void draw() {
        if (gameModel.isRunning()) {
            JLabel apple = labels[gameModel.getAppleX()][gameModel.getAppleY()];
            apple.setIcon(redCircle);
        }
    }

}
