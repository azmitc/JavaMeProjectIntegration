/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author azmi
 */
public class Organisateur extends Utilisateur{

   private String typeOrganisateur ;

    public Organisateur( String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role,String typeOrganisateur ,String image) {
        super(nom, prenom, num_tel, cin, mail, user_name, pwd, role, image);
        this.typeOrganisateur = typeOrganisateur;
    }

   

    public void setTypeOrganisateur(String typeOrganisateur) {
        this.typeOrganisateur = typeOrganisateur;
    }

    public String getTypeOrganisateur() {
        return typeOrganisateur;
    }

  

    
    
    
    
}
