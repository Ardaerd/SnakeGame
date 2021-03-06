import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private GameModel gameModel;
    public static int width = main.SCREEN_WIDTH/main.UNIT_SIZE;
    public static int height = main.SCREEN_HEIGHT/main.UNIT_SIZE;
    private ImageIcon redCircle;
    private JLabel labels[][];
    public static Timer timer;
    private JLabel apple;

    public GamePanel() {
        // Initializing the components
        gameModel = new GameModel();
        redCircle = new ImageIcon("src/Pic/icons8-filled-circle-24.png");
        labels = new JLabel[width][height];
        setFocusable(true);
        timer = new Timer(main.DELAY,this);
        addKeyListener(new MyKeyAdapter());

        if (gameModel.isRunning()) {
            timer.start();
        }

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
    }

    public void addApple() {
        if (gameModel.isRunning()) {
            apple = labels[gameModel.getAppleY()][gameModel.getAppleX()];
            apple.setIcon(redCircle);

            if (gameModel.checkApple()) {
                apple.setIcon(null);
                addApple();
                timer.setDelay(main.DELAY);
                main.DELAY -= 1;
                System.out.println(main.DELAY);
            }
        }
    }

    public void draw() {
        if (gameModel.isRunning()) {

            for (int i = 0; i < gameModel.getBodyParts(); i++) {
                if (i == 0) {
                    labels[gameModel.getY(i)][gameModel.getX(i)].setBackground(Color.GREEN);
                } else {
                    labels[gameModel.getY(i)][gameModel.getX(i)].setBackground(new Color(45,180,0));
                }

                if (i == gameModel.getBodyParts() - 1) {
                    labels[gameModel.getY(i)][gameModel.getX(i)].setBackground(Color.BLACK);
                }
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (gameModel.isRunning()) {
            draw();
            addApple();
            gameModel.move();
            gameModel.checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (gameModel.getDirection() != 'R' && gameModel.isRunning()) {
                        gameModel.addDirections('L');
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (gameModel.getDirection() != 'L' && gameModel.isRunning()) {
                        gameModel.addDirections('R');
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (gameModel.getDirection() != 'D' && gameModel.isRunning()) {
                        gameModel.addDirections('U');
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (gameModel.getDirection() != 'U' && gameModel.isRunning()) {
                        gameModel.addDirections('D');
                    }
                    break;

                case KeyEvent.VK_R:

                    break;

                case KeyEvent.VK_SPACE:
                    if (gameModel.isRunning()) {
                        timer.stop();
                        gameModel.setRunning(false);
                    } else {
                        timer.start();
                        gameModel.setRunning(true);
                    }
            }
        }
    }
}
