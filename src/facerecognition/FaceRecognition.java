package facerecognition;

import gnu.io.NoSuchPortException;
import java.util.concurrent.TimeUnit;
import model.ArduinoConnection;
import model.MongoConnection;
import model.SQLConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FaceRecognition {
    public static void main(String[] args) throws InterruptedException, NoSuchPortException, IOException, ClassNotFoundException, SQLException {
        MongoConnection db = new MongoConnection();
        db.getUser();
        
        SQLConnection sqlDatabase = new SQLConnection();
        sqlDatabase.getUsers();
        
        new Thread(new ArduinoInterrupt()).start();
    } 
    
    public static class ArduinoInterrupt implements Runnable {
        @Override
        public void run() {
            ArduinoConnection connect = new ArduinoConnection();
            connect.setPort("/dev/ttyACM0");
            try {
                connect.initializeConection();
            } catch (NoSuchPortException ex) {
                Logger.getLogger(FaceRecognition.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(FaceRecognition.class.getName()).log(Level.SEVERE, null, ex);
            }
            connect.sendData("start");
            String response = "no";
            while (response.equals("no")) {
                response = connect.receiveData();
            }
            System.out.println(response);
            System.out.println("Ya esta");
            try {
                connect.close();
            } catch (IOException ex) {
                Logger.getLogger(FaceRecognition.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
