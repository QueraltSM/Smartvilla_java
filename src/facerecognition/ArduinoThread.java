package facerecognition;

import gnu.io.NoSuchPortException;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ArduinoConnection;

public class ArduinoThread implements Callable<Integer> {
    
    @Override
    public Integer call() throws Exception {
        ArduinoConnection connect = new ArduinoConnection();
        int result = 0;
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
        result = 1;
        try {
            connect.close();
        } catch (IOException ex) {
            Logger.getLogger(FaceRecognition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
