package facerecognition;

import java.util.concurrent.TimeUnit;
import model.ArduinoConnection;

public class FaceRecognition {
    public static void main(String[] args) throws InterruptedException {
        ArduinoConnection connect = new ArduinoConnection();
        connect.setPort("/dev/ttyACM0");
        connect.initializeConection();
        TimeUnit.SECONDS.sleep(2);
        String response = "no";
        while(response.equals("no")){
            connect.sendData("H");
            response = connect.receiveData();
        }
        System.out.println("La respuesta es: " + response);
    } 
}
