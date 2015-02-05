
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
        tasks[0] = "Finde einen RUCKSACK, um Gegenstände tragen zu können";
        tasks[1] = "Bau dir einen GLÜCKSBRINGER und benutze ihn. Du wirst ihn brauchen. Stichwort: Keller!";
        tasks[2] = "Gewinne im Schere-Stein-Papier";
        tasks[3] = "Begib dich in die Küche.";
        tasks[4] = "Bau dir die MAGISCHEMIESMUSCHEL";
        tasks[5] = "Schreibe die Antwort auf die Fragen aller Fragen in die Kommandozeile!\n Tipp: Schaue dich in der Vorratskammer genauer um!";
    }
    
    public String getCurrentTask(){
         return "\nAktuelle Mission: " + tasks[taskCounter];    
    }
    
    public String changeCurrentTask(){
        taskCounter += 1;
        return "\n" + getCurrentTask();
    }

   
}
