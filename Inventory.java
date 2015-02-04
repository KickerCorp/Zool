import java.util.*;
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private ArrayList<String> Inventar;
    private boolean InventarIsFull;
    private int ObjectsInInventory;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        Inventar = new ArrayList<>();
        InventarIsFull = false;
        ObjectsInInventory = 0;
    }

    public void addToInventory(String item){
         if(ObjectsInInventory == 3){InventarIsFull = true;}
        else{InventarIsFull = false;}

        
        
        if(!InventarIsFull){
            Inventar.add(item);
            ObjectsInInventory++;
            //Rucksack wird nicht mehr im Inventar angezeigt.
            if(Inventar.contains("RUCKSACK")){
                removeItem("RUCKSACK");
                
            } 
        }
        else{System.out.println("Dein Rucksack ist schon voll!");}

       
    }
    
    public boolean inventoryFull(){
        return InventarIsFull;
    }

    public boolean removeItem(String item){
        boolean returned = false;    
        if (Inventar.contains(item)){
            Inventar.remove(item);
            ObjectsInInventory--;
            returned = true;
            return returned;
        }
        return returned;
    }

    public String showInventory(){
        String result = "In deinem Rucksack befinden sich: ";   
        for (String Item : Inventar){ 
            result += Item;
            result += " ";
        }
        if(Inventar.isEmpty()){
            result = "nichts - Es ist leer!";
        }
        return result;
    }

    public boolean contains(String n){
        boolean containing = false;
        containing = Inventar.contains(n);
        return containing;
    }
}
