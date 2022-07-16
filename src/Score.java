import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    public Score(int gameWidth, int gameHeight) {
        Score.GAME_HEIGHT = gameHeight;
        Score.GAME_WIDTH = gameWidth;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Consolas", Font.PLAIN, 60));
        graphics.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        graphics.drawString(player1 / 10 + String.valueOf(player1%10),(GAME_WIDTH/2)-85, 50);
        graphics.drawString(player2 / 10 + String.valueOf(player2%10),(GAME_WIDTH/2)+20, 50);
    }
}