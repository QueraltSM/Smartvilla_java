package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    public Connection connection;
    
    public SQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:/home/giovanni/Documentos/ITQ/users");
    }
    
    public ResultSet getUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
        return rs;
    }
    
    public void setUser(Person person) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO USERS (email, password, name, surname, last_visit) " + "VALUES ('" + person.getEmail() + "', '"
        + person.getPassword() + "', '" + person.getUsername() + "', '" + person.getSurname() + "', '" + person.getLastVisit() + "');");
    }
    
    public void setMessages(Message message) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO MESSAGES (sender, receiver, message) " + "VALUES ('" + message.getFrom() +
                "', '" + message.getTo() + "', '" + message.getMessage() + "');");
    }
    
    public void deleteUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM USERS");
    }
    
    public void updateUsers(MongoConnection db) throws SQLException {
        this.deleteUsers();
        for (Person user1 : db.getUser()) {
            this.setUser(user1);
        }
    }
    
    public void deleteMessages() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM MESSAGES");
    }
    
    public void updateMessages(MongoConnection db) throws SQLException {
        this.deleteMessages();
        for (Message message1 : db.getMessages()) {
            this.setMessages(message1);
        }
    }
    
    public String[] getUserMessages (String receiver) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet count = statement.executeQuery("SELECT COUNT(*) FROM MESSAGES WHERE RECEIVER = " + receiver);
        int length = 0;
        if (count.next()) length = count.getInt(1);
        ResultSet rs = statement.executeQuery("SELECT * FROM MESSAGES WHERE RECEIVER = "+ receiver);
        String[] messages = new String[length];
        int i = 0;
        while (rs.next()) {
            messages[i] = rs.getString("message");
            i++;
        }
        return messages;
    }
    
    public void deletePhoto(Person[] users) throws SQLException, IOException {
        ResultSet sqlUsers = this.getUsers();
        boolean found;
        while (sqlUsers.next()) {
            found = false;
            for (Person user : users)
                if (user.getEmail().equals(sqlUsers.getString("email"))) found = true;
            if (!found) {
                System.out.println("/home/giovanni/Dropbox/Aplicaciones/SmartVilla/" + sqlUsers.getString("name") +
                        " " + sqlUsers.getString("surname") + ".jpg");
                Files.deleteIfExists(Paths.get(stringPath));
            }
        }
    }
}