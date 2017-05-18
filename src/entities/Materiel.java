/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author balha
 */
public class Materiel {
    
   private String id;
    private String nom;
    private String reference;
    private String description;
    private String prix;
    private SimpleStringProperty type;
    private String etat;
    private String quantite;
    private String pic;
    

    public Materiel() {
    }

    public Materiel(String id, String quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public Materiel( String nom, String reference, String description, String  prix, String type, String etat ,String quantite,String pic) {
       
        this.nom = nom;
        this.reference = reference;
        this.description = description;
        this.prix = prix;
        this.type = new SimpleStringProperty(type);
        this.etat = etat;
         this.quantite = quantite;
         this.pic = pic;
    }

    
public void setPic(String pic) {
        this.pic = pic;

    }
public String getPic() {
        return pic;
    }
    

    public String getNom() {
        return nom;
    }
     public String getId() {
        return id;
    }
     public void setId(String id) {
        this.id = id;
    }
     public String getQuantite() {
        return quantite;
    }
     public void setQuantite(String  quantite) {
        this.quantite =  quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getPrix() {
        return prix ;
    }

    public void setPrix(String  prix) {
        this.prix = prix;
    }

    public String getType() {
        return  type.get();
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.reference);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materiel other = (Materiel) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Materiel{" + "nom=" + nom + ", reference=" + reference + ", description=" + description + ", prix=" + prix + ", type=" + type + ", etat=" + etat + ",quantite="+quantite+", pic=" + pic +'}';
    }

    


    
    
}
