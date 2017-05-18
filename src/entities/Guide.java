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
public class Guide extends Utilisateurs {
private String disponibilite;
    private String competence;

    public Guide() {
    }

    public Guide(String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,String disponibilite, String competence,String image) {
        super(nom, prenom, num_tel, cin, mail, user_name, pwd, role,image);
        this.disponibilite = disponibilite;
        this.competence = competence;
    }

   
    

   

    
    
    

    public String getCompetence() {
        return competence;
    }


    public String getDisponibilite() {
        return disponibilite;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    
    
    
    
}
