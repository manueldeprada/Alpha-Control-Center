/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphamods.controlcenter;

import com.alphamods.controlcenter.utils.config;
import com.alphamods.controlcenter.utils.methods;
import com.alphamods.controlcenter.utils.secuences;
import static com.alphamods.controlcenter.utils.secuences.path;
import com.bric.swing.ColorPicker;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author prada
 */
public class mainGUI extends javax.swing.JFrame {
private int mode = 0;
private methods methods = new methods();
    com.alphamods.controlcenter.utils.secuences s1record;
    com.alphamods.controlcenter.utils.secuences s1play = new com.alphamods.controlcenter.utils.secuences();
    
    com.alphamods.controlcenter.utils.secuences s2record;
    com.alphamods.controlcenter.utils.secuences s2play = new com.alphamods.controlcenter.utils.secuences();
    
    com.alphamods.controlcenter.utils.secuences s3record;
    com.alphamods.controlcenter.utils.secuences s3play = new com.alphamods.controlcenter.utils.secuences();
    
    com.alphamods.controlcenter.utils.secuences s4record;
    com.alphamods.controlcenter.utils.secuences s4play = new com.alphamods.controlcenter.utils.secuences();
    
    com.alphamods.controlcenter.utils.secuences s5record;
    com.alphamods.controlcenter.utils.secuences s5play = new com.alphamods.controlcenter.utils.secuences();
    
    private boolean testmode = false;
ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

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
            Temp7.setText(methods.getMulti().getMessage(6));
            Temp8.setText(methods.getMulti().getMessage(7));
            Temp9.setText(methods.getMulti().getMessage(8));
            Temp10.setText(methods.getMulti().getMessage(9));
            Temp11.setText(methods.getMulti().getMessage(10));
            List<String> message = Arrays.asList(methods.getMulti().getMessage(0),methods.getMulti().getMessage(1),methods.getMulti().getMessage(2),methods.getMulti().getMessage(3),methods.getMulti().getMessage(4),methods.getMulti().getMessage(5),methods.getMulti().getMessage(6),methods.getMulti().getMessage(7),methods.getMulti().getMessage(8),methods.getMulti().getMessage(9),methods.getMulti().getMessage(10));
                                        tochart2(message);

            methods.getMulti().flushBuffer();
        }
    }
};
List<JLabel> fanlabels;
List<JSlider> fansliders;
List<JTextField> indicatorfans;
List<JLabel> fanrpmlabels;
List<JLabel> pumplabels;
List<JSlider> pumpsliders;
List<JTextField> indicatorpumps;
List<JLabel> pumprpmlabels;
boolean music = Boolean.parseBoolean(config.getValue("music"));
JFreeChart chart;
DefaultCategoryDataset Datos = new DefaultCategoryDataset();
XYSeriesCollection xyseriescollection = new XYSeriesCollection();
int i = 0;
List<JLabel> temptitles;
List<JLabel> degrees;
List<JTextField> temps;
List<JPanel> panels;
    
    public void trayIcon(){
        TrayIcon trayIcon;

        if (SystemTray.isSupported()) {

            try {
                
                SystemTray tray = SystemTray.getSystemTray();
                
                BufferedImage trayIconImage = ImageIO.read(getClass().getResource("res/icon128.png"));
                int trayIconWidth = new TrayIcon(trayIconImage).getSize().width;
                
                
                
                ActionListener exitListener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                };
                
                MouseListener mouse = new MouseAdapter()  {
                public void mouseClicked(MouseEvent e) {
                        setVisible(true);
                        requestFocusInWindow();
                        requestFocus();
                        toFront();
                        setState(Frame.NORMAL);
                        
                        
                    }

                    
                };
                
                
                ActionListener openListener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);
                        requestFocusInWindow();
                        requestFocus();
                        toFront();
                        setState(Frame.NORMAL);
                        
                        
                    }
                };
                
                PopupMenu popup = new PopupMenu();
                
                MenuItem openItem = new MenuItem(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("OPEN"));
                openItem.addActionListener(openListener);
                popup.add(openItem);
                MenuItem exitItem = new MenuItem(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("EXIT"));
                exitItem.addActionListener(exitListener);
                popup.addSeparator();
                popup.add(exitItem);
                
                
                trayIcon = new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH), java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RIGHT CLICK FOR OPTIONS"), popup);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(openListener);
                trayIcon.addMouseListener(mouse);
                
                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    System.err.println("TrayIcon could not be added.");
                }
                
                addWindowStateListener(new WindowStateListener() {
                    public void windowStateChanged(WindowEvent e) {
                        if(e.getNewState()==ICONIFIED){
                            setVisible(false);
                        }
                        
                        if(e.getNewState()==7){
                            setVisible(false);
                        }
                        
                        if(e.getNewState()==MAXIMIZED_BOTH){
                            setVisible(true);
                        }
                        
                        if(e.getNewState()==NORMAL){
                            setVisible(true);
                        }
                    }
                });
                setDefaultCloseOperation(mainGUI.EXIT_ON_CLOSE);
                
                
            } catch (IOException ex) {
                Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        } else {
            System.err.println("System tray is currently not supported.");
        }
    }

    
    /**
     * Creates new form mainGUI
     */
    public mainGUI() {
        
        initComponents();
        trayIcon();
        makeBigPanels();
        methods.initialicePicker(picker);
        methods.initialiceArrays(picker, music);
        initPanel();
        ports();
        
        refreshMode();
        loadsecpreviews();        
        setIcons();
        if(Boolean.parseBoolean(config.getValue("startupdate"))==true){checkUpdates();}
        enablefansandpumps();
        loadpercentajes();
        updaterpms();
        picker.addPropertyChangeListener(picker.SELECTED_COLOR_PROPERTY, new PropertyChangeListener() {
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            PickerColorChanged(evt);}}); 
    
        chart2();
        
        if (new File(path+"\\ambilight.jar").exists()){
            jPanel2.setVisible(false);
        }
        
    }
    
    
    
    private void enablefansandpumps(){
        fanlabels = Arrays.asList(fanlabel1,fanlabel2,fanlabel3,fanlabel4,fanlabel5,fanlabel6,fanlabel7,fanlabel8,fanlabel9,fanlabel10,fanlabel11);
        fansliders = Arrays.asList(fanslider1,fanslider2,fanslider3,fanslider4,fanslider5,fanslider6,fanslider7,fanslider8,fanslider9,fanslider10,fanslider11);
        indicatorfans = Arrays.asList(indicatorfan1,indicatorfan2,indicatorfan3,indicatorfan4,indicatorfan5,indicatorfan6,indicatorfan7,indicatorfan8,indicatorfan9,indicatorfan10,indicatorfan11);
        fanrpmlabels = Arrays.asList(fanrpmlabel1,fanrpmlabel2,fanrpmlabel3,fanrpmlabel4,fanrpmlabel5,fanrpmlabel6,fanrpmlabel7,fanrpmlabel8,fanrpmlabel9,fanrpmlabel10,fanrpmlabel11);
        
        pumplabels = Arrays.asList(pumplabel1,pumplabel2,pumplabel3,pumplabel4,pumplabel5,pumplabel6,pumplabel7,pumplabel8,pumplabel9,pumplabel10,pumplabel11);
        pumpsliders = Arrays.asList(pumpslider1,pumpslider2,pumpslider3,pumpslider4,pumpslider5,pumpslider6,pumpslider7,pumpslider8,pumpslider9,pumpslider10,pumpslider11);
        indicatorpumps = Arrays.asList(indicatorpump1,indicatorpump2,indicatorpump3,indicatorpump4,indicatorpump5,indicatorpump6,indicatorpump7,indicatorpump8,indicatorpump9,indicatorpump10,indicatorpump11);
        pumprpmlabels = Arrays.asList(pumprpmlabel1,pumprpmlabel2,pumprpmlabel3,pumprpmlabel4,pumprpmlabel5,pumprpmlabel6,pumprpmlabel7,pumprpmlabel8,pumprpmlabel9,pumprpmlabel10,pumprpmlabel11);
        int vfan = Integer.parseInt(config.getValue("fans"));
        int vpump = Integer.parseInt(config.getValue("pumps"));
        
    for (JLabel fanlabel : fanlabels) {
        fanlabel.setVisible(false);
    }
    for (JSlider fanslider : fansliders) {
        fanslider.setVisible(false);
    }
    for (JTextField rpmlabelfan : indicatorfans) {
        rpmlabelfan.setVisible(false);
    }
    for (JLabel fanrpmlabel : fanrpmlabels) {
        fanrpmlabel.setVisible(false);
    }
    
    
    for (JLabel pumplabel : pumplabels) {
        pumplabel.setVisible(false);
    }
    for (JSlider pumpslider : pumpsliders) {
        pumpslider.setVisible(false);
    }
    for (JTextField rpmlabelpump : indicatorpumps) {
        rpmlabelpump.setVisible(false);
    }
    for (JLabel pumprpmlabel : pumprpmlabels) {
        pumprpmlabel.setVisible(false);
    }
    
        
        for(int i=0; i<vfan; i++){
            fanlabels.get(i).setVisible(true);
            fansliders.get(i).setVisible(true);
            indicatorfans.get(i).setVisible(true);
            fanrpmlabels.get(i).setVisible(true);
        }

        for(int i=0; i<vpump; i++){
            pumplabels.get(i).setVisible(true);
            pumpsliders.get(i).setVisible(true);
            indicatorpumps.get(i).setVisible(true);
            pumprpmlabels.get(i).setVisible(true);
        }
        
        
         for(int i=0; i<vfan; i++){
             
            fansliders.get(i).addChangeListener(this::fanslidersStateChanged);
        }
        for(int i=0; i<vpump; i++){
            pumpsliders.get(i).addChangeListener(this::pumpslidersStateChanged);
        }
        temptitles = Arrays.asList(temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10,temp11);
        degrees = Arrays.asList(degree1,degree2,degree3,degree4,degree5,degree6,degree7,degree8,degree9,degree10,degree11);
        temps = Arrays.asList(Temp1,Temp2,Temp3,Temp4,Temp5,Temp6,Temp7,Temp8,Temp9,Temp10,Temp11);
        panels = Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11);
        for(int u=0;u<temps.size();u++){
            temps.get(u).setVisible(false);
            degrees.get(u).setVisible(false);
            temptitles.get(u).setVisible(false);
            panels.get(u).setVisible(false);
        }
        for(int i=0;i<Integer.parseInt(config.getValue("sensors"));i++){
            temps.get(i).setVisible(true);
            degrees.get(i).setVisible(true);
            temptitles.get(i).setVisible(true);
            panels.get(i).setVisible(true);
        }
        
    }
    
    public void makeBigPanels(){
        makeJPanels(bigpanel1);
        makeJPanels(bigpanel2);
        makeJPanels(bigpanel3);
        makeJPanels(bigpanel4);
        makeJPanels(bigpanel5);
        
    }
    public void checkUpdates(){
        notificationsLabel.setVisible(true);
        notificationsLabel.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("CHECKING FOR UPDATES..."));
        notificationsBar.setVisible(true);
        notificationsBar.setIndeterminate(true);
        updater updater = new updater();
        updater.start();
        updater.setIndicators(notificationsLabel, notificationsBar, this);
        
    }
    
    
    public void setIcons(){
    try {
        
        BufferedImage icon16 = ImageIO.read(main.class.getResourceAsStream("res/icon16.png"));
        BufferedImage icon32 = ImageIO.read(main.class.getResourceAsStream("res/icon32.png"));
        BufferedImage icon64 = ImageIO.read(main.class.getResourceAsStream("res/icon64.png"));
        BufferedImage icon128 = ImageIO.read(main.class.getResourceAsStream("res/icon128.png"));

        List<Image> icons = new ArrayList<Image>();
        icons.add(icon16);
        icons.add(icon32);
        icons.add(icon64);
        icons.add(icon128);
        
        this.setIconImages(icons);
    } catch (IOException ex) {
        Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void loadsecpreviews(){
        loadSecuencePreview(1,this.playButton1, this.clearButton1, bigpanel1);
        loadSecuencePreview(2,this.playButton2, this.clearButton2, bigpanel2);
        loadSecuencePreview(3,this.playButton2, this.clearButton3, bigpanel3);
        loadSecuencePreview(4,this.playButton2, this.clearButton4, bigpanel4);
        loadSecuencePreview(5,this.playButton2, this.clearButton5, bigpanel5);
        
    }
        public void loadSecuencePreview(int number, JToggleButton play, JButton clear, JPanel bigpanel){
            File file = new File(path + File.separator+"secuence"+number+".properties");
            if (!file.exists() || config.getValue("iFinal",file)==""|| config.getValue("iFinal",file)==null){
                play.setEnabled(false);
                clear.setEnabled(false);
                
            }else{
            String ifinal =config.getValue("iFinal", file);
            
            if (ifinal =="" ||ifinal== null){
                
            }else{
            int period = Integer.parseInt(ifinal)/100;
            for(int u= 0;u<100;u++){
            
            int rd = Integer.parseInt(config.getValue("c"+period*u+"r", file));
            int gd = Integer.parseInt(config.getValue("c"+period*u+"g", file));
            int bd = Integer.parseInt(config.getValue("c"+period*u+"b", file));
                    bigpanel.getComponent(u).setBackground(new Color(rd,gd,bd));
            }
            }
        }
        }  
        public void ports(){
        methods.ports(PortsBox);
    }
    
    public void savepercentajes(){
        int vfan = Integer.parseInt(config.getValue("fans"));
        for (int i=0;i<vfan;i++){
            config.setValue("fanvalue"+i, Integer.toString(fansliders.get(i).getValue()));
        }
        int vpump = Integer.parseInt(config.getValue("pumps"));
        for (int i=0;i<vpump;i++){
            config.setValue("pumpvalue"+i, Integer.toString(pumpsliders.get(i).getValue()));
        }
    }
    public void loadpercentajes(){
        int vfan = Integer.parseInt(config.getValue("fans"));
        for (int i=0;i<vfan;i++){
            if(config.getValue("fanvalue"+i)!="" && config.getValue("fanvalue"+i) !=null){
                
            fansliders.get(i).setValue(Integer.parseInt(config.getValue("fanvalue"+i)));
        }
        }
        int vpump = Integer.parseInt(config.getValue("pumps"));
        for (int i=0;i<vpump;i++){
            if(config.getValue("pumpvalue"+i)!="" && config.getValue("pumpvalue"+i) !=null){
                
            pumpsliders.get(i).setValue(Integer.parseInt(config.getValue("pumpvalue"+i)));
        }
        }
    }
    
    
    
    public void refreshMode(){
        boolean ub = methods.refreshMode(RefreshCheckBox);
        if (ub){
            refreshSecondsSpinner.setEnabled(true);
            jLabel19.setEnabled(true);
            jLabel18.setEnabled(true);
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(refreshTemp, 0, Long.parseLong(refreshSecondsSpinner.getValue().toString()), TimeUnit.SECONDS);
        }
    }
    private void updaterpms(){
        
        for (int i=0;i<Integer.parseInt(config.getValue("fans"));i++){
            indicatorfans.get(i).setText(Integer.toString(methods.calculaterpms(i, fansliders, "fan")));
        }
        
        for (int i=0;i<Integer.parseInt(config.getValue("pumps"));i++){
            indicatorpumps.get(i).setText(Integer.toString(methods.calculaterpms(i, pumpsliders, "pump")));
        }

    }
    public void write(){
        if (methods.isConnected()){
           methods.write(mode, picker, fansliders, pumpsliders, LedC1, LedC2, LedC3,LedC4,testmode,music);
        }else if (testmode){
            methods.write(mode, picker, fansliders, pumpsliders, LedC1, LedC2, LedC3,LedC4,testmode,music);
        }
    }
    public void makeJPanels(JPanel bigPanel){
       
        for (int i=0;i<100;i++){
            
        }
        
        
        JPanel p1 = new JPanel();   
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        JPanel p10 = new JPanel();
        JPanel p11 = new JPanel();
        JPanel p12 = new JPanel();
        JPanel p13 = new JPanel();
        JPanel p14 = new JPanel();
        JPanel p15 = new JPanel();
        JPanel p16 = new JPanel();
        JPanel p17 = new JPanel();
        JPanel p18 = new JPanel();
        JPanel p19 = new JPanel();
        JPanel p20 = new JPanel();
        JPanel p21 = new JPanel();
        JPanel p22 = new JPanel();
        JPanel p23 = new JPanel();
        JPanel p24 = new JPanel();
        JPanel p25 = new JPanel();
        JPanel p26 = new JPanel();
        JPanel p27 = new JPanel();
        JPanel p28 = new JPanel();
        JPanel p29 = new JPanel();
        JPanel p30 = new JPanel();
        JPanel p31 = new JPanel();
        JPanel p32 = new JPanel();
        JPanel p33 = new JPanel();
        JPanel p34 = new JPanel();
        JPanel p35 = new JPanel();
        JPanel p36 = new JPanel();
        JPanel p37 = new JPanel();
        JPanel p38 = new JPanel();
        JPanel p39 = new JPanel();
        JPanel p40 = new JPanel();
        JPanel p41 = new JPanel();
        JPanel p42 = new JPanel();
        JPanel p43 = new JPanel();
        JPanel p44 = new JPanel();
        JPanel p45 = new JPanel();
        JPanel p46 = new JPanel();
        JPanel p47 = new JPanel();
        JPanel p48 = new JPanel();
        JPanel p49 = new JPanel();
        JPanel p50 = new JPanel();
        JPanel p51 = new JPanel();   
        JPanel p52 = new JPanel();
        JPanel p53 = new JPanel();
        JPanel p54 = new JPanel();
        JPanel p55 = new JPanel();
        JPanel p56 = new JPanel();
        JPanel p57 = new JPanel();
        JPanel p58 = new JPanel();
        JPanel p59 = new JPanel();
        JPanel p60 = new JPanel();
        JPanel p61 = new JPanel();
        JPanel p62 = new JPanel();
        JPanel p63 = new JPanel();
        JPanel p64 = new JPanel();
        JPanel p65 = new JPanel();
        JPanel p66 = new JPanel();
        JPanel p67 = new JPanel();
        JPanel p68 = new JPanel();
        JPanel p69 = new JPanel();
        JPanel p70 = new JPanel();
        JPanel p71 = new JPanel();
        JPanel p72 = new JPanel();
        JPanel p73 = new JPanel();
        JPanel p74 = new JPanel();
        JPanel p75 = new JPanel();
        JPanel p76 = new JPanel();
        JPanel p77 = new JPanel();
        JPanel p78 = new JPanel();
        JPanel p79 = new JPanel();
        JPanel p80 = new JPanel();
        JPanel p81 = new JPanel();
        JPanel p82 = new JPanel();
        JPanel p83 = new JPanel();
        JPanel p84 = new JPanel();
        JPanel p85 = new JPanel();
        JPanel p86 = new JPanel();
        JPanel p87 = new JPanel();
        JPanel p88 = new JPanel();
        JPanel p89 = new JPanel();
        JPanel p90 = new JPanel();
        JPanel p91 = new JPanel();
        JPanel p92 = new JPanel();
        JPanel p93 = new JPanel();
        JPanel p94 = new JPanel();
        JPanel p95 = new JPanel();
        JPanel p96 = new JPanel();
        JPanel p97 = new JPanel();
        JPanel p98 = new JPanel();
        JPanel p99 = new JPanel();
        JPanel p100 = new JPanel();
        
        
               int i = 2;
               int a = 35;
               p1.setPreferredSize(new Dimension(i,a));
               p2.setPreferredSize(new Dimension(i,a));
               p3.setPreferredSize(new Dimension(i,a));
               p4.setPreferredSize(new Dimension(i,a));
               p5.setPreferredSize(new Dimension(i,a));
               p6.setPreferredSize(new Dimension(i,a));
               p7.setPreferredSize(new Dimension(i,a));
               p8.setPreferredSize(new Dimension(i,a));
               p9.setPreferredSize(new Dimension(i,a));
               p10.setPreferredSize(new Dimension(i,a));
               p11.setPreferredSize(new Dimension(i,a));
               p12.setPreferredSize(new Dimension(i,a));
               p13.setPreferredSize(new Dimension(i,a));
               p14.setPreferredSize(new Dimension(i,a));
               p15.setPreferredSize(new Dimension(i,a));
               p16.setPreferredSize(new Dimension(i,a));
               p17.setPreferredSize(new Dimension(i,a));
               p18.setPreferredSize(new Dimension(i,a));
               p19.setPreferredSize(new Dimension(i,a));
               p20.setPreferredSize(new Dimension(i,a));
               p21.setPreferredSize(new Dimension(i,a));
               p22.setPreferredSize(new Dimension(i,a));
               p23.setPreferredSize(new Dimension(i,a));
               p24.setPreferredSize(new Dimension(i,a));
               p25.setPreferredSize(new Dimension(i,a));
               p26.setPreferredSize(new Dimension(i,a));
               p27.setPreferredSize(new Dimension(i,a));
               p28.setPreferredSize(new Dimension(i,a));
               p29.setPreferredSize(new Dimension(i,a));
               p30.setPreferredSize(new Dimension(i,a));
               p31.setPreferredSize(new Dimension(i,a));
               p32.setPreferredSize(new Dimension(i,a));
               p33.setPreferredSize(new Dimension(i,a));
               p34.setPreferredSize(new Dimension(i,a));
               p35.setPreferredSize(new Dimension(i,a));
               p36.setPreferredSize(new Dimension(i,a));
               p37.setPreferredSize(new Dimension(i,a));
               p38.setPreferredSize(new Dimension(i,a));
               p39.setPreferredSize(new Dimension(i,a));
               p40.setPreferredSize(new Dimension(i,a));
               p41.setPreferredSize(new Dimension(i,a));
               p42.setPreferredSize(new Dimension(i,a));
               p43.setPreferredSize(new Dimension(i,a));
               p44.setPreferredSize(new Dimension(i,a));
               p45.setPreferredSize(new Dimension(i,a));
               p46.setPreferredSize(new Dimension(i,a));
               p47.setPreferredSize(new Dimension(i,a));
               p48.setPreferredSize(new Dimension(i,a));
               p49.setPreferredSize(new Dimension(i,a));
               p50.setPreferredSize(new Dimension(i,a));
               p51.setPreferredSize(new Dimension(i,a));
               p52.setPreferredSize(new Dimension(i,a));
               p53.setPreferredSize(new Dimension(i,a));
               p54.setPreferredSize(new Dimension(i,a));
               p55.setPreferredSize(new Dimension(i,a));
               p56.setPreferredSize(new Dimension(i,a));
               p57.setPreferredSize(new Dimension(i,a));
               p58.setPreferredSize(new Dimension(i,a));
               p59.setPreferredSize(new Dimension(i,a));
               p60.setPreferredSize(new Dimension(i,a));
               p61.setPreferredSize(new Dimension(i,a));
               p62.setPreferredSize(new Dimension(i,a));
               p63.setPreferredSize(new Dimension(i,a));
               p64.setPreferredSize(new Dimension(i,a));
               p65.setPreferredSize(new Dimension(i,a));
               p66.setPreferredSize(new Dimension(i,a));
               p67.setPreferredSize(new Dimension(i,a));
               p68.setPreferredSize(new Dimension(i,a));
               p69.setPreferredSize(new Dimension(i,a));
               p70.setPreferredSize(new Dimension(i,a));
               p71.setPreferredSize(new Dimension(i,a));
               p72.setPreferredSize(new Dimension(i,a));
               p73.setPreferredSize(new Dimension(i,a));
               p74.setPreferredSize(new Dimension(i,a));
               p75.setPreferredSize(new Dimension(i,a));
               p76.setPreferredSize(new Dimension(i,a));
               p77.setPreferredSize(new Dimension(i,a));
               p78.setPreferredSize(new Dimension(i,a));
               p79.setPreferredSize(new Dimension(i,a));
               p80.setPreferredSize(new Dimension(i,a));
               p81.setPreferredSize(new Dimension(i,a));
               p82.setPreferredSize(new Dimension(i,a));
               p83.setPreferredSize(new Dimension(i,a));
               p84.setPreferredSize(new Dimension(i,a));
               p85.setPreferredSize(new Dimension(i,a));
               p86.setPreferredSize(new Dimension(i,a));
               p87.setPreferredSize(new Dimension(i,a));
               p88.setPreferredSize(new Dimension(i,a));
               p89.setPreferredSize(new Dimension(i,a));
               p90.setPreferredSize(new Dimension(i,a));
               p91.setPreferredSize(new Dimension(i,a));
               p92.setPreferredSize(new Dimension(i,a));
               p93.setPreferredSize(new Dimension(i,a));
               p94.setPreferredSize(new Dimension(i,a));
               p95.setPreferredSize(new Dimension(i,a));
               p96.setPreferredSize(new Dimension(i,a));
               p97.setPreferredSize(new Dimension(i,a));
               p98.setPreferredSize(new Dimension(i,a));
               p99.setPreferredSize(new Dimension(i,a));
               p100.setPreferredSize(new Dimension(i,a));
      
               
        bigPanel.add(p1);
        bigPanel.add(p2);
        bigPanel.add(p3);
        bigPanel.add(p4);
        bigPanel.add(p5);
        bigPanel.add(p6);
        bigPanel.add(p7);
        bigPanel.add(p8);
        bigPanel.add(p9);
        bigPanel.add(p10);
        bigPanel.add(p11);
        bigPanel.add(p12);
        bigPanel.add(p13);
        bigPanel.add(p14);
        bigPanel.add(p15);
        bigPanel.add(p16);
        bigPanel.add(p17);
        bigPanel.add(p18);
        bigPanel.add(p19);
        bigPanel.add(p20);
        bigPanel.add(p21);
        bigPanel.add(p22);
        bigPanel.add(p23);
        bigPanel.add(p24);
        bigPanel.add(p25);
        bigPanel.add(p26);
        bigPanel.add(p27);
        bigPanel.add(p28);
        bigPanel.add(p29);
        bigPanel.add(p30);
        bigPanel.add(p31);
        bigPanel.add(p32);
        bigPanel.add(p33);
        bigPanel.add(p34);
        bigPanel.add(p35);
        bigPanel.add(p36);
        bigPanel.add(p37);
        bigPanel.add(p38);
        bigPanel.add(p39);
        bigPanel.add(p40);
        bigPanel.add(p41);
        bigPanel.add(p42);
        bigPanel.add(p43);
        bigPanel.add(p44);
        bigPanel.add(p45);
        bigPanel.add(p46);
        bigPanel.add(p47);
        bigPanel.add(p48);
        bigPanel.add(p49);
        bigPanel.add(p50);
        bigPanel.add(p51);
        bigPanel.add(p52);
        bigPanel.add(p53);
        bigPanel.add(p54);
        bigPanel.add(p55);
        bigPanel.add(p56);
        bigPanel.add(p57);
        bigPanel.add(p58);
        bigPanel.add(p59);
        bigPanel.add(p60);
        bigPanel.add(p61);
        bigPanel.add(p62);
        bigPanel.add(p63);
        bigPanel.add(p64);
        bigPanel.add(p65);
        bigPanel.add(p66);
        bigPanel.add(p67);
        bigPanel.add(p68);
        bigPanel.add(p69);
        bigPanel.add(p70);
        bigPanel.add(p71);
        bigPanel.add(p72);
        bigPanel.add(p73);
        bigPanel.add(p74);
        bigPanel.add(p75);
        bigPanel.add(p76);
        bigPanel.add(p77);
        bigPanel.add(p78);
        bigPanel.add(p79);
        bigPanel.add(p80);
        bigPanel.add(p81);
        bigPanel.add(p82);
        bigPanel.add(p83);
        bigPanel.add(p84);
        bigPanel.add(p85);
        bigPanel.add(p86);
        bigPanel.add(p87);
        bigPanel.add(p88);
        bigPanel.add(p89);
        bigPanel.add(p90);
        bigPanel.add(p91);
        bigPanel.add(p92);
        bigPanel.add(p93);
        bigPanel.add(p94);
        bigPanel.add(p95);
        bigPanel.add(p96);
        bigPanel.add(p97);
        bigPanel.add(p98);
        bigPanel.add(p99);
        bigPanel.add(p100);
        
        
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
        if (com.alphamods.controlcenter.utils.config.getValue("refreshTime") == null || com.alphamods.controlcenter.utils.config.getValue("refreshTime") == ""){
          return 5;  
        }else {
            return Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("refreshTime"));
        }
    }
    private void initPanel(){
        loadpreviews();
        
        buttonGroup1.add(fadeRadioButton);
        buttonGroup1.add(normalRadioButton);
        normalRadioButton.setSelected(true);
        notificationsLabel.setVisible(false);
        notificationsBar.setVisible(false);
        if (!methods.isConnected()){
            fadeRadioButton.setEnabled(false);
            normalRadioButton.setEnabled(false);
            notConnectedLabel.setVisible(true);
            recordButton1.setEnabled(false);
            playButton1.setEnabled(false);
            Refresh2.setEnabled(false);
            picker.setEnabled(false);
            clearButton1.setEnabled(false);
        }
        refreshSecondsSpinner.setEnabled(false);
        jLabel19.setEnabled(false);
        jLabel18.setEnabled(false);
        
        PortsBox.removeAllItems();
        methods.getArduino().getSerialPorts().forEach(i -> PortsBox.addItem(i));
        if(PortsBox.getSelectedItem()==null){
            connectButton.setEnabled(false);
        }else{
            connectButton.setEnabled(true);            
        }
        if(config.getValue("port")!=null ||config.getValue("port")!=""){
            
            
            try {
                PortsBox.setSelectedItem(config.getValue("port"));
                methods.connect(evento, PortsBox);
                

            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(methods.isConnected()){
                connectButton.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("DISCONNECT"));
                fadeRadioButton.setEnabled(true);
                normalRadioButton.setEnabled(true);
                recordButton1.setEnabled(true);
                playButton1.setEnabled(true);
                picker.setEnabled(true);
                notConnectedLabel.setVisible(false);
                Refresh2.setEnabled(true);
                clearButton1.setEnabled(true);
                loadsecpreviews();
                write();
            }
                
        }
        this.ledModeLabel.setVisible(false);
        this.fadeRadioButton.setVisible(false);
        this.normalRadioButton.setVisible(false);
        
        int u = Integer.parseInt(config.getValue("leds"));
        if (u==1){
            LedC1.setEnabled(true);
            LedC2.setEnabled(false);
            LedC3.setEnabled(false);
            LedC4.setEnabled(false);
            LedC2.setSelected(false);
            LedC3.setSelected(false);
            LedC4.setSelected(false);
        }else if(u==2){
            LedC1.setEnabled(true);
            LedC2.setEnabled(true);
            LedC3.setEnabled(false);
            LedC4.setEnabled(false); 
            LedC3.setSelected(false);
            LedC4.setSelected(false);
        }else if (u==3){
            LedC1.setEnabled(true);
            LedC2.setEnabled(true);
            LedC3.setEnabled(true);
            LedC4.setEnabled(false); 
            LedC4.setSelected(false);
        }else if (u==4){
            LedC1.setEnabled(true);
            LedC2.setEnabled(true);
            LedC3.setEnabled(true);
            LedC4.setEnabled(true);            
        }
        if (music){
            LedC1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("MUSIC CHANNEL"));
            LedC1.setFont(LedC1.getFont().deriveFont(Font.BOLD));
            LedC1.setForeground(Color.green.darker());
            LedC1.setEnabled(false);
        }
    }
boolean dialogdone =false;
JFrame frame = this;
ChartPanel Panel;
MouseListener mousa = new MouseAdapter()  {
                public void mouseClicked(MouseEvent e) {
                    if(dialogdone==true){
                        
                    }else{
                        JDialog dialog = new JDialog(frame);
                        dialog.add(Panel);
                        dialog.setTitle(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("TEMPERATURE CHART"));
                        dialog.pack();
                        dialog.setLocationRelativeTo(frame);
                        dialog.setVisible(true);
                        dialog.addWindowListener(null);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.addWindowListener(window);
                        dialogdone=true;
                    }
                        
                    }

                    
                };
WindowListener window = new WindowAdapter(){
    public void windowClosing(WindowEvent e){
       
        chart2();
        dialogdone=false;
    }
};

public void chart2(){
     
      JFreeChart jfreechart = createChart(createDataset());
      Panel = new ChartPanel(jfreechart);
      jfreechart.getTitle().setFont(new java.awt.Font("Segoe UI Light", 0, 26));
    grafica.setLayout(new BorderLayout());
grafica.add(Panel,BorderLayout.CENTER);
grafica.validate();
grafica.setToolTipText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("CLICK FOR A DETAILED VIEW"));
Panel.addMouseListener(mousa);

}
private static JFreeChart createChart(XYDataset xydataset)
        {
                JFreeChart jfreechart = ChartFactory.createXYLineChart(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("GRÁFICA"), java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("TIEMPO"), java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("GRADOS ºC"), xydataset, PlotOrientation.VERTICAL, true, true, false);
                jfreechart.setBackgroundPaint(new Color(240,240,240));
                
                XYPlot xyplot = (XYPlot)jfreechart.getPlot();
                for (int u = 0; u<6;u++){
    xyplot.getRenderer().setSeriesStroke(u,new BasicStroke(2.0f));

        
}
                XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
                xylineandshaperenderer.setBaseShapesVisible(true);
                xylineandshaperenderer.setBaseShapesFilled(true);
                xylineandshaperenderer.setDrawOutlines(true);
                NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
                numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                return jfreechart;
        }
private  XYDataset createDataset()
        { 

            if(xyseriescollection.getSeries().size()>1){
                return xyseriescollection;
            }else {
                    int active = Integer.parseInt(config.getValue("sensors"));
for (int i = 0; i < active; i++)
        
                {
                        XYSeries xyseries = new XYSeries("Temp. "+Integer.toString(i+1));
    xyseriescollection.addSeries(xyseries);


                }
    
      
                return xyseriescollection;                
            }

        }
public void tochart2(List<String> message){
    List<String> args = message;
    int active = Integer.parseInt(config.getValue("sensors"));
    for (int i = 0; i < active; i++)
        
                {
                        XYSeries xyseries = xyseriescollection.getSeries(i);
                        xyseries.add(this.i, Integer.parseInt(args.get(i)));


                }
    i++;
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
        bigpanel1 = new javax.swing.JPanel();
        recordButton1 = new javax.swing.JToggleButton();
        clearButton1 = new javax.swing.JButton();
        sec1label = new javax.swing.JLabel();
        sec2label = new javax.swing.JLabel();
        recordButton2 = new javax.swing.JToggleButton();
        clearButton2 = new javax.swing.JButton();
        bigpanel2 = new javax.swing.JPanel();
        sec3label = new javax.swing.JLabel();
        recordButton3 = new javax.swing.JToggleButton();
        clearButton3 = new javax.swing.JButton();
        bigpanel3 = new javax.swing.JPanel();
        sec4label = new javax.swing.JLabel();
        bigpanel4 = new javax.swing.JPanel();
        recordButton4 = new javax.swing.JToggleButton();
        clearButton4 = new javax.swing.JButton();
        sec5label = new javax.swing.JLabel();
        bigpanel5 = new javax.swing.JPanel();
        recordButton5 = new javax.swing.JToggleButton();
        clearButton5 = new javax.swing.JButton();
        loopCheckBox1 = new javax.swing.JCheckBox();
        playButton1 = new javax.swing.JToggleButton();
        playButton2 = new javax.swing.JToggleButton();
        playButton3 = new javax.swing.JToggleButton();
        playButton4 = new javax.swing.JToggleButton();
        playButton5 = new javax.swing.JToggleButton();
        loopCheckBox2 = new javax.swing.JCheckBox();
        loopCheckBox3 = new javax.swing.JCheckBox();
        loopCheckBox4 = new javax.swing.JCheckBox();
        loopCheckBox5 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        LedC1 = new javax.swing.JCheckBox();
        LedC2 = new javax.swing.JCheckBox();
        LedC3 = new javax.swing.JCheckBox();
        LedC4 = new javax.swing.JCheckBox();
        testMode = new javax.swing.JToggleButton();
        FanPumpPanel = new javax.swing.JPanel();
        fansTitleLabel = new javax.swing.JLabel();
        pumpsTitleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Refresh2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        RefreshCheckBox = new javax.swing.JCheckBox();
        refreshSecondsSpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        fanlabel1 = new javax.swing.JLabel();
        fanslider1 = new javax.swing.JSlider();
        indicatorfan1 = new javax.swing.JTextField();
        fanrpmlabel1 = new javax.swing.JLabel();
        fanrpmlabel2 = new javax.swing.JLabel();
        indicatorfan2 = new javax.swing.JTextField();
        fanslider2 = new javax.swing.JSlider();
        fanlabel2 = new javax.swing.JLabel();
        fanrpmlabel3 = new javax.swing.JLabel();
        indicatorfan3 = new javax.swing.JTextField();
        fanslider3 = new javax.swing.JSlider();
        fanlabel3 = new javax.swing.JLabel();
        fanrpmlabel4 = new javax.swing.JLabel();
        indicatorfan4 = new javax.swing.JTextField();
        fanslider4 = new javax.swing.JSlider();
        fanlabel4 = new javax.swing.JLabel();
        fanrpmlabel5 = new javax.swing.JLabel();
        indicatorfan5 = new javax.swing.JTextField();
        fanslider5 = new javax.swing.JSlider();
        fanlabel5 = new javax.swing.JLabel();
        fanrpmlabel6 = new javax.swing.JLabel();
        indicatorfan6 = new javax.swing.JTextField();
        fanslider6 = new javax.swing.JSlider();
        fanlabel6 = new javax.swing.JLabel();
        fanrpmlabel7 = new javax.swing.JLabel();
        indicatorfan7 = new javax.swing.JTextField();
        fanslider7 = new javax.swing.JSlider();
        fanlabel7 = new javax.swing.JLabel();
        fanrpmlabel8 = new javax.swing.JLabel();
        indicatorfan8 = new javax.swing.JTextField();
        fanslider8 = new javax.swing.JSlider();
        fanlabel8 = new javax.swing.JLabel();
        fanrpmlabel9 = new javax.swing.JLabel();
        indicatorfan9 = new javax.swing.JTextField();
        fanslider9 = new javax.swing.JSlider();
        fanlabel9 = new javax.swing.JLabel();
        fanrpmlabel10 = new javax.swing.JLabel();
        indicatorfan10 = new javax.swing.JTextField();
        fanslider10 = new javax.swing.JSlider();
        fanlabel10 = new javax.swing.JLabel();
        fanlabel11 = new javax.swing.JLabel();
        fanslider11 = new javax.swing.JSlider();
        indicatorfan11 = new javax.swing.JTextField();
        fanrpmlabel11 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        pumplabel1 = new javax.swing.JLabel();
        pumpslider1 = new javax.swing.JSlider();
        indicatorpump1 = new javax.swing.JTextField();
        pumprpmlabel1 = new javax.swing.JLabel();
        pumprpmlabel2 = new javax.swing.JLabel();
        indicatorpump2 = new javax.swing.JTextField();
        pumpslider2 = new javax.swing.JSlider();
        pumplabel2 = new javax.swing.JLabel();
        pumprpmlabel3 = new javax.swing.JLabel();
        indicatorpump3 = new javax.swing.JTextField();
        pumpslider3 = new javax.swing.JSlider();
        pumplabel3 = new javax.swing.JLabel();
        pumprpmlabel4 = new javax.swing.JLabel();
        indicatorpump4 = new javax.swing.JTextField();
        pumpslider4 = new javax.swing.JSlider();
        pumplabel4 = new javax.swing.JLabel();
        pumprpmlabel5 = new javax.swing.JLabel();
        indicatorpump5 = new javax.swing.JTextField();
        pumpslider5 = new javax.swing.JSlider();
        pumplabel5 = new javax.swing.JLabel();
        pumprpmlabel6 = new javax.swing.JLabel();
        indicatorpump6 = new javax.swing.JTextField();
        pumpslider6 = new javax.swing.JSlider();
        pumplabel6 = new javax.swing.JLabel();
        pumprpmlabel7 = new javax.swing.JLabel();
        indicatorpump7 = new javax.swing.JTextField();
        pumpslider7 = new javax.swing.JSlider();
        pumplabel7 = new javax.swing.JLabel();
        pumprpmlabel8 = new javax.swing.JLabel();
        indicatorpump8 = new javax.swing.JTextField();
        pumpslider8 = new javax.swing.JSlider();
        pumplabel8 = new javax.swing.JLabel();
        pumprpmlabel9 = new javax.swing.JLabel();
        indicatorpump9 = new javax.swing.JTextField();
        pumpslider9 = new javax.swing.JSlider();
        pumplabel9 = new javax.swing.JLabel();
        pumprpmlabel10 = new javax.swing.JLabel();
        indicatorpump10 = new javax.swing.JTextField();
        pumpslider10 = new javax.swing.JSlider();
        pumplabel10 = new javax.swing.JLabel();
        pumplabel11 = new javax.swing.JLabel();
        pumpslider11 = new javax.swing.JSlider();
        indicatorpump11 = new javax.swing.JTextField();
        pumprpmlabel11 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        grafica = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        t1 = new javax.swing.JPanel();
        temp1 = new javax.swing.JLabel();
        degree1 = new javax.swing.JLabel();
        Temp1 = new javax.swing.JTextField();
        t2 = new javax.swing.JPanel();
        degree2 = new javax.swing.JLabel();
        Temp2 = new javax.swing.JTextField();
        temp2 = new javax.swing.JLabel();
        t3 = new javax.swing.JPanel();
        temp3 = new javax.swing.JLabel();
        degree3 = new javax.swing.JLabel();
        Temp3 = new javax.swing.JTextField();
        t4 = new javax.swing.JPanel();
        temp4 = new javax.swing.JLabel();
        Temp4 = new javax.swing.JTextField();
        degree4 = new javax.swing.JLabel();
        t5 = new javax.swing.JPanel();
        temp5 = new javax.swing.JLabel();
        Temp5 = new javax.swing.JTextField();
        degree5 = new javax.swing.JLabel();
        t6 = new javax.swing.JPanel();
        Temp6 = new javax.swing.JTextField();
        temp6 = new javax.swing.JLabel();
        degree6 = new javax.swing.JLabel();
        t7 = new javax.swing.JPanel();
        temp7 = new javax.swing.JLabel();
        Temp7 = new javax.swing.JTextField();
        degree7 = new javax.swing.JLabel();
        t8 = new javax.swing.JPanel();
        Temp8 = new javax.swing.JTextField();
        degree8 = new javax.swing.JLabel();
        temp8 = new javax.swing.JLabel();
        t9 = new javax.swing.JPanel();
        Temp9 = new javax.swing.JTextField();
        degree9 = new javax.swing.JLabel();
        temp9 = new javax.swing.JLabel();
        t10 = new javax.swing.JPanel();
        temp10 = new javax.swing.JLabel();
        Temp10 = new javax.swing.JTextField();
        degree10 = new javax.swing.JLabel();
        t11 = new javax.swing.JPanel();
        Temp11 = new javax.swing.JTextField();
        degree11 = new javax.swing.JLabel();
        temp11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        refreshPortsButton = new javax.swing.JButton();
        PortsBox = new javax.swing.JComboBox();
        connectButton = new javax.swing.JButton();
        notConnectedLabel = new javax.swing.JLabel();
        notificationsLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        notificationsBar = new javax.swing.JProgressBar();
        menubar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        settings = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle"); // NOI18N
        setTitle(bundle.getString("ALPHA CONTROL CENTER")); // NOI18N
        setBackground(new java.awt.Color(66, 66, 66));
        setIconImages(null);
        setResizable(false);
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
        ledModeLabel.setText(bundle.getString("LED MODE")); // NOI18N

        fadeRadioButton.setText(bundle.getString("FADE")); // NOI18N
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

        normalRadioButton.setText(bundle.getString("NORMAL")); // NOI18N
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

        favColorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Favourite colors")));

        labelColor1.setText(bundle.getString("COLOR 1")); // NOI18N

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

        labelColor6.setText(bundle.getString("COLOR 6")); // NOI18N

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

        getButtonColor6.setText(bundle.getString("GET")); // NOI18N
        getButtonColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor6ActionPerformed(evt);
            }
        });

        setButtonColor6.setText(bundle.getString("SET")); // NOI18N
        setButtonColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor6ActionPerformed(evt);
            }
        });

        getButtonColor1.setText(bundle.getString("GET")); // NOI18N
        getButtonColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor1ActionPerformed(evt);
            }
        });

        setButtonColor1.setText(bundle.getString("SET")); // NOI18N
        setButtonColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor1ActionPerformed(evt);
            }
        });

        labelColor2.setText(bundle.getString("COLOR 2")); // NOI18N

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

        setButtonColor2.setText(bundle.getString("SET")); // NOI18N
        setButtonColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor2ActionPerformed(evt);
            }
        });

        labelColor3.setText(bundle.getString("COLOR 3")); // NOI18N

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

        getButtonColor2.setText(bundle.getString("GET")); // NOI18N
        getButtonColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor2ActionPerformed(evt);
            }
        });

        labelColor7.setText(bundle.getString("COLOR 7")); // NOI18N

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

        getButtonColor7.setText(bundle.getString("GET")); // NOI18N
        getButtonColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor7ActionPerformed(evt);
            }
        });

        setButtonColor7.setText(bundle.getString("SET")); // NOI18N
        setButtonColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor7ActionPerformed(evt);
            }
        });

        setButtonColor8.setText(bundle.getString("SET")); // NOI18N
        setButtonColor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor8ActionPerformed(evt);
            }
        });

        labelColor8.setText(bundle.getString("COLOR 8")); // NOI18N

        getButtonColor8.setText(bundle.getString("GET")); // NOI18N
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

        getButtonColor3.setText(bundle.getString("GET")); // NOI18N
        getButtonColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor3ActionPerformed(evt);
            }
        });

        setButtonColor3.setText(bundle.getString("SET")); // NOI18N
        setButtonColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor3ActionPerformed(evt);
            }
        });

        labelColor4.setText(bundle.getString("COLOR 4")); // NOI18N

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

        labelColor9.setText(bundle.getString("COLOR 9")); // NOI18N

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

        getButtonColor9.setText(bundle.getString("GET")); // NOI18N
        getButtonColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor9ActionPerformed(evt);
            }
        });

        setButtonColor9.setText(bundle.getString("SET")); // NOI18N
        setButtonColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor9ActionPerformed(evt);
            }
        });

        getButtonColor4.setText(bundle.getString("GET")); // NOI18N
        getButtonColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor4ActionPerformed(evt);
            }
        });

        setButtonColor4.setText(bundle.getString("SET")); // NOI18N
        setButtonColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor4ActionPerformed(evt);
            }
        });

        labelColor5.setText(bundle.getString("COLOR 5")); // NOI18N

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

        getButtonColor5.setText(bundle.getString("GET")); // NOI18N
        getButtonColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor5ActionPerformed(evt);
            }
        });

        setButtonColor5.setText(bundle.getString("SET")); // NOI18N
        setButtonColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor5ActionPerformed(evt);
            }
        });

        setButtonColor10.setText(bundle.getString("SET")); // NOI18N
        setButtonColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor10ActionPerformed(evt);
            }
        });

        labelColor10.setText(bundle.getString("COLOR 10")); // NOI18N

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

        getButtonColor10.setText(bundle.getString("GET")); // NOI18N
        getButtonColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonColor10ActionPerformed(evt);
            }
        });

        cleanButton.setText(bundle.getString("CLEAN ALL")); // NOI18N
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        labelColor11.setText(bundle.getString("COLOR 11")); // NOI18N

        getButtonColor11.setText(bundle.getString("GET")); // NOI18N
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

        setButtonColor11.setText(bundle.getString("SET")); // NOI18N
        setButtonColor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor11ActionPerformed(evt);
            }
        });

        labelColor12.setText(bundle.getString("COLOR 12")); // NOI18N

        getButtonColor12.setText(bundle.getString("GET")); // NOI18N
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

        setButtonColor12.setText(bundle.getString("SET")); // NOI18N
        setButtonColor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonColor12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout favColorsPanelLayout = new javax.swing.GroupLayout(favColorsPanel);
        favColorsPanel.setLayout(favColorsPanelLayout);
        favColorsPanelLayout.setHorizontalGroup(
            favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cleanButton)
                .addGap(100, 100, 100))
            .addGroup(favColorsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, favColorsPanelLayout.createSequentialGroup()
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColor5)
                                    .addComponent(setButtonColor5))
                                .addGap(7, 7, 7)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getButtonColor5)
                                    .addComponent(panelColor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, favColorsPanelLayout.createSequentialGroup()
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(setButtonColor6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                    .addComponent(getButtonColor12)))))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
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
                                    .addComponent(setButtonColor3)
                                    .addComponent(setButtonColor4)
                                    .addComponent(setButtonColor2))
                                .addGap(7, 7, 7)
                                .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor2)
                                    .addComponent(getButtonColor3)
                                    .addComponent(panelColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getButtonColor4)
                                    .addComponent(panelColor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
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
                                .addComponent(panelColor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
                        .addComponent(labelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setButtonColor6))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelColor12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelColor12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(favColorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setButtonColor12)
                            .addComponent(getButtonColor12)))
                    .addGroup(favColorsPanelLayout.createSequentialGroup()
                        .addComponent(panelColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getButtonColor6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(cleanButton)
                .addContainerGap())
        );

        colors_secuencesPanel.addTab(bundle.getString("SAVED COLORS"), favColorsPanel); // NOI18N

        SecuencesTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SecuencesTitle.setText(bundle.getString("SECUENCES")); // NOI18N

        bigpanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bigpanel1.setPreferredSize(new java.awt.Dimension(100, 37));
        bigpanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        recordButton1.setText(bundle.getString("RECORD")); // NOI18N
        recordButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recordButton1ItemStateChanged(evt);
            }
        });

        clearButton1.setText(bundle.getString("CLEAR")); // NOI18N
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        sec1label.setText(bundle.getString("SEC. 1")); // NOI18N

        sec2label.setText(bundle.getString("SEC. 2")); // NOI18N

        recordButton2.setText(bundle.getString("RECORD")); // NOI18N
        recordButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recordButton2ItemStateChanged(evt);
            }
        });

        clearButton2.setText(bundle.getString("CLEAR")); // NOI18N
        clearButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton2ActionPerformed(evt);
            }
        });

        bigpanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bigpanel2.setPreferredSize(new java.awt.Dimension(100, 37));
        bigpanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        sec3label.setText(bundle.getString("SEC. 3")); // NOI18N

        recordButton3.setText(bundle.getString("RECORD")); // NOI18N
        recordButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recordButton3ItemStateChanged(evt);
            }
        });

        clearButton3.setText(bundle.getString("CLEAR")); // NOI18N
        clearButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton3ActionPerformed(evt);
            }
        });

        bigpanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bigpanel3.setPreferredSize(new java.awt.Dimension(100, 37));
        bigpanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        sec4label.setText(bundle.getString("SEC. 4")); // NOI18N

        bigpanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bigpanel4.setPreferredSize(new java.awt.Dimension(100, 37));
        bigpanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        recordButton4.setText(bundle.getString("RECORD")); // NOI18N
        recordButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recordButton4ItemStateChanged(evt);
            }
        });

        clearButton4.setText(bundle.getString("CLEAR")); // NOI18N
        clearButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton4ActionPerformed(evt);
            }
        });

        sec5label.setText(bundle.getString("SEC. 5")); // NOI18N

        bigpanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bigpanel5.setPreferredSize(new java.awt.Dimension(100, 37));
        bigpanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        recordButton5.setText(bundle.getString("RECORD")); // NOI18N
        recordButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recordButton5ItemStateChanged(evt);
            }
        });

        clearButton5.setText(bundle.getString("CLEAR")); // NOI18N
        clearButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton5ActionPerformed(evt);
            }
        });

        loopCheckBox1.setText(bundle.getString("LOOP")); // NOI18N
        loopCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loopCheckBox1ItemStateChanged(evt);
            }
        });
        loopCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loopCheckBox1ActionPerformed(evt);
            }
        });

        playButton1.setText(bundle.getString("PLAY")); // NOI18N
        playButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playButton1ItemStateChanged(evt);
            }
        });

        playButton2.setText(bundle.getString("PLAY")); // NOI18N
        playButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playButton2ItemStateChanged(evt);
            }
        });

        playButton3.setText(bundle.getString("PLAY")); // NOI18N
        playButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playButton3ItemStateChanged(evt);
            }
        });

        playButton4.setText(bundle.getString("PLAY")); // NOI18N
        playButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playButton4ItemStateChanged(evt);
            }
        });

        playButton5.setText(bundle.getString("PLAY")); // NOI18N
        playButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playButton5ItemStateChanged(evt);
            }
        });

        loopCheckBox2.setText(bundle.getString("LOOP")); // NOI18N
        loopCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loopCheckBox2ItemStateChanged(evt);
            }
        });

        loopCheckBox3.setText(bundle.getString("LOOP")); // NOI18N
        loopCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loopCheckBox3ItemStateChanged(evt);
            }
        });
        loopCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loopCheckBox3ActionPerformed(evt);
            }
        });

        loopCheckBox4.setText(bundle.getString("LOOP")); // NOI18N
        loopCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loopCheckBox4ItemStateChanged(evt);
            }
        });

        loopCheckBox5.setText(bundle.getString("LOOP")); // NOI18N
        loopCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loopCheckBox5ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout SecuencesPanelLayout = new javax.swing.GroupLayout(SecuencesPanel);
        SecuencesPanel.setLayout(SecuencesPanelLayout);
        SecuencesPanelLayout.setHorizontalGroup(
            SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SecuencesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addComponent(sec1label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recordButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton1)
                        .addGap(59, 59, 59))
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sec2label)
                            .addComponent(loopCheckBox2))
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(recordButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton2)
                                .addGap(8, 8, 8)
                                .addComponent(clearButton2)
                                .addGap(59, 59, 59))
                            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bigpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addComponent(loopCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bigpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(SecuencesTitle))
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sec3label)
                            .addComponent(loopCheckBox3))
                        .addGap(9, 9, 9)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bigpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                                .addComponent(recordButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton3)
                                .addGap(8, 8, 8)
                                .addComponent(clearButton3))))
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sec4label)
                            .addComponent(loopCheckBox4))
                        .addGap(9, 9, 9)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bigpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                                .addComponent(recordButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(playButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearButton4))))
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sec5label)
                            .addComponent(loopCheckBox5))
                        .addGap(9, 9, 9)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bigpanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                                .addComponent(recordButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton5)
                                .addGap(8, 8, 8)
                                .addComponent(clearButton5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SecuencesPanelLayout.setVerticalGroup(
            SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecuencesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SecuencesTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recordButton1)
                    .addComponent(clearButton1)
                    .addComponent(sec1label)
                    .addComponent(playButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bigpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loopCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recordButton2)
                    .addComponent(clearButton2)
                    .addComponent(sec2label)
                    .addComponent(playButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addComponent(bigpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recordButton3)
                            .addComponent(clearButton3)
                            .addComponent(sec3label)
                            .addComponent(playButton3)))
                    .addComponent(loopCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addComponent(bigpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recordButton4)
                            .addComponent(clearButton4)
                            .addComponent(sec4label)
                            .addComponent(playButton4)))
                    .addComponent(loopCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SecuencesPanelLayout.createSequentialGroup()
                        .addComponent(bigpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recordButton5)
                            .addComponent(clearButton5)
                            .addComponent(sec5label)
                            .addComponent(playButton5)))
                    .addComponent(loopCheckBox4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SecuencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bigpanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loopCheckBox5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        colors_secuencesPanel.addTab(bundle.getString("SECUENCES"), SecuencesPanel); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel17.setText(bundle.getString("LED CHANNELS")); // NOI18N

        LedC1.setText(bundle.getString("CHANNEL 1")); // NOI18N

        LedC2.setText(bundle.getString("CHANNEL 2")); // NOI18N

        LedC3.setText(bundle.getString("CHANNEL 3")); // NOI18N
        LedC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LedC3ActionPerformed(evt);
            }
        });

        LedC4.setText(bundle.getString("CHANNEL 4")); // NOI18N

        testMode.setText(bundle.getString("TEST MODE")); // NOI18N
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
                    .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(picker, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorTabLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(colorTabLayout.createSequentialGroup()
                                    .addGap(133, 133, 133)
                                    .addComponent(jLabel17))
                                .addGroup(colorTabLayout.createSequentialGroup()
                                    .addComponent(LedC1)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC2)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC3)
                                    .addGap(18, 18, 18)
                                    .addComponent(LedC4)))))
                    .addComponent(testMode)
                    .addGroup(colorTabLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ledModeLabel)
                            .addGroup(colorTabLayout.createSequentialGroup()
                                .addComponent(fadeRadioButton)
                                .addGap(35, 35, 35)
                                .addComponent(normalRadioButton)))))
                .addGap(10, 10, 10)
                .addComponent(colors_secuencesPanel)
                .addContainerGap())
        );
        colorTabLayout.setVerticalGroup(
            colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorTabLayout.createSequentialGroup()
                .addComponent(picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LedC1)
                    .addComponent(LedC2)
                    .addComponent(LedC3)
                    .addComponent(LedC4))
                .addGap(70, 70, 70)
                .addComponent(ledModeLabel)
                .addGap(18, 18, 18)
                .addGroup(colorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(normalRadioButton)
                    .addComponent(fadeRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(testMode))
            .addGroup(colorTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(colors_secuencesPanel)
                .addContainerGap())
        );

        tabPanel.addTab("Color", colorTab);

        fansTitleLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        fansTitleLabel.setText(bundle.getString("FANS")); // NOI18N

        pumpsTitleLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        pumpsTitleLabel.setText(bundle.getString("PUMPS")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText(bundle.getString("TEMPERATURES")); // NOI18N

        Refresh2.setText(bundle.getString("REFRESH")); // NOI18N
        Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh2ActionPerformed(evt);
            }
        });

        jLabel18.setText(bundle.getString("REFRESH EACH")); // NOI18N

        jLabel19.setText(bundle.getString("SECONDS")); // NOI18N

        RefreshCheckBox.setText(bundle.getString("REFRESH AUTOMATICALLY")); // NOI18N
        RefreshCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshCheckBoxActionPerformed(evt);
            }
        });

        refreshSecondsSpinner.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(5L), Long.valueOf(2L), null, Long.valueOf(1L)));
        refreshSecondsSpinner.setToolTipText("");
        refreshSecondsSpinner.setValue(refreshTime());
        refreshSecondsSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                refreshSecondsSpinnerStateChanged(evt);
            }
        });

        fanlabel1.setText(bundle.getString("CHANNEL 1")); // NOI18N

        fanslider1.setPaintTicks(true);

        indicatorfan1.setEditable(false);
        indicatorfan1.setText("0000");

        fanrpmlabel1.setText("RPM");

        fanrpmlabel2.setText("RPM");

        indicatorfan2.setEditable(false);
        indicatorfan2.setText("0000");

        fanslider2.setPaintTicks(true);

        fanlabel2.setText(bundle.getString("CHANNEL 2")); // NOI18N

        fanrpmlabel3.setText("RPM");

        indicatorfan3.setEditable(false);
        indicatorfan3.setText("0000");

        fanslider3.setPaintTicks(true);

        fanlabel3.setText(bundle.getString("CHANNEL 3")); // NOI18N

        fanrpmlabel4.setText("RPM");

        indicatorfan4.setEditable(false);
        indicatorfan4.setText("0000");

        fanslider4.setPaintTicks(true);

        fanlabel4.setText(bundle.getString("CHANNEL 4")); // NOI18N

        fanrpmlabel5.setText("RPM");

        indicatorfan5.setEditable(false);
        indicatorfan5.setText("0000");

        fanslider5.setPaintTicks(true);

        fanlabel5.setText(bundle.getString("CHANNEL 5")); // NOI18N

        fanrpmlabel6.setText("RPM");

        indicatorfan6.setEditable(false);
        indicatorfan6.setText("0000");

        fanslider6.setPaintTicks(true);

        fanlabel6.setText(bundle.getString("CHANNEL 6")); // NOI18N

        fanrpmlabel7.setText("RPM");

        indicatorfan7.setEditable(false);
        indicatorfan7.setText("0000");

        fanslider7.setPaintTicks(true);

        fanlabel7.setText(bundle.getString("CHANNEL 7")); // NOI18N

        fanrpmlabel8.setText("RPM");

        indicatorfan8.setEditable(false);
        indicatorfan8.setText("0000");

        fanslider8.setPaintTicks(true);

        fanlabel8.setText(bundle.getString("CHANNEL 8")); // NOI18N

        fanrpmlabel9.setText("RPM");

        indicatorfan9.setEditable(false);
        indicatorfan9.setText("0000");

        fanslider9.setPaintTicks(true);

        fanlabel9.setText(bundle.getString("CHANNEL 9")); // NOI18N

        fanrpmlabel10.setText("RPM");

        indicatorfan10.setEditable(false);
        indicatorfan10.setText("0000");

        fanslider10.setPaintTicks(true);

        fanlabel10.setText(bundle.getString("CHANNEL 10")); // NOI18N

        fanlabel11.setText(bundle.getString("CHANNEL 11")); // NOI18N

        fanslider11.setPaintTicks(true);

        indicatorfan11.setEditable(false);
        indicatorfan11.setText("0000");

        fanrpmlabel11.setText("RPM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fanlabel2)
                    .addComponent(fanlabel3)
                    .addComponent(fanlabel4)
                    .addComponent(fanlabel5)
                    .addComponent(fanlabel6)
                    .addComponent(fanlabel7)
                    .addComponent(fanlabel8)
                    .addComponent(fanlabel9)
                    .addComponent(fanlabel10)
                    .addComponent(fanlabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel8))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel9))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fanslider11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorfan11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanrpmlabel11))
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(fanlabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fanlabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fanslider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indicatorfan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fanlabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fanslider10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorfan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fanrpmlabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fanrpmlabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(fanlabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanslider11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(indicatorfan11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel3);

        pumplabel1.setText(bundle.getString("CHANNEL 1")); // NOI18N

        pumpslider1.setPaintTicks(true);

        indicatorpump1.setEditable(false);
        indicatorpump1.setText("0000");

        pumprpmlabel1.setText("RPM");

        pumprpmlabel2.setText("RPM");

        indicatorpump2.setEditable(false);
        indicatorpump2.setText("0000");

        pumpslider2.setPaintTicks(true);

        pumplabel2.setText(bundle.getString("CHANNEL 2")); // NOI18N

        pumprpmlabel3.setText("RPM");

        indicatorpump3.setEditable(false);
        indicatorpump3.setText("0000");

        pumpslider3.setPaintTicks(true);

        pumplabel3.setText(bundle.getString("CHANNEL 3")); // NOI18N

        pumprpmlabel4.setText("RPM");

        indicatorpump4.setEditable(false);
        indicatorpump4.setText("0000");

        pumpslider4.setPaintTicks(true);

        pumplabel4.setText(bundle.getString("CHANNEL 4")); // NOI18N

        pumprpmlabel5.setText("RPM");

        indicatorpump5.setEditable(false);
        indicatorpump5.setText("0000");

        pumpslider5.setPaintTicks(true);

        pumplabel5.setText(bundle.getString("CHANNEL 5")); // NOI18N

        pumprpmlabel6.setText("RPM");

        indicatorpump6.setEditable(false);
        indicatorpump6.setText("0000");

        pumpslider6.setPaintTicks(true);

        pumplabel6.setText(bundle.getString("CHANNEL 6")); // NOI18N

        pumprpmlabel7.setText("RPM");

        indicatorpump7.setEditable(false);
        indicatorpump7.setText("0000");

        pumpslider7.setPaintTicks(true);

        pumplabel7.setText(bundle.getString("CHANNEL 7")); // NOI18N

        pumprpmlabel8.setText("RPM");

        indicatorpump8.setEditable(false);
        indicatorpump8.setText("0000");

        pumpslider8.setPaintTicks(true);

        pumplabel8.setText(bundle.getString("CHANNEL 8")); // NOI18N

        pumprpmlabel9.setText("RPM");

        indicatorpump9.setEditable(false);
        indicatorpump9.setText("0000");

        pumpslider9.setPaintTicks(true);

        pumplabel9.setText(bundle.getString("CHANNEL 9")); // NOI18N

        pumprpmlabel10.setText("RPM");

        indicatorpump10.setEditable(false);
        indicatorpump10.setText("0000");

        pumpslider10.setPaintTicks(true);

        pumplabel10.setText(bundle.getString("CHANNEL 10")); // NOI18N

        pumplabel11.setText(bundle.getString("CHANNEL 11")); // NOI18N

        pumpslider11.setPaintTicks(true);

        indicatorpump11.setEditable(false);
        indicatorpump11.setText("0000");

        pumprpmlabel11.setText("RPM");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pumplabel2)
                    .addComponent(pumplabel3)
                    .addComponent(pumplabel4)
                    .addComponent(pumplabel5)
                    .addComponent(pumplabel6)
                    .addComponent(pumplabel7)
                    .addComponent(pumplabel8)
                    .addComponent(pumplabel9)
                    .addComponent(pumplabel10)
                    .addComponent(pumplabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel3))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel5))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel7))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel8))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel10))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pumpslider11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(indicatorpump11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pumprpmlabel11))
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pumplabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pumplabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pumpslider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indicatorpump1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pumplabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpslider11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indicatorpump11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumprpmlabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout graficaLayout = new javax.swing.GroupLayout(grafica);
        grafica.setLayout(graficaLayout);
        graficaLayout.setHorizontalGroup(
            graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graficaLayout.setVerticalGroup(
            graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        temp1.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp1.setText(bundle.getString("TEMP 1")); // NOI18N

        degree1.setText("ºC");

        Temp1.setEditable(false);
        Temp1.setText("0");

        javax.swing.GroupLayout t1Layout = new javax.swing.GroupLayout(t1);
        t1.setLayout(t1Layout);
        t1Layout.setHorizontalGroup(
            t1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(t1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(temp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Temp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree1))
        );
        t1Layout.setVerticalGroup(
            t1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree1)))
        );

        degree2.setText("ºC");

        Temp2.setEditable(false);
        Temp2.setText("0");

        temp2.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp2.setText(bundle.getString("TEMP 2")); // NOI18N

        javax.swing.GroupLayout t2Layout = new javax.swing.GroupLayout(t2);
        t2.setLayout(t2Layout);
        t2Layout.setHorizontalGroup(
            t2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t2Layout.createSequentialGroup()
                .addGroup(t2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Temp2)
                    .addComponent(temp2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree2))
        );
        t2Layout.setVerticalGroup(
            t2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree2)))
        );

        temp3.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp3.setText(bundle.getString("TEMP 3")); // NOI18N

        degree3.setText("ºC");

        Temp3.setEditable(false);
        Temp3.setText("0");

        javax.swing.GroupLayout t3Layout = new javax.swing.GroupLayout(t3);
        t3.setLayout(t3Layout);
        t3Layout.setHorizontalGroup(
            t3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(temp3)
            .addGroup(t3Layout.createSequentialGroup()
                .addComponent(Temp3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree3))
        );
        t3Layout.setVerticalGroup(
            t3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(t3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(t3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Temp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(degree3))
                    .addGroup(t3Layout.createSequentialGroup()
                        .addComponent(temp3)
                        .addGap(26, 26, 26))))
        );

        temp4.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp4.setText(bundle.getString("TEMP 4")); // NOI18N

        Temp4.setEditable(false);
        Temp4.setText("0");

        degree4.setText("ºC");

        javax.swing.GroupLayout t4Layout = new javax.swing.GroupLayout(t4);
        t4.setLayout(t4Layout);
        t4Layout.setHorizontalGroup(
            t4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t4Layout.createSequentialGroup()
                .addComponent(Temp4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree4))
            .addComponent(temp4)
        );
        t4Layout.setVerticalGroup(
            t4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree4)))
        );

        temp5.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp5.setText(bundle.getString("TEMP 5")); // NOI18N

        Temp5.setEditable(false);
        Temp5.setText("0");

        degree5.setText("ºC");

        javax.swing.GroupLayout t5Layout = new javax.swing.GroupLayout(t5);
        t5.setLayout(t5Layout);
        t5Layout.setHorizontalGroup(
            t5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t5Layout.createSequentialGroup()
                .addComponent(temp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(t5Layout.createSequentialGroup()
                .addGroup(t5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Temp5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(t5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(degree5)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        t5Layout.setVerticalGroup(
            t5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t5Layout.createSequentialGroup()
                .addGroup(t5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(t5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Temp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(degree5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t5Layout.createSequentialGroup()
                        .addComponent(temp5)
                        .addGap(26, 26, 26)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Temp6.setEditable(false);
        Temp6.setText("0");

        temp6.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp6.setText(bundle.getString("TEMP 6")); // NOI18N

        degree6.setText("ºC");

        javax.swing.GroupLayout t6Layout = new javax.swing.GroupLayout(t6);
        t6.setLayout(t6Layout);
        t6Layout.setHorizontalGroup(
            t6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t6Layout.createSequentialGroup()
                .addGroup(t6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(temp6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Temp6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree6)
                .addContainerGap())
        );
        t6Layout.setVerticalGroup(
            t6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree6)))
        );

        temp7.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp7.setText(bundle.getString("TEMP 7")); // NOI18N

        Temp7.setEditable(false);
        Temp7.setText("0");

        degree7.setText("ºC");

        javax.swing.GroupLayout t7Layout = new javax.swing.GroupLayout(t7);
        t7.setLayout(t7Layout);
        t7Layout.setHorizontalGroup(
            t7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t7Layout.createSequentialGroup()
                .addGroup(t7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(temp7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Temp7, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        t7Layout.setVerticalGroup(
            t7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree7)))
        );

        Temp8.setEditable(false);
        Temp8.setText("0");

        degree8.setText("ºC");

        temp8.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp8.setText(bundle.getString("TEMP 8")); // NOI18N

        javax.swing.GroupLayout t8Layout = new javax.swing.GroupLayout(t8);
        t8.setLayout(t8Layout);
        t8Layout.setHorizontalGroup(
            t8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(temp8)
            .addGroup(t8Layout.createSequentialGroup()
                .addComponent(Temp8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree8))
        );
        t8Layout.setVerticalGroup(
            t8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(t8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(t8Layout.createSequentialGroup()
                        .addComponent(temp8)
                        .addGap(26, 26, 26))
                    .addGroup(t8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Temp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(degree8))))
        );

        Temp9.setEditable(false);
        Temp9.setText("0");

        degree9.setText("ºC");

        temp9.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp9.setText(bundle.getString("TEMP 9")); // NOI18N

        javax.swing.GroupLayout t9Layout = new javax.swing.GroupLayout(t9);
        t9.setLayout(t9Layout);
        t9Layout.setHorizontalGroup(
            t9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Temp9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(t9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(degree9))
            .addComponent(temp9)
        );
        t9Layout.setVerticalGroup(
            t9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree9)))
        );

        temp10.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp10.setText(bundle.getString("TEMP 10")); // NOI18N

        Temp10.setEditable(false);
        Temp10.setText("0");

        degree10.setText("ºC");

        javax.swing.GroupLayout t10Layout = new javax.swing.GroupLayout(t10);
        t10.setLayout(t10Layout);
        t10Layout.setHorizontalGroup(
            t10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t10Layout.createSequentialGroup()
                .addGroup(t10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Temp10)
                    .addComponent(temp10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree10))
        );
        t10Layout.setVerticalGroup(
            t10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree10)))
        );

        Temp11.setEditable(false);
        Temp11.setText("0");

        degree11.setText("ºC");

        temp11.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        temp11.setText(bundle.getString("TEMP 11")); // NOI18N

        javax.swing.GroupLayout t11Layout = new javax.swing.GroupLayout(t11);
        t11.setLayout(t11Layout);
        t11Layout.setHorizontalGroup(
            t11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t11Layout.createSequentialGroup()
                .addComponent(Temp11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degree11))
            .addComponent(temp11)
        );
        t11Layout.setVerticalGroup(
            t11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(temp11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Temp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree11)))
        );

        javax.swing.GroupLayout FanPumpPanelLayout = new javax.swing.GroupLayout(FanPumpPanel);
        FanPumpPanel.setLayout(FanPumpPanelLayout);
        FanPumpPanelLayout.setHorizontalGroup(
            FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fansTitleLabel)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane3))
                    .addComponent(pumpsTitleLabel))
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(grafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RefreshCheckBox)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(Refresh2)
                                .addGap(31, 31, 31)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refreshSecondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        FanPumpPanelLayout.setVerticalGroup(
            FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FanPumpPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pumpsTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11)
                                .addComponent(Refresh2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RefreshCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(FanPumpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(refreshSecondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(FanPumpPanelLayout.createSequentialGroup()
                                .addComponent(fansTitleLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        tabPanel.addTab("Fans & Pumps", FanPumpPanel);

        jButton1.setText(bundle.getString("START AMBILIGHT!")); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(610, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(444, Short.MAX_VALUE))
        );

        tabPanel.addTab("Ambilight", jPanel2);

        refreshPortsButton.setText(bundle.getString("REFRESH")); // NOI18N
        refreshPortsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPortsButtonActionPerformed(evt);
            }
        });

        PortsBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PortsBox.setToolTipText(bundle.getString("AVALIABLE ARDUINO PORTS")); // NOI18N

        connectButton.setText(bundle.getString("CONNECT")); // NOI18N
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        notConnectedLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        notConnectedLabel.setForeground(new java.awt.Color(225, 2, 27));
        notConnectedLabel.setText(bundle.getString("NOT CONNECTED!!")); // NOI18N

        notificationsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        notificationsLabel.setText("jLabel20");
        notificationsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jPanel1.setPreferredSize(new java.awt.Dimension(146, 14));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(notificationsBar, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(notificationsBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenu1.setText(bundle.getString("FILE")); // NOI18N

        settings.setText(bundle.getString("SETTINGS")); // NOI18N
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        jMenu1.add(settings);

        menubar.add(jMenu1);

        jMenu3.setText(bundle.getString("ABOUT")); // NOI18N

        jMenuItem1.setText(bundle.getString("CHECK FOR UPDATES")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText(bundle.getString("ABOUT")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        menubar.add(jMenu3);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPanel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshPortsButton)
                        .addGap(196, 196, 196)
                        .addComponent(notConnectedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connectButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(notificationsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notificationsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshPortsButton)
                    .addComponent(connectButton)
                    .addComponent(notConnectedLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void getButtonColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor6ActionPerformed
        int color6R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color6R"));
        int color6G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color6G"));
        int color6B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color6B"));
        Color color6 = new Color(color6R,color6G,color6B);
        picker.setColor(color6);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor6ActionPerformed

    private void setButtonColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor6ActionPerformed
        Color color6 = picker.getColor();
        panelColor6.setBackground(color6);
        com.alphamods.controlcenter.utils.config.setValue("color6R", Integer.toString(color6.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color6G", Integer.toString(color6.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color6B", Integer.toString(color6.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor6ActionPerformed

    private void getButtonColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor1ActionPerformed
        int color1R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color1R"));
        int color1G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color1G"));
        int color1B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color1B"));
        Color color1 = new Color(color1R,color1G,color1B);
        picker.setColor(color1);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor1ActionPerformed

    private void setButtonColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor1ActionPerformed
        Color color1 = picker.getColor();
        panelColor1.setBackground(color1);
        com.alphamods.controlcenter.utils.config.setValue("color1R", Integer.toString(color1.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color1G", Integer.toString(color1.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color1B", Integer.toString(color1.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor1ActionPerformed

    private void setButtonColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor2ActionPerformed
        Color color2 = picker.getColor();
        panelColor2.setBackground(color2);
        com.alphamods.controlcenter.utils.config.setValue("color2R", Integer.toString(color2.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color2G", Integer.toString(color2.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color2B", Integer.toString(color2.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor2ActionPerformed

    private void getButtonColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor2ActionPerformed
        int color2R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color2R"));
        int color2G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color2G"));
        int color2B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color2B"));
        Color color2 = new Color(color2R,color2G,color2B);
        picker.setColor(color2);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor2ActionPerformed

    private void getButtonColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor7ActionPerformed
        int color7R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color7R"));
        int color7G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color7G"));
        int color7B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color7B"));
        Color color7 = new Color(color7R,color7G,color7B);
        picker.setColor(color7);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor7ActionPerformed

    private void setButtonColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor7ActionPerformed
        Color color7 = picker.getColor();
        panelColor7.setBackground(color7);
        com.alphamods.controlcenter.utils.config.setValue("color7R", Integer.toString(color7.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color7G", Integer.toString(color7.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color7B", Integer.toString(color7.getBlue()));

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
        com.alphamods.controlcenter.utils.config.setValue("color8R", Integer.toString(color8.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color8G", Integer.toString(color8.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color8B", Integer.toString(color8.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor8ActionPerformed

    private void getButtonColor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor8ActionPerformed
        int color8R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color8R"));
        int color8G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color8G"));
        int color8B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color8B"));
        Color color8 = new Color(color8R,color8G,color8B);
        picker.setColor(color8);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor8ActionPerformed

    private void getButtonColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor3ActionPerformed
        int color3R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color3R"));
        int color3G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color3G"));
        int color3B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color3B"));
        Color color3 = new Color(color3R,color3G,color3B);
        picker.setColor(color3);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor3ActionPerformed

    private void setButtonColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor3ActionPerformed
        Color color3 = picker.getColor();
        panelColor3.setBackground(color3);
        com.alphamods.controlcenter.utils.config.setValue("color3R", Integer.toString(color3.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color3G", Integer.toString(color3.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color3B", Integer.toString(color3.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor3ActionPerformed

    private void getButtonColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor9ActionPerformed
        int color9R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color9R"));
        int color9G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color9G"));
        int color9B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color9B"));
        Color color9 = new Color(color9R,color9G,color9B);
        picker.setColor(color9);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor9ActionPerformed

    private void setButtonColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor9ActionPerformed
        Color color9 = picker.getColor();
        panelColor9.setBackground(color9);
        com.alphamods.controlcenter.utils.config.setValue("color9R", Integer.toString(color9.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color9G", Integer.toString(color9.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color9B", Integer.toString(color9.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor9ActionPerformed

    private void getButtonColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor4ActionPerformed
        int color4R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color4R"));
        int color4G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color4G"));
        int color4B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color4B"));
        Color color4 = new Color(color4R,color4G,color4B);
        picker.setColor(color4);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor4ActionPerformed

    private void setButtonColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor4ActionPerformed
        Color color4 = picker.getColor();
        panelColor4.setBackground(color4);
        com.alphamods.controlcenter.utils.config.setValue("color4R", Integer.toString(color4.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color4G", Integer.toString(color4.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color4B", Integer.toString(color4.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor4ActionPerformed

    private void getButtonColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor5ActionPerformed
        int color5R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color5R"));
        int color5G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color5G"));
        int color5B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color5B"));
        Color color5 = new Color(color5R,color5G,color5B);
        picker.setColor(color5);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor5ActionPerformed

    private void setButtonColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor5ActionPerformed
        Color color5 = picker.getColor();
        panelColor5.setBackground(color5);
        com.alphamods.controlcenter.utils.config.setValue("color5R", Integer.toString(color5.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color5G", Integer.toString(color5.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color5B", Integer.toString(color5.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor5ActionPerformed

    private void setButtonColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor10ActionPerformed
        Color color10 = picker.getColor();
        panelColor10.setBackground(color10);
        com.alphamods.controlcenter.utils.config.setValue("color10R", Integer.toString(color10.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color10G", Integer.toString(color10.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color10B", Integer.toString(color10.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor10ActionPerformed

    private void getButtonColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor10ActionPerformed
        int color10R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color10R"));
        int color10G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color10G"));
        int color10B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color10B"));
        Color color10 = new Color(color10R,color10G,color10B);
        picker.setColor(color10);

        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor10ActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
methods.cleanFavourites();

loadpreviews();// TODO add your handling code here:
    }//GEN-LAST:event_cleanButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
if (methods.isConnected()) {
    
                methods.disconnect();
                notConnectedLabel.setVisible(true);
                connectButton.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("CONNECT"));
                picker.setEnabled(false);
                fadeRadioButton.setEnabled(false);
                normalRadioButton.setEnabled(false);
                recordButton1.setEnabled(false);
                playButton1.setEnabled(false);
                Refresh2.setEnabled(false);
                clearButton1.setEnabled(false);
            

        } else {

            try {
                if (PortsBox.getSelectedItem()==null){
                    
                }else{
                    methods.connect(evento, PortsBox);
                connectButton.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("DISCONNECT"));
                fadeRadioButton.setEnabled(true);
                normalRadioButton.setEnabled(true);
                recordButton1.setEnabled(true);
                playButton1.setEnabled(true);
                picker.setEnabled(true);
                notConnectedLabel.setVisible(false);
                Refresh2.setEnabled(true);
                clearButton1.setEnabled(true);
                loadsecpreviews();
                write();
                }
                

            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_connectButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        savepercentajes();
        if (methods.isConnected()){
        }
        
        
        if (methods.isConnected()){
            com.alphamods.controlcenter.utils.config.setValue("Port", PortsBox.getSelectedItem().toString());
        }

        com.alphamods.controlcenter.utils.config.setValue("refreshTime", refreshSecondsSpinner.getValue().toString());
        if (RefreshCheckBox.isSelected()){
            com.alphamods.controlcenter.utils.config.setValue("refreshMode", "1");
        }
        else if (!RefreshCheckBox.isSelected()){
            com.alphamods.controlcenter.utils.config.setValue("refreshMode", "0");
        }
        
        com.alphamods.controlcenter.utils.config.setValue("channel1R", methods.getChannel(1)[0]);
        com.alphamods.controlcenter.utils.config.setValue("channel1G", methods.getChannel(1)[1]);
        com.alphamods.controlcenter.utils.config.setValue("channel1B", methods.getChannel(1)[2]);
        
        com.alphamods.controlcenter.utils.config.setValue("channel2R", methods.getChannel(2)[0]);
        com.alphamods.controlcenter.utils.config.setValue("channel2G", methods.getChannel(2)[1]);
        com.alphamods.controlcenter.utils.config.setValue("channel2B", methods.getChannel(2)[2]);
        
        com.alphamods.controlcenter.utils.config.setValue("channel3R", methods.getChannel(3)[0]);
        com.alphamods.controlcenter.utils.config.setValue("channel3G", methods.getChannel(3)[1]);
        com.alphamods.controlcenter.utils.config.setValue("channel3B", methods.getChannel(3)[2]);
        
        com.alphamods.controlcenter.utils.config.setValue("channel4R", methods.getChannel(4)[0]);
        com.alphamods.controlcenter.utils.config.setValue("channel4G", methods.getChannel(4)[1]);
        com.alphamods.controlcenter.utils.config.setValue("channel4B", methods.getChannel(4)[2]);
        
        config.setValue("colorR", Integer.toString(picker.getColor().getRed()));
        config.setValue("colorG", Integer.toString(picker.getColor().getGreen()));
        config.setValue("colorB", Integer.toString(picker.getColor().getBlue()));
        if(methods.isConnected()){
            config.setValue("port", PortsBox.getSelectedItem().toString());
        }
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void refreshPortsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPortsButtonActionPerformed
   PortsBox.removeAllItems();
        methods.getArduino().getSerialPorts().forEach(i -> PortsBox.addItem(i));   
        if(PortsBox.getSelectedItem()==null){
            connectButton.setEnabled(false);
        }else{
            connectButton.setEnabled(true);            
        }// TODO add your handling code here:
    }//GEN-LAST:event_refreshPortsButtonActionPerformed

    private void pickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pickerPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_pickerPropertyChange

    private void LedC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LedC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LedC3ActionPerformed

    private void getButtonColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor11ActionPerformed
        int color11R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color11R"));
        int color11G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color11G"));
        int color11B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color11B"));
        Color color11 = new Color(color11R,color11G,color11B);
        picker.setColor(color11);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor11ActionPerformed

    private void setButtonColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor11ActionPerformed
        Color color11 = picker.getColor();
        panelColor11.setBackground(color11);
        com.alphamods.controlcenter.utils.config.setValue("color11R", Integer.toString(color11.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color11G", Integer.toString(color11.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color11B", Integer.toString(color11.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor11ActionPerformed

    private void getButtonColor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getButtonColor12ActionPerformed
        int color12R = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color12R"));
        int color12G = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color12G"));
        int color12B = Integer.parseInt(com.alphamods.controlcenter.utils.config.getValue("color12B"));
        Color color12 = new Color(color12R,color12G,color12B);
        picker.setColor(color12);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_getButtonColor12ActionPerformed

    private void setButtonColor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonColor12ActionPerformed
        Color color12 = picker.getColor();
        panelColor12.setBackground(color12);
        com.alphamods.controlcenter.utils.config.setValue("color12R", Integer.toString(color12.getRed()));
        com.alphamods.controlcenter.utils.config.setValue("color12G", Integer.toString(color12.getGreen()));
        com.alphamods.controlcenter.utils.config.setValue("color12B", Integer.toString(color12.getBlue()));

        // TODO add your handling code here:
    }//GEN-LAST:event_setButtonColor12ActionPerformed

    
    
    private void testModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_testModeItemStateChanged

        setIcons();
        testmode = testMode.isSelected();
if (testMode.isSelected()){
    
                connectButton.setText("testing");
                fadeRadioButton.setEnabled(true);
                normalRadioButton.setEnabled(true);
                picker.setEnabled(true);
                notConnectedLabel.setVisible(false);
                Refresh2.setEnabled(true);
                clearButton1.setEnabled(true);
                recordButton1.setEnabled(true);
                playButton1.setEnabled(true);
}else {
                notConnectedLabel.setVisible(true);
                connectButton.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("CONNECT"));
                picker.setEnabled(false);
                fadeRadioButton.setEnabled(false);
                normalRadioButton.setEnabled(false);
                recordButton1.setEnabled(false);
                playButton1.setEnabled(false);
                Refresh2.setEnabled(false); 
                
}
    }//GEN-LAST:event_testModeItemStateChanged

    private void recordButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recordButton1ItemStateChanged
    if (recordButton1.isSelected()){
        s1record = new com.alphamods.controlcenter.utils.secuences();
        s1record.record(picker, 1);
        recordButton1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("STOP"));  
    }
    else{
        s1record.recorderStop(bigpanel1); 
        recordButton1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RECORD"));
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_recordButton1ItemStateChanged

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        secuences.clean(1);
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void recordButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recordButton3ItemStateChanged
        if (recordButton3.isSelected()){
        s3record = new com.alphamods.controlcenter.utils.secuences();
        s3record.record(picker, 3);
        recordButton3.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("STOP"));  
    }
    else{
        s3record.recorderStop(bigpanel3); 
        recordButton3.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RECORD"));
    }// TODO add your handling code here:
    }//GEN-LAST:event_recordButton3ItemStateChanged

    private void clearButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton3ActionPerformed
                secuences.clean(3);
// TODO add your handling code here:
    }//GEN-LAST:event_clearButton3ActionPerformed

    private void recordButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recordButton4ItemStateChanged
      if (recordButton4.isSelected()){
        s4record = new com.alphamods.controlcenter.utils.secuences();
        s4record.record(picker, 4);
        recordButton4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("STOP"));  
    }
    else{
        s4record.recorderStop(bigpanel4); 
        recordButton4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RECORD"));
    }  // TODO add your handling code here:
    }//GEN-LAST:event_recordButton4ItemStateChanged

    private void clearButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton4ActionPerformed
              secuences.clean(4);
  // TODO add your handling code here:
    }//GEN-LAST:event_clearButton4ActionPerformed

    private void recordButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recordButton5ItemStateChanged
      if (recordButton5.isSelected()){
        s5record = new com.alphamods.controlcenter.utils.secuences();
        s5record.record(picker, 5);
        recordButton5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("STOP"));  
    }
    else{
        s5record.recorderStop(bigpanel5); 
        recordButton5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RECORD"));
    }  // TODO add your handling code here:
    }//GEN-LAST:event_recordButton5ItemStateChanged

    private void clearButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton5ActionPerformed
               secuences.clean(5);
 // TODO add your handling code here:
    }//GEN-LAST:event_clearButton5ActionPerformed

    private void loopCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loopCheckBox1ItemStateChanged
if (loopCheckBox1.isSelected()){

    s1play.setLoop(true);
    

}else{
    
    s1play.setLoop(false);
    
}


// TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox1ItemStateChanged

    private void playButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playButton1ItemStateChanged
if (playButton1.isSelected()){
    s1play = new com.alphamods.controlcenter.utils.secuences();
    s1play.play(picker, 1, playButton1);
    if (loopCheckBox1.isSelected()){
        s1play.setLoop(loopCheckBox1.isSelected());
    }
}else {
    s1play.playerStop();
    
}// TODO add your handling code here:
    }//GEN-LAST:event_playButton1ItemStateChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
settings settings = new settings(this, true);
settings.setVisible(true);
settings.requestFocusInWindow();
// TODO add your handling code here:
    }//GEN-LAST:event_settingsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
   
        /*    try {
        String fpathunlockb = path +"alphalight.exe";
        String[] args = {"cmd","/c","start", "tt", fpathunlockb};
        Runtime rt = Runtime.getRuntime();
        ProcessBuilder pb = new ProcessBuilder(args);
        Process pr = pb.start();
        
        
        // TODO add your handling code here:
        } catch (IOException ex) {
        Logger.getLogger(UpdaterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
        */
         try {
        Runtime.getRuntime().exec("java -Xmx256m -jar ./ambilight.jar");
    } catch (IOException ex) {
        Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton2ActionPerformed
               secuences.clean(2);
 // TODO add your handling code here:
    }//GEN-LAST:event_clearButton2ActionPerformed

    private void recordButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recordButton2ItemStateChanged
       if (recordButton2.isSelected()){
        s2record = new com.alphamods.controlcenter.utils.secuences();
        s2record.record(picker, 2);
        recordButton2.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("STOP"));  
    }
    else{
        s2record.recorderStop(bigpanel2); 
        recordButton2.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("RECORD"));
    } // TODO add your handling code here:
    }//GEN-LAST:event_recordButton2ItemStateChanged

    private void playButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playButton2ItemStateChanged
       if (playButton2.isSelected()){
    s2play = new com.alphamods.controlcenter.utils.secuences();
    s2play.play(picker, 2, playButton2);
    if (loopCheckBox2.isSelected()){
        s2play.setLoop(loopCheckBox2.isSelected());
    }
}else {
    s2play.playerStop();
    
} // TODO add your handling code here:
    }//GEN-LAST:event_playButton2ItemStateChanged

    private void playButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playButton3ItemStateChanged
       if (playButton3.isSelected()){
    s3play = new com.alphamods.controlcenter.utils.secuences();
    s3play.play(picker, 3, playButton3);
    if (loopCheckBox3.isSelected()){
        s3play.setLoop(loopCheckBox3.isSelected());
    }
}else {
    s3play.playerStop();
    
} // TODO add your handling code here:
    }//GEN-LAST:event_playButton3ItemStateChanged

    private void playButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playButton4ItemStateChanged
     if (playButton4.isSelected()){
    s4play = new com.alphamods.controlcenter.utils.secuences();
    s4play.play(picker, 4, playButton4);
    if (loopCheckBox4.isSelected()){
        s4play.setLoop(loopCheckBox4.isSelected());
    }
}else {
    s4play.playerStop();
    
}   // TODO add your handling code here:
    }//GEN-LAST:event_playButton4ItemStateChanged

    private void playButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playButton5ItemStateChanged
    if (playButton5.isSelected()){
    s5play = new com.alphamods.controlcenter.utils.secuences();
    s5play.play(picker, 5, playButton5);
    if (loopCheckBox5.isSelected()){
        s5play.setLoop(loopCheckBox5.isSelected());
    }
}else {
    s5play.playerStop();
    
}    // TODO add your handling code here:
    }//GEN-LAST:event_playButton5ItemStateChanged

    private void loopCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loopCheckBox2ItemStateChanged
     if (loopCheckBox2.isSelected()){
    s2play.setLoop(true);
     }else{
    s2play.setLoop(false);
}   // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox2ItemStateChanged

    private void loopCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loopCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox1ActionPerformed

    private void loopCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loopCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox3ActionPerformed

    private void loopCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loopCheckBox3ItemStateChanged
if (loopCheckBox3.isSelected()){
    s3play.setLoop(true);
     }else{
    s3play.setLoop(false);
}         // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox3ItemStateChanged

    private void loopCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loopCheckBox4ItemStateChanged
if (loopCheckBox4.isSelected()){
    s4play.setLoop(true);
     }else{
    s4play.setLoop(false);
}         // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox4ItemStateChanged

    private void loopCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loopCheckBox5ItemStateChanged
if (loopCheckBox5.isSelected()){
    s5play.setLoop(true);
     }else{
    s5play.setLoop(false);
}         // TODO add your handling code here:
    }//GEN-LAST:event_loopCheckBox5ItemStateChanged

    private void normalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonActionPerformed

    private void normalRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_normalRadioButtonStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonStateChanged

    private void normalRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_normalRadioButtonItemStateChanged
        write();        // TODO add your handling code here:
    }//GEN-LAST:event_normalRadioButtonItemStateChanged

    private void fadeRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fadeRadioButtonStateChanged

    }//GEN-LAST:event_fadeRadioButtonStateChanged

    private void fadeRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fadeRadioButtonItemStateChanged
        write();        // TODO add your handling code here:
    }//GEN-LAST:event_fadeRadioButtonItemStateChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
this.checkUpdates();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
new About(this,true).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void refreshSecondsSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_refreshSecondsSpinnerStateChanged
        executor.shutdown();
        executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(refreshTemp, 0, Long.parseLong(refreshSecondsSpinner.getValue().toString()), TimeUnit.SECONDS);
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshSecondsSpinnerStateChanged

    private void RefreshCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshCheckBoxActionPerformed
        if (RefreshCheckBox.isSelected()){
            refreshSecondsSpinner.setEnabled(true);
            jLabel19.setEnabled(true);
            jLabel18.setEnabled(true);
            executor = Executors.newScheduledThreadPool(1);
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

    private void Refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh2ActionPerformed
        write();        // TODO add your handling code here:
    }//GEN-LAST:event_Refresh2ActionPerformed
private void fanslidersStateChanged(javax.swing.event.ChangeEvent evt) {                                         

int index = fansliders.lastIndexOf(evt.getSource());
indicatorfans.get(index).setText(Integer.toString(methods.calculaterpms(index, fansliders, "fan")));
write();

}

private void pumpslidersStateChanged(javax.swing.event.ChangeEvent evt) {                                         
int index = pumpsliders.lastIndexOf(evt.getSource());
indicatorpumps.get(index).setText(Integer.toString(methods.calculaterpms(index, pumpsliders, "pump")));
write();
}


    Runnable refreshTemp = this::write;
    
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
    private javax.swing.JLabel SecuencesTitle;
    private javax.swing.JTextField Temp1;
    private javax.swing.JTextField Temp10;
    private javax.swing.JTextField Temp11;
    private javax.swing.JTextField Temp2;
    private javax.swing.JTextField Temp3;
    private javax.swing.JTextField Temp4;
    private javax.swing.JTextField Temp5;
    private javax.swing.JTextField Temp6;
    private javax.swing.JTextField Temp7;
    private javax.swing.JTextField Temp8;
    private javax.swing.JTextField Temp9;
    private javax.swing.JPanel bigpanel1;
    private javax.swing.JPanel bigpanel2;
    private javax.swing.JPanel bigpanel3;
    private javax.swing.JPanel bigpanel4;
    private javax.swing.JPanel bigpanel5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cleanButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JButton clearButton2;
    private javax.swing.JButton clearButton3;
    private javax.swing.JButton clearButton4;
    private javax.swing.JButton clearButton5;
    private javax.swing.JPanel colorTab;
    private javax.swing.JTabbedPane colors_secuencesPanel;
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel degree1;
    private javax.swing.JLabel degree10;
    private javax.swing.JLabel degree11;
    private javax.swing.JLabel degree2;
    private javax.swing.JLabel degree3;
    private javax.swing.JLabel degree4;
    private javax.swing.JLabel degree5;
    private javax.swing.JLabel degree6;
    private javax.swing.JLabel degree7;
    private javax.swing.JLabel degree8;
    private javax.swing.JLabel degree9;
    private javax.swing.JRadioButton fadeRadioButton;
    private javax.swing.JLabel fanlabel1;
    private javax.swing.JLabel fanlabel10;
    private javax.swing.JLabel fanlabel11;
    private javax.swing.JLabel fanlabel2;
    private javax.swing.JLabel fanlabel3;
    private javax.swing.JLabel fanlabel4;
    private javax.swing.JLabel fanlabel5;
    private javax.swing.JLabel fanlabel6;
    private javax.swing.JLabel fanlabel7;
    private javax.swing.JLabel fanlabel8;
    private javax.swing.JLabel fanlabel9;
    private javax.swing.JLabel fanrpmlabel1;
    private javax.swing.JLabel fanrpmlabel10;
    private javax.swing.JLabel fanrpmlabel11;
    private javax.swing.JLabel fanrpmlabel2;
    private javax.swing.JLabel fanrpmlabel3;
    private javax.swing.JLabel fanrpmlabel4;
    private javax.swing.JLabel fanrpmlabel5;
    private javax.swing.JLabel fanrpmlabel6;
    private javax.swing.JLabel fanrpmlabel7;
    private javax.swing.JLabel fanrpmlabel8;
    private javax.swing.JLabel fanrpmlabel9;
    private javax.swing.JLabel fansTitleLabel;
    private javax.swing.JSlider fanslider1;
    private javax.swing.JSlider fanslider10;
    private javax.swing.JSlider fanslider11;
    private javax.swing.JSlider fanslider2;
    private javax.swing.JSlider fanslider3;
    private javax.swing.JSlider fanslider4;
    private javax.swing.JSlider fanslider5;
    private javax.swing.JSlider fanslider6;
    private javax.swing.JSlider fanslider7;
    private javax.swing.JSlider fanslider8;
    private javax.swing.JSlider fanslider9;
    private javax.swing.JPanel favColorsPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
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
    private javax.swing.JPanel grafica;
    private javax.swing.JTextField indicatorfan1;
    private javax.swing.JTextField indicatorfan10;
    private javax.swing.JTextField indicatorfan11;
    private javax.swing.JTextField indicatorfan2;
    private javax.swing.JTextField indicatorfan3;
    private javax.swing.JTextField indicatorfan4;
    private javax.swing.JTextField indicatorfan5;
    private javax.swing.JTextField indicatorfan6;
    private javax.swing.JTextField indicatorfan7;
    private javax.swing.JTextField indicatorfan8;
    private javax.swing.JTextField indicatorfan9;
    private javax.swing.JTextField indicatorpump1;
    private javax.swing.JTextField indicatorpump10;
    private javax.swing.JTextField indicatorpump11;
    private javax.swing.JTextField indicatorpump2;
    private javax.swing.JTextField indicatorpump3;
    private javax.swing.JTextField indicatorpump4;
    private javax.swing.JTextField indicatorpump5;
    private javax.swing.JTextField indicatorpump6;
    private javax.swing.JTextField indicatorpump7;
    private javax.swing.JTextField indicatorpump8;
    private javax.swing.JTextField indicatorpump9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JCheckBox loopCheckBox1;
    private javax.swing.JCheckBox loopCheckBox2;
    private javax.swing.JCheckBox loopCheckBox3;
    private javax.swing.JCheckBox loopCheckBox4;
    private javax.swing.JCheckBox loopCheckBox5;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JRadioButton normalRadioButton;
    private javax.swing.JLabel notConnectedLabel;
    private javax.swing.JProgressBar notificationsBar;
    private javax.swing.JLabel notificationsLabel;
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
    private javax.swing.JToggleButton playButton1;
    private javax.swing.JToggleButton playButton2;
    private javax.swing.JToggleButton playButton3;
    private javax.swing.JToggleButton playButton4;
    private javax.swing.JToggleButton playButton5;
    private javax.swing.JLabel pumplabel1;
    private javax.swing.JLabel pumplabel10;
    private javax.swing.JLabel pumplabel11;
    private javax.swing.JLabel pumplabel2;
    private javax.swing.JLabel pumplabel3;
    private javax.swing.JLabel pumplabel4;
    private javax.swing.JLabel pumplabel5;
    private javax.swing.JLabel pumplabel6;
    private javax.swing.JLabel pumplabel7;
    private javax.swing.JLabel pumplabel8;
    private javax.swing.JLabel pumplabel9;
    private javax.swing.JLabel pumprpmlabel1;
    private javax.swing.JLabel pumprpmlabel10;
    private javax.swing.JLabel pumprpmlabel11;
    private javax.swing.JLabel pumprpmlabel2;
    private javax.swing.JLabel pumprpmlabel3;
    private javax.swing.JLabel pumprpmlabel4;
    private javax.swing.JLabel pumprpmlabel5;
    private javax.swing.JLabel pumprpmlabel6;
    private javax.swing.JLabel pumprpmlabel7;
    private javax.swing.JLabel pumprpmlabel8;
    private javax.swing.JLabel pumprpmlabel9;
    private javax.swing.JLabel pumpsTitleLabel;
    private javax.swing.JSlider pumpslider1;
    private javax.swing.JSlider pumpslider10;
    private javax.swing.JSlider pumpslider11;
    private javax.swing.JSlider pumpslider2;
    private javax.swing.JSlider pumpslider3;
    private javax.swing.JSlider pumpslider4;
    private javax.swing.JSlider pumpslider5;
    private javax.swing.JSlider pumpslider6;
    private javax.swing.JSlider pumpslider7;
    private javax.swing.JSlider pumpslider8;
    private javax.swing.JSlider pumpslider9;
    private javax.swing.JToggleButton recordButton1;
    private javax.swing.JToggleButton recordButton2;
    private javax.swing.JToggleButton recordButton3;
    private javax.swing.JToggleButton recordButton4;
    private javax.swing.JToggleButton recordButton5;
    private javax.swing.JButton refreshPortsButton;
    private javax.swing.JSpinner refreshSecondsSpinner;
    private javax.swing.JLabel sec1label;
    private javax.swing.JLabel sec2label;
    private javax.swing.JLabel sec3label;
    private javax.swing.JLabel sec4label;
    private javax.swing.JLabel sec5label;
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
    private javax.swing.JMenuItem settings;
    private javax.swing.JPanel t1;
    private javax.swing.JPanel t10;
    private javax.swing.JPanel t11;
    private javax.swing.JPanel t2;
    private javax.swing.JPanel t3;
    private javax.swing.JPanel t4;
    private javax.swing.JPanel t5;
    private javax.swing.JPanel t6;
    private javax.swing.JPanel t7;
    private javax.swing.JPanel t8;
    private javax.swing.JPanel t9;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JLabel temp1;
    private javax.swing.JLabel temp10;
    private javax.swing.JLabel temp11;
    private javax.swing.JLabel temp2;
    private javax.swing.JLabel temp3;
    private javax.swing.JLabel temp4;
    private javax.swing.JLabel temp5;
    private javax.swing.JLabel temp6;
    private javax.swing.JLabel temp7;
    private javax.swing.JLabel temp8;
    private javax.swing.JLabel temp9;
    private javax.swing.JToggleButton testMode;
    // End of variables declaration//GEN-END:variables
}
