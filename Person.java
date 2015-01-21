

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String name;
    private String item;
    private String firstAnswer;
    private String secondAnswer;
    private boolean hasItem;
    /**
     * Constructor for objects of class Person
     */
    public Person(String theName)
    {
       name = theName;
       item = null;
       firstAnswer = null ;
       secondAnswer = null;
       hasItem = false;
        
      
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getName()
    {
        return name;
    }
    
    // Getter:
    
    public String getFirstAnswer(){
        return firstAnswer;
    }
    
     public String getSecondAnswer(){
        return secondAnswer;
    }
    
    // Methoden:
    
 
    
    public void setAnswersAndItem(String PersonName){
        if(PersonName.equals("Tom")){firstAnswer = "Hallo ich bin Tom!";item = "";hasItem = true;}
        if(PersonName.equals("")){firstAnswer = "";item = "";hasItem = true;}
        if(PersonName.equals("")){firstAnswer = "";item = "";hasItem = true;}
        if (PersonName.equals("")){firstAnswer = "";item = "";hasItem = true;}
       
        
        if(item == null){hasItem = false;}
        
        if(PersonName.equals("")){secondAnswer = "";}
        if(PersonName.equals("")){secondAnswer = "";}
        if(PersonName.equals("")){secondAnswer = "";}
        if(PersonName.equals("")){secondAnswer = "";}
    }
    
    
    public void removeItem(String item){
       if(item.equals(this.item)){
         this.item = null;
         hasItem = false;
        }
    }
}
