
/**
 * Write a description of class Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignments // der Quest Status
{
    // instance variables - replace the example below with your own
    private String[] tasks;
    static int taskCounter;

    /**
     * Constructor for objects of class Assignment
     */
    public Assignments()
    {
        tasks = new String[10];
        addTasks();
        taskCounter = 0;
    }
    
    public void addTasks(){
        tasks[0] = "Finde einen Rucksack, um Gegenstände tragen zu können";
        tasks[1] = "Bau dir einen Glücksbringer";
        tasks[2] = "Gewinne im Schere Stein Papier";
        tasks[3] = "Benutze den Schlüssel.";
        tasks[4] = "Bau dir die Magischemiesmuschel";
        tasks[5] = "Gewinne das Spiel";
    }
    
    public String getCurrentTask(){
         return "\nAktuelle Mission: " + tasks[taskCounter];    
    }
    
    static void changeCurrentTask(){
        taskCounter += 1;
    }

   
}
