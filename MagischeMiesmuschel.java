import java.util.*;
/**
 * This class implements a technical support system. It is the top
 * level class in this project. The support system communicates via
 * text input/output in the text terminal.
 * 
 * This class uses an object of class InputReader to read input from
 * the user, and an object of class Responder to generate responses.
 * It contains a loop that repeatedly reads input and generates output
 * until the users wants to leave.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.1 (2011.07.31)
 */
public class MagischeMiesmuschel
{
    private Responder responder;
    
    
    /**
     * Creates a technical support system.
     */
    public MagischeMiesmuschel()
    {
        
        responder = new Responder();
    }

    /**
     * Start the technical support system. This will print a welcome
     * message and enter into a dialog with the user, until the user
     * ends the dialog.
     */
    public boolean start(String input)
    {  
        if(input.contains("ZUR�CKLEGEN")) {
               System.out.println("Du legst die Muschel zur�ck!");
               return false;
            }
        else {
                String response = responder.generateResponse(input);
                System.out.println(response);
                return true;
             }
    }

    /**
     * Print a welcome message to the screen.
     */
    public void printWelcome()
    {
        System.out.println("An der Muschel hängt eine Schnur - du ziehst sie.");
        System.out.println("Während sich die Schnur langsam wieder zurückzieht, ertönt aus der Muschel:");
        System.out.println("'Ich bin eine magische Miesmuschel'");
        System.out.println("'Stelle eine Ja/Nein Frage und ziehe an der Schnur!'");
        System.out.println("Wenn du fertig bist kannst du die Muschel 'ZUR�CKLEGEN'");
    }

    /**
     * Print a good-bye message to the screen.
     */
    private void printGoodbye()
    {
        System.out.println("Du hast die Muschel zurückgelegt");
    }
   
   
}


