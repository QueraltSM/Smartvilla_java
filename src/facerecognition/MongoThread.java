package facerecognition;

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
    }
    
    @Override
    public void run() {
        while (true) {
            if (mongoConnection.getUserCount() != count) {
                
                try {
                    sqlConnection.updateUsers(mongoConnection);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                try {
                    pythonConnection.send("update");
                } catch (IOException e) {
                    System.out.println(e);   
                }
                
                count = mongoConnection.getUserCount();
            }
            
            if (mongoConnection.getMessageCount() != messageCount) {
                
                try {
                    sqlConnection.updateMessages(mongoConnection);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                messageCount = mongoConnection.getMessageCount();
            }
        }
    }
}
