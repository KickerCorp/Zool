import java.util.ArrayList;
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
                
            
                if(!InventarIsFull){
                    Inventar.add(item);
                    ObjectsInInventory++;
                        //Rucksack wird nicht mehr im Inventar angezeigt.
                        if(Inventar.contains("Rucksack")){
                            Inventar.remove("Rucksack");
                        } 
                }
                else{System.out.println("Dein Rucksack ist schon voll!");}
            
                if(ObjectsInInventory == 3){InventarIsFull = true;}
                else{InventarIsFull = false;}
            
        }
    
    public void useItem(String Use){
        for(String Item : Inventar){
          if(Item.equals(Use)){
             Inventar.remove(Inventar.indexOf(Item));
             ObjectsInInventory = ObjectsInInventory -1;
          }
       }
    }
    
    public String showInventory(){
        String result = "In deinem Rucksack befinden sich: ";   
        for (String Item : Inventar){ 
         result += Item;
        }
        if(Inventar.isEmpty()){
            result = "nichts - Es ist leer!";
        }
        return result;
    }
}
