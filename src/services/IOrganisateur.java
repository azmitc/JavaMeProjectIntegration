/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Organisateur;
import java.util.ArrayList;

/**
 *
 * @author azmi
 */
public interface IOrganisateur {
   public void ajouterOrganisateur(Organisateur p);
    public ArrayList<Organisateur> afficher();
    public void deleteOrganisateur(String cin);
    public void updateOrganisateur(String login, String password);
    public Organisateur findOrganisateurByLogin (String login);
    
}
