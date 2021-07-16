import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int[] x;
    private int[] y;

    public GameModel(GamePanel gamePanel) {
        x = new int[GamePanel.width];
        y = new int[GamePanel.height];

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

    public void snake() {

    }

    public void newApple() {
        boolean check = true;
        while (check) {
            appleX = random.nextInt((int) (main.SCREEN_WIDTH/main.UNIT_SIZE));
            appleY = random.nextInt((int) (main.SCREEN_HEIGHT/main.UNIT_SIZE));

            for (int i = 0; i < Grid.length; i++) {
                for (int j = 0; j < Grid[i].length; j++) {
                    if (Grid[i][j] == 1 && (i != appleX) && (j != appleY) && (Grid[i][j] != 2)) {
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
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];

            Grid[y[i]][x[i]] = 2;
        }

        Grid[y[bodyParts]][x[bodyParts]] = 0;

        switch (direction) {
            case 'R':
                x[0] = x[0] + 1;
                break;

            case 'L':
                x[0] = x[0] - 1;
                break;

            case 'D':
                y[0] = y[0] - 1;
                break;

            case 'U':
                y[0] = y[0] + 1;
                break;
        }
//        System.out.println(Arrays.toString(x));
//        System.out.println(Arrays.toString(y));
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
            System.out.println("true");
        }
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

    public int getX(int i) {
        return x[i];
    }

    public int getY(int i) {
        return y[i];
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
