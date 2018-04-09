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
    public static void main(String[] args) throws InterruptedException, NoSuchPortException, IOException, ClassNotFoundException, SQLException {
        MongoConnection db = new MongoConnection();
        PythonConnection python = new PythonConnection(10000);
        SQLConnection sqlDatabase = new SQLConnection();
        Integer py = 0;
        sqlDatabase.deleteAll();
        
        
        Person[] user = db.getUser();
        for (Person user1 : user) {
            sqlDatabase.setUser(user1);
        }
        
        //new Thread(new ArduinoInterrupt()).start();
        while (true) {
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
                python.send("start");
                String res = python.receive();
                System.out.println(res);
            }
            Thread.sleep(10000);
        }
    } 
}
