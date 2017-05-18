/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Note {
    private int id_user;
    private String id_rando;
     private String note;
     private String nom;

   

    public Note(int id_user, String id_rando, String note) {
        this.id_user = id_user;
        this.id_rando = id_rando;
        this.note = note;
    }

    public Note() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
 public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getId_rando() {
        return id_rando;
    }

    public void setId_rando(String id_rando) {
        this.id_rando = id_rando;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
     
   
}
