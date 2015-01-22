import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room 
{
  public String description;
  private HashMap<String, Room> exits;
  private Person person;
  private String item;
  private boolean open;
 

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String personName, String item, boolean open) 
    {
        this.description = description;
        exits = new HashMap<>();
         if(personName != null){     
        person = new Person(personName);
        person.setAnswersAndItem(personName);
       } 
       else{person =null;}
         if(item != null){    
        this.item = item;
       } 
       else{item =null;} 
       this.open = open;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction,Room roomName) 
    {
        exits.put(direction, roomName);
    }
    
    public String getPersonName(){
        if (person == null){return "NICHTS";}
       else{return person.getName();}
    }
    
    public String getItemName(){
        return  item;
    }

    public void removeItem(){
        item = null;
    }
    
    public String getExits(){
        String result = "";
        for(String n : exits.keySet()){
            result += "[" + n +"]" + " ";
        
        }
        return result;
    }
    
    public Room changeRoom(String pdirection){
        Room nextRoom = null;
        //Ausg�nge werden im Uhrzeigersinn �berpr�ft.
        if (pdirection.equals("NORDEN")){
            nextRoom = exits.get("NORDEN");
        }
        if (pdirection.equals("OSTEN")){
            nextRoom = exits.get("OSTEN");
        }
         if(pdirection.equals("S�DEN")){
            nextRoom = exits.get("S�DEN");
        }
         if(pdirection.equals("WESTEN")){
            nextRoom = exits.get("WESTEN");
        }
        if (pdirection.equals("OBEN")){
            nextRoom = exits.get("OBEN");
        }
        if (pdirection.equals("UNTEN")){
            nextRoom = exits.get("UNTEN");
        }
        
        return nextRoom;
    }
    
    public String getAnswerOfThePersonInThisRoom (){
        if(person == null){return "Hier ist niemand mit dem du sprechen k�nntest!";}   
        else {return person.getFirstAnswer();}
    }
    
    public boolean isOpen(){
        return open;
    }
    
    public void setOpen(){
        this.open = true;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @return All exits of the room.
     */
    
}
