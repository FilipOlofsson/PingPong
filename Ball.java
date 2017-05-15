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
        this.velocity = new Velocity(1,1);
        this.radius = radius;
    }

    public Velocity getVelocity() {
        if(isTouchingPlayer1() || isTouchingPlayer2()) {
            return new Velocity(-1 * velocity.x, velocity.y);
        }
        if(isTouchingBottomSides() || isTouchingTopSides()) {
            return new Velocity(velocity.x, -1 * velocity.y);
        }
        if(isGoal())
            return new Velocity(0, 0);
        return this.velocity;
    }

    @Override
    public void run() {
        try {
            while(running) {
                Thread.sleep(pace);
                this.velocity.x *= 1.0001;
                this.velocity.y *= 1.0001;
                this.velocity = getVelocity();
                this.x += Math.round(velocity.x);
                this.y += Math.round(velocity.y);
                gui.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isTouchingPlayer1() {
        if(this.x < gui.player1.x + gui.player1.width && this.x > gui.player1.x && this.y > gui.player1.y && this.y < gui.player1.height + gui.player1.y) return true;
        return false;
    }
    public boolean isTouchingPlayer2() {
        if(this.x + radius*2 < gui.player2.x + gui.player2.width && this.x + radius*2 > gui.player2.x && this.y + radius*2 > gui.player2.y && this.y < gui.player2.height + gui.player2.y) return true;
        return false;
    }
    public boolean isTouchingTopSides() {
        if(this.y < 0) return true;
        return false;
    }
    public boolean isTouchingBottomSides() {
        if (this.y + radius*6 > gui.height) return true;
        return false;
    }
    public boolean isGoal() {
        if(this.x < 0 || this.x + radius*4 > gui.width) return true;
        return false;
    }
}
