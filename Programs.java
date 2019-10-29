

public class Programs {
    String Name,ID,degree,location;
    int seatcapacity,seatremained;

    public Programs(String name, String ID, String degree, String location, int seatcapacity) {
        Name = name;
        this.ID = ID;
        this.degree = degree;
        this.location = location;
        this.seatcapacity = seatcapacity;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeatcapacity() {
        return seatcapacity;
    }

    public void setSeatcapacity(int seatcapacity) {
        this.seatcapacity = seatcapacity;
    }

    public int getSeatremained() {
        return seatremained;
    }

    public void setSeatremained(int seatremained) {
        this.seatremained = seatremained;
    }
}
