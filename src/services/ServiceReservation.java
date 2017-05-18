/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionSingleton;

/**
 *
 * @author balha
 */
public class ServiceReservation {
         private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
    
    public ServiceReservation() {
          cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
       public void ajouterReservation(reservation res) {
        try {
            String requete = "INSERT INTO `reservation`(`prix`, `quantite`, `idmateriel_id`, `image`,`idclient_id` )VALUES (?,?,?,?,?)";
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, res.getPrix());
            ps.setString(2, res.getQuantite());
            ps.setString(3, res.getMateriel());
            //ps.setDate(, (Date) res.getDateRes());
            ps.setString(4, res.getImage());
            ps.setInt(5, res.getIdc());

               System.out.println("here");

            
            
          
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       }
       
       
       
       
          public ArrayList<reservation> SelectReservation() {
        try {
        
            String requete = "select * from reservation";
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            ArrayList<reservation> reservations= new ArrayList<>();
            while (result.next()) {
              reservation res = new reservation();
                res.setId(result.getString("prix"));
                res.setQuantite(result.getString("quantite"));
                res.setMateriel(result.getString("idMateriel"));
               // res.setDateRes(result.getDate("pdateRes"));
               res.setImage(result.getString("image"));
                reservations.add(res);                
            }
            return reservations;
        } catch (Exception ee) {
            System.out.println("erreur dans select reservations " + ee.getMessage());
        }
        return null;
    }
       
       
       
       
}
    
    
    

    
    
    

