/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;


/**
 *
 * @author Othman Ben Jemaa
 */
public class Admin extends Utilisateur{
    //String role;
    public Admin( String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,Date birthday,String photo)
 {
        super( nom, prenom, num_tel, cin, mail, user_name, pwd,role,birthday,photo);
       //this.role=role;
    }
public Admin(){};
//    public String getRole() {
//        return role;
//    }

//    @Override
//    public String toString() {
//        return "Admin{" +super.toString()+ "role=" + role + '}';
//    }
    
}
