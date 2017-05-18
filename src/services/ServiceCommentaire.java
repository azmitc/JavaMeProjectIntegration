/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Commentaire;
import entities.Sujet;

import utils.ConnexionSingleton;
import utils.MyFile;

/**
 *
 * @author user
 */
public class ServiceCommentaire {
      ConnexionSingleton connect;
    Statement ste ;
    PreparedStatement prepste;
     private ResultSet rs;
       private static ServiceCommentaire instance2;

    public ServiceCommentaire() {
            try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public static ServiceCommentaire getIns(){
        if(instance2==null) 
            instance2=new ServiceCommentaire();
        return instance2;
    }
  public ArrayList<Commentaire> Affiche() {
        try {
        
            String requete = "select * from commentaire";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Commentaire> co= new ArrayList<Commentaire>();
                         

            while (result.next()) {
               Commentaire com = new Commentaire();
              com.setId_com(result.getString("id_com"));
                com.setText(result.getString("text"));
                com.setUser_name(result.getString("user_id"));
               com.setId(result.getString("id"));
                com.setDate_env(result.getDate("dateEnv"));
                co.add(com);
   }
            return co;
        } catch (Exception ee) {
            System.out.println("erreur dan select commentaire " + ee.getMessage());
        }
        return null;
    }
  
  
   
  
  public void ajoutCom( Commentaire com) {
        try {
            String requete= "INSERT INTO `commentaire`(`id`,`text`,`user_id`)VALUES(?,?,?)";
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);           
            ps.setString(1, com.getId());
            ps.setString(2, com.getText());
            ps.setString(3, com.getUser_id());
            ps.executeUpdate();
         } catch (Exception e) {
            System.out.println("erreur dans la methode ajout commentaire " + e.getMessage() + " " + e.getLocalizedMessage());
        }}
      
     public void suppCom(String id) {
        String requete = "delete from commentaire where id_com=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression commentaire" + ex.getMessage());
        } }
      public void suppCom2(String id) {
        String requete = "delete from commentaire where id=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression commentaire" + ex.getMessage());
        } }
      public void modifCom(String id, String text) {
        String requete = "UPDATE commentaire SET text=? WHERE id_com=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(3,text);
            ps.setString(2,id);    
            ps.executeUpdate();
            System.out.println("Commentaire update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  Commentaire" + ex.getMessage());
        } }
      
   public ArrayList<Commentaire> RechZ(String id) {
        try {
        
            String requete = "select * from commentaire c inner join utilisateur u on c.user_id=u.username where c.id ="+id;
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Commentaire> co= new ArrayList<Commentaire>();
            while (result.next()) {
               Commentaire com = new Commentaire();
                //com.setDate_env(result.getTimestamp(""))
                com.setText(result.getString("text"));  
                com.setId_com(result.getString("id_com"));  
                com.setUser_name(result.getString("user_id"));
                com.setMail(result.getString("email"));
              
               com.setId(result.getString("id"));

                co.add(com);
        } return co;
        } catch (Exception ee) {
            System.out.println("erreur dans select commentaire " + ee.getMessage());
        }
        return null;    }
   
     public void SignalCom(String id) {
        String requete = "UPDATE commentaire SET signals=1 WHERE id_com=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
           
            ps.setString(1,id);    
            ps.executeUpdate();
            System.out.println("Commentaire signalé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  Commentaire" + ex.getMessage());
        } }
     public ArrayList<Commentaire> AfficheSig() {
        try {
        
            String requete = "select * from commentaire where signals=1";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Commentaire> co= new ArrayList<Commentaire>();
                         

            while (result.next()) {
               Commentaire com = new Commentaire();
              com.setId_com(result.getString("id_com"));
                com.setText(result.getString("text"));
                com.setUser_name(result.getString("user_id"));
               com.setId(result.getString("id"));
                co.add(com);
   }
            return co;
        } catch (Exception ee) {
            System.out.println("erreur dan select commentaire " + ee.getMessage());
        }
        return null;
    }
    
}
