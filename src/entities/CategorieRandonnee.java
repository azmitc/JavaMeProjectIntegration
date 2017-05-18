/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.time.LocalDate;


/**
 *
 * @author Amine
 */
public class CategorieRandonnee {
    String id;
    String nom;
    String image;
    String Description;

    public CategorieRandonnee(String id, String nom,String image, String Description) {
        this.id = id;
        this.nom = nom;
        this.image=image;
        this.Description = Description;
    }

    public CategorieRandonnee(String nom, String image, String Description) {
        this.nom = nom;
        this.image = image;
        this.Description = Description;
    }

    public CategorieRandonnee() {}

    public CategorieRandonnee(String text, LocalDate value, LocalDate value0, String text0, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String string, String text8, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "CategorieRandonnee{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", Description=" + Description + '}';
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    

    
    
    
    

    
}
