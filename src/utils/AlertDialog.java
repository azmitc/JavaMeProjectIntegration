/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author nadim
 */
public class AlertDialog {
    
    public static void show(String title,String message,Alert.AlertType type){
        Alert al;
         al = new Alert(type);
            al.setTitle(title);
            al.setHeaderText(""); 
            al.setContentText(message);
            al.showAndWait();
        
    }
    
      public static Alert showDialog(String title,String message,Alert.AlertType type){
        Alert al;
         al = new Alert(type);
            al.setTitle(title);
            al.setHeaderText(""); 
            al.setContentText(message);
            
            
            return al;
        
    }
    
   
    
}
