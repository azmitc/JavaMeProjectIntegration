/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author balha
 */
public interface IMateriel {
    public void ajouterMateriel(Materiel materiel);
    public ArrayList<Materiel> SelectMateriel();
     public void deleteMateriel(String id);
     public List<Materiel> SelectMaterielByRef(String Ref);
     public Materiel findMatrielByRef (String Ref);
     public void updateMateriel(String nom,String Prix,String Desc,String Type,String Etat,String Ref,String Id,String quantite);
    
    
}
