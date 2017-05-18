/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import entities.Reclamation;

/**
 *
 * @author balha
 */
public interface IReclamation {
    public void ajouterReclamation(   Reclamation Rec);
      public ArrayList<Reclamation> SelectReclamation();
       public void deleteReclamation(String id) ;
       public ArrayList<Reclamation> SelectReclamationById(String id);
public void updateReclamation(String idUtilisateur, String typeReclamation, String description, String dateAchat, String dateEnvoi, String validitée, String  idMateriel,String  id) ;

}
