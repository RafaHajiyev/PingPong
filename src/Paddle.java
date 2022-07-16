import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;

    public Paddle(int x, int y, int paddleWidth, int paddleHeigth, int id) {
        super(x,y,paddleWidth,paddleHeigth);
        this.id = id;
    }

    public void keyPressed(KeyEvent pressedKey){
        switch (id){
            case 1:
                if (pressedKey.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                if (pressedKey.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (pressedKey.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                if (pressedKey.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent releasedKey) {
        switch (id) {
            case 1:
                if (releasedKey.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (releasedKey.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (releasedKey.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (releasedKey.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
        y = y+ yVelocity;
    }
    public void draw(Graphics graphicsL){
        if (id==1)
            graphicsL.setColor(Color.MAGENTA);
        else
            graphicsL.setColor(Color.RED);
        graphicsL.fillRect(x,y,width,height);


    }

}
