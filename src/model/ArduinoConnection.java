package model;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

public class ArduinoConnection {
    private String port;
    private OutputStream output;
    private BufferedReader input;
    private SerialPort serialPort;
    private static int timeout;
    private static int dataRate;
    private boolean conectionState;
    
    public ArduinoConnection() {
        output = null;
        timeout = 2000;
        dataRate = 9600;
        conectionState = false;
    }
    
    public void setPort(String port){
        this.port = port;
    }
    
    public boolean getConectionState(){
        return conectionState;
    }
    public void initializeConection(){
        CommPortIdentifier portID=null;
        Enumeration portEnum=CommPortIdentifier.getPortIdentifiers();
        
        while(portEnum.hasMoreElements()){
            CommPortIdentifier currentPortID=(CommPortIdentifier) portEnum.nextElement();
            if(port.equals(currentPortID.getName())){
                portID=currentPortID;
                break;
            }
        }
        
        if(portID==null){
            conectionState = false;
        }
        
        try{
            serialPort = (SerialPort) portID.open(this.getClass().getName(), timeout);
            serialPort.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            conectionState = true;
            
        } catch(Exception e){
            conectionState = false;
            System.out.println("Error");
            System.out.println(e);
        }
    }
    
    public void sendData(String data){
        try{
            output.write(data.getBytes());
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String receiveData(){
        String result = "";
        try{
            result = input.readLine();
        } catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
}
