package model;

/**
 * Created by Jasper
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadCSV {

    public static void main(String[] args) {
    }

    public ArrayList studentRead(String path) {
        ArrayList<ArrayList> arrayListArrayList = new ArrayList<>();
        ArrayList<String>  voornaamList = new ArrayList<>();
        ArrayList<String>  achternaamList = new ArrayList<>();
        ArrayList<String>  tussenVoegselList = new ArrayList<>();
        ArrayList<String>  studentNummerList = new ArrayList<>();
        ArrayList<String>  klasCodeList = new ArrayList<>();
        String csvFile = "/CSV";
        csvFile += path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            Map<String, String> maps = new HashMap<String, String>();
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                voornaamList.add(country[0]);
                maps.put(country[0], country[1], country[2], country[3], country[4]);
            }
            //loop map
            for (Map.Entry<String, String> entry : maps.entrySet()) {

                System.out.println("Country [code= " + entry.getKey() + " , name="
                        + entry.getValue() + "]");
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

        System.out.println("Done");
        return
    }

}
