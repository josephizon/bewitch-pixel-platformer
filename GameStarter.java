/**
	GameStarter is the class that contains the main method of the program which will be used to start the game for each player.
    The GameFrame is instantiated here, and it also connects to the server and sets up the GUI.
	
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

 public class GameStarter{   
    /** This is the main method of the program. */
    public static void main(String[] args) {

        GameFrame pf = new GameFrame(640, 480);
        pf.connectToServer();
        pf.setUpGUI();
    }

}
