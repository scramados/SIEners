package controller;

import model.*;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class DocentController implements Handler {
    private PrIS informatieSysteem;

    /**
     * De DocentController klasse moet alle docent-gerelateerde aanvragen
     * afhandelen. Methode handle() kijkt welke URI is opgevraagd en laat
     * dan de juiste methode het werk doen. Je kunt voor elke nieuwe URI
     * een nieuwe methode schrijven.
     *
     * @param infoSys - het toegangspunt tot het domeinmodel
     */
    public DocentController(PrIS infoSys) {
        informatieSysteem = infoSys;
    }

    public void handle(Conversation conversation) {
        if (conversation.getRequestedURI().startsWith("/docent/mijnvakken")) {
            mijnVakken(conversation);
        }

        if (conversation.getRequestedURI().startsWith("/docent/mijnRooster")) {
            mijnLessen(conversation);
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */
    private void mijnVakken(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");

        Docent docent = informatieSysteem.getDocent(gebruikersnaam);    // Docent-object ophalen!
        ArrayList<Vak> vakken = docent.getVakken();                        // Vakken van de docent ophalen!

        JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...

        for (Vak v : vakken) {
            jab.add(Json.createObjectBuilder()                            // daarin voor elk vak een JSON-object...
                    .add("vakcode", v.getVakCode())
                    .add("vaknaam", v.getVakNaam()));

        }

        conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
    }

    private void mijnLessen(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");

        Docent docent = informatieSysteem.getDocent(gebruikersnaam);    // Docent-object ophalen!
        Rooster mijnRooster = informatieSysteem.getDocent(gebruikersnaam).getRooster();
        ArrayList<Les> deLessen = mijnRooster.getLessen();                        // Vakken van de docent ophalen!

        JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...

        for (Les l : deLessen) {
            jab.add(Json.createObjectBuilder()                            // daarin voor elk vak een JSON-object...
                    .add("datum",l.getDate())
                    .add("tijd", l.getStartTijd()));

        }

        conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
    }
}
