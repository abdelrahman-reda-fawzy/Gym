package backend;
public class TrainerDataBase extends DataBase<Trainer>{
    public TrainerDataBase(String filename) {
        super(filename);
    }
    public Trainer createRecordFrom(String line)
    {
        String [] s=line.split(",");
        return new Trainer(s[0],s[1],s[2],s[3],s[4]);
    }

}
