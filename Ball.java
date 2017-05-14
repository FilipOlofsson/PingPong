import java.util.Random;

public class Ball extends Engine implements Runnable {
    int radius;
    Velocity velocity;
    int x, y;
    Random rnd;

    boolean running = true;


    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        rnd = new Random();
        this.velocity = new Velocity(1 + rnd.nextInt(2), 1 + rnd.nextInt(2));
        this.radius = radius;
    }

    public Velocity getVelocity() {
        if(gui.player1.x + gui.player1.width == x && this.y > gui.player1.y && this.y < gui.player1.y + gui.player1.height) {
            return new Velocity(-1 * velocity.x, velocity.y);
        }
        if(gui.player2.x == x + (radius*2) && this.y > gui.player2.y && this.y < gui.player2.y + gui.player2.height) {
            return new Velocity(-1 * velocity.x, velocity.y);
        }
        if(this.y < 0 || this.y > gui.height - radius*6) {
            return new Velocity(velocity.x, -1 * velocity.y);
        }
        if(this.x < 0 || this.x + radius*4 > gui.width)
            return new Velocity(0, 0);
        return this.velocity;
    }

    @Override
    public void run() {
        try {
            while(running) {
                Thread.sleep(pace);
                this.velocity = getVelocity();
                this.x += velocity.x;
                this.y += velocity.y;
                gui.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
