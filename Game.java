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
        Person bettler, duc, prinzessin,  t�rsteher;
        Item rucksack, kleeblatt, hufeisen, notizzettel, herd, werkbank;
        // create the rooms

        eingangshalle = new Room("Du befindest dich in der Eingangshalle",null, null,true);
        schlossgarten = new Room("Du befindest dich im Schlossgarten",null,null,true);
        kueche = new Room("Du befindest dich in der K�che", null,herd = new Item("HERD", true),false);
        schlafzimmer = new Room("Du befindest dich im Schlafzimmer",null,null,true); //Rucksack hinzugef�gt
        badezimmer = new Room("Du befindest dich im Badezimmer",null,null,true);
        keller = new Room("Du befindest dich im Keller", t�rsteher = new Person("T�RSTEHER"),null,true);
        vorratskammer = new Room("Du befindest dich in der Vorratskammer",null,null,false);
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
        rooms[i].addItem("RUCKSACK", false);
        rooms[i].addItem("WASSER", false); 
        rooms[3].addItem("MIESMUSCHEL", false);
        rooms[i].addItem("KLEEBLATT", false);
        rooms[9].addItem("NOTIZZETTEL", false);

        // initialise room exits
        //alle Himmelsrichtungen gro�geschrieben wegen trimUpperCase() in Parser
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
        schlossgarten.setExits("S�DEN", terrasse);
        kueche.setExits("S�DEN", eingangshalle);
        kueche.setExits("OSTEN", schlossgarten);
        flur.setExits("WESTEN", badezimmer);
        flur.setExits("UNTEN" , eingangshalle);
        flur.setExits("OSTEN" , schlafzimmer);
        flur.setExits("OBEN", kickerraum);
        badezimmer.setExits("OSTEN", flur);
        schlafzimmer.setExits("WESTEN", flur);
        kickerraum.setExits("UNTEN", flur);
        zielraum.setExits("S�DEN", schlossgarten);
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
        System.out.println("Du willst schon gehen? Nagut.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Herzlich Willkommen in der World of Duc!!");
        System.out.println("World of Duc wird dich ver�ndern.");  
        System.out.println();
        System.out.println("Du wirst R�tsel l�sen, unglaublichen Gefahren ausgesetzt sein und");
        System.out.println("Schere-Stein-Papier bis zum Umfallen spielen");
        System.out.println("Schreibe [HILFE], wenn du sie ben�tigst.");
        System.out.println();
        System.out.println("Schreibe [NACH] gefolgt von [OSTEN], [WESTEN], [NORDEN],");
        System.out.println("[S�DEN], [OBEN] oder [UNTEN], um einen Raum");
        System.out.println("in angegebener Richtung zu verlassen.");
        System.out.println();
        System.out.println("Schreibe [AUFTRAG], um dir deine aktuelle Mission anzeigen zu lassen.");
        System.out.println();
        System.out.println("Schreibe [NIMM + gew�nschten Gegenstand], um einen Gegenstand aufzusammeln.");
        System.out.println();
        System.out.println("Schreibe [INVENTAR] um dir ein derzeitiges Inventar anzuzeigen.");
        System.out.println();
        System.out.println("Schreibe [UMSCHAUEN] um dir die aktuellen Gegenst�nde im Raum anzeigen zu lassen.");
        System.out.println();
        System.out.println("Schreibe [KARTE], um dir die Karte des aktuellen Stockwerks anzeigen zu lassen.");
        System.out.println();
        System.out.println("Schreibe [BENUTZE] um eine Gegenstand in dem Raum oder im Inventar zu benutzen");
        System.out.println();
        System.out.println("Schreibe [TSCH�SS], wenn du das Spiel beenden m�chtest.");
        System.out.println();
        System.out.println("Tipp: Schaue dich in jedem Raum gr�ndlich um.\n");
        System.out.println(assignment.getCurrentTask());
        System.out.println();
        System.out.println(currentRoom.getDescription());
        System.out.println();
        System.out.print("zur Verf�gung stehende Ausg�nge: ");
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
        //alle commands gro�geschrieben wegen trimUpperCase() in Parser
        if (commandWord.equals("HILFE"))
            result = printHelp();
        else if (commandWord.equals("NACH"))    
            result = goRoom(command);
        else if (commandWord.equals("TSCH�SS"))
            result = quit(command);
        else if (commandWord.equals("AUFTRAG"))
            result = auftrag(command);
        else if (commandWord.equals("BENUTZE"))     
            result = benutzen(command);
        //benutzen hinzugef�gt
        //else if (commandWord.equals("SPRICH"))
        //result = sprechen(command); //sprechen hinzugef�gt*/
        else if (commandWord.equals("NIMM"))        
            result = nehmen(command);
        else if (commandWord.equals("INVENTAR"))
            result = showInventar();
        else if (commandWord.equals("KARTE"))
            result = showMap();
        else if (commandWord.equals("UMSCHAUEN"))
            result = currentRoom.lookAround();
        else if (commandWord.equals("ZUR�CK"))
            result = zur�ck();
        else if(commandWord.equals("KOMBINIERE")){
            if(werkbankInUsage){
                result = kombinieren(command);
            }else {
                result = "Du kannst zur Zeit nichts kombinieren! Benutze daf�r die WERKBANK";
            }}
        else if (commandWord.equals("STEIN")||commandWord.equals("SCHERE")||commandWord.equals("PAPIER"))
            result = "Das spielst im Moment nicht Schere-Stein-Papier!";
        else if (commandWord.equals("ZWEIUNDVIERZIG")){
            System.out.println("HERZLICHEN GL�CKWUNSCH DU HAST GEWONNEN. DU HATTEST SPASS.");
            result = null;
        }

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
        result += "Du hast dich verirrt und bist alleine. Du wanderst\n";
        result += "durch das Schloss.\n";
        result += "\n";
        result += "Deine Kommandos sind:\n";
        result += "[AUFTRAG] [UMSCHAUEN] [KARTE] [ZUR�CK] [TSCH�SS]   \n";
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
            return "Wohin?";
        }
        String result = "";
        //speichert aktuellen Raum lokal
        lastRoom = currentRoom;

        //direction ist zweites eingegebens Wort
        String direction = command.getSecondWord();

        //Raumwechsel wenn m�glich
        Room nextRoom = currentRoom.changeRoom(direction);
        this.currentRoom = nextRoom;

        // wenn nicht m�glich
        if(currentRoom == null){
            result += "Du rennst gegen eine Wand und hast furchtbare Schmerzen.\nDeine Abenteuerlust aber besiegt den Schmerz.\nIn dieser Richtung gibt es keine T�r.\n";
            currentRoom = lastRoom;
            return result;
        }
        if(currentRoom == rooms[9] && !currentRoom.isOpen()){
            result +=  schereSteinPapier();
            return result;
        }
        if(!currentRoom.isOpen()){

            result += " Um diesen Raum betreten zu k�nnen, brauchst du einen K�CHENSCHL�SSEL.\n";
             if(currentRoom == rooms[3] && inventar.contains("K�CHENSCHL�SSEL")){

                result += "Du benutzt den K�CHENSCHL�SSEL und �ffnest die T�r.\n";
                result += currentRoom.getDescription()+"\n" + "\n" + "zur Verf�gung stehende Ausg�nge: " + currentRoom.getExits();
                inventar.removeItem("K�CHENSCHL�SSEL");
                currentRoom.setOpen();
                result += assignment.changeCurrentTask();
                return result;
            }
            currentRoom = lastRoom;
            return result;
        }else{            
            System.out.println();
            result += currentRoom.getDescription()+"\n" + "\n" + "zur Verf�gung stehende Ausg�nge: " + currentRoom.getExits();

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
            return "Was?";
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
                    inventar.addToInventory("GL�CKSBRINGER");

                    werkbankInUsage = false;
                    result += "Du hast einen GL�CKSBRINGER gebaut.";
                    result += assignment.changeCurrentTask();
                    return result;
                
                
            }
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

    private String zur�ck(){
        String result = "";
        currentRoom = lastRoom;
        result += currentRoom.getDescription()+"\n" + "\n" + "zur Verf�gung stehende Ausg�nge: " + currentRoom.getExits();
        return result;
    }

    private String benutzen(Command command){
        String result = "";
        if(!command.hasSecondWord()) {
            return "Was willst du benutzen?";
        }
        else if(command.getSecondWord().equals("GL�CKSBRINGER")&& inventar.contains("GL�CKSBRINGER")){
            engine.setProbability(50);
            inventar.removeItem("GL�CKSBRINGER");
            return "Du hast den GL�CKSBRINGER benutzt und dein Gl�ck im Spiel steigt extrem!!";
        }
        else if(command.getSecondWord().equals("WERKBANK")&& currentRoom.getItemName().contains("WERKBANK")){
            werkbankInUsage = true;
            return "Du benutzt jetzt die WERKBANK. Du kannst hier zwei Items kombinieren.Welche Gegenst�nde m�chtest du kombinieren?\n Gib [KOMBINIERE] [] [GEGENSTAND1+GEGENSTAND2] ein";
        }
        else if(command.getSecondWord().equals("MAGISCHEMIESMUSCHEL")&& inventar.contains("MAGISCHEMIESMUSCHEL")){ 
            boolean muschelInUse = true;  
            muschel = new MagischeMiesmuschel();
            muschel.printWelcome();
            while(muschelInUse == true){
                String input = parser.getCommandAsString();
                muschelInUse = muschel.start(input);
            }
            result ="Du legst die MAGISCHEMIESMUSCHEL zur�ck in dein INVENTAR.";
        }
        else if(command.getSecondWord().equals("NOTIZZETTEL")&& inventar.contains("NOTIZZETTEL")){ 
            result ="Auf dem NOTIZZETTEL stehet: Wenn du mal die Gelgenheit hast etwas zu fragen , frag nach dem Sinn des Lebens!' ";
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
            if(currentRoom.getItemName() == null){
                return "Das gibt es hier nicht!";
            } 
        }
        if(inventar.inventoryFull()){
            result += "Dein RUCKSACK ist schon voll!";
            return result;
        }
        for(int i = 0; i < currentRoom.getNumberOfItems();i++){
            if(currentRoom.getEachItem(i).equals(command.getSecondWord())){
                result += itemIsInRoom(command);
                return result;
            }
        }
        return "Das kann ich nicht einsammeln";

    }

    public String itemIsInRoom(Command command){
        String result = "";
        if(command.getSecondWord().equals("RUCKSACK")){
            backpackFound=true;            
        }
        if(backpackFound){
            if(currentRoom.getFixedItems().contains(command.getSecondWord())){
                result += "Das ist zu schwer f�r dich! Das kannst du nicht aufheben!";
                return result;
            }
                    inventar.addToInventory(command.getSecondWord());
                    result += "Du erh�lst: ";
                    result += command.getSecondWord();
                    if(command.getSecondWord().equals("RUCKSACK")){
                        result+= assignment.changeCurrentTask();
                    }
                    currentRoom.removeItem(command.getSecondWord());
                    return result;
                }
                else  {return "Du brauchst erst einen RUCKSACK!";}

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
        //Wenn in dem Raum, der T�rsteher steht, wird das PaperScissorRockGame gestartet!

        System.out.println("\nVor der Kellert�r steht ein unglaublich breiter T�rsteher.\nHier kommst du nur vorbei, wenn du ihn im Schere-Stein-Papier besiegt!\nSchreibe [Stein],[Schere] oder [Papier] um zu spielen\nUm aufzugeben, schreibe [Mist].");
        result += engine.startPlaying(parser.getCommand());
        //solange man verliert, l�uft das Spiel weiter.
        while(result.contains("verlierst") ||result.contains("Ahnung") ){
            result = "";

            result += engine.startPlaying(parser.getCommand());
        }
        if(result.contains("gewinnst")){
            currentRoom.setOpen();
            result += "\nDu erh�lst K�CHENSCHL�SSEL.";
            inventar.addToInventory("K�CHENSCHL�SSEL");
            result += "\n" + currentRoom.getDescription()+"\n" + "\n" + "zur Verf�gung stehende Ausg�nge: " + currentRoom.getExits();
            result += 
            assignment.changeCurrentTask();

        }

        if(result.contains("Klatsche")){
            currentRoom = lastRoom;
            result += "\n" + currentRoom.getDescription()+"\n" + "\n" + "zur Verf�gung stehende Ausg�nge: " + currentRoom.getExits();
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
                result +="|  K�che   |---| Garten |\n";
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
                result += "|           |   |          |   |            |\n";
                result += "|Vorratsraum|---|  Keller  |---| Werkstatt  |\n";
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

