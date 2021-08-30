/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import controller.LoginController;
import entities.CategorieRandonnee;
import entities.Randonnee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class SerRandonnee implements ServiceRandonnee<Randonnee> {
private static SerRandonnee instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
    
    public SerRandonnee() {
         cs = ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SerRandonnee getInstance(){
        if(instance==null) 
            instance=new SerRandonnee();
        return instance;
    }

    
    
    
    
     @Override
    public void insert(Randonnee p) {
       try{
        String req = "INSERT INTO `Randonnee` (`date_debut`, `date_fin`, `heure_depart`, `heure_retour`, `prix`,`nbrplace`, `lieu`, `circuit`, `niveau`, `equipments`, `description`, `categorie` ,`autorisationOfficiel` ,  `guidId`, `orgId`,`etat`,`image_name` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?)";


            PreparedStatement ps = cs.getCnx().prepareStatement(req);
               
           
           
            ps.setDate(1, (Date) p.getDate_debut());
            ps.setDate(2, (Date) p.getDate_retour());
            ps.setTime(3, p.getHeure_depart());
             ps.setTime(4, p.getHeure_retour());
            ps.setString(5,p.getPrix());
           ps.setString(6,p.getNbrplace());
           ps.setString(7,p.getLieu());
           ps.setString(8,p.getCircuit());
           ps.setString(9,p.getNiveau());
           ps.setString(10,p.getEquipement());
           ps.setString(11,p.getDescription());
           ps.setString(12,p.getCategorie());
           ps.setString(13,p.getAutorisation());
           ps.setString(14,p.getId_guide());
           ps.setString(15,LoginController.adm.getUser_name());
            ps.setString(16,p.getImage());
          
    ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
@Override
    public ObservableList<String> getNomCategorie() 
    {
        ObservableList<String> lst = FXCollections.observableArrayList();
     
        try {
             
            String req = "SELECT `nom` FROM `categorierandonnee`  ";
           rs=st.executeQuery(req);
            
           
             while(rs.next())
            {
                
                String x=rs.getString(1);
                lst.add(x);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lst ; 
            
            
        
     
           
    }
    public String getIdCategorie(String nom) 
    {
       String x=null;
     
        try {
             
            String req = "SELECT `id` FROM `categorierandonnee` where nom='"+nom+"';";
            
           rs=st.executeQuery(req);
            
           while(rs.next())
            {
                
                 x=rs.getString(1);
                

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ; 
            
            
        
     
           
    }
public String getnomCat(String id) 
    {
       String x=null;
     
        try {
             
            String req = "SELECT `nom` FROM `categorierandonnee` where id='"+id+"';";
            
           rs=st.executeQuery(req);
            
           while(rs.next())
            {
                
                 x=rs.getString(1);
                

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ; 
            
            
        
     
           
    }    
@Override
    public ObservableList<String> getNomGuide() 
    {
        ObservableList<String> lst = FXCollections.observableArrayList();
     
        try {
             
            String req = "SELECT `nom` FROM `utilisateur` where roles='a:1:{i:0;s:10:\"ROLE_GUIDE\";}'  ";
           rs=st.executeQuery(req);
            
           
             while(rs.next())
            {
                
                String x=rs.getString(1);
                lst.add(x);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lst ; 
            
            
        
     
           
    }
@Override
    public ObservableList<String> getNomOrganisateur() 
    {
        ObservableList<String> lst = FXCollections.observableArrayList();
     
        try {
             
            String req = "SELECT `nom` FROM `utilisateur` where roles='a:1:{i:0;s:11:\"ROLE_AGENCE\";}'  ";
           rs=st.executeQuery(req);
            
           
             while(rs.next())
            {
                
                String x=rs.getString(1);
                lst.add(x);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lst ; 
            
            
        
     
           
    }
    
    


@Override
    public ArrayList<Randonnee> Affiches() {
        try {
           
        
            String requete = "select * from randonnee where etat=0";
             PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Randonnee> suj= new ArrayList<Randonnee>();
            while (result.next()) {
               Randonnee ran = new Randonnee(); 
                
                     
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
                ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setImage(result.getString("image_name"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                
                
  
                suj.add(ran);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select randonnées " + ee.getMessage());
        }
        return null;
    }
@Override
     public ArrayList<Randonnee> Affiches1() {
        try {
           
        
            String requete = "select * from randonnee where etat=1";
             PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Randonnee> suj= new ArrayList<Randonnee>();
            while (result.next()) {
               Randonnee ran = new Randonnee(); 
                
                     
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
               ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setImage(result.getString("image_name"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
  
                suj.add(ran);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select randonnées " + ee.getMessage());
        }
        return null;
    }

@Override
 public ArrayList<Randonnee> stat() {
        try {
           
        
            String requete = "select * from randonnee";
             PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Randonnee> suj= new ArrayList<Randonnee>();
            while (result.next()) {
               Randonnee ran = new Randonnee(); 
                
                     
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
                ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setImage(result.getString("image_name"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                
  
                suj.add(ran);
         
            }
            return suj;
        } catch (Exception ee) {
            System.out.println("erreur dan select randonnées " + ee.getMessage());
        }
        return null;
    }
    @Override
    public void delete(String id) {
        String requete = "delete from randonnee where id=?";
        try {
           
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
           // ResultSet result = ps.executeQuery();
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Randonnée supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression Randonnée" + ex.getMessage());
        }}

   

  
@Override
     public void update(Randonnee p)
    
     {
        String requete = "UPDATE randonnee SET date_debut=?, date_fin=?, heure_depart=?, heure_retour=?, prix=?,nbrplace=?, lieu=?, circuit=?, niveau=?, equipments=?, description=?, categorie =? WHERE id=?";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
                
         
           
            ps.setDate(1, (Date) p.getDate_debut());
            ps.setDate(2, (Date) p.getDate_retour());
            ps.setTime(3, p.getHeure_depart());
             ps.setTime(4, p.getHeure_retour());
            ps.setString(5,p.getPrix());
           ps.setString(6,p.getNbrplace());
           ps.setString(7,p.getLieu());
           ps.setString(8,p.getCircuit());
           ps.setString(9,p.getNiveau());
           ps.setString(10,p.getEquipement());
           ps.setString(11,p.getDescription());
           ps.setString(12,p.getCategorie());
           ps.setString(13,p.getId());
           
           
            ps.executeUpdate();
            System.out.println("Randonnee modifiée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification " + ex.getMessage());
        } }
     
     
@Override
     public void Valider(String id){
    
        
        String requete = "UPDATE randonnee SET etat=1 WHERE id=?";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
               
            
            ps.setString(1, id);
           
            ps.executeUpdate();
            System.out.println("Randonnee validée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la validation " + ex.getMessage());
        } }
     
     
     
     
@Override
     public ArrayList<Randonnee> listerRech(String lieu) {

        ArrayList<Randonnee> list = new ArrayList<Randonnee>();
        ResultSet rs; // pour récupérer le résultat de select
       
        String req = "SELECT * FROM randonnee where lieu like '%"+lieu+"%'";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
            
            
            while (result.next()) {
                
                Randonnee ran = new Randonnee();
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
                       ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                list.add(ran);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
@Override
public ArrayList<Randonnee> listerRech1(String niveau) {

        ArrayList<Randonnee> list = new ArrayList<Randonnee>();
        ResultSet rs; // pour récupérer le résultat de select
       
        String req = "SELECT * FROM randonnee where niveau like '%"+niveau+"%'";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
            
            
            while (result.next()) {
                
                Randonnee ran = new Randonnee();
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
               ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                list.add(ran);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
@Override
public ArrayList<Randonnee> listerRech2(String prix) {

        ArrayList<Randonnee> list = new ArrayList<Randonnee>();
        ResultSet rs; // pour récupérer le résultat de select
       
        String req = "SELECT * FROM randonnee where prix like '%"+prix+"%'";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
            
            
            while (result.next()) {
                
                Randonnee ran = new Randonnee();
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
               ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                list.add(ran);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
@Override
public ArrayList<Randonnee> listerRech3(String categorie) {

        ArrayList<Randonnee> list = new ArrayList<Randonnee>();
        ResultSet rs; // pour récupérer le résultat de select
       
        String req = "SELECT * FROM randonnee where categorie like '%"+categorie+"%'";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
            
            
            while (result.next()) {
                
                Randonnee ran = new Randonnee();
               ran.setId(result.getString("id"));
               ran.setDate_debut(result.getDate("date_debut"));
               ran.setDate_retour(result.getDate("date_fin"));
               ran.setHeure_depart(result.getTime("heure_depart"));
               ran.setHeure_retour(result.getTime("heure_retour"));
               ran.setDescription(result.getString("description"));
               ran.setPrix(result.getString("prix"));
               ran.setLieu(result.getString("lieu"));
               ran.setNiveau(result.getString("niveau"));
               ran.setCategorie(result.getString("categorie"));
                ran.setEquipement(result.getString("equipments"));
                ran.setCircuit(result.getString("circuit"));
                ran.setNbrplace(result.getString("nbrplace"));
                list.add(ran);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SerRandonnee.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    

    
}
