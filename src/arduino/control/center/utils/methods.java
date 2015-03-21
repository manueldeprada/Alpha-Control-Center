/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center.utils;

import com.bric.swing.ColorPicker;
import java.awt.Color;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author prada
 */
public class methods {
    
    
    //Variable declarations
    static int R = 0, G = 0, B = 0;
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
    
    
    public static void write(int mode, ColorPicker picker) {
  
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
}
    
    public void loadpreviews(){
        if(config.getValue("color1R") != null){
            int color1R = Integer.parseInt(config.getValue("color1R"));
            int color1G = Integer.parseInt(config.getValue("color1G"));
            int color1B = Integer.parseInt(config.getValue("color1B"));
        Color color1 = new Color(color1R,color1G,color1B);
        jPanel1.setBackground(color1);
        }
        if(config.getValue("color2R") != null){
            int color2R = Integer.parseInt(config.getValue("color2R"));
            int color2G = Integer.parseInt(config.getValue("color2G"));
            int color2B = Integer.parseInt(config.getValue("color2B"));
        Color color2 = new Color(color2R,color2G,color2B);
        jPanel2.setBackground(color2);
        }
        if(config.getValue("color3R") != null){
            int color3R = Integer.parseInt(config.getValue("color3R"));
            int color3G = Integer.parseInt(config.getValue("color3G"));
            int color3B = Integer.parseInt(config.getValue("color3B"));
        Color color3 = new Color(color3R,color3G,color3B);
        jPanel3.setBackground(color3);
        }
        if(config.getValue("color4R") != null){
            int color4R = Integer.parseInt(config.getValue("color4R"));
            int color4G = Integer.parseInt(config.getValue("color4G"));
            int color4B = Integer.parseInt(config.getValue("color4B"));
        Color color4 = new Color(color4R,color4G,color4B);
        jPanel4.setBackground(color4);
        }
        if(config.getValue("color5R") != null){
            int color5R = Integer.parseInt(config.getValue("color5R"));
            int color5G = Integer.parseInt(config.getValue("color5G"));
            int color5B = Integer.parseInt(config.getValue("color5B"));
        Color color5 = new Color(color5R,color5G,color5B);
        jPanel5.setBackground(color5);
        }
        if(config.getValue("color6R") != null){
            int color6R = Integer.parseInt(config.getValue("color6R"));
            int color6G = Integer.parseInt(config.getValue("color6G"));
            int color6B = Integer.parseInt(config.getValue("color6B"));
        Color color6 = new Color(color6R,color6G,color6B);
        jPanel6.setBackground(color6);
        }
        if(config.getValue("color7R") != null){
            int color7R = Integer.parseInt(config.getValue("color7R"));
            int color7G = Integer.parseInt(config.getValue("color7G"));
            int color7B = Integer.parseInt(config.getValue("color7B"));
        Color color7 = new Color(color7R,color7G,color7B);
        jPanel7.setBackground(color7);
        }
        if(config.getValue("color8R") != null){
            int color8R = Integer.parseInt(config.getValue("color8R"));
            int color8G = Integer.parseInt(config.getValue("color8G"));
            int color8B = Integer.parseInt(config.getValue("color8B"));
        Color color8 = new Color(color8R,color8G,color8B);
        jPanel8.setBackground(color8);
        }
        if(config.getValue("color9R") != null){
            int color9R = Integer.parseInt(config.getValue("color9R"));
            int color9G = Integer.parseInt(config.getValue("color9G"));
            int color9B = Integer.parseInt(config.getValue("color9B"));
        Color color9 = new Color(color9R,color9G,color9B);
        jPanel9.setBackground(color9);
        }
        if(config.getValue("color10R") != null){
            int color10R = Integer.parseInt(config.getValue("color10R"));
            int color10G = Integer.parseInt(config.getValue("color10G"));
            int color10B = Integer.parseInt(config.getValue("color10B"));
        Color color10 = new Color(color10R,color10G,color10B);
        jPanel10.setBackground(color10);
    
}
    }
}