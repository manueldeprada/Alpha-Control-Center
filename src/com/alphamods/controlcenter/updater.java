/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphamods.controlcenter;

import com.alphamods.controlcenter.utils.config;
import com.alphamods.controlcenter.utils.methods;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Manu
 */
public class updater extends Thread{
    @Override
    public void run(){
        error = false;
    try {
            URL url = new URL("https://alphamods.com/files/info.txt");
               URLConnection uc;

            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", 
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

   uc.connect();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
                String str1;
                while ((str1 = in.readLine()) != null) {
                    data = data + str1 + System.getProperty("line.separator");
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            try {
                error = true;
                bar.setVisible(false);
                label.setText("Error connecting to server.");
                Thread.sleep(5000);
                label.setVisible(false);
                Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex1) {
                Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    check();
    }
    
    private void check(){
        double netversion = Double.parseDouble(config.getNetValue("version", data));
        if (netversion == methods.getversion()) {
            try {
                bar.setVisible(false);
                label.setText("No updates found");
                Thread.sleep(5000);
                label.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else if(netversion > methods.getversion()){
               bar.setVisible(false);     
               label.setVisible(false);
               
               
               UpdaterGUI gui = new UpdaterGUI(frame, true, data);
               gui.setVisible(true);
               gui.requestFocusInWindow();

            
        }
        } 
    
    public void setIndicators(JLabel label1, JProgressBar bar1, JFrame frame1){
        label = label1;
        bar = bar1;
        frame = frame1;
    }
    JProgressBar bar;
    JLabel label;
    String data = "";
    boolean error = false;
    JFrame frame;
    
}
