/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room[] rooms;
    private boolean backpackFound;
    private Assignments assignment;
    private Inventory Inventar;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new Room[12];
       backpackFound = false;
        createRooms();
        parser = new Parser();    
        assignment = new Assignments();
        Inventar = new Inventory(); // NEU NEU
        
    }

    /**
     * Create all the rooms and link their exits together.
     */

    public void createRooms()
    {
        Room eingangshalle, schlossgarten, kueche, terrasse, schlafzimmer,flur, badezimmer, keller, vorratskammer, kickerraum, werkstatt, zielraum;

        // create the rooms
        eingangshalle = new Room("Du befindest dich in der Eingangshalle",null,null);
        schlossgarten = new Room("Du befindest dich im Schlossgarten","Tom",null);
        kueche = new Room("Du befindest dich in der Küche",null,"Brot");
        schlafzimmer = new Room("Du befindest dich im Schlafzimmer",null,"Rucksack"); //Rucksack hinzugefügt
        badezimmer = new Room("Du befindest dich im Badezimmer",null,null);
        keller = new Room("Du befindest dich im Keller",null,null);
        vorratskammer = new Room("Du befindest dich in der Vorratskammer",null,null);
        kickerraum = new Room("Du befindest dich im Kickerraum",null,null);
        flur = new Room("Du befindest dich im Flur in der ersten Etage",null,null);
        zielraum = new Room("Du befindest dich im Zielraum",null,null);
        werkstatt = new Room("Du befindest dich in der Werkstatt",null,null);
        terrasse = new Room("Du befindest dich auf der Terrasse",null,null);

        rooms[0] = eingangshalle;
        rooms[1] = terrasse;
        rooms[2] = schlossgarten;
        rooms[3] = kueche;
        rooms[4] = flur;
        rooms[5] = badezimmer;
        rooms[6] = schlafzimmer;
        rooms[7] = keller;
        rooms[8] = werkstatt;
        rooms[9] = vorratskammer;
        rooms[10] = kickerraum;
        rooms[11] = zielraum;

        // initialise room exits
        eingangshalle.setExits( "Osten", terrasse );
        eingangshalle.setExits( "Norden", kueche );
        eingangshalle.setExits( "Oben", flur );
        eingangshalle.setExits( "Unten", keller );
        keller.setExits("Oben", eingangshalle);
        keller.setExits("Westen", vorratskammer);
        keller.setExits("Osten", werkstatt);
        vorratskammer.setExits("Osten", keller);
        werkstatt.setExits("Westen",keller);
        terrasse.setExits("Westen", eingangshalle);
        terrasse.setExits("Norden", schlossgarten);
        schlossgarten.setExits("Westen", kueche);
        schlossgarten.setExits("Norden", zielraum);
        schlossgarten.setExits("Süden", terrasse);
        kueche.setExits("Süden", eingangshalle);
        kueche.setExits("Osten", schlossgarten);
        flur.setExits("Westen", badezimmer);
        flur.setExits("Unten" , eingangshalle);
        flur.setExits("Osten" , schlafzimmer);
        flur.setExits("Oben", kickerraum);
        badezimmer.setExits("Osten", flur);
        schlafzimmer.setExits("Westen", flur);
        kickerraum.setExits("Unten", flur);
        zielraum.setExits("Süden", schlossgarten);
        currentRoom = eingangshalle;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            { 
                System.out.println(output);
            }

        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Herzlich Willkommen in der World of Duc!!");
        System.out.println("World of Duc wird dich verändern.");  
        System.out.println();
        System.out.println("Du wirst Rätsel lösen, unglaublichen Gefahren ausgesetzt sein,");
        System.out.println("Schere-Stein-Papier bis zum Umfallen spielen");
        System.out.println("und deine Kickerskills ins Unendliche verbessern!");
        System.out.println();
        System.out.println("Schreibe [Hilfe], wenn du sie benötigst.");
        System.out.println();
        System.out.println("Schreibe [nach] gefolgt von [Osten], [Westen], [Norden],");
        System.out.println("[Süden], [Oben] oder [Unten], um einen Raum");
        System.out.println("in angegebener Richtung zu verlassen");
        System.out.println();
        System.out.println("Schreibe [Auftrag], um dir deine aktuelle Mission anzeigen zu lassen");
        System.out.println();
        System.out.println("Schreibe [], um mit einer Person zu reden.");
        System.out.println();
        System.out.println("Schreibe [nimm + gewünschten Gegenstand], um einen Gegenstand aufzusammeln");
        System.out.println();
        System.out.println("Schreibe [Inventar] um dir ein derzeitges Inventar anzuzeigen.");
        System.out.println();
        System.out.println("Schreibe [Tschüss], wenn du das Spiel beenden möchtest.");
        System.out.println();
        System.out.println(assignment.getCurrentTask());
        System.out.println();
        System.out.println(currentRoom.getDescription());
        System.out.println();
        System.out.print("zur Verfügung stehende Ausgänge: ");
        System.out.println(currentRoom.getExits());

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public String processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            return "I don't know what you mean...";
        }

        String commandWord = command.getCommandWord();
        String result = null;
        if (commandWord.equals("Hilfe"))
            result = printHelp();
        else if (commandWord.equals("nach"))
            result = goRoom(command);
        else if (commandWord.equals("Tschüss"))
            result = quit(command);
        else if (commandWord.equals("Auftrag"))
            result = auftrag(command);
        else if (commandWord.equals("sprich"))
            result = sprechen(command);
        else if (commandWord.equals("nimm"))
            result = nehmen(command);
       else if (commandWord.equals("Inventar"))
            result = showInventar();
        return result;

    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private String printHelp() 
    {   String result = "";
        result += "You are lost. You are alone. You wander\n";
        result += "around at the university.\n";
        result += "\n";
        result += "Your command words are:\n";
        result += "   go quit help\n";
        return result;
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }
        String result = "";
        //speichert akutellen Raum lokal
        Room lastRoom = currentRoom;

        //direction ist zweites eingegebens Wort
        String direction = command.getSecondWord();

        //Raumwechsel wenn möglich
        Room nextRoom = currentRoom.changeRoom(direction);
        this.currentRoom = nextRoom;
        // wenn nicht möglich
        if(currentRoom == null){
            result += "Du rennst gegen eine Wand";
            currentRoom = lastRoom;
            return result;
        }else{            
            System.out.println();
            result += currentRoom.getDescription()+"\n" + "\n" + "zur Verfügung stehende Ausgänge: " + currentRoom.getExits();
            
            return result;
        }
        
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return null, if this command quits the game, something else to output otherwise.
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
    
    private String auftrag(Command command){
        String result = assignment.getCurrentTask();
        return result;
    }

    private String sprechen(Command command){
        String result = currentRoom.getAnswerOfThePersonInThisRoom();
        return result;
    }
    
    private String nehmen(Command command){
            if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Was soll ich nehmen?";
           }   
            else{
                if(currentRoom.getItemName() == null){return "Das gibt es hier nicht!";}
                else if(currentRoom.getItemName().equals(command.getSecondWord())){
                    if(command.getSecondWord().equals("Rucksack")){
                        backpackFound=true;
                    }
                    if(backpackFound){
                        String result = "Du erhälst ";
                        result += command.getSecondWord();
                        Inventar.addToInventory(command.getSecondWord());
                        currentRoom.removeItem();
                        return result;
                    }
                    else  {return "Du brauchst erst einen Rucksack!";}
                   
                }
                else{return "Das kann ich nicht einsammeln";}
                
                }
    }
    
    private String showInventar(){
     return Inventar.showInventory();
    }
    
    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }

}
