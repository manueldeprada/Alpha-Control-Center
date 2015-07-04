/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphamods.controlcenter.utils;
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
import javax.swing.JLabel;
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
    
     public static String path = System.getProperty("user.dir"); 
     String OutputFan1, OutputFan2, OutputPump1;
    private  PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); //Variable para //instanciar la librería Arduino
    private  PanamaHitek_multiMessage multi = new PanamaHitek_multiMessage(6, Arduino);
    private boolean connected = false;
    private String[] channel1 = {"000","000","000"};
    private String[] channel2 = {"000","000","000"};
    private String[] channel3 = {"000","000","000"};
    private String[] channel4 = {"000","000","000"};
    private static double version = 1.0;
   
    public static double getversion(){
        return version;
    }
    
    
    
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
    
   
    
    public boolean refreshMode(JCheckBox RefreshCheckBox){
        if ("0".equals(config.getValue("refreshMode"))){
            RefreshCheckBox.setSelected(false);
            return false;
        }
        if ("1".equals(config.getValue("refreshMode"))){
            RefreshCheckBox.setSelected(true);
            return true;
        }else {
            return false;
        }
        
    }
    
    public int calculaterpms(int index, List<JSlider> sliders, String type){
        
        int maxrpm = Integer.parseInt(config.getValue(type+"max"+index));
        int percentaje = sliders.get(index).getValue();
        
        return  percentaje*maxrpm/100;
        
    }
  
    
    public void ports(JComboBox PortsBox){
        if (config.getValue("Port") == null){       
        }
        else{
            PortsBox.setSelectedItem(config.getValue("Port"));
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
    private String setmotors(int input){
        if (input < 10) {
        OutputFan1 = "00" + input;
        } else if (input < 100) {
        OutputFan1 = "0" + input;
        } else {
        OutputFan1 = Integer.toString(input);
        }
        return OutputFan1;
    }
    
    public void write(int mode, ColorPicker picker, List<JSlider> fans, List<JSlider> pumps, JCheckBox c1, JCheckBox c2, JCheckBox c3, JCheckBox c4, boolean testmode) {
  String[] output1= {"000", "000","000"};
  String[] output2= {"000", "000","000"};
  String[] output3= {"000", "000","000"};
  String[] output4= {"000", "000","000"};
  String finalmotors = "";

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
                channel4[0] = Integer.toString(R);
                channel4[1] = Integer.toString(G);
                channel4[2] = Integer.toString(B);
            }
            
            
            output1 = SetData(channel1);
            output2 = SetData(channel2);
            output3 = SetData(channel3);
            output4 = SetData(channel4);
                   // System.out.println(output4[0] + output4[1]+ output4[2]);

            int vfan = Integer.parseInt(config.getValue("fans"));
            int vpump = Integer.parseInt(config.getValue("pumps"));
            for (int i=0;i<vfan;i++){
             int u = fans.get(i).getValue()*255/100;
             finalmotors = finalmotors + setmotors(u);
             
            }
            for (int i=0;i<vpump;i++){
             int u = pumps.get(i).getValue()*255/100;
             finalmotors = finalmotors + setmotors(u);
             
            }
                     
            }else if (mode == 1){ //fade 
               
            }else if (mode == 2){ //ambilight
               
        }
        
         if (!c1.isEnabled()){
                output1[0] = "";
                output1[1] = "";
                output1[2] = "";
            } 
            if (!c2.isEnabled()){

                output2[0] = "";
                output2[1] = "";                 
                output2[2] = "";                 
            }
            if (!c3.isEnabled()){

                output3[0] = "";                 
                output3[1] = "";                 
                output3[2] = "";                 
            }
            if (!c4.isEnabled()){
                output4[0] = "";                  
                output4[1] = "";                 
                output4[2] = "";                    
            }
                
                
        String send = output1[0] + output1[1] + output1[2] + output2[0] + output2[1] + output2[2] +  output3[0] + output3[1] + output3[2] + output4[0] + output4[1] + output4[2] + finalmotors;
        try {
                    
                    if(!testmode){
                    Arduino.sendData(send);
                    }else{
                        System.out.println(send);
                        
                    }
                        
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