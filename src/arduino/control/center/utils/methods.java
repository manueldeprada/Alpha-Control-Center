/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;
import javax.swing.JSlider;
import com.bric.swing.ColorPicker;
import java.awt.Color;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextField;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author prada
 */
public class methods {
    
    
    //Variable declarations
    static int R = 0, G = 0, B = 0;
    static int Fan1, Fan2, Pump1, Pump2;
    static String OutputR, OutputG, OutputB;
    static PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); //Variable para //instanciar la librería Arduino

    
    public static void initialicePicker(ColorPicker ub){
        ub.setHexControlsVisible(false);
    }
    
        private static void SetData() {
        /*OutputR = "1";
        OutputG = "2";
        OutputB = "3";*/

        if (R < 10) {
        OutputR = OutputR + "00" + R;
        } else if (R < 100) {
        OutputR = OutputR + "0" + R;
        } else {
        OutputR = OutputR + R;
        }

        if (G < 10) {
        OutputG = OutputG + "00" + G;
        } else if (G < 100) {
        OutputG = OutputG + "0" + G;
        } else {
        OutputG = OutputG + G;
        }

        if (B < 10) {
        OutputB = OutputB + "00" + B;
        } else if (B < 100) {
        OutputB = OutputB + "0" + B;
        } else {
        OutputB = OutputB + B;
        }

}
    
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
    public static void write(int mode, ColorPicker picker, JSlider fan1slider, JSlider fan2slider, JSlider pump1slider, JSlider pump2slider) {
  
        if (mode == 0){ //normal
            
            R = picker.getColor().getRed();
            G = picker.getColor().getGreen();
            B = picker.getColor().getBlue();
            
            SetData();
                try {
                    
                    Arduino.sendData(OutputR + "," + OutputG + "," + OutputB + "," + "0" + "," + "0" + "," + "0" + ".");

                } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

                
            }else if (mode == 1){ //fade 
                try {
                    
            } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

                

            }else if (mode == 2){ //ambilight
                try {
                    
            } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }


        }
        Fan1 = fan1slider.getValue();
        Fan2 = fan2slider.getValue();
        Pump1 = pump1slider.getValue();
        Pump2 = pump2slider.getValue();
        
        try {
            Arduino.sendData(Integer.toString(Fan1));
            Arduino.sendData(Integer.toString(Fan2));
            Arduino.sendData(Integer.toString(Pump1));
            Arduino.sendData(Integer.toString(Pump2));
            } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

}
    
    
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