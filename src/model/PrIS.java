package model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrIS {
    public ArrayList<Klas> deKlassen;
    public ArrayList<Les> deLessen;
    public ArrayList<Lokaal> deLokalen;
    public ArrayList<Vak> deVakken;
    private ArrayList<Docent> deDocenten;
    private ArrayList<Student> deStudenten;


    public PrIS() { // Constructor van het Presentie Informatie systeem
        deDocenten = new ArrayList<>();
        deStudenten = new ArrayList<>();
        deKlassen = new ArrayList<>();
        deLessen = new ArrayList<>();
        deLokalen = new ArrayList<>();
        deVakken = new ArrayList<>();

        //Niet genoeg info in de CSV bestanden dus worden handmatig aangemaakt
        deVakken.add(new Vak("TICT-V1GP-15", "Group Project"));
        deVakken.add(new Vak("TCIF-V1AUI-15", "Analysis & User Interfacing"));
        deVakken.add(new Vak("TICT-V1OODC-15", "OO Design & Construction"));
    }

    public void readKlassen(String filename) {   // Functie om de studenten en klassen in te lezen uit het CSV bestand klassen.csv
        BufferedReader br = null;
        String filedir = System.getProperty("user.dir") + "/CSV/" + filename; // folder directory verkrijgen van de computer en filenaam er achter plakken
        String line = "";
        String cvsSplitBy = ",";                 // het teken dat tussen de gegevens in het CSV bestand staat
        try {
            br = new BufferedReader(new FileReader(filedir)); // Buffered reader wordt aangemaakt
            while ((line = br.readLine()) != null) {
                String[] block = line.split(cvsSplitBy); // zorgt er voor dat de gegevens van het CSV bestand per regel terug te roepen is
                Klas k = getKlas(block[4]);     // Checkt of de klas al in de klassen lijst staat
                if (k == null) {                // Als de klas nog niet bestaat
                    k = new Klas(block[4]);     // Wordt deze aangemaakt
                    deKlassen.add(k);           // En toegevoegd aan de klassen lijst
                }
                Student s = null;
                if (block[2] != "") {           // Checkt of de student een tussen voegsel heeft
                    s = new Student(block[0], block[3], block[1], block[2]);
                } else {                        // Zo niet wordt de student zonder aangemaakt
                    s = new Student(block[0], block[3], block[1]);
                }
                k.addStudentKlas(s);            // Wijst de klas een student toe
                if (!deStudenten.contains(s)) { // Als de student nog niet voorkomt in de studenten lijst
                    deStudenten.add(s);         // Wordt deze toegevoegd
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close(); // Buffered reader wordt gesloten
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readRooster(String filename) {
        BufferedReader br = null;
        String filedir = System.getProperty("user.dir") + "/CSV/" + filename;// folder directory verkrijgen van de computer en filenaam er achter plakken
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(filedir)); // Buffered reader wordt aangemaakt
            while ((line = br.readLine()) != null) {
                String[] block = line.split(cvsSplitBy); // zorgt er voor dat de gegevens van het CSV bestand per regel terug te roepen is
                Klas k = getKlas(block[6]);     // Checkt of de klas al in de klassen lijst staat
                if (k == null) {                // Als de klas nog niet bestaat
                    k = new Klas(block[6]);     // Wordt deze aangemaakt
                    deKlassen.add(k);           // En toegevoegd aan de klassen lijst
                }
                Docent d = getDocent(block[4]); // Herhaling met docent
                if (d == null) {
                    d = new Docent(block[4]);
                    deDocenten.add(d);
                }
                Lokaal loka = getLokaal(block[5]); // herhaling met lokaal
                if (loka == null) {
                    loka = new Lokaal(block[5]);
                    deLokalen.add(loka);
                }
                Vak v = getVak(block[3]);  // Zoekt het vak op
                Les l = getLes(v, loka, k, d, stringToDateConvert(block[0]), stringToTimeConvert(block[1]), stringToTimeConvert(block[2])); // kijkt of de les al bestaat
                if (l == null) {        // als de les nog niet bestaat wordt deze aangemaakt
                    l = new Les(v, loka, k, d, stringToDateConvert(block[0]), stringToTimeConvert(block[1]), stringToTimeConvert(block[2]));
                    deLessen.add(l);    // en toegevoegd aan de lessen lijst
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close(); // Buffered reader wordt gesloten
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String login(String gebruikersnaam, String wachtwoord) { // Functie om gebruikers naam en correct wachtwoord docent of student terug te late geven
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

    public Klas getKlas(String klasCode) { // Vraagt om de klas code en geeft Klas object terug als deze bestaat
        Klas resultaat = null;

        for (Klas k : deKlassen) {
            if (k.getKlasCode().equals(klasCode)) {
                resultaat = k;
                break;
            }
        }
        return resultaat;
    }

    public Les getLes(Vak vak, Lokaal lokaal, Klas klas, Docent docent, Date date, Date startTijd, Date eindTijd) { // vraagt om les gegevens en geeft Klas object terug
        Les resultaat = null;

        for (Les l : deLessen) {
            if (deLessen.contains(new Les(vak, lokaal, klas, docent, date, startTijd, eindTijd))) {
                resultaat = l;
                break;
            }
        }
        return resultaat;
    }

    public Docent getDocent(String gebruikersnaam) { // Vraag Docent gebruikersnaam en geeft Docent object terug
        Docent resultaat = null;

        for (Docent d : deDocenten) {
            if (d.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = d;
                break;
            }
        }

        return resultaat;
    }

    public Student getStudent(String gebruikersnaam) { // Vraag om student gebruikersnaam en geeft Student object terug
        Student resultaat = null;

        for (Student s : deStudenten) {
            if (s.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = s;
                break;
            }
        }

        return resultaat;
    }

    public Klas getKlasVanStudent(Student student) { // Vraagt om Student object en geeft Klas van de student terug
        for (Klas k : deKlassen) {
            for (Student s : k.getStudentenKlas()) {
                if (s.equals(student)) {
                    return k;
                }
            }
        }
        return null;
    }

    public Vak getVak(String vakCode) { // vraag om vakcode en geeft Vak object terug
        Vak resultaat = null;

        for (Vak v : deVakken) {
            if (v.getVakCode().equals(vakCode)) {
                resultaat = v;
                break;
            }
        }
        return resultaat;
    }

    public Lokaal getLokaal(String lokaalNaam) { // Vraagt om lokaal naam en geeft Lokaal object terug
        Lokaal resultaat = null;

        for (Lokaal l : deLokalen) {
            if (l.getLokaalNaam().equals(lokaalNaam)) {
                resultaat = l;
                break;
            }
        }

        return resultaat;
    }

    public ArrayList<Vak> getVakkenDocent(Docent docent) { // Zoekt de vakken van het Docent object op
        ArrayList<Vak> vakkenDocent = new ArrayList<>();

        for (Les l : deLessen)
            if (l.getDocent().equals(docent)) {
                if(!vakkenDocent.contains(l.getVak())) {
                    vakkenDocent.add(l.getVak());
                }
            }
        return vakkenDocent;
    }

    public ArrayList<Absentie> getAbsentiesLes(Les les){ // Zoekt de Abseties van de opgegeven les op
        ArrayList<Absentie> absenties = new ArrayList<>();
        for(Les l : deLessen){
            if(l.equals(les)){
                for (Student s: l.getKlas().getStudentenKlas()){
                    for(Absentie a:s.getAbsentie()){
                            absenties.add(a);
                        }
                    }
                }
            }
        return absenties;
    }

    public Date stringToDateConvert(String dateString) { // Zet de String van de datum om in een Date object
        Date startDate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public Date stringToTimeConvert(String timeString) { // Zet de string de String van tijd om in een Date object
        Date time = null;
        DateFormat df = new SimpleDateFormat("hh:mm");
        try {
            time = df.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public void writeAbsentie() throws IOException{ // schrijft absenties weg naar obj file
        String s = System.getProperty("user.dir") + "/CSV/absenties.obj";
        FileOutputStream fos;
        ObjectOutputStream oos=null;
        ArrayList<Absentie> absenties = new ArrayList<>();
        ArrayList<Absentie> ab;
        try {
            fos = new FileOutputStream(s);
            oos = new ObjectOutputStream(fos);
            for (Student stu: deStudenten){
                ab = stu.getAbsentie();
                for(Absentie abs: ab){
                    absenties.add(abs);
                }
            }
            oos.writeObject(absenties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }
    }

    public void readAbsenties()throws IOException{ // haalt absenties op uit obj file
        String s = System.getProperty("user.dir") + "/CSV/absenties.obj";
        FileInputStream fis;
        ObjectInputStream ois=null;
        try {
            fis = new FileInputStream(s);
            ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ArrayList<Absentie> lijst= (ArrayList<Absentie>) obj;
            for(Absentie ab: lijst) {
                Student student = ab.getStudent();
                for (Student st: deStudenten){
                   if ( st.getGebruikersNaam().contains(student.getGebruikersNaam())){
                        student = st;
                    }
                }
                            student.addabsentie(ab);
                            System.out.println(student.getAbsentie());
                    }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
    }
}
