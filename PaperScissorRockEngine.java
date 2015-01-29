
import java.util.*;
/**
 * Write a description of class GameEngine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PaperScissorRockEngine
{
    private HashMap<String, String> winMap;
    private HashMap<String, String> loseMap;    
    private Random randomGenerator;
    //private Parser parser;
    private String result;
    private int probability;
    
    /**
     * Constructor for objects of class GameEngine
     */
    public PaperScissorRockEngine()
    {
        winMap = new HashMap<>();
        loseMap = new HashMap<>();
        randomGenerator = new Random();
        //parser = new Parser(); 
        fillValidWords();

    }


    public void fillValidWords(){
        loseMap.put("SCHERE", "STEIN");
        loseMap.put("PAPIER", "SCHERE");
        loseMap.put("STEIN", "PAPIER");
        loseMap.put("MIST", "Du gibst auf!");
        winMap.put("SCHERE", "PAPIER");
        winMap.put("PAPIER", "STEIN");
        winMap.put("STEIN", "SCHERE");

    }

    private String play(Command command){
        //Command command = parser.getCommand();
        int i = randomGenerator.nextInt(100);

        String commandWord = command.getCommandWord();
        
        if(command.isUnknown()) {
            return "Keine Ahnung was du meinst?!";
        }  
        
        if (commandWord.equals("MIST")){
            result += loseMap.get(commandWord);
            return result;
        }
        
        if (i < probability){
            result = "Dein Gegner wählt : ";
            if(commandWord.equals("STEIN"))    
                result += winMap.get(commandWord);
            else if (commandWord.equals("PAPIER"))
                result += winMap.get(commandWord);
            else if(commandWord.equals("SCHERE"))
                result += winMap.get(commandWord);

            result += ". Du gewinnst! :)";
        }
        else{
            result = "Dein Gegner wählt : ";
            if (commandWord.equals("SCHERE"))
                result += loseMap.get(commandWord);
            else if (commandWord.equals("STEIN"))    
                result += loseMap.get(commandWord);
            else if (commandWord.equals("PAPIER"))
                result += loseMap.get(commandWord);

            result += ". Du verlierst! :(";
        }
        return result;

    }

    public String startPlaying(Command command){
        result = "";
        boolean finished = false;
        result = play(command);
        if(result.contains(". Du gewinnst! :)")){

            result += "Herzlichen Glückwunsch!";
            return result;
        }
        if(result.contains("Du gibst auf!")){
            result += " Eine heftige Klatsche, versuche es später noch einmal!";
            return result;
        }
        System.out.println(result);
        
        return result;
    }
    
    public void setProbability(int p){
        probability = p;
    }
}