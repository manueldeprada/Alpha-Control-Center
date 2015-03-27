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
   
    
    Runnable recorder = new Runnable() {

    public void run() {
       
        Color now = picker.getColor();
        
        if (saved.getRGB() == now.getRGB()){
            
        }else{
            
            saved = now;
            String r = Integer.toString(now.getRed());
            String g = Integer.toString(now.getGreen());
            String b = Integer.toString(now.getBlue());
            
            config.setValue("c"+i+"r", r,file);
            config.setValue("c"+i+"g", g,file);
            config.setValue("c"+i+"b", b,file);
            config.setValue("t"+i, Long.toString(ch.getMilliseconds()),file);
            i++;
            
            
        }
        
        
        
    }
};
   
    Runnable player = new Runnable() {

    public void run() {
        
        int r =Integer.parseInt(config.getValue("c"+i+"r", file));
            int g =Integer.parseInt(config.getValue("c"+i+"g", file));
            int b =Integer.parseInt(config.getValue("c"+i+"b", file));
            
                    Color now = new Color(r, g, b);

       
        long timeNext = Long.parseLong(config.getValue("t"+i, file));
                    System.out.println(ch.getMilliseconds());

        if (ch.getMilliseconds() > timeNext){

             
            picker.setColor(now);
            
           
            i++;
            System.out.println("go");
            
            
        }
        
        
        
    }
};

    
    ScheduledExecutorService executor = 
            Executors.newScheduledThreadPool(1);
    
    public void record(ColorPicker picker1, int number){
        file = new File(path + "/secuence"+number+".properties");
        file.setWritable(true);
        try {       file.createNewFile();    } catch (IOException ex) {   Logger.getLogger(secuences.class.getName()).log(Level.SEVERE, null, ex); }
        Color initial = picker1.getColor();
        config.setValue("initialR", Integer.toString(initial.getRed()), file);
        config.setValue("initialG", Integer.toString(initial.getGreen()), file);
        config.setValue("initialB", Integer.toString(initial.getBlue()), file);
        picker = picker1; 
        saved = initial;
        ch.start();
        executor.scheduleAtFixedRate(recorder, 0, 10, TimeUnit.MILLISECONDS);
          
        
                    
                    

    }
    public void play(ColorPicker picker1, int number){
        file = new File(path + "/secuence"+number+".properties");
        
        
        int initR = Integer.parseInt(config.getValue("initialR", file));
        int initG = Integer.parseInt(config.getValue("initialG", file));
        int initB = Integer.parseInt(config.getValue("initialB", file)); 
        Color initial = new Color(initR, initG, initB);
        picker1.setColor(initial);
        picker = picker1; 
        saved = initial;
        ch.start();
        executor.scheduleAtFixedRate(player, 0, 10, TimeUnit.MILLISECONDS);
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
                end = System.currentTimeMillis();

        return end-begin;
    }
 
    public long getMilliseconds() {
        end = System.currentTimeMillis();
        return end-begin;
    }
 
    public double getSeconds() {
                end = System.currentTimeMillis();

        return (end - begin) / 1000.0;
    }
 
    public double getMinutes() {
                end = System.currentTimeMillis();

        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
                end = System.currentTimeMillis();

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
