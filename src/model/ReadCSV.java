
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
    private String csvFileKlas = System.getProperty("user.dir") + "/CSV/klassen.csv";
    private String csvFileRooster = System.getProperty("user.dir") + "/CSV/rooster_C.csv";
    private ArrayList<String> voornaamList = new ArrayList<>();
    private ArrayList<String> achternaamList = new ArrayList<>();
    private ArrayList<String> tussenVoegselList = new ArrayList<>();
    private ArrayList<String> studentNummerList = new ArrayList<>();
    private ArrayList<Klas> klasArrayList = new ArrayList<>();
    private ArrayList<Student> studentArrayList = new ArrayList<>();


    //De ArrayLists voor Rooster_C
    private ArrayList<String> datumArrayList = new ArrayList<>();
    private ArrayList<String> startTijdArrayList = new ArrayList<>();
    private ArrayList<String> eindTijdArrayList = new ArrayList<>();
    private ArrayList<String> vakCodeArrayList = new ArrayList<>();
    private ArrayList<String> docentNaamArrayList = new ArrayList<>();
    private ArrayList<String> lokaalNaamArrayList = new ArrayList<>();
    private ArrayList<Les> lesArrayList = new ArrayList<>();
    private ArrayList<Docent> docentArrayList = new ArrayList<>();
    private ArrayList<Lokaal> lokaalArrayList = new ArrayList<>();
    //klasArrayList hoort hierbij

    public ArrayList<Lokaal> lokaalRead(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileRooster));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                lokaalNaamArrayList.add(country[5]);
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
        for (int i = 0; i < lokaalNaamArrayList.size(); i++) {
            String lokaalNaam = lokaalNaamArrayList.get(i);
            if (!lokaalArrayList.contains(new Lokaal(lokaalNaam))) {
                lokaalArrayList.add(new Lokaal(lokaalNaam));
            }
        }
        return lokaalArrayList;
    }
    public ArrayList<Docent> docentRead(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileRooster));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                docentNaamArrayList.add(country[4]);
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
        for (int i = 0; i < docentNaamArrayList.size(); i++) {
            String docentnaam = docentNaamArrayList.get(i);
            if (!docentArrayList.contains(new Docent(docentnaam))) {
                docentArrayList.add(new Docent(docentnaam));
            }
        }
        return docentArrayList;
    }



    public ArrayList<Klas> klasRead(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileKlas));
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


    public ArrayList<Les> readLes(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileRooster));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                datumArrayList.add(country[0]);
                startTijdArrayList.add(country[1]);
                eindTijdArrayList.add(country[2]);
                vakCodeArrayList.add(country[3]);
                docentNaamArrayList.add(country[4]);
                lokaalNaamArrayList.add(country[5]);
                klasCodeList.add(country[6]);
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

        // TODO: 23-3-2016 object docent en lokaal genereren uit csv

        for (int i = 0; i < datumArrayList.size(); i++) {
            String startTijd = startTijdArrayList.get(i);
            String eindTijd = eindTijdArrayList.get(i);
            String lokaalNaam =  lokaalNaamArrayList.get(i);
            String datum = datumArrayList.get(i);
            String docentNaam = docentNaamArrayList.get(i);
            lesArrayList.add(new Les(datum, startTijd, eindTijd));
            //docent object toe wijzen
            for (Docent d : docentArrayList) {
                if (d.getGebruikersNaam().equals(docentNaam)) {
                    lesArrayList.get(i).setDocent(d);
                }
            }
            for (Lokaal l : lokaalArrayList) {
                if (l.getLokaalNaam().equals(lokaalNaam)) {
                    lesArrayList.get(i).setLokaal(l);
                }
            }




        }
        return lesArrayList;

    }
}
