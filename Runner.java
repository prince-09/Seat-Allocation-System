import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Runner {

    boolean isStudentsReady = false, isProgramsReady = false;
    ArrayList<Student> students;
    ArrayList<Program> programs;

    HashMap<String, ArrayList<Student>> collegeResults;
    HashMap<String, String> individualResults;

    public static void main(String args[]) {
        new Runner().initiateAllocation();

    }

    void initiateAllocation() {
        (new InputThread(InputThread.TYPE.STUDENT, new ArrayList<Student>()))
                .setOnInputCompleteListener(new InputThread.OnInputCompleteListener() {
                    @Override
                    public void onListReady(ArrayList list) {
                        students = list;
                        isStudentsReady = true;

                        if (isProgramsReady && isStudentsReady) {
                            allocate(programs, students);
                            publishResults();
                        }
                    }
                });

        (new InputThread(InputThread.TYPE.PROGRAM, new ArrayList<Program>()))
                .setOnInputCompleteListener(new InputThread.OnInputCompleteListener() {
                    @Override
                    public void onListReady(ArrayList list) {
                        programs = list;
                        isProgramsReady = true;

                        if (isProgramsReady && isStudentsReady) {
                            allocate(programs, students);
                            publishResults();
                        }
                    }
                });

    }

    void allocate(ArrayList<Program> programs, ArrayList<Student> students) {

        individualResults = new HashMap<>();
        collegeResults = new HashMap<>();
        Student student1[] = new Student[students.size()];
        for (int i = 0; i < students.size(); i++) {
            student1[i] = students.get(i);
        }
        long p = 0;
        int l = 0;
        for (int i = 1; i < students.size(); i++) {
            Student student = student1[i];
            l = i - 1;
            p = student1[i].getGeneralRank();
            while (l >= 0 && student1[l].getGeneralRank() > p) {
                student1[l + 1] = student1[l];
                l--;
                if (l < 0) {
                    break;
                }
            }
            student1[l + 1] = student;
        }
        ArrayList<Student> students2 = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            students2.add(student1[i]);
        }
        int a[] = new int[programs.size()];
        for (int i = 0; i < students2.size(); i++) {
            int g = 0;
            while (g != students2.get(i).getCollegePreferences().size()) {
                String choice = students2.get(i).getCollegePreferences().get(g);
                for (int j = 0; j < programs.size(); j++) {
                    if (choice.equals("" + programs.get(j).getProgramID())) {
                        if (a[j] >= programs.get(j).getSeatCapacity()) {
                            break;
                        }
                        students2.get(i).isAllocated = true;
                        individualResults.put("" + programs.get(j).getProgramID(), students2.get(i).getName());
                        if (collegeResults.containsKey("" + programs.get(j).getCollegeName())) {
                            ArrayList<Student> list = new ArrayList<>(
                                    collegeResults.get("" + programs.get(j).getCollegeName()));
                            list.add(students2.get(i));
                            collegeResults.put("" + programs.get(j).getCollegeName(), list);
                        } else {
                            ArrayList<Student> list = new ArrayList<>();
                            list.add(students2.get(i));
                            collegeResults.put("" + programs.get(j).getCollegeName(), list);
                        }
                        a[j]++;
                    }
                }
                if(students2.get(i).getisallocated()){
                    break;
                }
                g++;
            }
        }

    }

    void publishResults() {
        try {
            JSONObject individualResultsObject = new JSONObject();
            individualResultsObject.put("results", individualResults);
            PrintWriter pw = new PrintWriter("IndividualResults.json");
            pw.write(individualResultsObject.toJSONString());

            pw.flush();
            pw.close();

            JSONObject detailedList = new JSONObject();
            for(String key : collegeResults.entrySet().toArray()){
                JSONArray studentsList = new JSONArray();

                for(Student stud : collegeResults.get(key)){
                    JSONObject
                }
            }

            // JSONObject detailedList = new JSONObject();
            // detailedList.put("results", collegeResults);
            // PrintWriter p2w = new PrintWriter("CollegeResults.json");
            // p2w.write(detailedList.toJSONString());

            // p2w.flush();
            // p2w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
