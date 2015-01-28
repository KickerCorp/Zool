
/**
 * Write a description of class Interaction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interaction
{
    // instance variables - replace the example below with your ow
    private Inventory inventar;

    /**
     * Constructor for objects of class Interaction
     */
    public Interaction(Inventory inventar)
    {
        this.inventar = inventar;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String interactWithIt(String item){
        String result = null;    
        if(item.contains("WERKBANK")){result = buildLuckItem();}
        else if(item.contains("HERD")){result = cooking();}
        else{result = "Ist niemand der mit dir sprechen will.";}
        return result;
    }
    
    public String buildLuckItem()
    {
        if (inventar.removeItem("KLEEBLATT")== true && inventar.removeItem("HUFEISEN")== true){
            inventar.removeItem("KLEEBLATT");
            inventar.removeItem("HUFEISEN");
            inventar.addToInventory("GLÜCKSBRINGER");
            return "Du hast einen Glücksbringer gebaut";
           }
       else if (inventar.removeItem("WASSER")== true && inventar.removeItem("MIESMUSCHEL")== true){
            inventar.addToInventory("MAGISCHEMIESMUSCHEL");
            return "Du hast einen Magische MIESMUSCHEL gebaut";
           }

       else{return "Du hast NICHT die richtigen Materialien!";}
    
    
  }
  
  public String cooking(){
    
        if (inventar.removeItem("FLEISCH")== true){
            inventar.addToInventory("ESSEN");
            return "Du hast tolles Essen gekocht";
           }
          else{return "Du hast NICHT die richtigen Zutaten!";} 
    }
}    
