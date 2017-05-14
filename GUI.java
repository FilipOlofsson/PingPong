import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GUI extends JPanel {

    int width, height;

    JFrame frame;

    Ball ball;

    Thread thread;

    Racket player1;
    Racket player2;

    public GUI(int width, int height, String title) {
        this.width = width;
        this.height = height;
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setFocusable(true);

        ImputHandler imputHandler = new ImputHandler();

        frame.addKeyListener(imputHandler);

        player1 = new Racket(100, height/2 - 100);
        player2 = new Racket(width - 150, height/2 - 100);

        ball = new Ball(width/2 - 10, height/2 - 10, 10);
        thread = new Thread(ball);
        thread.start();

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(player1.x, player1.y, player1.width, player1.height);
        g.fillRect(player2.x, player2.y, player2.width, player2.height);
        g.fillOval(ball.x, ball.y, ball.radius*2, ball.radius*2);
    }
}
class ImputHandler extends Engine implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            gui.player1.y -= gui.player1.speed;
        }

        if (key == KeyEvent.VK_S) {
            gui.player1.y += gui.player1.speed;
        }

        if (key == KeyEvent.VK_UP) {
            gui.player2.y -= gui.player2.speed;
        }

        if (key == KeyEvent.VK_DOWN) {
            gui.player2.y += gui.player2.speed;
        }
        if (key == KeyEvent.VK_PLUS) {
            gui.ball.velocity = new Velocity(gui.ball.velocity.x + 1, gui.ball.velocity.y + 1);
        }
        if (key == KeyEvent.VK_MINUS) {
            gui.ball.velocity = new Velocity(gui.ball.velocity.x - 1, gui.ball.velocity.y - 1);
        }
        if (key == KeyEvent.VK_R) {
            gui.ball.running = false;
            gui.frame.dispose();
            Engine.main(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}