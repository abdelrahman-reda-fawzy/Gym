package backend;
public class MemberDataBase extends DataBase<Member> {
    public MemberDataBase(String filename) {
        super(filename);
    }
    public Member createRecordFrom(String line)
    {
        String [] s=line.split(",");
        return new Member(s[0],s[1],s[2],s[3],s[4],s[5]);

    }
}
