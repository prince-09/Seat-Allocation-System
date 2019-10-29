
import java.util.List;

public class Student {

    String Name,ID,email;
    int generalrank,marks;
    List<String> collegepreferences;
    boolean isallocated=false;

    public Student(String name, String ID, String email, int generalrank, int marks, List<String> collegepreferences) {
        Name = name;
        this.ID = ID;
        this.email = email;
        this.generalrank = generalrank;
        this.marks = marks;
        this.collegepreferences = collegepreferences;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGeneralrank() {
        return generalrank;
    }

    public void setGeneralrank(int generalrank) {
        this.generalrank = generalrank;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public List<String> getCollegepreferences() {
        return collegepreferences;
    }

    public void setCollegepreferences(List<String> collegepreferences) {
        this.collegepreferences = collegepreferences;
    }
    public void setisallocated(boolean isallocated){
        this.isallocated=isallocated;
    }
    public boolean getisallocated(){
        return isallocated;
    }
}
