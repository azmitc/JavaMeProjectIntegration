/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import entities.Sujet;
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
import utils.MyFile;

/**
 *
 * @author user
 */

public class ServiceSujet {
  ConnexionSingleton connect;
    Statement ste ;
    PreparedStatement prepste;
     private ResultSet rs;
       private static ServiceSujet instance1;

    public ServiceSujet() {
      try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
      public static ServiceSujet getIns(){
        if(instance1==null) 
            instance1=new ServiceSujet();
        return instance1;
    }
  
 
 public ArrayList<Sujet> Affiches() {
        try {
        
            String requete = "select * from sujet s inner join utilisateur u on s.name_user=u.username where disponibilite=0";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Sujet> suj= new ArrayList<Sujet>();
            while (result.next()) {
               Sujet sujet = new Sujet();
                sujet.setIdentif(result.getString("id"));
                sujet.setTitre(result.getString("titre"));
                sujet.setText(result.getString("text"));
                sujet.setTheme(result.getString("theme"));
               sujet.setRole(result.getString("roles"));
               sujet.setName_user(result.getString("name_user"));
               sujet.setPic(result.getString("image_name"));
               //sujet.setDate1(result.getDate("date_creation"));
                //sujet.setImage(MyFile.blob2File(result.getBinaryStream("Image")));
                 //s.setPic(MyFile.blob2File(resultat.getBinaryStream(7)));
                suj.add(sujet);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
  public ArrayList<Sujet> AffichesnoDispo() {
        try {
        
            String requete = "select * from sujet s inner join utilisateur u on s.name_user=u.username where disponibilite=0";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Sujet> suj= new ArrayList<Sujet>();
            while (result.next()) {
               Sujet sujet = new Sujet();
                sujet.setIdentif(result.getString("id"));
                sujet.setTitre(result.getString("titre"));
                sujet.setText(result.getString("text"));
                sujet.setTheme(result.getString("theme"));
               sujet.setRole(result.getString("roles"));
               sujet.setName_user(result.getString("name_user"));
               sujet.setPic(result.getString("image_name"));
                //sujet.setImage(MyFile.blob2File(result.getBinaryStream("Image")));
                 //s.setPic(MyFile.blob2File(resultat.getBinaryStream(7)));
                suj.add(sujet);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }

 public ObservableList<String> getIdUser() 
    {
        ObservableList<String> lst = FXCollections.observableArrayList();
        try {
             
            String req = "SELECT `username`,`roles` FROM `utilisateur`";
           rs=ste.executeQuery(req);
            
           
             while(rs.next())
            { String x=rs.getString(1);
            String y=rs.getString(2);
                lst.add(x);  }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lst ;        
    }
 
    public void ajoutSujet( Sujet sujet) {
        try {
            String requete= "INSERT INTO `sujet`(`titre`,`theme`,`text`,`image_name`,`name_user`)VALUES(?,?,?,?,?)";
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            //ps.setInt(1, sujet.getIdentif());
            ps.setString(1, sujet.getTitre());
            ps.setString(2, sujet.getTheme());
            ps.setString(4, sujet.getPic());
             ps.setString(3, sujet.getText()); 
              ps.setString(5, sujet.getName_user()); 
       
        
             ps.executeUpdate();
         } catch (Exception e) {
            System.out.println("erreur dans la methode ajout sujet " + e.getMessage() + " " + e.getLocalizedMessage());
        }
    }
      
     public void suppSujet(String id) {
        String requete = "delete from sujet where id=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("sujet supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression sujet" + ex.getMessage());
        }
    }
      public void modifSujet(String id, String titre,String theme,String text,String pic) {
        String requete = "UPDATE sujet SET titre=?,theme=?,text=?,image_name=? WHERE id=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(3,text);
            ps.setString(2,theme);
            ps.setString(5,id);
            ps.setString(1,titre);
             ps.setString(4,pic);
         
            ps.executeUpdate();
            System.out.println("sujet update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  sujet" + ex.getMessage());
        }
    }
      

    

 
  
   public ArrayList<Sujet> RechZ(String id) {
        try {
        
            String requete = "select * from sujet where concat (`titre`,`text`)like'%"+id+"%'";
            PreparedStatement ps;
            ps = connect.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Sujet> suj= new ArrayList<Sujet>();
            while (result.next()) {
               Sujet sujet = new Sujet();
                sujet.setIdentif(result.getString("id"));
                sujet.setTitre(result.getString("titre"));
                sujet.setText(result.getString("text"));
                 sujet.setTheme(result.getString("theme"));
                 sujet.setPic(result.getString("image_name"));
                //sujet.setPic(MyFile.blob2File(result.getBinaryStream("pic")));
                suj.add(sujet);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dans select sujet " + ee.getMessage());
        }
        return null;
    }
  
/*public List<Sujet> findStoresByResponsable(String id) {
        List<Sujet> listeStores = new ArrayList<>();
        String query = "SELECT * FROM sujet WHERE id = ?";
        try{
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1,id);
              ResultSet resultat = ps.executeQuery();
              
              while (resultat.next()) {
            Sujet s =new Sujet(resultat.getString(1), resultat.getString(2),resultat.getString(4));
            s.setPic(MyFile.blob2File(resultat.getBinaryStream(7)));
            listeStores.add(s); 
        
              }
        return listeStores ; 
             }
    
       catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("erreur lors du chargement du store " + ex.getMessage());
            return null;
        }}*/
   /////////
    public void DesactiverSujet(String id) {
        String requete = "UPDATE sujet SET disponibilite=0 WHERE id=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1,id);
            ps.executeUpdate();
            System.out.println("sujet desactivé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la desactivation du sujet" + ex.getMessage());
        }
    }
     public void AugmenterNbParticipant(String id) {
        String requete = "UPDATE sujet SET nb_participants=nb_participants+1 WHERE id=?";
        try {
            PreparedStatement ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1,id);
            ps.executeUpdate();
            System.out.println("nombre de participant augmenté");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la desactivation du sujet" + ex.getMessage());
        }
    }
   
}
