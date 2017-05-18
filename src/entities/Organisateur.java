/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author azmi
 */
public class Organisateur extends Utilisateurs{
    protected String typeorg;

    public Organisateur(String typeorg, String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role, String image) {
        super(nom, prenom, num_tel, cin, mail, user_name, pwd, role, image);
        this.typeorg = typeorg;
    }

    public String getTypeorg() {
        return typeorg;
    }

    public void setTypeorg(String typeorg) {
        this.typeorg = typeorg;
    }




 

 
  
    public Organisateur() {
    }

    
    
    
    
}
