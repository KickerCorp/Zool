
/**
 * Write a description of class Interaction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interaction
{
    // instance variables - replace the example below with your ow
    private Inventory Inventar;

    /**
     * Constructor for objects of class Interaction
     */
    public Interaction(Inventory Inventar)
    {
        this.Inventar = Inventar;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String interactWithIt(String removingPerson){
        String result = null;    
        if(removingPerson.equals("WERKBANK")){result = buildLuckItem();}
        else if(removingPerson.equals("HERD")){result = cooking();}
        else{result = "Ist niemand der mit dir sprechen will.";}
        return result;
    }
    
    public String buildLuckItem()
    {
        if (Inventar.removeItem("KLEEBLATT")== true && Inventar.removeItem("HUFEISEN")== true){
            Inventar.addToInventory("GL�CKSBRINGER");
            return "Du hast einen Gl�cksbringer gebaut";
           }
       else if (Inventar.removeItem("WASSER")== true && Inventar.removeItem("MIESMUSCHEL")== true){
            Inventar.addToInventory("MAGISCHEMIESMUSCHEL");
            return "Du hast einen Magische MIESMUSCHEL gebaut";
           }

       else{return "Du hast NICHT die richtigen Materialien!";}
    
    
  }
  
  public String cooking(){
    
        if (Inventar.removeItem("FLEISCH")== true){
            Inventar.addToInventory("ESSEN");
            return "Du hast tolles Essen gekocht";
           }
          else{return "Du hast NICHT die richtigen Zutaten!";} 
    }
}    
