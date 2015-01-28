
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
   private String itemName;
    private boolean fixed;
    
    public Item (String name, boolean fixed){
        itemName = name;
        this.fixed = fixed;
    }
    
    public String getItem(){
        return itemName;
    }
    
    public boolean isFixed(){
        return fixed;
    }
}
