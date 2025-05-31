/**
	This is the Player class. It draws the players using gifs specific to their current action and player number.
    It sets up the movement of the player and the Collisions that occur when players hit certain objects.
	
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
import javax.swing.ImageIcon;

public class Player{


    private int x, y, width, height, pID, points, speedBuff;
    private int velX, velY, originalSpeed, maxSpeed, jumpHeight;
    private int gravity;
    private boolean colliding, jumping, falling;
    private boolean left, right, run, idle;
    private Image idleRightImg, idleLeftImg, runRightImg, runLeftImg;

    /**
     * This is the constructor of the Player class. When called, it initializes the Player's properties 
     * according to the passed arguments. It also determines which images to read according to the player number.
     * @param a This an integer that indicates the x coordinate of the player.
     * @param b This an integer that indicates the y coordinate of the player.
     * @param w This an integer that indicates the width of the player.
     * @param h This an integer that indicates the height of the player.
     * @param speed This an integer that indicates the speed, horizontal and vertical, of the player.
     * @param pID This an integer that indicates the player number of the player.
     */
    public Player(int a, int b, int w, int h, int speed, int pID)
    {
        x = a;
        y = b;
        width = w;
        height = h;
        points = 0;
        speedBuff = 0;
        velX = speed;
        velY = speed;
        originalSpeed = speed;
        jumpHeight = y-100;
        maxSpeed = 10;
        gravity = 1;
        jumping = false;
        falling = false;
        colliding = false;
        if(pID == 1)
        {
            left = false;
            right = true;
        }
        else
        {
            left = true;
            right = false;
        }

        run = false;
        idle = true;

            if(pID == 1)
            {
                idleRightImg = new ImageIcon("p1-idle-right.gif").getImage();
                idleLeftImg = new ImageIcon("p1-idle-left.gif").getImage();
                runRightImg = new ImageIcon("p1-run-right.gif").getImage();
                runLeftImg = new ImageIcon("p1-run-left.gif").getImage();

            }
            else
            {
                idleRightImg = new ImageIcon("p2-idle-right.gif").getImage();
                idleLeftImg = new ImageIcon("p2-idle-left.gif").getImage();
                runRightImg = new ImageIcon("p2-run-right.gif").getImage();
                runLeftImg = new ImageIcon("p2-run-left.gif").getImage();
            }



    }
    
    /**
     * This is the drawSprite class. This draws the image that was read during the instantiation of the Player.
     * It also draws the right image according to the action and the direction the player is facing.
     * @param g This is a Graphics object that acts as the pen that draws the Player.
     */
    public void drawSprite(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        if(idle && right)
            g2d.drawImage(idleRightImg, x, y, null);
        else if(idle && left)
            g2d.drawImage(idleLeftImg, x, y, null);
        else if(run && right)
            g2d.drawImage(runRightImg, x, y, null);
        else if(run && left)
            g2d.drawImage(runLeftImg, x, y, null);
    }

    /**
     * This is the moveRight method. This adds the x coordinate to move the sprite to the right according to its speed. When speed buffed, 
     * It also takes adds the speed buff value to the speed of the player.
     */
    public void moveRight()
    {
        x += velX+speedBuff;
    }

    /**
     * This is the moveLeft method. This subtracts the x coordinate to move the sprite to the left according to its speed. When speed buffed, 
     * It also takes adds the speed buff value to the speed of the player.
     */
    public void moveLeft()
    {
        x -= velX+speedBuff;
    }

    /**
     * This is the setX method. This turns the x coordinate of the player into the passed argument.
     * @param n This is an integer that represents the new x coordinate of the player.
     */
    public void setX(int n)
    {
        x = n;
    }

    /**
     * This is the setY method. This turns the y coordinate of the player into the passed argument.
     * @param n This is an integer that represents the new y coordinate of the player.
     */
    public void setY(int n)
    {
        y = n;
    }

    /**
     * This is the setVelY method. This turns the vertical speed of the player into the passed argument.
     * @param n This is an integer that represents the new vertical speed of the player.
     */
    public void setVelY(int n)
    {
        velY = n;
    }

    /**
     * This is the setVelX method. This turns the horizontal speed of the player into the passed argument.
     * @param n This is an integer that represents the new horizontal speed of the player.
     */
    public void setVelX(int n)
    {
        velX = n;
    }

    /**
     * This is the setSpeedBuff method. This turns the current speed buff into the passed argument.
     * In this game, the default speed buff property of the player is 0.
     * @param a This integer represents the new speed buff value attributed to the player.
     */
    public void setSpeedBuff(int a)
    {
        speedBuff = a;
    }

    /**
     * This is the setCollision method. This turns the colliding boolean into the passed argument.
     * When collidng is true, the player is prevented from falling down. The colliding boolean becomes true
     * when the player collides a platform.
     * @param a This boolean represents the new boolean value of the colliding field.
     */
    public void setCollision(boolean a)
    {
        colliding = a;
    }

    /**
     * This is the setJumping method. This turns the jumping boolean into the passed argument. 
     * When jumping is true, the gravity of the player will trigger. 
     * @param a This boolean represents the new boolean value of the jumping field.
     */
    public void setJumping(boolean a)
    {
        jumping = a;
    }

    /**
     * This is the setFalling method. This turns the falling boolean into the passed argument. 
     * When falling is true, the gravity of the player will trigger. 
     * @param a This boolean represents the new boolean value of the falling field.
     */
    public void setFalling(boolean a)
    {
        falling = a;
    }

    /**
     * This is the setAction method. This determines which player image will be drawn depending on the
     * passed argument.The player will either be idling(standing still), or running(moving). 
     * @param a This is the string that represents the action of the player.
     */
    public void setAction(String a)
    {
        if(a.equals("idle"))
        {
            idle = true;
            run = false;
        }

        else if(a.equals("run"))
        {
            idle = false;
            run = true;
        }
        
    }

    /**
     * This is the setFace method. This determines which player image will be drawn depending on the
     * passed argument.The player will either be facing right or left. 
     * @param a This is the string that represents the direction that the player is facing.
     */
    public void setFace(String a)
    {

        if(a.equals("right"))
        {
            right = true;
            left = false;
        }

        else if(a.equals("left"))
        {
            right = false;
            left = true;
        }

    }

     /**
     * This is the addPoints method. It adds the passed argument into the total points of the player.
     * @param a This is an integer representing the points to be added to the total ponts of the player.
     */
    public void addPoints(int a)
    {
        points = points + a;
    }



    /**
     * This is the jumpUp method. It moves the player upwards vertically by subtracting its y coordinate. 
     * It also triggers the jumping boolean to trigger gravity.
     */
    public void jumpUp()
    {
        y -= velY+10;
        setJumping(true);
    }
    
    /**
     * This is the fallDown method. It moves the player downwards all the time by adding the y coordinate
     * unless the Player is colliding a platform.
     */
    public void fallDown()
    {
        if(colliding)
        {
            setVelY(originalSpeed);
            setFalling(false);
        }
        else
        {
            y += velY;
            setFalling(true);
        }

    }
  
    /**
     * This is the gravity method. It gives characters more speed as it jumps up or falls down similar
     * to how gravity accelerates the fall of objects as it continues to move down. 
     */
    public void gravity()
    {
        if(falling || jumping)
        {
            if(velY > maxSpeed)
            {
                velY = maxSpeed;
            } else
            {
                velY += gravity;
            }
        }
            
    }

    /**
     * This is the calcMaxJumpHeight method. This is called when the player is on a platform and limits the player's 
     * vertical movement when they jump.
     */
    public void calcMaxJumpHeight()
    {
        if(colliding)
            jumpHeight = y - 100;
    }

    /**
     * This is the getX method. It returns the x coordinate of the Player.
     * @return x This an integer that indicates the x coordinate of the Player.
     */
    public int getX()
    {
        return x;
    }

    /**
     * This is the getY method. It returns the y coordinate of the Player.
     * @return y This is an integer that indicates the y coordinate of the Player.
     */
    public int getY()
    {
        return y;
    }

    /**
     * This is the getVelY method. It returns the vertical velocity of the Player.
     * @return velY This is an integer that indicates the vertical velocity of the Player.
     */
    public int getVelY()
    {
        return velY;
    }

    /**
     * This is the getVelX method. It returns the horizontal velocity of the Player.
     * @return velX This is an integer that indicates the horizontal velocity of the Player.
     */
    public int getVelX()
    {
        return velX;
    }

    /**
     * This is the getMaxJumpHeight method. It returns the max height that the player can reach when jumping.
     * This field is calculated in the calcMaxJumpHeight method.
     * @return jumpHeight This is an integer that indicates the max jump height of the player.
     */
    public int getMaxJumpHeight()
    {
        return jumpHeight;
    }

    /**
     * This is the getOriginalSpeed method. It returns the default speed of the player when player was instantiated. 
     * @return originalSpeed This is an integer that indicates the original speed of the player when it was
     * instantiated.
     */
    public int getOriginalSpeed()
    {
        return originalSpeed;
    }

    /**
     * This is the getPoints method. It returns the total points that the players have collected from PointOrbs
     * @return points This is an integer that represents the total points of the player.
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * This is the getAction method. It returns a string of the player's current action, whether idle or run.
     * This method was used for the mirroring of actions in the other player screen.
     * @return This is a string that represents the current action of the player.
     */
    public String getAction()
    {
        if(idle)
            return "idle";

        else if(run)
            return "run";

        return "";
    }

    /**
     * This is the getFace method. It returns a string of the direction the player is facing, whether right or left.
     * This method was used for the mirroring of direction of facing in the other player screen.
     * @return This is a string that represents the current direction the player is facing.
     */
    public String getFace()
    {  

        if(right)
            return "right";

        else if(left)
            return "left";

        return "";
        
    }

    /**
     * This is the isFalling method. This returns the falling boolean.
     * It is used in GameFrame to prevent players from repeatedly pressing the jump button.
     * @return falling This shows whether or not the player is falling.
     */
    public boolean isFalling()
    {
        return falling;
    }

    /**
     * This is the isJumping method. This returns the jumping boolean.
     * It is used in GameFrame to prevent players from repeatedly pressing the jump button.
     * @return jumping This shows whether or not the player is jumping.
     */
    public boolean isJumping()
    {
        return jumping;
    }

    /**
     * This is the isCollidingRightEdge method. This determines if the player is at the right edge
     * so that the Frame will move him to the other side.
     * @return This is a boolean resulted from the negatation a calculation whether x is less than 
     * position of the right edge.
     */
    public boolean isCollidingRightEdge()
    {
        return !(this.x < 640);
    }

    /**
     * This is the isCollidingLeftEdge method. This determines if the player is at the left edge
     * so that the Frame will move him to the other side.
     * @return This is a boolean resulted from the negatation of a calculation whether x plus player width 
     * is more than than position of the left edge.
     */
    public boolean isCollidingLeftEdge()
    {
        return !(this.x + this.width > 0);
    }

    /**
     * This is the isCollidingPlatform method. This determines if the player collides with a platform.
     * @param a This is a Platform that is tested with the properties of the player.
     * @return This is a boolean resulted from calculating the both the horizontal and vertical collision
     * of the player with regards to the passed platform.
     */
    public boolean isCollidingPlatform(Platform a)
    {
        boolean xCollision = !(this.x + this.width <= a.getX() || this.x >= a.getX()+ a.getWidth());
        boolean yCollision = (this.y + this.height == a.getY());
        return (xCollision && yCollision);
    }

    /**
     * This is the isCollidingOrb method. This determines if the player collides with an orb.
     * @param a This is a Orb that is tested with the properties of the player.
     * @return This is a boolean resulted from calculating the collision
     * of the player with regards to the passed orb.
     */
    public boolean isCollidingOrb(Orb a) {
        return !(this.x + this.width <= a.getX() ||
        this.x >= a.getX() + a.getWidth() ||
        this.y + this.height <= a.getY()||
        this.y >= a.getY() + a.getHeight()); 
    }

}