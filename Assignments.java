
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
    private int taskCounter;

    /**
     * Constructor for objects of class Assignment
     */
    public Assignments()
    {
        tasks = new String[5];
        addTasks();
        taskCounter = 0;
    }
    
    public void addTasks(){
        tasks[0] = "Finde einen Rucksack, um Gegenst�nde tragen zu k�nnen";
        tasks[1] = "Finde den Schl�ssel f�r den Keller";
    }
    
    public String getCurrentTask(){
        return tasks[taskCounter];    
    }
    
    public void changeCurrentTask(){
        taskCounter += 1;
    }

   
}
