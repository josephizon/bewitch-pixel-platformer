/**
	This is the GameServer class. It manages the Server side of the Game by regularly receiving 
    and sending information to the player.
	
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

import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private String p1Face, p2Face, p1Action, p2Action;

    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;

    private int p1x, p1y, p2x, p2y;
    private boolean p1Orb1, p1Orb2, p1Orb3, p2Orb1, p2Orb2, p2Orb3;
    private boolean p1CheckedOrb1, p1CheckedOrb2, p1CheckedOrb3, p2CheckedOrb1, p2CheckedOrb2, p2CheckedOrb3;
    private int orbPositionX1, orbPositionY1, orbPositionX2, orbPositionY2, orbPositionX3, orbPositionY3;
    private int p1Orb1time, p1Orb2time, p1Orb3time, p2Orb1time, p2Orb2time, p2Orb3time;
    private int p1Points, p2Points;

    /**
     * This is the Constructor of the Game Server. It creates the a GameServer object when instantiated. It initializes 
     * player and orb variables that will be used to send in and out data.
     */
    public GameServer() {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;
        //players
        p1Face = "";
        p2Face = "";
        p1Action = "";
        p2Action = "";
        p1x = 100;
        p1y = 350;
        p2x = 490;
        p2y = 350;

        //orb collisions
        p1Orb1 = false;
        p1Orb2 = false;
        p1Orb3 = false;
        p2Orb1 = false;
        p2Orb2 = false;
        p2Orb3 = false;
        
        p1CheckedOrb1 = false;
        p1CheckedOrb2 = false;
        p1CheckedOrb3 = false;
        p2CheckedOrb1 = false;
        p2CheckedOrb2 = false;
        p2CheckedOrb3 = false;

        p1Orb1time = 0;
        p1Orb2time = 0;
        p1Orb3time = 0;
        p2Orb1time = 0;
        p2Orb2time = 0;
        p2Orb3time = 0;

        orbPositionX1 = 0;
        orbPositionY1 = 0;

        while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
              (orbPositionX1 >= 400 && orbPositionX1 <= 550 && orbPositionY1 >= 200 && orbPositionY1 <= 215 ) || 
              (orbPositionX1 >= 100 && orbPositionX1 <= 250 && orbPositionY1 >= 310 && orbPositionY1 <= 320 ) ||
              (orbPositionX1 >= 300 && orbPositionX1 <= 375 && orbPositionY1 >= 250 && orbPositionY1 <= 260 ) ||
              (orbPositionX1 >= 100 && orbPositionX1 <= 250 && orbPositionY1 >= 150 && orbPositionY1 <= 160 ) ||
              (orbPositionX1 >= 350 && orbPositionX1 <= 425 && orbPositionY1 >= 75  && orbPositionY1 <= 85  ) ||
              (orbPositionX1 == 0 && orbPositionX1 == 0 && orbPositionY1 == 0  && orbPositionY1 == 0  )) 
              {
                    orbPositionX1 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                    orbPositionY1 = (int) Math.floor(Math.random() * (380 - 0) + 0);
        }

        orbPositionX2 = 0;
        orbPositionY2 = 0;

        while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
              (orbPositionX2 >= 400 && orbPositionX2 <= 550 && orbPositionY2 >= 200 && orbPositionY2 <= 215 ) || 
              (orbPositionX2 >= 100 && orbPositionX2 <= 250 && orbPositionY2 >= 310 && orbPositionY2 <= 320 ) ||
              (orbPositionX2 >= 300 && orbPositionX2 <= 375 && orbPositionY2 >= 250 && orbPositionY2 <= 260 ) ||
              (orbPositionX2 >= 100 && orbPositionX2 <= 250 && orbPositionY2 >= 150 && orbPositionY2 <= 160 ) ||
              (orbPositionX2 >= 350 && orbPositionX2 <= 425 && orbPositionY2 >= 75  && orbPositionY2 <= 85  ) ||
              (orbPositionX2 == 0 && orbPositionX2 == 0 && orbPositionY2 == 0  && orbPositionY2 == 0  ))  
              {
                    orbPositionX2 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                    orbPositionY2 = (int) Math.floor(Math.random() * (380 - 0) + 0);
        }

        orbPositionX3 = 0;
        orbPositionY3 = 0;

        while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
              (orbPositionX3 >= 400 && orbPositionX3 <= 550 && orbPositionY3 >= 200 && orbPositionY3 <= 215 ) || 
              (orbPositionX3 >= 100 && orbPositionX3 <= 250 && orbPositionY3 >= 310 && orbPositionY3 <= 320 ) ||
              (orbPositionX3 >= 300 && orbPositionX3 <= 375 && orbPositionY3 >= 250 && orbPositionY3 <= 260 ) ||
              (orbPositionX3 >= 100 && orbPositionX3 <= 250 && orbPositionY3 >= 150 && orbPositionY3 <= 160 ) ||
              (orbPositionX3 >= 350 && orbPositionX3 <= 425 && orbPositionY3 >= 75  && orbPositionY3 <= 85  ) ||
              (orbPositionX3 == 0 && orbPositionX3 == 0 && orbPositionY3 == 0  && orbPositionY3 == 0  ))  
              {
                    orbPositionX3 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                    orbPositionY3 = (int) Math.floor(Math.random() * (380 - 0) + 0);
        }

        try {
            ss = new ServerSocket(55555);
        } catch (IOException ex) {
            System.out.println("IOException from GameServer constructor");
        }

    }

    /**
     * This is the acceptConnections method. It opens the server socket to accept player connections 
     * until two players have connected. It creates the data input and output stream and starts 
     * threads for sending and receiving data to and from players.
     */
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1) {
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else {
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();

                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();

                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }

            }

            System.out.print("No longer accepting connections");

        } catch (IOException ex) {
            System.out.println("IOException from acceptConnections()");
        }
    }

    /**
     * This is the randomCoordinateGenerator method. When called, it randomizes the position of either orb1, orb2, or orb3
     * depending on the passed integer.
     * @param a This is an integer that indicates which orb's position is to be randomized. 
     * 1 would indicate orb1, 2 to orb2, and 3 to orb3.
     */
    public void randomCoordinateGenerator(int a){
        if(a == 1) {
        orbPositionX1 = 0;
        orbPositionY1 = 0;

        while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
              (orbPositionX1 >= 400 && orbPositionX1 <= 550 && orbPositionY1 >= 200 && orbPositionY1 <= 215 ) || 
              (orbPositionX1 >= 100 && orbPositionX1 <= 250 && orbPositionY1 >= 310 && orbPositionY1 <= 320 ) ||
              (orbPositionX1 >= 300 && orbPositionX1 <= 375 && orbPositionY1 >= 250 && orbPositionY1 <= 260 ) ||
              (orbPositionX1 >= 100 && orbPositionX1 <= 250 && orbPositionY1 >= 150 && orbPositionY1 <= 160 ) ||
              (orbPositionX1 >= 350 && orbPositionX1 <= 425 && orbPositionY1 >= 75  && orbPositionY1 <= 85  ) ||
              (orbPositionX1 == 0 && orbPositionX1 == 0 && orbPositionY1 == 0  && orbPositionY1 == 0  )) 
              {
                    orbPositionX1 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                    orbPositionY1 = (int) Math.floor(Math.random() * (380 - 0) + 0);
        }

        }
        else if (a == 2) {
            orbPositionX2 = 0;
            orbPositionY2 = 0;

        while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
              (orbPositionX2 >= 400 && orbPositionX2 <= 550 && orbPositionY2 >= 200 && orbPositionY2 <= 215 ) || 
              (orbPositionX2 >= 100 && orbPositionX2 <= 250 && orbPositionY2 >= 310 && orbPositionY2 <= 320 ) ||
              (orbPositionX2 >= 300 && orbPositionX2 <= 375 && orbPositionY2 >= 250 && orbPositionY2 <= 260 ) ||
              (orbPositionX2 >= 100 && orbPositionX2 <= 250 && orbPositionY2 >= 150 && orbPositionY2 <= 160 ) ||
              (orbPositionX2 >= 350 && orbPositionX2 <= 425 && orbPositionY2 >= 75  && orbPositionY2 <= 85  ) ||
              (orbPositionX2 == 0 && orbPositionX2 == 0 && orbPositionY2 == 0  && orbPositionY2 == 0  ))  
              {
                    orbPositionX2 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                    orbPositionY2 = (int) Math.floor(Math.random() * (380 - 0) + 0);
        }
        }
        else if(a == 3) {
            orbPositionX3 = 0;
            orbPositionY3 = 0;
    
            while((orbPositionX1 >= 0   && orbPositionX1 <= 640 && orbPositionY1 >= 0   && orbPositionY1 <= 40  ) || 
                  (orbPositionX3 >= 400 && orbPositionX3 <= 550 && orbPositionY3 >= 200 && orbPositionY3 <= 215 ) || 
                  (orbPositionX3 >= 100 && orbPositionX3 <= 250 && orbPositionY3 >= 310 && orbPositionY3 <= 320 ) ||
                  (orbPositionX3 >= 300 && orbPositionX3 <= 375 && orbPositionY3 >= 250 && orbPositionY3 <= 260 ) ||
                  (orbPositionX3 >= 100 && orbPositionX3 <= 250 && orbPositionY3 >= 150 && orbPositionY3 <= 160 ) ||
                  (orbPositionX3 >= 350 && orbPositionX3 <= 425 && orbPositionY3 >= 75  && orbPositionY3 <= 85  ) ||
                  (orbPositionX3 == 0 && orbPositionX3 == 0 && orbPositionY3 == 0  && orbPositionY3 == 0  )) 
                  {
                        orbPositionX3 = (int) Math.floor(Math.random() * (630 - 0) + 0);
                        orbPositionY3 = (int) Math.floor(Math.random() * (380 - 0) + 0);
            }



        }
    }
    
    /**
     * This is the inner class ReadFromClient which is ran through a thread to receive data from the player.
     * Specifically, it receives the coordinates of players to be later sent to their respective enemies. 
     * It also receives booleans indicating the collision of orbs for each player.
     */
    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        /**
         * This is the inner class ReadFromClient's constructor. When instantiated, it receives the specified player ID 
         * so that players will send information in separate threads.
         * @param pid This is an integer representing the Player number that the Server is receiving information from.
         * @param in This is a data input stream for receiving data from players.
         */
        public ReadFromClient(int pid, DataInputStream in) {
            playerID = pid;
            dataIn = in;
        }

        public void run() {
            try {
                while (true) {
                    
                    
                    if (playerID == 1) {

                        p1x = dataIn.readInt();
                        p1y = dataIn.readInt();
                        p1Face = dataIn.readUTF();
                        p1Action = dataIn.readUTF();
                        p1Orb1 = dataIn.readBoolean();
                        p1Orb2 = dataIn.readBoolean();
                        p1Orb3 = dataIn.readBoolean();
                        p1Points = dataIn.readInt();


                    } else {
                        p2x = dataIn.readInt();
                        p2y = dataIn.readInt();
                        p2Face = dataIn.readUTF();
                        p2Action = dataIn.readUTF();
                        p2Orb1 = dataIn.readBoolean();
                        p2Orb2 = dataIn.readBoolean();
                        p2Orb3 = dataIn.readBoolean();
                        p2Points = dataIn.readInt();

                    }

                }

            } catch (IOException ex) {
                System.out.println("IOException from RFC run()");
            }

        }

    }

    /**
     * This is the inner class WriteToClient which is ran through a thread to send data to the player.
     * Specifically, it sends the coordinates of players to their opponents for mirrored movement.
     * When the server receives a true boolean for orb collision, this class also randomizes the 
     * orb position and sends orb coordinates to both players.
     */
    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut;
        /**
         * This is the inner class WriteToClient's constructor. When instantiated, it receives the specified player ID 
         * so that the server will send information to both players in separate threads.
         * @param pid This is an integer representing the Player number that the Server is sending information to.
         * @param out This is a data output stream for sending data to players.
         */
        public WriteToClient(int pid, DataOutputStream out) {
            playerID = pid;
            dataOut = out;
        }

        public void run() {
            try {


                while (true) {

                    p1Orb1time++;
                    p1Orb2time++;
                    p1Orb3time++;
                    p2Orb1time++;
                    p2Orb2time++;
                    p2Orb3time++;

                    if (playerID == 1) {
                        dataOut.writeInt(p2x);
                        dataOut.writeInt(p2y);
                        dataOut.writeUTF(p2Face);
                        dataOut.writeUTF(p2Action);
                        dataOut.writeInt(p2Points);
                        dataOut.flush();
                        
                    } else {
                        dataOut.writeInt(p1x);
                        dataOut.writeInt(p1y);
                        dataOut.writeUTF(p1Face);
                        dataOut.writeUTF(p1Action);
                        dataOut.writeInt(p1Points);
                        dataOut.flush();
                    }

                    if(p1Orb1 && !p1CheckedOrb1)
                    {
                        randomCoordinateGenerator(1);
                        p1CheckedOrb1 = true;
                        p1Orb1time = 0;
                    }

                    if(p1Orb2 && !p1CheckedOrb2)
                    {
                        randomCoordinateGenerator(2);
                        p1CheckedOrb2 = true;
                        p1Orb2time = 0;
                    }

                    
                    if(p1Orb3 && !p1CheckedOrb3)
                    {
                        randomCoordinateGenerator(3);
                        p1CheckedOrb3 = true;
                        p1Orb3time = 0;
                    }
                    
                    if(p2Orb1 && !p2CheckedOrb1)
                    {
                        randomCoordinateGenerator(1);
                        p2CheckedOrb1 = true;
                        p2Orb1time = 0;
                    }

                    if(p2Orb2 && !p2CheckedOrb2)
                    {
                        randomCoordinateGenerator(2);
                        p2CheckedOrb2 = true;
                        p2Orb2time = 0;
                    }

                    if(p2Orb3 && !p2CheckedOrb3)
                    {
                        randomCoordinateGenerator(3);
                        p2CheckedOrb3 = true;
                        p2Orb3time = 0;
                    }

                    if(p1Orb1time > 50)
                        p1CheckedOrb1 = false;
                    if(p1Orb2time > 50)
                        p1CheckedOrb2 = false;
                    if(p1Orb3time > 50)
                        p1CheckedOrb3 = false;
                    if(p2Orb1time > 50)
                        p2CheckedOrb1 = false;
                    if(p2Orb2time > 50)
                        p2CheckedOrb2 = false; 
                    if(p2Orb3time > 100)
                        p2CheckedOrb3 = false;
                        

                    
                    dataOut.writeInt(orbPositionX1);
                    dataOut.writeInt(orbPositionY1);
                    dataOut.writeInt(orbPositionX2);
                    dataOut.writeInt(orbPositionY2);
                    dataOut.writeInt(orbPositionX3);
                    dataOut.writeInt(orbPositionY3);
                    dataOut.flush();
                    

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterrruptedException from WTC run()");
                    }
                    

                }
            } catch (IOException ex) {
                System.out.println("IOException from WTC run()");
            }

        }


        /**
         * This is the sendStartMsg method. It indicates to the players that there are two successful connections and
         * that the game is going to start.
         */
        public void sendStartMsg() {
            try {
                dataOut.writeUTF("We now have 2 players. Go!");
            } catch (IOException ex) {
                System.out.println("IOException from sendStartMsg()");
            }

        }

        

    }

    /**
     * This is the main method of the GameServer class. It instantiates the server and opens the server to connections.
     */
    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
