import javax.swing.ImageIcon;

public class Player
{
   private String name;
   private int x,y;
   private ImageIcon picture;
   private final ImageIcon defaultPic;
   private boolean isOut;
   private boolean hasImmunity;
   private int votes;
   
   public Player(String n, int dx, int dy, String fileName)
   {
      name = n;
      x = dx;
      y = dy;
      picture = new ImageIcon(fileName);    
      defaultPic = new ImageIcon(fileName);    
      isOut = false;
      hasImmunity = false;
      votes = 0;
   }
   
   public void reset()
   {
      hasImmunity = false;
      isOut = false;
      votes = 0;
   }
   
   public void addVote()
   {
      votes++;
   }
   
   public int getVotes()
   {
      return votes;
   }
   
   public void eliminate()
   {  
      picture = new ImageIcon("GRAPHICS/DEAD.png");
      isOut = true;
   }
   
   public void uneliminate()
   {
      picture = defaultPic;
      isOut = false;
   }
   
   public void giveImmunity()
   {
      hasImmunity = true;
   }
   
   public boolean getImmunity()
   {
      return hasImmunity;
   }
   
   public boolean isOut()
   {
      return isOut;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public void setX(int dx)
   {
      x = dx;
   }
   
   public void setCoords(int dx, int dy)
   {
      x = dx;
      y = dy;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setY(int dy)
   {
      y = dy;
   }
   
   public ImageIcon getPic()
   {
      return picture;
   }
   
   public String toString()
   {
      return name + ""+ x + "" + y;
   }
   
}