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
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import panamahitek.Arduino.PanamaHitek_Arduino;
import panamahitek.Arduino.PanamaHitek_multiMessage;
import javax.swing.JTextField;

/**
 *
 * @author prada
 */
public class methods {
    
    
    //Variable declarations
    
     int Fan1, Fan2, Pump1;
     public static String path = System.getProperty("user.dir"); 
     String OutputFan1, OutputFan2, OutputPump1;
    private  PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); //Variable para //instanciar la librería Arduino
    private  PanamaHitek_multiMessage multi = new PanamaHitek_multiMessage(6, Arduino);
    private boolean connected = false;
    private String[] channel1 = {null,null,null};
    private String[] channel2 = {null,null,null};
    private String[] channel3 = {null,null,null};
    private String[] channel4 = {null,null,null};
     
   

    public void initialicePicker(ColorPicker picker){
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
    public void initialiceArrays(ColorPicker picker){
        
        if (config.getValue("channel1R")==null || config.getValue("channel1R")==""){
            channel1[0]= Integer.toString(picker.getColor().getRed());
        channel1[1]= Integer.toString(picker.getColor().getGreen());
        channel1[2]= Integer.toString(picker.getColor().getBlue());
        
        channel2[0]=channel1[0];
        channel2[1]=channel1[1];
        channel2[2]=channel1[2];
        
        channel3[0]=channel1[0];
        channel3[1]=channel1[1];
        channel3[2]=channel1[2];
        
        channel4[0]=channel1[0];
        channel4[1]=channel1[1];
        channel4[2]=channel1[2];
        }else{
        channel1[0]= config.getValue("channel1R");
        channel1[1]= config.getValue("channel1G");
        channel1[2]= config.getValue("channel1B");
        
        channel2[0]= config.getValue("channel2R");
        channel2[1]= config.getValue("channel2G");
        channel2[2]= config.getValue("channel2B");
        
        
        channel3[0]= config.getValue("channel3R");
        channel3[1]= config.getValue("channel3G");
        channel3[2]= config.getValue("channel3B");
        
        
        channel4[0]= config.getValue("channel4R");
        channel4[1]= config.getValue("channel4G");
        channel4[2]= config.getValue("channel4B");
        }
        
        
    }
    
    public void sliders(JSlider fan1slider, JSlider fan2slider, JSlider pump1slider){
        if (config.getValue("Fan1") == null || config.getValue("Fan2") == null || config.getValue("Pump1") == null){
        fan1slider.setValue(50);
        fan2slider.setValue(50);
        pump1slider.setValue(50);    
        }
        else{
        int F1 = Integer.parseInt(config.getValue("Fan1"));
        int F2 = Integer.parseInt(config.getValue("Fan2"));
        int P1 = Integer.parseInt(config.getValue("Pump1"));
        fan1slider.setValue(F1);
        fan2slider.setValue(F2);
        pump1slider.setValue(P1);
        }
    }
    
    public void rpm(JTextField fan1max, JTextField fan2max, JTextField pump1max){
        if (config.getValue("Fan1max") == null || config.getValue("Fan2max") == null || config.getValue("Pump1max") == null){
        fan1max.setText("0");
        fan2max.setText("0");
        pump1max.setText("0");    
        }
        else{
            String F1max = config.getValue("Fan1max");
            String F2max = config.getValue("Fan2max");
            String P1max = config.getValue("Pump1max");
        fan1max.setText(F1max);
        fan2max.setText(F2max);
        pump1max.setText(P1max);
        }
    }
    
    
    public void ports(JComboBox PortsBox){
        if (config.getValue("Port") == null){       
        }
        else{
            PortsBox.setSelectedItem(config.getValue("Port"));
        }
    }
    
    public void rpmData(JTextField rmpLabelFan1, JTextField rmpLabelFan2, JTextField rmpLabelPump1, JTextField fan1max, JTextField fan2max, JTextField pump1max, JSlider fan1slider, JSlider fan2slider, JSlider pump1slider){
        if (Integer.parseInt(fan1max.getText())>0 || Integer.parseInt(fan2max.getText())>0 || Integer.parseInt(pump1max.getText())>0){
            int rpm1 = Integer.parseInt(fan1max.getText());
            int rpm2 = Integer.parseInt(fan2max.getText());
            int rpm3 = Integer.parseInt(pump1max.getText());
            int F1S = fan1slider.getValue()*rpm1/100;
            int F2S = fan2slider.getValue()*rpm2/100;
            int P1S = pump1slider.getValue()*rpm3/100;
            String OutputRPM1 = Integer.toString(F1S);
            String OutputRPM2 = Integer.toString(F2S);
            String OutputRPM3 = Integer.toString(P1S);
            rmpLabelFan1.setText(OutputRPM1);
            rmpLabelFan2.setText(OutputRPM2);
            rmpLabelPump1.setText(OutputRPM3);
        }
    }
    
    private  String[] SetData(String[] font) {
        
        int rx = Integer.parseInt(font[0]);        
        int gx = Integer.parseInt(font[1]);
        int bx = Integer.parseInt(font[2]);
String OutputR, OutputG, OutputB;
      
        if (rx < 10) {
        OutputR = "00" + rx;
        } else if (rx < 100) {
        OutputR = "0" + rx;
        } else {
        OutputR = Integer.toString(rx);
        }

        if (gx < 10) {
        OutputG = "00" + gx;
        } else if (gx < 100) {
        OutputG = "0" + gx;
        } else {
        OutputG = Integer.toString(gx);
        }

        if (bx < 10) {
        OutputB = "00" + bx;
        } else if (bx < 100) {
        OutputB = "0" + bx;
        } else {
        OutputB = Integer.toString(bx);
        
        }
        String[] output = new String[3];
        output[0] = OutputR;
        output[1] = OutputG;
        output[2] = OutputB;
        return output;
    }

    private  void SetData2() {

        if (Fan1 < 10) {
        OutputFan1 = "00" + Fan1;
        } else if (Fan1 < 100) {
        OutputFan1 = "0" + Fan1;
        } else {
        OutputFan1 = Integer.toString(Fan1);
        }

        if (Fan2 < 10) {
        OutputFan2 = "00" + Fan2;
        } else if (Fan2 < 100) {
        OutputFan2 = "0" + Fan2;
        } else {
        OutputFan2 = Integer.toString(Fan2);
        }

        if (Pump1 < 10) {
        OutputPump1 = "00" + Pump1;
        } else if (Pump1 < 100) {
        OutputPump1 = "0" + Pump1;
        } else {
        OutputPump1 = Integer.toString(Pump1);
        }
    }
    
    
    public void write(int mode, ColorPicker picker, JSlider fan1slider, JSlider fan2slider, JSlider pump1slider, JCheckBox c1, JCheckBox c2, JCheckBox c3, JCheckBox c4) {
  String[] output1;
  String[] output2;
  String[] output3;
        if (mode == 0){ //normal
            
            if (c1.isSelected()){
                int R = picker.getColor().getRed();
            int G = picker.getColor().getGreen();
            int B = picker.getColor().getBlue();
                channel1[0] = Integer.toString(R);
                channel1[1] = Integer.toString(G);
                channel1[2] = Integer.toString(B);
                
                
            } 
            if (c2.isSelected()){
                int R = picker.getColor().getRed();
            int G = picker.getColor().getGreen();
            int B = picker.getColor().getBlue();
                channel2[0] = Integer.toString(R);
                channel2[1] = Integer.toString(G);
                channel2[2] = Integer.toString(B);
            }
            if (c3.isSelected()){
                int R = picker.getColor().getRed();
            int G = picker.getColor().getGreen();
            int B = picker.getColor().getBlue();
                channel3[0] = Integer.toString(R);
                channel3[1] = Integer.toString(G);
                channel3[2] = Integer.toString(B);
            }
            if (c4.isSelected()){
                int R = picker.getColor().getRed();
            int G = picker.getColor().getGreen();
            int B = picker.getColor().getBlue();
                channel3[0] = Integer.toString(R);
                channel3[1] = Integer.toString(G);
                channel3[2] = Integer.toString(B);
            }
            
            
            output1 = SetData(channel1);
            output2 = SetData(channel2);
            output3 = SetData(channel3);
            
            Fan1 = fan1slider.getValue()*255/100;
            Fan2 = fan2slider.getValue()*255/100;
            Pump1 = pump1slider.getValue()*255/100;
            
            
            SetData2();
                               
            }else if (mode == 1){ //fade 
               
            }else if (mode == 2){ //ambilight
               
        }
        
        
        String send = channel1[0] + channel1[1] + channel1[2] + channel2[0] + channel2[1] + channel2[2] +  channel3[0] + channel3[1] + channel3[2] + channel4[0] + channel4[1] + channel4[2] + OutputFan1 + OutputFan2 + OutputPump1;
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
    
    public String[] getChannel(int i){
       if (i==1){
           return channel1;
       }else if(i==2){
           return channel2;
       }else if (i==3){
           return channel3;
       }else if (i==4){
           return channel4;
       }else{
           return null;
       }
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
        for (int i= 0; i <13; i++){ 
            config.setValue("color"+i+"R", "");
            config.setValue("color"+i+"G", "");
            config.setValue("color"+i+"B", "");
            
        }
    }    
}