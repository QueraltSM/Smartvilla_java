package facerecognition;

import gnu.io.NoSuchPortException;
import model.MongoConnection;
import model.SQLConnection;
import java.io.IOException;
import java.sql.SQLException;
import model.PythonConnection;
import view.FaceFrame;

public class FaceRecognition {
    private static MongoConnection db;
    private static PythonConnection python;
    private static SQLConnection sqlDatabase;
    private static boolean update;
    
    
    public static void main(String[] args) throws InterruptedException, NoSuchPortException, IOException, ClassNotFoundException, SQLException {
        new Control(new PythonConnection(10000), new FaceFrame(), new MongoConnection(), new SQLConnection());
    }
    
    
}
