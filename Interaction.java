
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
}

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    /** public String interactWithIt(String item){
        String result = null;    
        if(item.contains("WERKBANK")){result = interact();}
        else if(item.contains("HERD")){result = cooking();}
        else{result = "Ist niemand der mit dir sprechen will.";}
        return result;

    }
}
    /**
    public String benutzen(){
        if(command.getCommandWord().equals("BENUTZE")){
            if(!command.hasSecondWord()) {
                return "Was willst du benutzen?";
            }
            else if(currentRoom.listOfItems.contains(command.getSecondWord)){ //Wenn gew�hltes Item im currentRoom vorhanden, f�hre es aus
                //hier k�nnt ihr die jeweiligen Methoden f�r die Objekte reinschreiben
                
                //Methode der Magischen Miesmuschel
            if(command.getSecondWord.equals("MAGISCHE MIESMUSCHEL")) {
                    if(inventar.contains("MAGISCHE MIESMUSCHEL")){
                    MagischeMiesmuschel.play();
                }
                else { System.out.println("Du besitzt dieses Item nicht.");}
            }
            
            /**    //Methode der Gl�ckskette
            if(command.getSecondWord.equals("GL�CKSKETTE"){
                    if(inventory.contains("GL�CKSKETTE"){
                        increaseWinRate(); // noch nicht implementiert!
                        System.out.println("Das Gl�ck steht nun �fters an deiner Seite");
                    }
                    else { 
                       System.out.println("Dir fehlt ein oder mehrere Items zum kombinieren.");
                            }
                        }
                        
               **/ 
               
               /**
                //Methode der Werkbank
                
                if(command.getSecondWord.equals("WERKBANK")&&currentRoom.getItemName().contains("WERKBANK")){
                    System.out.println("Du benutzt die WERKBANK. Du kannst hier zwei Items kombinieren.");
                    System.out.println("Welche Gegenst�nde m�chtest du kombinieren?");
                    System.out.println("Gib [GEGENSTAND1] [] [GEGENSTAND2] ein");
                    
                    // Gl�ckbringer und MagischeMiesmuschel bauen
                       if(inventar.contains("KLEEBLATT")&&inventar.contains("HUFEISEN")){
                       if(command.getCommandWord().equals("KLEEBLATT")&&command.getSecondWord().equals("HUFEISEN")){
                            inventar.removeItem("KLEEBLATT");
                            inventar.removeItem("HUFEISEN");
                            inventar.addToInventory("GL�CKSBRINGER");
                            return "Du hast erh�lst GL�CKSBRINGER gebaut";
                        }
                    }
                    
                       else if(inventar.contains("WASSER")&&inventar.contains("MIESMUSCHEL")){
                        if(command.getCommandWord().equals("WASSER")&&command.getSecondWord().equals("MIESMUSCHEL")){
                            inventar.removeItem("WASSER"); 
                            inventar.removeItem("MIESMUSCHEL"); 
                            inventar.addToInventory("MAGISCHEMIESMUSCHEL");
                            return "Du hast einen MAGISCHEMIESMUSCHEL gebaut";
                        }
                        else{
                            return "Du hast NICHT die richtigen Materialien! �ndere gegebenfalls die Reihenfolge!";
                        }
                    }
                        
                      }
                    }
                }
            }
  
  public String cooking(){
    
        if (inventar.removeItem("FLEISCH")== true){
            inventar.addToInventory("ESSEN");
            return "Du hast tolles Essen gekocht";
           }
          else{return "Du hast NICHT die richtigen Zutaten!";} 
    }
}    
**/
