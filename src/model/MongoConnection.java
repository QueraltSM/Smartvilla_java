
package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import java.util.Set;
import org.bson.Document;

public class MongoConnection {
    public MongoClientURI mongoURI;
    public MongoClient mongoClient;
    public MongoDatabase db;
    public MongoCollection coll;
    
    public MongoConnection() {
        mongoURI = new MongoClientURI("mongodb://admin:admin@ds233238.mlab.com:33238/recognition");
        mongoClient = new MongoClient(mongoURI);
        db = mongoClient.getDatabase("recognition");
        coll = db.getCollection("users");
    }
    
    public void getUser() {
        FindIterable<Document> myDoc = coll.find();
        for (Document doc : myDoc) {
            System.out.println("Username: " + doc.getString("name"));
        }
    }
    
    public long getUserCount() {
        return coll.count();
    }
    
}
