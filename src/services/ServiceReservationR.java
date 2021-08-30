/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.ReservationR;
import java.sql.Connection;
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
 * @author azmi
 */
public class ServiceReservationR {
 ConnexionSingleton conn;  
 private Statement ste;
       
     public ServiceReservationR(){//on peut aussi appliquer Singleton sur ServicePersonne
            conn=ConnexionSingleton.getInstance();
            try{
                ste=conn.getCnx().createStatement();
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
            }
        }
     public void ajouterReservation(entities.ReservationR p) throws SQLException
    {
    String req="INSERT INTO `reservationr` (`capacite`, `nbredejour`, `nombreplace`,`dateres`,`prix`,`remise`,`total`) VALUES ('"+p.getCapacite()+"','"+p.getNbrejour()+"','"+p.getNbreplace()+"','"+p.getDateres()+"','"+p.getPrix()+"','"+p.getRemise()+"','"+p.getTotal()+"')";
    ste.executeUpdate(req);
             
    }
     public ArrayList<entities.ReservationR> afficher() {
         try {
             ArrayList<entities.ReservationR> list=new ArrayList<>();
             String req="select * from reservationr ";
             ResultSet rs= ste.executeQuery(req);
             ReservationR per=null;
             while (rs.next()){
                 per=new ReservationR(rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7), rs.getString(8));
                 list.add(per);
             }
                 return list;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceOrganisateur.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     
    }
     public void deleteReservation(String cin) {
        String requete = "delete from reservationr where capacite=?";
        try {
            PreparedStatement ps = conn.getCnx().prepareStatement(requete);
            ps.setString(1, cin);
            ps.executeUpdate();
            System.out.println("client supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression client" + ex.getMessage());
        }
    }
    
}
