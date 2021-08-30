/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.oracle.nio.BufferSecrets.instance;
import entities.Organisateur;
import java.sql.Connection;
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
     private ConnexionSingleton conn;
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
         try {
             String req="INSERT INTO `utilisateur` (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`password`,`roles`,`typeOrganisateur`,`image_name`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getNum_tel()+"','"+p.getCin()+"','"+p.getMail()+"','"+p.getUser_name()+"','"+p.getPwd()+"','"+p.getRole()+"','"+p.getTypeOrganisateur()+"','"+p.getImage()+"')";
             ste.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
             
    }
    public ArrayList<Organisateur> afficher() {
         try {
             ArrayList<Organisateur> list=new ArrayList<>();
             String req="select * from utilisateur";
             ResultSet rs= ste.executeQuery(req);
             Organisateur per=null;
             while (rs.next()){
                 per=new Organisateur(rs.getString("nom"), rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("typeOrganisateur"),rs.getString("image_name"));
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
                  per=new Organisateur(rs.getString("nom"), rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("typeOrganisateur"),rs.getString("image_name"));
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
                   per=new Organisateur(rs.getString("nom"), rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("typeOrganisateur"),rs.getString("image_name"));
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
        String requete = "UPDATE utilisateur SET nom=? WHERE username=?";
        try {
            PreparedStatement ps =  conn.getCnx().prepareStatement(requete);
            ps.setString(1, password);
            ps.setString(2, login);
           
            ps.executeUpdate();
            System.out.println("Organisateur update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  ORGANISATEUR" + ex.getMessage());
        }
    }

       public Organisateur findOrganisateurByLogin (String login){
      Organisateur admin =null;
        String requete = "select * from utilisateur where user_name='"+login+"'";
        try {
           Statement statement =  conn.getCnx().createStatement();
            ResultSet rs = statement.executeQuery(requete);
            while(rs.next()){
                 admin=new Organisateur(rs.getString("nom"), rs.getString("prenom"),rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("typeOrganisateur"),rs.getString("image_name"));


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
