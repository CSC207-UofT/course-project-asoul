package serialization;

import java.io.*;

public class Serializer {
    public void serialize(String filePath, Serializable obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(obj);
        oos.flush();
        oos.close();
    }
}
