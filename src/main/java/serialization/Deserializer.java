package serialization;
import java.io.*;

public class Deserializer {
    Serializable obj;

    public void deserialize(String path) throws ClassNotFoundException, IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            obj = (Serializable) ois.readObject();
            ois.close();
        }catch (InvalidClassException e){
            System.out.println("Overwrite!");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); // Clear the file if its corrupted
            oos.writeObject("");
            oos.flush();
            oos.close();
        }catch (EOFException e){
            // Do nothing
        }
    }

    public Serializable getObject(){
        return obj;
    }
}
