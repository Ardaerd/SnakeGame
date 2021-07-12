import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private boolean running = false;
    private int bodyParts = 6;
    private int applesEaten = 0;
    private char direction = 'R';
    private Random random = new Random();
    // 0 for empty, 1 for apple, 2 for snake
    private int Grid[][] = new int[GamePanel.width][GamePanel.height];
    private int appleX;
    private int appleY;

    public GameModel() {
        for (int i = 0; i < Grid.length; i++) {
            for (int j = 0; j < Grid[i].length; j++) {
                Grid[i][j] = 0;
            }
        }

        startGame();
    }

    public void startGame() {
        running = true;
        newApple();
    }

    public void newApple() {
        boolean check = true;
        while (check) {
            appleX = random.nextInt((int) (main.SCREEN_WIDTH/main.UNIT_SIZE));
            appleY = random.nextInt((int) (main.SCREEN_HEIGHT/main.UNIT_SIZE));

            for (int i = 0; i < Grid.length; i++) {
                for (int j = 0; j < Grid[i].length; j++) {
                    if (Grid[i][j] == 1 && (i != appleX) && (j != appleY)) {
                        Grid[i][j] = 0;
                        check = false;
                    }
                }
            }

            System.out.println("(" + appleX + ", " + appleY + ")");
            Grid[appleX][appleY] = 1;
        }
    }

    public void move() {

    }

    public void checkApple() {

    }

    public void checkCollisions() {

    }

    public void gameOver() {

    }

    public boolean isRunning() {
        return running;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public int getApplesEaten() {
        return applesEaten;
    }

    public char getDirection() {
        return direction;
    }

    public Random getRandom() {
        return random;
    }

    public int[][] getGrid() {
        return Grid;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }
}
