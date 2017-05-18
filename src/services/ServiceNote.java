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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Commentaire;
import entities.Note;
import utils.ConnexionSingleton;

/**
 *
 * @author user
 */
public class ServiceNote {
     ConnexionSingleton connect;
    Statement ste ;
    PreparedStatement prepste;
     private ResultSet rs;
       private static ServiceNote instance2;

    public ServiceNote() {
            try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public static ServiceNote getIns(){
        if(instance2==null) 
            instance2=new ServiceNote();
        return instance2;
    }
  public ArrayList<Note> Affiche() {
        try {
        
            String requete = "select * from note n inner join randonnée r on n.id_rando=r.id";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
      
            ResultSet result = ps.executeQuery();
            ArrayList<Note> co= new ArrayList<Note>();
            while (result.next()) {
               Note no = new Note();
              no.setId_rando(result.getString("id_rando"));
                //no.setNom(result.getInt("nom"));
                 no.setNote(result.getString("note"));
               //com.setTitre(result.getString("titre"));
                co.add(no);
   }
            return co;
        } catch (Exception ee) {
            System.out.println("erreur dan select commentaire " + ee.getMessage());
        }
        return null;
    }
  
  
   public ArrayList<Note> Affiche2(String ide) {
        try {
        
            String requete = "select * from note where id_rando="+ide;
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
      
            ResultSet result = ps.executeQuery();
               
            ArrayList<Note> co= new ArrayList<Note>();
            while (result.next()) {
               Note no = new Note();
              //no.setId_rando(result.getString("id_rando"));
                //no.setNom(result.getInt("nom"));
                 no.setNote(result.getString("note"));
               //com.setTitre(result.getString("titre"));
                co.add(no);
   }
            return co;
        } catch (Exception ee) {
            System.out.println("erreur dan select Note " + ee.getMessage());
        }
        return null;
    }
  
  
  public void ajoutNote( Note no) {
        try {
            String requete= "INSERT INTO `note`(`note`)VALUES(?)";
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);           
            //ps.setInt(1, no.getId_rando());
           // ps.setInt(2, no.getId_user());
            ps.setString(1, no.getNote());
            ps.executeUpdate();
         } catch (Exception e) {
            System.out.println("erreur dans la methode ajout note " + e.getMessage() + " " + e.getLocalizedMessage());
        }}
      
    
}
