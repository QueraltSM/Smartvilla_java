package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import view.FaceFrame;


public class Responsiveness {
    private static FaceFrame frame;
    
    public Responsiveness(FaceFrame frame){
        this.frame = frame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(239,246,238));
    }
             
    public void AddToTextArea(String text, JTextArea textArea){
        textArea.setText(text);
    }

    public void FrameSize(double scale){
        // Gets the screen size.
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        int w = (int)(dim.width * scale);
        int h = (int)(dim.height * scale);

        // Sets frame size.
        frame.setSize(w, h);
        //frame.setVisible(true);
    }

    public void PositionsX(JPanel panel, double margin, int yPos){ 
        double frameWidth = frame.getSize().getWidth();
        double panelWidth = panel.getSize().getWidth();
        
        double posLabelHor = (frameWidth - panelWidth) * margin;
        
        panel.setLocation((int)posLabelHor, yPos);
        frame.add(panel);
    }   
    
    public void PositionsXY(JPanel panel, double marginX, double marginY){ 
        // Width
        double frameWidth = frame.getSize().getWidth();
        double panelWidth = panel.getSize().getWidth();
        
        // Height
        double frameHeight = frame.getSize().getHeight();
        double panelHeight = panel.getSize().getHeight();
        
        // Positions
        double posLabelHor = (frameWidth - panelWidth)* marginX;
        double posLabelVer = (frameHeight - panelHeight)* marginY;
        
        panel.setLocation((int)posLabelHor, (int)posLabelVer);
        frame.add(panel);
    }  
}
