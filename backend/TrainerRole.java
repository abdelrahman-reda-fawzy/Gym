package backend;
import constants.FileNames;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
public class TrainerRole {
    private MemberDataBase memberDataBase;
    private ClassDataBase classDataBase;
    private MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole() {
        memberDataBase = new MemberDataBase(FileNames.MEMBER_FILENAME);
        classDataBase = new ClassDataBase(FileNames.CLASS_FILENAME);
        registrationDatabase = new MemberClassRegistrationDatabase(FileNames.REGISTRATION_FILENAME);
    }

    public boolean addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        if (memberDataBase.contains(memberID)) {
            System.out.println("Member already exists");
            return false;
        }
            if (email.contains("@") && email.contains(".")) ;
            else {
                throw new IllegalArgumentException();
            }
            try {
                Integer.parseInt(phoneNumber);
                if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '1' && phoneNumber.length() == 11) ;
                else {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            memberDataBase.insertRecord(new Member(memberID, name, membershipType, email, phoneNumber, status));
            System.out.println("Member Added");
            return true;
    }
    public ArrayList<Member> getListOfMembers() {
        return memberDataBase.returnAllRecords();
    }

    public boolean addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        if (classDataBase.contains(classID)) {
            System.out.println("Class Already Exists");
            return false;
        }
        classDataBase.insertRecord(new Class(classID, className, trainerID, duration, maxParticipants));
        System.out.println("Class Added");
        return true;
    }

    public ArrayList<Class> getListOfClasses() {
        return classDataBase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
       
        if(registrationDatabase.contains(memberID+classID))
        {
            throw new IllegalArgumentException();
        }
        Class c = classDataBase.getRecord(classID);
        if (c==null)
        {
            throw new IOException();
        }
        if (c.getAvailableSeats() == 0) return false;
        MemberClassRegistration m = new MemberClassRegistration(memberID, classID, registrationDate, "active");
        c.setAvailableSeats(c.getAvailableSeats() - 1);
        registrationDatabase.insertRecord(m);
        return true;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        if (!registrationDatabase.contains(memberID + classID))
        {
            System.out.println("Registration Not Found");
            throw new IllegalArgumentException();
        }
        MemberClassRegistration m=registrationDatabase.getRecord(memberID + classID);
 
        LocalDate d = m.getRegistrationDate().plusDays(3);
        if (d.isAfter(LocalDate.now()) || d.isEqual(LocalDate.now())) {
           m.setStatus("canceled");
        } else {
            return  false;
        }
        Class c = classDataBase.getRecord(classID);
        registrationDatabase.deleteRecord(memberID+classID);
        c.setAvailableSeats(c.getAvailableSeats() + 1);
        return true;
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        ArrayList<MemberClassRegistration> registrations;
        registrations = registrationDatabase.returnAllRecords();
        return new ArrayList<>(registrations);
    }
    public void logout()
    {
        classDataBase.saveToFile();
        memberDataBase.saveToFile();
        registrationDatabase.saveToFile();
        System.out.println("Changes Saved");
    }
}
