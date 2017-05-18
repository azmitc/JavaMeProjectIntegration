/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Commentaire;
import entities.Galerie;
import utils.ConnexionSingleton;

/**
 *
 * @author user
 */
public class ServiceGalerie {
   ConnexionSingleton connect;
    Statement ste ;
    PreparedStatement prepste;
     private ResultSet rs;
       private static ServiceGalerie instance2;

    public ServiceGalerie() {
            try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public static ServiceGalerie getIns(){
        if(instance2==null) 
            instance2=new ServiceGalerie();
        return instance2;
    } 
      
       public void ajoutGalerie( Galerie gal) {
        try {
            String requete= "INSERT INTO `galerie`(`photo`)VALUES(?)";
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);           
            ps.setString(1, gal.getPhoto());
          
            ps.executeUpdate();
         } catch (Exception e) {
            System.out.println("erreur dans la methode ajout commentaire " + e.getMessage() + " " + e.getLocalizedMessage());
        }}
}
