
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
        else{result = "Ist niemand der mit dir sprechen will.";}
        return result;
    }
    
    public String buildLuckItem()
    {
        if (Inventar.removeItem("KLEEBLATT")== true && Inventar.removeItem("HUFEISEN")== true){
            Inventar.addToInventory("GLÜCKSBRINGER");
            return "Du hast einen Glücksbringer gebaut";
        }
        else{return "Du hast NICHT die richtigen Materialien!";}
    }

}
    
