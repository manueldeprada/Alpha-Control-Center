/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;
import javax.swing.JSlider;
import com.bric.swing.ColorPicker;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import panamahitek.Arduino.PanamaHitek_Arduino;
import panamahitek.Arduino.PanamaHitek_multiMessage;

/**
 *
 * @author prada
 */
public class methods {
    
    
    //Variable declarations
     int R = 0, G = 0, B = 0;
     int Fan1, Pump1, Pump2;
     String OutputR, OutputG, OutputB;
     String OutputFan1, OutputPump1, OutputPump2;
    private  PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); //Variable para //instanciar la librería Arduino
    private  PanamaHitek_multiMessage multi = new PanamaHitek_multiMessage(3, Arduino);
    private boolean connected = false;
    
    public  void initialicePicker(ColorPicker picker){
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
    
        private  void SetData() {

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
        private  void SetData2() {
        
        

        if (Fan1 < 10) {
        OutputFan1 = "00" + Fan1;
        } else if (Fan1 < 100) {
        OutputFan1 = "0" + Fan1;
        } else {
        OutputFan1 = Integer.toString(Fan1);
        }

        if (Pump1 < 10) {
        OutputPump1 = "00" + Pump1;
        } else if (Pump1 < 100) {
        OutputPump1 = "0" + Pump1;
        } else {
        OutputPump1 = Integer.toString(Pump1);
        }

        if (Pump2 < 10) {
        OutputPump2 = "00" + Pump2;
        } else if (Pump2 < 100) {
        OutputPump2 = "0" + Pump2;
        } else {
        OutputPump2 = Integer.toString(Pump2);
        }

}
    
    
    public void write(int mode, ColorPicker picker, JSlider fan1slider, JSlider pump1slider, JSlider pump2slider) {
  
        if (mode == 0){ //normal
            
            R = picker.getColor().getRed();
            G = picker.getColor().getGreen();
            B = picker.getColor().getBlue();
            Fan1 = fan1slider.getValue()*255/100;
            Pump1 = pump1slider.getValue()*255/100;
            Pump2 = pump2slider.getValue()*255/100;
            
            SetData();
            SetData2();
                               
            }else if (mode == 1){ //fade 
               
            }else if (mode == 2){ //ambilight
               
        }
        
        
        String send = OutputR + OutputG + OutputB + OutputFan1 + OutputPump1 + OutputPump2;
        try {
                    System.out.println(send);
                    Arduino.sendData(send);

                } catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }

}
    
    public PanamaHitek_Arduino getArduino(){
        return Arduino;
    }
    
    public PanamaHitek_multiMessage getMulti(){
        return multi;
    }
    
    
    public boolean isConnected(){
        return connected;
    }
    
    public void connect(SerialPortEventListener evento, JComboBox PortsBox){
        
         try {
             Arduino.arduinoRXTX(PortsBox.getSelectedItem().toString(), 9600, evento);
             connected = true;
         } catch (Exception ex) {
             Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    public void disconnect(){
         try {
             Arduino.killArduinoConnection();
             connected = false;
         } catch (Exception ex) {
             Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public String[] getPorts() {
        List<String> ports = Arduino.getSerialPorts();
        String[] array = ports.toArray(new String[ports.size()]);
        return array;

    }
    public void loadpreview(int number, JPanel panel){
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
    public void cleanFavourites(){
        for (int i= 0; i <11; i++){ 
            config.setValue("color"+i+"R", "");
            config.setValue("color"+i+"G", "");
            config.setValue("color"+i+"B", "");
            
        }
    }    
}