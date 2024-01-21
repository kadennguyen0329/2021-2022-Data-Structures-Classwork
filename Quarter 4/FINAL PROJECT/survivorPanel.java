import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

   
public class survivorPanel extends JPanel
{
   private static final int STARTSCREEN = 0, INTRO = 1, LOBBY = 2, GAME = 3, VOTING = 4, END = 5, WIN = 6; //gamemodes
   private static final int LOBBYTIME = 5, ENDGAMETIME = 5, ENDVOTETIME = 5; //length of time between modes
   private static final int SPEED = 35; //how many pixels players travel per step
   private static final int DELAY = 1;
   private static final int PLAYERXSIZE = 25, PLAYERYSIZE = 40; //size of players on screen
   public static final int XSIZE = 960;
   public static final int YSIZE = 600;
   private static final int TEXTSIZE = 25;
   private static final String[] names = {"Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "Elijah", "Charlotte", 
                                          "William", "Sophia", "James", "Amelia", "Benjamin", "Isabella", "Lucas", 
                                          "Mia", "Henry", "Evelyn", "Alexander", "Harper"}; //list of names for randomly generated players
   private static int frames; //keeps track of frames
   private static int lobbyTime, endGameTime, endVoteTime;//"timers" based off of how many frames have passed
   private static boolean votingIsDone, hasEliminated, listCreated;
   private static Timer t;
   private static String voteMessage; //message displayed at end of voting
   private static int gameMode; //current game mode
   private static int numPlayers; //number of players
   private static ArrayList<Player> players;
   private static Player user;
   private static String username;
   public static int playersAlive; //how many players are alive during the minigame
   public static Player winner, selected, eliminated; //pointers to specific players
   public static int selectedNum; //keep track of the selected player while voting
    
   public survivorPanel()
   {
      gameMode = STARTSCREEN;
      t = new Timer(DELAY, new Listener());
      t.start();
      numPlayers = 9;
      frames = 0;
      voteMessage = "";
      lobbyTime = LOBBYTIME;
      endGameTime = ENDGAMETIME;
      endVoteTime = ENDVOTETIME;
      hasEliminated = false;
      listCreated = false;
      selected = null;
      winner = null;
      eliminated = null;
      votingIsDone = false;
      selectedNum = -1;
      players = new ArrayList();
      for(int i = 0; i < numPlayers; i++) //creates list of randomly generated players
      {
         String name = names[(int)(Math.random()*((names.length-1)-0+1)+0)];
         players.add(new Player(name, (XSIZE/2)+(int)(Math.random()*(200+200+1)-200), (YSIZE/2)+(int)(Math.random()*(200+200+1)-200), "GRAPHICS/PLAYER.png"));
         listCreated = true;
      }
      playersAlive = players.size()+1;
      username = "";
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      showBoard(g);
   }
   
   public void showBoard(Graphics g)//paints background and players based on game mode
   {
      if(gameMode == STARTSCREEN)
      { 
         ImageIcon pic = new ImageIcon("GRAPHICS/WELCOME.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
      }
      else if(gameMode == INTRO)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/INTRO.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
         g.setColor(Color.BLACK);	
         g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE));
         g.drawString(username, (XSIZE/2)-100, (YSIZE/2));   
      }
      else if(gameMode == LOBBY)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/LOBBY.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
         g.setColor(Color.BLACK);	
         g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE));
         g.drawString("Use WASD to move ", 10, 20);
         g.drawImage(user.getPic().getImage(), user.getX(), user.getY(), PLAYERXSIZE, PLAYERYSIZE, null);
         g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE-10));
         g.drawString(username, user.getX(), user.getY()); 
         for(int i = 0; i < players.size(); i++)
         {
            g.drawImage(players.get(i).getPic().getImage(), players.get(i).getX(), players.get(i).getY(), PLAYERXSIZE, PLAYERYSIZE, null);
            g.drawString(players.get(i).getName(), players.get(i).getX(), players.get(i).getY());
         }
         g.drawString("Next game will start in "+ lobbyTime, XSIZE/2, YSIZE-50);
      }
      else if(gameMode == GAME)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/GAME.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
         g.setColor(Color.BLACK);	
         g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE));
         g.drawString("Players Left: "+playersAlive, 10, 20);
         g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE-10));
         g.drawImage(user.getPic().getImage(), user.getX(), user.getY(), PLAYERXSIZE, PLAYERYSIZE, null);
         g.drawString(username, user.getX(), user.getY());
         for(int i = 0; i < players.size(); i++)
         {
            g.drawImage(players.get(i).getPic().getImage(), players.get(i).getX(), players.get(i).getY(), PLAYERXSIZE, PLAYERYSIZE, null);
            g.drawString(players.get(i).getName(), players.get(i).getX(), players.get(i).getY());
         } 
         
         if(playersAlive <= 1 && endGameTime != 0) //if someone has won
         {
            g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE+14));
            g.drawString(winner.getName() + " IS THE LAST ONE STANDING!", 0, (YSIZE/2)-200);
            g.drawString(winner.getName() + " GETS VOTING IMMUNITY!", 0, (YSIZE/2)-150);
         }
         
      }
      else if(gameMode == VOTING)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/VOTE.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
         g.drawImage(user.getPic().getImage(), user.getX(), user.getY(), PLAYERXSIZE, PLAYERYSIZE, null);
         g.drawString(username, user.getX(), user.getY());
         for(int i = 0; i < players.size(); i++)
         {
            g.drawImage(players.get(i).getPic().getImage(), players.get(i).getX(), players.get(i).getY(), PLAYERXSIZE, PLAYERYSIZE, null);
            g.drawString(players.get(i).getName(), players.get(i).getX(), players.get(i).getY());
         }
         
         g.setColor(Color.RED);
         if(selected == user) //shows selected player in voting
            g.drawRect(user.getX(), user.getY(), PLAYERXSIZE, PLAYERYSIZE);
         for(int i = 0; i < players.size(); i++)
         {
            if(selected == players.get(i))
               g.drawRect(players.get(i).getX(), user.getY(), PLAYERXSIZE, PLAYERYSIZE);
         }
         
         if(votingIsDone && endVoteTime != 0)//display the voting messsage
         {
            g.setFont(new Font("Serif", Font.PLAIN, TEXTSIZE+14));
            g.drawString(voteMessage, 0, (YSIZE/2)-100);
         }
      }
      else if(gameMode == END)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/END.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
      }
      else if(gameMode == WIN)
      {
         ImageIcon pic = new ImageIcon("GRAPHICS/WIN.jpg");
         g.drawImage(pic.getImage(), 0, 0, XSIZE, YSIZE, null);
      }
   }      
   
   public void hitKey(int key) //sets keybinds
   {
      if(key == KeyEvent.VK_ENTER)
         advance();
   
      if(gameMode == INTRO)
      {
         if(key != KeyEvent.VK_BACK_SPACE)//backspace to delete from username
            username += "" + (char)(key);
         else if(key == KeyEvent.VK_BACK_SPACE && username.length() != 0)//enter in username
            username = username.substring(0, username.length()-1);
      }
      else if(gameMode == LOBBY || gameMode == GAME) //WASD movement
      {
         if(!user.isOut())
         {
            if(key == KeyEvent.VK_W)
               user.setY(user.getY()-SPEED);
            if(key == KeyEvent.VK_A)
               user.setX(user.getX()-SPEED);
            if(key == KeyEvent.VK_S)
               user.setY(user.getY()+SPEED);
            if(key == KeyEvent.VK_D)
               user.setX(user.getX()+SPEED);
         }
      }
      if(gameMode == VOTING)//left, right, and enter for voting navigation
      {
         if(key == KeyEvent.VK_LEFT)
         {
            if(selectedNum > -1)
               selectedNum--;
         }
         if(key == KeyEvent.VK_RIGHT)
         {
            if(selectedNum < players.size()-1)
               selectedNum++;
         }
         if(key == KeyEvent.VK_ENTER)
            votingIsDone = true;
      }
   }
   
   public void advance()//advancement from welcome screen to introduction to lobby
   {
      if(gameMode == STARTSCREEN)
      {
         gameMode = INTRO;
         resetTime();
      }
      else if(gameMode == INTRO)
      {
         user = new Player(username, (XSIZE/2), (YSIZE/2), "GRAPHICS/USER.png");
         gameMode = LOBBY;
         resetTime();
      }
   }
   
   public void timerAdvancement()//increments the "timer" based off of how many frames have passed
   {
      if(gameMode == LOBBY)
      {
         if(frames % 50 == 0)
            lobbyTime--;
      }
      if(gameMode == GAME && playersAlive <= 1)
      {
         if(frames % 50 == 0)
            endGameTime--;
      }
      
      if(gameMode == VOTING && votingIsDone)
      {
         if(frames % 50 == 0)
            endVoteTime--;
      }
   }
   
   public void resetTime() //resets all timers to default time
   {
      frames = 0;
      lobbyTime = LOBBYTIME;
      endGameTime = ENDGAMETIME;
      endVoteTime = ENDVOTETIME;
   }

   
   public void playerRandomMovement()//random movement by players
   {
      for(int i = 0; i < players.size(); i++)
      {
         if(!players.get(i).isOut())
         {
            Player player = players.get(i);
            if(Math.random() < 0.15)
               player.setX(player.getX() + ((int)(Math.random()*(SPEED+SPEED)+1)-SPEED));
            else if(Math.random() < 0.15)
               player.setY(player.getY() + ((int)(Math.random()*(SPEED+SPEED+1)-SPEED)));
            checkBorderExceed(player);
         }
      }
   }
   
   public void checkBorderExceed(Player player)//checks  if player has exceeded the border, and teleports them back
   {
      if(player.getX() > XSIZE-PLAYERXSIZE)
         player.setX(XSIZE-PLAYERXSIZE);
      else if(player.getX() < 0)
         player.setX(0);
         
      if(player.getY() > YSIZE-PLAYERYSIZE)
         player.setY(YSIZE-PLAYERYSIZE);
      else if(player.getY() < 0)
         player.setY(0);
   }
  
   public double distance(int x1, int y1, int x2, int y2)//distance formula
   {
      return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
   }
   
   public boolean hasStompedHead(Player stomper, Player stomped)//checks if player's feet have touched player's head
   {
      int stomperFootX = stomper.getX() + (PLAYERXSIZE/2);
      int stomperFootY = stomper.getY() + (PLAYERXSIZE);
      
      int stompedHeadX = stomped.getX() + (PLAYERXSIZE/2);
      int stompedHeadY = stomped.getY();
      
      if(distance(stomperFootX, stomperFootY, stompedHeadX, stompedHeadY) < TEXTSIZE)
         return true;
      return false;
   }
   
   public void setupGame() //teleports all players to designated places
   {
      user.setCoords(453-(PLAYERXSIZE/2), 459-PLAYERYSIZE);
      for(int i = 0; i < players.size(); i++)
      {
         if(i == 0)
         {
            players.get(0).setCoords(701-(PLAYERXSIZE/2), 439-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 1)
         {
            players.get(1).setCoords(802-(PLAYERXSIZE/2), 334-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 2)
         {
            players.get(2).setCoords(773-(PLAYERXSIZE/2), 223-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 3)
         {
            players.get(3).setCoords(661-(PLAYERXSIZE/2), 145-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 4)
         {
            players.get(4).setCoords(471-(PLAYERXSIZE/2), 124-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 5)
         {
            players.get(5).setCoords(286-(PLAYERXSIZE/2), 132-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 6)
         {
            players.get(6).setCoords(162-(PLAYERXSIZE/2), 214-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 7)
         {
            players.get(7).setCoords(124-(PLAYERXSIZE/2), 320-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
         if(i == 8)
         {
            players.get(8).setCoords(236-(PLAYERXSIZE/2), 420-PLAYERYSIZE);
            players.get(i).uneliminate();
         }
      }
      playersAlive = players.size() + 1;
      
   }
   
   public void stompHeadGame()//constantly checks collisions, and eliminates when required
   {
      for(int i = 0; i < players.size(); i++)//user on players
      {
         if((!players.get(i).isOut() && !user.isOut()) && hasStompedHead(user, players.get(i)))
         {
            players.get(i).eliminate();
            playersAlive--;
         }
      }
      
      for(int i = 0; i < players.size(); i++)//players on players
      {
         if(!players.get(i).isOut())
         {
            for(int j = 0; j < players.size(); j++)
            {
               if(!players.get(j).isOut() && players.get(i) != players.get(j) && hasStompedHead(players.get(i), players.get(j)))
               {
                  players.get(j).eliminate();
                  playersAlive--;
               }
            }
         }
      }
      
      for(int i = 0; i < players.size(); i++)//players on user
      {
         if((!players.get(i).isOut() && !user.isOut()) && hasStompedHead(players.get(i), user))
         {
            user.eliminate();
            playersAlive--;
         }
      }
      
      if(playersAlive <= 1 && endGameTime != 0)//if only one person left on field, give them immunity for voting
      {
         if(!user.isOut())
         {
            winner = user;
         }
         else
         {
            for(int i = 0; i < players.size(); i++)
            {
               if(!players.get(i).isOut())
               {
                  winner = players.get(i);
               }
            }
         }
         winner.giveImmunity();
      }
      
   }
   
   public void setupVote() //uneliminates everyone, and teleports them to designated places for voting
   {
      int xCoord = 80;
      user.setCoords(xCoord, YSIZE/2);
      user.uneliminate();
      for(int i = 0; i < players.size(); i++)
      {
         xCoord += 80;
         players.get(i).setCoords(xCoord, YSIZE/2);
         players.get(i).uneliminate();
      }
   }
   
   public void voting() //allows player to select someone to vote off, and then eliminates the person with the most votes
   {
      if(!votingIsDone)//if user hasnt selected someone
      {
         if(selectedNum == -1)
            selected = user;
         else
            selected = players.get(selectedNum);
      }
      else if(votingIsDone && !hasEliminated)//if user selected someone but they have not been eliminated
      {
         selected.addVote();
         for(int i = 0; i < players.size(); i++)//randomly adds votes to other players to simulate bots voting
         {
            int x = (int)(Math.random()*(players.size()+1)-1);
            if(x == -1)
               user.addVote();
            else
               players.get(x).addVote();
         }
         
         eliminated = user;
         for(int i = 0; i < players.size(); i++) //traverses through players and user and assigns eliminated pointer to person with most votes
         {
            if(players.get(i).getVotes() > eliminated.getVotes())
            {
               eliminated = players.get(i);
            }
         }
         if(eliminated == user && !user.getImmunity())//if user is voted off, game ends
            gameMode = END;
         else if(eliminated == user && user.getImmunity())//if user is voted off, but has immunity
         {
            voteMessage = user.getName() + " WAS VOTED OFF WITH " + user.getVotes() + "VOTES, BUT STAYS DUE TO IMMUNITY!";
            hasEliminated = true;
         }
         else
         {
            for(int i = 0; i < players.size(); i++)
            {
               if(players.get(i) == eliminated && !players.get(i).getImmunity())//if a player is voted off w/o immunity
               {
                  voteMessage = players.get(i).getName() + " WAS VOTED OFF WITH " + players.get(i).getVotes() + "VOTES!";
                  players.remove(i);
                  hasEliminated = true;
               }
               else if(players.get(i) == eliminated && players.get(i).getImmunity())//if a player is voted off w/ immunity
               {
                  voteMessage = players.get(i).getName() + " WAS VOTED OFF, BUT HAS IMMUNITY!";
                  hasEliminated = true;
               }
            }
         }
      }
   }
   
   public void resetAll() //resets all variables for next cycle
   {
      resetTime();
      hasEliminated = false;
      selected = null;
      winner = null;
      eliminated = null;
      votingIsDone = false;
      voteMessage = "";
      selectedNum = -1;
      playersAlive = players.size()+1;
      user.reset();
      for(int i = 0; i < players.size(); i++)
         players.get(i).reset();
   }
   
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(gameMode == LOBBY)
         {
            checkBorderExceed(user);
            playerRandomMovement();
         }
         if(lobbyTime == 0)
         {
            gameMode = GAME;
            resetTime();
         }
         if(gameMode == GAME)
         {
            if(playersAlive > 1 && frames <= 200)//beginning of the game
            {
               setupGame();
            }
            else
            {
               playerRandomMovement();
               checkBorderExceed(user);
               stompHeadGame();
            }
         }
         if(endGameTime == 0)
         {
            gameMode = VOTING;
            resetTime();
         }
         
         if(gameMode == VOTING)
         {
            if(frames <= 1)
            {
               setupVote();
            }
            voting();
         }
         if(endVoteTime == 0)
         {
            resetAll();
            gameMode = LOBBY;
         }
         if(players.isEmpty() && listCreated)
         {
            gameMode = WIN;
         }
         repaint();
         timerAdvancement();
         frames++; 
         System.out.println(gameMode);
      }
   }
}//(int)(Math.random()*(MAX-MIN+1)+MIN)