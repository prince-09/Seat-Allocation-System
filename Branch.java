
public class Branch {
    String Name,ID,degree;
    int seatcapacity,seatremained;

    public Branch(String name, String ID, String degree, int seatcapacity) {
        Name = name;
        this.ID = ID;
        this.degree = degree;
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
