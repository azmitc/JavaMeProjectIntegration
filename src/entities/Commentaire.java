/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Commentaire {
    private String id;
    private String text;
    private String user_id;
    private String id_com;
    private String titre;
    private Date date_env;
    private String mail;
    private String user_name;
    private String signals;

    public String getSignals() {
        return signals;
    }

    public void setSignals(String signals) {
        this.signals = signals;
    }
    

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDate_env() {
        return date_env;
    }

    public void setDate_env(Date date_env) {
        this.date_env = date_env;
    }
    

    public Commentaire(String id, String text,String titre) {
        this.id = id;
        this.text = text;
    
    }

    public String getId_com() {
        return id_com;
    }

    public void setId_com(String id_com) {
        this.id_com = id_com;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

  

    public Commentaire() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", text=" + text + ", user_name=" + user_name + ", id_com=" + id_com + '}';
    }

  
    
    
    
}
