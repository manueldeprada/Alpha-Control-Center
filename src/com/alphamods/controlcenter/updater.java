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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            URL url = new URL("http://alphamods.com/files/info.txt");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
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

            
        }
        } 
    
    public void setIndicators(JLabel label1, JProgressBar bar1){
        label = label1;
        bar = bar1;
    }
    JProgressBar bar;
    JLabel label;
    String data = "";
    boolean error = false;
    
}
