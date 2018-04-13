/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.JFrame;


/**
 *
 * @author danie
 */
public class FaceFrame extends JFrame{
    private TitlePanel title;
    private MsgHeaderPanel msgTitle;
    private InfoHeaderPanel infoTitle;
    private GifPanel gif;
    private ClockPanel clock;
    private InfoPanel infoPanel;
    private MsgPanel msgPanel;
    private LogoPanel logo;
    private LogoPanel logo1;
    
    public FaceFrame(){
        initComponents();
    }
    
    public void changeImage(String path){
        gif.changeImage(path);
    }  
    
    public void changeTitle(String str){
        title.ChangeTitle(str);
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        this.getContentPane().setBackground(new Color(239,246,238));
        
        title = new TitlePanel();
        msgTitle = new MsgHeaderPanel();
        infoTitle = new InfoHeaderPanel();
        gif = new GifPanel();
        clock = new ClockPanel();
        infoPanel = new InfoPanel();
        msgPanel = new MsgPanel();
        logo = new LogoPanel();
        logo1 = new LogoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        //gridBagConstraints.weighty = 1;
        gridBagConstraints.gridwidth = 3;
        //gridBagConstraints.anchor = gridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        getContentPane().add(title, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(msgTitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(gif, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        getContentPane().add(infoTitle, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(msgPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(infoPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(clock, gridBagConstraints);
        
        /*gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(logo, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(logo1, gridBagConstraints);*/
        
        pack();
    }
    
}
