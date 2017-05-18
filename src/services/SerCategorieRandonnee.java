/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.CategorieRandonnee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.ConnexionSingleton;
/**
 *
 * @author Amine
 */
public class SerCategorieRandonnee implements ServiceCategorieInt<CategorieRandonnee> {
    private static SerCategorieRandonnee instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
    
    public SerCategorieRandonnee() {
         cs = ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SerCategorieRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SerCategorieRandonnee getInstance(){
        if(instance==null) 
            instance=new SerCategorieRandonnee();
        return instance;
    }

    @Override
    public void insert(CategorieRandonnee r) {
       
        try {
            String req="INSERT INTO `categorierandonnee`(`nom`,  `description`,`image_name`) VALUES (?,?,?)";
          PreparedStatement ps = cs.getCnx().prepareStatement(req);
                
          
            ps.setString(1,r.getNom());
            ps.setString(3,r.getImage());
            ps.setString(2,r.getDescription());
           
            
           
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SerCategorieRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
    
    @Override
     public void update(CategorieRandonnee r)
    
     {
        String requete = "UPDATE categorierandonnee SET nom=?,description=? WHERE id=?";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
                
          
          
            ps.setString(1,r.getNom());
            ps.setString(2,r.getDescription());
             ps.setString(3,r.getId());
            
            System.out.println(ps);
           
            ps.executeUpdate();
            System.out.println("categorie modifiée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification " + ex.getMessage());
        } }

   /*
    public int nombrecat()
    {int count = 0;
    try{
    String requete="select count(*) as '"+count+"' from categorierandonnée";
    
     PreparedStatement ps = cs.getConn().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
    
    }
    catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
    return count;
    }*/
    
    @Override
    public ArrayList<CategorieRandonnee> Affiches() {
        try {
           
        
            String requete = "select * from categorierandonnee";
             PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<CategorieRandonnee> suj= new ArrayList<CategorieRandonnee>();
            while (result.next()) {
               CategorieRandonnee cr = new CategorieRandonnee();
                cr.setId(result.getString("id"));

               cr.setNom(result.getString("nom"));
                cr.setImage(result.getString("image_name"));
                cr.setDescription(result.getString("description"));
                
  
                suj.add(cr);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select categorie " + ee.getMessage());
        }
        return null;
    }


    
    @Override
    public ArrayList<CategorieRandonnee> listerRech(String nom) {

        ArrayList<CategorieRandonnee> list = new ArrayList<CategorieRandonnee>();
        ResultSet rs; // pour récupérer le résultat de select
       
        String req = "SELECT * FROM categorierandonnee where nom like '%"+nom+"%'";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
            
            
            while (result.next()) {
                
                CategorieRandonnee ran = new CategorieRandonnee();
               ran.setId(result.getString("id"));
               ran.setNom(result.getString("nom"));
               ran.setImage(result.getString("image_name"));
               ran.setDescription(result.getString("Description"));
                list.add(ran);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    
   
    @Override
    public void delete(String id) {
        String requete = "delete from categorierandonnee where id=?";
        try {
           
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
           // ResultSet result = ps.executeQuery();
            ps.setString(1, id);
               System.out.println(ps);
            ps.executeUpdate();
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de la categorie" + ex.getMessage());
        }}

   


}
