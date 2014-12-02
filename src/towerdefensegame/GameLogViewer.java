/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Game Log Viewer
 * @author Rahul K Kikani
 */
public class GameLogViewer extends JFrame {
    
    public static JPanel ITpanel;
    public static JPanel CTpanel;
    public static JPanel Wpanel;
    public static JPanel GGpanel;
    
    public static JScrollPane ITScroller;
    public static JScrollPane CTScroller;
    public static JScrollPane WScroller;
    public static JScrollPane GGScroller;
    
    public static JTextPane ITjtp;
    public static JTextArea CTjtp;
    public static JTextArea Wjtp;
    public static JTextArea GGjtp;
    
    public static boolean testFlag = false;
    
    /**
     * Initialized Game Lob Viewer
     */
    public GameLogViewer(){
        this.setSize(600,700);
        this.setTitle("Game Log Console");
        this.setResizable(false);
        
        
        ITpanel = new JPanel();
        CTpanel = new JPanel();
        Wpanel = new JPanel();
        GGpanel = new JPanel();
        
        ITjtp = new JTextPane();
        CTjtp = new JTextArea();
        Wjtp = new JTextArea();
        GGjtp = new JTextArea();
        
        ITjtp.setEditable(false);
        CTjtp.setEditable(false);
        Wjtp.setEditable(false);
        GGjtp.setEditable(false);
        
        Wjtp.setForeground(Color.white);
        
        CTjtp.setLineWrap(true);
        Wjtp.setLineWrap(true);
        GGjtp.setLineWrap(true);
        
        this.setLayout(new GridLayout(2, 2, 5, 5));
        
        ITjtp.setBackground(Color.red);
        CTjtp.setBackground(Color.green);
        Wjtp.setBackground(Color.blue);
        GGjtp.setBackground(Color.yellow);
        
        
        ITpanel.setLayout(new GridLayout(0, 1, 5, 5));
        CTpanel.setLayout(new GridLayout(0, 1, 5, 5));
        Wpanel.setLayout(new GridLayout(0, 1, 5, 5));
        GGpanel.setLayout(new GridLayout(0, 1, 5, 5));
        
        ITScroller = new JScrollPane();        
        ITScroller.setBorder(BorderFactory.createTitledBorder("Individual Tower Log"));
        ITScroller.setViewportView(ITjtp);
        ITpanel.add(ITScroller);
        
        CTScroller = new JScrollPane();        
        CTScroller.setBorder(BorderFactory.createTitledBorder("Collective Tower Log"));
        CTScroller.setViewportView(CTjtp);
        CTpanel.add(CTScroller);
        
        WScroller = new JScrollPane();        
        WScroller.setBorder(BorderFactory.createTitledBorder("Wave Log"));
        WScroller.setViewportView(Wjtp);
        Wpanel.add(WScroller);
        
        GGScroller = new JScrollPane();        
        GGScroller.setBorder(BorderFactory.createTitledBorder("Global Game Log"));
        GGScroller.setViewportView(GGjtp);
        GGpanel.add(GGScroller);
        
        this.add(ITpanel);
        this.add(CTpanel);
        this.add(Wpanel);
        this.add(GGpanel);
        
        this.setVisible(true);
    }
    
    /**
     * Update Global Game Log Panel
     * @param str Global Game Log
     */
    public static void addText_GGjtp(String str){
        if(!testFlag){
            GGjtp.append(str);
            JScrollBar vbar = GGScroller.getVerticalScrollBar();
            vbar.setValue(vbar.getMaximum());
            vbar.paint(vbar.getGraphics());
        }
    }
    
    /**
     * Update Collective tower log Panel
     * @param str Collective tower log
     */
    public static void addText_CTjtp(String str){
        if(!testFlag){
            CTjtp.append(str);
            JScrollBar vbar = CTScroller.getVerticalScrollBar();
            vbar.setValue(vbar.getMaximum());
            vbar.paint(vbar.getGraphics());
        }
    }
    
    /**
     * Update Individual tower Log Panel
     * @param str Individual tower Log
     */
    public static void addText_ITjtp(String str){
        if(!testFlag){
            ITjtp.setText(str);
            JScrollBar vbar = ITScroller.getVerticalScrollBar();
            vbar.setValue(vbar.getMaximum());
            vbar.paint(vbar.getGraphics());
        }
    }
    
    /**
     * Update Wave Log Panel
     * @param str Wave Log
     */
    public static void addText_Wjtp(String str){
        if(!testFlag){
            Wjtp.append(str);
            JScrollBar vbar = WScroller.getVerticalScrollBar();
            vbar.setValue(vbar.getMaximum());
            vbar.paint(vbar.getGraphics());
        }
    }
}
