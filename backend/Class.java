package backend;

public class Class extends General {
    private String classID;
    private String className;
    private String trainerID;
    private int duration;
    private int availableSeats;
    public Class(String classID, String className, String trainerID, int duration, int availableSeats) {
        this.classID = classID;
        this.className = className;
        this.trainerID = trainerID;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        if(availableSeats >= 0)
        this.availableSeats = availableSeats;
        else {
            throw new IllegalArgumentException("Invalid available seats");
        }
    }

    public String lineRepresentation() {
        return classID + "," + className + "," + trainerID + "," + duration + "," + availableSeats;
    }

    public String getSearchKey() {
        return classID;
    }
}
