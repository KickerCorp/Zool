import java.util.*;
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
    private Parser iParser;

    /**
     * Constructor for objects of class Interaction
     */
    public Interaction(Inventory inventar, Parser parser)
    {
        this.inventar = inventar;
        iParser = new Parser();
        iParser = parser;
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
     public String interactWithIt(String item, Command command){
        String result = null;    
        if(item.contains("WERKBANK")){result = benutzen(command);}
        else if(item.contains("HERD")){result = cooking();}
        else{result = "Das kann man hier nicht benutzen.";}
        return result;
        
    }

    
    public String benutzen(Command command){
        String result = "";
                    System.out.println("Du benutzt die WERKBANK. Du kannst hier zwei Items kombinieren.");
                    System.out.println("Welche Gegenstände möchtest du kombinieren?");
                    System.out.println("Gib [GEGENSTAND1] [] [GEGENSTAND2] ein");
        
            
                            //hier könnt ihr die jeweiligen Methoden für die Objekte reinschreiben
                
                //Methode der Magischen Miesmuschel
           /** if(newCommand.getSecondWord().equals("MAGISCHE MIESMUSCHEL")) {
                    if(inventar.contains("MAGISCHE MIESMUSCHEL")){
                        MagischeMiesmuschel muschel = new MagischeMiesmuschel();
                        //MagischeMiesmuschel.play();
                }
                else { System.out.println("Du besitzt dieses Item nicht.");}
            }
            
                //Methode der Glückskette
            if(newCommand.getSecondWord().equals("GLÜCKSKETTE")){
                    if(inventar.contains("GLÜCKSKETTE")){
                        //increaseWinRate(); // noch nicht implementiert!
                        System.out.println("Das Glück steht nun öfters an deiner Seite");
                    }
                    else { 
                       System.out.println("Dir fehlt ein oder mehrere Items zum kombinieren.");
                            }
                        }**/
       
                        // Glückbringer und MagischeMiesmuschel bauen
                    if(inventar.contains("KLEEBLATT")&&inventar.contains("HUFEISEN")){
                       if(command.getCommandWord().equals("KLEEBLATT")&& command.getSecondWord().equals("HUFEISEN")){
                            inventar.removeItem("KLEEBLATT");
                            inventar.removeItem("HUFEISEN");
                            inventar.addToInventory("GLÜCKSBRINGER");
                            return "Du hast erhälst GLÜCKSBRINGER gebaut";
                        }
                    }
                    
                       else if(inventar.contains("WASSER")&&inventar.contains("MIESMUSCHEL")){
                        if(command.getCommandWord().equals("WASSER")&&command.getSecondWord().equals("MIESMUSCHEL") || command.getCommandWord().equals("WASSER") && command.getSecondWord().equals("MIESMUSCHEL")){
                            System.out.println("Test");
                            inventar.removeItem("WASSER"); 
                            inventar.removeItem("MIESMUSCHEL"); 
                            inventar.addToInventory("MAGISCHEMIESMUSCHEL");
                            return "Du hast einen MAGISCHEMIESMUSCHEL gebaut";
                        }
                        else{
                            return "Du hast NICHT die richtigen Materialien!";
                        }
                    
                        
                      }
                    
                    return result;
                }
                
            
  
  public String cooking(){
    
        if (inventar.removeItem("FLEISCH")== true){
            inventar.addToInventory("ESSEN");
            return "Du hast tolles Essen gekocht";
           }
          else{return "Du hast NICHT die richtigen Zutaten!";} 
    }
}    

