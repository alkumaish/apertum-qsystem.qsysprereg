/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.apertum.qsystem.common;

/**
 *
 * @author Evgeniy Egorov
 */
public class Properties {
    
    private Properties() {
    }
    
    public static Properties getInstance() {
        return PropertiesHolder.INSTANCE;
    }
    
    private static class PropertiesHolder {

        private static final Properties INSTANCE = new Properties();
    }
}
