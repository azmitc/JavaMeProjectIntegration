/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author balha
 */
public class reservation {
    String id;
    String prix;
    String quantite;
    String materiel;
    Date dateRes;
    String image;
    int idC;

    public reservation(String id, String prix, String quantite, String materiel, Date  dateRes, String image,int idC) {
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
        this.materiel = materiel;
        this.dateRes = dateRes;
        this.image = image;
        this.idC = idC;
    }

    public reservation(String prix, String quantite, String materiel, String image) {
        this.prix = prix;
        this.quantite = quantite;
        this.materiel = materiel;
        //this.dateRes = dateRes;
        this.image = image;
    }

    public reservation() {
    }
    public reservation(String prix, String quantite, String idM, String image, int user_name) {
        this.prix = prix;
        this.quantite = quantite;
        this.materiel = idM;
        this.image = image;
        this.idC = user_name;
    }

    public String getId() {
        return id;
    }
    public int getIdc() {
        return idC;
    }
    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public Date  getDateRes() {
        return dateRes;
    }

    public void setDateRes(Date  dateRes) {
        this.dateRes = dateRes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.prix);
        hash = 59 * hash + Objects.hashCode(this.quantite);
        hash = 59 * hash + Objects.hashCode(this.materiel);
        hash = 59 * hash + Objects.hashCode(this.dateRes);
        hash = 59 * hash + Objects.hashCode(this.image);
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
        final reservation other = (reservation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.quantite, other.quantite)) {
            return false;
        }
        if (!Objects.equals(this.materiel, other.materiel)) {
            return false;
        }
        if (!Objects.equals(this.dateRes, other.dateRes)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", materiel=" + materiel + ", dateRes=" + dateRes + ", image=" + image + '}';
    }
    
    
}
