import java.util.*;
import java.util.Scanner;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is returned.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.1 (2011.07.31)
 */
public class InputReader
{
    public Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    public HashSet<String> getInput()
    {
        System.out.print("> ");         // print prompt
        
       String capital = reader.nextLine().toUpperCase();
        String[] wordArray = capital.split(" |\\!|\\?|\\.|\\,");   // add capital in wordarray
         // add word array into HashSet words  
        HashSet<String> words = new HashSet<String>();
        for(String word : wordArray){
        words.add(word);
    }
        
      
       return words;
    }
}

