package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PythonConnection {
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private final int serverPort;

    public PythonConnection(int serverPort) throws IOException {
        this.serverPort = serverPort;
        this.clientSocket = new Socket("localhost", this.serverPort);
        this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
        this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    public void send(String message) throws IOException{
        outToServer.writeUTF(message);
    }
    
    public String receive() throws IOException{
        return inFromServer.readLine();
    }
    
}
