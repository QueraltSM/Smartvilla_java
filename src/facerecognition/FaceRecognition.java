package facerecognition;

import gnu.io.NoSuchPortException;
import model.MongoConnection;
import model.SQLConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.Person;
import model.PythonConnection;

public class FaceRecognition {
    private static MongoConnection db;
    private static PythonConnection python;
    private static SQLConnection sqlDatabase;
    private static boolean update;
    
    
    public static void main(String[] args) throws InterruptedException, NoSuchPortException, IOException, ClassNotFoundException, SQLException {
        db = new MongoConnection();
        python = new PythonConnection(10000);
        sqlDatabase = new SQLConnection();
        String name;
        
        Runnable mongoThread = new MongoThread(db, sqlDatabase, python);
        new Thread(mongoThread).start();
        
        while (true) {
            name = recognize();
        }
    }
    
    public static String recognize() throws InterruptedException, IOException {
        int py = 0;
        String res = "";
        try {
            ExecutorService service = Executors.newFixedThreadPool(1);
            Future<Integer> result = service.submit(new ArduinoThread());
            if (result.get() == 1) {
                py = 1;
            }
            service.shutdown();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (py == 1) {
            /*if (update) {   
                python.send("start");
                update = false;
            } else {
                python.send("continue");
            }*/
            python.send("continue");
            res = python.receive();
            System.out.println(res);
        }
        Thread.sleep(10000);
        return res;
    }
}
