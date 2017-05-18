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
 * @author Othman Ben Jemaa
 */
public class Utilisateur  {
    protected int id;
    protected SimpleStringProperty nom;
    protected SimpleStringProperty prenom;
    protected SimpleStringProperty num_tel;
    protected SimpleStringProperty cin;
    protected SimpleStringProperty mail;
    protected SimpleStringProperty user_name;
    protected SimpleStringProperty pwd;
    protected SimpleStringProperty role;
    protected Date birthday;
    protected String photo;
    


    public Utilisateur( String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,Date birthday,String photo)
 {
       // this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.num_tel = new SimpleStringProperty(num_tel);
        this.cin = new SimpleStringProperty(cin);
        this.mail = new SimpleStringProperty(mail);
        this.user_name = new SimpleStringProperty(user_name);
        this.pwd = new SimpleStringProperty(pwd);
        this.role=new SimpleStringProperty(role);
        this.birthday=birthday;
        this.photo=photo;
    }

    public Utilisateur(String nom, String prenom,String num_tel,String cin, String mail, String user_name, String pwd, String role, String photo) {
           this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.num_tel = new SimpleStringProperty(num_tel);
        this.cin = new SimpleStringProperty(cin);
        this.mail = new SimpleStringProperty(mail);
        this.user_name = new SimpleStringProperty(user_name);
        this.pwd = new SimpleStringProperty(pwd);
        this.role=new SimpleStringProperty(role);
        this.photo = photo;
    }

    public Utilisateur( int id,String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd,String role,Date birthday,String photo)
 {
       this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.num_tel = new SimpleStringProperty(num_tel);
        this.cin = new SimpleStringProperty(cin);
        this.mail = new SimpleStringProperty(mail);
        this.user_name = new SimpleStringProperty(user_name);
        this.pwd = new SimpleStringProperty(pwd);
        this.role=new SimpleStringProperty(role);
        this.birthday=birthday;
        this.photo=photo;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role.get();
    }

    
    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getNum_tel() {
        return num_tel.get();
    }

    public String getCin() {
        return cin.get();
    }

    public String getMail() {
        return mail.get();
    }

    public String getUser_name() {
        return user_name.get();
    }

    public String getPwd() {
        return pwd.get();
    }

    @Override
    public String toString() {
        return "infos{" + "nom=" + nom + ", prenom=" + prenom + ", num_tel=" + num_tel + ", cin=" + cin + ", mail=" + mail + ", user_name=" + user_name + ", pwd=" + pwd + ", role=" + role + '}';
    }
public SimpleStringProperty getUser_nameProperty() {
        return user_name;
}
    public SimpleStringProperty getPwdProperty() {
        return pwd;
    }
        public SimpleStringProperty getMailProperty() {
        return mail;
    }
        public SimpleStringProperty getPhoneProperty() {
        return num_tel;
    }
    public void setCin(String cin) {
        this.cin = new SimpleStringProperty(cin);
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = new SimpleStringProperty(num_tel);
    }

    public void setMail(String mail) {
        this.mail = new SimpleStringProperty(mail);
    }

    public void setUser_name(String user_name) {
        this.user_name = new SimpleStringProperty(user_name);
    }

    public void setPwd(String pwd) {
        this.pwd = new SimpleStringProperty(pwd);
    }
   

   
    
    
    
    
}
