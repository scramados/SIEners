package controller;

import model.*;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;

public class StudentController implements Handler {
    private PrIS informatieSysteem;
    private Student s;
    private Les l;

    /**
     * De StudentController klasse moet alle student-gerelateerde aanvragen
     * afhandelen. Methode handle() kijkt welke URI is opgevraagd en laat
     * dan de juiste methode het werk doen. Je kunt voor elke nieuwe URI
     * een nieuwe methode schrijven.
     *
     * @param infoSys - het toegangspunt tot het domeinmodel
     */
    public StudentController(PrIS infoSys) {
        informatieSysteem = infoSys;
    }

    public void handle(Conversation conversation) {
        if (conversation.getRequestedURI().startsWith("/student/mijnmedestudenten")) {
            mijnMedestudenten(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/student/mijnLessen")) {
            mijnStudentRooster(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/student/student-AbsentieOpgeven")) {
            studentAbsentieOpgeven(conversation);
        }
        if (conversation.getRequestedURI().startsWith("/student/absentietonen")) {
            toonabsenties(conversation);
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */
    private void mijnMedestudenten(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");

        Student student = informatieSysteem.getStudent(gebruikersnaam);            // Student-object opzoeken
        Klas klas = informatieSysteem.getKlasVanStudent(student);                // klascode van de student opzoeken
        ArrayList<Student> studentenVanKlas = klas.getStudentenKlas();    // medestudenten opzoeken

        JsonArrayBuilder jab = Json.createArrayBuilder();                        // Uiteindelijk gaat er een array...

        for (Student s : studentenVanKlas) {                                    // met daarin voor elke medestudent een JSON-object...
            if (!s.getGebruikersNaam().equals(gebruikersnaam)) {    // behalve de student zelf...
                jab.add(Json.createObjectBuilder()
                        .add("naam", s.getVoorNaam()));
            }

        }

        conversation.sendJSONMessage(jab.build().toString());                    // terug naar de Polymer-GUI!
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */

    private void mijnStudentRooster(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        Student student = informatieSysteem.getStudent(gebruikersnaam);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Klas klas = informatieSysteem.getKlasVanStudent(student);
        ArrayList<Les> lessen = new ArrayList<>();

        for (Les l : informatieSysteem.deLessen) {                          //Zoek de lessen op
            if (l.getKlas().getKlasCode().contains(klas.getKlasCode())) {
                lessen.add(l);
            }
        }

        for (Les l : lessen) {                                              // Maak Array aan...
            jab.add(Json.createObjectBuilder()
                    .add("datum", l.getDateString())
                    .add("begintijd", l.getStartTijdString())
                    .add("eindtijd", l.getEindTijdString())
                    .add("lokaal", l.getLokaal().getLokaalNaam())
                    .add("docent", l.getDocent().getGebruikersNaam())
                    .add("klas", l.getKlas().getKlasCode())
            );
        }

        conversation.sendJSONMessage(jab.build().toString());                    // terug naar de Polymer-GUI!
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan gebruikt om de les en student te vinden zodat hiermee een absentie aangemaakt
     * kan worden.
     * @param conversation - alle informatie over het request
     */

    private void studentAbsentieOpgeven(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        String datum = jsonObjectIn.getString("datum");
        String begintijd = jsonObjectIn.getString("begintijd");
        String eindtijd = jsonObjectIn.getString("eindtijd");

        Student student = informatieSysteem.getStudent(gebruikersnaam);            // Student-object opzoeken

        Klas klas = informatieSysteem.getKlasVanStudent(student);         // klas van de student opzoeken

        for (Les l : informatieSysteem.deLessen) {                         // als de Les is gevonden...
            if (l.getKlas().getKlasCode().contains(klas.getKlasCode()) && l.getDateString().contains(datum)
                    && l.getStartTijdString().contains(begintijd) && l.getEindTijdString().contains(eindtijd)) {
                Absentie absentie = new Absentie(l, student);               // maak absentie aan
                if (!student.getAbsentie().contains(absentie)) {
                    student.addabsentie(absentie);                          // als deze nog niet bestaat in de lijst sla hem hier dan op.
                    try {
                        informatieSysteem.writeAbsentie();                  // Update het objecten bestand.
                    } catch (IOException e) {

                    }
                }
            }
        }
    }

    /**
     * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
     * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
     * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
     *
     * @param conversation - alle informatie over het request
     */

    private void toonabsenties(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        Student student = informatieSysteem.getStudent(gebruikersnaam);            // Student-object opzoeken

        JsonArrayBuilder jab = Json.createArrayBuilder();                        // Uiteindelijk gaat er een array...
        for (Absentie ab : student.getAbsentie()) {
            jab.add(Json.createObjectBuilder()
                    .add("datum", ab.getLes().getDateString())
                    .add("begintijd", ab.getLes().getStartTijdString())
                    .add("eindtijd", ab.getLes().getEindTijdString())
                    .add("lokaal", ab.getLes().getLokaal().getLokaalNaam())
                    .add("docent", ab.getLes().getDocent().getGebruikersNaam())
                    .add("klas", ab.getLes().getKlas().getKlasCode())
            );
        }

        conversation.sendJSONMessage(jab.build().toString());                       // terug naar de Polymer-GUI!
    }
}
