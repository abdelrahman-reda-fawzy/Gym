package backend;

public class ClassDataBase extends DataBase <Class>{
    public ClassDataBase(String filename) {
        super(filename);
    }
    public Class createRecordFrom(String line)
    {
        String [] s=line.split(",");
        return new Class(s[0],s[1],s[2],Integer.parseInt(s[3]),Integer.parseInt(s[4]));
    }
}
