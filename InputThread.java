import java.util.ArrayList;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import dependencies.org.json.simple.JSONArray;
import dependencies.org.json.simple.JSONObject;
import dependencies.org.json.simple.parser.*;

public class InputThread extends Thread {
    public enum TYPE {
        STUDENT, PROGRAM
    }

    TYPE type;
    ArrayList outputArray;

    public InputThread(TYPE type, ArrayList array) {
        this.type = type;
        outputArray = array;
        this.start();
    }

    Student parseToStudent(JSONObject JO) {

        ArrayList<String> preferences = new ArrayList();

        Iterator itr = ((JSONArray) JO.get("collegePreferences")).iterator();
        while (itr.hasNext())
            preferences.add((String) itr.next());

        return new Student((String) JO.get("name"), (String) JO.get("id"), (String) JO.get("email"),
                (Long) JO.get("generalRank"), (Long) JO.get("marks"), preferences);
    }

    Student parseToProgram(JSONObject JO) {
        return new Program((String) JO.get("programID"),
        (String) JO.get("collegeName"),
        (String) JO.get("location"),
        (String) JO.get("degree"),
        (String) JO.get("branch"),
        (Long) JO.get("seatCapacity"),
        (Long) JO.get("seatRemaining"));

    }

    public void run() {
        try {
            if (type == TYPE.STUDENT) {
                Object obj = new JSONParser().parse(new FileReader("Students.json"));
                Iterator itr = ((JSONArray) ((JSONObject) obj).get("Students")).iterator();

                while (itr.hasNext()) {
                    JSONObject JO = (JSONObject) itr.next();
                    Student S = parseToStudent(JO);
                    outputArray.add(S);
                }

            } else {

                Object obj = new JSONParser().parse(new FileReader("Programs.json"));
                Iterator itr = ((JSONArray) ((JSONObject) obj).get("Programs")).iterator();

                while (itr.hasNext()) {
                    JSONObject JO = (JSONObject) itr.next();
                    Program S = parseToProgram(JO);
                    outputArray.add(S);
                }

            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}