/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;

/**
 *
 * @author balha
 */
public class Reclamation {
    private String id;
    private String idUtilisateur;
    private String idMateriel;
    private String typeReclamation;
    private String description;
    private Date dateAchat;
    private Date dateEnvoi;
    private String validitée;
    

    public Reclamation() {
    }

    public Reclamation(String idUtilisateur, String idMateriel, String typeReclamation, String description, Date dateAchat) {
        this.idUtilisateur = idUtilisateur;
        this.idMateriel = idMateriel;
        this.typeReclamation = typeReclamation;
        this.description = description;
        this.dateAchat = dateAchat;
    }
    

    public Reclamation(String description, String typeReclamation,Date dateAchat, Date dateEnvoi,String idUtilisateur,String idMateriel) {
        this.idUtilisateur = idUtilisateur;
        this.idMateriel = idMateriel;
        this.typeReclamation = typeReclamation;
        this.description = description;
        this.dateAchat = dateAchat;
        this.dateEnvoi = dateEnvoi;
    }
        public Reclamation(String description, String typeReclamation,Date dateAchat,String idUtilisateur,String idMateriel) {
        this.idUtilisateur = idUtilisateur;
        this.idMateriel = idMateriel;
        this.typeReclamation = typeReclamation;
        this.description = description;
        this.dateAchat = dateAchat;
        this.dateEnvoi = dateEnvoi;
    }

    public Reclamation(String idUtilisateur, String idMateriel, String typeReclamation, String description, Date dateAchat, Date dateEnvoi, String validitée) {
        this.idUtilisateur = idUtilisateur;
        this.idMateriel = idMateriel;
        this.typeReclamation = typeReclamation;
        this.description = description;
        this.dateAchat = dateAchat;
        this.dateEnvoi = dateEnvoi;
        this.validitée = validitée;
    }
    

    public Reclamation(String id, String idUtilisateur,String idMateriel, String typeReclamation,String typeProb, String description, Date dateAchat, Date dateEnvoi, String validitée) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idMateriel = idMateriel;
        this.typeReclamation = typeReclamation;
        this.description = description;
        this.dateAchat = dateAchat;
        this.dateEnvoi = dateEnvoi;
        this.validitée = validitée;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(String idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getValiditée() {
        return validitée;
    }

    public void setValiditée(String validitée) {
        this.validitée = validitée;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        if (this.idMateriel != other.idMateriel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idUtilisateur=" + idUtilisateur + ", idMateriel=" + idMateriel + ", typeReclamation=" + typeReclamation +  ", description=" + description + ", dateAchat=" + dateAchat + ", dateEnvoi=" + dateEnvoi + ", validité=" + validitée + '}';
    }

   


    
}
