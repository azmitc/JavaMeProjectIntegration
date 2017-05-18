/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.oracle.nio.BufferSecrets.instance;
import entities.Organisateur;
import entities.Randonneur;
import entities.Sujet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import utils.ConnexionSingleton;

/**
 *
 * @author azmi
 */
public class ServiceOrganisateur  implements IOrganisateur{
      ConnexionSingleton conn;
    private Statement ste;
    private static ServiceOrganisateur instance;
        
        public ServiceOrganisateur(){//on peut aussi appliquer Singleton sur ServicePersonne
            conn=ConnexionSingleton.getInstance();
            try{
                ste=conn.getCnx().createStatement();
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
            }
            
        }
        public static ServiceOrganisateur getInstance(){
        if(instance==null) 
            instance=new ServiceOrganisateur();
        return instance;
    }
    
    public void ajouterOrganisateur(Organisateur p) 
    {
 String req="INSERT INTO `utilisateur` (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`password`,`roles`,`typeOrganisateur`,`image_name`) values (?,?,?,?,?,?,?,?,?,?)";

   try {
            PreparedStatement ps = conn.getCnx().prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
                 ps.setString(3, p.getNum_tel());
          
            ps.setString(4, p.getCin());
            ps.setString(5, p.getMail());
            ps.setString(6, p.getUser_name());
            ps.setString(7, p.getPwd());
            ps.setString(8, p.getRole());
             ps.setString(9, p.getTypeorg());
          
            ps.setString(10, p.getImage());
          
            //ps.executeUpdate();
                      System.out.println(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }   
             
    }
    public ArrayList<Organisateur> afficher() {
         try {
             ArrayList<Organisateur> list=new ArrayList<>();
             String req="select * from utilisateur";
             ResultSet rs= ste.executeQuery(req);
               Organisateur per=null;
             while (rs.next()){    //public Organisateur(String typeorg, String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role, String photo) {

                 per=new Organisateur(rs.getString("typeOrganisateur"), rs.getString("nom"),rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("image_name"));
                 list.add(per);
            
               list.add(per);
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }
    public ArrayList<Organisateur> loadchart() {
         try {
             ArrayList<Organisateur> list=new ArrayList<>();
             String req="select * from utilisateur";
             ResultSet rs= ste.executeQuery(req);
             XYChart.Series<Object,Object> series=new XYChart.Series<Object, Object>();
             Organisateur per=null;
             while (rs.next()){
                 per=new Organisateur(rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(12),rs.getString("image_name"));
                 list.add(per);
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }
     public ArrayList<Organisateur> Rechercher(String cin) {
         try {
             ArrayList<Organisateur> list=new ArrayList<>();
             String req="select * from utilisateur where cin like'%"+cin+"%'";
             ResultSet rs= ste.executeQuery(req);
             Organisateur per=null;
             while (rs.next()){
                 per=new Organisateur(rs.getString("typeOrganisateur"), rs.getString("nom"),rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("image_name"));
                 list.add(per);
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }
       
 public void deleteOrganisateur(String cin) {
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
 
  public void updateOrganisateur(String login, String password) {
        String requete = "UPDATE utilisateur SET pwd=? WHERE user_name=?";
        try {
            PreparedStatement ps =  conn.getCnx().prepareStatement(requete);
            ps.setString(1, password);
            ps.setString(2, login);
           
            ps.executeUpdate();
            System.out.println("Admin update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  Admin" + ex.getMessage());
        }
    }

       public Organisateur findOrganisateurByLogin (String login){
      Organisateur admin =null;
        String requete = "select * from utilisateur where user_name='"+login+"'";
        try {
           Statement statement =  conn.getCnx().createStatement();
            ResultSet rs = statement.executeQuery(requete);
            while(rs.next()){
                 admin=new Organisateur( rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(12),rs.getString(16),rs.getString("image_name"));


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
