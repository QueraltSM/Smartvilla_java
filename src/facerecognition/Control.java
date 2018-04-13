package facerecognition;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.MongoConnection;
import model.PythonConnection;
import model.SQLConnection;
import view.FaceFrame;

public class Control {

    private final PythonConnection python;
    private final FaceFrame frame;
    private final MongoConnection mongo;
    private final SQLConnection sql;
    private String command;

    public Control(PythonConnection python, FaceFrame frame, MongoConnection mongo, SQLConnection sql) throws InterruptedException, IOException {
        this.python = python;
        this.frame = frame;
        this.mongo = mongo;
        this.sql = sql;
        this.command = "start";
        frame.setVisible(true);
        
        Runnable mongoThread = new MongoThread(mongo, sql, python);
        new Thread(mongoThread).start();
        
        while (true) {
            recognize();
        }
    }
    
    public void recognize() throws InterruptedException, IOException {
    int py = 0;
    String res = "";
    try {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> result = service.submit(new ArduinoThread());
        if (result.get() == 1) {
            py = 1;
        }
        service.shutdown();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    if (py == 1) {
        python.send(command);
        res = python.receive();
        System.out.println(res);
        if(command.equals("start")){
            command = "continue";
        }
    }
    if(!"NoOne".equals(res) && !"Unknown".equals(res)){
        frame.changeImage("/home/giovanni/Dropbox/Aplicaciones/SmartVilla/" + res + ".jpg");
        frame.changeTitle("Welcome " + res);
        Thread.sleep(10000);
        frame.changeImage("restore");
        frame.changeTitle("Welcome to Smart Villa");
    }else if(res.equals("Unknown")){
        command = "start";
    }
}

}
