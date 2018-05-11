/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.FaceFrame;

/**
 *
 * @author giovanni
 */
public class MsgThread implements Runnable {

    private String[] messages; 
    private final FaceFrame frame;
    
    public MsgThread(String[] messages, FaceFrame frame) throws SQLException {
        this.messages = messages;
        this.frame = frame;
    }
    
    
    @Override
    public void run() {
        System.out.println("Messages length = " + messages.length);
        if(messages.length > 0) {
            for (String message : messages) {
                frame.changeMessage(message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        } else {
            frame.changeMessage("You have no message at the moment");
        }
    }
}
