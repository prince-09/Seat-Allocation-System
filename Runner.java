import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Runner {

    boolean isStudentsReady = false, isProgramsReady = false;
    ArrayList<Student> students;
    ArrayList<Program> programs;

    HashMap<String, ArrayList<ArrayList<String>>> collegeResults;
    HashMap<String, ArrayList<String>> individualResults;

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
        // System.out.println(students.size());
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
        // for(int i=0;i<students2.size();i++)
        // System.out.println(students2.get(i).getName());
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
                        // System.out.print(i + " ");
                        students2.get(i).isAllocated = true;
                        // System.out.println(students2.get(i).getStudentId());
                        ArrayList<String> arr = new ArrayList<>();
                        arr.add(programs.get(j).getProgramID());
                        arr.add(students2.get(i).getStudentId());
                        arr.add(students2.get(i).getEmail());
                        arr.add("" + students2.get(i).getGeneralRank());
                        arr.add("" + students2.get(i).getMarks());
                        individualResults.put(students2.get(i).getName(), arr);
                        if (collegeResults.containsKey("" + programs.get(j).getCollegeName())) {
                            ArrayList<String> stud = new ArrayList<>();
                            stud.add(students2.get(i).getName());
                            stud.add(students2.get(i).getStudentId());
                            stud.add(students2.get(i).getEmail());
                            stud.add("" + students2.get(i).getMarks());
                            stud.add("" + students2.get(i).getGeneralRank());
                            stud.add(programs.get(j).getProgramID());
                            ArrayList<ArrayList<String>> list = new ArrayList<>(
                                    collegeResults.get("" + programs.get(j).getCollegeName()));
                            list.add(stud);
                            collegeResults.put("" + programs.get(j).getCollegeName(), list);
                        } else {
                            ArrayList<String> stud = new ArrayList<>();
                            stud.add(students2.get(i).getName());
                            stud.add(students2.get(i).getStudentId());
                            stud.add(students2.get(i).getEmail());
                            stud.add("" + students2.get(i).getMarks());
                            stud.add("" + students2.get(i).getGeneralRank());
                            stud.add(programs.get(j).getProgramID());
                            ArrayList<ArrayList<String>> list = new ArrayList<>();
                            list.add(stud);
                            collegeResults.put("" + programs.get(j).getCollegeName(), list);
                        }
                        a[j]++;
                    }
                }
                if (students2.get(i).getisallocated()) {
                    break;
                }
                g++;
            }

        }
        for (int i = 0; i < students2.size(); i++) {
            if (students2.get(i).getisallocated() == false) {
                ArrayList<String> arr = new ArrayList<>();
                arr.add("Not Allocated");
                individualResults.put(students2.get(i).getName(), arr);
            }
        }
        // System.out.println(individualResults);

    }

    void publishResults() {
        try {
            JSONObject individualResultsObject = new JSONObject();
            individualResultsObject.put("results", individualResults);
            PrintWriter pw = new PrintWriter("IndividualResults.json");
            pw.write(individualResultsObject.toJSONString());
            pw.flush();
            pw.close();

            JSONObject collegeResultsObject = new JSONObject();
            collegeResultsObject.put("results", collegeResults);
            PrintWriter pwi = new PrintWriter("CollegeResults.json");
            pwi.write(collegeResultsObject.toJSONString());

            pwi.flush();
            pwi.close();

            Scanner scanner = new Scanner(new File("individualResults.html"));
            String sample = scanner.useDelimiter("\\A").next();

            String[] parts = sample.split("SPLIT_HERE");

            StringBuilder export = new StringBuilder();
            export.append(parts[0]);

            for (int i = 0; i < individualResults.keySet().toArray().length; i++) {
                ArrayList<String> temp = individualResults.get(individualResults.keySet().toArray()[i]);
                if (!temp.get(0).equals("Not Allocated"))
                    if (i % 2 == 1)
                        export.append("<tr> <td>" + temp.get(1) + "</td><td>" + individualResults.keySet().toArray()[i]
                                + "</td><td>" + temp.get(2) + "</td><td>" + temp.get(3) + "</td><td>" + temp.get(4)
                                + "</td><td>" + temp.get(0) + "</td></tr>");
                    else
                        export.append("<tr class = \"gray\"> <td>" + temp.get(1) + "</td><td>"
                                + individualResults.keySet().toArray()[i] + "</td><td>" + temp.get(2) + "</td><td>"
                                + temp.get(3) + "</td><td>" + temp.get(4) + "</td><td>" + temp.get(0) + "</td></tr>");

            }

            export.append(parts[1]);

            FileWriter writer = new FileWriter(new File("individualResultsCalculated.html"));
            writer.write(export.toString());

            writer.close();
            scanner.close();

            Scanner scanner2 = new Scanner(new File("CollegeResults.html"));
            String sample2 = scanner2.useDelimiter("\\A").next();

            String[] parts2 = sample2.split("SPLIT_HERE");

            StringBuilder export2 = new StringBuilder();
            export2.append(parts2[0]);

            for (int i = 0; i < collegeResults.keySet().toArray().length; i++) {
                ArrayList<ArrayList<String>> tempBunch = collegeResults.get(collegeResults.keySet().toArray()[i]);
                for (ArrayList<String> temp : tempBunch)
                    if (i % 2 == 1)
                        export2.append("<tr> <td>" + collegeResults.keySet().toArray()[i] + "</td><td>" + temp.get(0)
                                + "</td><td>" + temp.get(2) + "</td><td>" + temp.get(4) + "</td><td>" + temp.get(3)
                                + "</td><td>" + temp.get(5) + "</td></tr>");
                    else
                        export2.append("<tr class = \"gray\"> <td>" + collegeResults.keySet().toArray()[i] + "</td><td>"
                                + temp.get(0) + "</td><td>" + temp.get(2) + "</td><td>"
                                + temp.get(4) + "</td><td>" + temp.get(3) + "</td><td>" + temp.get(5) + "</td></tr>");

            }

            export2.append(parts2[1]);

            FileWriter writer2 = new FileWriter(new File("CollegeResultsCalculated.html"));
            writer2.write(export2.toString());

            writer2.close();
            scanner2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
