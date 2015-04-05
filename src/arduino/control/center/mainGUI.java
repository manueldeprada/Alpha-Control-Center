/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.control.center;

import arduino.control.center.utils.methods;
import com.bric.swing.ColorPicker;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import panamahitek.Arduino.PanamaHitek_multiMessage;

/**
 *
 * @author prada
 */
public class mainGUI extends javax.swing.JFrame {
private int mode = 0;
private methods methods = new methods();
private boolean testmode = false;
ScheduledExecutorService executor = 
            Executors.newScheduledThreadPool(1);

    private SerialPortEventListener evento = new SerialPortEventListener() {

    @Override
    public void serialEvent(SerialPortEvent spe) {
        if (methods.getMulti().DataReceptionCompleted()== true){
            Temp1.setText(methods.getMulti().getMessage(0));
            Temp2.setText(methods.getMulti().getMessage(1));
            Temp3.setText(methods.getMulti().getMessage(2));
            Temp4.setText(methods.getMulti().getMessage(3));
            Temp5.setText(methods.getMulti().getMessage(4));
            Temp6.setText(methods.getMulti().getMessage(5));
            methods.getMulti().flushBuffer();
        }
    }
};
    
    /**
     * Creates new form mainGUI
     */
    public mainGUI() {
        initComponents(); 
        methods.initialicePicker(picker);
        methods.initialiceArrays(picker);
        initPanel();
        sliders();
        ports();
        rpmData();
        rpm();
        picker.addPropertyChangeListener(picker.SELECTED_COLOR_PROPERTY, new PropertyChangeListener() {
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            PickerColorChanged(evt);}}); 
        }
    
    public void  Flash(ColorPicker picker) {
        int N=500000;
        float gHue = Color.RGBtoHSB(0, 1, 0, null)[0];
        float bHue = Color.RGBtoHSB(0, 0, 1, null)[0];
        for (int i = 0; i < N; i++) {
            picker.setColor(Color.getHSBColor(gHue + (i * (bHue - gHue) / N), 1, 1));
        }
        for (int i = 0; i < N; i++) {
            picker.setColor(Color.getHSBColor(bHue - (i * (bHue - gHue) / N), 1, 1));
        }
    }
        
        
    public void sliders(){
        methods.sliders(fan1slider, fan2slider, pump1slider);
    }
    
    public void ports(){
        methods.ports(PortsBox);
    }
    
    public void rpmData(){
        methods.rpmData(rmpLabelFan1, rmpLabelFan2, rmpLabelPump1, fan1max, fan2max, pump1max, fan1slider, fan2slider, pump1slider);
    }
    
    public void rpm(){
        methods.rpm(fan1max, fan2max, pump1max);
    }
    
    
    
    public void write(){
        if (methods.isConnected()){
           methods.write(mode, picker, fan1slider, fan2slider, pump1slider,  LedC1, LedC2, LedC3,LedC4,testmode);
        }else if (testmode){
            methods.write(mode, picker, fan1slider, fan2slider,pump1slider, LedC1, LedC2, LedC3,LedC4,testmode);
        }
    }
   
    public void loadpreviews(){
        methods.loadpreview(1, panelColor1);
        methods.loadpreview(2, panelColor2);
        methods.loadpreview(3, panelColor3);
        methods.loadpreview(4, panelColor4);
        methods.loadpreview(5, panelColor5);
        methods.loadpreview(6, panelColor6);
        methods.loadpreview(7, panelColor7);
        methods.loadpreview(8, panelColor8);
        methods.loadpreview(9, panelColor9);
        methods.loadpreview(10,panelColor10);
        methods.loadpreview(11,panelColor11);
        methods.loadpreview(12,panelColor12);
    }
    public int refreshTime(){
        if (arduino.control.center.utils.config.getValue("refreshTime") == null || arduino.control.center.utils.config.getValue("refreshTime") == ""){
          return 5;  
        }else {
            return Integer.parseInt(arduino.control.center.utils.config.getValue("refreshTime"));
        }
    }
    private void initPanel(){
        loadpreviews();
        
        buttonGroup1.add(fadeRadioButton);
        buttonGroup1.add(normalRadioButton);
        buttonGroup1.add(musicRadioButton);
        normalRadioButton.setSelected(true);
        
        if (!methods.isConnected()){
            fadeRadioButton.setEnabled(false);
            normalRadioButton.setEnabled(false);
            musicRadioButton.setEnabled(false);
            notConnectedLabel.setVisible(true);
            SecuencesRecordButton.setEnabled(false);
            SecuencesPlayButton.setEnabled(false);
            Refresh2.setEnabled(false);
            picker.setEnabled(testmode);
        }
        refreshSecondsSpinner.setEnabled(false);
        jLabel19.setEnabled(false);
        jLabel18.setEnabled(false);
        
        PortsBox.removeAllItems();
        methods.getArduino().getSerialPorts().forEach(i -> PortsBox.addItem(i));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabPanel = new javax.swing.JTabbedPane();
        colorTab = new javax.swing.JPanel();
        picker = new com.bric.swing.ColorPicker();
        ledModeLabel = new javax.swing.JLabel();
        fadeRadioButton = new javax.swing.JRadioButton();
        normalRadioButton = new javax.swing.JRadioButton();
        musicRadioButton = new javax.swing.JRadioButton();
        colors_secuencesPanel = new javax.swing.JTabbedPane();
        favColorsPanel = new javax.swing.JPanel();
        labelColor1 = new javax.swing.JLabel();
        panelColor1 = new javax.swing.JPanel();
        labelColor6 = new javax.swing.JLabel();
        panelColor6 = new javax.swing.JPanel();
        getButtonColor6 = new javax.swing.JButton();
        setButtonColor6 = new javax.swing.JButton();
        getButtonColor1 = new javax.swing.JButton();
        setButtonColor1 = new javax.swing.JButton();
        labelColor2 = new javax.swing.JLabel();
        panelColor2 = new javax.swing.JPanel();
        setButtonColor2 = new javax.swing.JButton();
        labelColor3 = new javax.swing.JLabel();
        panelColor3 = new javax.swing.JPanel();
        getButtonColor2 = new javax.swing.JButton();
        labelColor7 = new javax.swing.JLabel();
        panelColor7 = new javax.swing.JPanel();
        getButtonColor7 = new javax.swing.JButton();
        setButtonColor7 = new javax.swing.JButton();
        setButtonColor8 = new javax.swing.JButton();
        labelColor8 = new javax.swing.JLabel();
        getButtonColor8 = new javax.swing.JButton();
        panelColor8 = new javax.swing.JPanel();
        getButtonColor3 = new javax.swing.JButton();
        setButtonColor3 = new javax.swing.JButton();
        labelColor4 = new javax.swing.JLabel();
        panelColor4 = new javax.swing.JPanel();
        labelColor9 = new javax.swing.JLabel();
        panelColor9 = new javax.swing.JPanel();
        getButtonColor9 = new javax.swing.JButton();
        setButtonColor9 = new javax.swing.JButton();
        getButtonColor4 = new javax.swing.JButton();
        setButtonColor4 = new javax.swing.JButton();
        labelColor5 = new javax.swing.JLabel();
        panelColor5 = new javax.swing.JPanel();
        getButtonColor5 = new javax.swing.JButton();
        setButtonColor5 = new javax.swing.JButton();
        setButtonColor10 = new javax.swing.JButton();
        labelColor10 = new javax.swing.JLabel();
        panelColor10 = new javax.swing.JPanel();
        getButtonColor10 = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        labelColor11 = new javax.swing.JLabel();
        getButtonColor11 = new javax.swing.JButton();
        panelColor11 = new javax.swing.JPanel();
        setButtonColor11 = new javax.swing.JButton();
        labelColor12 = new javax.swing.JLabel();
        getButtonColor12 = new javax.swing.JButton();
        panelColor12 = new javax.swing.JPanel();
        setButtonColor12 = new javax.swing.JButton();
        SecuencesPanel = new javax.swing.JPanel();
        SecuencesTitle = new javax.swing.JLabel();
        SecuencesRecordButton = new javax.swing.JButton();
        SecuencesPlayButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        LedC1 = new javax.swing.JCheckBox();
        LedC2 = new javax.swing.JCheckBox();
        LedC3 = new javax.swing.JCheckBox();
        LedC4 = new javax.swing.JCheckBox();
        testMode = new javax.swing.JToggleButton();
        ambilightTab = new javax.swing.JPanel();
        FanPumpPanel = new javax.swing.JPanel();
        fan1label = new javax.swing.JLabel();
        fan1slider = new javax.swing.JSlider();
        pump1label = new javax.swing.JLabel();
        pump2label = new javax.swing.JLabel();
        fan2slider = new javax.swing.JSlider();
        pump1slider = new javax.swing.JSlider();
        fansTitleLabel = new javax.swing.JLabel();
        pumpsTitleLabel = new javax.swing.JLabel();
        rpmlabel1 = new javax.swing.JLabel();
        rpmlabel3 = new javax.swing.JLabel();
        rpmlabel4 = new javax.swing.JLabel();
        rmpLabelFan1 = new javax.swing.JTextField();
        rmpLabelPump1 = new javax.swing.JTextField();
        rmpLabelFan2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Temp1 = new javax.swing.JTextField();
        Temp2 = new javax.swing.JTextField();
        Temp3 = new javax.swing.JTextField();
        Temp4 = new javax.swing.JTextField();
        Temp5 = new javax.swing.JTextField();
        Temp6 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Refresh2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pump1max = new javax.swing.JTextField();
        fan1max = new javax.swing.JTextField();
        fan2max = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        RefreshCheckBox = new javax.swing.JCheckBox();
        refreshSecondsSpinner = new javax.swing.JSpinner();
        refreshPortsButton = new javax.swing.JButton();
        PortsBox = new javax.swing.JComboBox();
        connectButton = new javax.swing.JButton();
        sendbutton = new javax.swing.JButton();
        notConnectedLabel = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arduino Control Center");
        setBackground(new java.awt.Color(66, 66, 66));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        picker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pickerPropertyChange(evt);
            }
        });

        ledModeLabel.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        ledModeLabel.setText("LED Mode");

        fadeRadioButton.setText("Fade");
        fadeRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fadeRadioButtonItemStateChanged(evt);
            }
        });
        fadeRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fadeRadioButtonStateChanged(evt);
            }
        });

        normalRadioButton.setText("Normal");
        normalRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                normalRadioButtonItemStateChanged(evt);
            }
        });
        normalRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                normalRadioButtonStateChanged(evt);
            }
        });
        normalRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalRadioButtonActionPerformed(evt);
            }
        });

        musicRadioButton.setText("Music");
        musicRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                musicRadioButtonItemStateChanged(evt);
            }
        });
        musicRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicRadioButtonStateChanged(evt);
            }
        });

        favColorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Favourite colors")));

        labelColor1.setText("Color 1");

        panelColor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor1Layout = new javax.swing.GroupLayout(panelColor1);
        panelColor1.setLayout(panelColor1Layout);
        panelColor1Layout.setHorizontalGroup(
            panelColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor1Layout.setVerticalGroup(
            panelColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        labelColor6.setText("Color 6");

        panelColor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor6Layout = new javax.swing.GroupLayout(panelColor6);
        panelColor6.setLayout(panelColor6Layout);
        panelColor6Layout.setHorizontalGroup(
            panelColor6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor6Layout.setVerticalGroup(
            panelColor6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor6.setText("Get");
        getButtonColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor6ActionPerformed(evt);
            }
        });

        setButtonColor6.setText("Set");
        setButtonColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor6ActionPerformed(evt);
            }
        });

        getButtonColor1.setText("Get");
        getButtonColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor1ActionPerformed(evt);
            }
        });

        setButtonColor1.setText("Set");
        setButtonColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor1ActionPerformed(evt);
            }
        });

        labelColor2.setText("Color 2");

        panelColor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor2Layout = new javax.swing.GroupLayout(panelColor2);
        panelColor2.setLayout(panelColor2Layout);
        panelColor2Layout.setHorizontalGroup(
            panelColor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor2Layout.setVerticalGroup(
            panelColor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        setButtonColor2.setText("Set");
        setButtonColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor2ActionPerformed(evt);
            }
        });

        labelColor3.setText("Color 3");

        panelColor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor3Layout = new javax.swing.GroupLayout(panelColor3);
        panelColor3.setLayout(panelColor3Layout);
        panelColor3Layout.setHorizontalGroup(
            panelColor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor3Layout.setVerticalGroup(
            panelColor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor2.setText("Get");
        getButtonColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor2ActionPerformed(evt);
            }
        });

        labelColor7.setText("Color 7");

        panelColor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor7Layout = new javax.swing.GroupLayout(panelColor7);
        panelColor7.setLayout(panelColor7Layout);
        panelColor7Layout.setHorizontalGroup(
            panelColor7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor7Layout.setVerticalGroup(
            panelColor7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor7.setText("Get");
        getButtonColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor7ActionPerformed(evt);
            }
        });

        setButtonColor7.setText("Set");
        setButtonColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor7ActionPerformed(evt);
            }
        });

        setButtonColor8.setText("Set");
        setButtonColor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor8ActionPerformed(evt);
            }
        });

        labelColor8.setText("Color 8");

        getButtonColor8.setText("Get");
        getButtonColor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor8ActionPerformed(evt);
            }
        });

        panelColor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor8Layout = new javax.swing.GroupLayout(panelColor8);
        panelColor8.setLayout(panelColor8Layout);
        panelColor8Layout.setHorizontalGroup(
            panelColor8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor8Layout.setVerticalGroup(
            panelColor8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor3.setText("Get");
        getButtonColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor3ActionPerformed(evt);
            }
        });

        setButtonColor3.setText("Set");
        setButtonColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor3ActionPerformed(evt);
            }
        });

        labelColor4.setText("Color 4");

        panelColor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor4Layout = new javax.swing.GroupLayout(panelColor4);
        panelColor4.setLayout(panelColor4Layout);
        panelColor4Layout.setHorizontalGroup(
            panelColor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor4Layout.setVerticalGroup(
            panelColor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        labelColor9.setText("Color 9");

        panelColor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor9Layout = new javax.swing.GroupLayout(panelColor9);
        panelColor9.setLayout(panelColor9Layout);
        panelColor9Layout.setHorizontalGroup(
            panelColor9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor9Layout.setVerticalGroup(
            panelColor9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor9.setText("Get");
        getButtonColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor9ActionPerformed(evt);
            }
        });

        setButtonColor9.setText("Set");
        setButtonColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor9ActionPerformed(evt);
            }
        });

        getButtonColor4.setText("Get");
        getButtonColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor4ActionPerformed(evt);
            }
        });

        setButtonColor4.setText("Set");
        setButtonColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor4ActionPerformed(evt);
            }
        });

        labelColor5.setText("Color 5");

        panelColor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor5Layout = new javax.swing.GroupLayout(panelColor5);
        panelColor5.setLayout(panelColor5Layout);
        panelColor5Layout.setHorizontalGroup(
            panelColor5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor5Layout.setVerticalGroup(
            panelColor5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor5.setText("Get");
        getButtonColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor5ActionPerformed(evt);
            }
        });

        setButtonColor5.setText("Set");
        setButtonColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor5ActionPerformed(evt);
            }
        });

        setButtonColor10.setText("Set");
        setButtonColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor10ActionPerformed(evt);
            }
        });

        labelColor10.setText("Color 10");

        panelColor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor10Layout = new javax.swing.GroupLayout(panelColor10);
        panelColor10.setLayout(panelColor10Layout);
        panelColor10Layout.setHorizontalGroup(
            panelColor10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor10Layout.setVerticalGroup(
            panelColor10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        getButtonColor10.setText("Get");
        getButtonColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor10ActionPerformed(evt);
            }
        });

        cleanButton.setText("Clean all");
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        labelColor11.setText("Color 11");

        getButtonColor11.setText("Get");
        getButtonColor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor11ActionPerformed(evt);
            }
        });

        panelColor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor11Layout = new javax.swing.GroupLayout(panelColor11);
        panelColor11.setLayout(panelColor11Layout);
        panelColor11Layout.setHorizontalGroup(
            panelColor11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor11Layout.setVerticalGroup(
            panelColor11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        setButtonColor11.setText("Set");
        setButtonColor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor11ActionPerformed(evt);
            }
        });

        labelColor12.setText("Color 12");

        getButtonColor12.setText("Get");
        getButtonColor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor12ActionPerformed(evt);
            }
        });

        panelColor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColor12Layout = new javax.swing.GroupLayout(panelColor12);
        panelColor12.setLayout(panelColor12Layout);
        panelColor12Layout.setHorizontalGroup(
            panelColor12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelColor12Layout.setVerticalGroup(
            panelColor12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        setButtonColor12.setText("Set");
        setButtonColor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout favColorsPanelLayout = new javax.swing.GroupLayout(favColorsPanel);
        favColorsPanel.setLayout(favColorsPanelLayout);
        favColorsPanelLayout.setHorizontalGroup(
            favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(favColorsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelColor1)
                            .addComponent(setButtonColor1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getButtonColor1)))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelColor2)
                            .addComponent(labelColor3)
                            .addComponent(labelColor4)
                            .addComponent(labelColor5)
                            .addComponent(setButtonColor3)
                            .addComponent(setButtonColor4)
                            .addComponent(setButtonColor5)
                            .addComponent(setButtonColor2))
                        .addGap(7, 7, 7)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getButtonColor2)
                            .addComponent(getButtonColor3)
                            .addComponent(panelColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getButtonColor4)
                            .addComponent(panelColor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getButtonColor5)
                            .addComponent(panelColor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(setButtonColor6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getButtonColor6)
                            .addComponent(panelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(favColorsPanelLayout.createSequentialGroup()
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColor10)
                                    .addComponent(labelColor8)
                                    .addComponent(labelColor9)
                                    .addComponent(setButtonColor7)
                                    .addComponent(setButtonColor8)
                                    .addComponent(setButtonColor9)
                                    .addComponent(setButtonColor10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getButtonColor9)
                                    .addComponent(panelColor10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor7)
                                    .addComponent(panelColor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor8)
                                    .addComponent(panelColor9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor10)))
                            .addGroup(favColorsPanelLayout.createSequentialGroup()
                                .addComponent(labelColor7)
                                .addGap(18, 18, 18)
                                .addComponent(panelColor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColor11)
                                    .addComponent(setButtonColor11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelColor11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColor12)
                                    .addComponent(setButtonColor12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelColor12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor12))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cleanButton)
                .addGap(100, 100, 100))
        );
        favColorsPanelLayout.setVerticalGroup(
            favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(favColorsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor1)
                            .addComponent(getButtonColor1))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor2)
                            .addComponent(getButtonColor2))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor3)
                            .addComponent(getButtonColor3))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor4)
                            .addComponent(getButtonColor4)))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor7)
                            .addComponent(getButtonColor7))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor8)
                            .addComponent(getButtonColor8))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor9)
                            .addComponent(getButtonColor9))
                        .addGap(18, 18, 18)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor10)
                            .addComponent(getButtonColor10))))
                .addGap(18, 18, 18)
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor5)
                            .addComponent(getButtonColor5)))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor11)
                            .addComponent(getButtonColor11))))
                .addGap(18, 18, 18)
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor6)
                            .addComponent(getButtonColor6)))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor12)
                            .addComponent(getButtonColor12))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(cleanButton)
                .addContainerGap())
        );

        colors_secuencesPanel.addTab("Saved colors", favColorsPanel);

        SecuencesTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SecuencesTitle.setText("Secuences");

        SecuencesRecordButton.setText("Record");
        SecuencesRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecuencesRecordButtonActionPerformed(evt);
            }
        });

        SecuencesPlayButton.setText("Play");
        SecuencesPlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecuencesPlayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SecuencesPanelLayout = new javax.swing.GroupLayout(SecuencesPanel);
        SecuencesPanel.setLayout(SecuencesPanelLayout);
        SecuencesPanelLayout.setHorizontalGroup(
            SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(SecuencesRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(SecuencesPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(SecuencesTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SecuencesPanelLayout.setVerticalGroup(
            SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SecuencesTitle)
                .addGap(28, 28, 28)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecuencesRecordButton)
                    .addComponent(SecuencesPlayButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        colors_secuencesPanel.addTab("Secuences", SecuencesPanel);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel17.setText("LED Channels");

        LedC1.setText("Channel 1");

        LedC2.setText("Channel 2");

        LedC3.setText("Channel 3");
        LedC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LedC3ActionPerformed(evt);
            }
        });

        LedC4.setText("Channel 4");

        testMode.setText("Test Mode");
        testMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                testModeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout colorTabLayout = new javax.swing.GroupLayout(colorTab);
        colorTab.setLayout(colorTabLayout);
        colorTabLayout.setHorizontalGroup(
            colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorTabLayout.createSequentialGroup()
                .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorTabLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ledModeLabel)
                            .addGroup(colorTabLayout.createSequentialGroup()
                                .addComponent(fadeRadioButton)
                                .addGap(35, 35, 35)
                                .addComponent(normalRadioButton)))
                        .addGap(36, 36, 36)
                        .addComponent(musicRadioButton))
                    .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(picker, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorTabLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorTabLayout.createSequentialGroup()
                                    .addComponent(LedC1)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC2)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC3)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC4))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorTabLayout.createSequentialGroup()
                                    .addGap(133, 133, 133)
                                    .addComponent(jLabel17)))))
                    .addComponent(testMode))
                .addGap(18, 18, 18)
                .addComponent(colors_secuencesPanel)
                .addContainerGap())
        );
        colorTabLayout.setVerticalGroup(
            colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorTabLayout.createSequentialGroup()
                .addComponent(picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LedC1)
                    .addComponent(LedC2)
                    .addComponent(LedC3)
                    .addComponent(LedC4))
                .addGap(36, 36, 36)
                .addComponent(ledModeLabel)
                .addGap(18, 18, 18)
                .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(normalRadioButton)
                    .addComponent(musicRadioButton)
                    .addComponent(fadeRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(testMode))
            .addGroup(colorTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(colors_secuencesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Color", colorTab);

        javax.swing.GroupLayout ambilightTabLayout = new javax.swing.GroupLayout(ambilightTab);
        ambilightTab.setLayout(ambilightTabLayout);
        ambilightTabLayout.setHorizontalGroup(
            ambilightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        ambilightTabLayout.setVerticalGroup(
            ambilightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        tabPanel.addTab("Ambilight", ambilightTab);

        fan1label.setText("Channel 1");

        fan1slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fan1sliderStateChanged(evt);
            }
        });

        pump1label.setText("Pump 1");

        pump2label.setText("Channel 2");

        fan2slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fan2sliderStateChanged(evt);
            }
        });

        pump1slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pump1sliderStateChanged(evt);
            }
        });

        fansTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        fansTitleLabel.setText("Fans");

        pumpsTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        pumpsTitleLabel.setText("Pumps");

        rpmlabel1.setText("RPM");

        rpmlabel3.setText("RPM");

        rpmlabel4.setText("RPM");

        rmpLabelFan1.setEditable(false);
        rmpLabelFan1.setText("0000");

        rmpLabelPump1.setEditable(false);
        rmpLabelPump1.setText("0000");

        rmpLabelFan2.setEditable(false);
        rmpLabelFan2.setText("0000");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Temperatures");

        Temp1.setEditable(false);
        Temp1.setText("0");

        Temp2.setEditable(false);
        Temp2.setText("0");

        Temp3.setEditable(false);
        Temp3.setText("0");

        Temp4.setEditable(false);
        Temp4.setText("0");

        Temp5.setEditable(false);
        Temp5.setText("0");

        Temp6.setEditable(false);
        Temp6.setText("0");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel2.setText("Temp 1");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel3.setText("Temp 2");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel4.setText("Temp 3");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel5.setText("Temp 4");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel6.setText("Temp 5");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jLabel7.setText("Temp 6");

        jLabel8.setText("ºC");

        jLabel9.setText("ºC");

        jLabel10.setText("ºC");

        jLabel11.setText("ºC");

        jLabel12.setText("ºC");

        jLabel13.setText("ºC");

        Refresh2.setText("Refresh");
        Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Max. RPM Channel 1");

        jLabel15.setText("Max. RPM Channel 2");

        jLabel16.setText("Max. RPM");

        pump1max.setText("0");

        fan1max.setText("0");

        fan2max.setText("0");

        jLabel18.setText("Refresh each");

        jLabel19.setText("seconds");

        RefreshCheckBox.setText("Refresh automatically");
        RefreshCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshCheckBoxActionPerformed(evt);
            }
        });

        refreshSecondsSpinner.setValue(refreshTime());
        refreshSecondsSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                refreshSecondsSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout FanPumpPanelLayout = new javax.swing.GroupLayout(FanPumpPanel);
        FanPumpPanel.setLayout(FanPumpPanelLayout);
        FanPumpPanelLayout.setHorizontalGroup(
            FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addComponent(fansTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(86, 86, 86)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Temp2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))
                        .addGap(255, 255, 255))
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fan1label)
                                    .addComponent(pump2label)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                                .addComponent(pump1slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rmpLabelPump1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                                .addComponent(fan2slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rmpLabelFan2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rpmlabel4)
                                            .addComponent(rpmlabel3)))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(fan1slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rmpLabelFan1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rpmlabel1))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(pump1label))
                                    .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FanPumpPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(fan1max))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FanPumpPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(fan2max, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pump1max, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(pumpsTitleLabel))
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Temp1))
                                        .addGap(414, 414, 414))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(340, 340, 340))
                                    .addComponent(Temp5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Temp3)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addGap(86, 86, 86)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Temp4)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel11)
                                        .addGap(86, 86, 86)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Temp6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refreshSecondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel19))
                                    .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                                            .addComponent(Refresh2)
                                            .addGap(319, 319, 319))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                                            .addComponent(RefreshCheckBox)
                                            .addGap(289, 289, 289)))))))))
        );
        FanPumpPanelLayout.setVerticalGroup(
            FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fansTitleLabel)
                    .addComponent(jLabel1))
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(fan1label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fan1slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rmpLabelFan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rpmlabel1))
                        .addGap(36, 36, 36)
                        .addComponent(pump2label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fan2slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rmpLabelFan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rpmlabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(fan2max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpsTitleLabel)
                            .addComponent(Refresh2))
                        .addGap(20, 20, 20)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pump1label)
                                    .addComponent(RefreshCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pump1slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rmpLabelPump1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(refreshSecondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(rpmlabel3)))
                        .addGap(24, 24, 24)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(pump1max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Temp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Temp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13))
                                .addGap(89, 89, 89)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(fan1max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Temp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Temp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)))
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Temp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Temp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabPanel.addTab("Fans & Pumps", FanPumpPanel);

        refreshPortsButton.setText("Refresh");
        refreshPortsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPortsButtonActionPerformed(evt);
            }
        });

        PortsBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PortsBox.setToolTipText("Avaliable Arduino Ports");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        sendbutton.setText("Send");
        sendbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbuttonActionPerformed(evt);
            }
        });

        notConnectedLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        notConnectedLabel.setForeground(new java.awt.Color(225, 2, 27));
        notConnectedLabel.setText("Not connected!!");

        jMenu1.setText("File");
        menubar.add(jMenu1);

        jMenu2.setText("Edit");
        menubar.add(jMenu2);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshPortsButton)
                        .addGap(196, 196, 196)
                        .addComponent(notConnectedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshPortsButton)
                    .addComponent(connectButton)
                    .addComponent(sendbutton)
                    .addComponent(notConnectedLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void getButtonColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor6ActionPerformed
        int color6R = Integer.parseInt(arduino.control.center.utils.config.getValue("color6R"));
        int color6G = Integer.parseInt(arduino.control.center.utils.config.getValue("color6G"));
        int color6B = Integer.parseInt(arduino.control.center.utils.config.getValue("color6B"));
        Color color6 = new Color(color6R,color6G,color6B);
        picker.setColor(color6);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor6ActionPerformed

    private void setButtonColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor6ActionPerformed
        Color color6 = picker.getColor();
        panelColor6.setBackground(color6);
        arduino.control.center.utils.config.setValue("color6R", Integer.toString(color6.getRed()));
        arduino.control.center.utils.config.setValue("color6G", Integer.toString(color6.getGreen()));
        arduino.control.center.utils.config.setValue("color6B", Integer.toString(color6.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor6ActionPerformed

    private void getButtonColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor1ActionPerformed
        int color1R = Integer.parseInt(arduino.control.center.utils.config.getValue("color1R"));
        int color1G = Integer.parseInt(arduino.control.center.utils.config.getValue("color1G"));
        int color1B = Integer.parseInt(arduino.control.center.utils.config.getValue("color1B"));
        Color color1 = new Color(color1R,color1G,color1B);
        picker.setColor(color1);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor1ActionPerformed

    private void setButtonColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor1ActionPerformed
        Color color1 = picker.getColor();
        panelColor1.setBackground(color1);
        arduino.control.center.utils.config.setValue("color1R", Integer.toString(color1.getRed()));
        arduino.control.center.utils.config.setValue("color1G", Integer.toString(color1.getGreen()));
        arduino.control.center.utils.config.setValue("color1B", Integer.toString(color1.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor1ActionPerformed

    private void setButtonColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor2ActionPerformed
        Color color2 = picker.getColor();
        panelColor2.setBackground(color2);
        arduino.control.center.utils.config.setValue("color2R", Integer.toString(color2.getRed()));
        arduino.control.center.utils.config.setValue("color2G", Integer.toString(color2.getGreen()));
        arduino.control.center.utils.config.setValue("color2B", Integer.toString(color2.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor2ActionPerformed

    private void getButtonColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor2ActionPerformed
        int color2R = Integer.parseInt(arduino.control.center.utils.config.getValue("color2R"));
        int color2G = Integer.parseInt(arduino.control.center.utils.config.getValue("color2G"));
        int color2B = Integer.parseInt(arduino.control.center.utils.config.getValue("color2B"));
        Color color2 = new Color(color2R,color2G,color2B);
        picker.setColor(color2);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor2ActionPerformed

    private void getButtonColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor7ActionPerformed
        int color7R = Integer.parseInt(arduino.control.center.utils.config.getValue("color7R"));
        int color7G = Integer.parseInt(arduino.control.center.utils.config.getValue("color7G"));
        int color7B = Integer.parseInt(arduino.control.center.utils.config.getValue("color7B"));
        Color color7 = new Color(color7R,color7G,color7B);
        picker.setColor(color7);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor7ActionPerformed

    private void setButtonColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor7ActionPerformed
        Color color7 = picker.getColor();
        panelColor7.setBackground(color7);
        arduino.control.center.utils.config.setValue("color7R", Integer.toString(color7.getRed()));
        arduino.control.center.utils.config.setValue("color7G", Integer.toString(color7.getGreen()));
        arduino.control.center.utils.config.setValue("color7B", Integer.toString(color7.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor7ActionPerformed

    private void PickerColorChanged(java.beans.PropertyChangeEvent evt){

        if (methods.isConnected()) {
            write();
        }else if (testmode){
            write();
        }
    }
    
    
    private void setButtonColor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor8ActionPerformed
        Color color8 = picker.getColor();
        panelColor8.setBackground(color8);
        arduino.control.center.utils.config.setValue("color8R", Integer.toString(color8.getRed()));
        arduino.control.center.utils.config.setValue("color8G", Integer.toString(color8.getGreen()));
        arduino.control.center.utils.config.setValue("color8B", Integer.toString(color8.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor8ActionPerformed

    private void getButtonColor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor8ActionPerformed
        int color8R = Integer.parseInt(arduino.control.center.utils.config.getValue("color8R"));
        int color8G = Integer.parseInt(arduino.control.center.utils.config.getValue("color8G"));
        int color8B = Integer.parseInt(arduino.control.center.utils.config.getValue("color8B"));
        Color color8 = new Color(color8R,color8G,color8B);
        picker.setColor(color8);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor8ActionPerformed

    private void getButtonColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor3ActionPerformed
        int color3R = Integer.parseInt(arduino.control.center.utils.config.getValue("color3R"));
        int color3G = Integer.parseInt(arduino.control.center.utils.config.getValue("color3G"));
        int color3B = Integer.parseInt(arduino.control.center.utils.config.getValue("color3B"));
        Color color3 = new Color(color3R,color3G,color3B);
        picker.setColor(color3);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor3ActionPerformed

    private void setButtonColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor3ActionPerformed
        Color color3 = picker.getColor();
        panelColor3.setBackground(color3);
        arduino.control.center.utils.config.setValue("color3R", Integer.toString(color3.getRed()));
        arduino.control.center.utils.config.setValue("color3G", Integer.toString(color3.getGreen()));
        arduino.control.center.utils.config.setValue("color3B", Integer.toString(color3.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor3ActionPerformed

    private void getButtonColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor9ActionPerformed
        int color9R = Integer.parseInt(arduino.control.center.utils.config.getValue("color9R"));
        int color9G = Integer.parseInt(arduino.control.center.utils.config.getValue("color9G"));
        int color9B = Integer.parseInt(arduino.control.center.utils.config.getValue("color9B"));
        Color color9 = new Color(color9R,color9G,color9B);
        picker.setColor(color9);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor9ActionPerformed

    private void setButtonColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor9ActionPerformed
        Color color9 = picker.getColor();
        panelColor9.setBackground(color9);
        arduino.control.center.utils.config.setValue("color9R", Integer.toString(color9.getRed()));
        arduino.control.center.utils.config.setValue("color9G", Integer.toString(color9.getGreen()));
        arduino.control.center.utils.config.setValue("color9B", Integer.toString(color9.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor9ActionPerformed

    private void getButtonColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor4ActionPerformed
        int color4R = Integer.parseInt(arduino.control.center.utils.config.getValue("color4R"));
        int color4G = Integer.parseInt(arduino.control.center.utils.config.getValue("color4G"));
        int color4B = Integer.parseInt(arduino.control.center.utils.config.getValue("color4B"));
        Color color4 = new Color(color4R,color4G,color4B);
        picker.setColor(color4);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor4ActionPerformed

    private void setButtonColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor4ActionPerformed
        Color color4 = picker.getColor();
        panelColor4.setBackground(color4);
        arduino.control.center.utils.config.setValue("color4R", Integer.toString(color4.getRed()));
        arduino.control.center.utils.config.setValue("color4G", Integer.toString(color4.getGreen()));
        arduino.control.center.utils.config.setValue("color4B", Integer.toString(color4.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor4ActionPerformed

    private void getButtonColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor5ActionPerformed
        int color5R = Integer.parseInt(arduino.control.center.utils.config.getValue("color5R"));
        int color5G = Integer.parseInt(arduino.control.center.utils.config.getValue("color5G"));
        int color5B = Integer.parseInt(arduino.control.center.utils.config.getValue("color5B"));
        Color color5 = new Color(color5R,color5G,color5B);
        picker.setColor(color5);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor5ActionPerformed

    private void setButtonColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor5ActionPerformed
        Color color5 = picker.getColor();
        panelColor5.setBackground(color5);
        arduino.control.center.utils.config.setValue("color5R", Integer.toString(color5.getRed()));
        arduino.control.center.utils.config.setValue("color5G", Integer.toString(color5.getGreen()));
        arduino.control.center.utils.config.setValue("color5B", Integer.toString(color5.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor5ActionPerformed

    private void setButtonColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor10ActionPerformed
        Color color10 = picker.getColor();
        panelColor10.setBackground(color10);
        arduino.control.center.utils.config.setValue("color10R", Integer.toString(color10.getRed()));
        arduino.control.center.utils.config.setValue("color10G", Integer.toString(color10.getGreen()));
        arduino.control.center.utils.config.setValue("color10B", Integer.toString(color10.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor10ActionPerformed

    private void getButtonColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor10ActionPerformed
        int color10R = Integer.parseInt(arduino.control.center.utils.config.getValue("color10R"));
        int color10G = Integer.parseInt(arduino.control.center.utils.config.getValue("color10G"));
        int color10B = Integer.parseInt(arduino.control.center.utils.config.getValue("color10B"));
        Color color10 = new Color(color10R,color10G,color10B);
        picker.setColor(color10);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor10ActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
methods.cleanFavourites();

loadpreviews();// TODO add your handling code here:
    }//GEN-LAST:event_cleanButtonActionPerformed

    private void SecuencesRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecuencesRecordButtonActionPerformed
arduino.control.center.utils.secuences ub = new arduino.control.center.utils.secuences();
ub.record(picker, 1);// TODO add your handling code here:
    }//GEN-LAST:event_SecuencesRecordButtonActionPerformed

    private void SecuencesPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecuencesPlayButtonActionPerformed
     arduino.control.center.utils.secuences ub = new arduino.control.center.utils.secuences();
ub.play(picker, 1);   // TODO add your handling code here:
    }//GEN-LAST:event_SecuencesPlayButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
if (methods.isConnected()) {
    
                methods.disconnect();
                notConnectedLabel.setVisible(true);
                connectButton.setText("Connect");
                picker.setEnabled(false);
                fadeRadioButton.setEnabled(false);
                normalRadioButton.setEnabled(false);
                musicRadioButton.setEnabled(false);
                SecuencesRecordButton.setEnabled(false);
                SecuencesPlayButton.setEnabled(false);
                Refresh2.setEnabled(false);
            

        } else {

            try {
                methods.connect(evento, PortsBox);
                connectButton.setText("Disconnect");
                fadeRadioButton.setEnabled(true);
                normalRadioButton.setEnabled(true);
                musicRadioButton.setEnabled(true);
                picker.setEnabled(true);
                notConnectedLabel.setVisible(false);
                Refresh2.setEnabled(true);

            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_connectButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        arduino.control.center.utils.config.setValue("colorR", Integer.toString(picker.getColor().getRed()));
        arduino.control.center.utils.config.setValue("colorG", Integer.toString(picker.getColor().getGreen()));
        arduino.control.center.utils.config.setValue("colorB", Integer.toString(picker.getColor().getBlue()));
        arduino.control.center.utils.config.setValue("Fan1", Integer.toString(fan1slider.getValue()));
        arduino.control.center.utils.config.setValue("Fan2", Integer.toString(fan2slider.getValue()));
        arduino.control.center.utils.config.setValue("Pump1", Integer.toString(pump1slider.getValue()));
        arduino.control.center.utils.config.setValue("Fan1max", fan1max.getText());
        arduino.control.center.utils.config.setValue("Fan2max", fan2max.getText());
        arduino.control.center.utils.config.setValue("Pump1max", pump1max.getText());
        if (methods.isConnected()){
        arduino.control.center.utils.config.setValue("Port", PortsBox.getSelectedItem().toString());
        }
        
        arduino.control.center.utils.config.setValue("refreshTime", this.refreshSecondsSpinner.getValue().toString());
        
        arduino.control.center.utils.config.setValue("channel1R", methods.getChannel(1)[0]);
        arduino.control.center.utils.config.setValue("channel1G", methods.getChannel(1)[1]);
        arduino.control.center.utils.config.setValue("channel1B", methods.getChannel(1)[2]);
        
        arduino.control.center.utils.config.setValue("channel2R", methods.getChannel(2)[0]);
        arduino.control.center.utils.config.setValue("channel2G", methods.getChannel(2)[1]);
        arduino.control.center.utils.config.setValue("channel2B", methods.getChannel(2)[2]);
        
        arduino.control.center.utils.config.setValue("channel3R", methods.getChannel(3)[0]);
        arduino.control.center.utils.config.setValue("channel3G", methods.getChannel(3)[1]);
        arduino.control.center.utils.config.setValue("channel3B", methods.getChannel(3)[2]);
        
        arduino.control.center.utils.config.setValue("channel4R", methods.getChannel(4)[0]);
        arduino.control.center.utils.config.setValue("channel4G", methods.getChannel(4)[1]);
        arduino.control.center.utils.config.setValue("channel4B", methods.getChannel(4)[2]);
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void refreshPortsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPortsButtonActionPerformed
   PortsBox.removeAllItems();
        methods.getArduino().getSerialPorts().forEach(i -> PortsBox.addItem(i));     // TODO add your handling code here:
    }//GEN-LAST:event_refreshPortsButtonActionPerformed

    private void sendbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbuttonActionPerformed
    write();        // TODO add your handling code here:
    }//GEN-LAST:event_sendbuttonActionPerformed

    private void musicRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_musicRadioButtonStateChanged
    }//GEN-LAST:event_musicRadioButtonStateChanged

    private void fadeRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fadeRadioButtonStateChanged
    }//GEN-LAST:event_fadeRadioButtonStateChanged

    private void normalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonActionPerformed

    private void normalRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_normalRadioButtonStateChanged
                // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonStateChanged

    private void pickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pickerPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_pickerPropertyChange

    private void normalRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_normalRadioButtonItemStateChanged
write();        // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonItemStateChanged

    private void fadeRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fadeRadioButtonItemStateChanged
write();        // TODO add your handling code here:
    }//GEN-LAST:event_fadeRadioButtonItemStateChanged

    private void musicRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_musicRadioButtonItemStateChanged
write();        // TODO add your handling code here:
    }//GEN-LAST:event_musicRadioButtonItemStateChanged

    private void fan1sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fan1sliderStateChanged
write();
rpmData();
        // TODO add your handling code here:
    }//GEN-LAST:event_fan1sliderStateChanged

    private void fan2sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fan2sliderStateChanged
write();
rpmData();
        // TODO add your handling code here:
    }//GEN-LAST:event_fan2sliderStateChanged

    private void pump1sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pump1sliderStateChanged
write();
rpmData();
        // TODO add your handling code here:
    }//GEN-LAST:event_pump1sliderStateChanged

    private void Refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh2ActionPerformed
write();
rpmData();
        // TODO add your handling code here:
    }//GEN-LAST:event_Refresh2ActionPerformed

    private void LedC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LedC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LedC3ActionPerformed

    private void getButtonColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor11ActionPerformed
        int color11R = Integer.parseInt(arduino.control.center.utils.config.getValue("color11R"));
        int color11G = Integer.parseInt(arduino.control.center.utils.config.getValue("color11G"));
        int color11B = Integer.parseInt(arduino.control.center.utils.config.getValue("color11B"));
        Color color11 = new Color(color11R,color11G,color11B);
        picker.setColor(color11);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor11ActionPerformed

    private void setButtonColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor11ActionPerformed
        Color color11 = picker.getColor();
        panelColor11.setBackground(color11);
        arduino.control.center.utils.config.setValue("color11R", Integer.toString(color11.getRed()));
        arduino.control.center.utils.config.setValue("color11G", Integer.toString(color11.getGreen()));
        arduino.control.center.utils.config.setValue("color11B", Integer.toString(color11.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor11ActionPerformed

    private void getButtonColor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor12ActionPerformed
        int color12R = Integer.parseInt(arduino.control.center.utils.config.getValue("color12R"));
        int color12G = Integer.parseInt(arduino.control.center.utils.config.getValue("color12G"));
        int color12B = Integer.parseInt(arduino.control.center.utils.config.getValue("color12B"));
        Color color12 = new Color(color12R,color12G,color12B);
        picker.setColor(color12);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor12ActionPerformed

    private void setButtonColor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor12ActionPerformed
        Color color12 = picker.getColor();
        panelColor12.setBackground(color12);
        arduino.control.center.utils.config.setValue("color12R", Integer.toString(color12.getRed()));
        arduino.control.center.utils.config.setValue("color12G", Integer.toString(color12.getGreen()));
        arduino.control.center.utils.config.setValue("color12B", Integer.toString(color12.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor12ActionPerformed

    private void RefreshCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshCheckBoxActionPerformed
        if (RefreshCheckBox.isSelected()){
            refreshSecondsSpinner.setEnabled(true);
            jLabel19.setEnabled(true);
            jLabel18.setEnabled(true);
            executor.scheduleAtFixedRate(refreshTemp, 0, Long.parseLong(refreshSecondsSpinner.getValue().toString()), TimeUnit.SECONDS);
        }
        else{
            refreshSecondsSpinner.setEnabled(false);
            jLabel19.setEnabled(false);
            jLabel18.setEnabled(false);
            executor.shutdown();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_RefreshCheckBoxActionPerformed

    
    
    private void refreshSecondsSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_refreshSecondsSpinnerStateChanged
executor.shutdown();  
            executor.scheduleAtFixedRate(refreshTemp, 0, Long.parseLong(refreshSecondsSpinner.getValue().toString()), TimeUnit.SECONDS);
// TODO add your handling code here:
    }//GEN-LAST:event_refreshSecondsSpinnerStateChanged

    private void testModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_testModeItemStateChanged
testmode = testMode.isSelected();
if (testMode.isSelected()){
    
                connectButton.setText("testing");
                fadeRadioButton.setEnabled(true);
                normalRadioButton.setEnabled(true);
                musicRadioButton.setEnabled(true);
                picker.setEnabled(true);
                notConnectedLabel.setVisible(false);
                Refresh2.setEnabled(true);
}else {
  notConnectedLabel.setVisible(true);
                connectButton.setText("Connect");
                picker.setEnabled(false);
                fadeRadioButton.setEnabled(false);
                normalRadioButton.setEnabled(false);
                musicRadioButton.setEnabled(false);
                SecuencesRecordButton.setEnabled(false);
                SecuencesPlayButton.setEnabled(false);
                Refresh2.setEnabled(false);   
}
    }//GEN-LAST:event_testModeItemStateChanged

    
    Runnable refreshTemp = new Runnable() {

    public void run() {
    write();
    rpmData();
        
    }
};
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FanPumpPanel;
    private javax.swing.JCheckBox LedC1;
    private javax.swing.JCheckBox LedC2;
    private javax.swing.JCheckBox LedC3;
    private javax.swing.JCheckBox LedC4;
    private javax.swing.JComboBox PortsBox;
    private javax.swing.JButton Refresh2;
    private javax.swing.JCheckBox RefreshCheckBox;
    private javax.swing.JPanel SecuencesPanel;
    private javax.swing.JButton SecuencesPlayButton;
    private javax.swing.JButton SecuencesRecordButton;
    private javax.swing.JLabel SecuencesTitle;
    private javax.swing.JTextField Temp1;
    private javax.swing.JTextField Temp2;
    private javax.swing.JTextField Temp3;
    private javax.swing.JTextField Temp4;
    private javax.swing.JTextField Temp5;
    private javax.swing.JTextField Temp6;
    private javax.swing.JPanel ambilightTab;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cleanButton;
    private javax.swing.JPanel colorTab;
    private javax.swing.JTabbedPane colors_secuencesPanel;
    private javax.swing.JButton connectButton;
    private javax.swing.JRadioButton fadeRadioButton;
    private javax.swing.JLabel fan1label;
    private javax.swing.JTextField fan1max;
    private javax.swing.JSlider fan1slider;
    private javax.swing.JTextField fan2max;
    private javax.swing.JSlider fan2slider;
    private javax.swing.JLabel fansTitleLabel;
    private javax.swing.JPanel favColorsPanel;
    private javax.swing.JButton getButtonColor1;
    private javax.swing.JButton getButtonColor10;
    private javax.swing.JButton getButtonColor11;
    private javax.swing.JButton getButtonColor12;
    private javax.swing.JButton getButtonColor2;
    private javax.swing.JButton getButtonColor3;
    private javax.swing.JButton getButtonColor4;
    private javax.swing.JButton getButtonColor5;
    private javax.swing.JButton getButtonColor6;
    private javax.swing.JButton getButtonColor7;
    private javax.swing.JButton getButtonColor8;
    private javax.swing.JButton getButtonColor9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel labelColor1;
    private javax.swing.JLabel labelColor10;
    private javax.swing.JLabel labelColor11;
    private javax.swing.JLabel labelColor12;
    private javax.swing.JLabel labelColor2;
    private javax.swing.JLabel labelColor3;
    private javax.swing.JLabel labelColor4;
    private javax.swing.JLabel labelColor5;
    private javax.swing.JLabel labelColor6;
    private javax.swing.JLabel labelColor7;
    private javax.swing.JLabel labelColor8;
    private javax.swing.JLabel labelColor9;
    private javax.swing.JLabel ledModeLabel;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JRadioButton musicRadioButton;
    private javax.swing.JRadioButton normalRadioButton;
    private javax.swing.JLabel notConnectedLabel;
    private javax.swing.JPanel panelColor1;
    private javax.swing.JPanel panelColor10;
    private javax.swing.JPanel panelColor11;
    private javax.swing.JPanel panelColor12;
    private javax.swing.JPanel panelColor2;
    private javax.swing.JPanel panelColor3;
    private javax.swing.JPanel panelColor4;
    private javax.swing.JPanel panelColor5;
    private javax.swing.JPanel panelColor6;
    private javax.swing.JPanel panelColor7;
    private javax.swing.JPanel panelColor8;
    private javax.swing.JPanel panelColor9;
    private com.bric.swing.ColorPicker picker;
    private javax.swing.JLabel pump1label;
    private javax.swing.JTextField pump1max;
    private javax.swing.JSlider pump1slider;
    private javax.swing.JLabel pump2label;
    private javax.swing.JLabel pumpsTitleLabel;
    private javax.swing.JButton refreshPortsButton;
    private javax.swing.JSpinner refreshSecondsSpinner;
    private javax.swing.JTextField rmpLabelFan1;
    private javax.swing.JTextField rmpLabelFan2;
    private javax.swing.JTextField rmpLabelPump1;
    private javax.swing.JLabel rpmlabel1;
    private javax.swing.JLabel rpmlabel3;
    private javax.swing.JLabel rpmlabel4;
    private javax.swing.JButton sendbutton;
    private javax.swing.JButton setButtonColor1;
    private javax.swing.JButton setButtonColor10;
    private javax.swing.JButton setButtonColor11;
    private javax.swing.JButton setButtonColor12;
    private javax.swing.JButton setButtonColor2;
    private javax.swing.JButton setButtonColor3;
    private javax.swing.JButton setButtonColor4;
    private javax.swing.JButton setButtonColor5;
    private javax.swing.JButton setButtonColor6;
    private javax.swing.JButton setButtonColor7;
    private javax.swing.JButton setButtonColor8;
    private javax.swing.JButton setButtonColor9;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JToggleButton testMode;
    // End of variables declaration//GEN-END:variables
}
