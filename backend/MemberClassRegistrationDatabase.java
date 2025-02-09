package backend;
import java.time.LocalDate;

public class MemberClassRegistrationDatabase extends DataBase<MemberClassRegistration>{
    public MemberClassRegistrationDatabase(String filename) {
        super(filename);
    }
    public MemberClassRegistration createRecordFrom(String line)
    {
        String [] s=line.split(",");
        return new MemberClassRegistration(s[0],s[1], LocalDate.parse(s[2]),s[3]);
    }
}
