import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballX = 120;
    private int ballY = 350;
    private int ballXDir = -1;
    private int ballYdir = -2;

    private MapGeneratorRishi map;

    public GamePlay (){
        map = new MapGeneratorRishi(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint (Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        //drawing map
        map.draw((Graphics2D)g);

        //border
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //scores
        g.setColor(Color.white);
        g.setFont(new Font ("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballX,ballY,20,20);

        if (totalBricks <= 0){
            play = false;
            ballXDir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font ("serif",Font.BOLD,30));
            g.drawString("YOU WIN, Score: "+score, 190, 300);

            g.setFont(new Font ("serif",Font.BOLD,20));
            g.drawString("Press Enter to restart", 230, 350);


        }

        if (ballY > 570) {
            play = false;
            ballXDir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font ("serif",Font.BOLD,30));
            g.drawString("Game Over, Score: "+score, 190, 300);

            g.setFont(new Font ("serif",Font.BOLD,20));
            g.drawString("Press Enter to restart", 230, 350);


        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play = true){
            if(new Rectangle(ballX, ballY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
                ballYdir = -ballYdir;
            }

            A: for (int i = 0; i<map.map.length; i++)
                for (int j = 0; j<map.map[0].length; j++)
                    if (map.map[i][j]>0){
                        int brickX = j*map.brickWidth+80;
                        int brickY = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle (ballX,ballY,20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;

                            if (ballX + 19 <= brickRect.x || ballX +1 >= brickRect.x + brickRect.width){
                                ballXDir = -ballXDir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }

                            break A;

                        }
                    }

            ballX+=ballXDir;
            ballY+=ballYdir;
            if (ballX < 0){
                ballXDir = -ballXDir;
            }
            if (ballY < 0){
                ballYdir = -ballYdir;
            }
            if (ballX > 670){
                ballXDir = -ballXDir;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (playerX >= 600) {
                playerX= 600;
            }
            else{
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (playerX <= 10) {
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballX = 120;
                ballY = 350;
                ballXDir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGeneratorRishi(3,7);

                repaint();
            }
        }
    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
