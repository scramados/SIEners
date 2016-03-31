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
//        if (conversation.getRequestedURI().startsWith("/docent/mijnVakken")) {
//            mijnVakken(conversation);
//        }

        if (conversation.getRequestedURI().startsWith("/docent/mijnRooster")) {
            mijnLessen(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/docent/studentabsenties")) {
            stdab(conversation);
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */
//    private void mijnVakken(Conversation conversation) {
//        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
//        String gebruikersnaam = jsonObjectIn.getString("username");
//
//        Docent docent = informatieSysteem.getDocent(gebruikersnaam);    // Docent-object ophalen!
//
//        JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
//        ArrayList<Vak> vakken = new ArrayList<>();
//        vakken = informatieSysteem.getVakkenDocent(docent);
//        for (Vak v : informatieSysteem.deVakken) {
//            if (vakken.contains(docent.getVakken())) {
//                vakken.add(v);
//            }
//        }
//        for (Vak v : vakken) {
//            jab.add(Json.createObjectBuilder()
//                    .add("vaknaam", v.getVakNaam())
//                    .add("vakcode", v.getVakCode()));
//        }
//
//        conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
//    }

    private void stdab(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        String stdnr = jsonObjectIn.getString("stdnr");
        Docent docent = informatieSysteem.getDocent(gebruikersnaam);    // Docent-object ophalen!

        try {
            Student student = informatieSysteem.getStudent(stdnr);
            JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
            // Uiteindelijk gaat er een array...
            for (Absentie ab : student.getAbsentie()) {
                jab.add(Json.createObjectBuilder()
                        .add("datum", ab.getLes().getDateString())
                        .add("begintijd", ab.getLes().getStartTijdString())
                        .add("eindtijd", ab.getLes().getEindTijdString())
                        //.add("lokaal", l.getLokaal().getLokaalNaam())
                        .add("docent", ab.getLes().getDocent().getGebruikersNaam())
                        .add("klas", ab.getLes().getKlas().getKlasCode())
                );
            }
            conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
        } catch (NullPointerException error) {
            //System.out.println(error.getMessage());
        }
    }

    private void mijnLessen(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");

        Docent docent = informatieSysteem.getDocent(gebruikersnaam);    // Docent-object ophalen!
        Rooster mijnRooster = informatieSysteem.getDocent(gebruikersnaam).getRooster();
        ArrayList<Les> deLessen = informatieSysteem.deLessen;                        // Vakken van de docent ophalen!

        JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
        System.out.println(informatieSysteem.deVakken.toString());
        for (Les l : deLessen) {
            if (l.getDocent().getGebruikersNaam().contains(gebruikersnaam)) {
                jab.add(Json.createObjectBuilder()
                        .add("datum", l.getDateString())
                        .add("begintijd", l.getStartTijdString())
                        .add("eindtijd", l.getEindTijdString())
                        //.add("lokaal", l.getLokaal().getLokaalNaam())
                        .add("docent", l.getDocent().getGebruikersNaam())
                        //.add("klas", l.getKlas().getKlasCode())
//                        .add("vak", docent.getVakken().toString())
                );
            }
        }

        conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
    }
}
