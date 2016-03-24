package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrIS {
    private ArrayList<Docent> deDocenten;
    private ArrayList<Student> deStudenten;
    public ArrayList<Klas> deKlassen;
    public ArrayList<Les> deLessen;
    public ArrayList<Lokaal> deLokalen;


    /**
     * De constructor maakt een set met standaard-data aan. Deze data
     * moet nog vervangen worden door gegevens die uit een bestand worden
     * ingelezen, maar dat is geen onderdeel van deze demo-applicatie!
     * <p>
     * De klasse PrIS (PresentieInformatieSysteem) heeft nu een meervoudige
     * associatie met de klassen Docent en Student. Uiteraard kan dit nog veel
     * verder uitgebreid en aangepast worden!
     * <p>
     * De klasse fungeert min of meer als ingangspunt voor het domeinmodel. Op
     * dit moment zijn de volgende methoden aanroepbaar:
     * <p>
     * String login(String gebruikersnaam, String wachtwoord)
     * Docent getDocent(String gebruikersnaam)
     * Student getStudent(String gebruikersnaam)
     * ArrayList<Student> getStudentenVanKlas(String klasCode)
     * <p>
     * Methode login geeft de rol van de gebruiker die probeert in te loggen,
     * dat kan 'studentRead', 'docent' of 'undefined' zijn! Die informatie kan gebruikt
     * worden om in de Polymer-GUI te bepalen wat het volgende scherm is dat getoond
     * moet worden.
     */
    public PrIS() {
        deDocenten = new ArrayList<>();
        deStudenten = new ArrayList<>();
        deKlassen = new ArrayList<>();
        deLessen = new ArrayList<>();
        deLokalen = new ArrayList<>();

//      Les l1 = new Les("19-23-2016", "10:00");
//      Les l2 = new Les("19-23-2016", "10:00");
        Rooster r1 = new Rooster("rooster1");

        Docent d1 = new Docent("Wim", "test");
        Docent d2 = new Docent("Hans", "test");
        Docent d3 = new Docent("Jan", "test");

        d1.voegVakToe(new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces"));
        d1.voegVakToe(new Vak("TICT-V1GP-15", "Group Project"));
        d1.voegVakToe(new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction"));

        deDocenten.add(d1);
        deDocenten.add(d2);
        deDocenten.add(d3);
        d1.setRooster(r1);

//      r1.setLes(l1);
//      r1.setLes(l2);
    }

    public void readKlassen(String filename){
        BufferedReader br = null;
        String filedir = System.getProperty("user.dir") + "/CSV/" + filename;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(filedir));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] block = line.split(cvsSplitBy);
                Klas k = getKlas(block[4]);
                if (k == null){
                    k = new Klas(block[4]);
                    deKlassen.add(k);
                }
                Student s = null;
                if (block[2] != "") {
                    s = new Student(block[0], block[1], block[3], block[2]);
                } else {
                    s = new Student(block[0], block[1], block[3]);
                }
                k.addStudentKlas(s);
                if(!deStudenten.contains(s)){
                    deStudenten.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readRooster(String filename) {
        BufferedReader br = null;
        String filedir = System.getProperty("user.dir") + "/CSV/" + filename;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(filedir));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] block = line.split(cvsSplitBy);
                Klas k = getKlas(block[6]);
                if (k == null){
                    k = new Klas(block[6]);
                    deKlassen.add(k);
                }
                Docent d = getDocent(block[5]);
                if (k == null){
                    d = new Docent(block[5]);
                    deDocenten.add(d);
                }
                Les l = getLes(k, d, stringToDateConvert(block[0]), stringToTimeConvert(block[1]), stringToTimeConvert(block[2]));
                if (!deLessen.contains(l)){
                    deLessen.add(l);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String login(String gebruikersnaam, String wachtwoord) {
        for (Docent d : deDocenten) {
            if (d.getGebruikersNaam().equals(gebruikersnaam)) {
                if (d.controleerWachtwoord(wachtwoord)) {
                    return "docent";
                }
            }
        }

        for (Student s : deStudenten) {
            if (s.getGebruikersNaam().equals(gebruikersnaam)) {
                if (s.controleerWachtwoord(wachtwoord)) {
                    return "student";
                }
            }
        }

        return "undefined";
    }

    public Klas getKlas(String klasCode) {
        Klas resultaat = null;

        for (Klas k : deKlassen) {
            if (k.getKlasCode().equals(klasCode)) {
                resultaat = k;
                break;
            }
        }
        return resultaat;
    }
    public Les getLes(Klas klas, Docent docent, Date date, Date startTijd, Date eindTijd){
        Les resultaat = null;

        for (Les l :deLessen) {
            if (deLessen.contains(new Les(klas,docent,date,startTijd,eindTijd))) {
                resultaat = l;
                break;
            }
        }
        return resultaat;
    }

    public Docent getDocent(String gebruikersnaam) {
        Docent resultaat = null;

        for (Docent d : deDocenten) {
            if (d.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = d;
                break;
            }
        }

        return resultaat;
    }

    public Student getStudent(String gebruikersnaam) {
        Student resultaat = null;

        for (Student s : deStudenten) {
            if (s.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = s;
                break;
            }
        }

        return resultaat;
    }

    public ArrayList<Student> getStudentenVanKlas(String klasCode) {
        Klas k = getKlas(klasCode);
        return k.getStudentenKlas();
    }

    public Klas getKlasVanStudent(Student student){
        for (Klas k : deKlassen){
            for (Student s : k.getStudentenKlas()){
                if(s.equals(student)){
                    return k;
                }
            }
        }
        return null;
    }

    public Date stringToDateConvert(String dateString){
        Date startDate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public Date stringToTimeConvert(String timeString){
        Date time = null;
        DateFormat df = new SimpleDateFormat("hh:mm");
        try {
            time = df.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
