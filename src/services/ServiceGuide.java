/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Guide;
import entities.Materiel;
import entities.Organisateur;
import entities.Utilisateurs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import utils.ConnexionSingleton;

/**
 *
 * @author azmi
 */
public class ServiceGuide {
     ConnexionSingleton conn;
    private Statement ste;
         private static ServiceGuide instance;
        public ServiceGuide(){//on peut aussi appliquer Singleton sur ServicePersonne
            conn=ConnexionSingleton.getInstance();
            try{
                ste=conn.getCnx().createStatement();
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
            }
        }
         public static ServiceGuide getInstance(){
        if(instance==null) 
            instance=new ServiceGuide();
        return instance;
    }
    
    public void ajouterGuide(Guide p) 
    {
    String req="INSERT INTO `utilisateur` (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`password`,`roles`,`disponibilité`,`competance`,`image_name`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getNum_tel()+"','"+p.getCin()+"','"+p.getMail()+"','"+p.getUser_name()+"','"+p.getPwd()+"','"+p.getRole()+"','"+p.getDisponibilite()+"','"+p.getCompetence()+"','"+p.getImage()+"')";
         try {
             ste.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
         }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Guide inserée avec succeé ");
        alert.show();
             
    }
     public ArrayList<Guide> afficher() {
         try {
             ArrayList<Guide> list=new ArrayList<>();
             String req="select * from utilisateur";
             ResultSet rs= ste.executeQuery(req);
               Guide per=null;
             while (rs.next()){    //public Organisateur(String typeorg, String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role, String photo) {

                 per=new Guide( rs.getString("nom"),rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("disponibilité"),rs.getString("competance"),rs.getString("image_name"));
                 list.add(per);
            
             
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }
    
 public void deleteGuide(String cin) {
        String requete = "delete from utilisateur where cin=?";
        try {
            PreparedStatement ps = conn.getCnx().prepareStatement(requete);
            ps.setString(1, cin);
            ps.executeUpdate();
            System.out.println("client supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression organisateur" + ex.getMessage());
        }
    }
  public void updateGuide(String login, String password) {
        String requete = "UPDATE utilisateur SET nom=? WHERE username=?";
        try {
            PreparedStatement ps =  conn.getCnx().prepareStatement(requete);
            ps.setString(1, password);
            ps.setString(2, login);
           
            ps.executeUpdate();
            System.out.println("guide update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  guide" + ex.getMessage());
        }
    }
  public ArrayList<Guide> Rechercher(String cin) {
      /* */
         try {
             ArrayList<Guide> list=new ArrayList<>();
             String req="select * from utilisateur where cin like'%"+cin+"%'";
            PreparedStatement ps = conn.getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
ArrayList<Utilisateurs> users= new ArrayList<>();
Guide per=null;
             while (rs.next()){
      
               
                               per=new Guide( rs.getString("nom"),rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("disponibilité"),rs.getString("competance"),rs.getString("image_name"));

                                //utilisateur.setRole(result.getString("pic"));
//utilisateur.setresult.getString("pic"));
                //materiel.setPic(MyFile.blob2File(result.getBinaryStream("pic")));
                
              
                users.add(per);
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }

       public Guide findGuideByLogin (String login){
      Guide admin =null;
        String requete = "select * from utilisateur where username='"+login+"'";
        try {
           Statement statement =  conn.getCnx().createStatement();
            ResultSet rs = statement.executeQuery(requete);
            while(rs.next()){
         admin=new Guide(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(16));


            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
        }
        if (admin != null) { System.out.println("organisateur existe");}
        else  System.out.println("cet organisateur n'existe pas");

        return admin;
        
    }
}
