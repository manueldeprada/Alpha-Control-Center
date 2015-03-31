/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;
import javax.swing.JSlider;
import com.bric.swing.ColorPicker;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Window;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author prada
 */
public class methods {
    
    
    //Variable declarations
    static int R = 0, G = 0, B = 0;
    static int Fan1, Pump1, Pump2;
    static String OutputR, OutputG, OutputB;
    //static PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); //Variable para //instanciar la librería Arduino
    IODevice device;
    
    
    
    public void writeFirmata(){
        try {
            
            
            device.sendMessage(Integer.toString(R) + Integer.toString(G)+ Integer.toString(B));
        
        
        } catch (IOException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void startFirmata(JComboBox box){
        device = new FirmataDevice(box.getSelectedItem().toString());
        try {
            device.ensureInitializationIsDone();
        } catch (InterruptedException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public static void initialicePicker(ColorPicker picker){
        picker.setHexControlsVisible(false);
         if (config.getValue("colorR") == null){
            picker.setColor(Color.white);
            }
            else {
            int rx = Integer.parseInt(config.getValue("colorR"));
            int rg = Integer.parseInt(config.getValue("colorG"));
            int rb = Integer.parseInt(config.getValue("colorB"));

            Color x = new Color(rx,rg,rb);
            picker.setColor(x);
        }
        
    }
    
        private static void SetData() {

        if (R < 10) {
        OutputR = "00" + R;
        } else if (R < 100) {
        OutputR = "0" + R;
        } else {
        OutputR = Integer.toString(R);
        }

        if (G < 10) {
        OutputG = "00" + G;
        } else if (G < 100) {
        OutputG = "0" + G;
        } else {
        OutputG = Integer.toString(G);
        }

        if (B < 10) {
        OutputB = "00" + B;
        } else if (B < 100) {
        OutputB = "0" + B;
        } else {
        OutputB = Integer.toString(B);
        }

}
   /*
    public static void receive(JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, JTextField jTextField8){
    if (Arduino.isMessageAvailable()){
        
        String message = Arduino.printMessage();
        
        if (message.contains("Fan 1")){
        jTextField5.setText(message);
        }
        else if (message.contains("Fan 2")){
        jTextField6.setText(message);
        }
        else if (message.contains("Pump 1")){
        jTextField7.setText(message);
        }
        else if (message.contains("Pump 2")){
        jTextField8.setText(message);
        }
       }
    }
    
    */
    
   /* 
    public static void write(int mode, ColorPicker picker, JSlider fan1slider, JSlider fan2slider, JSlider pump1slider, JSlider pump2slider) {
  
        if (mode == 0){ //normal
            
            R = picker.getColor().getRed();
            G = picker.getColor().getGreen();
            B = picker.getColor().getBlue();
            
            SetData();
                try {
                    
                    Arduino.sendData(OutputR + OutputG + OutputB + Integer.toString(Fan1) + Integer.toString(Pump1) + Integer.toString(Pump2));

                } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

                
            }else if (mode == 1){ //fade 
                try {
                    
            } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

                

            }else if (mode == 2){ //ambilight
                try {
                    
            } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }


        }
        
        Fan1 = fan1slider.getValue();
        Pump1 = pump1slider.getValue();
        Pump2 = pump2slider.getValue();

}
  */  
    
    public static void loadpreview(int number, JPanel panel){
        if ("".equals(config.getValue("color"+number+"R")) || (config.getValue("color"+number+"R")) == null){
        panel.setBackground(null);
        }else {
if (config.getValue("color"+number+"R")!= null || !"und".equals(config.getValue("color"+number+"R")) ){
            int colorR = Integer.parseInt(config.getValue("color"+number+"R"));
            int colorG = Integer.parseInt(config.getValue("color"+number+"G"));
            int colorB = Integer.parseInt(config.getValue("color"+number+"B"));
        Color color = new Color(colorR,colorG,colorB);
        panel.setBackground(color);
}
        }
    
    }
    public static void cleanFavourites(){
        for (int i= 0; i <11; i++){ 
            config.setValue("color"+i+"R", "");
            config.setValue("color"+i+"G", "");
            config.setValue("color"+i+"B", "");
            
        }
    }    
}