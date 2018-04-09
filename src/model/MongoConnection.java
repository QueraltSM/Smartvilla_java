
package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import org.bson.Document;

public class MongoConnection {
    private final MongoClientURI mongoURI;
    private final MongoClient mongoClient;
    private final MongoDatabase db;
    private final MongoCollection userColl;
    private final MongoCollection messageColl;
    
    public MongoConnection() {
        mongoURI = new MongoClientURI("mongodb://admin:admin@ds233238.mlab.com:33238/recognition");
        mongoClient = new MongoClient(mongoURI);
        db = mongoClient.getDatabase("recognition");
        userColl = db.getCollection("users");
        messageColl = db.getCollection("messages");
    }
    
    public Person[] getUser() {
        MongoCursor<Document> cursor = userColl.find().iterator();
        Person[] user = new Person[(int) userColl.count()];
        int i = 0;
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            //Iterator it = doc.keySet().iterator();
            Person actualUser = new Person();
            doc.keySet().forEach((key) -> {
                actualUser.setPerson(key, doc.get(key));
            });
            user[i] = actualUser;
            i++;
        }
        return user;
    }
    
    public Message[] getMessages() {
        MongoCursor<Document> cursor = messageColl.find().iterator();
        Message[] message = new Message[(int) messageColl.count()];
        
        int i = 0;
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Message actualMessage = new Message();
            doc.keySet().forEach((key) -> {
                actualMessage.setMessage(key, doc.get(key));
            });
            message[i] = actualMessage;
            i++;
        }
        return message;
    }
    
    public long getUserCount() {
        return userColl.count();
    }
    
    public long getMessageCount() {
        return messageColl.count();
    }
    
}
