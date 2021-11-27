package serialization;

import java.io.*;

public class Serializer {
    String path;
    Serializable obj;

    public Serializer(String filePath, Serializable obj){
        this.path = filePath;
        this.obj = obj;
    }

    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(obj);
        oos.flush();
        oos.close();
    }
}
