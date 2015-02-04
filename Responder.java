import java.util.*;
/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response to an input string.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.1 (2011.07.31)
 */
public class Responder
{
    private Random randomGenerator;
    private ArrayList<String> responses;
    public HashMap<String, String> answers;
    /**
     * Construct a Responder - nothing to do
     */
    public Responder()
    {
        randomGenerator = new Random();
        responses = new ArrayList<String>();
        fillResponses();
        answers = new HashMap<String, String>();
        fillAnswers();
    }

    /**
     * Generate a response.
     * @return   A string that should be displayed as the response
     */
    public void fillResponses()
    {
        responses.add("Sieht nicht danach aus");
        responses.add("Heute nicht");
        responses.add("Sehr unwahrscheinlich");
        responses.add("Diese Frage ist nicht so leicht zu beantworten");
        responses.add("Nicht möglich");
        responses.add("Nicht möglich");
        responses.add("Was sonst?");
        responses.add("Na, aber hallo?");
        responses.add("Eines Tages vielleicht");
        responses.add("Möglich");
        responses.add("Frag' jemand anderen!");
        responses.add("Aber sicher!");
        responses.add("Vielleicht");
        responses.add("Was willst du denn?");
        responses.add("Ja - wann immer du willst");
        responses.add("Eher nicht");
        responses.add("Wenn nicht jetzt? Wann dann?");
        responses.add("Frag noch mal in einer Stunde");
        responses.add("Wenn du so weiter machst trennen sich unsere Wege!");
        responses.add("Um Gottes Willen ... nein!");
        responses.add("Gute Entscheidung");
        responses.add("Absolut");
        responses.add("Definitiv");
        responses.add("Willst du mich auf den Arm nehmen?");
        responses.add("Auf gar keinen Fall");
        responses.add("Klar");
        responses.add("Ja");
        responses.add("Nein");
        responses.add("Kein Zweifel");
        responses.add("Das würde ich auf jeden Fall sagen");
        responses.add("Sowas von unangebracht!");
        responses.add("Willst du Ärger?");
        responses.add("Was für eine seltsame Frage");
        responses.add("Kein Kommentar");
        responses.add("Die Frage ist belanglos");
        responses.add("Irgenwann vielleicht einmal");
        responses.add("Natürlich");
        responses.add("Das klingt spitze!");
        responses.add("Hör auf zu fragen!");
        responses.add("Das liegt doch auf der Hand!");
        responses.add("Lieber nicht");
        responses.add("Theoretisch schon");
        responses.add("Mit Verlaub!");
        responses.add("Freilich!");
        responses.add("Das glaubst du doch selbst nicht?");
        responses.add("Eine weise Wahl!");
        responses.add("Frag doch einfach nochmal?");
        responses.add("Totaler Blödsinn!");
        responses.add("Geniale Idee!");
        responses.add("Das ist aber jetzt nicht dein Ernst?");
        responses.add("unwahrscheinlich");
        responses.add("Ähh.. was?");
        responses.add("Ja bitte");
        responses.add("Das klingt sehr vernünftig");
        responses.add("Diesmal nicht");

    }

    public void fillAnswers()
    {
        answers.put("Was ist der Sinn des Lebens?", "Moment ich rechne... 'Zweiundvierzig' ist die Antwort");
    }

    public String generateRandomResponse(){

        int index = randomGenerator.nextInt(responses.size());
        return responses.get(index);
    }

    public String generateResponse(String word){

        String response = answers.get(word);

        if(response != null) {
            return response;
        }

        return 

        generateRandomResponse();
    }
}

