import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandlineInterface cm = new CommandlineInterface();
        while(cm.isRunning()){
            cm.run(br.readLine());
        }
        System.out.println("Exit Program!");
        br.close();
    }
}
