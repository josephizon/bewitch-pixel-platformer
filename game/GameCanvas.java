/**
	GameCanvas is responsible for the graphics that are seen throughout the GUI / GameFrame. 
    This is essentially the drawing canvas of the whole game wherein most graphics are located here. 
	
	@author Cherish K. Magpayo (203171)
	@author Joseph Raymund F. Izon (202605)
	@version June 01, 2021
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/
package game;


import game.components.*; 
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


/** This is the start of the GameCanvas class. */
public class GameCanvas extends JComponent{
    
    
    private Player p1, p2;
    private PointOrb orb1, orb2;
    private SpeedOrb orb3;
    private ArrayList<Platform> platformList;
    private int orbPosX1, orbPosY1, orbPosX2, orbPosY2, orbPosX3, orbPosY3;
    private String p1Points;
    private String p2Points;
    Font undertale;

    /** This is the constructor of the GameCanvas wherein most of the variables are instantiated here.  */
    public GameCanvas(){

        platformList = new ArrayList<>();
        platformList.add(new Platform(-100, 410, 800, 100)); // main platform 
        platformList.add(new Platform(400, 200, 150, 10)); // Right most platform 
        platformList.add(new Platform(100, 310, 150, 10)); // Lower Left Most Platform 
        platformList.add(new Platform(300, 250, 75, 10)); // Mid Plaftform
        platformList.add(new Platform(100, 150, 150, 10));  // Upper Left Most Platform 
        platformList.add(new Platform(350, 75, 75, 10));  // Upper Right Most Platform 
        p1 = new Player(100, 350, 32, 50, 5, 1);
        p2 = new Player(490, 350, 32, 50, 5, 2);
        orbPosX1 = 0;
        orbPosY1 = 0;
        orbPosX2 = 0;
        orbPosY2 = 0;
        orbPosX3 = 0;
        orbPosY3 = 0;
        orb1 = new PointOrb(orbPosX1, orbPosY1, 10, 10, Color.YELLOW);
        orb2 = new PointOrb(orbPosX2, orbPosY2, 10, 10, Color.GREEN);
        orb3 = new SpeedOrb(orbPosX3, orbPosY3, 10, 10, Color.MAGENTA);
        p1Points = "00";
        p2Points = "00";

        try {
            undertale = Font.createFont(Font.TRUETYPE_FONT, new File("assets/MonsterFriendFore.otf")).deriveFont(25f);
            GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/MonsterFriendFore.otf")));
            background = ImageIO.read(new File("assets/background.png"));
        } catch (IOException|FontFormatException e) {
        }

    }

    /** paintComponent serves as the drawing canvas and paint brush as the graphics are made here.  */
    BufferedImage background;
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints. VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.drawImage(background, 0, 0, null);

        for(Platform x:platformList)
        {
            x.drawPlatform(g2d);
        }

        p1.drawSprite(g2d);
        p2.drawSprite(g2d);
        
        if(orb1.getX()!= 0)
        {
            orb1.drawOrb(g2d);
            orb2.drawOrb(g2d);
            orb3.drawOrb(g2d);
        }
        g.setFont(undertale);
        g.setColor(Color.WHITE);
        g.drawString("Bewitch", 240, 40);
        g.setColor(new Color(111, 205, 207));
        g.drawString(p1Points, 15, 40);
        g.setColor(new Color(251, 68, 67));
        g.drawString(p2Points, 580, 40);
        
        
    }

    /** getSprite() identifies which sprite to return depending on which player asks for it in GameFrame 
     * @param a is the integer which defines either the first or second player. 
    */
    public Player getSprite(int a)
    {
        if(a == 1)
            return p1;
        else
            return p2;
    }

    /** getPlatformList() is used to return the array list of platforms instantiated in the constructor. */
    public ArrayList<Platform> getPlatformList()
    {
        return platformList;
    }

    /** getOrb() identifies which orb  should be returned, whether it be the yellow or green point orb. 
     *  @param i is the integer which differentiates which orb it should be getting. 
    */
    public PointOrb getOrb(int i)
    {
        if(i == 1)
        {
            return orb1;
        }
        else 
        {
            return orb2;
        }
        

    }

    /** getSpeedOrb() returns the value of orb 3 or the speed orb. */
    public SpeedOrb getSpeedOrb()
    {
        return orb3;
    }

    /** changePointString() converts the Integer of the points into a String to be used later.
     * @param a stores the value of the points of the player but converted to string 
     * @param i is used to differentiate where the points should go, either to player 1 or 2. 
     */
    public void changePointString(String a, int i)
    {
        if(i == 1)
        {
            if(Integer.parseInt(a)<10)
                p1Points = "0"+a;
            else
                p1Points = a;
        }
        

        else if(i == 2)
        {
            if(Integer.parseInt(a)<10)
                p2Points = "0"+a;
            else
                p2Points = a;
        }
        
    }
}
