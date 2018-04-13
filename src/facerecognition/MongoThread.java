package facerecognition;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import model.MongoConnection;
import model.PythonConnection;
import model.SQLConnection;

public class MongoThread implements Runnable {
    private final MongoConnection mongoConnection;
    private final SQLConnection sqlConnection;
    private final PythonConnection pythonConnection;
    
    private long count;
    private long messageCount;
    
    public MongoThread(MongoConnection mongoConnection, SQLConnection sqlConnection, PythonConnection pythonConnection) {
        this.mongoConnection = mongoConnection;
        this.sqlConnection = sqlConnection;
        this.pythonConnection = pythonConnection;
        this.count = 0;
        this.messageCount = 0;
    }
    
    @Override
    public void run() {
        while (true) {
            if (mongoConnection.getUserCount() != count) {
                try {
                    if (mongoConnection.getUserCount() < count) { //A user has been deleted, so it has to be its photo
                        sqlConnection.deletePhoto(mongoConnection.getUser());
                    }
                    sqlConnection.updateUsers(mongoConnection);
                    System.out.println("Users table has been updated");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                count = mongoConnection.getUserCount();
                try {
                    File directory = new File("/home/giovanni/Dropbox/Aplicaciones/SmartVilla/");
                    int fileCount = directory.list().length;
                    System.out.println("Número de ficheros " +  fileCount);
                    while ((fileCount - 1) != count) {
                        fileCount = directory.list().length;
                    }
                    pythonConnection.send("update");
                } catch (IOException e) {
                    System.out.println(e);   
                }
            }
            
            if (mongoConnection.getMessageCount() != messageCount) {
                
                try {
                    sqlConnection.updateMessages(mongoConnection);
                    System.out.println("Messages table has been updated");
                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                messageCount = mongoConnection.getMessageCount();
            }
        }
    }
}
