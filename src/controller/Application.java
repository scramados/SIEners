package controller;

import model.PrIS;
import server.JSONFileServer;

import java.io.File;

public class Application {
    /**
     * Deze klasse is het startpunt voor de applicatie. Hierin maak je een server
     * op een bepaalde poort (bijv. 8888). Daarna is er een PrIS-object gemaakt. Dit
     * object fungeert als toegangspunt van het domeinmodel. Hiervandaan kan alle
     * informatie bereikt worden.
     * <p>
     * Om het domeinmodel en de Polymer-GUI aan elkaar te koppelen zijn diverse controllers
     * gemaakt. Er zijn meerdere controllers om het overzichtelijk te houden. Je mag zoveel
     * controller-klassen maken als je nodig denkt te hebben. Elke controller krijgt een
     * koppeling naar het PrIS-object om benodigde informatie op te halen.
     * <p>
     * Je moet wel elke URL die vanaf Polymer aangeroepen kan worden registreren! Dat is
     * hieronder gedaan voor een drietal URLs. Je geeft daarbij ook aan welke controller
     * de URL moet afhandelen.
     * <p>
     * Als je alle URLs hebt geregistreerd kun je de server starten en de applicatie in de
     * browser opvragen! Zie ook de controller-klassen voor een voorbeeld!
     */
    public static void main(String[] args) {
        JSONFileServer server = new JSONFileServer(new File("webapp/app"), 8888);

        PrIS infoSysteem = new PrIS();
        infoSysteem.readKlassen("klassen.csv");
        infoSysteem.readRooster("rooster_C.csv");
        UserController userController = new UserController(infoSysteem);
        DocentController docentController = new DocentController(infoSysteem);
        StudentController studentController = new StudentController(infoSysteem);

        server.registerHandler("/login", userController);
        server.registerHandler("/docent/mijnvakken", docentController);
        server.registerHandler("/docent/mijnRooster", docentController);
        server.registerHandler("/student/mijnmedestudenten", studentController);
        server.registerHandler("/docent/mijnrooster", docentController);
        server.registerHandler("/docent/studentabsenties", docentController);
        server.registerHandler("/student/student-AbsentieOpgeven", studentController);
        server.registerHandler("/student/mijnLessen",studentController);
        server.registerHandler("/student/absentietonen",studentController);
        server.start();
    }
}