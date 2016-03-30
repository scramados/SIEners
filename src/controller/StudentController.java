package controller;

import model.PrIS;
import model.Student;
import server.Conversation;
import server.Handler;
import model.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

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

    private void mijnStudentRooster(Conversation conversation) {
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        Student student = informatieSysteem.getStudent(gebruikersnaam);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Klas klas = informatieSysteem.getKlasVanStudent(student);
        ArrayList<Les> lessen = new ArrayList<>();
        //System.out.println(informatieSysteem.deLessen);
        for (Les l : informatieSysteem.deLessen) {


            if (l.getKlas().getKlasCode().contains(klas.getKlasCode())) {
                lessen.add(l);
            }
        }



        for(Les l : lessen){
            jab.add(Json.createObjectBuilder()
                    .add("datum", l.getDateString())
                    .add("begintijd", l.getStartTijdString())
                    .add("eindtijd", l.getEindTijdString())
                    //.add("lokaal", l.getLokaal().getLokaalNaam())
                    .add("docent", l.getDocent().getGebruikersNaam())
                    .add("klas", l.getKlas().getKlasCode())
                    );
        }

        conversation.sendJSONMessage(jab.build().toString());                    // terug naar de Polymer-GUI!
    }
    private void studentAbsentieOpgeven(Conversation conversation){
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");
        String datum=jsonObjectIn.getString("datum");

        //System.out.println(datum);

        Student student = informatieSysteem.getStudent(gebruikersnaam);            // Student-object opzoeken

        Klas klas = informatieSysteem.getKlasVanStudent(student);         // klascode van de student opzoeken
        for (Les l : informatieSysteem.deLessen) {
            if (l.getKlas().getKlasCode().contains(klas.getKlasCode())) {
                student.addabsentie(datum, l);
            }
        }
        JsonArrayBuilder jab = Json.createArrayBuilder();                        // Uiteindelijk gaat er een array...
        jab.add(student.getAbsentie().get(0).getLes().getDateString());

        conversation.sendJSONMessage(jab.build().toString());                       // terug naar de Polymer-GUI!
    }

    private void toonabsenties(Conversation conversation){
        JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String gebruikersnaam = jsonObjectIn.getString("username");


        Student student = informatieSysteem.getStudent(gebruikersnaam);            // Student-object opzoeken

        Klas klas = informatieSysteem.getKlasVanStudent(student);         // klascode van de student opzoeken




        JsonArrayBuilder jab = Json.createArrayBuilder();                        // Uiteindelijk gaat er een array...
        for (Absentie ab: student.getAbsentie()){
        jab.add(ab.getLes().getDateString()+" Datum");
        jab.add(ab.getLes().getEindTijdString()+ " Eindtijd");
            jab.add(ab.getLes().getStartTijdString()+" Starttijd");
            jab.add(ab.getLes().getDocent().getGebruikersNaam()+ "Docent");}

        conversation.sendJSONMessage(jab.build().toString());                       // terug naar de Polymer-GUI!
    }


}
