import javax.swing.JFrame;

/**
 * Write a description of class GameFrame here.
 *
 * @author (karamjeet kaur)
 * @version (2.0)
 */
public class GameFrame extends JFrame
{
    GameFrame(){
       // GamePanel panel = new GamePanel();
        //this.add(panel);
        this.add(new GamePanel()); // Add the game Pannel
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
    // instance variables - replace the example below with your own

}
