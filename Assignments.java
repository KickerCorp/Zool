
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
        tasks[0] = "Finde einen Rucksack, um Gegenst�nde tragen zu k�nnen";
        tasks[1] = "Finde den Schl�ssel f�r die K�che"; // Um die T�r zu �ffnen (in der K�che)
        tasks[2] = "�ffne den zweite K�chent�r";// mit use K�chen schl�ssel 
        tasks[3] = "Bau dir einen Gl�cksbringer";// Aus Kleeblatt und Hufeisen
        tasks[4] = "Finde die Vorratskammer";// Schere Stein Papier Mensch besiegen.
        tasks[5] = "�ffne die Turmt�r";// Mit dem Schl�ssel vom aus dem Vorratsraum. 
        tasks[6] = "Hilf dem Bettler";// Essen kochen und dem Bettler bringen um Gl�ck zu erh�hen
        tasks[7] = "Finde einen weg in den Zielturm";// Indem du den Kickerspieler besiegst bekommst du einen Schl�ssel
        tasks[8] = "Finde einen Hiwei�";// Um die Statur im Garten anzusprechen
        tasks[9] = "L�se das R�tel";// Mit indem du den Hinwei� mit der Statur verwendest.
    }
    
    public String getCurrentTask(){
         return "\nAktuelle Mission: " + tasks[taskCounter];    
    }
    
    public void changeCurrentTask(){
        taskCounter += 1;
    }

   
}
