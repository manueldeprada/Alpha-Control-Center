/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alphamods.controlcenter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

/**
 *
 * @author Prada
 */
public class config {
    public static String getValue(String key){
        return getValue(key, new File(path + File.separator+"settings.properties"));
    }
    
    
    public static String getValue(String key, File file){
    File propfile = file;
Properties prop = new Properties();
InputStream input = null;
try {
 
		input = new FileInputStream(propfile);
 
		// load a properties file
		prop.load(input);
 
		// get the property value and print it out
		return prop.getProperty(key);
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
                        }
                }
}
        return null;
    }
    
     public static String getNetValue(String key, String file){
Properties prop = new Properties();
try {
 
 
		// load a properties file
		prop.load(new StringReader(file));
 
		// get the property value and print it out
		return prop.getProperty(key);
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		
}
        return null;
    }
    
    
    public static void setValue(String key, String value){
        setValue(key, value, new File(path + File.separator+"settings.properties"));
        
    }
    
    
    public static void setValue(String key, String value, File file){
    File propfile = file;

OutputStream output = null;
InputStream input = null;
try {


 input = new FileInputStream(propfile);	
 
 Properties prop = new Properties() {
    @Override
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(new TreeSet<Object>(super.keySet()));
    }
};

 
		prop.load(input);// load a properties file
		input.close();
 output = new FileOutputStream(propfile);
		// get the property value and print it out
		prop.setProperty(key, value);
                prop.store(output, null);
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
                        }
                }
}
    }
   public static String path = System.getProperty("user.dir"); 
}
