public class Program {
    String programName, programID, collegeName, location, degree, branch;
    Long seatCapacity, seatRemaining;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Long seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Long getSeatRemaining() {
        return seatRemaining;
    }

    public void setSeatRemaining(Long seatRemaining) {
        this.seatRemaining = seatRemaining;
    }

    public Program(String programID, String collegeName, String location, String degree, String branch,
            Long seatCapacity, Long seatRemaining) {
        this.programID = programID;
        this.collegeName = collegeName;
        this.location = location;
        this.degree = degree;
        this.branch = branch;
        this.seatCapacity = seatCapacity;
        this.programName = degree + " in " + branch;
        this.seatRemaining = seatRemaining;
    }
}
