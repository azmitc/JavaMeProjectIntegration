/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionSingleton {
    
    private String url="jdbc:mysql://localhost:3306/pidev";
    private String login="root";
    String pwd="";
    private Connection cnx;
    private static ConnexionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ConnexionSingleton getInstance(){
       
       if(instance==null)
           instance=new ConnexionSingleton();
       return instance;
   }
    
    
    
    
}
