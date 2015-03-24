/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;

import com.bric.swing.ColorPicker;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prada
 */
public class secuences {
   
    
    Runnable reader = new Runnable() {

    public void run() {
        if (saved == picker.getColor()){
            
        }else{
            Color now = picker.getColor();
            String r = Integer.toString(now.getRed());
            String g = Integer.toString(now.getGreen());
            String b = Integer.toString(now.getBlue());
            
            config.setValue("c"+i+"r", r,file);
            config.setValue("t"+i, Long.toString(ch.getMilliseconds()),file);
            i++;
            
        }
        
        
        
    }
};
   

    
    ScheduledExecutorService executor = 
            Executors.newScheduledThreadPool(1);
    
    public void make(ColorPicker picker1, int number){
        file = new File(path + "secuence"+number+".properties");
        file.setWritable(true);
        try {       file.createNewFile();    } catch (IOException ex) {   Logger.getLogger(secuences.class.getName()).log(Level.SEVERE, null, ex); }
        Color initial = picker1.getColor();
        config.setValue("initialR", Integer.toString(initial.getRed()), file);
        config.setValue("initialG", Integer.toString(initial.getGreen()), file);
        config.setValue("initialB", Integer.toString(initial.getBlue()), file);
        picker = picker1; 
        saved = initial;
        ch.start();
        executor.scheduleAtFixedRate(reader, 0, 10, TimeUnit.MILLISECONDS);
          
        
                    
                    

    }
    public final class Chronometer{
    private long begin, end;
 
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public double getSeconds() {
        return (end - begin) / 1000.0;
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
    
    
    }
      
       public static String path = System.getProperty("user.dir"); 
       public ColorPicker picker = null;
       public Color saved;
       public int i = 0;
       File file = null;
       Chronometer ch = new Chronometer();
    
}
