package Serialization;
import java.io.*;

public class Deserializer {
    String path;
    Serializable obj;

    public Deserializer(String filePath, Serializable obj){
        this.path = filePath;
        this.obj = obj;
    }

    public void deserialize() throws ClassNotFoundException, IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            obj = (Serializable) ois.readObject();
            ois.close();
        }catch (InvalidClassException e){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); // Clear the file if its corrupted
            oos.writeObject("");
            oos.flush();
            oos.close();
        }catch (EOFException e){
            // Do nothing
        }
    }
}
