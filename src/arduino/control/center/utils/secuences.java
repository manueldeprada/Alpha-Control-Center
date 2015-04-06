/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;

import com.bric.swing.ColorPicker;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

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
            config.setValue("t"+i, Long.toString(chr.getMilliseconds()),file);
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
if (chp.getTime()>Long.parseLong(config.getValue("endTime", file))){
        System.out.println("biennn");
        playerexecutor.shutdown();
        }
        if (chp.getMilliseconds() > timeNext){

             
            picker.setColor(now);
            
           
            i++;
            
            
        }
        if (chp.getTime()>Long.parseLong(config.getValue("endTime", file))){
        System.out.println("biennn");
        playerexecutor.shutdown();
        }
        
    }
};

    
    ScheduledExecutorService recorderexecutor = 
            Executors.newScheduledThreadPool(1);
    ScheduledExecutorService playerexecutor = 
            Executors.newScheduledThreadPool(1);
    
    public void recorderStop(JPanel panel){
        recorderexecutor.shutdown();
        config.setValue("endTime", Long.toString(chr.getTime()), file);
        config.setValue("iFinal", Integer.toString(i), file);
        chr.stop();
        
        for(int u= 0;u<100;u++){
            int period = i/100;
            int rd = Integer.parseInt(config.getValue("c"+period*u+"r", file));
            int gd = Integer.parseInt(config.getValue("c"+period*u+"g", file));
            int bd = Integer.parseInt(config.getValue("c"+period*u+"b", file));
                    panel.getComponent(u).setBackground(new Color(rd,gd,bd));

        }
        
        
        
    }
    
    public void playerStop(){
        playerexecutor.shutdown();
        chp.stop();
    }
    public static void clean(int num){
        PrintWriter writer = null;   
    try {
        File file = new File(path + File.separator+"secuence"+num+".properties");
        writer = new PrintWriter(file);
        writer.print("");
        writer.close();
// TODO add your handling code here:
    
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(secuences.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        writer.close();
    }
    }
    
    public void record(ColorPicker picker1, int number){
        file = new File(path + File.separator+"secuence"+number+".properties");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(secuences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clean(number);
        file.setWritable(true);
        try {       file.createNewFile();    } catch (IOException ex) {   Logger.getLogger(secuences.class.getName()).log(Level.SEVERE, null, ex); }
        Color initial = picker1.getColor();
        config.setValue("initialR", Integer.toString(initial.getRed()), file);
        config.setValue("initialG", Integer.toString(initial.getGreen()), file);
        config.setValue("initialB", Integer.toString(initial.getBlue()), file);
        picker = picker1; 
        saved = initial;
        chr.start();
        recorderexecutor.scheduleAtFixedRate(recorder, 0, 10, TimeUnit.MILLISECONDS);
          
        
                    
                    

    }
    public void play(ColorPicker picker1, int number){
        file = new File(path + File.separator+"secuence"+number+".properties");
        
        
        int initR = Integer.parseInt(config.getValue("initialR", file));
        int initG = Integer.parseInt(config.getValue("initialG", file));
        int initB = Integer.parseInt(config.getValue("initialB", file)); 
        Color initial = new Color(initR, initG, initB);
        picker1.setColor(initial);
        picker = picker1; 
        saved = initial;
        chp.start();
        playerexecutor.scheduleAtFixedRate(player, 0, 10, TimeUnit.MILLISECONDS);
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
              Chronometer chr = new Chronometer();
                     Chronometer chp = new Chronometer();


}
