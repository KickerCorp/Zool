
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
        tasks[1] = "Finde den Schlüssel für die Küche"; // Um die Tür zu öffnen (in der Küche)
        tasks[2] = "Öffne den zweite Küchentür";// mit use Küchen schlüssel 
        tasks[3] = "Bau dir einen Glücksbringer";// Aus Kleeblatt und Hufeisen
        tasks[4] = "Finde die Vorratskammer";// Schere Stein Papier Mensch besiegen.
        tasks[5] = "Öffne die Turmtür";// Mit dem Schlüssel vom aus dem Vorratsraum. 
        tasks[6] = "Hilf dem Bettler";// Essen kochen und dem Bettler bringen um Glück zu erhöhen
        tasks[7] = "Finde einen weg in den Zielturm";// Indem du den Kickerspieler besiegst bekommst du einen Schlüssel
        tasks[8] = "Finde einen Hiweiß";// Um die Statur im Garten anzusprechen
        tasks[9] = "Löse das Rätel";// Mit indem du den Hinweiß mit der Statur verwendest.
    }
    
    public String getCurrentTask(){
         return "\nAktuelle Mission: " + tasks[taskCounter];    
    }
    
    public void changeCurrentTask(){
        taskCounter += 1;
    }

   
}
