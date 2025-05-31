/**
	This is the SpeedOrb, a subclass of the Orb superclass. It inherits all the fields and methods of the Orb superclass 
    but has a speed buff value.
	
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
public class SpeedOrb extends Orb{
    private int speedBuff;


    /**
     * This the constuctor of the SpeedOrb class. It uses the Orb superclass constructor and initializes Orb properties 
     * based on the passed arguments.
     * @param a This is an integer that indicates the x coordinate of the SpeedOrb.
     * @param b This is an integer that indicates the y coordinate of the SpeedOrb.
     * @param w This is an integer that indicates the width of the SpeedOrb.
     * @param h This is an integer that indicates the height of the SpeedOrb.
     * @param c This is an integer that indicates the color of a SpeedOrb.
     */
    public SpeedOrb(int a, int b, int w, int h, Color c) {
        super(a, b, w, h, c);
        speedBuff = 3;

    }

    /**
     * This is the getSpeedBuff method. It returns the amount of speed added to the player velocity.
     * @return speedBuff This is an integer that represents amount of speed added to the player velocity.
     */
    public int getSpeedBuff() {
        return speedBuff;
    }

}
    

