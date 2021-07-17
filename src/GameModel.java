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

    public GameModel() {
        x = new int[main.GAME_UNITS];
        y = new int[main.GAME_UNITS];

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
                    if (Grid[i][j] == 1 && (i != appleX) && (j != appleY)) {
                        Grid[i][j] = 0;
                    }
                }
            }

            System.out.println("(" + appleX + ", " + appleY + ")");
            if ((Grid[appleY][appleX] != 2)) {
                Grid[appleY][appleX] = 1;
                check = false;
            } else {
                check = true;
            }
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

//        System.out.println("Apple cordinate: " + "(" + appleX + ", " + appleY + ")");
//        System.out.println("(" + x[0] + ", " + y[0] + ")");
        System.out.println(Arrays.toString(Grid[0]));
    }

    public boolean checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
            return true;
        }
        return false;
    }

    public void checkCollisions() {
        // Checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                System.out.println("(" + x[0] + ", " + y[0] + ")");
                System.out.println("(" + x[i] + ", " + y[i] + ")");
                running = false;
            }
        }

        // Check if head touches right border
        if (x[0] < 0) {
            running = false;
            System.out.println("right border");
        }

        // Check if head touches left border
        if (x[0] > GamePanel.width-1) {
            running = false;
        }

        // Check if head touches top border
        if (y[0] < 0) {
            running = false;
        }

        // Check if head touches bottom border
        if (y[0] > GamePanel.height-1) {
            running = false;
        }

        if (!running) {
            GamePanel.timer.stop();
        }
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
