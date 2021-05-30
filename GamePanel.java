import java.awt.*;
  import javax.swing.JPanel;

import java.awt.event.*;
//import javafx.scene.input.KeyEvent;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyEvent;

/**
 * 
 * game pannel is main board where all function define for game
 *
 * @author (karamjeet)
 * @version (a version number:2)
 */
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS  = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 200;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts =5;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        //create new instance of apple at the game start
        newApple();
        //make the snake running at the start of the apple
        running = true;
        //timer for the speed of the snake
        timer = new Timer(DELAY,this);
        timer.start();
        
    } 

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //called the draw function to draw the apple and grid
        draw(g);
    }
    
    public void draw(Graphics g){
        if (running){
        //draw apple and grid inthe game screen
           /* for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
                g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
                
            }*/
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE,UNIT_SIZE);
            for(int i =0; i<bodyParts; i++){
                if(i==0){
                    g.setColor(new Color(0,100,0));
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
                else{
                    g.setColor(new Color(144,238,144));
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
            }
            
            g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
        
        }
        else{
            gameOver(g);
        }
    }
    public void newApple(){
        //to locate the apple at random position
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        
    }
    
    public void move(){
        for(int i=bodyParts;i>0;i--){
            x[i] = x[i-1]; //shift the array to move the snake
            y[i] = y[i-1];
            
        }
        //switch case to set the direction
        switch(direction){
        case 'U':
            y[0] = y[0]-UNIT_SIZE;
            break;
        case 'D':
            y[0] = y[0]+UNIT_SIZE;
            break;
        case 'L':
            x[0] = x[0]- UNIT_SIZE;
            break;
         case 'R':
            x[0] = x[0]+UNIT_SIZE;
            break;
        }
        
    }
    
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
    }
}
    public void checkCollision(){
        //check if heads collides with body
        for(int i = bodyParts;i>0;i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }
        //check if head of snake collide with borders
        if(x[0] < 0){
            running = false;
        }
        //check if head of snake collide with borders
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //check if head of snake collide with borders
        if(y[0] < 0){
            running = false;
        }
        //check if head of snake collide with borders
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        
        if(!running){
            timer.stop();
        }
        
    }
    
    public void gameOver(Graphics g){
        //score text
         g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,25));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
        
        //game over text setup
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH-metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        
        
    
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction !='R'){
                        direction= 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction !='L'){
                        direction= 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction !='D'){
                        direction= 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction !='U'){
                        direction= 'D';
                    }
                    break;
            }
        }
    
    
    }
}