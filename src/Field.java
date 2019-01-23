import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Field extends JPanel implements ActionListener {
private final int SIZE = 400;
private final int POINT_SIZE = 16;
 private final int ALL_POINTS = 625;
 private Image point;
 private Image apple;
 private int appleX;
 private int appleY;
 private int [] x = new int [ALL_POINTS];
 private int [] y = new int [ALL_POINTS];
 private int points;
 private Timer timer;
 private  boolean left = false;
 private  boolean right = true;
 private  boolean up = false;
 private  boolean down = false;
 private boolean inPlay = true;

 public Field(){
     setBackground(Color.gray);
LoadingImage();
InitGame();
addKeyListener(new FieldKeyListener());
setFocusable(true);
    }
    public void InitGame(){
     points = 3;
     for(int i = 0; i < points;i++){
         x[i] = 48 - i*POINT_SIZE;
         y[i] = 48;
     }
     timer = new Timer(250,this);
     timer.start();
     appearApple();
    }
    public void appearApple(){
     appleX = new Random().nextInt(25)*POINT_SIZE;
     appleY = new Random().nextInt(25)*POINT_SIZE;
 }
    public void LoadingImage(){
     ImageIcon iconApple = new ImageIcon("apple.png");
     apple = iconApple.getImage();
     ImageIcon iconPoint = new ImageIcon("point.png");
     point = iconPoint.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inPlay){
            g.drawImage(apple,appleX,appleY,this);
            for(int i = 0; i < points; i++){
                g.drawImage(point,x[i],y[i],this);
            }
        }else{
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,165,SIZE/2);
        }
    }

    public void move(){
     for (int i = points; i > 0; i--)
     {
         x[i] = x[i-1];
         y[i] = y[i-1];
     }
     if(left){
         x[0] -= POINT_SIZE;
     }
     if(right){
            x[0] += POINT_SIZE;
        }
        if(up){
            y[0] -= POINT_SIZE;
        }
        if(down){
            y[0] += POINT_SIZE;
        }
    }
    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            points++;
            appearApple();
        }
    }

    public void checkCollisions(){
        for (int i = points; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inPlay = false;
            }
        }
            if(x[0]>SIZE){
                inPlay = false;
            }
            if(x[0]<0){
                inPlay = false;
            }
            if(y[0]>SIZE){
                inPlay = false;
            }
            if(y[0]<0){
                inPlay = false;
            }
        }
    public void actionPerformed (ActionEvent e){
     if(inPlay){
         checkApple();
         checkCollisions();
         move();
     }
     repaint();
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT &&! right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT &&! left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP &&! down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN &&! up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
