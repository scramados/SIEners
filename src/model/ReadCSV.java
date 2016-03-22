package model;

/**
 * Created by Jasper
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    private String csvFile = System.getProperty("user.dir") + "/CSV/klassen.csv";
    private ArrayList<String> voornaamList = new ArrayList<>();
    private ArrayList<String> achternaamList = new ArrayList<>();
    private ArrayList<String> tussenVoegselList = new ArrayList<>();
    private ArrayList<String> studentNummerList = new ArrayList<>();
    private ArrayList<String> klasCodeList = new ArrayList<>();
    private ArrayList<Klas> klasArrayList = new ArrayList<>();
    private ArrayList<Student> studentArrayList = new ArrayList<>();


    public ArrayList<Klas> klasRead(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                klasCodeList.add(country[4]);
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
        for (int i = 0; i < klasCodeList.size(); i++) {
            String klasCode = klasCodeList.get(i);
            if (!klasArrayList.contains(new Klas(klasCode))) {
                klasArrayList.add(new Klas(klasCode));
            }
        }
        return klasArrayList;
    }

    public ArrayList<Student> studentRead() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                studentNummerList.add(country[0]);
                voornaamList.add(country[3]);
                tussenVoegselList.add(country[2]);
                achternaamList.add(country[1]);
                klasCodeList.add(country[4]);
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

        for (int i = 0; i < studentNummerList.size(); i++) {
            String studentNummer = studentNummerList.get(i);
            String klasCode = klasCodeList.get(i);
            String voorNaam = voornaamList.get(i);
            String achterNaam = achternaamList.get(i);
            if (tussenVoegselList.get(i) != "") {
                String tussenvoegsel = tussenVoegselList.get(i);
                studentArrayList.add(new Student(studentNummer, voorNaam, achterNaam, tussenvoegsel));
            } else {
                studentArrayList.add(new Student(studentNummer, voorNaam, achterNaam));
            }

            for (Klas k : klasArrayList) {
                if (k.getKlasCode().equals(klasCode)) {
                    studentArrayList.get(i).setMijnKlas(k);
                }
            }
        }
        return studentArrayList;
    }
}
