import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class survivorDriver
{

   public static survivorPanel screen;

   public static void main (String[] args)
   {
      screen = new survivorPanel();
      JFrame frame = new JFrame("Survivor");
      frame.setSize(survivorPanel.XSIZE, survivorPanel.YSIZE);
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(screen);		
      frame.setVisible(true);
      frame.addKeyListener(new KeyboardInput());
   }
   

   public static class KeyboardInput implements KeyListener
   {
      public void keyTyped(KeyEvent e)
      {
         
      }
   
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(1);
         screen.hitKey(e.getKeyCode());
      }
   
      public void keyReleased(KeyEvent e)
      {
      
      }
   }
   
}