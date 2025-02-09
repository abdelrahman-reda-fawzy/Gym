package backend;
import constants.FileNames;
import java.util.ArrayList;
public class AdminRole {
    private TrainerDataBase database;
    public AdminRole() {
        database=new TrainerDataBase(FileNames.TRAINER_FILENAME);
    }


    public boolean addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        if (database.contains(trainerId))
        {
            System.out.println("Trainer already exists");
            return false;
        }
        if(email.contains("@")&&email.contains("."));
        else
        {
            throw new IllegalArgumentException();
        }
        try {
            Integer.parseInt(phoneNumber);
            if(phoneNumber.charAt(0)=='0'&&phoneNumber.charAt(1)=='1'&&phoneNumber.length()==11);
            else
            {
                throw new IllegalArgumentException();
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
            Trainer t=new Trainer(trainerId, name, email, speciality, phoneNumber);
            database.insertRecord(t);
            System.out.println("Trainer Added");
            return true;
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return database.returnAllRecords();
    }

    public boolean removeTrainer(String key) {
            return database.deleteRecord(key);
    }

    public void logout() {
        System.out.println("Changes Saved");
        database.saveToFile();
    }
}
