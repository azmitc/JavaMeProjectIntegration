/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;
import java.util.Date;




/**
 *
 * @author Amine
 */
public class Randonnee {
    String id;
    String image;
    Date date_debut;
    Date date_retour;
   Time heure_depart;
    Time heure_retour;
    String lieu;
    String circuit;
    String niveau;
    String equipement;
    int note;
    String categorie;
    String description;
    String autorisation;
    String prix;
    String id_guide;
    String id_organisateur;
    String nbrplace;
    String etat;

    public Randonnee( Date date_debut, Date date_retour, Time heure_depart, Time heure_retour, String lieu, String circuit, String niveau, String equipement, int note, String categorie, String description, float prix, String id_guide, String id_organisateur) {
        
        this.date_debut = date_debut;
        this.date_retour = date_retour;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.lieu = lieu;
        this.circuit = circuit;
        this.niveau = niveau;
        this.equipement = equipement;
        this.note = note;
        this.categorie = categorie;
        this.description = description;
        this.id_guide = id_guide;
        this.id_organisateur = id_organisateur;
    }

    public Randonnee(String image,Date date_debut, Date date_retour, Time heure_depart, Time heure_retour, String prix, String nbrplace, String lieu, String circuit, String niveau, String equipement, String description, String categorie,String autorisation, String id_guide, String id_organisateur) {
        this.image=image;    
        this.date_debut = date_debut;
        this.date_retour = date_retour;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.lieu = lieu;
        this.circuit = circuit;
        this.niveau = niveau;
        this.equipement = equipement;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.nbrplace=nbrplace;
        this.autorisation=autorisation;
        this.id_guide = id_guide;
        this.id_organisateur = id_organisateur;
    }

    public String getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(String nbrplace) {
        this.nbrplace = nbrplace;
    }

    public Randonnee(Date date_debut, Date date_retour, Time heure_depart, Time heure_retour, String lieu, String circuit, String niveau, String equipement,  String categorie, String description, String autorisation, String prix, String id_guide, String id_organisateur) {
        this.date_debut = date_debut;
        this.date_retour = date_retour;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.lieu = lieu;
        this.circuit = circuit;
        this.niveau = niveau;
        this.equipement = equipement;
        this.categorie = categorie;
        this.description = description;
        this.autorisation = autorisation;
        this.prix = prix;
        this.id_guide = id_guide;
        this.id_organisateur = id_organisateur;
        
    }
    

    public Randonnee() {
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    

    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public Time getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Time heure_depart) {
        this.heure_depart = heure_depart;
    }

    public Time getHeure_retour() {
        return heure_retour;
    }

    public void setHeure_retour(Time heure_retour) {
        this.heure_retour = heure_retour;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(String autorisation) {
        this.autorisation = autorisation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getId_guide() {
        return id_guide;
    }

    public void setId_guide(String id_guide) {
        this.id_guide = id_guide;
    }

    public String getId_organisateur() {
        return id_organisateur;
    }

    public void setId_organisateur(String id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    @Override
    public String toString() {
        return "Randonnee{" + "id=" + id + ", date_debut=" + date_debut + ", date_retour=" + date_retour + ", heure_depart=" + heure_depart + ", heure_retour=" + heure_retour + ", lieu=" + lieu + ", circuit=" + circuit + ", niveau=" + niveau + ", equipement=" + equipement + ", note=" + note + ", categorie=" + categorie + ", description=" + description + ", prix=" + prix + ", id_guide=" + id_guide + ", id_organisateur=" + id_organisateur + '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
        
    
}
