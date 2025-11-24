/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.config;

import java.awt.Component;
import javax.swing.JOptionPane;


/**
 *
 * @author LIZ
 */

public class Message {

    public static void success(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void warning(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void error(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }    
    
    public static void exception(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Excepción", JOptionPane.ERROR_MESSAGE);
    }
}
