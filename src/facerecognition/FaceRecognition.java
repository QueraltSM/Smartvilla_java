package facerecognition;

import gnu.io.NoSuchPortException;
import model.MongoConnection;
import model.SQLConnection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.PythonConnection;
import view.FaceFrame;

public class FaceRecognition {
    private static MongoConnection db;
    private static PythonConnection python;
    private static SQLConnection sqlDatabase;
    private static boolean update;
    
    
    public static void main(String[] args) throws InterruptedException, NoSuchPortException, IOException, ClassNotFoundException, SQLException {
        new Control(new PythonConnection(10000), new FaceFrame(), new MongoConnection(), new SQLConnection());
        
        /*db = new MongoConnection();
        python = new PythonConnection(10000);
        sqlDatabase = new SQLConnection();
        String name;
        
        Runnable mongoThread = new MongoThread(db, sqlDatabase, python);
        new Thread(mongoThread).start();
        
        while (true) {
            name = recognize();
        }*/
    }
    
    /*public static String recognize() throws InterruptedException, IOException, SQLException {
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
            python.send("continue");
            res = python.receive();
            System.out.println(res);
        }
        if (!res.equals("NoOne") && !(res.equals("Unknown"))) {
            ResultSet message = sqlDatabase.getUserMessages(res);
            Thread.sleep(10000);
        }
        return res;
    }*/
}
