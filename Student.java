
import java.util.List;

public class Student {

    String name, studentId, email;
    Long generalRank, marks;
    List<String> collegePreferences;
    boolean isAllocated;

    public Student(String name, String studentId, String email, Long generalrank, Long marks, List<String> collegepreferences) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.generalRank = generalrank;
        this.marks = marks;
        this.collegePreferences = collegepreferences;
        isAllocated = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGeneralRank() {
        return generalRank;
    }

    public void setGeneralRank(Long generalRank) {
        this.generalRank = generalRank;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public List<String> getCollegePreferences() {
        return collegePreferences;
    }

    public void setCollegepreferences(List<String> collegePreferences) {
        this.collegePreferences = collegePreferences;
    }

    public boolean isAllocated() {
        return isAllocated;
    }

    public void setAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }
    public void setisallocated(boolean isallocated){
        this.isallocated=isallocated;
    }
    public boolean getisallocated(){
        return isallocated;
    }
}
