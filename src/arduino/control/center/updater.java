/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center;

import arduino.control.center.utils.config;
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
            URL url = new URL("http://cm.manueldeprada.com/files/info.txt");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String str1;
                while ((str1 = in.readLine()) != null) {
                    data = data + str1 + System.getProperty("line.separator");
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            error = true;
            Logger.getLogger(updater.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void setIndicators(JLabel label1, JProgressBar bar1){
        
    }
    
    JLabel label;
    String data = "";
    boolean error = false;
    
}
