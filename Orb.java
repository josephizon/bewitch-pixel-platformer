/**
	This is the Orb superclass. This class holds the basic properties of all orb: its coordinates, size, and shape.
	
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
import java.awt.geom.*;

public class Orb {

    int x, y, width, height;
    Color color;

    /**
     * This is the constuctor of the Orb superclass. When called, it initializes the orb's properties 
     * according to the passed arguments.
     * @param a This is an integer that indicates the x coordinate of the Orb.
     * @param b This is an integer that indicates the y coordinate of the Orb.
     * @param w This is an integer that indicates the width of the Orb.
     * @param h This is an integer that indicates the height of the Orb.
     * @param c This is an integer that indicates the color of the Orb.
     */
    public Orb(int a, int b, int w, int h, Color c) {
        x = a;
        y = b;
        width = w;
        height = h;
        color = c;
    }

    /**
     * This is the drawOrb method. It draws an ellipse using Graphics2D and the properties of the Orb.
     * @param g2d This is a Graphics2D object that acts as the pen that draws the Orb.
     */
    public void drawOrb(Graphics2D g2d) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        Ellipse2D.Double test = new Ellipse2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(test);
    }
    
    /**
     * This is the getX method. It returns the x coordinate of the Orb.
     * @return x This an integer that indicates the x coordinate of the Orb.
     */
    public int getX() {
        return x;
    }

    /**
     * This is the getY method. It returns the y coordinate of the Orb.
     * @return y This is an integer that indicates the y coordinate of the Orb.
     */
    public int getY() {
        return y;
    }

    /**
     * This is the getWidth method. It returns the width of the Orb.
     * @return width This is an integer that indicates the width of the Orb.
     */
    public int getWidth() {
        return width;
    }

    /**
     * This is the getHeight method. It returns the height of the Orb.
     * @return height This is an integer that indicates the height of the Orb.
     */
    public int getHeight() {
        return height;
    }

    /**
     * This is the setX method. It sets the horizontal position of the Orb to the passed argument.
     * @param a This is an integer that represents the new x coordinate of the Orb.
     */
    public void setX(int a) {
        x = a;
    }

    /**
     * This is the setY method. It sets the vertical position of the Orb to the passed argument.
     * @param a This is an integer that represents the new y coordinate of the Orb.
     */
    public void setY(int a) {
        y = a;
    }

}
