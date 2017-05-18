/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author azmi
 */
public class ReservationR {
    private String capacite;
    private String nbrejour;
    private String nbreplace;
    private Date dateres;
    private String prix;
    private String remise;
    private String total;

    public ReservationR() {
    }

    public ReservationR(String capacite, String nbrejour, String nbreplace, Date dateres, String prix, String remise, String total) {
        this.capacite = capacite;
        this.nbrejour = nbrejour;
        this.nbreplace = nbreplace;
        this.dateres = dateres;
        this.prix = prix;
        this.remise = remise;
        this.total = total;
    }

    public String getCapacite() {
        return capacite;
    }

    public String getNbrejour() {
        return nbrejour;
    }

    public String getNbreplace() {
        return nbreplace;
    }

    public Date getDateres() {
        return dateres;
    }

    public String getPrix() {
        return prix;
    }

    public String getRemise() {
        return remise;
    }

    public String getTotal() {
        return total;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public void setNbrejour(String nbrejour) {
        this.nbrejour = nbrejour;
    }

    public void setNbreplace(String nbreplace) {
        this.nbreplace = nbreplace;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
