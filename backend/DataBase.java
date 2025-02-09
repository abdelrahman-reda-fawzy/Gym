package backend;

import java.io.*;
import java.util.ArrayList;

public abstract class DataBase<T extends General> {
    private String filename;
    private ArrayList<T> records;
    public DataBase(String filename) {
        this.filename = filename;
        records = new ArrayList<>();
        readFromFile();
    }
    protected void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("ERROR");
        }
    }
    protected abstract T createRecordFrom(String line);
    public ArrayList<T>returnAllRecords() {
        return records;
    }
    public boolean contains(String key)
    {
        for(General record : records) {
            if(record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    public T getRecord(String key)
    {
        for(T record : records) {
            if(record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }
    public void insertRecord(T record)
    {
        for(T record1 : records) {
            if(record1.getSearchKey().equals(record.getSearchKey())) {
                return;
            }
        }
        records.add(record);
    }
    public boolean deleteRecord(String key)
    {
        for(T record : records) {
            if(record.getSearchKey().equals(key)) {
                records.remove(record);
                System.out.println("Deleted");
                return true;
            }
        }
        System.out.println("ID not exist");
        return false;
    }
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            for(T record : records) {
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("ERROR");
        }
    }

}
