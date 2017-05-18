/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.IMateriel;
import entities.Materiel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionSingleton;
/**
 *
 * @author balha
 */
public class ServiceMateriel implements IMateriel {
   private static ServiceMateriel instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;


    
    public ServiceMateriel() {
         cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     public static ServiceMateriel getInstance(){
        if(instance==null) 
            instance=new ServiceMateriel();
        return instance;
    }
    public ObservableList<String> getRefProd() 
    {
        ObservableList<String> lst = FXCollections.observableArrayList();
     
        try {
             
            String req = "SELECT `reference` FROM `materiel`  ";
              PreparedStatement ps;
           // ps = cs.prepareStatement(req);
           ps = cs.getCnx().prepareStatement(req);
            ResultSet result = ps.executeQuery();
           
            //ste = getInstance().conn.prepareStatement(req);
            
            ResultSet rs = ps.executeQuery(); // select 
             while(rs.next())
            {
                
                String x=rs.getString("reference");
                lst.add(x);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lst ; 
            
            
        
     
           
    }
    
     public void ajouterMateriel(Materiel client) {
        try {
            String requete = "INSERT INTO `materiel`(`nom`, `reference`, `prix`, `description`, `type`, `etat`, `quantite`,`pic`)VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, client.getNom());
            ps.setString(2, client.getReference());
                 ps.setString(3, client.getPrix());
          
            ps.setString(4, client.getDescription());
            ps.setString(5, client.getType());
            ps.setString(6, client.getEtat());
            ps.setString(7, client.getQuantite());
            ps.setString(8, client.getPic());
            //ps.executeUpdate();
                      System.out.println(requete);

            
     
          
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
    }
     
     public ArrayList<Materiel> SelectMateriel() {
        try {
        
            String requete = "select * from materiel";
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Materiel> materiels= new ArrayList<>();
            while (result.next()) {
               Materiel materiel = new Materiel();
                materiel.setId(result.getString("id"));
                materiel.setNom(result.getString("nom"));
                materiel.setReference(result.getString("reference"));
                materiel.setPrix(result.getString("prix"));
                materiel.setDescription(result.getString("description"));
               
                materiel.setType(result.getString("type"));
                materiel.setEtat(result.getString("etat"));
                materiel.setQuantite(result.getString("quantite"));
                                materiel.setPic(result.getString("pic"));

                //materiel.setPic(MyFile.blob2File(result.getBinaryStream("pic")));
                
              
                materiels.add(materiel);
                
                 // System.out.println("materiel "+materiel.getNom());
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
     public  ObservableList<Materiel> SelectAllMateriel() {
        try {
        
            String requete = "select * from materiel";
                       PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ObservableList<Materiel> materiels= FXCollections.observableArrayList();
            while (result.next()) {
               Materiel materiel = new Materiel();
                materiel.setId(result.getString("id"));
                materiel.setNom(result.getString("nom"));
                materiel.setReference(result.getString("reference"));
                materiel.setPrix(result.getString("prix"));
                materiel.setDescription(result.getString("description"));
               
                materiel.setType(result.getString("type"));
                materiel.setEtat(result.getString("etat"));
                materiel.setQuantite(result.getString("quantite"));
                                materiel.setPic(result.getString("pic"));

                //materiel.setPic(MyFile.blob2File(result.getBinaryStream("pic")));
                
              
                materiels.add(materiel);
                
                 // System.out.println("materiel "+materiel.getNom());
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
     
     
       public ArrayList<Materiel> SelectMaterielPourReserver() {
        try {
        
            String requete = "SELECT * FROM `materiel` WHERE etat =\"dispo\" ";
                        PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<Materiel> materiels= new ArrayList<>();
            while (result.next()) {
               Materiel materiel = new Materiel();
               
                materiel.setNom(result.getString("nom"));
                materiel.setReference(result.getString("reference"));
                materiel.setPrix(result.getString("prix"));
                materiel.setDescription(result.getString("description"));
               
                materiel.setType(result.getString("type"));
               
                materiel.setQuantite(result.getString("quantite"));
                materiel.setPic(result.getString("pic"));

                materiels.add(materiel);
                
                 
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
   /*  public ArrayList<Materiel> DisplayAllAllList() throws SQLException{
        ArrayList<Materiel> list=new ArrayList<>();
        String req="select * from materiel";
      rs= ste.executeQuery(req);
     while (rs.next()){
         Materiel per=new Materiel(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(7),rs.getString(8));
         list.add(per);
     }
     return list;
    }*/
     
     
      public ArrayList<Materiel> SelectType() {
        try {
        
            String requete = "select type from materiel";
                       PreparedStatement ps = cs.getCnx().prepareStatement(requete);

            ResultSet result = ps.executeQuery();
            ArrayList<Materiel> materiels= new ArrayList<>();
            while (result.next()) {
               Materiel materiel = new Materiel();
               
               
                materiel.setType(result.getString("type"));
                
                
              
                materiels.add(materiel);
                
                 // System.out.println("materiel "+materiel.getNom());
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
     
     
     

   public void deleteMateriel(String id) {
        String requete = "delete from materiel where id=?";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Materiel supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression Materiel" + ex.getMessage());
        }
    }


   
 
   
   
   
   
   public ArrayList<Materiel> SelectMaterielByRef(String Recherche) {
        try {
        
            String requete = "select * from materiel where concat (`reference`,`nom`,`prix`,`type`,`etat`) like '%"+Recherche+"%' ";
            //concat (`reference`,`nom`,`prix`,`type`) like '%"+Recherche+"%'
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            //ps = conn.prepareStatement(requete);
            //ResultSet result = ps.executeQuery();
           //  ps.setString(1, Ref);
            ResultSet result = ps.executeQuery();
            ArrayList<Materiel> materiels= new ArrayList<>();
            while (result.next()) {
               Materiel materiel = new Materiel();
               materiel.setId(result.getString("id"));
                materiel.setNom(result.getString("nom"));
                materiel.setReference(result.getString("reference"));
                materiel.setPrix(result.getString("prix"));
                materiel.setDescription(result.getString("description"));
     
                materiel.setType(result.getString("type"));
                materiel.setEtat(result.getString("etat"));
                materiel.setQuantite(result.getString("quantite"));
              //  materiel.setPic(MyFile.blob2File(result.getBinaryStream("pic")));
                
              
                materiels.add(materiel);
                
                  //System.out.println("materiel "+materiel.getNom());
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
   
   
    
   public ArrayList<Materiel> SelectMaterielByRefClient(String Recherche) {
        try {
        
            String requete = "select * from materiel where concat (`reference`,`nom`,`prix`,`type`) like '%"+Recherche+"%'  and etat =\"dispo\"     ";
            //"select * from alerte where concat(`lieudepart`,`lieuarrivee`,`date`,`heure`) like '%"+recherche+"%'
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            //ps = conn.prepareStatement(requete);
            //ResultSet result = ps.executeQuery();
           //  ps.setString(1, Ref);
            ResultSet result = ps.executeQuery();
            ArrayList<Materiel> materiels= new ArrayList<>();
            while (result.next()) {
               Materiel materiel = new Materiel();
               materiel.setId(result.getString("id"));
                materiel.setNom(result.getString("nom"));
                materiel.setReference(result.getString("reference"));
                materiel.setPrix(result.getString("prix"));
                materiel.setDescription(result.getString("description"));     
                materiel.setType(result.getString("type"));
                materiel.setQuantite(result.getString("quantite"));
                
              
                materiels.add(materiel);
                
                  //System.out.println("materiel "+materiel.getNom());
            }
            return materiels;
        } catch (Exception ee) {
            System.out.println("erreur dan select materiel " + ee.getMessage());
        }
        return null;
    }
   
   
   
   
       public Materiel findMatrielByRef (String Ref){
      Materiel materiel  =null;
        String requete = "select nom ,reference from materiel where reference ='"+Ref+"'";
        try {
           Statement statement =  st;
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
               materiel  = new  Materiel();
               materiel .setNom(resultat.getString(1));
               materiel .setReference(resultat.getString(2));
            }
        } catch (SQLException ex) {
           Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des matrieles "+ex.getMessage());
        }
        return materiel;
        
    }
       
       
       
       
         
       
       
   public void updateMateriel(String nom,String Ref,String Prix,String Desc,String Type,String Etat,String quantite,String Id) {
        String requete = "UPDATE materiel SET nom=?,reference=?,prix=?,description=?,type=?,etat=?,quantite=?  WHERE id=?";
 
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1,nom); 
            ps.setString(3, Prix);
            ps.setString(4,Desc);
            ps.setString(5,Type);
            ps.setString(6,Etat);
            ps.setString(2, Ref);
            ps.setString(8,Id);
           ps.setString(7,quantite);
            ps.executeUpdate();
            System.out.println("matriel update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  matriel" + ex.getMessage());
        }
    }
   
   
   
   
   
   
    public void updateQuant(String quantite,String id) {
        String requete = "UPDATE materiel SET quantite=?  WHERE reference=?";
 
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(2,id); 
            ps.setString(1, quantite);
            
            ps.executeUpdate();
            System.out.println("prix update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification de prix  matriel" + ex.getMessage());
        }
    }/*
    public boolean update(Materiel m) {
        String qry = "UPDATE materiel SET quantite=? WHERE id =? ";
        System.out.println(qry);
        try {
            PreparedStatement ps =  conn.prepareStatement(qry);
            ps.setString(2,m.getId()); 
            ps.setString(1, m.getQuantite());
            
            ps.executeUpdate();
            System.out.println("prix update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification de prix  matriel" + ex.getMessage());
        }
        return false;   
    }*/

  
   

    

    
   
}
   
    