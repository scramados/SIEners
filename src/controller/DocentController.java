package controller;

import model.*;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.io.IOException;
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
        if (conversation.getRequestedURI().startsWith("/docent/mijnVakken")) {
            mijnVakken(conversation);
        }

        if (conversation.getRequestedURI().startsWith("/docent/mijnRooster")) {
            mijnLessen(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/docent/studentabsenties")) {
            stdab(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/docent/absverwijderen")) {
            absverwijderen(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/docent/toonKlasAbs")) {
            toonKlasAbs(conversation);
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

        JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
        ArrayList<Vak> vakken = new ArrayList<>();
        vakken = informatieSysteem.getVakkenDocent(docent);

        for (Vak v : vakken) {
            jab.add(Json.createObjectBuilder()
                    .add("vaknaam", v.getVakNaam())
                    .add("vakcode", v.getVakCode()));
        }

        conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan gebruikt om de juiste absentie te vinden en te verwijderen.
     *
     * @param conversation - alle informatie over het request
     */

    private void absverwijderen(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        String stdnr = jsonObjectIn.getString("stdnr");
        String datum = jsonObjectIn.getString("datum");
        String begintijd = jsonObjectIn.getString("begintijd");
        String eindtijd = jsonObjectIn.getString("eindtijd");
        System.out.println(datum + begintijd + eindtijd);

        Student student = informatieSysteem.getStudent(stdnr);              // Student-object opzoeken

        Klas klas = informatieSysteem.getKlasVanStudent(student);           // klas van de student opzoeken

        try  { //Hier gaan we absentie verwijderen wanneer er een studentnummer is opgegeven
            Les les = null;
            for (Les l : informatieSysteem.deLessen) {                      //zoek naar de juiste les
                if (l.getKlas().getKlasCode().contains(klas.getKlasCode()) && l.getDateString().contains(datum)
                        && l.getStartTijdString().contains(begintijd) && l.getEindTijdString().contains(eindtijd)) {
                    les = l;
                }
                for (int i = 0; i < student.getAbsentie().size(); i++) {    //zoek naar de absentie die verwijdert moet worden.
                    if (student.getAbsentie().get(i).getLes().equals(les)) {
                        Absentie ab = student.getAbsentie().get(i);
                        student.removeabsentie(ab);
                        try{ //update het objecten bestand.
                            informatieSysteem.writeAbsentie();
                        }catch(IOException e){

                        }
                    }
                }
            }

        } catch (NullPointerException e) {
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */

    private void stdab(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        String stdnr = jsonObjectIn.getString("stdnr");

        try { //Student absenties ophalen
            Student student = informatieSysteem.getStudent(stdnr);
            JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
            for (Absentie ab : student.getAbsentie()) {                        // Uiteindelijk gaat er een array...
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
        }
    }

    private void toonKlasAbs(Conversation conversation){
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String klascode = jsonObjectIn.getString("klascode");

        try {
            ArrayList<Absentie> absenties = null;
            Klas klas = null;
            JsonArrayBuilder jab = Json.createArrayBuilder();                // En uiteindelijk gaat er een JSON-array met...
            for (Klas kl : informatieSysteem.deKlassen) {                   // zoek de juiste klas
                if (kl.getKlasCode().contains(klascode)) {
                    klas = kl;
                }
            }
            for (Les l : informatieSysteem.deLessen) {                      // zoek de juiste les
                if (l.getKlas().equals(klas)) {
                    absenties = informatieSysteem.getAbsentiesLes(l);
                }
            }
            for (Absentie ab : absenties) {                                  // Uiteindelijk gaat er een array...
                jab.add(Json.createObjectBuilder()
                        .add("datum", ab.getLes().getDateString())
                        .add("begintijd", ab.getLes().getStartTijdString())
                        .add("eindtijd", ab.getLes().getEindTijdString())
                        //.add("lokaal", l.getLokaal().getLokaalNaam())
                        .add("docent", ab.getLes().getDocent().getGebruikersNaam())
                        .add("klas", ab.getLes().getKlas().getKlasCode())
                        .add("student", ab.getStudent().getGebruikersNaam())
                );
            }

            conversation.sendJSONMessage(jab.build().toString());            // terug naar de Polymer-GUI!

        } catch (NullPointerException error) {
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */

    private void mijnLessen(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");

        ArrayList<Les> deLessen = informatieSysteem.deLessen;                       // Vakken van de docent ophalen!

        JsonArrayBuilder jab = Json.createArrayBuilder();                           // En uiteindelijk gaat er een JSON-array met...
        System.out.println(informatieSysteem.deVakken.toString());
        for (Les l : deLessen) {
            if (l.getDocent().getGebruikersNaam().contains(gebruikersnaam)) {       // Zoek naar de docent.
                jab.add(Json.createObjectBuilder()                                  // En maak Array aan.
                        .add("datum", l.getDateString())
                        .add("begintijd", l.getStartTijdString())
                        .add("eindtijd", l.getEindTijdString())
                        .add("lokaal", l.getLokaal().getLokaalNaam())
                        .add("docent", l.getDocent().getGebruikersNaam())
                        .add("klas", l.getKlas().getKlasCode())
                        .add("vak", l.getVak().getVakCode())
                );
            }
        }

        conversation.sendJSONMessage(jab.build().toString());                       // terug naar de Polymer-GUI!
    }
}
