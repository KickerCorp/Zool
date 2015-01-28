import java.util.*;

public class Benutzen
{
    
    
    public void benutzen(){
        if(command.getCommandWord().equals("BENUTZE")){
            if(!command.hasSecondWord()) {
                return "Was willst du benutzen?";
            }
            else if(currentRoom.listOfItems.contains(command.getSecondWord)){ //Wenn gewähltes Item im currentRoom vorhanden, führe es aus
                //hier könnt ihr die jeweiligen Methoden für die Objekte reinschreiben
                
                //Methode der Magischen Miesmuschel
            if(command.getSecondWord.equals("MAGISCHE MIESMUSCHEL")) {
                    if(inventar.contains("MAGISCHE MIESMUSCHEL")){
                    MagischeMiesmuschel.play();
                }
                else { System.out.println("Du besitzt dieses Item nicht.");}
            }
            
            /**    //Methode der Glückskette
            if(command.getSecondWord.equals("GLÜCKSKETTE"){
                    if(inventory.contains("GLÜCKSKETTE"){
                        increaseWinRate(); // noch nicht implementiert!
                        System.out.println("Das Glück steht nun öfters an deiner Seite");
                    }
                    else { 
                       System.out.println("Dir fehlt ein oder mehrere Items zum kombinieren.");
                            }
                        }
                        
               **/ 
                //Methode der Werkbank
                
                if(command.getSecondWord.equals("WERKBANK")&&currentRoom.getItemName().contains("WERKBANK")){
                    System.out.println("Du benutzt die WERKBANK. Du kannst hier zwei Items kombinieren.");
                    System.out.println("Welche Gegenstände möchtest du kombinieren?");
                    System.out.println("Gib [GEGENSTAND1] [] [GEGENSTAND2] ein");
                    
                    // Glückbringer und MagischeMiesmuschel bauen
                       if(inventar.contains("KLEEBLATT")&&inventar.contains("HUFEISEN")){
                       if(command.getCommandWord().equals("KLEEBLATT")&&command.getSecondWord().equals("HUFEISEN")){
                            inventar.removeItem("KLEEBLATT");
                            inventar.removeItem("HUFEISEN");
                            inventar.addToInventory("GLÜCKSBRINGER");
                            return "Du hast erhälst GLÜCKSBRINGER gebaut";
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
                            return "Du hast NICHT die richtigen Materialien! Ändere gegebenfalls die Reihenfolge!";
                        }
                    }
                        
                      }
                    }
                }
            }
        } 
    
               
                
            
            
    
