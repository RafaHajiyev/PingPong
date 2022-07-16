import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel  extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGTH = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    public GamePanel() {

        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();

    }
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT/2)-(BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2) - (PADDLE_HEIGTH/2), PADDLE_WIDTH, PADDLE_HEIGTH, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT/2) - (PADDLE_HEIGTH/2), PADDLE_WIDTH, PADDLE_HEIGTH,2);

    }
    public void paint(Graphics graphicsL){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        graphicsL.drawImage(image,0,0,this);

    }
    public void draw(Graphics graphicsL){
        paddle1.draw(graphicsL);
        paddle2.draw(graphicsL);
        ball.draw(graphicsL);
        score.draw(graphicsL);
    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        //Bounce balls of the top edges
        if (ball.y <=0){
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >=GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.x <=0){
            ball.setXDirection(-ball.xVelocity);
        }
        if (ball.x >=GAME_WIDTH-BALL_DIAMETER){
            ball.setXDirection(-ball.xVelocity);
        }

        // this bounces balls to the paddles

        // this is for the paddle1
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        // this is for the paddle2
        if (ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity >0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //this method stops the paddles from passing the edges of the frame
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGTH))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGTH;
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGTH))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGTH;


        //this will give points to players and creates new paddles as well a new ball
        if (ball.x<=0){
            score.player2++;
            newPaddles();
            newBall();
            // System.out.println("Player 2: "+ score.player2);
        }
        if (ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            //System.out.println("Player 1: "+score.player1);
        }

    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoseconds = 1000000000/amountOfTicks;
        double delta = 0;

        while (true){
            long now = System.nanoTime();
            delta+= (now - lastTime)/nanoseconds;
            lastTime = now;
            if (delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class ActionListener extends KeyAdapter {

        public void keyPressed(KeyEvent pressedKey){
            paddle1.keyPressed(pressedKey);
            paddle2.keyPressed(pressedKey);
        }

        public void keyReleased(KeyEvent releasedKey){
            paddle1.keyReleased(releasedKey);
            paddle2.keyReleased(releasedKey);
        }
    }
}
