
import java.util.List;

public class College {
    String Name;
    String ID;
    String Location;
    List<String> courseoffered;

    public College(String name, String ID, String location, List<String> courseoffered) {
        Name = name;
        this.ID = ID;
        Location = location;
        this.courseoffered = courseoffered;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public List<String> getCourseoffered() {
        return courseoffered;
    }

    public void setCourseoffered(List<String> courseoffered) {
        this.courseoffered = courseoffered;
    }
}
