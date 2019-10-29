import java.util.ArrayList;
import java.util.HashMap;

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
        

        // TODO modify collegeResults & individualResults here

        
    }

    void publishResults(){

        // TODO save collegeResults & individualResults to file here

    }

}