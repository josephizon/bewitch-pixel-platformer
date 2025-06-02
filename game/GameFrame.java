/**
	GameFrame serves as the Client Side Server. Most of the code located here are used to set up the main JFrame of the player. 
	
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

/** This is the start of the GameFrame class.  */
public class GameFrame extends JFrame {

    private int width, height;
    private Player me, enemy;
    private Container contentPane;
    private GameCanvas gc;
    private javax.swing.Timer animationTimer;
    private boolean right, left, jump;
    private Socket socket;
    private Scanner console;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private int jumpHeight;
    private int time1, time2, time3, speedBuffDuration;
    private boolean colliding;
    private boolean checkedOrb1;
    private boolean checkedOrb2;
    private boolean checkedOrb3;
    private int enemyPoints, maxPoints; 
    private boolean whileCondition; 

    /** This is the GameFrame constructor where most of variable are instantiated.
     * @param w is the value to be placed for the width of the frame that is to be made. 
     * @param h is the value to be placed for the height of the frame that is to be made. 
    */

    public GameFrame(int w, int h) {
        width = w;
        height = h;
        console = new Scanner(System.in);
        gc = new GameCanvas();
        right = false;
        left = false;
        jump = false;
        jumpHeight = 0;
        time1 = 0;
        time2 = 0;
        time3 = 0;
        speedBuffDuration=0;
        checkedOrb1 = false;
        checkedOrb2 = false;
        checkedOrb3 = false;
        enemyPoints = 0;
        maxPoints = 100; 
        whileCondition = true; 
        
    }

    /** setUpGUI() is used to setup the JFrame for the two players who will be playing the game.  */
    public void setUpGUI() {

        contentPane = this.getContentPane();
        this.setTitle("Player #" + playerID);
        contentPane.setPreferredSize(new Dimension(width, height));
        createSprites();
        contentPane.add(gc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        setUpAnimationTimer();
        setUpKeyListener();
    }

    /** createSprites is used to identify which sprites to get from the gc or GameCanvas and whether to assign that sprite to either me (player) or the enemy (other player)  */
    private void createSprites() {

        if (playerID == 1) {
            me = gc.getSprite(1);
            enemy = gc.getSprite(2);
            me.gravity();
            enemy.gravity();


        } else {
            me = gc.getSprite(2);
            enemy = gc.getSprite(1);
            me.gravity();
            enemy.gravity();
            
        }
    }

    /** setUpAnimationTimer() has all the code needed for the animation and movements of the  everything inside the GameFrame JFrame. */
    private void setUpAnimationTimer() {
        int interval = 10;
        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                time1++;
                time2++;
                time3++;
                testCollision();

                
                if (colliding == true)
                    me.setCollision(true);
                else
                    me.setCollision(false);
                
                if(checkedOrb1 == false)
                {
                    testCollisionOrb(1);
                    time1 = 0;
                }

                if(checkedOrb2 == false)
                {
                    testCollisionOrb(2);
                    time2 = 0;
                }

                if(checkedOrb3 == false)
                {
                    testCollisionSpeedOrb();
                    time3 = 0;
                    speedBuffDuration++;
                }
                
                changePointText();
                
                if(time1 > 50)
                    checkedOrb1 = false;
                if(time2 > 50) 
                    checkedOrb2 = false;
                if(time3 > 50) 
                {
                    checkedOrb3 = false;

                }
                if(speedBuffDuration > 100)
                {
                    speedBuffExpire();
                    speedBuffDuration = 0;
                }


                while(whileCondition){
                    if(playerID == 1){
                        if(me.getPoints() >= maxPoints ) {
                            System.out.println("Congratulations Player 1! You won the battle with " + me.getPoints() + " points!");
                            whileCondition = false;
                            System.exit(0);                 
                            }
                        else if (enemyPoints >= maxPoints) {
                            int difference = enemyPoints - me.getPoints();
                            System.out.println("You have been defeated! You will forever lose you powers for losing by " + difference + " points!" );
                            whileCondition = false;
                            System.exit(0);
                        }
                    }
                    else {
                        if(me.getPoints() >= maxPoints ) {
                            System.out.println("Congratulations Player 2! You won the battle with " + me.getPoints() + " points!");
                            whileCondition = false;
                            System.exit(0);
                        }
                        else if (enemyPoints >= maxPoints) {
                            int difference = enemyPoints - me.getPoints();
                            System.out.println("You have been defeated! You will forever lose you powers for losing by " + difference + " points!");
                            whileCondition = false;
                            System.exit(0);
                        }
                    }
                    break;
                }
                
                me.fallDown();

                if (right) {
                    me.setFace("right");
                    me.setAction("run");

                    if (me.isCollidingRightEdge())
                        me.setX(0);
                        me.moveRight();
                }

                if (left) {
                    me.setFace("left");
                    me.setAction("run");

                    if (me.isCollidingLeftEdge())
                        me.setX(640);
                        me.moveLeft();

                }

                if (!left && !right) {
                    me.setAction("idle");
                }

                if (jump) {

                    me.calcMaxJumpHeight();
                    jumpHeight = me.getMaxJumpHeight();

                    if (me.getY() < jumpHeight) {
                        jump = false;
                    }

                    me.jumpUp();
                    repaint();
                }

                gc.repaint();                      

            }

        };
        animationTimer = new javax.swing.Timer(interval, al);
        animationTimer.start();
    }

    /** testCollision() is used to give all platforms the colliding variable if they are colliding with the character. */
    private void testCollision() {
        for (Platform x : gc.getPlatformList()) {
            if (me.isCollidingPlatform(x)) {
                colliding = true;
                break;
            } else
                colliding = false;
        }

    }

    /** testCollisionOrb() is used to check whether or not the character is colliding with the point orbs in the game. */
    private void testCollisionOrb(int i){
        
        if(me.isCollidingOrb(gc.getOrb(i)))
        {
            if (i == 1)
                checkedOrb1 = true;
            else
                checkedOrb2 = true;
            me.addPoints(gc.getOrb(i).getPointValue());
        }
    }

    /** testCollisionSpeedOrb() is used to check whether or not the character is colliding with the speed orbs in the game. */
    private void testCollisionSpeedOrb()
    {
        if(me.isCollidingOrb(gc.getSpeedOrb()))
        {
            checkedOrb3 = true;
            me.setSpeedBuff(gc.getSpeedOrb().getSpeedBuff());
        }
    }

    /** speedBuffExpire() is used to set the speedBuff back to zero when it expires. */
    private void speedBuffExpire()
    {
        me.setSpeedBuff(0);
    }
    
    /** changePointText() is used to convert the points from int into String value to be displayed in the GUI */
    private void changePointText()
    {
        if(playerID == 1)
            gc.changePointString(Integer.toString(me.getPoints()), 1);
        else
            gc.changePointString(Integer.toString(me.getPoints()), 2);
    }

    /** setUpKeyListener() is used to assign the specific keyboard functions towards movement in the game. */
    private void setUpKeyListener() {

        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!me.isJumping() || !me.isFalling())
                            jump = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        jump = false;
                        break;
                }

            }
        };
        contentPane.addKeyListener(kl);
        contentPane.setFocusable(true);
    }

    /** connectToServer() is used to establish a connection to the socket to be used in the program.  */
    public void connectToServer() {
        try {
            System.out.print("Please input the server's IP Address: ");
            String ipAddress = console.nextLine();
            System.out.print("Please input the port number: ");
            int portNum = Integer.parseInt(console.nextLine());
            System.out.println("ATTEMPTING TO CONNECT TO THE SERVER...");
            socket = new Socket(ipAddress, portNum);
            System.out.println("CONNECTION SUCCESSFUL!");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player #" + playerID);
            if (playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();

        } catch (IOException ex) {
            System.out.println("IOException from connectTo Server()");
        }
    }

    /** ReadFromServer receives all the data coming from the Write to Client of the GameServer */
    private class ReadFromServer implements Runnable {
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in) {
            dataIn = in;
        }

        @Override
        public void run() {
            try {
                while (true) {

                    if (enemy != null) {
                        enemy.setX(dataIn.readInt());
                        enemy.setY(dataIn.readInt());
                        enemy.setFace(dataIn.readUTF());
                        enemy.setAction(dataIn.readUTF());
                        if(playerID == 1){
                            enemyPoints = dataIn.readInt();
                            gc.changePointString(Integer.toString(enemyPoints), 2);
                        }
                        else{
                            enemyPoints = dataIn.readInt();
                            gc.changePointString(Integer.toString(enemyPoints), 1);
                        }

                    }
                        gc.getOrb(1).setX(dataIn.readInt());
                        gc.getOrb(1).setY(dataIn.readInt());
                        gc.getOrb(2).setX(dataIn.readInt());
                        gc.getOrb(2).setY(dataIn.readInt());
                        gc.getSpeedOrb().setX(dataIn.readInt());
                        gc.getSpeedOrb().setY(dataIn.readInt());
                    
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterrruptedException from WTC run()");
                    }
                }

            } catch (IOException ex) {
                System.out.println("IOException from RFS run()");
            }

        }

        /** waitForStartMsg() waits for the startMsg from the GameServer, before running the threads  */
        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server: " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();

            } catch (IOException ex) {
                System.out.println("IOException from waitForStartMsg()");
            }
        }

    }

    
    /** WriteToServer sends the data from the GameFrame to the Read From Client of the GameServer */
    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out) {
            dataOut = out;
        }

        @Override
        public void run() {
            try {
                while (true) {

                    if (me != null) {
                        dataOut.writeInt(me.getX());
                        dataOut.writeInt(me.getY());
                        dataOut.writeUTF(me.getFace());
                        dataOut.writeUTF(me.getAction());
                        
                        dataOut.writeBoolean(me.isCollidingOrb(gc.getOrb(1)));
                        dataOut.writeBoolean(me.isCollidingOrb(gc.getOrb(2)));
                        dataOut.writeBoolean(me.isCollidingOrb(gc.getSpeedOrb()));
                        dataOut.writeInt(me.getPoints());
                        dataOut.flush();

                    }
                    try {   
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterruptedException from WTS run()");
                    }
                }

            } catch (IOException ex) {
                System.out.println("IOException from WTS run()");
            }

        }

    }

}
