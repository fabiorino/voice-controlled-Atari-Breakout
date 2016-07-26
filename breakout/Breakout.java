package breakout;

import java.awt.*;
import javax.swing.*;

public class Breakout {

    public final int WIDTH = 640;
    public final int HEIGHT = 480;

    public JFrame frame;
    public Panel panel;

    public int score = 0;

    public Breakout() {        
        frame = new JFrame("Breakout | score: 0");
        panel = new Panel(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.ball.start();
        panel.paddle.start();
    }

    public void score() {
        score++;
        frame.setTitle("Breakout | score: " + score);
    }

    public void checkWall() {
        if(panel.wall.isCompletelyBroken()) {
            restart();
        }
    }

    public void restart() {
        panel.restart_game.setVisible(true);
        
        panel.restart_game.setText("The game will be restarted in ... 3");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        panel.restart_game.setText("The game will be restarted in ... 2");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        panel.restart_game.setText("The game will be restarted in ... 1");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {}
        
        panel.restart_game.setVisible(false);

        panel.ball.x = WIDTH / 2 - panel.ball.DIAMETER / 2;
        panel.ball.y = HEIGHT - 50 - panel.ball.DIAMETER;

        panel.ball.going_up = true;
        panel.ball.going_right = false;

        panel.paddle.x = WIDTH / 2 - panel.paddle.WIDTH / 2;
        panel.paddle.direction = 's';

        for(Brick[] bricks_row : panel.wall.bricks) {
            for(Brick brick : bricks_row) {
                brick.destroyed = false;
            }
        }

        score = 0;
        frame.setTitle("Breakout | score: " + score);
    }
}
