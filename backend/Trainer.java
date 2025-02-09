package backend;
public class Trainer extends General{
    private String trainerID;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;
    public Trainer(String trainerID, String name, String email, String speciality, String phoneNumber) {
        this.trainerID = trainerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.speciality = speciality;
    }
    public String lineRepresentation() {
        return trainerID + "," + name + "," + email + "," + speciality + "," + phoneNumber;
    }
    public String getSearchKey() {
        return trainerID;
    }
}
