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
public class Randonneur extends Utilisateur {
    int pt_fidel;
    int connected=0 ;
    public Randonneur() {
    }

     public Randonneur( String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,Date birthday,int pt_fidel,String photo) {
        super( nom, prenom, num_tel, cin, mail, user_name, pwd,role,birthday,photo);
        this.pt_fidel=pt_fidel;
    }
    
    public Randonneur( int  id,String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,Date birthday,int pt_fidel,String photo) {
        super( id,nom, prenom, num_tel, cin, mail, user_name, pwd,role,birthday,photo);
        this.pt_fidel=pt_fidel;
    }

    public int getPt_fidel() {
        return pt_fidel;
    }

    public void setPt_fidel(int pt_fidel) {
        this.pt_fidel = pt_fidel;
    }

    public int getConnected() {
        return connected;
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    
      @Override
    public String toString() {
        return "Randonneur{" +super.toString()+ "Points_fidelite=" + pt_fidel + '}';
    }
}
