/**
	This is the Platform class. This class creates platforms for the players to move on. 
	
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

import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


public class Platform{
    
    private int x, y, width, height;
    private BufferedImage platform;

    /**
     * This is the constructor of the Platform class.When called, it initializes the platform's properties 
     * according to the passed arguments. It reads the respective image depending on the width that is passed through
     * the parameter.
     * @param a This is an integer that indicates the x coordinate of the Platform.
     * @param b This is an integer that indicates the y coordinate of the Platform.
     * @param w This is an integer that indicates the width of the Platform.
     * @param h This is an integer that indicates the height of the Platform.
     */
    public Platform(int a, int b, int w, int h)
    {
        x = a;
        y = b;
        width = w;
        height = h;

        try {
            if(w == 800)
                platform = null;
            else if(w == 150)
                platform = ImageIO.read(new File("platform150.png"));
            else if(w == 75)
                platform = ImageIO.read(new File("platform75.png"));

        } catch (IOException e) {
        }
        
    }

    /**
     * This is the drawPlatform class. This draws the image that was read during the instantiation of the platform.
     * @param g2d This is a Graphics2D object that acts as the pen that draws the Platform.
     */
    public void drawPlatform(Graphics2D g2d)
    {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints. VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.drawImage(platform, x, y, null);
    }

    /**
     * This is the getX method. It returns the x coordinate of the Platform.
     * @return x This is an integer that indicates the x coordinate of the Platform.
     */
    public int getX()
    {
        return x;
    }

    /**
     * This is the getY method. It returns the y coordinate of the Platform.
     * @return y This is an integer that indicates the y coordinate of the Platform.
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * This is the getWidth method. It returns the width of the Platform.
     * @return width This is an integer that indicates the width of the Platform.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * This is the getHeight method. It returns the height of the Platform.
     * @return height This is an integer that indicates the height of the Platform.
     */
    public int getHeight()
    {
        return height;
    }

}
