import java.util.*;
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
    private Inventory inventar;
    private Room lastRoom;
    private Random rand;
    private PaperScissorRockEngine engine;
    public boolean werkbankInUsage;
    private MagischeMiesmuschel muschel;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new Room[12];
        backpackFound = false;
        rand = new Random();
        createRooms();
        parser = new Parser();    
        assignment = new Assignments();
        inventar = new Inventory();
        werkbankInUsage = false;
        engine = new PaperScissorRockEngine();
       

    }

    public void createRooms()
    {
        Room eingangshalle, schlossgarten, kueche, terrasse, schlafzimmer,flur, badezimmer, keller, vorratskammer, kickerraum, werkstatt, zielraum;
        Person bettler, duc, prinzessin,  türsteher;
        Item rucksack, kleeblatt, hufeisen, gepökelterSchweinerücken, herd, werkbank;
        // create the rooms

        eingangshalle = new Room("Du befindest dich in der Eingangshalle",null, null,true);
        schlossgarten = new Room("Du befindest dich im Schlossgarten",null,kleeblatt = new Item("KLEEBLATT", false),true);
        kueche = new Room("Du befindest dich in der Küche", null,herd = new Item("HERD", true),false);
        schlafzimmer = new Room("Du befindest dich im Schlafzimmer",null,null,true); //Rucksack hinzugefügt
        badezimmer = new Room("Du befindest dich im Badezimmer",null,hufeisen = new Item("HUFEISEN", false),true);
        keller = new Room("Du befindest dich im Keller", türsteher = new Person("Türsteher"),null,true);
        vorratskammer = new Room("Du befindest dich in der Vorratskammer",bettler = new Person("BETTLER"),gepökelterSchweinerücken = new Item("GEPÖKELTER SCHWEINERÜCKEN", false),false);
        kickerraum = new Room("Du befindest dich im Kickerraum",duc = new Person("DUC DER KICKERMAN"),null,false);
        flur = new Room("Du befindest dich im Flur in der ersten Etage",null,null,true);
        zielraum = new Room("Du befindest dich im Zielraum",prinzessin = new Person("PRINZESSIN"),null,false);
        werkstatt = new Room("Du befindest dich in der Werkstatt", null,werkbank = new Item("WERKBANK", true),true);
        terrasse = new Room("Du befindest dich auf der Terrasse",null,null,true);

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

        int i = rand.nextInt(9);
        while(i == 3){
            i = rand.nextInt(9);
        }
        rooms[0].addItem("RUCKSACK", false);
        rooms[0].addItem("WASSER", false); 
        rooms[0].addItem("MIESMUSCHEL", false);
      

        // initialise room exits
        //alle Himmelsrichtungen großgeschrieben wegen trimUpperCase() in Parser
        eingangshalle.setExits( "OSTEN", terrasse );
        eingangshalle.setExits( "NORDEN", kueche );
        eingangshalle.setExits( "OBEN", flur );
        eingangshalle.setExits( "UNTEN", keller );
        keller.setExits("OBEN", eingangshalle);
        keller.setExits("WESTEN", vorratskammer);
        keller.setExits("OSTEN", werkstatt);
        vorratskammer.setExits("OSTEN", keller);
        werkstatt.setExits("WESTEN",keller);
        terrasse.setExits("WESTEN", eingangshalle);
        terrasse.setExits("NORDEN", schlossgarten);
        schlossgarten.setExits("WESTEN", kueche);
        schlossgarten.setExits("NORDEN", zielraum);
        schlossgarten.setExits("SÜDEN", terrasse);
        kueche.setExits("SÜDEN", eingangshalle);
        kueche.setExits("OSTEN", schlossgarten);
        flur.setExits("WESTEN", badezimmer);
        flur.setExits("UNTEN" , eingangshalle);
        flur.setExits("OSTEN" , schlafzimmer);
        flur.setExits("OBEN", kickerraum);
        badezimmer.setExits("OSTEN", flur);
        schlafzimmer.setExits("WESTEN", flur);
        kickerraum.setExits("UNTEN", flur);
        zielraum.setExits("SÜDEN", schlossgarten);
        currentRoom = eingangshalle; 
        lastRoom = eingangshalle;// start game outside
    }

    /**
     * Create all the rooms and link their exits together.
     */

    public String getcurrentRoomPerson(){
        return currentRoom.getPersonName();
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
        System.out.println("in angegebener Richtung zu verlassen.");
        System.out.println();
        System.out.println("Schreibe [Auftrag], um dir deine aktuelle Mission anzeigen zu lassen.");
        System.out.println();
        System.out.println("Schreibe [], um mit einer Person zu reden.");
        System.out.println();
        System.out.println("Schreibe [nimm + gewünschten Gegenstand], um einen Gegenstand aufzusammeln.");
        System.out.println();
        System.out.println("Schreibe [Inventar] um dir ein derzeitges Inventar anzuzeigen.");
        System.out.println();
        System.out.println("Schreibe [Tschüss], wenn du das Spiel beenden möchtest.");
        System.out.println();
        System.out.println("Schreibe [Karte], um dir die Karte des aktuellen Stockwerks anzeigen zu lassen.");
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
            return "Keine Ahnung was du meinst?!";
        }

        String commandWord = command.getCommandWord();
        String result = null;
        //alle commands großgeschrieben wegen trimUpperCase() in Parser
        if (commandWord.equals("HILFE"))
            result = printHelp();
        else if (commandWord.equals("NACH"))    
            result = goRoom(command);
        else if (commandWord.equals("TSCHÜSS"))
            result = quit(command);
        else if (commandWord.equals("AUFTRAG"))
            result = auftrag(command);
        else if (commandWord.equals("BENUTZE"))     
            result = benutzen(command);
        //benutzen hinzugefügt
        //else if (commandWord.equals("SPRICH"))
        //result = sprechen(command); //sprechen hinzugefügt*/
        else if (commandWord.equals("NIMM"))        
            result = nehmen(command);
        else if (commandWord.equals("INVENTAR"))
            result = showInventar();
        else if (commandWord.equals("KARTE"))
            result = showMap();
        else if (commandWord.equals("UMSCHAUEN"))
            result = currentRoom.lookAround();
        else if (commandWord.equals("ZURÜCK"))
            result = zurück();
        else if(commandWord.equals("KOMBINIERE")){
            if(werkbankInUsage){
                result = kombinieren(command);
            }else {
                result = "Du kannst zur Zeit nichts kombinieren! Benutze dafür die WERKBANK";
            }}
        else if (commandWord.equals("STEIN")||commandWord.equals("SCHERE")||commandWord.equals("PAPIER"))
            result = "Das spielst im Moment nicht Schere Stein Papier!";
        
        
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
        //speichert aktuellen Raum lokal
        lastRoom = currentRoom;

        //direction ist zweites eingegebens Wort
        String direction = command.getSecondWord();

        //Raumwechsel wenn möglich
        Room nextRoom = currentRoom.changeRoom(direction);
        this.currentRoom = nextRoom;

        // wenn nicht möglich
        if(currentRoom == null){
            result += "Du rennst gegen eine Wand und hast furchtbare Schmerzen.\nDeine Abenteuerlust aber besiegt den Schmerz.\nIn dieser Richtung gibt es keine Tür.\n";
            currentRoom = lastRoom;
            return result;
        }
        if(currentRoom == rooms[9] && !currentRoom.isOpen()){
            result +=  schereSteinPapier();
            return result;
        }
        if(!currentRoom.isOpen()){
            result += " Um diesen Raum betreten zu können, brauchst du einen Schlüssel.\n";
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

    private String kombinieren(Command command){
        String result = "";

        String commandAsString = command.getSecondWord();
        if(commandAsString == null){
            return "Was willst du kombinieren?";
        }

        if(commandAsString.contains("WASSER")&& commandAsString.contains("MIESMUSCHEL")){
            
            if(inventar.contains("WASSER") && inventar.contains("MIESMUSCHEL")){

                inventar.removeItem("WASSER"); 
                inventar.removeItem("MIESMUSCHEL"); 
                inventar.addToInventory("MAGISCHEMIESMUSCHEL");
                werkbankInUsage = false;
                return "Du hast einen MAGISCHEMIESMUSCHEL gebaut.";
            }else{
                werkbankInUsage = false;
                return "Du hast NICHT die richtigen Materialien!";
            }
            
        }
        
        else if(commandAsString.contains("HUFEISEN")&& commandAsString.contains("KLEEBLATT")){
            
             if(inventar.contains("KLEEBLATT") && inventar.contains("HUFEISEN")){

                    inventar.removeItem("HUFEISEN"); 
                    inventar.removeItem("KLEEBLATT"); 
                    inventar.addToInventory("GLÜCKSBRINGER");
                    werkbankInUsage = false;
                    return "Du hast einen GLÜCKSBRINGER gebaut.";}
                    else{
                    werkbankInUsage = false;
                    return "Du hast NICHT die richtigen Materialien!";
                }
                
        }
        
        else{
            werkbankInUsage = false;
            return "Das kannst du nicht kombinieren!";
        }
    }

    private String zurück(){
        String result = "";
        currentRoom = lastRoom;
        result += currentRoom.getDescription()+"\n" + "\n" + "zur Verfügung stehende Ausgänge: " + currentRoom.getExits();
        return result;
    }

    private String benutzen(Command command){
        String result = "";
        if(!command.hasSecondWord()) {
            return "Was willst du benutzen?";
        }
        else if(command.getSecondWord().equals("GLÜCKSBRINGER")&& inventar.contains("GLÜCKSBRINGER")){
            engine.setProbability(50);
            inventar.removeItem("GLÜCKSBRINGER");
            return "Du hast den Glücksbringer benutzt und dein Glück im Spiel steig extrem!!";
        }
        else if(command.getSecondWord().equals("WERKBANK")&& currentRoom.getItemName().contains("WERKBANK")){
            werkbankInUsage = true;
            return "Du benutzt jetzt die WERKBANK. Du kannst hier zwei Items kombinieren.Welche Gegenstände möchtest du kombinieren?\n Gib [KOMBINIERE] [] [GEGENSTAND1+GEGENSTAND2] ein";
        }
        else if(command.getSecondWord().equals("MAGISCHEMIESMUSCHEL")&& inventar.contains("MAGISCHEMIESMUSCHEL")){ 
             boolean muschelInUse = true;  
             muschel = new MagischeMiesmuschel();
             muschel.printWelcome();
             while(muschelInUse == true){
             String input = parser.getCommandAsString();
             muschelInUse = muschel.start(input);
            }
            result ="Du legst die Muschel zurück in dein Inventar.";
        }
        else{result = "Das kannst du hier nicht benutzen!";}

        return result;
    }

    private String nehmen(Command command){
        String result = "";
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Was soll ich nehmen?";
        }   
        else{
            if(currentRoom.getItemName() == null){return "Das gibt es hier nicht!";}
            else if(currentRoom.getItemName().contains(command.getSecondWord())){
                if(command.getSecondWord().equals("RUCKSACK")){
                    backpackFound=true;
                }
                if(backpackFound){
                    if(currentRoom.getFixedItems().contains(command.getSecondWord())){
                        result += "Das ist zu schwer für dich! Das kannst du nicht aufheben!";
                        return result;
                    }
                    result = "Du erhälst ";
                    result += command.getSecondWord();
                    inventar.addToInventory(command.getSecondWord());
                    currentRoom.removeItem(command.getSecondWord());
                    return result;
                }
                else  {return "Du brauchst erst einen Rucksack!";}

            }
            else{return "Das kann ich nicht einsammeln";}

        }
    }

    private String showInventar(){
        return inventar.showInventory();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }

    public String schereSteinPapier(){
        String result = ""; 
        //Wenn in dem Raum, der Türsteher steht, wird das PaperScissorRockGame gestartet!

        System.out.println("\nVor der Kellertür steht ein unglaublich breiter Türsteher.\nHier kommst du nur vorbei, wenn du ihn im Schere-Stein-Papier besiegt!\nSchreibe [Stein],[Schere] oder [Papier] um zu spielen\nUm aufzugeben, schreibe [Mist].");
        result += engine.startPlaying(parser.getCommand());
        //solange man verliert, läuft das Spiel weiter.
        while(result.contains("verlierst") ||result.contains("Ahnung") ){
            result = "";

            result += engine.startPlaying(parser.getCommand());
        }
        if(result.contains("gewinnst")){
            currentRoom.setOpen();
            result += "\n" + currentRoom.getDescription()+"\n" + "\n" + "zur Verfügung stehende Ausgänge: " + currentRoom.getExits();
            inventar.addToInventory("SCHLÜSSEL");
        }

        if(result.contains("Klatsche")){
            currentRoom = lastRoom;
            result += "\n" + currentRoom.getDescription()+"\n" + "\n" + "zur Verfügung stehende Ausgänge: " + currentRoom.getExits();
        }
        return result;
    }

    public String showMap(){
        String result = "";
        for(int i = 0; i < 4; i++){
            if(currentRoom == rooms[i]){
                result +="Erdgeschoss     T = Treppe\n";               
                result +=" __________     ________\n";
                result +="|          |   |        |\n";
                result +="|  Küche   |---| Garten |\n";
                result +="|__________|   |________|\n";
                result +=" _____|____     ____|___\n";
                result +="|          |   |        |\n";
                result +="| Eingang  |---|Terasse |\n";
                result +="|_T________|   |________|\n";
                return result;
            }
        }

        for(int i = 7; i <10; i++){
            if (currentRoom == rooms[i]){
                result += "Kellergeschoss     T = Treppe\n";

                result += " ___________     ___________    ____________\n";
                result += "| Werkstatt |   |          |   |            |\n";
                result += "|           |---|   Keller |---|Vorratsraum |\n";
                result += "|___________|   |_T________|   |____________|\n";
                return result;
            }
        }

        for(int i = 4; i < 7; i++){
            if(currentRoom == rooms[i]){
                result += "1. Etage     T = Treppe\n";               
                result += " ___________     ___________     ____________\n";
                result += "|           |   |           |   |            |\n";
                result += "|   Bad     |---|   Flur    |---|Schlafzimmer|\n";
                result += "|___________|   |_T_________|   |____________|\n";
                return result;
            }

        }
        for (int i = 10; i < 12; i++){
            if(currentRoom == rooms[i]){
                result += "Turmgeschoss     T = Treppe\n";               
                result += " ___________     ____________\n";
                result += "|           |   |           |\n";
                result += "|Kickerraum |---|  Zielturm |\n";
                result += "|_T_________|   |___________|\n";
                return result;
            }

        }

        return result;
    }
}

