
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
    private final MongoCollection coll;
    
    public MongoConnection() {
        mongoURI = new MongoClientURI("mongodb://admin:admin@ds233238.mlab.com:33238/recognition");
        mongoClient = new MongoClient(mongoURI);
        db = mongoClient.getDatabase("recognition");
        coll = db.getCollection("users");
    }
    
    public Person[] getUser() {
        MongoCursor<Document> cursor = coll.find().iterator();
        Person[] user = new Person[(int) coll.count()];
        
        System.out.println("length: " + user.length);
        //Person user = new Person();
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
    
    public long getUserCount() {
        return coll.count();
    }
    
}
