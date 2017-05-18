/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Othman Ben Jemaa
 */
public class Utilisateurs {
     //protected int id;
    protected String nom;
    protected String prenom;
    protected String num_tel;
    protected String cin;
    protected String mail;
    protected String user_name;
    protected String pwd;
    protected String role;
    protected String typeorg;
    protected String image;
    

    public Utilisateurs(String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role,String typeorg, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.cin = cin;
        this.mail = mail;
        this.user_name = user_name;
        this.pwd = pwd;
        this.role = role;
        this.typeorg=typeorg;
        this.image = image;
    }

        public Utilisateurs(String nom, String prenom, String num_tel, String cin, String mail, String user_name, String pwd, String role, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.cin = cin;
        this.mail = mail;
        this.user_name = user_name;
        this.pwd = pwd;
        this.role = role;
  
        this.image = image;
    }
    public void setTypeorg(String typeorg) {
        this.typeorg = typeorg;
    }

    public String getTypeorg() {
        return typeorg;
    }

    public Utilisateurs() {
    }

    public String getRole() {
        return role;
    }

    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getCin() {
        return cin;
    }

    public String getMail() {
        return mail;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "infos{" + "nom=" + nom + ", prenom=" + prenom + ", num_tel=" + num_tel + ", cin=" + cin + ", mail=" + mail + ", user_name=" + user_name + ", pwd=" + pwd + ", role=" + role + '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

   
    
    
    
    
}
