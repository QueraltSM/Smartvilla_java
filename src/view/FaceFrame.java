/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author danie
 */
public class FaceFrame extends JFrame{
    private LogoPanel logo;
    private LogoPanel logo1;
    
    private MsgPanel   pMsg;
    private GifPanel   pGif;
    private InfoPanel  pInfo;
    private ClockPanel pClock;
    private TitlePanel pTitle;
    private InfoHeaderPanel pInfoHeader;
    private MsgHeaderPanel  pMsgHeader;
    
    public FaceFrame(){
        initComponents();
    }
    
    public void changeImage(String path){
        pGif.changeImage(path);
    }  
    
    public void changeTitle(String str){
        pTitle.ChangeTitle(str);
    }
    
    public void changeMessage(String str) {
        pMsg.setMessage(str);
    }
    
    private void initComponents() {
        double margin = 0.12;
        
        // <editor-fold defaultstate="collapsed" desc="Instantiation"> 
                
        Responsiveness r = new Responsiveness(this);

        pMsg   = new MsgPanel();
        pGif   = new GifPanel();
        pInfo  = new InfoPanel();
        pClock = new ClockPanel();
        pTitle = new TitlePanel();
        pInfoHeader = new InfoHeaderPanel();
        pMsgHeader  = new MsgHeaderPanel();
        
        // </editor-fold>
        
        r.FrameSize(0.7);
        r.PositionsX(basicProps(this, pTitle), 0.5, 60);
        
        r.PositionsX(basicProps(this, pMsg), margin, 250);
        r.PositionsX(basicProps(this, pMsgHeader), margin, 200);
       
        r.PositionsX(basicProps(this, pInfo), 1 - margin, 250);
        r.PositionsX(basicProps(this, pInfoHeader), 1 - margin, 200);

        r.PositionsXY(basicProps(this, pClock), 0.5, 0.9);
        r.PositionsXY(basicProps(this, pGif), 0.5, 0.5);
        
    }
    
    static JPanel basicProps(JFrame frame, JPanel panel){
        panel.setVisible(true);
        panel.setSize(panel.getPreferredSize());
        
        return panel;
    }
    
}
