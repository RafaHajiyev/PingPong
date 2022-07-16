import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed=2;

    public Ball(int x, int y, int width, int heigth) {
        super(x,y,width,heigth);
        random = new Random();
        int randomXdirection = random.nextInt(2);
        if (randomXdirection==0)
            randomXdirection--;
        setXDirection(randomXdirection*initialSpeed);
        int randomYdirection = random.nextInt(2);
        if (randomYdirection==0)
            randomYdirection--;
        setYDirection(randomYdirection*initialSpeed);

    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillOval(x,y,height,width);
    }

}



