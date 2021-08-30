/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Materiel;
import utils.ConnexionSingleton;
/**
 *
 * @author balha
 */
public class ServiceReclamation implements IReclamation {
 ConnexionSingleton conn;   
 private Statement ste;
    
    public ServiceReclamation() {
         conn=ConnexionSingleton.getInstance();
        try {
            ste=conn.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
         public void ajouterReclamation(   Reclamation Rec) {
             
        try {
            String requete = "INSERT INTO `reclamation`( `description`, `typeReclamation`,  `date_achat`,`mailClient`, `refmateriel_id`,`idclient_id` )VALUES (?,?,?,?,?,?)";
             PreparedStatement ps = conn.getCnx().prepareStatement(requete);
            //ps.setString(1, Rec.getId());
           
            ps.setString(1, Rec.getDescription());
            ps.setString(2, Rec.getTypeReclamation());
            ps.setDate(3, (Date) Rec.getDateAchat());
            ps.setInt(4, Rec.getIdUtilisateur());

            ps.setString(5, Rec.getIdMateriel());
              ps.setInt(6, Rec.getIdUtilisateur());
            System.out.println(ps);

            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("erreur dans la methode ajout Reclamation " + e.getMessage() + " " + e.getLocalizedMessage());
        }
           
    }
         
          public ArrayList<Reclamation> SelectReclamation() {
        try {
        
            String requete = "select * from reclamation";
            PreparedStatement ps;
            ps = conn.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Reclamation> reclamations= new ArrayList<Reclamation>();
            while (result.next()) {
               Reclamation reclamation = new Reclamation();
               reclamation.setId(result.getString("id"));
               reclamation.setIdUtilisateur(result.getInt("idclient_id"));
               reclamation.setDescription(result.getString("description"));
                reclamation.setTypeReclamation(result.getString("typeReclamation"));
               
                reclamation.setDateAchat(result.getDate("date_achat"));
                reclamation.setDateEnvoi(result.getDate("date_envoi"));
                reclamation.setIdMateriel(result.getString("refmateriel_id"));
               // reclamation.setValiditée(result.getString(8));
                
                
              
               reclamations.add(reclamation);
                
                  System.out.println("Reclamation "+reclamation.getId());
            }
            return reclamations;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
    
    
    
       public void deleteReclamation(String id) {
        String requete = "delete  from reclamation where id=?";
        try {
            PreparedStatement ps = conn.getCnx().prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression Reclamation" + ex.getMessage());
        }
    }
       
       
       
        public ArrayList<Reclamation> SelectReclamationById(String id) {
        try {
        
            String requete = "select * from reclamation where id=?";
             PreparedStatement ps = conn.getCnx().prepareStatement(requete);
            //ps = conn.prepareStatement(requete);
            //ResultSet result = ps.executeQuery();
             ps.setString(1, id);
            ResultSet result = ps.executeQuery();
            ArrayList<Reclamation> reclamations= new ArrayList<Reclamation>();
            while (result.next()) {
               Reclamation reclamation = new Reclamation();
               reclamation.setId(result.getString(1));
               reclamation.setDescription(result.getString(2));
                reclamation.setTypeReclamation(result.getString(3));
               
                reclamation.setDateAchat(result.getDate(4));
                reclamation.setDateEnvoi(result.getDate(5));
                reclamation.setIdMateriel(result.getString(6));
                reclamation.setValiditée(result.getString(7));
                
                
              
               reclamations.add(reclamation);
                
                  System.out.println("Reclamation "+reclamation.getId());
            }
            return reclamations;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
        
         public void updateReclamation(String idUtilisateur, String typeReclamation,  String description, String dateAchat, String dateEnvoi, String  validitée, String  idMateriel,String  id) {
        String requete = "UPDATE reclamation SET description=?,typeReclamation=?,date_achat=? ,date_envoi=?,validitée=?,idMateriel=?  WHERE id=?";

        //,prix=?,description=?,type=?,etat=?
        //,Float Prix,String Desc,String Type,String Etat
        try {
            PreparedStatement ps =  conn.getCnx().prepareStatement(requete);
            ps.setString (8, id);
            
            ps.setString(1,description);
            ps.setString(2, typeReclamation);
            //ps.setString(3,typeProb);
             ps.setString(3,dateAchat);
            ps.setString(4,dateEnvoi);
            ps.setString (5,validitée);
            ps.setString (6, idMateriel);
            ps.setString (7, idUtilisateur);
           
           
            
           
            
           
           
            ps.executeUpdate();
            System.out.println("Reclamation update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  Reclamation" + ex.getMessage());
        }
    }
    
    
    
}
