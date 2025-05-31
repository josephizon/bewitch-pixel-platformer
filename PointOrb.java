/**
	This is the PointOrb, a subclass of the Orb superclass. It inherits all the fields and methods of the Orb superclass 
    but has a point value.
	
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

class PointOrb extends Orb {

    private int points;

    /**
     * This the constuctor of the PointOrb class. It uses the Orb superclass constructor and initializes Orb properties 
     * based on the passed arguments. Depending on the color, the PointOrb will have different point values.
     * Green Orbs give 3 points while Yellow Orbs give 5 points.
     * @param a This is an integer that indicates the x coordinate of the PointOrb.
     * @param b This is an integer that indicates the y coordinate of the PointOrb.
     * @param w This is an integer that indicates the width of the PointOrb.
     * @param h This is an integer that indicates the height of the PointOrb.
     * @param c This is an integer that indicates the color of a PointOrb.
     */
    public PointOrb(int a, int b, int w, int h, Color c) {
        super(a, b, w, h, c);
        if (c == Color.GREEN)
            points = 3;
        else if (c == Color.YELLOW)
            points = 5;
    }

    /**
     * This is the getPointValue method. It returns the designated points for a PointOrb 
     * that was determined when it was instantiated.
     * @return points This is an integer that represents the point value of the PointOrb.
     */
    public int getPointValue() {
        return points;
    }

}
